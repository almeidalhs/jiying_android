package com.atman.jishang.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.model.ShopInformationModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/12 18:30
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class ShopInformationActivity extends SimpleTitleBarActivity {

    @Bind(R.id.shopinfo_name)
    TextView shopinfoName;
    @Bind(R.id.shopinfo_phone)
    TextView shopinfoPhone;
    @Bind(R.id.shopinfo_address)
    TextView shopinfoAddress;
    @Bind(R.id.shopinfo_type)
    TextView shopinfoType;
    @Bind(R.id.shopinfo_description)
    TextView shopinfoDescription;

    private TextView mRightTx;
    private LinearLayout mLiftLl;
    private Context mContext = ShopInformationActivity.this;
    private boolean canEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopinformation);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context, boolean canEdit){
        Intent intent = new Intent(context, ShopInformationActivity.class);
        intent.putExtra("canEdit", canEdit);
        return intent;
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle(R.string.personal_shopInfo);
        if (!getIntent().getBooleanExtra("canEdit",false)) {
            mRightTx = showRightTV("编辑");
            mRightTx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(mContext, EditShopActivity.class));
                }
            });
        }

        mLiftLl = getLlBack();
        mLiftLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    private void back() {
        Intent mIntent = new Intent();
        setResult(RESULT_OK, mIntent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataManager().getShopInformation(ShopInformationModel.class, true);
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof ShopInformationModel) {
            ShopInformationModel mShopInformationModel = (ShopInformationModel) response;
            if (!mShopInformationModel.getResult().equals("1")) {
                showToast(mShopInformationModel.getBody().getMessage());
            } else {
                BaiYeBaseApplication.mShopInformationModel = mShopInformationModel;
                shopinfoName.setText(mShopInformationModel.getBody().getStoreName());
                shopinfoPhone.setText(mShopInformationModel.getBody().getStoreTel());
                shopinfoAddress.setText(mShopInformationModel.getBody().getStoreAddress());
                shopinfoType.setText(mShopInformationModel.getBody().getScName());
                shopinfoDescription.setText(mShopInformationModel.getBody().getDescription());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            back();
        }
        return super.onKeyDown(keyCode, event);
    }
}
