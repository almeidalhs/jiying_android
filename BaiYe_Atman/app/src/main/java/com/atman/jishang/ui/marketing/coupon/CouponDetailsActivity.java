package com.atman.jishang.ui.marketing.coupon;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.MyUMShareListenner;
import com.atman.jishang.net.model.DeleteCouponModel;
import com.atman.jishang.net.model.FinishCouponModel;
import com.atman.jishang.net.model.GetDetailsModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.MyTools;
import com.atman.jishang.utils.UiHelper;
import com.atman.jishang.utils.save.Tools;
import com.atman.jishang.widget.BottomDialog;
import com.atman.jishang.widget.ShareDialog;
import com.atman.jishang.widget.YLBDialog;
import com.corelib.util.LogUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 优惠券详情
 * 作者 tangbingliang
 * 时间 16/5/23 17:41
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class CouponDetailsActivity extends SimpleTitleBarActivity {

    @Bind(R.id.coupon_detail_state)
    TextView couponDetailState;
    @Bind(R.id.coupon_detail_denomination_tx)
    TextView couponDetailDenominationTx;
    @Bind(R.id.coupon_detail_achieve_tx)
    TextView couponDetailAchieveTx;
    @Bind(R.id.coupon_detail_surplus_tx)
    TextView couponDetailSurplusTx;
    @Bind(R.id.coupon_detail_time_tx)
    TextView couponDetailTimeTx;
    @Bind(R.id.coupon_detail_iv)
    ImageView couponDetailIv;
    @Bind(R.id.coupon_details_describe_tx)
    TextView couponDetailsDescribeTx;

    private Context mContext = CouponDetailsActivity.this;
    private GetDetailsModel mGetDetailsModel;

    private String mState;
    private int id;
    private final int toEdit = 10006;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupondetails);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context, int id, String state) {
        Intent intent = new Intent(context, CouponDetailsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("state", state);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mState = getIntent().getStringExtra("state");
        id = getIntent().getIntExtra("id", -1);
        if (id == -1) {
            return;
        }
        getDataManager().getCouponById(id, GetDetailsModel.class, true);
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle(R.string.coupon_details_title);
        getIvRightOk().setImageResource(R.mipmap.coupon_details_topright);
        showRightLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddrPopupWindow(v);
            }
        });
        getLlBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    String str = "";
    private void showAddrPopupWindow(View view) {
        String str2 = "编辑";
        if (mState.equals("1")) {
            str = "您确定要删除该未开始的优惠券吗？";
        } else if (mState.equals("2")) {
            str = "您确定要删除该进行中的优惠券吗？";
            str2 = "结束";
        } else if (mState.equals("3")) {
            str = "您确定要删除该已结束的优惠券吗？";
        }
        BottomDialog.Builder builder = new BottomDialog.Builder(mContext);
        builder.setTitle(Html.fromHtml("<font color=\"#8F8F8F\">操作</font>"));
        builder.setItems(new String[]{"分享", str2, "删除"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {//编辑
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
                            doShare(type);
                        }
                    });
                } else if (which == 1) {//编辑
                    if (mState.equals("2")) {
                        getDataManager().finishCouponById(id, FinishCouponModel.class, true);
                    } else {
                        isEdit = false;
                        startActivityForResult(EditCouponActivity.buildIntent(mContext, id, mState), toEdit);
                    }
                } else {//删除
                    YLBDialog.Builder builder = new YLBDialog.Builder(mContext);
                    builder.setMessage(str);
                    builder.setPositiveButton("删除", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            getDataManager().deleteCouponById(id, DeleteCouponModel.class, true);
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });
        builder.setNeutralButton("取消", null);
        builder.show();
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
        if (response instanceof GetDetailsModel) {
            mGetDetailsModel = (GetDetailsModel) response;
            if (mGetDetailsModel.getResult().equals("1")) {
                couponDetailDenominationTx.setText("" + (int)mGetDetailsModel.getBody().getCouponPrice());
                couponDetailAchieveTx.setText("满" + mGetDetailsModel.getBody().getCouponLimit() + "可用");
                couponDetailSurplusTx.setText("剩余" + (mGetDetailsModel.getBody().getCouponStorage() - mGetDetailsModel.getBody().getCouponUsage()) + "张");
                if (mGetDetailsModel !=null && mGetDetailsModel.getBody().getCouponDesc()!=null
                        && !mGetDetailsModel.getBody().getCouponDesc().isEmpty()) {
                    couponDetailsDescribeTx.setText(mGetDetailsModel.getBody().getCouponDesc());
                }
                couponDetailTimeTx.setText(MyTools.convertTimeThree(mGetDetailsModel.getBody().getCouponStartDate())
                        + "-" + MyTools.convertTimeThree(mGetDetailsModel.getBody().getCouponEndDate()));
                mState = String.valueOf(mGetDetailsModel.getBody().getCouponState());
                if (mState.equals("1")) {
                    couponDetailState.setText("未开始");
                } else if (mState.equals("2")) {
                    couponDetailState.setText("进行中");
                } else if (mState.equals("3")) {
                    couponDetailState.setText("已结束");
                    couponDetailIv.setVisibility(View.VISIBLE);
                }
            } else {
                showToast("优惠券详情获取失败");
            }
        } else if (response instanceof DeleteCouponModel) {
            DeleteCouponModel mDeleteCouponModel = (DeleteCouponModel) response;
            if (mDeleteCouponModel.getResult().equals("1")) {
                finish();
            }
            showToast(mDeleteCouponModel.getBody().getMessage());
        } else if (response instanceof FinishCouponModel) {
            FinishCouponModel mFinishCouponModel = (FinishCouponModel) response;
            if (mFinishCouponModel.getResult().equals("1") && mState.equals("2")) {
                getDataManager().getCouponById(id, GetDetailsModel.class, true);
            }
            showToast(mFinishCouponModel.getBody().getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.coupon_detail_share_iv, R.id.coupon_detail_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.coupon_detail_share_iv:
                if (mState.equals("3")) {
                    return;
                }
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
                        doShare(type);
                    }
                });
                break;
            case R.id.coupon_detail_bt:
                startActivity(ReceiveCouponRecordsActivity.buildIntent(mContext,id,mGetDetailsModel.getBody().getCouponStorage()));
                break;
        }
    }

    public void doShare(int type) {
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
                Tools.copy(mGetDetailsModel.getBody().getCouponTitle() + "\n"
                        + mGetDetailsModel.getBody().getShareUrl(), mContext);
                break;
            default:
                break;

        }
    }

    private void shareHelper(SHARE_MEDIA Platform) {
        String str = mGetDetailsModel.getBody().getCouponDesc();
        LogUtils.e("withText:" + str);
        if (str.isEmpty()) {
            str = "优惠券";
        }
        new ShareAction(CouponDetailsActivity.this).setPlatform(Platform).setCallback(new MyUMShareListenner(mContext))
                .withText(mGetDetailsModel.getBody().getCouponDesc())
                .withTitle(mGetDetailsModel.getBody().getCouponTitle())
//                .withMedia(image)
                .withTargetUrl(mGetDetailsModel.getBody().getShareUrl())
                .share();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 返回键
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == toEdit) {
            isEdit = true;
        }

    }

    private void back() {
        if (isEdit) {
            Intent mIntent = new Intent();
            setResult(RESULT_OK,mIntent);
        }
        finish();
    }
}
