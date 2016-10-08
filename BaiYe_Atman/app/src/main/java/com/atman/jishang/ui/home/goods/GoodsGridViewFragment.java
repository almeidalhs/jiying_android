package com.atman.jishang.ui.home.goods;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.adapter.GoodsGridViewAdapter;
import com.atman.jishang.ui.base.BaiYeBaseFragment;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.model.GetGoodsByClassIdModel;
import com.corelib.util.LogUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/25 15:25
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GoodsGridViewFragment extends BaiYeBaseFragment implements AdapterInterface {
    @Bind(R.id.pull_refresh_grid)
    PullToRefreshGridView pullRefreshGrid;

    private GetGoodsByClassIdModel mGetGoodsByClassIdModel;
    private List<GetGoodsByClassIdModel.BodyEntity> mBodyEntityList;

    private String categoryId;//商品分类id 0：全部
    private String title;
    private int GoodsType = 1;//商品状态 1：出售中，0:已下架
    private int page = 0;//商品页码
    private int mPageSize = 20;//每页个数
    private GoodsGridViewAdapter mAdapter;
    protected ImageLoader imageLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_goodsgridview, null);
        ButterKnife.bind(this, v);
        initRefreshView(PullToRefreshBase.Mode.BOTH, pullRefreshGrid);
        Bundle b = getArguments();
        title = b.getString("TITLES");
        categoryId = b.getString("typeId");
        page = 0;
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onResponse(Object response) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(View view, int position) {
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
    }
}