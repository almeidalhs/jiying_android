package com.corelib.base;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.corelib.net.IHttpInterface;
import com.corelib.net.IVolleyListener;

import java.util.Map;

/**
 * Created by loyee on 15-12-1.
 */
public abstract class BaseFragment extends Fragment implements IVolleyListener, IHttpInterface, OnRefreshListener2 {

    /*
     * fragment的activity必须继承自BaseAppCompatActivity，否则出错
     */
    @Override
    public void sendHttpRequest(String url, Map<String, Object> params, Class clz, IVolleyListener listener) {
        getBaseAppCompatActivity().sendHttpRequest(url,params,clz, listener);
    }

    @Override
    public void sendHttpRequest(String url, String params, Class clz, IVolleyListener listener) {
        getBaseAppCompatActivity().sendHttpRequest(url,params,clz, listener);
    }

    @Override
    public void sendHttpRequest(String url, Map<String, Object> params, Class clz, boolean showLodding, IVolleyListener listener) {
        if (showLodding) {
            showLoading();
        }
        getBaseAppCompatActivity().sendHttpRequest(url,params,clz, listener);
    }

    @Override
    public void sendHttpRequest(String url, String params, Class clz, boolean showLodding, IVolleyListener listener) {
        if (showLodding) {
            showLoading();
        }
        getBaseAppCompatActivity().sendHttpRequest(url,params,clz, listener);
    }

    @Override
    public void sendHttpGetRequest(String url, Class clz, boolean showLodding, IVolleyListener listener) {
        if (showLodding) {
            showLoading();
        }
        getBaseAppCompatActivity().sendHttpGetRequest(url, clz, showLodding, listener);
    }

    @Override
    public void sendHttpDeleteRequest(String url, Class clz, boolean showLodding, IVolleyListener listener) {
        if (showLodding) {
            showLoading();
        }
        getBaseAppCompatActivity().sendHttpDeleteRequest(url, clz, showLodding, listener);
    }

    @Override
    public void sendHttpPutRequest(String url, Class clz, boolean showLodding, IVolleyListener listener) {
        if (showLodding) {
            showLoading();
        }
        getBaseAppCompatActivity().sendHttpPutRequest(url, clz, showLodding,listener);
    }

    @Override
    public void cancelRequest(String url) {
        getBaseAppCompatActivity().cancelRequest(url);
    }

    @Override
    public void setBitmapToImageView(ImageView imageView, String imgUrl, int defaultResId) {
        getBaseAppCompatActivity().setBitmapToImageView(imageView, imgUrl, defaultResId);
    }

    @Override
    public ImageLoader getImageLoader() {
        return getBaseAppCompatActivity().getImageLoader();
    }

    public void showLoading() {
        getBaseAppCompatActivity().showLoading();
    }

    public void cancelLoading() {
        getBaseAppCompatActivity().cancelLoading();
    }

    protected BaseAppCompatActivity getBaseAppCompatActivity() {
        return (BaseAppCompatActivity) getActivity();
    }

    /**
     * 初始化可支持上下啦的控件
     *
     * @param refreshMode       刷新模式从PullMode中间取
     * @param pullToRefreshBase 基类View 可传多个不同的View
     */
    protected void initRefreshView(PullToRefreshBase.Mode refreshMode, PullToRefreshBase... pullToRefreshBase) {
        getBaseAppCompatActivity().initRefreshView(refreshMode, this, pullToRefreshBase);
    }

    /**
     * 数据加载完调用
     *
     * @param refreshMode 刷新完成后设置下拉上啦模式
     */
    protected void onLoad(PullToRefreshBase.Mode refreshMode, PullToRefreshBase... pullToRefreshBase) {
        //有时候getActivity null
        try {
            getBaseAppCompatActivity().onLoad(refreshMode, pullToRefreshBase);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }
}
