package com.atman.jishang.ui.personal.loginandregister;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.interfaces.LimitSizeTextWatcher;
import com.atman.jishang.net.model.LoginResultModel;
import com.atman.jishang.ui.MainActivity;
import com.atman.jishang.utils.PreferenceUtil;
import com.corelib.base.BaseApplication;
import com.corelib.common.SubmitHelper;
import com.corelib.util.StringUtils;
import com.corelib.widget.MyCleanEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 登录界面
 * 作者 tangbingliang
 * 时间 16/4/11 10:22
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class LoginActivity extends SimpleTitleBarActivity {

    @Bind(R.id.login_forgetPW_tx)
    TextView loginForgetPWTx;
    @Bind(R.id.login_username_et)
    MyCleanEditText loginUsernameEt;
    @Bind(R.id.login_password_et)
    MyCleanEditText loginPasswordEt;
    @Bind(R.id.login_login_bt)
    Button loginLoginBt;
    @Bind(R.id.login_register_bt)
    Button loginRegisterBt;
    @Bind(R.id.CS_telephone)
    TextView CSTelephone;
    @Bind(R.id.login_top_iv)
    ImageView loginTopIv;

    private Context mContext = LoginActivity.this;
    private final int toRegisterSeed = 601;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disableLoginCheck();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        hideTitleBar();
        setSwipeBackEnable(false);
    }

    //在onCreate（）之后，用于初始化
    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(getmWidth(),
                getmWidth() * 370 / 750);
        loginTopIv.setLayoutParams(params);
        loginUsernameEt.addTextChangedListener(new LimitSizeTextWatcher(mContext, loginUsernameEt, 11, "账号不能超过11位"));
        loginPasswordEt.addTextChangedListener(new LimitSizeTextWatcher(mContext, loginPasswordEt, 17, "密码长度为6-17位"));

        String us = PreferenceUtil.getPreferences(mContext, PreferenceUtil.PARM_US);
        String pw = PreferenceUtil.getPreferences(mContext, PreferenceUtil.PARM_PW);
        loginUsernameEt.setText(us);
        loginPasswordEt.setText(pw);
        if (!us.isEmpty() && !pw.isEmpty()) {
            getDataManager().login(us, SubmitHelper.getMD5(pw), LoginResultModel.class, true);
        }
    }

    /**
     * 获取Intent
     *
     * @param context
     * @param successIntent 登陆成功后跳转的activity 的Intent
     * @return
     */
    public static Intent createIntent(Context context, Intent successIntent) {
        Intent intent = new Intent(context, LoginActivity.class);
        if (successIntent != null) {
            intent.putExtra("successIntent", successIntent);
        }
        return intent;
    }

    @OnClick({R.id.login_login_bt, R.id.login_register_bt
            , R.id.login_forgetPW_tx, R.id.CS_telephone, R.id.experience_tx})
    public void onClick(View view) {
        String us = loginUsernameEt.getText().toString().trim();
        String pw = loginPasswordEt.getText().toString().trim();
        switch (view.getId()) {
            case R.id.login_login_bt:
                if (!checkUserName(us, 1)) {
                    return;
                } else if (!checkUserName(pw, 2)) {
                    return;
                }
                PreferenceUtil.savePreference(mContext, PreferenceUtil.PARM_US, us);
                PreferenceUtil.savePreference(mContext, PreferenceUtil.PARM_PW, pw);
//                getDataManager().login("13500000043", "343b1c4a3ea721b2d640fc8700db0e36", LoginResultModel.class, true);
                getDataManager().login(us, SubmitHelper.getMD5(pw), LoginResultModel.class, true);
                break;
            case R.id.login_register_bt:
                startActivityForResult(RegisterSeedMessageActivity.createIntent(mContext, us), toRegisterSeed);
                break;
            case R.id.login_forgetPW_tx:
                startActivity(ResetWordActivity.createIntent(mContext, us));
                break;
            case R.id.CS_telephone:
                toPhone(mContext, getResources().getString(R.string.service_telephone));
                break;
            case R.id.experience_tx:
                if (BaiYeBaseApplication.mExperienceUS!=null && !BaiYeBaseApplication.mExperienceUS.isEmpty()) {
                    getDataManager().login(BaiYeBaseApplication.mExperienceUS, BaiYeBaseApplication.mExperiencePW, LoginResultModel.class, true);
                }
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
                showToast(getResources().getString(R.string.login_password_hint));
                return false;
            } else if (str.length() < 6) {
                showToast("密码长度为6-17位");
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == toRegisterSeed) {
            toMainActivity();
        }
    }

    private void toMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    //初始化访问网络
    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    //接口调用返回
    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof LoginResultModel) {
            LoginResultModel mLoginResultModel = (LoginResultModel) response;
            if (mLoginResultModel.getResult().equals("1")) {
                showToast("登录成功！");
                BaiYeBaseApplication.mLoginResultModel = mLoginResultModel;
                BaseApplication.mSessionId = mLoginResultModel.getBody().getMemberQqInfo();
                toMainActivity();
            } else {
                showToast(mLoginResultModel.getBody().getMessage());
            }
        }
    }
}
