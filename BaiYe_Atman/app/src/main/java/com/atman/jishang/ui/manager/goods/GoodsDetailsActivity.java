package com.atman.jishang.ui.manager.goods;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.MyUMShareListenner;
import com.atman.jishang.net.model.DeleteGoodsModel;
import com.atman.jishang.net.model.DeleteGoodsResuiltModel;
import com.atman.jishang.net.model.GoodsDetailInfoModel;
import com.atman.jishang.net.model.UnShelveOrShelveModel;
import com.atman.jishang.net.model.UnShelveOrShelveResultModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.UiHelper;
import com.atman.jishang.utils.save.Tools;
import com.atman.jishang.widget.ShareDialog;
import com.atman.jishang.widget.YLBDialog;
import com.corelib.util.YLBConversionUtils;
import com.corelib.widget.AutoScrollViewPager;
import com.corelib.widget.AutoScrollViewPagerAdapter;
import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/26 10:27
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GoodsDetailsActivity extends SimpleTitleBarActivity implements
        DialogInterface.OnClickListener, AutoScrollViewPagerAdapter.TopOnBack {

    @Bind(R.id.autoScrollViewpager)
    AutoScrollViewPager autoScrollViewpager;
    @Bind(R.id.viewgroup_index_dots)
    LinearLayout viewgroupIndexDots;
    @Bind(R.id.home_top_rl)
    RelativeLayout homeTopRl;
    @Bind(R.id.goods_detail_name)
    TextView goodsDetailName;
    @Bind(R.id.goods_detail_price)
    TextView goodsDetailPrice;
    @Bind(R.id.goods_detail_collection)
    TextView goodsDetailCollection;
    @Bind(R.id.goods_detail_explain)
    TextView goodsDetailExplain;
    @Bind(R.id.goods_detail_specName)
    TextView goodsDetailSpecName;
    @Bind(R.id.goodsdetail_unshelve_tx)
    TextView goodsdetailUnshelveTx;
    @Bind(R.id.bottom_ll)
    LinearLayout bottomLl;

    private ImageView ivRightOk;

    private Context mContext = GoodsDetailsActivity.this;
    private final static String mTitle = "title";
    private final static String mGoodsId = "goodsid";
    private final static String mGoodsShow = "goodsShow";
    private int goodsShow;
    private GoodsDetailInfoModel mGoodsDetailInfoModel;
    private List<String> AdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetails);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context, String title, long id, int goodsShow) {
        Intent intent = new Intent(context, GoodsDetailsActivity.class);
        intent.putExtra(mTitle, title);
        intent.putExtra(mGoodsId, id);
        intent.putExtra(mGoodsShow, goodsShow);
        return intent;
    }

    public static Intent buildIntent(Context context, String title, int id, int goodsShow, boolean isEdit) {
        Intent intent = new Intent(context, GoodsDetailsActivity.class);
        intent.putExtra(mTitle, title);
        intent.putExtra(mGoodsId, id);
        intent.putExtra(mGoodsShow, goodsShow);
        intent.putExtra("isEdit", isEdit);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataManager().getGoodsByClassId(getIntent().getLongExtra(mGoodsId, 0), GoodsDetailInfoModel.class, true);
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
//        setSwipeBackEnable(false);
        goodsShow = getIntent().getIntExtra(mGoodsShow, 1);
        if (goodsShow == 1) {
            goodsdetailUnshelveTx.setText("下架");
        } else {
            goodsdetailUnshelveTx.setText("上架");
        }
        setToolbarTitle(getResources().getString(R.string.goodsdetail_title));
//        setToolbarTitle(getIntent().getStringExtra(mTitle));
        LinearLayout.LayoutParams params_top = new LinearLayout.LayoutParams(getmWidth()
                , getmWidth() * 300 / 568);
        LinearLayout.LayoutParams params_top_right = new LinearLayout.LayoutParams(BaiYeBaseApplication.getApp().dp2px(20)
                , LinearLayout.LayoutParams.WRAP_CONTENT);
        homeTopRl.setLayoutParams(params_top);
        ivRightOk = getIvRightOk();
//        ivRightOk.setLayoutParams(params_top_right);
        ivRightOk.setImageResource(R.mipmap.share_ic);
        ivRightOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        if (getIntent().getBooleanExtra("isEdit",false)) {
            bottomLl.setVisibility(View.GONE);
        }
    }

    public void doShare(int type) {
        if (mGoodsDetailInfoModel == null) {
            YLBDialog.Builder builder = new YLBDialog.Builder(mContext);
            builder.setMessage("商品信息获取失败，需要重新获取吗？");
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
                Tools.copy(mGoodsDetailInfoModel.getBody().getGoodsName() + "\n" +
                        mGoodsDetailInfoModel.getBody().getGoodsWebUrl(), mContext);
                break;
            default:
                break;

        }
    }

    private void shareHelper(SHARE_MEDIA Platform) {
        UMImage image = new UMImage(mContext, mGoodsDetailInfoModel.getBody().getFullGoodsImage());
        String strTitle = mGoodsDetailInfoModel.getBody().getGoodsName();
        if (strTitle == null || strTitle.isEmpty()) {
            strTitle = BaiYeBaseApplication.mShopInformationModel.getBody().getStoreName()+ ":" + mGoodsDetailInfoModel.getBody().getGoodsName();
        }
        String strContent = mGoodsDetailInfoModel.getBody().getGoodsDescription();
        if (strContent == null || strContent.isEmpty()) {
            strContent = BaiYeBaseApplication.mShopInformationModel.getBody().getStoreName()+ ":" + mGoodsDetailInfoModel.getBody().getGoodsName();
        }
        new ShareAction(GoodsDetailsActivity.this).setPlatform(Platform).setCallback(new MyUMShareListenner(mContext))
                .withText(strContent)
                .withTitle(strTitle)
                .withMedia(image)
                .withTargetUrl(mGoodsDetailInfoModel.getBody().getGoodsWebUrl())
                .share();
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();

    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof GoodsDetailInfoModel) {
            mGoodsDetailInfoModel = (GoodsDetailInfoModel) response;
            if (mGoodsDetailInfoModel.getResult().equals("1")) {
                initTopAD();
                updateUI();
            } else {
                showToast(mGoodsDetailInfoModel.getBody().getMessage());
            }
        } else if (response instanceof DeleteGoodsResuiltModel) {
            DeleteGoodsResuiltModel mDeleteGoodsResuiltModel = (DeleteGoodsResuiltModel) response;
            if (mDeleteGoodsResuiltModel.getResult().equals("1")) {
                showToast("删除成功");
                finish();
            } else {
                showToast("删除失败");
            }
        } else if (response instanceof UnShelveOrShelveResultModel) {
            UnShelveOrShelveResultModel mUnShelveOrShelveResultModel = (UnShelveOrShelveResultModel) response;
            String str = goodsdetailUnshelveTx.getText().toString();
            if (mUnShelveOrShelveResultModel.getResult().equals("1")) {
                showToast(str + "成功");
                if (str.equals("下架")) {
                    goodsShow = 0;
                    goodsdetailUnshelveTx.setText("上架");
                } else {
                    goodsShow = 1;
                    goodsdetailUnshelveTx.setText("下架");
                }
            } else {
                showToast(str + "失败");
            }
        }
    }

    private void updateUI() {
        goodsDetailName.setText(mGoodsDetailInfoModel.getBody().getGoodsName());
        DecimalFormat df = new DecimalFormat("##0.00");
        goodsDetailPrice.setText("¥ " + df.format(mGoodsDetailInfoModel.getBody().getPrice()));
        if (mGoodsDetailInfoModel.getBody().getGoodsDescription() == null
                || mGoodsDetailInfoModel.getBody().getGoodsDescription().isEmpty()) {
            goodsDetailExplain.setText("暂未添加商品说明");
        } else {
            goodsDetailExplain.setText(mGoodsDetailInfoModel.getBody().getGoodsDescription());
        }
        if (mGoodsDetailInfoModel.getBody().getGoodsSpec() == null
                || mGoodsDetailInfoModel.getBody().getGoodsSpec().isEmpty()) {
            goodsDetailSpecName.setText("暂未添加商品规格");
        } else {
            goodsDetailSpecName.setText(mGoodsDetailInfoModel.getBody().getGoodsSpec());
        }

    }

    private void initTopAD() {
        AdList.clear();
        AdList.addAll(mGoodsDetailInfoModel.getBody().getGoodsImageMoreList());
        if (AdList == null) {
            return;
        }
        AutoScrollViewPagerAdapter adapter = new AutoScrollViewPagerAdapter(GoodsDetailsActivity.this, AdList.size(), this);
        for (int i = 0; i < AdList.size(); i++) {
            adapter.addUrls(AdList.get(i));
        }
        autoScrollViewpager.setAdapter(adapter);
        autoScrollViewpager.setPageMargin(0);
        autoScrollViewpager.setInterval(2000);
//        autoScrollViewpager.startAutoScroll();
        // 设置图片小圆点
        viewgroupIndexDots.removeAllViews(); // 防止在使用下拉刷新的时候，重复加载小圆点
        viewgroupIndexDots.setBackgroundResource(R.drawable.point_bg);
        final ImageView[] imageViewDots = new ImageView[AdList.size()];
        for (int i = 0; i < AdList.size(); i++) {
            ImageView dotsImg = new ImageView(mContext);
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
            rllp.bottomMargin = YLBConversionUtils.dp2px(mContext, 5);
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBack(int num) {

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
        if (which == DialogInterface.BUTTON_POSITIVE) {
            getDataManager().getGoodsByClassId(getIntent().getIntExtra(mGoodsId, 0), GoodsDetailInfoModel.class, true);
        }
    }

    @OnClick({R.id.goodsdetail_edit_ll, R.id.goodsdetail_unshelve_ll, R.id.goodsdetail_delete_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goodsdetail_edit_ll:
                startActivity(new Intent(EditGoodsActivity.buildIntent(mContext, mGoodsDetailInfoModel.getBody().getId())));
                break;
            case R.id.goodsdetail_unshelve_ll:
                UnShelveOrShelveModel mUnShelveOrShelveModel =
                        new UnShelveOrShelveModel(mGoodsDetailInfoModel.getBody().getId(),
                                mGoodsDetailInfoModel.getBody().getStoreId());
                List<UnShelveOrShelveModel> list = new ArrayList<>();
                list.add(mUnShelveOrShelveModel);
                if (goodsShow == 1) {
                    getDataManager().batchUnShelve(new Gson().toJson(list),
                            UnShelveOrShelveResultModel.class, true);
                } else {
                    getDataManager().batchShelve(new Gson().toJson(list),
                            UnShelveOrShelveResultModel.class, true);
                }
                break;
            case R.id.goodsdetail_delete_ll:
                YLBDialog.Builder builder = new YLBDialog.Builder(GoodsDetailsActivity.this);
                builder.setMessage("您确定要删除该商品吗？");
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        List<DeleteGoodsModel> list = new ArrayList<DeleteGoodsModel>();
                        DeleteGoodsModel mDeleteGoodsModel = new DeleteGoodsModel(
                                mGoodsDetailInfoModel.getBody().getId(),
                                mGoodsDetailInfoModel.getBody().getStoreId());
                        list.add(mDeleteGoodsModel);
                        getDataManager().deleteGoods(new Gson().toJson(list),
                                DeleteGoodsResuiltModel.class, true);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
