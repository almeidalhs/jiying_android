package com.corelib.net.request;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.StringRequest;
import com.corelib.net.IVolleyListener;
import com.corelib.util.LogUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by loyee on 15-7-15.
 */
public class YLBStringRequest extends StringRequest {
    private static RetryPolicy policy = null;
    private Map<String, String> mPostParams = new HashMap<>();
    private final Response.Listener<String> mListener;

    private YLBStringRequest(int method, String url, final Map<String, String> postParams, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        mPostParams = postParams;
        mListener = listener;
        if (policy == null) {
            policy = new DefaultRetryPolicy(6000, 3, 1.0f);
        }
        setRetryPolicy(policy);
    }

    public static YLBStringRequest createGetRequest(String urlbase, final Map<String, String> postParams, IVolleyListener<String> listeners) {
        String url = createGetUrl(urlbase, postParams);
        YLBStringRequest newRequest = new YLBStringRequest(Method.GET, url, postParams, listeners, listeners);
        return newRequest;
    }

    public static YLBStringRequest createPostRequest(String url, final Map<String, String> postParams, IVolleyListener<String> listeners) {
        YLBStringRequest newRequest = new YLBStringRequest(Method.POST, url, postParams, listeners, listeners);
        return newRequest;
    }

    public static YLBStringRequest createPostRequest(String url, IVolleyListener<String> listeners) {
        YLBStringRequest newRequest = new YLBStringRequest(Method.POST, url, null, listeners, listeners);
        return newRequest;
    }

    public static YLBStringRequest createGetRequest(String url, IVolleyListener<String> listeners) {
        YLBStringRequest newRequest = new YLBStringRequest(Method.GET, url, null, listeners, listeners);
        return newRequest;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mPostParams;
    }

//    @Override
//    protected Response<String> parseNetworkResponse(NetworkResponse response) {
//        String parsed;
//        String s = new String(response.data);
//        Log.e("","==old=>"+s);
//        try {
//            parsed = new String(response.data,"utf-");
//            Log.e("","==parsed=>"+parsed);
//        } catch (UnsupportedEncodingException e) {
//            parsed = new String(response.data);
//        }
//        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
//    }

    public static String createGetUrl(String baseUrl,Map<String,String> params) {
        if(baseUrl == null) {
            return "";
        }
        if(params==null || params.size()==0) {
            return baseUrl;
        }
        Set<String> keys = params.keySet();
        String str="";
        for(String key:keys) {
            if(str.length()>0) {
                str+="&&";
            }
            str=str + key+"="+params.get(key);
        }
        baseUrl = baseUrl+"?"+str;
        LogUtils.v("===>createUrl=" + baseUrl);
        return baseUrl;
    }
}
