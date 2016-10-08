package com.atman.jishang.ui.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.atman.jishang.R;
import com.atman.jishang.net.model.LoginResultModel;
import com.atman.jishang.net.model.ShopInformationModel;
import com.atman.jishang.widget.dateselect.DateObject;
import com.corelib.base.BaseApplication;
import com.corelib.util.DeviceUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 描述 基础App application类，用于存储全局变量和初始化数据
 * 作者 tangbingliang
 * 时间 16/4/11 09:22
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class BaiYeBaseApplication extends BaseApplication{

    private static Context mContext = null;
    protected static BaiYeBaseApplication mInstance;
    public static String mToken = "0";
    public static LoginResultModel mLoginResultModel = null;
    public static ShopInformationModel mShopInformationModel = null;
    public static String mDeviceToken = "";
    public static String mVersionName = "";
    public static String mChannel = "";
    public static String mExperienceUS = "";
    public static String mExperiencePW = "";
    public static String mSMS_URL = "";
    public static String mWEB_URL = "";
    private DisplayMetrics displayMetrics = null;
    private DisplayImageOptions options,optionsHead, optionsNot;
    public static String appId = "";

    private int[] num = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public ArrayList<DateObject> dateList = new ArrayList<>();
    private DateObject dateObject;      //日期数据对象

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mInstance = this;

        UMmengInit();
        mVersionName = getAppInfo();
        mChannel = getAppMetaData(mContext, "UMENG_CHANNEL");

        Log.e("tag",">>>>mDeviceToken:"+mDeviceToken);

        InitDownImageConfig();
        initDisplayConfig();

        final int year = Calendar.getInstance().get(Calendar.YEAR);
        new Thread(new Runnable() {
            public void run() {
                for (int y=year-1;y<=(year+4);y++) {
                    for (int m=1;m<=num.length;m++) {
                        int max = num[m-1];
                        if (y%4==0 && m==2) {
                            max = num[m-1]+1;
                        }
                        for (int d=1;d<=max;d++) {
                            dateObject = new DateObject(y, m, d, getWeek(y+"-"+m+"-"+d));
                            dateList.add(dateObject);
                        }
                    }
                }
            }
        }).start();
    }

    public ArrayList<DateObject> getDateList() {
        return dateList;
    }

    private int getWeek(String pTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c.get(Calendar.DAY_OF_WEEK);
    }

    private void InitDownImageConfig() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(mContext)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
//                .discCacheExtraOptions(480, 800, Bitmap.CompressFormat.JPEG, 75, null) // Can slow ImageLoader, use it carefully (Better don't use it)/设置缓存的详细信息，最好不要设置这个
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100) //缓存的文件数量
//                .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(mContext, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();//开始构建
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    private void initDisplayConfig() {
        options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.mipmap.ic_launcher) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
//                .decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置
//                .delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
//                .preProcessor(BitmapProcessor preProcessor)  //设置图片加入缓存前，对bitmap进行设置
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成

        optionsNot = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
//                .decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置
//                .delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
//                .preProcessor(BitmapProcessor preProcessor)  //设置图片加入缓存前，对bitmap进行设置
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成

        optionsHead = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.member_item_ic) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.member_item_ic)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.member_item_ic)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
//                .decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置
//                .delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
//                .preProcessor(BitmapProcessor preProcessor)  //设置图片加入缓存前，对bitmap进行设置
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成
    }

    public DisplayImageOptions getOptions() {
        return options;
    }

    public DisplayImageOptions getOptionsNot() {
        return optionsNot;
    }

    public DisplayImageOptions getOptionsHead() {
        return optionsHead;
    }

    public DisplayImageOptions getMemberHead() {
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.addmember_top_iv) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.addmember_top_iv)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.addmember_top_iv)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
//                .decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置
//                .delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
//                .preProcessor(BitmapProcessor preProcessor)  //设置图片加入缓存前，对bitmap进行设置
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(40))//是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();
    }

    private void UMmengInit() {
        //启动umen推送
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.enable();
        mDeviceToken = mPushAgent.getRegistrationId();
        if (mDeviceToken==null || mDeviceToken.isEmpty()) {
            BaseApplication.mToken = DeviceUtils.getDeviceId();
        } else {
            BaseApplication.mToken = mDeviceToken;
        }

        //微信(朋友圈) appid appsecret
        PlatformConfig.setWeixin("wxb7ed76c833633acb", "def3ab85e1800d17ca6c0c745264c139");
        //新浪微博 appkey appsecret
        PlatformConfig.setSinaWeibo("2128401587", "d3584b455955d504c52cc092b2d20765");
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone("1105376672", "16jHvFwD3rh5yJFi");
    }

    public BaiYeBaseApplication(){
        mInstance = this;
    }

    public static Context getContext(){
        return mContext;
    }

    public static BaiYeBaseApplication getApp(){
        return mInstance;
    }

    private String getAppInfo() {
        try {
            String pkName = this.getPackageName();
            String versionName = this.getPackageManager().getPackageInfo(pkName, 0).versionName;
            int versionCode = this.getPackageManager().getPackageInfo(pkName, 0).versionCode;
            return versionName;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 获取application中指定的meta-data
     * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return resultData;
    }

    public float getScreenDensity() {
        if (this.displayMetrics == null) {
            setDisplayMetrics(getResources().getDisplayMetrics());
        }
        return this.displayMetrics.density;
    }

    public void setDisplayMetrics(DisplayMetrics DisplayMetrics) {
        this.displayMetrics = DisplayMetrics;
    }

    public int dp2px(float f) {
        return (int) (0.5F + f * getScreenDensity());
    }

    public int px2dp(float pxValue) {
        return (int) (pxValue / getScreenDensity() + 0.5f);
    }

    //获取应用的data/data/....File目录
    public String getFilesDirPath() {
        return getFilesDir().getAbsolutePath();
    }

    //获取应用的data/data/....Cache目录
    public String getCacheDirPath() {
        return getCacheDir().getAbsolutePath();
    }



}
