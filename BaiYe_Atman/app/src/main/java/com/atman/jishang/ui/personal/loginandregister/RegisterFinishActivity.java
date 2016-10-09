package com.atman.jishang.ui.personal.loginandregister;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.TimeCount;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述 注册界面
 * 作者 tangbingliang
 * 时间 16/4/13 10:39
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class RegisterFinishActivity extends SimpleTitleBarActivity {

    @Bind(R.id.register_time_tx)
    TextView registerTimeTx;

    private Context mContext = RegisterFinishActivity.this;
    private TimeCount timeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disableLoginCheck();
        setContentView(R.layout.acticity_registerfinish);
        ButterKnife.bind(this);
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        setToolbarTitle(R.string.register_title);
//        timeCount = new TimeCount(registerTimeTx, 5 * 1000, 1000);
//        timeCount.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mIntent = new Intent();
                setResult(RESULT_OK,mIntent);
                finish();
            }
        }, 5000);
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
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
