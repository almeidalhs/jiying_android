package com.atman.jishang.ui.service.code;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.atman.jishang.R;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;

/**
 * Created by tangbingliang on 16/10/11.
 */

public class CreateQRCodeActivity extends SimpleTitleBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createqrcode);
    }

    public static Intent buildIntent (Context context) {
        Intent intent = new Intent(context, CreateQRCodeActivity.class);
        return intent;
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);

        setToolbarTitle("制作二维码");
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
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
