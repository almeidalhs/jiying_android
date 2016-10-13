package com.atman.jishang.ui.marketing;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.adapter.MarketingManagementAdapter;
import com.atman.jishang.net.model.MarketListModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.UiHelper;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述 营销管理
 * 作者 tangbingliang
 * 时间 16/5/16 16:40
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MarketingManagementActivity extends SimpleTitleBarActivity {

    @Bind(R.id.pullToRefreshListView)
    PullToRefreshListView pullToRefreshListView;

    private int mPage = 1;
    private int mCount = 10;
    private List<MarketListModel.BodyEntity> mMarketListModelList = new ArrayList<>();
    private MarketingManagementAdapter mAdapter;
    private Context mContext = MarketingManagementActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketingmanagement);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle(R.string.marketingmanagement_title);
        initRefreshView(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
        mAdapter = new MarketingManagementAdapter(mContext, getmWidth(), mMarketListModelList);
        pullToRefreshListView.setAdapter(mAdapter);
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UiHelper.toSecondLevelMarket(mContext, mAdapter.getItem(position-1));
            }
        });
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        getDataManager().getMarket(mPage, mCount, MarketListModel.class, true);
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof MarketListModel) {
            MarketListModel mMarketListModel = (MarketListModel) response;
            if (mMarketListModel.getResult().equals("1")) {
                mMarketListModelList = mMarketListModel.getBody();
                if (mMarketListModelList == null || mMarketListModelList.size() == 0) {
                    if (mAdapter!=null && mAdapter.getCount()>0) {
                        showToast("没有更多");
                    }
                    onLoad(PullToRefreshBase.Mode.PULL_FROM_START, pullToRefreshListView);
                } else {
                    onLoad(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
                    mAdapter.setBody(mMarketListModelList);
                }
            } else {
                showToast("营销管理列表获取失败");
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        if (mPage > 0) {
            mPage -= 1;
        }
        onLoad(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mPage = 1;
        mAdapter.clearBody();
        doInitBaseHttp();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        mPage += 1;
        doInitBaseHttp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
