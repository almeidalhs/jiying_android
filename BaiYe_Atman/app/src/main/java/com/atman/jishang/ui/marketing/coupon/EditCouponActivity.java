package com.atman.jishang.ui.marketing.coupon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.LimitSizeTextWatcher;
import com.atman.jishang.net.model.AddCouponModel;
import com.atman.jishang.net.model.FinishCouponModel;
import com.atman.jishang.net.model.GetDetailsModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.MyTools;
import com.atman.jishang.widget.dateselect.DatePicker;
import com.atman.jishang.widget.dateselect.DateSelectDialogUtil;
import com.atman.jishang.widget.dateselect.TimePicker;
import com.corelib.util.LogUtils;
import com.corelib.widget.MyCleanEditText;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/23 13:06
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class EditCouponActivity extends SimpleTitleBarActivity {

    @Bind(R.id.addcoupon_denomination_ex)
    MyCleanEditText addcouponDenominationEx;
    @Bind(R.id.addcoupon_number_ex)
    MyCleanEditText addcouponNumberEx;
    @Bind(R.id.addcoupon_limit_ex)
    MyCleanEditText addcouponLimitEx;
    @Bind(R.id.addcoupon_limitnumber_ex)
    MyCleanEditText addcouponLimitnumberEx;
    @Bind(R.id.addcoupon_starttime_tx)
    TextView addcouponStarttimeTx;
    @Bind(R.id.addcoupon_endtime_tx)
    TextView addcouponEndtimeTx;
    @Bind(R.id.addcoupon_describe_et)
    MyCleanEditText addcouponDescribeEt;
    @Bind(R.id.coupon_add_tx)
    TextView couponAddTx;

    private Context mContext = EditCouponActivity.this;
    private String selectDate;
    private String selectTime;

    private String denomination;
    private String number;
    private String limit;
    private String limitnumber = "1";
    private String startTime;
    private String endTime;
    private String remark;
    private String mState;
    private int id;

    private String str = "";
    private String resultstr = "";
    private int numDataOne = -1;
    private int numDataTwo = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcoupon);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context, int id, String state) {
        Intent intent = new Intent(context, EditCouponActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("state", state);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle(R.string.edit_coupon_title);

        mState = getIntent().getStringExtra("state");
        id = getIntent().getIntExtra("id", -1);

        if (mState.equals("1")) {
            str = "您确定要删除该未开始的优惠券吗？";
            resultstr = "优惠券编辑成功";
            couponAddTx.setText("完成编辑");
        } else if (mState.equals("2")) {
            str = "您确定要删除该进行中的优惠券吗？";
            couponAddTx.setText("结束");
            resultstr = "结束优惠券成功";
        } else if (mState.equals("3")) {
            str = "您确定要删除该已结束的优惠券吗？";
            resultstr = "编辑并创建优惠券成功";
            couponAddTx.setText("编辑并创建优惠券");
        }

        selectDate = MyTools.getLacalDate();
        selectTime = MyTools.getLacalTime();
        addcouponStarttimeTx.setText(selectDate + " " + selectTime);
        addcouponEndtimeTx.setText(selectDate + " " + selectTime);

        addcouponDescribeEt.addTextChangedListener(new LimitSizeTextWatcher(mContext,
                addcouponDescribeEt, 50, getResources().getString(R.string.add_coupon_explain_hint)));
        addcouponDenominationEx.addTextChangedListener(new LimitSizeTextWatcher(mContext,
                addcouponDenominationEx, 7, "输入的金额太大"));
        addcouponNumberEx.addTextChangedListener(new LimitSizeTextWatcher(mContext,
                addcouponNumberEx, 8, "输入的数量太大"));
        addcouponLimitEx.addTextChangedListener(new LimitSizeTextWatcher(mContext,
                addcouponLimitEx, 7, "输入的金额太大"));
        addcouponLimitnumberEx.addTextChangedListener(new LimitSizeTextWatcher(mContext,
                addcouponLimitnumberEx, 9, "输入的数值太大"));
        addcouponLimitnumberEx.setText(limitnumber);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        if (id == -1) {
            return;
        }
        getDataManager().getCouponById(id, GetDetailsModel.class, true);
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof AddCouponModel) {
            AddCouponModel mAddCouponModel = (AddCouponModel) response;
            if (mAddCouponModel.getResult().equals("1")) {
                showToast(resultstr);
                Intent mIntent = new Intent();
                setResult(RESULT_OK,mIntent);
                finish();
            } else {
                showToast(mAddCouponModel.getBody().getMessage());
            }
        } else if (response instanceof GetDetailsModel) {
            GetDetailsModel mGetDetailsModel = (GetDetailsModel) response;
            if (mGetDetailsModel.getResult().equals("1")) {
                DecimalFormat df = new DecimalFormat("##0");
                addcouponDenominationEx.setText("" + df.format(mGetDetailsModel.getBody().getCouponPrice()));
                addcouponNumberEx.setText("" + mGetDetailsModel.getBody().getCouponStorage());
                addcouponLimitEx.setText("" + df.format(mGetDetailsModel.getBody().getCouponLimit()));
                addcouponLimitnumberEx.setText("" + mGetDetailsModel.getBody().getUserReceiveLimit());
                addcouponDescribeEt.setText(mGetDetailsModel.getBody().getCouponDesc());
                addcouponStarttimeTx.setText(MyTools.convertTimeTwo(mGetDetailsModel.getBody().getCouponStartDate()));
                addcouponEndtimeTx.setText(MyTools.convertTimeTwo(mGetDetailsModel.getBody().getCouponEndDate()));
                numDataOne = MyTools.getGapCount(BaiYeBaseApplication.getApp().dateList.get(0).getYear()
                        +"-"+BaiYeBaseApplication.getApp().dateList.get(0).getMonth()
                        +"-"+BaiYeBaseApplication.getApp().dateList.get(0).getDay()
                        , MyTools.convertTimeTwo(mGetDetailsModel.getBody().getCouponStartDate()));
                numDataTwo = MyTools.getGapCount(BaiYeBaseApplication.getApp().dateList.get(0).getYear()
                                +"-"+BaiYeBaseApplication.getApp().dateList.get(0).getMonth()
                                +"-"+BaiYeBaseApplication.getApp().dateList.get(0).getDay()
                        , MyTools.convertTimeTwo(mGetDetailsModel.getBody().getCouponEndDate()));
            } else {
                showToast("优惠券详情获取失败");
            }
        } else if (response instanceof FinishCouponModel) {
            FinishCouponModel mFinishCouponModel = (FinishCouponModel) response;
            showToast(mFinishCouponModel.getBody().getMessage());
            if (mFinishCouponModel.getResult().equals("1") && mState.equals("2")) {
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.addcoupon_starttime_ll, R.id.addcoupon_endtime_ll, R.id.coupon_add_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addcoupon_starttime_ll:
                DateSelectDialogUtil mStart = new DateSelectDialogUtil(mContext, view
                        , startOneListener, startTwoListener, new DateSelectDialogUtil.OnNowListener(){
                    @Override
                    public void onNow() {
                        addcouponStarttimeTx.setText(MyTools.getLacalDate("yyyy-MM-dd HH:mm"));
                    }
                });
                mStart.ShowDialog(numDataOne, addcouponStarttimeTx.getText().toString());
                break;
            case R.id.addcoupon_endtime_ll:
                DateSelectDialogUtil mEnd = new DateSelectDialogUtil(mContext, view
                        , endOneListener, endTwoListener, new DateSelectDialogUtil.OnNowListener(){
                    @Override
                    public void onNow() {
                        addcouponEndtimeTx.setText(MyTools.getLacalDate("yyyy-MM-dd HH:mm"));
                    }
                });
                mEnd.ShowDialog(numDataTwo, addcouponEndtimeTx.getText().toString());
                break;
            case R.id.coupon_add_ll:
                if (initParms()) {
                    return;
                }
                if (mState.equals("3")) {//已结束 创建
                    getDataManager().addCoupon(denomination, number, limit, limitnumber, startTime, endTime, remark, AddCouponModel.class, true);
                } else if (mState.equals("2")) {//进行中 结束
                    getDataManager().finishCouponById(id, FinishCouponModel.class, true);
                } else if (mState.equals("1")) {//未开始 编辑
                    getDataManager().editCoupon(id, denomination, number, limit, limitnumber, startTime, endTime, remark, AddCouponModel.class, true);
                }
                break;
        }
    }

    private boolean initParms() {
        denomination = addcouponDenominationEx.getText().toString().trim();
        if (denomination.isEmpty()) {
            showToast("请输入券面金额");
            return true;
        } else {
            if (Integer.parseInt(denomination)<=0) {
                showToast("券面金额需要大于0");
                return true;
            }
        }

        number = addcouponNumberEx.getText().toString().trim();
        if (number.isEmpty()) {
            showToast("请设置可领取数量");
            return true;
        } else {
            if (Integer.parseInt(number)<=0) {
                showToast("请设置可领取数量");
                return true;
            }
        }

        limit = addcouponLimitEx.getText().toString().trim();
        if (limit.isEmpty()) {
            showToast("请设置订单金额");
            return true;
        } else {
            if (Integer.parseInt(limit)<0) {
                showToast("请设置订单金额");
                return true;
            }
            if (Integer.parseInt(limit) < Integer.parseInt(denomination) ) {
                showToast("券面金额不能高于订单金额");
                return true;
            }
        }

        limitnumber = addcouponLimitnumberEx.getText().toString().trim();
        if (limitnumber.isEmpty()) {
            showToast("限领数量必须大于0");
            return true;
        } else {
            if (Integer.parseInt(limitnumber)<1) {
                showToast("限领数量必须大于0");
                return true;
            }
        }
        LogUtils.e("addfullcutStarttimeTx.getText().toString():"+addcouponStarttimeTx.getText().toString());
        startTime = MyTools.transformationToUnix(addcouponStarttimeTx.getText().toString());
        endTime = MyTools.transformationToUnix(addcouponEndtimeTx.getText().toString());
        if (Double.valueOf(endTime) <= Double.valueOf(startTime)) {
            showToast("结束时间必须晚于开始时间");
            return true;
        }
        if (Double.valueOf(endTime) -
                Double.valueOf(MyTools.transformationToUnix(MyTools.getLacalDate()+" "+MyTools.getLacalTime())) <= 3600) {
            showToast("活动生效时间不能小于1小时");
            return true;
        }
        if (Double.valueOf(endTime) - Double.valueOf(startTime) <= 3600) {
            showToast("活动生效时间不能小于1小时");
            return true;
        }
        LogUtils.e("startTime:"+startTime);
        remark = addcouponDescribeEt.getText().toString().trim();
        return false;
    }

    //listeners
    DatePicker.OnChangeListener startOneListener = new DatePicker.OnChangeListener() {
        @Override
        public void onChange(int year, int month, int day, int day_of_week, int num) {
            selectDate = year + "-" + month + "-" + day;
            numDataOne = num;
            addcouponStarttimeTx.setText(MyTools.transformationToStandard(selectDate + " " + addcouponStarttimeTx.getText().toString().substring(11,16)));
        }
    };
    TimePicker.OnChangeListener startTwoListener = new TimePicker.OnChangeListener() {
        @Override
        public void onChange(int hour, int minute) {
            selectTime = hour + ":" + ((minute < 10) ? ("0" + minute) : minute);
            addcouponStarttimeTx.setText(MyTools.transformationToStandard(addcouponStarttimeTx.getText().toString().substring(0,11) + " " + selectTime));
        }
    };

    //listeners
    DatePicker.OnChangeListener endOneListener = new DatePicker.OnChangeListener() {
        @Override
        public void onChange(int year, int month, int day, int day_of_week, int num) {
            selectDate = year + "-" + month + "-" + day;
            numDataTwo = num;
            addcouponEndtimeTx.setText(MyTools.transformationToStandard(selectDate + " " + addcouponEndtimeTx.getText().toString().substring(11,16)));
        }
    };
    TimePicker.OnChangeListener endTwoListener = new TimePicker.OnChangeListener() {
        @Override
        public void onChange(int hour, int minute) {
            selectTime = hour + ":" + ((minute < 10) ? ("0" + minute) : minute);
            addcouponEndtimeTx.setText(MyTools.transformationToStandard(addcouponEndtimeTx.getText().toString().substring(0,11) + " " + selectTime));
        }
    };
}
