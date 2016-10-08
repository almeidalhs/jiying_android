package com.atman.jishang.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.TimeCountInterface;
import com.atman.jishang.net.downfile.DownloadFile;
import com.atman.jishang.net.model.LoginResultModel;
import com.atman.jishang.net.model.RegisterModel;
import com.atman.jishang.ui.base.BaiYeBaseActivity;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.manager.WebPageActivity;
import com.atman.jishang.utils.PreferenceUtil;
import com.atman.jishang.utils.TimeCount;
import com.corelib.base.BaseApplication;
import com.corelib.common.SubmitHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by loyee on 16-1-5.
 */
public class SplashActivity extends BaiYeBaseActivity implements TimeCountInterface {
    @Bind(R.id.splash_iv_one)
    ImageView splashIvOne;
    @Bind(R.id.splash_iv_two)
    ImageView splashIvTwo;
    @Bind(R.id.jump_tx)
    TextView jumpTx;

    private boolean isInit = true;
    private TimeCount timeCount;
    private Context mContext = SplashActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disableLoginCheck();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setSwipeBackEnable(false);
        timeCount = new TimeCount(5 * 1000, 1000, this);
    }

    private void redirectTo() {
        if (isInit) {
            isInit = false;
            boolean isLearned = PreferenceUtil.getBoolPreferences(this, "isLearned");
            if (!isLearned) {
                goGuide();
            } else {
                goHome();
            }
        }
    }

    private void goHome() {
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    private void goGuide() {
//        startActivity(new Intent(this, GuideActivity.class));
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String us = PreferenceUtil.getPreferences(mContext, PreferenceUtil.PARM_US);
        String pw = PreferenceUtil.getPreferences(mContext, PreferenceUtil.PARM_PW);
        if (!us.isEmpty() && !pw.isEmpty()) {
            getDataManager().login(us, SubmitHelper.getMD5(pw), LoginResultModel.class, false);
        }
        new DownloadFile(SplashActivity.this, splashIvOne, splashIvTwo)
                .execute("http://www.justing.com/baiye/config.json");
        timeCount.start();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                toMainActivity();
//            }
//        }, 5000);
        getDataManager().register("", SubmitHelper.getMD5(pw)
                , "", RegisterModel.class, true);
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof LoginResultModel) {
            LoginResultModel mLoginResultModel = (LoginResultModel) response;
            if (mLoginResultModel.getResult().equals("1")) {
                BaiYeBaseApplication.mLoginResultModel = mLoginResultModel;
                BaseApplication.mSessionId = mLoginResultModel.getBody().getMemberQqInfo();
            }
        }
    }

    @OnClick(R.id.jump_tx)
    public void onClick() {
        timeCount.cancel();
        toMainActivity();
    }

    private void toMainActivity() {
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onTimeOut() {
        toMainActivity();
    }

    @Override
    public void onBackTick(long millisUntilFinished) {
        jumpTx.setText("跳过 " + millisUntilFinished / 1000 + " s");
    }

    @OnClick({R.id.splash_iv_one, R.id.splash_iv_two})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.splash_iv_one:
            case R.id.splash_iv_two:
                timeCount.cancel();
                startActivity(WebPageActivity.buildIntent(SplashActivity.this, BaiYeBaseApplication.mWEB_URL, "",true));
                finish();
                break;
        }
    }
}
