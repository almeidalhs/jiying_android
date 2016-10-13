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
import com.atman.jishang.interfaces.IndustryTitleConfigInterface;
import com.atman.jishang.interfaces.LimitSizeTextWatcher;
import com.atman.jishang.net.Urls;
import com.atman.jishang.net.model.EditShopInfoModel;
import com.atman.jishang.net.model.GetIndustryTitleConfigModel;
import com.atman.jishang.net.model.IndustryTypeModel;
import com.atman.jishang.net.model.ShopInformationModel;
import com.atman.jishang.net.upload.UpLoadPicture;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.UiHelper;
import com.atman.jishang.widget.BottomDialog;
import com.atman.jishang.widget.WheelView.OnWheelChangedListener;
import com.atman.jishang.widget.WheelView.OnWheelScrollListener;
import com.atman.jishang.widget.WheelView.WheelView;
import com.atman.jishang.widget.dateselect.TimePicker;
import com.atman.jishang.widget.dateselect.TimeSelectDialogUtil;
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
public class EditShopActivity extends SimpleTitleBarActivity implements UpLoadPicture.uploadListener {

    @Bind(R.id.shop_head_rl)
    RelativeLayout shopHeadRl;
    @Bind(R.id.shop_name_tx)
    EditText shopNameTx;
    @Bind(R.id.shop_phone_tx)
    EditText shopPhoneTx;
    @Bind(R.id.shop_address_tx)
    EditText shopAddressTx;
    @Bind(R.id.shop_type_tx)
    TextView shopTypeTx;
    @Bind(R.id.shop_time_tx)
    TextView shopTimeTx;
    @Bind(R.id.shop_type_ll)
    LinearLayout shopTypeLl;
    @Bind(R.id.shop_description_tx)
    EditText shopDescriptionTx;
    @Bind(R.id.shop_top_iv)
    ImageView shopTopIv;
    @Bind(R.id.shop_name_title_tx)
    TextView shopNameTitleTx;
    @Bind(R.id.shop_address_title_tx)
    TextView shopAddressTitleTx;
    @Bind(R.id.shop_description_title_tx)
    TextView shopDescriptionTitleTx;

    private Context mContext = EditShopActivity.this;
    private ShopInformationModel mShopInformationModel;
    private IndustryTypeModel mIndustryTypeModel;
    private String mShopName;
    private int mScId = -1;
    private String mShopBanner;
    private String mShopAddress;
    private String mShopTel;
    private String mDescription;
    private String mTime;
    private int mId;

    private int numDataOne = -1;
    private String hOne = "00";
    private String mOne = "00";
    private String hTwo = "00";
    private String mTwo = "00";

