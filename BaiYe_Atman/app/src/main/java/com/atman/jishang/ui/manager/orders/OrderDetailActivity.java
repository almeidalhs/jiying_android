package com.atman.jishang.ui.manager.orders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.model.OrderDetailModel;
import com.atman.jishang.net.model.WeiXinPrePayModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.MyTools;
import com.atman.jishang.widget.pay.PayDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 订单详情
 * 作者 tangbingliang
 * 时间 16/6/21 17:15
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class OrderDetailActivity extends SimpleTitleBarActivity {

    @Bind(R.id.orderdetail_id_tx)
    TextView orderdetailIdTx;
    @Bind(R.id.orderdetail_money_tx)
    TextView orderdetailMoneyTx;
    @Bind(R.id.orderdetail_goods_tx)
    TextView orderdetailGoodsTx;
    @Bind(R.id.orderdetail_remarks_tx)
    TextView orderdetailRemarksTx;
    @Bind(R.id.orderdetail_time_tx)
    TextView orderdetailTimeTx;
    @Bind(R.id.orderdetail_state_tx)
    TextView orderdetailStateTx;
    @Bind(R.id.orderdetail_pay_ll)
    LinearLayout orderdetailPayLl;

    private Context mContext = OrderDetailActivity.this;
    private String orderId;
    private OrderDetailModel mOrderDetailModel;
    private PayDialog mPayDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context, String orderId) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("orderId", orderId);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle("订单详情");
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        orderId = getIntent().getStringExtra("orderId");
        getDataManager().getOrderDetail(orderId, OrderDetailModel.class, true);
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof OrderDetailModel) {
            mOrderDetailModel = (OrderDetailModel) response;
            if (mOrderDetailModel.getResult().equals("1")) {
                updateUI();
            } else {
                showToast("订单详情获取失败");
            }
        } else if (response instanceof WeiXinPrePayModel) {
            WeiXinPrePayModel mWeiXinPrePayModel = (WeiXinPrePayModel) response;
            if (mWeiXinPrePayModel.getResult().equals("1")) {
                mPayDialog.weixinPay(mWeiXinPrePayModel);
            }  else {
                showToast(mWeiXinPrePayModel.getBody().getMessage());
            }
        }
    }

    private void updateUI() {
        orderdetailIdTx.setText(mOrderDetailModel.getBody().getOut_trade_no());
        orderdetailMoneyTx.setText(mOrderDetailModel.getBody().getTotal_fee()+"");
        String str="";
        for (int i=0;i<mOrderDetailModel.getBody().getOrderGoodses().size();i++) {
            if (i!=0) {
                str += ",";
            }
            str += mOrderDetailModel.getBody().getOrderGoodses().get(i).getGoodsName();
        }
        orderdetailGoodsTx.setText(str);
        orderdetailRemarksTx.setText(mOrderDetailModel.getBody().getRemark());
        orderdetailTimeTx.setText(MyTools.convertTimeTwo(mOrderDetailModel.getBody().getCreateTime()/1000));
        orderdetailStateTx.setText(mOrderDetailModel.getBody().getOrderStateName());
        if (mOrderDetailModel.getBody().getPayTime()>0) {
            orderdetailPayLl.setVisibility(View.GONE);
        } else {
            orderdetailPayLl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.orderdetail_pay_bt)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.orderdetail_pay_bt:
                mPayDialog = new PayDialog(mContext, mOrderDetailModel.getBody().getId()+"");
                mPayDialog.show();
                break;
        }
    }

    public static String changeStateStr(int stateId){
        String str = "";
        switch (stateId) {
            case 1:
                str = "已提交";
                break;
            case 2:
                str = "备货中";
                break;
            case 3:
                str = "已发货";
                break;
            case 4:
                str = "已备货";
                break;
            case 6:
                str = "已收货";
                break;
            case 7:
                str = "定金入账";
                break;
            case 8:
                str = "已成功";
                break;
            case 9:
                str = "失败";
                break;
        }
        return str;
    }
}
