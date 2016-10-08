package com.atman.jishang.ui.manager.orders;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.adapter.OrderBatchManageAdapter;
import com.atman.jishang.net.model.OrderManageListModel;
import com.atman.jishang.net.model.UnionOrdersModel;
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
public class OrderBatchManageActivity extends SimpleTitleBarActivity
        implements OrderBatchManageAdapter.IonSlidingViewClickListener {

    @Bind(R.id.pull_refresh_recycler)
    PullToRefreshRecyclerView pullRefreshRecycler;
    @Bind(R.id.empty_tx)
    TextView emptyTx;
    @Bind(R.id.ordermanage_num_tx)
    TextView ordermanageNumTx;
    @Bind(R.id.ordermanage_money_tx)
    TextView ordermanageMoneyTx;
    @Bind(R.id.item_ordermanage_ll)
    LinearLayout itemOrdermanageLl;

    private Context mContext = OrderBatchManageActivity.this;

    private OrderBatchManageAdapter mAdapter;
    private List<OrderManageListModel.BodyEntity> mAllList;
    private RecyclerView mRecyclerView;
    private TextView allSelect;
    private PayDialog mPayDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderbatchmanage);
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
        allSelect = showRightTV("全选");
        allSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allSelect.getText().equals("全选")) {
                    allSelect.setText("取消全选");
                    mAdapter.allSelectList(true);
                } else {
                    allSelect.setText("全选");
                    mAdapter.allSelectList(false);
                }
                showBottomLl();
            }
        });
        initRefreshView(PullToRefreshBase.Mode.DISABLED, pullRefreshRecycler);
        initListView();
    }

    private void initListView() {

        mAdapter = new OrderBatchManageAdapter(mContext, getmWidth(), this);

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
        doHttp(true);
    }

    private void doHttp(boolean b) {
        getDataManager().getPayOrderList(OrderManageListModel.class, b);
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof OrderManageListModel) {
            OrderManageListModel mOrderManageListModel = (OrderManageListModel) response;
            mAllList = mOrderManageListModel.getBody();
            if (mAllList == null || mAllList.size() == 0) {
                if (mAdapter != null && mAdapter.getItemCount() == 0) {
                    emptyTx.setVisibility(View.VISIBLE);
                    pullRefreshRecycler.setVisibility(View.GONE);
                } else {
                    showToast("没有更多");
                }
            } else {
                emptyTx.setVisibility(View.GONE);
                pullRefreshRecycler.setVisibility(View.VISIBLE);
            }
            mAdapter.addData(mAllList);
            showBottomLl();
        } else if (response instanceof UnionOrdersModel) {
            UnionOrdersModel mUnionOrdersModel = (UnionOrdersModel)response;
            if (mUnionOrdersModel.getResult().equals("1")) {
                mPayDialog = new PayDialog(mContext, mUnionOrdersModel.getBody().getId()+"");
                mPayDialog.show();
            } else {
                showToast(mUnionOrdersModel.getBody().getMessage());
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        onLoad(PullToRefreshBase.Mode.BOTH, pullRefreshRecycler);
        showEmptyView();
    }

    private void showEmptyView() {
        if (mAdapter.getItemCount() == 0) {
            emptyTx.setVisibility(View.VISIBLE);
            pullRefreshRecycler.setVisibility(View.GONE);
        } else {
            emptyTx.setVisibility(View.GONE);
            pullRefreshRecycler.setVisibility(View.VISIBLE);
        }
        showBottomLl();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.item_ordermanage_pay_bt:

                break;
            case R.id.item_fullcut_root_rl:
                startActivity(OrderDetailActivity.buildIntent(mContext, mAdapter.getItemById(position).getId() + ""));
                break;
            case R.id.item_ordermanage_select_iv:
                if (mAdapter.selectById(position)) {
                    allSelect.setText("取消全选");
                } else {
                    allSelect.setText("全选");
                }
                showBottomLl();
                break;
        }
    }

    private void showBottomLl(){
        if (mAdapter.isSelected()) {
            itemOrdermanageLl.setVisibility(View.VISIBLE);
        } else {
            itemOrdermanageLl.setVisibility(View.GONE);
        }

        List<OrderManageListModel.BodyEntity> list = mAdapter.getSelectedList();
        ordermanageNumTx.setText("订  单  数："+list.size());
        int money = 0;
        for (int i=0;i<list.size();i++) {
            money += list.get(i).getTotal_fee();
        }
        ordermanageMoneyTx.setText("付款金额：¥ "+money);

        if (list.size() == mAdapter.getItemCount()) {
            allSelect.setText("取消全选");
        } else {
            allSelect.setText("全选");
        }
    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {

    }

    @OnClick({R.id.empty_tx, R.id.orderbatchmanage_pay_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.empty_tx:
                doHttp(true);
                break;
            case R.id.orderbatchmanage_pay_bt:
                String str = "";
                List<OrderManageListModel.BodyEntity> list = mAdapter.getSelectedList();
                for (int i=0;i<list.size();i++) {
                    if (i!=0) {
                        str += ",";
                    }
                    str += list.get(i).getId();
                }
                if (str == "") {
                    showToast("请选择要支付的订单");
                    return;
                }
                getDataManager().unionOrder(str, UnionOrdersModel.class, true);
                break;
        }
    }
}