    private String shopTitleStr = "店铺信息";
    private String shopNameTitleStr = "店铺名称";
    private String shopAddressTitleStr = "店铺地址";
    private String shopDescriptionTitleStr = "店铺描述";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editshop);
        ButterKnife.bind(this);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();

        if (BaiYeBaseApplication.mGetIndustryTitleConfigModel!=null) {
            List<GetIndustryTitleConfigModel.BodyBean> temp = BaiYeBaseApplication.mGetIndustryTitleConfigModel.getBody();
            for (int i=0;i< temp.size();i++) {
                if (temp.get(i).getPageNum() == IndustryTitleConfigInterface.ConfigShopInformationId
                        && temp.get(i).getTitle()!=null) {
                    shopTitleStr = temp.get(i).getTitle();
                    for (int j=0;j<temp.get(i).getConfPageBodyList().size();j++) {
                        if (temp.get(i).getConfPageBodyList().get(j).getSort()==1) {
                            shopNameTitleStr = temp.get(i).getConfPageBodyList().get(j).getTitle();
                        } else if (temp.get(i).getConfPageBodyList().get(j).getSort()==2) {
                            shopAddressTitleStr = temp.get(i).getConfPageBodyList().get(j).getTitle();
                        } else if (temp.get(i).getConfPageBodyList().get(j).getSort()==3) {
                            shopDescriptionTitleStr = temp.get(i).getConfPageBodyList().get(j).getTitle();
                        }
                    }
                }
            }
        }

        setToolbarTitle(shopTitleStr);
        shopNameTitleTx.setText(shopNameTitleStr);
        shopNameTx.setHint(shopNameTitleStr);
        shopAddressTitleTx.setText(shopAddressTitleStr);
        shopAddressTx.setHint(shopAddressTitleStr);
        shopDescriptionTitleTx.setText(shopDescriptionTitleStr);
        shopDescriptionTx.setHint(shopDescriptionTitleStr);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getmWidth(),
                getmWidth() * 504 / 1504);
        shopHeadRl.setLayoutParams(params);
        shopNameTx.addTextChangedListener(
                new LimitSizeTextWatcher(mContext, shopNameTx, 24, shopNameTitleStr+"不能超过25个汉字"));
        shopAddressTx.addTextChangedListener(
                new LimitSizeTextWatcher(mContext, shopAddressTx, 49, shopAddressTitleStr+"不能超过50个汉字"));
        shopDescriptionTx.addTextChangedListener(
                new LimitSizeTextWatcher(mContext, shopDescriptionTx, 255, shopDescriptionTitleStr+"不能超过256个汉字"));
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        getDataManager().getShopInformation(ShopInformationModel.class, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onResponse(Object response, String data) {
        if (response instanceof ShopInformationModel) {
            mShopInformationModel = (ShopInformationModel) response;
            if (mShopInformationModel.getResult().equals("1")) {
                UpDateUI();
            } else {
                showToast(mShopInformationModel.getBody().getMessage());
            }
            if (mIndustryTypeModel == null || mIndustryTypeModel.getBody().size() == 0) {
                getDataManager().getIndustryType(IndustryTypeModel.class, true);
            } else {
                setShopType();
                super.onResponse(response, data);
            }
        } else if (response instanceof IndustryTypeModel) {
            super.onResponse(response, data);
            mIndustryTypeModel = (IndustryTypeModel) response;
            if (!mIndustryTypeModel.getResult().equals("1")) {
                showToast("行业类别获取失败");
            } else {
                setShopType();
            }
        } else if (response instanceof EditShopInfoModel) {
            super.onResponse(response, data);
            EditShopInfoModel mEditShopInfoModel = (EditShopInfoModel) response;
            if (mEditShopInfoModel.getResult().equals("1")) {
                showToast(shopTitleStr+"更新成功");
                Intent mIntent = new Intent();
                setResult(RESULT_OK, mIntent);
                finish();
            } else {
                showToast(mEditShopInfoModel.getBody().getMessage());
            }
        }
    }

    private void setShopType() {
        if (mIndustryTypeModel != null || mIndustryTypeModel.getBody().size() > 0) {
            for (int i = 0; i < mIndustryTypeModel.getBody().size(); i++) {
                if (mIndustryTypeModel.getBody().get(i).getId() == mShopInformationModel.getBody().getScId()) {
                    mScId = mIndustryTypeModel.getBody().get(i).getId();
                    mScName = mIndustryTypeModel.getBody().get(i).getScName();
                    mId = mShopInformationModel.getBody().getId();
                    shopTypeTx.setText(mScName);
                    break;
                }
            }
        }
    }

    private void UpDateUI() {
        shopTimeTx.setText(changeStr(mShopInformationModel.getBody().getOpenTime()));
        shopNameTx.setText(mShopInformationModel.getBody().getStoreName());
        shopPhoneTx.setText(mShopInformationModel.getBody().getStoreTel());
        shopAddressTx.setText(mShopInformationModel.getBody().getStoreAddress());
        shopDescriptionTx.setText(mShopInformationModel.getBody().getDescription());
        mShopBanner = mShopInformationModel.getBody().getStoreBanner();
        setBitmapToImageView(shopTopIv, Urls.HEADIMG_BEFOR + mShopBanner, R.mipmap.personal_top_bg);
    }

    private StringBuilder changeStr(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(2, ":");
        sb.insert(5, "-");
        sb.insert(8, ":");
        return sb;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.shop_head_rl, R.id.shop_top_tx, R.id.shop_type_ll, R.id.shop_commit_bt, R.id.shop_time_ll})
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
                mTime = shopTimeTx.getText().toString().replace(":", "").replace("-", "");
                if (!checkData()) {
                    return;
                }
                getDataManager().updateShop(mId, mShopName, mScId, mShopBanner, mShopAddress, mShopTel
                        , mDescription, mTime, EditShopInfoModel.class, true);
                break;
            case R.id.shop_time_ll:
                TimeSelectDialogUtil mStart = new TimeSelectDialogUtil(mContext, view
                        , timeListenerOne, timeListenerTwo);
                mStart.ShowDialog(shopTimeTx.getText().toString().split("-")[0]
                        , shopTimeTx.getText().toString().split("-")[1]);
                hOne = (shopTimeTx.getText().toString().split("-")[0]).split(":")[0];
                mOne = (shopTimeTx.getText().toString().split("-")[0]).split(":")[1];
                hTwo = (shopTimeTx.getText().toString().split("-")[1]).split(":")[0];
                mTwo = (shopTimeTx.getText().toString().split("-")[1]).split(":")[1];
                break;
        }
    }

    TimePicker.OnChangeListener timeListenerOne = new TimePicker.OnChangeListener() {
        @Override
        public void onChange(int hour, int minute) {
            if (hour < 10) {
                hOne = "0" + hour;
            } else {
                hOne = "" + hour;
            }
            if (minute < 10) {
                mOne = "0" + minute;
            } else {
                mOne = "" + minute;
            }
            setTime();
        }
    };

    private void setTime() {
        String str = hOne + mOne + hTwo + mTwo;
        shopTimeTx.setText(changeStr(str));
    }

    TimePicker.OnChangeListener timeListenerTwo = new TimePicker.OnChangeListener() {
        @Override
        public void onChange(int hour, int minute) {
            if (hour < 10) {
                hTwo = "0" + hour;
            } else {
                hTwo = "" + hour;
            }
            if (minute < 10) {
                mTwo = "0" + minute;
            } else {
                mTwo = "" + minute;
            }
            setTime();
        }
    };

    private boolean checkData() {
        if (mShopBanner == null || mShopBanner.isEmpty()) {
            showToast("请选择店铺背景");
            return false;
        } else if (mShopName.isEmpty()) {
            showToast("请输入"+shopNameTitleStr);
            return false;
        } else if (mShopTel.isEmpty()) {
            showToast("请输入店铺联系电话");
            return false;
        } else if (!StringUtils.isPhone(mShopTel) && !StringUtils.isCallNumber(mShopTel)) {
            showToast(getResources().getString(R.string.shop_phone_wran));
            return false;
        } else if (mShopAddress.isEmpty()) {
            showToast("请输入"+shopAddressTitleStr);
            return false;
        } else if (mScId == -1) {
            showToast("请选择行业类型");
            return false;
        } else if (mDescription.isEmpty()) {
            showToast("请输入"+shopDescriptionTitleStr);
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
            imageUri = Uri.parse("file:///" + path);
            LogUtils.e("TAKE_BIG_PICTURE>>>>imageUri:" + imageUri);
            cropImageUri(imageUri, outputX, outputY, CROP_BIG_PICTURE);
        } else if (requestCode == CROP_BIG_PICTURE) {
            LogUtils.e("CROP_BIG_PICTURE>>>>imageUri:" + imageUri);
            if (imageUri != null) {
                new UpLoadPicture(mContext, Urls.UP_STOREIMG, this).execute(imageUri);
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
        if (mList != null && mList.size() > 0) {
            selcteId = 0;
            if (shopTypeTx.getText().toString().isEmpty()) {
                initPopwindowData(mList.get(selcteId));
            }
            mSelectTypePop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }

    }

    private void initPopwindowData(IndustryTypeModel.BodyEntity mList) {
        mScName = mList.getScName();
        mScId = mList.getId();
        shopTypeTx.setText(mScName);
    }
}
