package com.atman.jishang.ui.manager.goods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.MyFragmentAdapter;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.ui.manager.classification.GoodsClasesActivity;
import com.atman.jishang.ui.personal.CreateShopActivity;
import com.corelib.widget.NoSwipeViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 商品管理
 * 作者 tangbingliang
 * 时间 16/4/26 16:58
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GoodsListActivity extends SimpleTitleBarActivity {

    @Bind(R.id.part_goodsmanagement_top_back_iv)
    ImageView partGoodsmanagementTopBackIv;
    @Bind(R.id.part_goodsmanagement_tabs_rg)
    RadioGroup partGoodsmanagementTabsRg;
    @Bind(R.id.part_goodsmanagement_top_right_tv)
    TextView partGoodsmanagementTopRightTv;
    @Bind(R.id.part_goodsmanagement_viewpager)
    NoSwipeViewPager partGoodsmanagementViewpager;
    @Bind(R.id.goodslist_emptyview_ll)
    LinearLayout goodslistEmptyviewLl;
    @Bind(R.id.goodslist_not_emptyview_ll)
    LinearLayout goodslistNotEmptyviewLl;

    private Context mContext = GoodsListActivity.this;
    private final String ONSALE_TAG = "on_sale";
    private final String UNDERSHELF_TAG = "under_shelf";
    private Fragment fg;
    private MyFragmentAdapter adapter;
    private String isOpen = "false";
    private int mSelectView = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodslist);
        ButterKnife.bind(this);
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
    }

    private void initBottomBar() {
        partGoodsmanagementTabsRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.part_goodsmanagement_tab_onsale:
                        mSelectView = 0;
                        selelctView();
                        break;
                    case R.id.part_goodsmanagement_tab_undershelf:
                        mSelectView = 1;
                        selelctView();
                        break;
                }
            }
        });
    }

    private void selelctView() {
        partGoodsmanagementViewpager.setCurrentItem(mSelectView, false);
        fg = adapter.getItem(mSelectView);
    }

    private void initViewpager() {
        if (BaiYeBaseApplication.mShopInformationModel == null) {
            isOpen = "false";
            goodslistEmptyviewLl.setVisibility(View.VISIBLE);
            goodslistNotEmptyviewLl.setVisibility(View.GONE);
        } else {
            isOpen = "true";
            goodslistEmptyviewLl.setVisibility(View.GONE);
            goodslistNotEmptyviewLl.setVisibility(View.VISIBLE);
        }
        partGoodsmanagementViewpager.setPagingEnabled(true);//是否支持手势滑动
        adapter = new MyFragmentAdapter(getSupportFragmentManager());

        GoodsListFragment oneFragment = new GoodsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("isOpen", isOpen);
        bundle.putString("TITLES", getResources().getString(R.string.goodsmanagement_onsale_tx));
        bundle.putString("goods_show", "1");
        oneFragment.setArguments(bundle);
        adapter.addFragment(oneFragment, ONSALE_TAG);

        GoodsListFragment twoFragment = new GoodsListFragment();
        Bundle bundle_two = new Bundle();
        bundle_two.putString("isOpen", isOpen);
        bundle_two.putString("TITLES", getResources().getString(R.string.goodsmanagement_undershelf_tx));
        bundle_two.putString("goods_show", "0");
        twoFragment.setArguments(bundle_two);
        adapter.addFragment(twoFragment, UNDERSHELF_TAG);

        partGoodsmanagementViewpager.setOffscreenPageLimit(2);
        partGoodsmanagementViewpager.setAdapter(adapter);
        partGoodsmanagementViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mSelectView = position;
                partGoodsmanagementTabsRg.getChildAt(position).performClick();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initViewpager();
        initBottomBar();
        selelctView();
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        hideTitleBar();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.part_goodsmanagement_top_back_iv, R.id.part_goodsmanagement_top_right_tv,
            R.id.goodsmanagement_add_ll, R.id.goodsmanagement_manage_ll,R.id.goodslist_emptyview_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.part_goodsmanagement_top_back_iv:
                finish();
                break;
            case R.id.part_goodsmanagement_top_right_tv:
                if (isOpen.equals("false")) {
                    showToast("请先创建店铺");
                } else {
                    startActivity(new Intent(mContext, GoodsClasesActivity.class));
                }
                break;
            case R.id.goodsmanagement_add_ll:
                startActivity(new Intent(mContext, AddGoodsActivity.class));
                break;
            case R.id.goodsmanagement_manage_ll:
                startActivity(GoodsListManagementActivity.buildIntent(
                        mContext, partGoodsmanagementViewpager.getCurrentItem()));
                break;
            case R.id.goodslist_emptyview_bt:
                startActivity(new Intent(mContext, CreateShopActivity.class));
                break;
        }
    }
}
