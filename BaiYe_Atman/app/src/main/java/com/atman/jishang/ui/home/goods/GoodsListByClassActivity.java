package com.atman.jishang.ui.home.goods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.corelib.widget.NoSwipeViewPager;

import java.util.ArrayList;
import java.util.List;

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
public class GoodsListByClassActivity extends SimpleTitleBarActivity {

    @Bind(R.id.part_goodsmanagement_top_back_iv)
    ImageView partGoodsmanagementTopBackIv;
    @Bind(R.id.part_goodsmanagement_tabs_rg)
    RadioGroup partGoodsmanagementTabsRg;
    @Bind(R.id.part_goodsmanagement_top_name_tv)
    TextView partGoodsmanagementTopNameTv;
    @Bind(R.id.part_goodsmanagement_viewpager)
    NoSwipeViewPager partGoodsmanagementViewpager;

    private Context mContext = GoodsListByClassActivity.this;
    private final String ONSALE_TAG = "on_sale";
    private final String UNDERSHELF_TAG = "under_shelf";
    private Fragment fg;
    private Adapter adapter;
    private long mStcId;
    private String mStcName;

    private boolean isRecord = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodslistbyclass);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context,String name,long id){
        Intent intent = new Intent(context, GoodsListByClassActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("id", id);
        return intent;
    }

    public static Intent buildIntent(Context context,String name,long id,boolean isReord){
        Intent intent = new Intent(context, GoodsListByClassActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("id", id);
        intent.putExtra("isReord", isReord);
        return intent;
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        mStcId = getIntent().getLongExtra("id", -1);
        mStcName = getIntent().getStringExtra("name");
        isRecord = getIntent().getBooleanExtra("isReord", false);
        partGoodsmanagementTopNameTv.setText(mStcName);
        initViewpager();
        initBottomBar();
    }

    private void initBottomBar() {
        partGoodsmanagementTabsRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.part_goodsmanagement_tab_onsale:
                        partGoodsmanagementViewpager.setCurrentItem(0, false);
                        fg = adapter.getItem(0);
                        break;
                    case R.id.part_goodsmanagement_tab_undershelf:
                        partGoodsmanagementViewpager.setCurrentItem(1, false);
                        fg = adapter.getItem(1);
                        break;
                }
            }
        });
    }

    private void initViewpager() {
        partGoodsmanagementViewpager.setPagingEnabled(true);//是否支持手势滑动
        adapter = new Adapter(getSupportFragmentManager());

        GoodsListFragment oneFragment = new GoodsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("isOpen", "true");
        bundle.putString("TITLES", getResources().getString(R.string.goodsmanagement_onsale_tx));
        bundle.putString("goods_show", "1");
        bundle.putString("id", String.valueOf(mStcId));
        bundle.putBoolean("isRecord", isRecord);
        oneFragment.setArguments(bundle);
        adapter.addFragment(oneFragment, ONSALE_TAG);

        GoodsListFragment twoFragment = new GoodsListFragment();
        Bundle bundle_two = new Bundle();
        bundle_two.putString("isOpen", "true");
        bundle_two.putString("TITLES", getResources().getString(R.string.goodsmanagement_undershelf_tx));
        bundle_two.putString("goods_show", "0");
        bundle_two.putString("id", String.valueOf(mStcId));
        bundle_two.putBoolean("isRecord", isRecord);
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

    @OnClick({R.id.part_goodsmanagement_top_back_iv, R.id.goodsmanagement_add_ll,
            R.id.goodsmanagement_manage_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.part_goodsmanagement_top_back_iv:
                finish();
                break;
            case R.id.goodsmanagement_add_ll:
                startActivity(AddGoodsActivity.buildIntent(mContext, getIntent().getIntExtra("id",-1)));
                break;
            case R.id.goodsmanagement_manage_ll:
                startActivity(GoodsListManagementActivity.buildIntent(
                        mContext, partGoodsmanagementViewpager.getCurrentItem(),mStcId, mStcName));
                break;
        }
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
