package com.atman.jishang.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.DragAdapter;
import com.atman.jishang.interfaces.MyUMShareListenner;
import com.atman.jishang.net.model.HomeAdModel;
import com.atman.jishang.net.model.HomeGridViewDataModel;
import com.atman.jishang.net.model.HomeListPostModel;
import com.atman.jishang.net.model.ShopInformationModel;
import com.atman.jishang.net.model.UpdateListOrderModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.BaiYeBaseFragment;
import com.atman.jishang.ui.home.goods.ScanCodeActivity;
import com.atman.jishang.ui.home.orders.OrderDetailActivity;
import com.atman.jishang.utils.UiHelper;
import com.atman.jishang.utils.save.Tools;
import com.atman.jishang.widget.DragGrid;
import com.atman.jishang.widget.ShareDialog;
import com.atman.jishang.widget.YLBDialog;
import com.corelib.util.LogUtils;
import com.corelib.util.YLBConversionUtils;
import com.corelib.widget.AutoScrollViewPager;
import com.corelib.widget.AutoScrollViewPagerAdapter;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 首页
 * 作者 tangbingliang
 * 时间 16/4/19 16:05
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class HomeFragment extends BaiYeBaseFragment implements
        DialogInterface.OnClickListener, AutoScrollViewPagerAdapter.TopOnBack {

    @Bind(R.id.home_title)
    TextView homeTitle;
    @Bind(R.id.home_nav_right_ll)
    LinearLayout homeNavRightLl;
    @Bind(R.id.autoScrollViewpager)
    AutoScrollViewPager autoScrollViewpager;
    @Bind(R.id.viewgroup_index_dots)
    LinearLayout viewgroupIndexDots;
    @Bind(R.id.home_top_rl)
    RelativeLayout homeTopRl;
    @Bind(R.id.userGridView)
    DragGrid userGridView;

    private HomeGridViewDataModel mHomeGridViewDataModel;
    private List<HomeAdModel.BodyEntity> AdList = new ArrayList<>();
    private List<HomeGridViewDataModel.BodyEntity> dataSourceList = new ArrayList<>();
    private List<HomeGridViewDataModel.BodyEntity> dataAlreadyList = new ArrayList<>();
    private List<HomeGridViewDataModel.BodyEntity> dataOtherList = new ArrayList<>();
    private HomeGridViewDataModel.BodyEntity dataMore = new HomeGridViewDataModel.BodyEntity();
    private List<HomeListPostModel> homeListPostModel = new ArrayList<HomeListPostModel>();
    private HomeAdModel mHomeAdModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        homeTitle.setText(getActivity().getResources().getString(R.string.tab_home));
        LinearLayout.LayoutParams params_top = new LinearLayout.LayoutParams(getmWidth()
                , getmWidth() * 283 / 644);
        homeTopRl.setLayoutParams(params_top);

    }

    @Override
    public void onResume() {
        super.onResume();
        getDataManager().getHomeGridView(HomeGridViewDataModel.class, true);
        getDataManager().getHomeAd(HomeAdModel.class, false);
        if (BaiYeBaseApplication.mLoginResultModel.getBody().getStoreId() != 0) {
            getDataManager().getShopInformation(ShopInformationModel.class, false);
        }
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof HomeGridViewDataModel) {
            mHomeGridViewDataModel = (HomeGridViewDataModel) response;
            if (mHomeGridViewDataModel.getResult().equals("1")) {
                initGridView();
            } else {
                showToast("功能列表获取失败");
            }
        } else if (response instanceof HomeAdModel) {
            mHomeAdModel = (HomeAdModel) response;
            if (mHomeAdModel.getResult().equals("1")) {
                AdList = mHomeAdModel.getBody();
                initTopAD();
            } else {
                showToast("广告获取失败");
            }
        } else if (response instanceof UpdateListOrderModel) {
            UpdateListOrderModel mUpdateListOrderModel = (UpdateListOrderModel) response;
            if (mUpdateListOrderModel.getResult().equals("1")) {
                showToast("更新成功");
            } else {
                showToast("更新失败");
            }
        } else if (response instanceof ShopInformationModel) {
            ShopInformationModel mShopInformationModel = (ShopInformationModel) response;
            if (!mShopInformationModel.getResult().equals("1")) {
                showToast(mShopInformationModel.getBody().getMessage());
            } else {
                BaiYeBaseApplication.mShopInformationModel = mShopInformationModel;
            }
        }
    }

    private void initTopAD() {
        if (AdList == null) {
            return;
        }
        AutoScrollViewPagerAdapter adapter = new AutoScrollViewPagerAdapter(getBaseAppCompatActivity(), this);
        for (int i = 0; i < AdList.size(); i++) {
            adapter.addUrls(AdList.get(i).getAdPic());
        }
        autoScrollViewpager.setAdapter(adapter);
        autoScrollViewpager.setPageMargin(0);
        autoScrollViewpager.setInterval(5000);
        autoScrollViewpager.startAutoScroll();
        // 设置图片小圆点
        viewgroupIndexDots.removeAllViews(); // 防止在使用下拉刷新的时候，重复加载小圆点
        viewgroupIndexDots.setBackgroundResource(R.drawable.point_bg);
        final ImageView[] imageViewDots = new ImageView[AdList.size()];
        for (int i = 0; i < AdList.size(); i++) {
            ImageView dotsImg = new ImageView(getActivity());
            imageViewDots[i] = dotsImg;
            if (i == 0) {
                imageViewDots[i]
                        .setBackgroundResource(R.mipmap.dote_pressed);
            } else {
                imageViewDots[i]
                        .setBackgroundResource(R.mipmap.dote);
            }

            LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            margin.rightMargin = (i == AdList.size() - 1 ? 14 : 5);
            margin.leftMargin = (i == 0 ? 14 : 5);
            margin.topMargin = 5;
            margin.bottomMargin = 5;
            RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            rllp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            rllp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            rllp.bottomMargin = YLBConversionUtils.dp2px(getActivity(), 5);
            viewgroupIndexDots.setLayoutParams(rllp);
            viewgroupIndexDots.addView(dotsImg, margin);
        }
        autoScrollViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int selectItems) {
                selectItems = selectItems % AdList.size();
                if (AdList.size() > 0) {
                    for (int i = 0; i < imageViewDots.length; i++) {
                        if (i == selectItems) {
                            imageViewDots[i]
                                    .setBackgroundResource(R.mipmap.dote_pressed);
                        } else {
                            imageViewDots[i]
                                    .setBackgroundResource(R.mipmap.dote);
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onBack(int num) {
        new UiHelper().toSecondLevelByTypeAD(getActivity(), mHomeAdModel.getBody().get(num));
    }

    private void initGridView() {
        dataAlreadyList.clear();
        dataOtherList.clear();

        for (int i = 0; i < mHomeGridViewDataModel.getBody().size(); i++) {
            HomeGridViewDataModel.BodyEntity temp = mHomeGridViewDataModel.getBody().get(i);
            if (temp.getUcState() == 1) {
                dataAlreadyList.add(temp);
            } else if (temp.getUcState() == 0) {
                dataOtherList.add(temp);
            } else {
                dataMore = temp;
            }
        }

        dataAlreadyList.add(dataMore);

        final DragAdapter adapter = new DragAdapter(getActivity(),dataAlreadyList);
        userGridView.setAdapter(adapter);
        userGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new UiHelper().toSecondLevelByType(getActivity(), adapter.getItem(position));
            }
        });
        userGridView.setOnDragGridViewListener(new DragGrid.onDragGridViewListener() {
            @Override
            public void dragfinish() {
                dataSourceList = adapter.getList();
                for (int i = 0; i < dataSourceList.size(); i++) {
                    HomeGridViewDataModel.BodyEntity dataSource = dataSourceList.get(i);
                    HomeListPostModel temp = new HomeListPostModel(dataSource.getConsoleId()
                            , i, dataSource.getUcConsoleSort(), dataSource.getUcState());
                    homeListPostModel.add(temp);
                }
                String str = new Gson().toJson(homeListPostModel);
                getDataManager().updateListOrder(str, UpdateListOrderModel.class, true);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

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

    @OnClick({R.id.home_nav_left_ll, R.id.home_nav_right_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_nav_left_ll:
                new IntentIntegrator(getActivity()).setCaptureActivity(ScanCodeActivity.class).initiateScan();
                break;
            case R.id.home_nav_right_ll:

                ShareDialog.Builder builder = new ShareDialog.Builder(getActivity());
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
        }
    }

    public void doShare(int type) {
        if (BaiYeBaseApplication.mLoginResultModel.getBody().getStoreId() == 0) {
            showToast("你还没有创建店铺，不能进行分享");
            return;
        }
        if (BaiYeBaseApplication.mShopInformationModel == null) {
            YLBDialog.Builder builder = new YLBDialog.Builder(getActivity());
            builder.setMessage("店铺信息获取失败，需要重新获取吗？");
            builder.setPositiveButton("重新获取", this);
            builder.setNegativeButton("取消", this);
            builder.show();
            return;
        }
        switch (type) {
            case 1://微信
                if (UiHelper.isAppInstalled(getActivity(), UiHelper.WEIXIN_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.WEIXIN);
                }
                break;
            case 2://朋友圈
                if (UiHelper.isAppInstalled(getActivity(), UiHelper.WEIXIN_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.WEIXIN_CIRCLE);
                }
                break;
            case 3://微博
                if (UiHelper.isAppInstalled(getActivity(), UiHelper.SINA_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.SINA);
                }
                break;
            case 4://qq
                if (UiHelper.isAppInstalled(getActivity(), UiHelper.QQ_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.QQ);
                }
                break;
            case 5://copy
                Tools.copy(BaiYeBaseApplication.mShopInformationModel.getBody().getStoreName() + "\n"
                        + BaiYeBaseApplication.mShopInformationModel.getBody().getStoreWebUrl(), getActivity());
                break;
            default:
                break;

        }
    }

    private void shareHelper(SHARE_MEDIA Platform) {
        LogUtils.e("withText:" + BaiYeBaseApplication.mShopInformationModel.getBody().getDescription());
        UMImage image = new UMImage(getActivity(), BaiYeBaseApplication.mShopInformationModel.getBody().getFullStoreBanner());
        new ShareAction(getActivity()).setPlatform(Platform).setCallback(new MyUMShareListenner(getActivity()))
                .withText(BaiYeBaseApplication.mShopInformationModel.getBody().getDescription())
                .withTitle(BaiYeBaseApplication.mShopInformationModel.getBody().getStoreName())
                .withMedia(image)
                .withTargetUrl(BaiYeBaseApplication.mShopInformationModel.getBody().getStoreWebUrl())
                .share();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
        if (which == DialogInterface.BUTTON_POSITIVE) {
            getDataManager().getShopInformation(ShopInformationModel.class, true);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                LogUtils.e(">>>>result.getContents():"+result.getContents());
                String code = "wushangatman";
                String code2 = "atman_nongshang_order_";
                if (result.getContents().startsWith(code)) {
                    startActivity(BindingEquipmentActivity.buildIntent(getActivity(), result.getContents().replace(code,"")));
                } else if (result.getContents().startsWith(code2)) {
                    startActivity(OrderDetailActivity.buildIntent(getActivity(), result.getContents().replace(code2,"")));
                } else {
                    showToast("非即商二维码");
                }
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
