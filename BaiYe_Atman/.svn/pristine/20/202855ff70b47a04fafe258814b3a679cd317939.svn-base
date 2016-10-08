package com.corelib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seawind.corelib.R;
import com.corelib.util.LogUtils;

/**
 * Created by loyee on 15-12-24.
 */
public class LoadingLinearLayout extends LinearLayout {
    public LoadingLinearLayout(Context context) {
        super(context);
        init();
    }

    public LoadingLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mErrorView = createErrorView(getLayoutInflater());
        mLoadingView = createLoadingView(getLayoutInflater());
        addView(mLoadingView,params);
        addView(mErrorView,params);
        mErrorView.setVisibility(GONE);
        mLoadingView.setVisibility(GONE);
    }

    private View mLoadingView;
    private View mErrorView;
    private ShowType mType = ShowType.CONTENT;
    private enum ShowType {
        CONTENT,
        LOADING,
        ERROR
    }

    private View createLoadingView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.loadingview, null);
    }

    private View createErrorView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.errorview, null);
    }

    public void showLoading() {
        if (mLoadingView == null) {
            mLoadingView = createLoadingView(LayoutInflater.from(getContext()));
        }
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.VISIBLE);
            mType = ShowType.LOADING;
        } else {
            LogUtils.e("loading view is not ready,is null");
        }


        if (mErrorView != null) {
            mErrorView.setVisibility(View.GONE);
        }
    }

    public void showError(String msg) {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.GONE);
        }
        if (mErrorView == null) {
            mErrorView = createErrorView(getLayoutInflater());
        }
        if (mErrorView != null) {
            mErrorView.setVisibility(View.VISIBLE);
            TextView tv = (TextView) mErrorView.findViewById(R.id.errorMsg);
            tv.setText(msg);
            mType = ShowType.ERROR;
        }
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    public void showContent() {
        mErrorView.setVisibility(GONE);
        mLoadingView.setVisibility(GONE);
//        if (mContentView == null) {
//            LogUtils.e("enable addLoadingView first");
//        }
//        if (mLoadingView != null) {
//            mLoadingView.setVisibility(View.GONE);
//        }
//        if (mContentView != null) {
//            mContentView.setVisibility(View.VISIBLE);
//            mType = ShowType.CONTENT;
//        }
//        if (mErrorView != null) {
//            mErrorView.setVisibility(View.GONE);
//        }
    }

}
