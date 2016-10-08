package com.atman.jishang.ui.manager.goods;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.adapter.GoodsListViewAdapter;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.BaiYeBaseFragment;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.model.GetGoodsByClassIdModel;
import com.corelib.util.LogUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述 出售中和已下架商品列表
 * 作者 tangbingliang
 * 时间 16/4/27 10:20
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GoodsListFragment extends BaiYeBaseFragment implements AdapterInterface {

    @Bind(R.id.pullToRefreshListView)
    PullToRefreshListView pullToRefreshListView;

    private String title;
    private String goodsShow;
    private String stc_id;
    private String isOpen = "";
    private int page;
    private int mPageSize = 20;//每页个数
    private String categoryId = "0";
    private GoodsListViewAdapter mAdapter;
    private boolean isRecord = false;
    private boolean isHint = false;

    private GetGoodsByClassIdModel mGetGoodsByClassIdModel;
    private List<GetGoodsByClassIdModel.BodyEntity> mBodyEntityList;

    protected ImageLoader imageLoader;
    private View mEmpty;
    private TextView mEmptyTX;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goodslist, null);
        ButterKnife.bind(this, view);
        initRefreshView(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
        Bundle b = getArguments();
        title = b.getString("TITLES");
        goodsShow = b.getString("goods_show");
        stc_id = b.getString("id");
        isOpen = b.getString("isOpen");
        isRecord = b.getBoolean("isRecord");
        page = 0;
        LogUtils.e(title+":"+isOpen);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        imageLoader = ImageLoader.getInstance();
        mEmpty = LayoutInflater.from(getActivity()).inflate(R.layout.part_list_empty, null);
        mEmptyTX = (TextView) mEmpty.findViewById(R.id.empty_list_tx);
        mEmptyTX.setText("暂无"+title+"商品");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isOpen ==null) {
            return;
        }
        LogUtils.e(">>>>>isOpen:"+isOpen);
        if (isOpen.equals("true")) {
            initListView();
            doHttp();
        }
    }

    public void doHttp() {
        if (stc_id != null && !stc_id.isEmpty() && !stc_id.equals("-1")) {
            categoryId = stc_id;
        }
        if (goodsShow == null || goodsShow.isEmpty()) {
            return;
        }
        if (BaiYeBaseApplication.mShopInformationModel==null) {
            return;
        }
        getDataManager().getGoodsByClassId(categoryId, Integer.parseInt(goodsShow),
                page, mPageSize, GetGoodsByClassIdModel.class, true);
    }

    private void initListView() {
        mAdapter = new GoodsListViewAdapter(getActivity(), getmWidth(), imageLoader, this);
        pullToRefreshListView.setEmptyView(mEmpty);
        pullToRefreshListView.setAdapter(mAdapter);
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (isRecord) {
                    Intent mIntent = new Intent();
                    mIntent.putExtra("goodsId",mAdapter.getItem(position - 1).getId());
                    mIntent.putExtra("goodsPrice",mAdapter.getItem(position - 1).getPrice());
                    mIntent.putExtra("goodsName",mAdapter.getItem(position - 1).getGoodsName());
                    mIntent.putExtra("goodsInfo",mAdapter.getItem(position - 1).getGoodsDescription());
                    mIntent.putExtra("goodsImage",mAdapter.getItem(position - 1).getGoodsImage());
                    getActivity().setResult(getActivity().RESULT_OK,mIntent);
                    getActivity().finish();
                } else {
                    startActivity(GoodsDetailsActivity.buildIntent(getActivity(),
                            mAdapter.getItem(position - 1).getGoodsName(),
                            mAdapter.getItem(position - 1).getId(),
                            mAdapter.getItem(position - 1).getGoodsShow()));
                }
            }
        });
    }

    @Override
    public void onResponse(Object response) {
        if (response instanceof GetGoodsByClassIdModel) {
            super.onResponse(response);
            mGetGoodsByClassIdModel = (GetGoodsByClassIdModel) response;
            if (mGetGoodsByClassIdModel.getResult().equals("1")) {
                mBodyEntityList = mGetGoodsByClassIdModel.getBody();
                if (mBodyEntityList == null || mBodyEntityList.size() == 0) {
                    if (isHint) {
                        if (mAdapter.getCount()>0) {
                            showToast("没有更多");
                        }
                    }
                    onLoad(PullToRefreshBase.Mode.PULL_FROM_START, pullToRefreshListView);
                } else {
                    onLoad(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
                    mAdapter.setBody(mBodyEntityList);
                }
            } else {
                showToast("商品获取失败");
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        if (page>0) {
            page -= 1;
        }
        onLoad(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
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
        page = 0;
        mAdapter.clearBody();
        doHttp();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page += 1;
        doHttp();
    }
}
