package com.corelib.net;

import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import java.util.Map;

/**
 * Created by loyee on 15-12-18.
 */
public interface IHttpInterface {
    void sendHttpRequest(String url, Map<String, Object> params, Class clz, IVolleyListener listener);
    void sendHttpRequest(String url, Map<String, Object> params, Class clz, boolean showLodding, IVolleyListener listener);
    void sendHttpRequest(String url, String params, Class clz, IVolleyListener listener);
    void sendHttpRequest(String url, String params, Class clz, boolean showLodding, IVolleyListener listener);
    void sendHttpGetRequest(String url, Class clz, boolean showLodding, IVolleyListener listener);
    void sendHttpDeleteRequest(String url, Class clz, boolean showLodding, IVolleyListener listener);
    void sendHttpPutRequest(String url, Class clz, boolean showLodding, IVolleyListener listener);
    void cancelRequest(final String url);
    void setBitmapToImageView(ImageView imageView, String imgUrl, int defaultResId);
    ImageLoader getImageLoader();
}
