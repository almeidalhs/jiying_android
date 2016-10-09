package com.atman.jishang.ui.personal.loginandregister;

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

import com.atman.jishang.R;
import com.atman.jishang.interfaces.LimitSizeTextWatcher;
import com.atman.jishang.net.Urls;
import com.atman.jishang.net.model.LoginResultModel;
import com.atman.jishang.net.model.RegisterModel;
import com.atman.jishang.net.upload.UpLoadPicture;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.PreferenceUtil;
import com.atman.jishang.widget.BottomDialog;
import com.corelib.base.BaseApplication;
import com.corelib.common.SubmitHelper;
import com.corelib.util.LogUtils;
import com.corelib.widget.MyCleanEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 注册界面
 * 作者 tangbingliang
 * 时间 16/4/13 10:39
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class RegisterSetPassWordActivity extends SimpleTitleBarActivity implements UpLoadPicture.uploadListener {

    @Bind(R.id.register_header_iv)
    ImageView registerHeaderIv;
    @Bind(R.id.register_password_et)
    MyCleanEditText registerPasswordEt;
    @Bind(R.id.register_next_bt)
    Button registerNextBt;

    private String mPhoneNumber;
    private static String IntentTag = "phonenumber";
    private Context mContext = RegisterSetPassWordActivity.this;
    private String mAvatarUrl;
    private final int toRegisterFinish = 603;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disableLoginCheck();
        setContentView(R.layout.acticity_registersetpassword);
        ButterKnife.bind(this);
    }

    /**
     * 获取Intent
     *
     * @param context
     * @param phonenumber 手机号码
     * @return
     */
    public static Intent createIntent(Context context, String phonenumber) {
        Intent intent = new Intent(context, RegisterSetPassWordActivity.class);
        intent.putExtra(IntentTag, phonenumber);
        return intent;
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        mPhoneNumber = getIntent().getStringExtra(IntentTag);
        registerPasswordEt.addTextChangedListener(new LimitSizeTextWatcher(mContext, registerPasswordEt, 17, "密码长度为6-17位"));
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof RegisterModel) {
            RegisterModel mRegisterModel = (RegisterModel) response;
            if (mRegisterModel.getResult().equals("1")) {
                getDataManager().login(mPhoneNumber,
                        SubmitHelper.getMD5(registerPasswordEt.getText().toString().trim()), LoginResultModel.class, true);
            } else {
                showToast(mRegisterModel.getBody().getMessage());
            }
        } else if(response instanceof LoginResultModel){
            LoginResultModel mLoginResultModel = (LoginResultModel) response;
            if (mLoginResultModel.getResult().equals("1")) {
                showToast("注册成功");
                BaiYeBaseApplication.mLoginResultModel = mLoginResultModel;
                BaseApplication.mSessionId = mLoginResultModel.getBody().getMemberQqInfo();
                PreferenceUtil.savePreference(mContext, PreferenceUtil.PARM_US, mLoginResultModel.getBody().getMemberName());
                PreferenceUtil.savePreference(mContext, PreferenceUtil.PARM_PW, registerPasswordEt.getText().toString().trim());
                startActivityForResult(new Intent(mContext, RegisterFinishActivity.class), toRegisterFinish);

            } else {
                showToast(mLoginResultModel.getBody().getMessage());
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.register_header_iv, R.id.register_next_bt})
    public void onClick(View view) {
        String pw = registerPasswordEt.getText().toString().trim();
        switch (view.getId()) {
            case R.id.register_header_iv:
                showAddrPopupWindow(view);
                break;
            case R.id.register_next_bt:
                if (!checkUserName(pw) && mPhoneNumber.isEmpty()) {
                    return;
                }
                getDataManager().register(mPhoneNumber, SubmitHelper.getMD5(pw)
                        , mAvatarUrl, RegisterModel.class, true);
                break;
        }
    }

    private boolean checkUserName(String str) {
        if (str.isEmpty()) {
            showToast(getResources().getString(R.string.register_password_wran));
            return false;
        }
        if (mPhoneNumber.isEmpty()) {
            showToast("用户名为空");
            return false;
        }
        if (str.length() < 6) {
            showToast("密码长度为6-17位");
            return false;
        }
        return true;
    }

    private Uri imageUri;//The Uri to store the big bitmap
    private final int CHOOSE_BIG_PICTURE = 444;
    private final int TAKE_BIG_PICTURE = 555;
    private final int CROP_BIG_PICTURE = 666;
    private int outputX = 400;
    private int outputY = 400;
    private int mWidth;
    private int mHeight;

    private void showAddrPopupWindow(View view) {
        BottomDialog.Builder builder = new BottomDialog.Builder(mContext);
        builder.setTitle(Html.fromHtml("<font color=\"#f9464a\">选择头像</font>"));
        builder.setItems(new String[]{"拍照", "从相册中获取"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {//拍照
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, TAKE_BIG_PICTURE);//or TAKE_SMALL_PICTURE
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e("requestCode:" + requestCode + "resultCode:" + resultCode);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == CHOOSE_BIG_PICTURE) {//选择照片
            imageUri = data.getData();
            LogUtils.e("CHOOSE_BIG_PICTURE>>>>imageUri:"+imageUri);
            cropImageUri(imageUri, outputX, outputX, CROP_BIG_PICTURE);
        } else if (requestCode == TAKE_BIG_PICTURE) {
            imageUri = data.getData();
            LogUtils.e("TAKE_BIG_PICTURE>>>>imageUri:"+imageUri);
            if (imageUri == null) {
                //use bundle to get data
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    Bitmap photo = (Bitmap) bundle.get("data"); //get bitmap
                    imageUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), photo, null,null));
                }
                LogUtils.e("TAKE_BIG_PICTURE@@@@@>>>>imageUri:"+imageUri);
            }
            cropImageUri(imageUri, outputX, outputX, CROP_BIG_PICTURE);
        } else if (requestCode == CROP_BIG_PICTURE) {
            LogUtils.e("CROP_BIG_PICTURE>>>>imageUri:"+imageUri);
            if (imageUri != null) {
                new UpLoadPicture(mContext,Urls.UP_HEADIMG, this).execute(imageUri);
            }
        } else if (requestCode == toRegisterFinish) {
            Intent mIntent = new Intent();
            setResult(RESULT_OK, mIntent);
            finish();
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
        setBitmapToImageView(registerHeaderIv, Urls.HEADIMG_BEFOR+mAvatarUrl, R.mipmap.ic_launcher);
        showToast("上传成功");
    }

    @Override
    public void fail(String failInfo) {
        showToast(failInfo);
    }

}
