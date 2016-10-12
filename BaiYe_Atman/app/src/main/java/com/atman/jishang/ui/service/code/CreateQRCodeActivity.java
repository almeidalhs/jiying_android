package com.atman.jishang.ui.service.code;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.MyUMShareListenner;
import com.atman.jishang.net.Urls;
import com.atman.jishang.net.model.CreateQRCodeModel;
import com.atman.jishang.net.model.ModuleListModel;
import com.atman.jishang.net.upload.UpLoadPicture;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.UiHelper;
import com.atman.jishang.utils.save.FileUtils;
import com.atman.jishang.utils.save.Tools;
import com.atman.jishang.widget.ShareDialog;
import com.atman.jishang.widget.XCFlowLayout;
import com.atman.jishang.widget.YLBDialog;
import com.corelib.util.DensityUtil;
import com.corelib.util.LogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tangbingliang on 16/10/11.
 */

public class CreateQRCodeActivity extends SimpleTitleBarActivity implements UpLoadPicture.uploadListener {

    @Bind(R.id.flowlayout)
    XCFlowLayout flowlayout;
    @Bind(R.id.createcode_qrcode_iv)
    ImageView createcodeQrcodeIv;
    @Bind(R.id.createcode_content_et)
    EditText createcodeContentEt;
    @Bind(R.id.create_share_ll)
    LinearLayout createShareLl;

    private Context mContext = CreateQRCodeActivity.this;

    private int qrcodeType;
    private int singleType;
    private List<ModuleListModel> listModel;
    private String mTitle = "";
    private String mPath = "";
    private String mContent = "";
    private String mWebUrl = "";
    private boolean isShare = false;

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

        LogUtils.e("qrcodeType:" + qrcodeType + ",singleType:" + singleType + ",listModel.size():" + listModel.size());

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
                isShare = true;
                mPath = createImageString();
                if ("".equals(mPath)) {
                    return;
                }

                showLoading();
                new UpLoadPicture(mContext, Urls.UP_CODE, this).execute(Uri.parse("file:///"+mPath));

                break;
            case R.id.createcode_buttom_right_ll:
                isShare = false;
                createImageString();
                break;
        }
    }

    private String createImageString() {
        if (createcodeContentEt.getText().toString().trim().isEmpty()) {
            YLBDialog.Builder builderWran = new YLBDialog.Builder(mContext);
            builderWran.setMessage("您还没有填写二维码描述哟");
            builderWran.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderWran.show();
            return "";
        } else {
            mContent = createcodeContentEt.getText().toString().trim();
        }
        createcodeContentEt.setBackgroundResource(R.color.color_white);
        createcodeContentEt.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL);
        createcodeContentEt.clearFocus();

        // View是你需要截图的View
        View mView = CreateQRCodeActivity.this.getWindow().getDecorView();
        mView.setDrawingCacheEnabled(true);
        mView.buildDrawingCache();
        // 获取实际显示范围
        int[] location = new int[2];
        createShareLl.getLocationOnScreen(location);
        int strokeWidth = DensityUtil.dp2px(mContext, 1);

        // 去掉标题栏
        Bitmap b = Bitmap.createBitmap(mView.getDrawingCache(), location[0]+strokeWidth, location[1]+strokeWidth
                , createShareLl.getWidth()-(2*strokeWidth), createShareLl.getHeight()-(2*strokeWidth));
        mView.destroyDrawingCache();

        createcodeContentEt.setBackgroundResource(R.drawable.create_code_edit_bg);
        createcodeContentEt.setGravity(Gravity.TOP|Gravity.LEFT);
        return saveImageToGallery(b);
    }

    public String saveImageToGallery(Bitmap bmp) {
        if (bmp == null){
            showToast("保存出错了...");
            return "";
        }
        // 首先保存图片
        File appDir = new File(FileUtils.getInst().getSystemPhotoPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            showToast("文件未发现");
            e.printStackTrace();
        } catch (IOException e) {
            showToast("保存出错了...");
            e.printStackTrace();
        }catch (Exception e){
            showToast("保存出错了...");
            e.printStackTrace();
        }

        // 最后通知图库更新
        try {
            MediaStore.Images.Media.insertImage(mContext.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        mContext.sendBroadcast(intent);
        if (!isShare) {
            showToast("成功保存到相册");
        }

        return file.getPath();
    }

    public void doShare(int type) {
        if (BaiYeBaseApplication.mLoginResultModel.getBody().getStoreId() == 0) {
            showToast("你还没有创建店铺，不能进行分享");
            return;
        }
        mTitle = BaiYeBaseApplication.mShopInformationModel.getBody().getStoreName();
        if (singleType==0 && listModel.size()==1) {
            mTitle = listModel.get(0).getName();
        }
        switch (type) {
            case 1://微信
                if (UiHelper.isAppInstalled(mContext, UiHelper.WEIXIN_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.WEIXIN);
                }
                break;
            case 2://朋友圈
                if (UiHelper.isAppInstalled(mContext, UiHelper.WEIXIN_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.WEIXIN_CIRCLE);
                }
                break;
            case 3://微博
                if (UiHelper.isAppInstalled(mContext, UiHelper.SINA_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.SINA);
                }
                break;
            case 4://qq
                if (UiHelper.isAppInstalled(mContext, UiHelper.QQ_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.QQ);
                }
                break;
            case 5://copy
                Tools.copy(mTitle + "\n" + mWebUrl, mContext);
                break;
            default:
                break;

        }
    }

    private void shareHelper(SHARE_MEDIA Platform) {
        UMImage image = new UMImage(mContext, new File(mPath));
        new ShareAction(this).setPlatform(Platform).setCallback(new MyUMShareListenner(mContext))
                .withText(mContent)
                .withTitle(mTitle)
                .withMedia(image)
                .withTargetUrl(mWebUrl)
                .share();
    }

    @Override
    public void success(String imageUrl) {
        cancelLoading();
        mWebUrl = Urls.HEADIMG_BEFOR + imageUrl;

        ShareDialog.Builder builder = new ShareDialog.Builder(mContext);
        builder.setTitle("分享");
        builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ShareDialog dailog = builder.show();
        dailog.setShareListener(new ShareDialog.ShareListener() {
            @Override
            public void onShare(int type) {
                doShare(type);
            }
        });
    }

    @Override
    public void fail(String failInfo) {
        cancelLoading();
    }
}
