package com.atman.jishang.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.LimitSizeTextWatcher;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.net.model.ResetPassWordResultModel;
import com.atman.jishang.utils.PreferenceUtil;
import com.corelib.common.SubmitHelper;
import com.corelib.widget.MyCleanEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 修改密码
 * 作者 tangbingliang
 * 时间 16/4/19 10:57
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class ResetPassWordActivity extends SimpleTitleBarActivity {

    @Bind(R.id.reserPW_old_password_et)
    MyCleanEditText reserPWOldPasswordEt;
    @Bind(R.id.reserPW_new_password_et)
    MyCleanEditText reserPWNewPasswordEt;
    @Bind(R.id.reserPW_conf_password_et)
    MyCleanEditText reserPWConfPasswordEt;

    private Context mContext = ResetPassWordActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        ButterKnife.bind(this);
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        reserPWOldPasswordEt.addTextChangedListener(new LimitSizeTextWatcher(mContext, reserPWOldPasswordEt, 17, "密码长度为6-17位"));
        reserPWNewPasswordEt.addTextChangedListener(new LimitSizeTextWatcher(mContext, reserPWNewPasswordEt, 17, "密码长度为6-17位"));
        reserPWConfPasswordEt.addTextChangedListener(new LimitSizeTextWatcher(mContext, reserPWConfPasswordEt, 17, "密码长度为6-17位"));
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        setToolbarTitle(R.string.reset_password_title);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof ResetPassWordResultModel) {
            ResetPassWordResultModel mResetPassWordResultModel = (ResetPassWordResultModel) response;
            if (mResetPassWordResultModel.getResult().equals("1")) {
                showToast("密码修改成功，请重新登录");
                BaiYeBaseApplication.mLoginResultModel = null;
                BaiYeBaseApplication.mShopInformationModel = null;
                PreferenceUtil.savePreference(mContext, "PassWord", "");
                Intent mIntent = new Intent();
                setResult(RESULT_OK,mIntent);
                finish();
            } else {
                showToast(mResetPassWordResultModel.getBody().getMessage());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.resetPW_commit_bt})
    public void onClick(View view) {
        String mOldPW = reserPWOldPasswordEt.getText().toString().trim();
        String mNewPW = reserPWNewPasswordEt.getText().toString().trim();
        String mConfPW = reserPWConfPasswordEt.getText().toString().trim();
        switch (view.getId()) {
            case R.id.resetPW_commit_bt:
                if(!chcek(mOldPW, mNewPW, mConfPW)){
                    return;
                }
                getDataManager().resetPassWord(SubmitHelper.getMD5(mOldPW),
                        SubmitHelper.getMD5(mConfPW), ResetPassWordResultModel.class, true);
                break;
        }
    }

    private boolean chcek(String mOldPW, String mNewPW, String mConfPW) {
        if (mOldPW.isEmpty()) {
            showToast(getResources().getString(R.string.reset_password_old_wran));
            return false;
        } else if (mNewPW.isEmpty()) {
            showToast(getResources().getString(R.string.reset_password_new_wran));
            return false;
        } else if (mConfPW.isEmpty()) {
            showToast(getResources().getString(R.string.reset_password_comf_wran));
            return false;
        } else if (mOldPW.length()<6 || mNewPW.length()<6 || mConfPW.length()<6) {
            showToast("密码长度为6-17位");
            return false;
        }else if (!mConfPW.equals(mNewPW)) {
            showToast(getResources().getString(R.string.reset_password_comf_wran2));
            return false;
        }
        return true;
    }
}
