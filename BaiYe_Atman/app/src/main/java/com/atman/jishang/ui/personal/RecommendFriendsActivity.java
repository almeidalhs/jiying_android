package com.atman.jishang.ui.personal;

import android.os.Bundle;

import com.atman.jishang.R;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;

/**
 * 描述 推荐朋友
 * 作者 tangbingliang
 * 时间 16/4/15 14:36
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class RecommendFriendsActivity extends SimpleTitleBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendfriends);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        setToolbarTitle(R.string.personal_recommend);
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
