package com.atman.jishang.ui.personal;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.ShopTypeAdapter;
import com.atman.jishang.interfaces.LimitSizeTextWatcher;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.net.Urls;
import com.atman.jishang.net.model.CreateShopModel;
import com.atman.jishang.net.model.IndustryTypeModel;
import com.atman.jishang.net.model.ShopInformationModel;
import com.atman.jishang.net.upload.UpLoadPicture;
import com.atman.jishang.net.upload.UpLoadPicture.uploadListener;
import com.atman.jishang.utils.UiHelper;
import com.atman.jishang.widget.BottomDialog;
import com.atman.jishang.widget.WheelView.OnWheelChangedListener;
import com.atman.jishang.widget.WheelView.OnWheelScrollListener;
import com.atman.jishang.widget.WheelView.WheelView;
import com.corelib.util.LogUtils;
import com.corelib.util.StringUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 店铺信息
 * 作者 tangbingliang
 * 时间 16/4/15 14:40
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class CreateShopActivity extends SimpleTitleBarActivity implements uploadListener {

    @Bind(R.id.shop_top_tx)
    TextView shopTopTx;
    @Bind(R.id.shop_head_rl)
    RelativeLayout shopHeadRl;
    @Bind(R.id.shop_name_tx)
    EditText shopNameTx;
    @Bind(R.id.shop_phone_tx)
    EditText shopPhoneTx;
    @Bind(R.id.shop_address_tx)
    EditText shopAddressTx;
    @Bind(R.id.shop_type_ll)
    LinearLayout shopTypeLl;
    @Bind(R.id.shop_description_tx)
    EditText shopDescriptionTx;
    @Bind(R.id.shop_commit_bt)
    Button shopCommitBt;
    @Bind(R.id.shop_top_iv)
    ImageView shopTopIv;
    @Bind(R.id.shop_type_tx)
    TextView shopTypeTx;

    private Context mContext = CreateShopActivity.this;
    private IndustryTypeModel mIndustryTypeModel;
    private String mShopName;
    private int mScId = -1;
    private String mShopBanner;
    private String mShopAddress;
    private String mShopTel;
    private String mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createshop);
        ButterKnife.bind(this);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        setToolbarTitle(R.string.create_shop_title_tx);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getmWidth(),
                getmWidth() * 504 / 1504);
        shopHeadRl.setLayoutParams(params);
        shopNameTx.addTextChangedListener(
                new LimitSizeTextWatcher(mContext,shopNameTx,24,"店铺名称不能超过25个汉字"));
        shopAddressTx.addTextChangedListener(
                new LimitSizeTextWatcher(mContext,shopAddressTx,49,"店铺地址不能超过50个汉字"));
        shopDescriptionTx.addTextChangedListener(
                new LimitSizeTextWatcher(mContext,shopDescriptionTx,255,"店铺描述不能超过256个汉字"));
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        getDataManager().getIndustryType(IndustryTypeModel.class, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof IndustryTypeModel) {
            mIndustryTypeModel = (IndustryTypeModel) response;
            if (!mIndustryTypeModel.getResult().equals("1")) {
                showToast("行业类别获取失败");
            }
        } else if (response instanceof CreateShopModel) {
            CreateShopModel mCreateShopModel = (CreateShopModel) response;
            if (!mCreateShopModel.getResult().equals("1")) {
                showToast(mCreateShopModel.getBody().getMessage());
            } else {
                getDataManager().getShopInformation(ShopInformationModel.class, false);
            }
        } else if (response instanceof ShopInformationModel) {
            ShopInformationModel mShopInformationModel = (ShopInformationModel) response;
            if (!mShopInformationModel.getResult().equals("1")) {
                showToast(mShopInformationModel.getBody().getMessage());
            } else {
                BaiYeBaseApplication.mShopInformationModel = mShopInformationModel;
                showToast("店铺创建成功");
                Intent mIntent = new Intent();
                setResult(RESULT_OK, mIntent);
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.shop_head_rl,R.id.shop_top_tx, R.id.shop_type_ll, R.id.shop_commit_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shop_head_rl:
            case R.id.shop_top_tx:
                showAddrPopupWindow(view);
                break;
            case R.id.shop_type_ll:
                showTypePopupWindow(view, mIndustryTypeModel.getBody());
                break;
            case R.id.shop_commit_bt:
                mShopName = shopNameTx.getText().toString().trim();
                mShopAddress = shopAddressTx.getText().toString().trim();
                mShopTel = shopPhoneTx.getText().toString().trim();
                mDescription = shopDescriptionTx.getText().toString().trim();
                if (!checkData()) {
                    return;
                }
                getDataManager().createShop(mShopName, mScId, mShopBanner, mShopAddress, mShopTel
                        , mDescription, CreateShopModel.class, true);
                break;
        }
    }

    private boolean checkData() {
        if (mShopBanner == null || mShopBanner.isEmpty()) {
            showToast("请选择店铺背景");
            return false;
        } else if (mShopName.isEmpty()) {
            showToast("请输入店铺名称");
            return false;
        } else if (mShopTel.isEmpty()) {
            showToast("请输入店铺联系电话");
            return false;
        } else if (!StringUtils.isPhone(mShopTel) && !StringUtils.isCallNumber(mShopTel)) {
            showToast(getResources().getString(R.string.shop_phone_wran));
            return false;
        } else if (mShopAddress.isEmpty()) {
            showToast("请输入店铺地址");
            return false;
        } else if (mScId == -1) {
            showToast("请选择行业类型");
            return false;
        } else if (mDescription.isEmpty()) {
            showToast("请输入店铺描述");
            return false;
        }
        return true;
    }

    private Uri imageUri;//The Uri to store the big bitmap
    private final int CHOOSE_BIG_PICTURE = 1001;
    private final int TAKE_BIG_PICTURE = 1002;
    private final int CROP_BIG_PICTURE = 1003;
    private int outputX = 720;
    private int outputY = 240;
    private int mWidth;
    private int mHeight;

    private void showAddrPopupWindow(View view) {
        BottomDialog.Builder builder = new BottomDialog.Builder(mContext);
        builder.setTitle(Html.fromHtml("<font color=\"#f9464a\">更改店铺背景</font>"));
        builder.setItems(new String[]{"拍照", "从相册中获取"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {//拍照
                    path = UiHelper.photo(mContext, path, TAKE_BIG_PICTURE);
                } else {//选择照片
                    Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                    getAlbum.setType("image/*");
                    startActivityForResult(getAlbum, CHOOSE_BIG_PICTURE);
                }
            }
        });
        builder.setNeutralButton("取消", null);
        builder.show();
    }

    private String path = "";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e("requestCode:" + requestCode + "resultCode:" + resultCode);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == CHOOSE_BIG_PICTURE) {//选择照片
            imageUri = data.getData();
            LogUtils.e("CHOOSE_BIG_PICTURE>>>>imageUri:" + imageUri);
            cropImageUri(imageUri, outputX, outputX, CROP_BIG_PICTURE);
        } else if (requestCode == TAKE_BIG_PICTURE) {
            imageUri = Uri.parse("file:///"+path);
            LogUtils.e("TAKE_BIG_PICTURE>>>>imageUri:" + imageUri);
            cropImageUri(imageUri, outputX, outputY, CROP_BIG_PICTURE);
        } else if (requestCode == CROP_BIG_PICTURE) {
            LogUtils.e("CROP_BIG_PICTURE>>>>imageUri:" + imageUri);
            if (imageUri != null) {
                new UpLoadPicture(mContext,Urls.UP_STOREIMG, this).execute(imageUri);
            }
        }
    }

    //裁减照片
    private void cropImageUri(Uri uri, int outputX, int outputY, int requestCode) {
        if (uri == null) {
            return;
        }
        LogUtils.e("outputX:" + outputX + ",outputY:" + outputY);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 3);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        imageUri = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void success(String imageUrl) {
        mShopBanner = imageUrl;
        setBitmapToImageView(shopTopIv, Urls.HEADIMG_BEFOR + mShopBanner, R.mipmap.personal_top_bg);
        showToast("上传成功");
    }

    @Override
    public void fail(String failInfo) {
        showToast(failInfo);
    }

    private WheelView mTypeWheel;
    private ShopTypeAdapter mShopTypeAdapter;
    private String mScName;
    private int selcteId = 0;
    public void showTypePopupWindow(View view, final List<IndustryTypeModel.BodyEntity> mList) {

        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(mContext).inflate(R.layout.wheel_select_shop_type_view, null);

        final PopupWindow mSelectTypePop = new PopupWindow(contentView,
                RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT, true);

        mTypeWheel = (WheelView) contentView.findViewById(R.id.popwin_select_wv);
        Button pop_cancel = (Button) contentView.findViewById(R.id.pop_cancel);
        Button pop_ok = (Button) contentView.findViewById(R.id.pop_ok);
        mShopTypeAdapter = new ShopTypeAdapter(mContext, mTypeWheel, mList, 0, 0, 0);
        mTypeWheel.setVisibleItems(5);
        mTypeWheel.setViewAdapter(mShopTypeAdapter);
        mTypeWheel.setCurrentItem(0);
        mTypeWheel.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                mTypeWheel.setViewAdapter(mShopTypeAdapter);
            }
        });
        mTypeWheel.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                mScName = (String) mShopTypeAdapter.getItemText(wheel.getCurrentItem());
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).getScName().equals(mScName)) {
                        mScId = mList.get(i).getId();
                        shopTypeTx.setText(mScName);
                        break;
                    }
                }
            }
        });
        pop_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectTypePop != null) {
                    mSelectTypePop.dismiss();
                }
            }
        });
        pop_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectTypePop != null) {
                    mSelectTypePop.dismiss();
                }
//                initPopwindowData(mList.get(selcteId));
            }
        });

        mSelectTypePop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);
        mSelectTypePop.setTouchable(true);
        mSelectTypePop.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                return false;
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        mSelectTypePop.setBackgroundDrawable(getResources().getDrawable(R.drawable.pop_bg));
        // 设置好参数之后再show
        if (mList!=null && mList.size()>0) {
            selcteId = 0;
            if (shopTypeTx.getText().toString().isEmpty()){
                initPopwindowData(mList.get(selcteId));
            }
            mSelectTypePop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }

    }

    private void initPopwindowData(IndustryTypeModel.BodyEntity mList){
        mScName = mList.getScName();
        mScId = mList.getId();
        shopTypeTx.setText(mScName);
    }
}
