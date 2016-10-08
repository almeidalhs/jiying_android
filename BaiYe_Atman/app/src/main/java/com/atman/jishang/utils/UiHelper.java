package com.atman.jishang.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import com.atman.jishang.net.model.MarketListModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.net.model.HomeAdModel;
import com.atman.jishang.net.model.HomeGridViewDataModel;
import com.atman.jishang.ui.home.ChannelEditorActivity;
import com.atman.jishang.ui.home.goods.AddGoodsActivity;
import com.atman.jishang.ui.home.goods.GoodsListActivity;
import com.atman.jishang.ui.home.StorePreviewActivity;
import com.atman.jishang.ui.home.WebPageActivity;
import com.atman.jishang.ui.home.orders.OrderManageActivity;
import com.atman.jishang.ui.marketing.MarketingManagementActivity;
import com.atman.jishang.ui.marketing.coupon.CouponActivity;
import com.atman.jishang.ui.marketing.fullcut.FullCutActivity;
import com.atman.jishang.ui.member.AddMemberActivity;
import com.atman.jishang.ui.member.MemberListActivity;
import com.atman.jishang.ui.personal.CreateShopActivity;
import com.atman.jishang.widget.YLBDialog;
import com.choicepicture_library.tools.FileUtils;
import com.corelib.util.LogUtils;
import com.nostra13.universalimageloader.utils.L;

import java.io.File;

