package com.atman.jishang.ui.circle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.CircleListAdapter;
import com.atman.jishang.net.model.NewsListModel;
import com.atman.jishang.ui.base.BaiYeBaseFragment;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/6/20 11:08
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class CircleFragment extends BaiYeBaseFragment {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.circle_title)
    RelativeLayout circleTitle;
    @Bind(R.id.pullToRefreshListView)
    PullToRefreshListView pullToRefreshListView;
    @Bind(R.id.circle_totop)
    ImageView circleTotop;

    private int state = 1;
    private int page = 0;
    private int count = 20;
    private NewsListModel mNewsListModel;
    private CircleListAdapter mAdapter;
    private boolean isHint = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle, null);
        ButterKnife.bind(this, view);
        initRefreshView(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        doHttp(true);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        setToolbarTitle(getActivity().getResources().getString(R.string.tab_message));
    }

    private void setToolbarTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        state = 1;
        page = 0;
        mAdapter.clearBody();
        doHttp(false);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page += 1;
        doHttp(false);
    }

    private void doHttp(boolean b) {
        getDataManager().getNews(state, page, count, NewsListModel.class, b);
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof NewsListModel) {
            mNewsListModel = (NewsListModel) response;
            if (mNewsListModel.getResult().equals("1")) {
                if (mNewsListModel.getBody() == null || mNewsListModel.getBody().size() == 0) {
                    if (isHint) {
                        if (mAdapter.getCount() > 0) {
                            showToast("没有更多");
                        }
                    }
                    onLoad(PullToRefreshBase.Mode.PULL_FROM_START, pullToRefreshListView);
                } else {
                    onLoad(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
                    mAdapter.setBody(mNewsListModel.getBody());
                }
            } else {
                showToast("资讯数据获取失败");
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isHint = true;
        } else {
            isHint = false;
        }
    }

    @OnClick(R.id.publish_iv)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.publish_iv:
                break;
        }
    }
}
