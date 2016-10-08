package com.corelib.base;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.text.TextUtils;

import com.corelib.util.DeviceUtils;

public abstract class BaseApplication extends Application {

    private static Context mContext = null;
    private static LruCache<String, Bitmap> mMemoryCache = null;
    private String mDeviceToken;
    public static String mCookie;
    public static String mToken;
    public static String mSessionId;

    private void intMemoryCache() {
        // Get the Max available memory
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        intMemoryCache();
    }

    public static Context getContext() {
        return mContext;
    }

    public static LruCache<String, Bitmap> getMemoryCache() {
        return mMemoryCache;
    }

    public static void clearMemoryCache() {
        mMemoryCache.evictAll();
    }

    /**获取DeviceToken*/
    public String getDeviceToken() {
        if(TextUtils.isEmpty(mDeviceToken)) {
            mDeviceToken = DeviceUtils.getDeviceId();
        }
        return mDeviceToken;
    }
}
