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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.net.Urls;
import com.atman.jishang.net.model.UpdateHeadImgModel;
import com.atman.jishang.net.upload.UpLoadPicture;
import com.atman.jishang.utils.DataCleanManager;
import com.atman.jishang.utils.PreferenceUtil;
import com.atman.jishang.utils.UiHelper;
import com.atman.jishang.widget.BottomDialog;
import com.atman.jishang.widget.YLBDialog;
import com.corelib.util.LogUtils;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 个人信息
 * 作者 tangbingliang
 * 时间 16/4/15 16:02
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class PersonalInformationActivity extends SimpleTitleBarActivity
        implements DialogInterface.OnClickListener, UpLoadPicture.uploadListener{

    @Bind(R.id.personalinfo_head_img)
    ImageView personalinfoHeadImg;
    @Bind(R.id.personalinfo_username_tx)
    TextView personalinfoUsernameTx;
    @Bind(R.id.personalinfo_username_ll)
    LinearLayout personalinfoUsernameLl;
    @Bind(R.id.personalinfo_resetPW_ll)
    LinearLayout personalinfoResetPWLl;
    @Bind(R.id.personalinfo_clean_ll)
    LinearLayout personalinfoCleanLl;
    @Bind(R.id.shop_virsion_tx)
    TextView shopVirsionTx;
    @Bind(R.id.personalinfo_version_ll)
    LinearLayout personalinfoVersionLl;
    @Bind(R.id.personalinfo_exit_bt)
    Button personalinfoExitBt;
    @Bind(R.id.shop_cache_tx)
    TextView shopCacheTx;

    private int toResetPW = 610;
    private Context mContext = PersonalInformationActivity.this;
    private String mAvatarUrl;
    private String mCacheSize;
    private String mCachePath = "/data/data/com.atman.jishang/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinformation);
        ButterKnife.bind(this);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        setToolbarTitle(R.string.personalinfo_title);

        shopVirsionTx.setText(BaiYeBaseApplication.mVersionName.split("-")[0]);
        personalinfoUsernameTx.setText(BaiYeBaseApplication.mLoginResultModel.getBody().getMemberMobile());
        setBitmapToImageView(personalinfoHeadImg,
                Urls.HEADIMG_BEFOR + BaiYeBaseApplication.mLoginResultModel.getBody().getMemberAvatar(),
                R.mipmap.personal_head_default);
        personalinfoVersionLl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showToast("渠道：" + BaiYeBaseApplication.mChannel);
                return true;
            }
        });
        countCache();
    }

    private void countCache() {
        try {
            mCacheSize = DataCleanManager.getCacheSize(new File(mCachePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        shopCacheTx.setText(mCacheSize);
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
        if (response instanceof UpdateHeadImgModel) {
            UpdateHeadImgModel mUpdateHeadImgModel = (UpdateHeadImgModel) response;
            if (mUpdateHeadImgModel.getResult().equals("1")) {
                BaiYeBaseApplication.mLoginResultModel.getBody().setMemberAvatar(mAvatarUrl);
                setBitmapToImageView(personalinfoHeadImg, Urls.HEADIMG_BEFOR + mAvatarUrl, R.mipmap.personal_head_default);
                showToast("头像上传成功！");
            } else {
                showToast(mUpdateHeadImgModel.getBody().getMessage());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.personalinfo_head_img, R.id.personalinfo_resetPW_ll, R.id.personalinfo_clean_ll,
            R.id.personalinfo_version_ll, R.id.personalinfo_exit_bt, R.id.personalinfo_myhead_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.personalinfo_myhead_ll:
            case R.id.personalinfo_head_img:
                showAddrPopupWindow(view);
                break;
            case R.id.personalinfo_resetPW_ll:
                if (BaiYeBaseApplication.mLoginResultModel.getBody().getMemberName()
                        .equals(BaiYeBaseApplication.mExperienceUS) ||
                        BaiYeBaseApplication.mLoginResultModel.getBody().getMemberMobile()
                                .equals(BaiYeBaseApplication.mExperienceUS)) {
                    showToast("对不起，您没有权限执行此操作");
                    return;
                }
                startActivityForResult(new Intent(mContext,ResetPassWordActivity.class),toResetPW);
                break;
            case R.id.personalinfo_clean_ll:
                showCleanData();
                break;
            case R.id.personalinfo_version_ll:
                showToast("检查更新中...");
                break;
            case R.id.personalinfo_exit_bt:
                YLBDialog.Builder builder = new YLBDialog.Builder(PersonalInformationActivity.this);
                builder.setMessage("你确定要退出登录吗？");
                builder.setPositiveButton("退出登录", this);
                builder.setNegativeButton("取消", this);
                builder.show();
                break;
        }
    }

    private void showCleanData() {
        YLBDialog.Builder builder = new YLBDialog.Builder(PersonalInformationActivity.this);
        builder.setMessage("你确定清除应用缓存吗？");
        builder.setPositiveButton("清除", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                DataCleanManager.cleanApplicationData(mContext,mCachePath);
                countCache();
                shopCacheTx.setText("0 Byte");
            }
        });
        builder.setNegativeButton("取消", this);
        builder.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
        if (which == DialogInterface.BUTTON_POSITIVE) {
            BaiYeBaseApplication.mLoginResultModel = null;
            BaiYeBaseApplication.mShopInformationModel = null;
            PreferenceUtil.savePreference(mContext, "PassWord", "");
            Intent mIntent = new Intent();
            setResult(RESULT_OK,mIntent);
            finish();
        }
    }

    private Uri imageUri;//The Uri to store the big bitmap
    private final int CHOOSE_BIG_PICTURE = 444;
    private final int TAKE_BIG_PICTURE = 555;
    private final int CROP_BIG_PICTURE = 666;
    private int outputX = 50;
    private int outputY = 50;
    private int mWidth;
    private int mHeight;

    private void showAddrPopupWindow(View view) {
        BottomDialog.Builder builder = new BottomDialog.Builder(mContext);
        builder.setTitle(Html.fromHtml("<font color=\"#f9464a\">选择头像</font>"));
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == toResetPW) {
            Intent mIntent = new Intent();
            setResult(RESULT_OK,mIntent);
            finish();
        } else if (requestCode == CHOOSE_BIG_PICTURE) {//选择照片
            imageUri = data.getData();
            LogUtils.e("CHOOSE_BIG_PICTURE>>>>imageUri:" + imageUri);
            cropImageUri(imageUri, outputX, outputX, CROP_BIG_PICTURE);
        } else if (requestCode == TAKE_BIG_PICTURE) {
            imageUri = Uri.parse("file:///"+path);
            LogUtils.e("TAKE_BIG_PICTURE>>>>imageUri:"+imageUri);
            cropImageUri(imageUri, outputX, outputX, CROP_BIG_PICTURE);
        } else if (requestCode == CROP_BIG_PICTURE) {
            LogUtils.e("CROP_BIG_PICTURE>>>>imageUri:"+imageUri);
            if (imageUri != null) {
                new UpLoadPicture(mContext,Urls.UP_HEADIMG, this).execute(imageUri);
            }
        }
    }

    //裁减照片
    private void cropImageUri(Uri uri, int outputX, int outputY, int requestCode) {
        if (uri == null) {
            return;
        }
        LogUtils.e("outputX:"+outputX+",outputY:"+outputY);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
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
        mAvatarUrl = imageUrl;
        LogUtils.e("success>>>>mAvatarUrl:"+mAvatarUrl);
        getDataManager().updateAvatar(mAvatarUrl, UpdateHeadImgModel.class, true);
    }

    @Override
    public void fail(String failInfo) {
        showToast(failInfo);
    }
}
