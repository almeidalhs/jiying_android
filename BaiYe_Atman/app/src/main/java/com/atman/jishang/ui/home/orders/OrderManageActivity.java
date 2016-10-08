package com.atman.jishang.ui.home.orders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.adapter.OrderManageAdapter;
import com.atman.jishang.net.model.OrderManageListModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.widget.pay.PayDialog;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 订单管理
 * 作者 tangbingliang
 * 时间 16/6/22 12:03
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class OrderManageActivity extends SimpleTitleBarActivity
        implements OrderManageAdapter.IonSlidingViewClickListener {

    @Bind(R.id.pull_refresh_recycler)
    PullToRefreshRecyclerView pullRefreshRecycler;
    @Bind(R.id.empty_tx)
    TextView emptyTx;

    private Context mContext = OrderManageActivity.this;
    private int mPage = 0;
    private int mCount = 10;

    private OrderManageAdapter mAdapter;
    private List<OrderManageListModel.BodyEntity> mAllList;
    private RecyclerView mRecyclerView;
    private PayDialog mPayDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordermanage);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle("订单管理");

        showRightTV("批量\n管理").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAllList==null || mAllList.size()<=0) {
                    showToast("没有可管理的订单");
                } else {
                    startActivity(new Intent(mContext, OrderBatchManageActivity.class));
                }
            }
        });
        initRefreshView(PullToRefreshBase.Mode.BOTH, pullRefreshRecycler);
        initListView();
    }

    private void initListView() {

        mAdapter = new OrderManageAdapter(mContext, getmWidth(), this);

        mRecyclerView = pullRefreshRecycler.getRefreshableView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));//这里用线性显示 类似于listview
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        mPage = 0;
        doHttp(true);
    }

    private void doHttp(boolean b) {
        getDataManager().getOrderList(mPage, mCount, OrderManageListModel.class, b);
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof OrderManageListModel) {
            OrderManageListModel mOrderManageListModel = (OrderManageListModel) response;
            mAllList = mOrderManageListModel.getBody();
            if (mAllList == null || mAllList.size() == 0) {
                onLoad(PullToRefreshBase.Mode.PULL_FROM_START, pullRefreshRecycler);
                if (mAdapter != null && mAdapter.getItemCount() == 0) {
                    emptyTx.setVisibility(View.VISIBLE);
                    pullRefreshRecycler.setVisibility(View.GONE);
                } else {
                    showToast("没有更多");
                }
            } else {
                emptyTx.setVisibility(View.GONE);
                pullRefreshRecycler.setVisibility(View.VISIBLE);
                onLoad(PullToRefreshBase.Mode.BOTH, pullRefreshRecycler);
            }
            mAdapter.addData(mAllList);
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mPage = 0;
        mAdapter.clearData();
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
        onLoad(PullToRefreshBase.Mode.BOTH, pullRefreshRecycler);
        showEmptyView();
    }

    private void showEmptyView() {
        if (mAdapter.getItemCount()==0) {
            emptyTx.setVisibility(View.VISIBLE);
            pullRefreshRecycler.setVisibility(View.GONE);
        } else {
            emptyTx.setVisibility(View.GONE);
            pullRefreshRecycler.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.item_ordermanage_pay_bt:
                mPayDialog = new PayDialog(mContext, mAdapter.getItemById(position).getId()+"");
                mPayDialog.show();
                break;
            case R.id.item_fullcut_root_rl:
                startActivity(OrderDetailActivity.buildIntent(mContext, mAdapter.getItemById(position).getId()+""));
                break;
        }
    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {

    }

    @OnClick(R.id.empty_tx)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.empty_tx:
                mPage = 0;
                doHttp(true);
                break;
        }
    }
}
