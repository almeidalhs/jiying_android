package com.atman.jishang.ui.manager.goods;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.atman.jishang.R;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述 条形码/二维码扫描
 * 作者 tangbingliang
 * 时间 16/4/29 15:47
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class ScanCodeActivity extends SimpleTitleBarActivity {

    @Bind(R.id.zxing_barcode_scanner)
    CompoundBarcodeView zxingBarcodeScanner;

    private CaptureManager capture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scancode);
        ButterKnife.bind(this);

        capture = new CaptureManager(this, zxingBarcodeScanner);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle(R.string.add_goods_scancode_title);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
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
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return zxingBarcodeScanner.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}
