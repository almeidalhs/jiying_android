package com.atman.jishang.ui.marketing.coupon;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.adapter.CouponAllAdapter;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.interfaces.MyUMShareListenner;
import com.atman.jishang.net.model.DeleteCouponModel;
import com.atman.jishang.net.model.FinishCouponModel;
import com.atman.jishang.net.model.GetCouponListModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.UiHelper;
import com.atman.jishang.utils.save.Tools;
import com.atman.jishang.widget.ShareDialog;
import com.atman.jishang.widget.YLBDialog;
import com.corelib.util.LogUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 优惠券
 * 作者 tangbingliang
 * 时间 16/5/17 13:27
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class CouponActivity extends SimpleTitleBarActivity implements CouponAllAdapter.IonSlidingViewClickListener {

    @Bind(R.id.tab_coupon_notstart)
    RadioButton tabCouponNotstart;
    @Bind(R.id.tab_coupon_now)
    RadioButton tabCouponNow;
    @Bind(R.id.tab_coupon_finish)
    RadioButton tabCouponFinish;
    @Bind(R.id.tab_coupon)
    RadioGroup tabCoupon;
    @Bind(R.id.pull_refresh_recycler)
    PullToRefreshRecyclerView pullRefreshRecycler;
    @Bind(R.id.coupon_empty_iv)
    ImageView couponEmptyIv;

    private Context mContext = CouponActivity.this;
    private int mPage = 0;
    private int mCount = 10;
    private String mState = "2";//满减活动的状态（1：未开始，2：进行中，3：已结束）

    private CouponAllAdapter mAdapter;
    private List<GetCouponListModel.BodyEntity> mAllCouponList;
    private RecyclerView mRecyclerView;
    private int mDeleteId = -1;
    private final int toDeitals = 10005;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle(R.string.coupon_title);
        initRefreshView(PullToRefreshBase.Mode.BOTH, pullRefreshRecycler);
        initBottomBar();
        initListView();
    }

    private void initListView() {

        mAdapter = new CouponAllAdapter(mContext, getmWidth(), mState, this);

        mRecyclerView = pullRefreshRecycler.getRefreshableView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));//这里用线性显示 类似于listview
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initBottomBar() {
        tabCoupon.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mAdapter.clearData();
                switch (checkedId) {
                    case R.id.tab_coupon_notstart:
                        tabCouponNotstart.performClick();
                        mState = "1";
                        mAdapter.setState(mState);
                        doHttp(true);
                        break;
                    case R.id.tab_coupon_now:
                        tabCouponNow.performClick();
                        mState = "2";
                        mAdapter.setState(mState);
                        doHttp(true);
                        break;
                    case R.id.tab_coupon_finish:
                        tabCouponFinish.performClick();
                        mState = "3";
                        mAdapter.setState(mState);
                        doHttp(true);
                        break;
                }
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
        mPage = 0;
        doHttp(true);
    }

    private void doHttp(boolean b) {
        getDataManager().getCouponList(mState, mPage, mCount, GetCouponListModel.class, b);
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof GetCouponListModel) {
            GetCouponListModel mGetCouponListModel = (GetCouponListModel) response;
            mAllCouponList = mGetCouponListModel.getBody();
            LogUtils.e("mAllFullCutList.size():" + mAllCouponList.size());
            if (mAllCouponList == null || mAllCouponList.size() == 0) {
                onLoad(PullToRefreshBase.Mode.PULL_FROM_START, pullRefreshRecycler);
                if (mAdapter!=null && mAdapter.getItemCount() == 0) {
                    couponEmptyIv.setVisibility(View.VISIBLE);
                    pullRefreshRecycler.setVisibility(View.GONE);
                } else {
                    showToast("没有更多");
                }
            } else {
                couponEmptyIv.setVisibility(View.GONE);
                pullRefreshRecycler.setVisibility(View.VISIBLE);
                onLoad(PullToRefreshBase.Mode.BOTH, pullRefreshRecycler);
            }
            mAdapter.addData(mAllCouponList);
        } else if (response instanceof DeleteCouponModel) {
            DeleteCouponModel mDeleteCouponModel = (DeleteCouponModel) response;
            if (mDeleteCouponModel.getResult().equals("1")) {
                mAdapter.removeData(mDeleteId);
                showEmptyView();
            }
            showToast(mDeleteCouponModel.getBody().getMessage());
        } else if (response instanceof FinishCouponModel) {
            FinishCouponModel mFinishCouponModel = (FinishCouponModel) response;
            if (mFinishCouponModel.getResult().equals("1") && mState.equals("2")) {
                mAdapter.removeData(mDeleteId);
                showEmptyView();
            }
            showToast(mFinishCouponModel.getBody().getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.coupon_empty_iv, R.id.coupon_add_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.coupon_empty_iv:
                mPage = 0;
                doHttp(true);
                break;
            case R.id.coupon_add_ll:
                startActivity(new Intent(mContext, AddCouponActivity.class));
                break;
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mPage = 0;
        mAdapter.clearData();
        doHttp(false);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        mPage += 1;
        doHttp(false);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        onLoad(PullToRefreshBase.Mode.BOTH, pullRefreshRecycler);
        showEmptyView();
    }

    private void showEmptyView() {
        if (mAdapter.getItemCount()==0) {
            couponEmptyIv.setVisibility(View.VISIBLE);
            pullRefreshRecycler.setVisibility(View.GONE);
        } else {
            couponEmptyIv.setVisibility(View.GONE);
            pullRefreshRecycler.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(View view, final int position) {
        startActivityForResult(CouponDetailsActivity.buildIntent(mContext, mAdapter.getItemById(position).getId(), mState), toDeitals);
    }

    @Override
    public void onDeleteBtnCilck(View view, final int position) {
        switch (view.getId()) {
            case R.id.tv_edit:
                mDeleteId = position;
                if (mState.equals("2")) {//结束
                    getDataManager().finishCouponById(mAdapter.getItemById(position).getId(), FinishCouponModel.class, true);
                } else {//编辑
                    startActivity(EditCouponActivity.buildIntent(mContext, mAdapter.getItemById(position).getId(), mState));
                }
                break;
            case R.id.tv_delete:
                String str = "";
                if (mState.equals("1")) {
                    str = "您确定要删除该未开始的优惠券吗？";
                } else if (mState.equals("2")) {
                    str = "您确定要删除该进行中的优惠券吗？";
                } else if (mState.equals("3")) {
                    str = "您确定要删除该已结束的优惠券吗？";
                }
                YLBDialog.Builder builder = new YLBDialog.Builder(mContext);
                builder.setMessage(str);
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mDeleteId = position;
                        getDataManager().deleteCouponById(mAdapter.getItemById(position).getId(), DeleteCouponModel.class, true);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.item_coupon_share_iv:
                ShareDialog.Builder builder_share = new ShareDialog.Builder(mContext);
                builder_share.setTitle("分享");
                builder_share.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ShareDialog dailog = builder_share.show();
                dailog.setShareListener(new ShareDialog.ShareListener() {
                    @Override
                    public void onShare(int type) {
                        doShare(type,position);
                    }
                });
                break;
        }
    }

    public void doShare(int type, int num) {
        switch (type) {
            case 1://微信
                if (UiHelper.isAppInstalled(mContext, UiHelper.WEIXIN_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.WEIXIN, num);
                }
                break;
            case 2://朋友圈
                if (UiHelper.isAppInstalled(mContext, UiHelper.WEIXIN_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.WEIXIN_CIRCLE, num);
                }
                break;
            case 3://微博
                if (UiHelper.isAppInstalled(mContext, UiHelper.SINA_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.SINA, num);
                }
                break;
            case 4://qq
                if (UiHelper.isAppInstalled(mContext, UiHelper.QQ_PACKAGE)) {
                    shareHelper(SHARE_MEDIA.QQ, num);
                }
                break;
            case 5://copy
                Tools.copy(mAdapter.getItemById(num).getCouponTitle() + "\n"
                        + mAdapter.getItemById(num).getShareUrl(), mContext);
                break;
            default:
                break;

        }
    }

    private void shareHelper(SHARE_MEDIA Platform, int m) {
        String contentStr = mAdapter.getItemById(m).getCouponDesc();
        if (contentStr == null || contentStr.isEmpty()) {
            contentStr = BaiYeBaseApplication.mShopInformationModel.getBody().getStoreName()+ ":"+"优惠券";
        }
        String titleStr = mAdapter.getItemById(m).getCouponTitle();
        if (titleStr==null || titleStr.isEmpty()) {
            titleStr = BaiYeBaseApplication.mShopInformationModel.getBody().getStoreName()+ ":"+"优惠券";
        }

        new ShareAction(CouponActivity.this).setPlatform(Platform).setCallback(new MyUMShareListenner(mContext))
                .withText(contentStr)
                .withTitle(titleStr)
//                .withMedia(image)
                .withTargetUrl(mAdapter.getItemById(m).getShareUrl())
                .share();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == toDeitals) {
            String result = data.getStringExtra("params");
            if (mAdapter != null) {
                mAdapter.clearData();
            }
            mPage = 0;
            doHttp(true);
        }
    }
}
