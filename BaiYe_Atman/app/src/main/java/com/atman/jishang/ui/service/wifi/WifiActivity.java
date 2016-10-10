package com.atman.jishang.ui.service.wifi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.atman.jishang.R;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;

/**
 * Created by tangbingliang on 16/10/10.
 */

public class WifiActivity extends SimpleTitleBarActivity {

    private Context mContext = WifiActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
    }

    public static Intent bulidIntent(Context context, String title){
        Intent intent = new Intent(context, WifiActivity.class);
        intent.putExtra("title", title);
        return intent;
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);

        setToolbarTitle(getIntent().getStringExtra("title"));
        showRightTV("制码").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("制码");
            }
        });
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
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
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
