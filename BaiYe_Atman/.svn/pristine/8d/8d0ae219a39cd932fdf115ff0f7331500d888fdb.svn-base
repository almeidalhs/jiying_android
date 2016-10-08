package com.corelib.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.ui.floatingactionbutton.FloatingActionButton;
import com.seawind.corelib.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by cym on 15/3/21.
 */
public class YLBRecyclerview extends UltimateRecyclerView {

    public YLBRefreshFrameLayout mPtrFrameLayout;

    public YLBRecyclerview(Context context) {
        super(context);
    }

    public YLBRecyclerview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public YLBRecyclerview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initViews() {
        //super.initViews();
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ylb_recyclerview_layout, this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.ultimate_list);
        mSwipeRefreshLayout = null;

        if (mRecyclerView != null) {

            mRecyclerView.setClipToPadding(mClipToPadding);
            if (mPadding != -1.1f) {
                mRecyclerView.setPadding(mPadding, mPadding, mPadding, mPadding);
            } else {
                mRecyclerView.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
            }
        }

        defaultFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.defaultFloatingActionButton);
        setDefaultScrollListener();

        mEmpty = (ViewStub) view.findViewById(R.id.emptyview);
        mFloatingButtonViewStub = (ViewStub) view.findViewById(R.id.floatingActionViewStub);

        mEmpty.setLayoutResource(mEmptyId);

        mFloatingButtonViewStub.setLayoutResource(mFloatingButtonId);

        if (mEmptyId != 0)
            mEmptyView = mEmpty.inflate();
        mEmpty.setVisibility(View.GONE);

        if (mFloatingButtonId != 0) {
            mFloatingButtonView = mFloatingButtonViewStub.inflate();
            mFloatingButtonView.setVisibility(View.VISIBLE);
        }
        mPtrFrameLayout = (YLBRefreshFrameLayout) findViewById(R.id.pullrefresh);
    }

    public void setCustomSwipeToRefresh() {
        mPtrFrameLayout = (YLBRefreshFrameLayout) findViewById(R.id.pullrefresh);
        mPtrFrameLayout.setResistance(1.7f);
        mPtrFrameLayout.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrameLayout.setDurationToClose(200);
        mPtrFrameLayout.setDurationToCloseHeader(1000);
        mPtrFrameLayout.setPullToRefresh(false);
        mPtrFrameLayout.setKeepHeaderWhenRefresh(true);
    }

    public static abstract class RecyclerViewPtrHandler implements PtrHandler {
        @Override
        public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
            RecyclerView recyclerView = (RecyclerView) content;
            View firstView = recyclerView.getChildAt(0);
            int i = recyclerView.getChildAdapterPosition(firstView);
            if(i==0) {
                return true;
            }
            return false;
        }
    }

    /*
    * 设置下拉刷新回调接口
    * */
    public void setPulldownRefreshListener(RecyclerViewPtrHandler listener) {
        if(listener != null) {
            mPtrFrameLayout.setPtrHandler(listener);
        }
    }

    public void refreshComplete() {
        if(mPtrFrameLayout != null) {
            mPtrFrameLayout.refreshComplete();
        }
    }

}
