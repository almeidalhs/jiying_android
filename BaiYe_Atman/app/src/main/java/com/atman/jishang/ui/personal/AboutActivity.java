package com.atman.jishang.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.ui.personal.loginandregister.AgreementActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 关于
 * 作者 tangbingliang
 * 时间 16/4/15 14:15
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AboutActivity extends SimpleTitleBarActivity {

    @Bind(R.id.about_version_tx)
    TextView aboutVersionTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        setToolbarTitle(R.string.personal_about);
        aboutVersionTx.setText("即商 " + BaiYeBaseApplication.mVersionName.split("-")[0]);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.about_agreement_tx)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.about_agreement_tx:
                startActivity(new Intent(AboutActivity.this, AgreementActivity.class));
                break;
        }
    }
}
