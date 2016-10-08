package com.atman.jishang.ui.personal.loginandregister;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.net.model.CodeCheckModel;
import com.atman.jishang.net.model.SeedMessageModel;
import com.atman.jishang.utils.TimeCount;
import com.corelib.util.StringUtils;
import com.corelib.widget.MyCleanEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 注册界面
 * 作者 tangbingliang
 * 时间 16/4/13 10:39
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class RegisterSeedMessageActivity extends SimpleTitleBarActivity {

    @Bind(R.id.register_phone_et)
    MyCleanEditText registerPhoneEt;
    @Bind(R.id.register_code_et)
    MyCleanEditText registerCodeEt;
    @Bind(R.id.bt_getsms)
    TextView btGetsms;
    @Bind(R.id.CS_telephone)
    TextView CSTelephone;
    @Bind(R.id.register_agreement_tx)
    TextView registerAgreementTx;
    @Bind(R.id.register_next_bt)
    Button registerNextBt;

    /**
     * 获取验证码
     */
    private TimeCount timeCount;
    private Context mContext = RegisterSeedMessageActivity.this;
    private String mPhoneNumber;
    private final int toRegisterSetPW = 602;
    private static String IntentTag = "phonenumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disableLoginCheck();
        setContentView(R.layout.acticity_registerseedmessage);
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
        Intent intent = new Intent(context, RegisterSeedMessageActivity.class);
        intent.putExtra(IntentTag, phonenumber);
        return intent;
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        setToolbarTitle(R.string.register_title);
        timeCount = new TimeCount(btGetsms, 1 * 60 * 1000, 1000, registerPhoneEt);
        registerPhoneEt.setText(getIntent().getStringExtra(IntentTag));
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof SeedMessageModel) {
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
                startActivityForResult(RegisterSetPassWordActivity.createIntent(mContext, mPhoneNumber), toRegisterSetPW);
            } else {
                showToast(mCodeCheckModel.getBody());
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

    @OnClick({R.id.bt_getsms, R.id.CS_telephone
            , R.id.register_agreement_tx,R.id.register_next_bt})
    public void onClick(View view) {
        String us = registerPhoneEt.getText().toString().trim();
        String code = registerCodeEt.getText().toString().trim();
        switch (view.getId()) {
            case R.id.bt_getsms:
                if (!checkUserName(us, 1)) {
                    return;
                }
                getDataManager().seedMessage(us, SeedMessageModel.class, true);
                break;
            case R.id.CS_telephone:
                toPhone(mContext,getResources().getString(R.string.service_telephone));
                break;
            case R.id.register_agreement_tx:
                startActivity(new Intent(mContext,AgreementActivity.class));
                break;
            case R.id.register_next_bt:
                if (!checkUserName(us, 1)) {
                    return;
                } else if (!checkUserName(code, 2)) {
                    return;
                }
                getDataManager().codeCheck(us, code, CodeCheckModel.class, true);
                mPhoneNumber = us;
//                startActivityForResult(RegisterSetPassWordActivity.createIntent(mContext, us), toRegisterSetPW);
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
        if (requestCode == toRegisterSetPW) {
            Intent mIntent = new Intent();
            setResult(RESULT_OK,mIntent);
            finish();
        }

    }
}
