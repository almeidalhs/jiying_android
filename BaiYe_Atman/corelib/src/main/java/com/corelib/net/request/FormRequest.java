/*
 * Copyright (c) 2015, wordall1101@126.com All Rights Reserved.  
 */
package com.corelib.net.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.corelib.net.IVolleyListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author vftour.com
 * @version 1.0
 * @date: 15/12/31 下午4:54
 */
public class FormRequest extends Request<byte[]> {

    private final HttpParams mParams;
    private final IVolleyListener<byte[]> mListener;

    public FormRequest(String url, IVolleyListener listeners) {
        this(Request.Method.GET, url, null, listeners);
    }

    public FormRequest(int httpMethod, String url, HttpParams params,
                       IVolleyListener listeners) {
        super(httpMethod, url, listeners);
        if (params == null) {
            params = new HttpParams();
        }
        this.mParams = params;
        this.mListener = listeners;
    }

    @Override
    public String getCacheKey() {
        if (getMethod() == Method.POST) {
            return getUrl() + mParams.getUrlParams();
        } else {
            return getUrl();
        }
    }

    @Override
    public String getBodyContentType() {
        if (mParams.getContentType() != null) {
            return mParams.getContentType();
        } else {
            return super.getBodyContentType();
        }
    }

    @Override
    public Map<String, String> getHeaders() {
        return mParams.getHeaders();
    }

    @Override
    public byte[] getBody() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            mParams.writeTo(bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    @Override
    public Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(byte[] response) {
        mListener.onResponse(response);
    }
}
