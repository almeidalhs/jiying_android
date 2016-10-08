package com.atman.jishang.ui.personal.loginandregister;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.LimitSizeTextWatcher;
import com.atman.jishang.ui.base.BaiYeBaseActivity;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.net.model.CodeCheckModel;
import com.atman.jishang.net.model.ResetPassWordResultModel;
import com.atman.jishang.net.model.SeedMessageModel;
import com.atman.jishang.utils.TimeCount;
import com.corelib.common.SubmitHelper;
import com.corelib.util.StringUtils;
import com.corelib.widget.MyCleanEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 重新设置密码
 * 作者 tangbingliang
 * 时间 16/4/14 17:40
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class ResetWordActivity extends SimpleTitleBarActivity {

    @Bind(R.id.resetPW_username_et)
    MyCleanEditText resetPWUsernameEt;
    @Bind(R.id.resetPW_password_et)
    MyCleanEditText resetPWPasswordEt;
    @Bind(R.id.resetPW_code_et)
    MyCleanEditText resetPWCodeEt;
    @Bind(R.id.bt_getsms)
    TextView btGetsms;
    @Bind(R.id.resetPW_next_bt)
    Button resetPWNextBt;

    private Context mContext = ResetWordActivity.this;
    private String mPhoneNumber,mPassWord;
    private TimeCount timeCount;
    private static String IntentTag = "phonenumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disableLoginCheck();
        setContentView(R.layout.acticity_resetpassword);
        ButterKnife.bind(this);
    }

    /**
     * 获取Intent
     *
     * @param context
     * @param phonenumber 手机号码
     * @return
     */
    public static Intent createIntent(Context context, String phonenumber) {
        Intent intent = new Intent(context, ResetWordActivity.class);
        intent.putExtra(IntentTag, phonenumber);
        return intent;
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        resetPWUsernameEt.addTextChangedListener(new LimitSizeTextWatcher(mContext, resetPWUsernameEt, 11, "账号不能超过11位"));
        resetPWPasswordEt.addTextChangedListener(new LimitSizeTextWatcher(mContext, resetPWPasswordEt, 17, "密码长度为6-17位"));
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        setToolbarTitle(R.string.resetPW_title);
        timeCount = new TimeCount(btGetsms, 1 * 60 * 1000, 1000, resetPWUsernameEt);
        resetPWUsernameEt.setText(getIntent().getStringExtra(IntentTag));
    }

    @Override
    public void onResponse(Object response) {

        if (response instanceof SeedMessageModel) {
            super.onResponse(response);
            SeedMessageModel mSeedMessageModel = (SeedMessageModel) response;
            if (mSeedMessageModel.getResult().equals("1")) {
                showToast("验证码发送成功！");
                timeCount.start();
            } else {
                showToast(mSeedMessageModel.getBody().getMessage());
            }
        } else if (response instanceof CodeCheckModel) {
            CodeCheckModel mCodeCheckModel = (CodeCheckModel) response;
            if (mCodeCheckModel.getResult().equals("1")) {
                getDataManager().seedMessageForgotPwd(mPhoneNumber, SubmitHelper.getMD5(mPassWord), ResetPassWordResultModel.class, false);
            } else {
                super.onResponse(response);
                showToast(mCodeCheckModel.getBody());
            }
        } else if (response instanceof ResetPassWordResultModel) {
            super.onResponse(response);
            ResetPassWordResultModel mResetPassWordResultModel = (ResetPassWordResultModel) response;
            if (mResetPassWordResultModel.getResult().equals("1")) {
                showToast("密码修改成功，请重新登录");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent mIntent = new Intent();
                        setResult(RESULT_OK,mIntent);
                        finish();
                    }
                }, 2000);
            } else {
                showToast(mResetPassWordResultModel.getBody().getMessage());
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.bt_getsms, R.id.resetPW_next_bt})
    public void onClick(View view) {
        String us = resetPWUsernameEt.getText().toString().trim();
        String newPW = resetPWPasswordEt.getText().toString().trim();
        String code = resetPWCodeEt.getText().toString().trim();
        switch (view.getId()) {
            case R.id.bt_getsms:
                if (!checkUserName(us, 1)) {
                    return;
                }
                getDataManager().seedMessageForgotPwd(us, SeedMessageModel.class, true);
                break;
            case R.id.resetPW_next_bt:
                if (us.equals(BaiYeBaseApplication.mExperienceUS)) {
                    showToast("对不起，您没有权限执行此操作");
                    return;
                }
                if (!checkUserName(us, 1)) {
                    return;
                } else if (!checkUserName(newPW, 3)) {
                    return;
                } else if (!checkUserName(code, 2)) {
                    return;
                }
                getDataManager().codeCheck(us, code, CodeCheckModel.class, true);
                mPhoneNumber = us;
                mPassWord = newPW;
                break;
        }
    }

    private boolean checkUserName(String str, int num) {
        if (num == 1) {
            if (str.isEmpty()) {
                showToast(getResources().getString(R.string.login_username_hint));
                return false;
            } else if (!StringUtils.isPhone(str)) {
                showToast(getResources().getString(R.string.login_username_right));
                return false;
            }
        } else if (num == 2) {
            if (str.isEmpty()) {
                showToast(getResources().getString(R.string.register_code_wran_one));
                return false;
            } else if (str.length() != 6) {
                showToast(getResources().getString(R.string.register_code_wran_two));
                return false;
            } else if (str.length() < 6) {
                showToast("密码长度为6-17位");
                return false;
            }
        } else if (num == 3) {
            if (str.isEmpty()) {
                showToast(getResources().getString(R.string.login_password_hint));
                return false;
            } else if (str.length() < 6) {
                showToast("密码长度为6-17位");
                return false;
            }
        }
        return true;
    }
}
