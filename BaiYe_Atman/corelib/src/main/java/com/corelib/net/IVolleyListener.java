package com.corelib.net;

import com.android.volley.Response;

/**
 * Created by loyee on 15-12-9.
 */
public interface IVolleyListener<T> extends Response.ErrorListener, Response.Listener<T> {
    /** Called when a response is received. but code is not 1000..not correct */
    void onResponseNotCorrect(T response);
}