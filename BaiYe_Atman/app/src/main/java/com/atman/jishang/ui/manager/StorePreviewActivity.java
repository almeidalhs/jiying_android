package com.atman.jishang.ui.manager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.adapter.ClassGridViewAdapter;
import com.atman.jishang.adapter.GoodsGridViewAdapter;
import com.atman.jishang.adapter.StorePreviewFullcutGridviewAdapter;
import com.atman.jishang.adapter.TabAdapter;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.interfaces.MyUMShareListenner;
import com.atman.jishang.net.Urls;
import com.atman.jishang.net.model.AddRecordFullCutListModel;
import com.atman.jishang.net.model.GetCouponListModel;
import com.atman.jishang.net.model.GetGoodsByClassIdModel;
import com.atman.jishang.net.model.ShopInformationModel;
import com.atman.jishang.net.model.StoreGoodsClassesModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.ui.manager.goods.GoodsDetailsActivity;
import com.atman.jishang.ui.manager.goods.GoodsGridViewFragment;
import com.atman.jishang.ui.personal.CreateShopActivity;
import com.atman.jishang.ui.personal.ShopInformationActivity;
import com.atman.jishang.utils.UiHelper;
import com.atman.jishang.utils.save.Tools;
import com.atman.jishang.widget.ShareDialog;
import com.atman.jishang.widget.YLBDialog;
import com.corelib.util.LogUtils;
import com.corelib.widget.MyGridView;
import com.corelib.widget.NoSwipeViewPager;
import com.handmark.pulltorefresh.library.ObservableScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.ScrollViewListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 店铺预览
 * 作者 tangbingliang
 * 时间 16/4/25 11:31
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class StorePreviewActivity extends SimpleTitleBarActivity
        implements AdapterInterface, ScrollViewListener ,DialogInterface.OnClickListener{

    @Bind(R.id.part_storeptrview_top_back_iv)
    ImageView partStoreptrviewTopBackIv;
    @Bind(R.id.part_storeptrview_top_sreach_ll)
    LinearLayout partStoreptrviewTopSreachLl;
    @Bind(R.id.part_storeptrview_top_right_tv)
    TextView partStoreptrviewTopRightTv;
    @Bind(R.id.part_store_preview_headbg_img)
    ImageView partStorePreviewHeadbgImg;
    @Bind(R.id.part_store_preview_head_img)
    ImageView partStorePreviewHeadImg;
    @Bind(R.id.part_store_preview_head_ll)
    LinearLayout partStorePreviewHeadLl;
    @Bind(R.id.part_store_preview_shopname)
    TextView partStorePreviewShopname;
    @Bind(R.id.part_store_preview_shopaddre_tx)
    TextView partStorePreviewShopaddreTx;
    @Bind(R.id.part_store_preview_head_rl)
    RelativeLayout partStorePreviewHeadRl;
    @Bind(R.id.part_store_preview_shopsignature_tx)
    TextView partStorePreviewShopsignatureTx;
    @Bind(R.id.store_empty_tx)
    TextView mEmptyViewTx;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.storepreview_more_iv)
    ImageView storepreviewMoreIv;
    @Bind(R.id.storepreview_tab_ll)
    LinearLayout storepreviewTabLl;
    @Bind(R.id.pullToRefreshScrollView)
    PullToRefreshScrollView pullToRefreshScrollView;
    @Bind(R.id.info_viewpager)
    NoSwipeViewPager infoViewpager;
    @Bind(R.id.storepreview_grid)
    MyGridView pullRefreshGrid;
    @Bind(R.id.storepreview_top_bar_ll)
    LinearLayout storepreviewTopBarLl;
    @Bind(R.id.top_tab_layout)
    TabLayout topTabLayout;
    @Bind(R.id.top_storepreview_more_iv)
    ImageView topStorepreviewMoreIv;
    @Bind(R.id.storepreview_top_tab_ll)
    LinearLayout storepreviewTopTabLl;
    @Bind(R.id.storepreview_fullcut_tx)
    TextView storepreviewFullcutTx;
    @Bind(R.id.storepreview_fullcut_ll)
    LinearLayout storepreviewFullcutLl;
    @Bind(R.id.storepreview_coupon_gv)
    MyGridView storepreviewCouponGv;

    private Context mContext = StorePreviewActivity.this;

    private StoreGoodsClassesModel mStoreGoodsClassesModel;
    private List<StoreGoodsClassesModel.BodyEntity> list = new ArrayList<>();
    private TabAdapter fragPagerAdapter;
    private String phoneNum = "";

    private String categoryId = "0";//商品分类id 0：全部
    private int GoodsType = 1;//商品状态 1：出售中，0:已下架
    private int page = 0;//商品页码
    private int mPageSize = 20;//每页个数
    private GoodsGridViewAdapter mAdapter;
    protected ImageLoader imageLoader;

    private ShopInformationModel mShopInformationModel;
    private GetGoodsByClassIdModel mGetGoodsByClassIdModel;
    private List<GetGoodsByClassIdModel.BodyEntity> mBodyEntityList = new ArrayList<>();

    private int buyLayoutHeight;//购买布局的高度
    private int myScrollViewTop;//myScrollView与其父类布局的顶部距离
    private int buyLayoutTop;//购买布局与其父类布局的顶部距离
    private WindowManager mWindowManager;
    private int screenWidth; //手机屏幕宽度
    private ObservableScrollView scrollView;
    private List<AddRecordFullCutListModel.BodyEntity> mFullCutList;
    private List<GetCouponListModel.BodyEntity> mAllCouponList;
    private StorePreviewFullcutGridviewAdapter mFullcutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storepreview);
        ButterKnife.bind(this);

        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        screenWidth = mWindowManager.getDefaultDisplay().getWidth();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        hideTitleBar();
        initRefreshView(PullToRefreshBase.Mode.BOTH, pullToRefreshScrollView);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getmWidth(),
                getmWidth() * 500 / 1504);
        partStorePreviewHeadRl.setLayoutParams(params);

        imageLoader = ImageLoader.getInstance();
        mAdapter = new GoodsGridViewAdapter(mContext, getmWidth(), imageLoader, this);
        pullRefreshGrid.setAdapter(mAdapter);
        scrollView = pullToRefreshScrollView.getRefreshableView();
        scrollView.setScrollViewListener(this);
        storepreviewCouponGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(mContext, StorePreviewCouponListViewActivity.class));
            }
        });
    }

    /**
     * 窗口有焦点的时候，即所有的布局绘制完毕的时候，我们来获取购买布局的高度和myScrollView距离父类布局的顶部位置
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            buyLayoutHeight = storepreviewTabLl.getHeight();
            buyLayoutTop = storepreviewTabLl.getTop();

            myScrollViewTop = pullToRefreshScrollView.getTop();
        }
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        mShopInformationModel = BaiYeBaseApplication.mShopInformationModel;
        initShopInfoView();
    }

    private void initShopInfoView() {
        phoneNum = mShopInformationModel.getBody().getStoreTel();
        partStorePreviewShopname.setText(mShopInformationModel.getBody().getStoreName());
        partStorePreviewShopaddreTx.setText(mShopInformationModel.getBody().getStoreAddress());
        partStorePreviewShopsignatureTx.setText(mShopInformationModel.getBody().getDescription());
        setBitmapToImageView(partStorePreviewHeadbgImg,
                Urls.HEADIMG_BEFOR + mShopInformationModel.getBody().getStoreBanner(),
                R.mipmap.personal_top_bg);
        setBitmapToImageView(partStorePreviewHeadImg,
                Urls.HEADIMG_BEFOR + BaiYeBaseApplication.mLoginResultModel.getBody().getMemberAvatar(),
                R.mipmap.personal_head_default);
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        getDataManager().getGoodsClasses(StoreGoodsClassesModel.class, true);
        getDataManager().getFullcutList(AddRecordFullCutListModel.class, false);
        getDataManager().getCouponList("2", 0, 3, GetCouponListModel.class, false);
    }

    public void doHttp() {
        getDataManager().getGoodsByClassId(categoryId, GoodsType, page, mPageSize, GetGoodsByClassIdModel.class, false);
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

    @Override
    public void onResponse(Object response, String data) {
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
                if (mAdapter.getCount() < 5) {
                    storepreviewTopTabLl.setVisibility(View.GONE);
                }
                if (categoryId.equals("0") && mAdapter.getCount() == 0) {
                    mEmptyViewTx.setVisibility(View.VISIBLE);
                    mEmptyViewTx.setText("您的店铺暂无上架的商品");
                } else if (!categoryId.equals("0") && mAdapter.getCount() == 0) {
                    mEmptyViewTx.setVisibility(View.VISIBLE);
                    mEmptyViewTx.setText("该商品分类暂无上架的商品");
                }
            } else {
                showToast("商品获取失败");
            }
        } else if (response instanceof AddRecordFullCutListModel) {
            AddRecordFullCutListModel mAddRecordFullCutListModel = (AddRecordFullCutListModel) response;
            if (mAddRecordFullCutListModel.getResult().equals("1")) {
                mFullCutList = mAddRecordFullCutListModel.getBody();
                initFullCutView();
            } else {
                showToast("满减活动信息获取失败");
            }
        } else if (response instanceof GetCouponListModel) {
            GetCouponListModel mGetCouponListModel = (GetCouponListModel) response;
            mAllCouponList = mGetCouponListModel.getBody();
            LogUtils.e("mAllFullCutList.size():" + mAllCouponList.size());
            if (mAllCouponList != null && mAllCouponList.size() != 0) {
                mFullcutAdapter = new StorePreviewFullcutGridviewAdapter(mContext, mAllCouponList);
                storepreviewCouponGv.setAdapter(mFullcutAdapter);
            }
        }
    }

    private void initFullCutView() {
        String str = "全场 ";
        int num = 0;
        for (int i=0;i<mFullCutList.size();i++) {
            if (mFullCutList.get(i).getState()==2) {
                num += 1;
                if (i>0) {
                    str += ",";
                }
                str += "满"+mFullCutList.get(i).getPrice()+"减"+mFullCutList.get(i).getDiscount();
            }
        }

        if (num>0) {
            storepreviewFullcutLl.setVisibility(View.VISIBLE);
        } else {
            storepreviewFullcutLl.setVisibility(View.GONE);
        }
        storepreviewFullcutTx.setText(str);
    }

    private List<Fragment> fragments;

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
        topTabLayout.setupWithViewPager(infoViewpager);
        //设置TabLayout模式 -该使用Tab数量比较多的情况
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        topTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                infoViewpager.setCurrentItem(tabLayout.getSelectedTabPosition());
                if (storepreviewTopTabLl.getVisibility() != View.VISIBLE) {
                    LogUtils.e("tabLayout");
                    getListByPosition(tabLayout.getSelectedTabPosition());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        topTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                infoViewpager.setCurrentItem(topTabLayout.getSelectedTabPosition());
                if (storepreviewTopTabLl.getVisibility() == View.VISIBLE) {
                    LogUtils.e("topTabLayout");
                    getListByPosition(topTabLayout.getSelectedTabPosition());
                }
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
        page = 0;
        mAdapter.clearBody();
        getDataManager().getGoodsByClassId(categoryId, GoodsType, page, mPageSize, GetGoodsByClassIdModel.class, true);
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

    @OnClick({R.id.part_storeptrview_top_back_iv, R.id.part_storeptrview_top_sreach_ll
            , R.id.part_storeptrview_top_right_tv, R.id.storepreview_more_iv
            , R.id.part_store_preview_head_rl, R.id.top_storepreview_more_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.part_storeptrview_top_back_iv:
                finish();
                break;
            case R.id.part_storeptrview_top_sreach_ll:
                break;
            case R.id.part_storeptrview_top_right_tv:
//                if (!UiHelper.isTabletDevice(mContext)) {
//                    toPhone(mContext, phoneNum);
//                } else {
//                    showToast("您的设备不支持拨号");
//                }
                ShareDialog.Builder builder = new ShareDialog.Builder(mContext);
                builder.setTitle("分享");
                builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ShareDialog dailog = builder.show();
                dailog.setShareListener(new ShareDialog.ShareListener() {
                    @Override
                    public void onShare(int type) {
                        doShare(type);
                    }
                });
                break;
            case R.id.top_storepreview_more_iv:
            case R.id.storepreview_more_iv:
                storepreviewMoreIv.setImageResource(R.mipmap.goods_class_more_up);
                topStorepreviewMoreIv.setImageResource(R.mipmap.goods_class_more_up);
                showPopWindow(view);
                break;
            case R.id.part_store_preview_head_rl:
                if (BaiYeBaseApplication.mShopInformationModel == null) {
                    startActivity(new Intent(mContext, CreateShopActivity.class));
                } else {
                    startActivity(ShopInformationActivity.buildIntent(mContext, true));
                }
                break;
        }
    }

    public void doShare(int type) {
        if (BaiYeBaseApplication.mLoginResultModel.getBody().getStoreId() == 0) {
            showToast("你还没有创建店铺，不能进行分享");
            return;
        }
        if (BaiYeBaseApplication.mShopInformationModel == null) {
            YLBDialog.Builder builder = new YLBDialog.Builder(mContext);
            builder.setMessage("店铺信息获取失败，需要重新获取吗？");
            builder.setPositiveButton("重新获取", this);
            builder.setNegativeButton("取消", this);
            builder.show();
            return;
        }
        switch (type) {
            case 1://微信
                if (UiHelper.isAppInstalled(mContext, UiHelper.WEIXIN_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.WEIXIN);
                }
                break;
            case 2://朋友圈
                if (UiHelper.isAppInstalled(mContext, UiHelper.WEIXIN_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.WEIXIN_CIRCLE);
                }
                break;
            case 3://微博
                if (UiHelper.isAppInstalled(mContext, UiHelper.SINA_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.SINA);
                }
                break;
            case 4://qq
                if (UiHelper.isAppInstalled(mContext, UiHelper.QQ_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.QQ);
                }
                break;
            case 5://copy
                Tools.copy(BaiYeBaseApplication.mShopInformationModel.getBody().getStoreName() + "\n"
                        + BaiYeBaseApplication.mShopInformationModel.getBody().getStoreWebUrl(), mContext);
                break;
            default:
                break;

        }
    }

    private void shareHelper(SHARE_MEDIA Platform) {
        LogUtils.e("withText:" + BaiYeBaseApplication.mShopInformationModel.getBody().getDescription());
        UMImage image = new UMImage(mContext, BaiYeBaseApplication.mShopInformationModel.getBody().getFullStoreBanner());
        new ShareAction(this).setPlatform(Platform).setCallback(new MyUMShareListenner(mContext))
                .withText(BaiYeBaseApplication.mShopInformationModel.getBody().getDescription())
                .withTitle(BaiYeBaseApplication.mShopInformationModel.getBody().getStoreName())
                .withMedia(image)
                .withTargetUrl(BaiYeBaseApplication.mShopInformationModel.getBody().getStoreWebUrl())
                .share();
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
                topStorepreviewMoreIv.setImageResource(R.mipmap.goods_class_more);
            }
        });

        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);
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

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y >= buyLayoutTop) {
            storepreviewTopTabLl.setVisibility(View.VISIBLE);
        } else if (y <= buyLayoutTop + buyLayoutHeight) {
            storepreviewTopTabLl.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
        if (which == DialogInterface.BUTTON_POSITIVE) {
            getDataManager().getShopInformation(ShopInformationModel.class, true);
        }
    }
}
