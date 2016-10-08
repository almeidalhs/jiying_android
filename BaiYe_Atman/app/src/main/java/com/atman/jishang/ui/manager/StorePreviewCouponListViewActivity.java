package com.atman.jishang.ui.manager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.adapter.StorePreviewCouponListviewAdapter;
import com.atman.jishang.net.model.GetCouponListModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/6/8 13:07
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class StorePreviewCouponListViewActivity extends SimpleTitleBarActivity {

    @Bind(R.id.pullToRefreshListView)
    PullToRefreshListView pullToRefreshListView;

    private Context mContext = StorePreviewCouponListViewActivity.this;
    private int mPage = 0;
    private int mCount = 10;
    private String mState = "2";//满减活动的状态（1：未开始，2：进行中，3：已结束）

    private List<GetCouponListModel.BodyEntity> mAllCouponList = new ArrayList<>();
    private StorePreviewCouponListviewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storepreviewcouponlistview);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdapter != null) {
            mAdapter.clearBody();
        }
        mPage = 0;
        doHttp(true);
    }

    private void doHttp(boolean b) {
        getDataManager().getCouponList(mState, mPage, mCount, GetCouponListModel.class, b);
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle("优惠券列表");
        initRefreshView(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);

        initListView();
    }

    private void initListView() {
        mAdapter = new StorePreviewCouponListviewAdapter(mContext, mAllCouponList);
        pullToRefreshListView.setAdapter(mAdapter);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof GetCouponListModel) {
            GetCouponListModel mGetCouponListModel = (GetCouponListModel) response;
            mAllCouponList = mGetCouponListModel.getBody();
            if (mAllCouponList == null || mAllCouponList.size() == 0) {
                onLoad(PullToRefreshBase.Mode.PULL_FROM_START, pullToRefreshListView);
                if (mAdapter!=null && mAdapter.getCount() == 0) {
                } else {
                    showToast("没有更多");
                }
            } else {
                onLoad(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
                mAdapter.setBody(mAllCouponList);
            }
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mPage = 0;
        mAdapter.clearBody();
        doHttp(false);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        mPage += 1;
        doHttp(false);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        onLoad(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
