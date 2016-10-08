package com.atman.jishang.ui.personal.loginandregister;

import android.os.Bundle;

import com.atman.jishang.R;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;

/**
 * 描述 用户协议
 * 作者 tangbingliang
 * 时间 16/4/13 15:43
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AgreementActivity extends SimpleTitleBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disableLoginCheck();
        setContentView(R.layout.activity_agreement);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        setToolbarTitle(R.string.register_agreement_title);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
