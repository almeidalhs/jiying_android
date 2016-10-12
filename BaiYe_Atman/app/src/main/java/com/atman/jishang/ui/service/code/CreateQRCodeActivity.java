package com.atman.jishang.ui.service.code;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.model.CreateQRCodeModel;
import com.atman.jishang.net.model.ModuleListModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.widget.XCFlowLayout;
import com.atman.jishang.widget.YLBDialog;
import com.corelib.util.DensityUtil;
import com.corelib.util.LogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tangbingliang on 16/10/11.
 */

public class CreateQRCodeActivity extends SimpleTitleBarActivity {

    @Bind(R.id.flowlayout)
    XCFlowLayout flowlayout;
    @Bind(R.id.createcode_qrcode_iv)
    ImageView createcodeQrcodeIv;
    @Bind(R.id.createcode_content_et)
    EditText createcodeContentEt;

    private Context mContext = CreateQRCodeActivity.this;

    private int qrcodeType;
    private int singleType;
    private List<ModuleListModel> listModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createqrcode);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context, int qrcodeType, int singleType, List<ModuleListModel> list) {
        Intent intent = new Intent(context, CreateQRCodeActivity.class);
        intent.putExtra("qrcodeType", qrcodeType);
        intent.putExtra("singleType", singleType);
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) list);
        intent.putExtras(bundle);
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

        qrcodeType = getIntent().getIntExtra("qrcodeType", -1);
        singleType = getIntent().getIntExtra("singleType", -1);
        listModel = (List<ModuleListModel>) getIntent().getSerializableExtra("list");

        LogUtils.e("qrcodeType:"+qrcodeType+",singleType:"+singleType+",listModel.size():"+listModel.size());

        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = YLBDialog.dip2px(mContext, 0);
        lp.rightMargin = YLBDialog.dip2px(mContext, 0);
        lp.topMargin = YLBDialog.dip2px(mContext, 0);
        lp.bottomMargin = YLBDialog.dip2px(mContext, 0);

        Drawable drawable = getResources().getDrawable(R.mipmap.qrcode_select);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

        flowlayout.removeAllViews();
        for (int i = 0; i < listModel.size(); i++) {
            TextView view = new TextView(this);
            view.setText(" " + listModel.get(i).getName());
            view.setGravity(Gravity.CENTER_VERTICAL);
            view.setTextColor(getResources().getColor(R.color.color_727272));
            view.setCompoundDrawables(drawable, null, null, null);
            view.setPadding(YLBDialog.dip2px(mContext, 5), YLBDialog.dip2px(mContext, 0),
                    YLBDialog.dip2px(mContext, 5), YLBDialog.dip2px(mContext, 0));
            view.setTag(i);
            view.setTextSize(14);
            flowlayout.addView(view, lp);
        }

        int width = getmWidth() - DensityUtil.dp2px(mContext, 110);
        LinearLayout.LayoutParams params_top = new LinearLayout.LayoutParams(width, width);
        createcodeQrcodeIv.setLayoutParams(params_top);
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        getDataManager().createCode(qrcodeType, singleType, listModel, CreateQRCodeModel.class, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof CreateQRCodeModel) {
            CreateQRCodeModel mCreateQRCodeModel = (CreateQRCodeModel) response;
            if (mCreateQRCodeModel.getResult().equals("1")) {
                ImageLoader.getInstance().displayImage(mCreateQRCodeModel.getBody().getImg(),
                        createcodeQrcodeIv, BaiYeBaseApplication.getApp().getOptionsHead());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.createcode_buttom_left_ll, R.id.createcode_buttom_right_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createcode_buttom_left_ll:
                break;
            case R.id.createcode_buttom_right_ll:
                break;
        }
    }
}
