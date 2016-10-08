package com.atman.jishang.ui.circle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

import com.atman.jishang.R;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.UiHelper;
import com.atman.jishang.widget.BottomDialog;
import com.choicepicture_library.ImageGridActivity;
import com.choicepicture_library.PhotoActivity;
import com.choicepicture_library.adapters.GridAdapter;
import com.choicepicture_library.tools.Bimp;
import com.choicepicture_library.tools.UploadUtil;
import com.corelib.widget.MyCleanEditText;
import com.corelib.widget.MyGridView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/6/20 14:36
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class PublishActivity extends SimpleTitleBarActivity
        implements UploadUtil.OnUploadProcessListener {

    @Bind(R.id.publish_content_et)
    MyCleanEditText publishContentEt;
    @Bind(R.id.noScrollgridview)
    MyGridView noScrollgridview;

    private Context mContext = PublishActivity.this;
    private GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        Bimp.drr.clear();
        Bimp.drr_or.clear();
        Bimp.bmp.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter!=null) {
            noScrollgridview.setAdapter(adapter);
            adapter.update();
        }
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle("");
        showRightTV("发送").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(">>>");
            }
        });
        initGridView();
    }

    private void initGridView() {
        noScrollgridview = (MyGridView) findViewById(R.id.noScrollgridview);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));//选中的时候为透明色

        adapter = new GridAdapter(this);//初始化适配器
        adapter.update();

        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg2 == Bimp.bmp.size()) {
                    if (getCurrentFocus() != null) {
                        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus()
                                .getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    showAddrPopupWindow(arg1);
                } else {
                    Intent intent = new Intent(mContext,PhotoActivity.class);
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });
    }

    private Uri imageUri;//The Uri to store the big bitmap
    private final int CHOOSE_BIG_PICTURE = 1001;
    private final int TAKE_BIG_PICTURE = 1002;
    private final int CROP_BIG_PICTURE = 1003;
    private void showAddrPopupWindow(View view) {
        BottomDialog.Builder builder = new BottomDialog.Builder(mContext);
        builder.setTitle(Html.fromHtml("<font color=\"#f9464a\">选择商品图片</font>"));
        builder.setItems(new String[]{"拍照", "从相册中获取"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {//拍照
                    path = UiHelper.photo(mContext, path, TAKE_PICTURE);
                } else {//选择照片
                    startActivityForResult(new Intent(mContext, ImageGridActivity.class), CHOOSE_BIG_PICTURE);
                }
            }
        });
        builder.setNeutralButton("取消", null);
        builder.show();
    }

    private static final int TAKE_PICTURE = 0x000000;
    private String path = "";

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
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
        Bimp.num = 0;
        Bimp.drr.clear();
        Bimp.drr_or.clear();
        Bimp.bmp.clear();
        super.onDestroy();
    }

    @Override
    public void onUploadDone(int responseCode, String message) {
//        GoodsUpLoadImgMpdel mGoodsUpLoadImgMpdel = new GoodsUpLoadImgMpdel();
//        mGoodsUpLoadImgMpdel = new Gson().fromJson(message, GoodsUpLoadImgMpdel.class);
//        listImg.clear();
//        for(int i=0;i<mGoodsUpLoadImgMpdel.getBody().size();i++){
//            if (i==0) {
//                goodsImage = mGoodsUpLoadImgMpdel.getBody().get(i).getUrl();
//            }
//            listImg.add(mGoodsUpLoadImgMpdel.getBody().get(i).getUrl());
//        }
//        getDataManager().createGoods(goodsSerial, goodsName, stcId, goodsImage, listImg,
//                goodsSpec, goodsDescription, goodsStorePrice, price, storage, CreateGoodsModel.class, true);
    }

    @Override
    public void onUploadProcess(int uploadSize) {

    }

    @Override
    public void initUpload(int fileSize) {

    }

    @Override
    public void onUploadFail(String e) {
        showToast(e);
        cancelLoading();
    }
}