/**
 * 描述 首页功能列表跳转管理
 * 作者 tangbingliang
 * 时间 16/4/22 17:29
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class UiHelper implements DialogInterface.OnClickListener{

    //1  商品管理  2 新增商品  3 店铺预览  4 更多    5 会员管理  6 新增会员 7 营销管理  9 订单管理

    public UiHelper(){}

    public void toSecondLevelByType(Context mContext, HomeGridViewDataModel.BodyEntity b) {
        Intent mIntent;
        switch (b.getConsoleBean().getConsoleType()) {
            case 1://商品管理
                mContext.startActivity(new Intent(mContext, GoodsListActivity.class));
                break;
            case 2://新增商品
                if (BaiYeBaseApplication.mShopInformationModel==null) {
                    toCreateShop(mContext);
                    return;
                }
                mContext.startActivity(new Intent(mContext, AddGoodsActivity.class));
                break;
            case 3://店铺预览
                if (BaiYeBaseApplication.mShopInformationModel==null) {
                    toCreateShop(mContext);
                    return;
                }
                mContext.startActivity(new Intent(mContext, StorePreviewActivity.class));
                break;
            case 4://更多
                mContext.startActivity(new Intent(mContext,ChannelEditorActivity.class));
                break;
            case 5://会员管理
                mContext.startActivity(new Intent(mContext,MemberListActivity.class));
                break;
            case 6://新增会员
                mContext.startActivity(new Intent(mContext, AddMemberActivity.class));
                break;
            case 7://营销管理
                mContext.startActivity(new Intent(mContext,MarketingManagementActivity.class));
                break;
            case 9://订单管理
                mContext.startActivity(new Intent(mContext,OrderManageActivity.class));
                break;
            default://新增URL
                mIntent = WebPageActivity.buildIntent(mContext,b.getConsoleBean().getConsoleUrl(),b.getConsoleBean().getConsoleName());
                mContext.startActivity(mIntent);
                break;
        }
    }

    public void toSecondLevelByTypeAD(Context mContext, HomeAdModel.BodyEntity b) {
        Intent mIntent;
        switch (b.getType()) {
            case 1://商品管理
                mContext.startActivity(new Intent(mContext, GoodsListActivity.class));
                break;
            case 2://新增商品
                if (BaiYeBaseApplication.mShopInformationModel==null) {
                    toCreateShop(mContext);
                    return;
                }
                mContext.startActivity(new Intent(mContext, AddGoodsActivity.class));
                break;
            case 3://店铺预览
                if (BaiYeBaseApplication.mShopInformationModel==null) {
                    toCreateShop(mContext);
                    return;
                }
                mContext.startActivity(new Intent(mContext, StorePreviewActivity.class));
                break;
            case 4://更多
                mContext.startActivity(new Intent(mContext,ChannelEditorActivity.class));
                break;
            case 5://会员管理
                mContext.startActivity(new Intent(mContext, AddMemberActivity.class));
                break;
            case 6://新增会员
                mContext.startActivity(new Intent(mContext,MemberListActivity.class));
                break;
            case 7://营销管理
                mContext.startActivity(new Intent(mContext,MarketingManagementActivity.class));
                break;
            case 9://订单管理
                mContext.startActivity(new Intent(mContext,OrderManageActivity.class));
                break;
            default://新增URL
                if (b.getAdUrl()==null || b.getAdUrl().isEmpty()) {
                    return;
                }
                mIntent = WebPageActivity.buildIntent(mContext,b.getAdUrl(),b.getName());
                mContext.startActivity(mIntent);
                break;
        }
    }

    public static void toSecondLevelMarket(Context mContext, MarketListModel.BodyEntity b) {
        switch (b.getType()) {
            case 1://全场满减
                mContext.startActivity(new Intent(mContext, FullCutActivity.class));
                break;
            case 2://优惠券
                mContext.startActivity(new Intent(mContext, CouponActivity.class));
                break;
        }
    }

    private void toCreateShop(final Context mContext) {
        if (BaiYeBaseApplication.mShopInformationModel==null) {
            YLBDialog.Builder builder = new YLBDialog.Builder(mContext);
            builder.setMessage("您尚未创建店铺");
            builder.setPositiveButton("知道了", this);
            builder.setNegativeButton("创建店铺", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    mContext.startActivity(new Intent(mContext, CreateShopActivity.class));
                }
            });
            builder.show();
            return;
        }
    }

    public static String photo(final Context context, String path, int TAKE_PICTURE) {

        PackageManager pm = context.getPackageManager();
        boolean flag = (PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.CAMERA", "com.atman.jishang"));
        if (flag) {
            //有这个权限，做相应处理
        } else { //没有权限
            YLBDialog.Builder builder = new YLBDialog.Builder(context);
            builder.setMessage("您没有开启照相机权限？");
            builder.setPositiveButton("去开启", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    context.startActivity(getAppDetailSettingIntent(context));
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
            return "";
        }

        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
            String out_file_path = FileUtils.SDPATH;
            File dir = new File(out_file_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            path = out_file_path + System.currentTimeMillis() + ".jpg";
            LogUtils.e("photo>>path:"+path);
            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
            ((Activity)context).startActivityForResult(getImageByCamera, TAKE_PICTURE);
        } else {
            Toast.makeText(context, "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
        }
        return path;
    }

    private static Intent getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", "com.atman.jishang",null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings","com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", "com.atman.jishang");
        }
        return localIntent;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
    }

    public static String SINA_PACKAGE = "com.sina.weibo";
    public static String WEIXIN_PACKAGE = "com.tencent.mm";
    public static String QQ_PACKAGE = "com.tencent.mobileqq";
    public static String SMS_PACKAGE = "android.intent.action.SENDTO";
    public static String CALL_PACKAGE = "android.intent.action.DIAL";
    /*
    * check the app is installed
    */
    public static boolean isAppInstalled(Context context,String packagename) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
        }catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        String str = "";
        if (packagename.equals(SINA_PACKAGE)) {
            str = "您的手机还没有安装新浪微博客户端";
        } else if (packagename.equals(WEIXIN_PACKAGE)) {
            str = "您的手机还没有安装微信客户端";
        } else if (packagename.equals(QQ_PACKAGE)) {
            str = "您的手机还没有安装QQ客户端";
        } else if (packagename.equals(SMS_PACKAGE)) {
            str = "您的设备无法访问通讯录";
        } else if (packagename.equals(CALL_PACKAGE)) {
            str = "您的设备不支持拨号";
        }
        if(packageInfo ==null){
            Toast.makeText(context,str,Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }

    /**
     * 判断是否平板设备
     * @param context
     * @return true:平板,false:手机
     */
    public static boolean isTabletDevice(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static void sendSMS(Context context, String content, String number){
        Uri smsToUri = Uri.parse("smsto:"+number);
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", content);
        context.startActivity(intent);
    }
}
