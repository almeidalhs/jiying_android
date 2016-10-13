package com.atman.jishang.ui.service.foodpreview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.adapter.ClassGridViewAdapter;
import com.atman.jishang.adapter.GoodsGridViewAdapter;
import com.atman.jishang.adapter.TabAdapter;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.interfaces.CreateQRCodeInterface;
import com.atman.jishang.net.model.GetGoodsByClassIdModel;
import com.atman.jishang.net.model.ModuleListModel;
import com.atman.jishang.net.model.StoreGoodsClassesModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.ui.manager.goods.GoodsDetailsActivity;
import com.atman.jishang.ui.manager.goods.GoodsGridViewFragment;
import com.atman.jishang.ui.service.code.CreateQRCodeActivity;
import com.corelib.util.LogUtils;
import com.corelib.widget.MyGridView;
import com.corelib.widget.NoSwipeViewPager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tangbingliang on 16/10/13.
 */

public class FoodPreviewActivity extends SimpleTitleBarActivity implements AdapterInterface {

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.storepreview_more_iv)
    ImageView storepreviewMoreIv;
    @Bind(R.id.storepreview_tab_ll)
    LinearLayout storepreviewTabLl;
    @Bind(R.id.store_empty_tx)
    TextView storeEmptyTx;
    @Bind(R.id.storepreview_grid)
    MyGridView storepreviewGrid;
    @Bind(R.id.info_viewpager)
    NoSwipeViewPager infoViewpager;
    @Bind(R.id.pullToRefreshScrollView)
    PullToRefreshScrollView pullToRefreshScrollView;

    private Context mContext = FoodPreviewActivity.this;

    private int Id;
    private int moduleId;
    private int moduleStatus;
    private String title;

    private String categoryId = "0";//商品分类id 0：全部
    private int GoodsType = 1;//商品状态 1：出售中，0:已下架
    private int page = 1;//商品页码
    private int mPageSize = 20;//每页个数
    private GoodsGridViewAdapter mAdapter;
    private List<StoreGoodsClassesModel.BodyEntity> list = new ArrayList<>();
    private StoreGoodsClassesModel mStoreGoodsClassesModel;
    private GetGoodsByClassIdModel mGetGoodsByClassIdModel;
    private List<GetGoodsByClassIdModel.BodyEntity> mBodyEntityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodpreview);
        ButterKnife.bind(this);
    }

    public static Intent bulidIntent(Context context, String title, int Id, int moduleId, int moduleStatus) {
        Intent intent = new Intent(context, FoodPreviewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("Id", Id);
        intent.putExtra("moduleId", moduleId);
        intent.putExtra("moduleStatus", moduleStatus);
        return intent;
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);

        moduleId = getIntent().getIntExtra("moduleId", -1);
        Id = getIntent().getIntExtra("Id", -1);
        moduleStatus = getIntent().getIntExtra("moduleStatus", -1);
        title = getIntent().getStringExtra("title");

        setToolbarTitle(title);
        showRightTV("制码").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ModuleListModel> listModel = new ArrayList<>();
                ModuleListModel temp = new ModuleListModel(title, Id, moduleStatus);
                listModel.add(temp);
                startActivity(CreateQRCodeActivity.buildIntent(mContext,
                        CreateQRCodeInterface.QRCodeTypeMenu
                        , CreateQRCodeInterface.SingleType.All, listModel));
            }
        });

        initRefreshView(PullToRefreshBase.Mode.BOTH, pullToRefreshScrollView);

        mAdapter = new GoodsGridViewAdapter(mContext, getmWidth(), ImageLoader.getInstance(), this);
        storepreviewGrid.setAdapter(mAdapter);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        getDataManager().getGoodsClasses(StoreGoodsClassesModel.class, true);
    }

    public void doHttp() {
        getDataManager().getGoodsByClassId(categoryId, GoodsType, page, mPageSize, GetGoodsByClassIdModel.class, false);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page = 1;
        mAdapter.clearBody();
        doHttp();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page += 1;
        doHttp();
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof StoreGoodsClassesModel) {
            mStoreGoodsClassesModel = (StoreGoodsClassesModel) response;
            if (mStoreGoodsClassesModel.getResult().equals("1")) {
                int num = 0;
                for (int i = 0; i < mStoreGoodsClassesModel.getBody().size(); i++) {
                    num += mStoreGoodsClassesModel.getBody().get(i).getGoodsCount();
                    if (mStoreGoodsClassesModel.getBody().get(i).getGoodsCount() != 0) {
                        list.add(mStoreGoodsClassesModel.getBody().get(i));
                    }
                }
                StoreGoodsClassesModel.BodyEntity mFirst = new StoreGoodsClassesModel.BodyEntity(0, "全部", 0,
                        1, 0, 0, num);
                if (mStoreGoodsClassesModel.getBody().size() != 0) {
                    mFirst = new StoreGoodsClassesModel.BodyEntity(0, "全部", 0,
                            mStoreGoodsClassesModel.getBody().get(0).getStcState(),
                            mStoreGoodsClassesModel.getBody().get(0).getStoreId(),
                            0, num);
                }
                list.add(0, mFirst);
                initCategoryView();
                doHttp();
            } else {
                showToast("商品分类获取失败");
            }
        } else if (response instanceof GetGoodsByClassIdModel) {
            super.onResponse(response, data);
            mGetGoodsByClassIdModel = (GetGoodsByClassIdModel) response;
            if (mGetGoodsByClassIdModel.getResult().equals("1")) {
                mBodyEntityList = mGetGoodsByClassIdModel.getBody();
                if (mBodyEntityList == null || mBodyEntityList.size() == 0) {
                    onLoad(PullToRefreshBase.Mode.PULL_FROM_START, pullToRefreshScrollView);
                } else {
                    if (mBodyEntityList.size() < mPageSize) {
                        onLoad(PullToRefreshBase.Mode.PULL_FROM_START, pullToRefreshScrollView);
                    } else {
                        onLoad(PullToRefreshBase.Mode.BOTH, pullToRefreshScrollView);
                    }
                }
                mAdapter.setBody(mBodyEntityList);
//                if (categoryId.equals("0") && mAdapter.getCount() == 0) {
//                    storeEmptyTx.setVisibility(View.VISIBLE);
//                    storeEmptyTx.setText("您的店铺暂无上架的商品");
//                } else if (!categoryId.equals("0") && mAdapter.getCount() == 0) {
//                    storeEmptyTx.setVisibility(View.VISIBLE);
//                    storeEmptyTx.setText("该商品分类暂无上架的商品");
//                }
            } else {
                showToast("商品获取失败");
            }
        }
    }

    private List<Fragment> fragments;
    private TabAdapter fragPagerAdapter;

    private void initCategoryView() {
        fragments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            GoodsGridViewFragment oneFragment = new GoodsGridViewFragment();
            Bundle bundle = new Bundle();
            bundle.putString("TITLES", list.get(i).getStcName());
            bundle.putString("typeId", String.valueOf(list.get(i).getId()));
            oneFragment.setArguments(bundle);
            fragments.add(oneFragment);
        }
        fragPagerAdapter = new TabAdapter(getSupportFragmentManager());
        //设置显示的标题
        fragPagerAdapter.setList(list);
        //设置需要进行滑动的页面Fragment
        fragPagerAdapter.setFragments(fragments);

        infoViewpager.setAdapter(fragPagerAdapter);
        tabLayout.setupWithViewPager(infoViewpager);
        //设置TabLayout模式 -该使用Tab数量比较多的情况
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                infoViewpager.setCurrentItem(tabLayout.getSelectedTabPosition());
                getListByPosition(tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void getListByPosition(int selectedTabPosition) {
        LogUtils.e("tabLayout>>Position:" + selectedTabPosition);
        categoryId = String.valueOf(list.get(selectedTabPosition).getId());
        page = 1;
        mAdapter.clearBody();
        getDataManager().getGoodsByClassId(categoryId, GoodsType, page, mPageSize, GetGoodsByClassIdModel.class, true);
    }

    @OnClick({R.id.storepreview_more_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.storepreview_more_iv:
                storepreviewMoreIv.setImageResource(R.mipmap.goods_class_more_up);
                showPopWindow(view);
                break;
        }
    }

    private void showPopWindow(View v) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(mContext).inflate(
                R.layout.class_pop_window, null);
        final PopupWindow popupWindow = new PopupWindow(contentView, getmWidth(),
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        GridView gridview = (GridView) contentView.findViewById(R.id.pop_window_gv);
        ClassGridViewAdapter mClassGridViewAdapter = new ClassGridViewAdapter(mContext, list);
        gridview.setAdapter(mClassGridViewAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                infoViewpager.setCurrentItem(position);
//                getListByPosition(position);
            }
        });
        popupWindow.setTouchable(true);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.color.color_white));
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //改变显示的按钮图片为正常状态
                storepreviewMoreIv.setImageResource(R.mipmap.goods_class_more);
            }
        });

        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        if (page > 0) {
            page -= 1;
        }
        onLoad(PullToRefreshBase.Mode.BOTH, pullToRefreshScrollView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.item_goodspreview_ll:
                startActivity(GoodsDetailsActivity.buildIntent(mContext,
                        mAdapter.getItem(position).getGoodsName(),
                        mAdapter.getItem(position).getId(),
                        mAdapter.getItem(position).getGoodsShow()));
                break;
        }
    }
}
