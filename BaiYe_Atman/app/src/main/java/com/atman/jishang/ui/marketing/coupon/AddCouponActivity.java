package com.atman.jishang.ui.marketing.coupon;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.LimitSizeTextWatcher;
import com.atman.jishang.net.model.AddCouponModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.MyTools;
import com.atman.jishang.widget.dateselect.DatePicker;
import com.atman.jishang.widget.dateselect.DateSelectDialogUtil;
import com.atman.jishang.widget.dateselect.TimePicker;
import com.corelib.util.LogUtils;
import com.corelib.widget.MyCleanEditText;

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
public class AddCouponActivity extends SimpleTitleBarActivity {

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

    private Context mContext = AddCouponActivity.this;
    private String selectDate;
    private String selectTime;

    private String denomination;
    private String number;
    private String limit;
    private String limitnumber = "1";
    private String startTime;
    private String endTime;
    private String remark;

    private int numDataOne = -1;
    private int numDataTwo = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcoupon);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle(R.string.add_coupon_title);
        selectDate = MyTools.getLacalDate();
        selectTime = MyTools.getLacalTime();
        addcouponStarttimeTx.setText(selectDate+" "+selectTime);
        addcouponEndtimeTx.setText(selectDate+" "+selectTime);

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
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof AddCouponModel) {
            AddCouponModel mAddCouponModel = (AddCouponModel) response;
            if (mAddCouponModel.getResult().equals("1")) {
                showToast("优惠券添加成功");
                finish();
            } else {
                showToast(mAddCouponModel.getBody().getMessage());
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
                        ,startOneListener,startTwoListener, new DateSelectDialogUtil.OnNowListener(){
                    @Override
                    public void onNow() {
                        addcouponStarttimeTx.setText(MyTools.getLacalDate("yyyy-MM-dd HH:mm"));
                    }
                });
                mStart.ShowDialog(numDataOne, addcouponStarttimeTx.getText().toString());
                break;
            case R.id.addcoupon_endtime_ll:
                DateSelectDialogUtil mEnd = new DateSelectDialogUtil(mContext, view
                        ,endOneListener,endTwoListener, new DateSelectDialogUtil.OnNowListener(){
                    @Override
                    public void onNow() {
                        addcouponEndtimeTx.setText(MyTools.getLacalDate("yyyy-MM-dd HH:mm"));
                    }
                });
                mEnd.ShowDialog(numDataTwo,addcouponEndtimeTx.getText().toString());
                break;
            case R.id.coupon_add_ll:
                if (initParms()) {
                    return;
                }
                getDataManager().addCoupon(denomination,number,limit,limitnumber,startTime,endTime,remark,AddCouponModel.class,true);
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
        remark = addcouponDescribeEt.getText().toString().trim();
        return false;
    }

    //listeners
    DatePicker.OnChangeListener startOneListener = new DatePicker.OnChangeListener() {
        @Override
        public void onChange(int year, int month, int day, int day_of_week, int num) {
            selectDate = year + "-" + month + "-" + day;
            numDataOne = num;
            addcouponStarttimeTx.setText(MyTools.transformationToStandard(selectDate+ " " +selectTime));
        }
    };
    TimePicker.OnChangeListener startTwoListener = new TimePicker.OnChangeListener() {
        @Override
        public void onChange(int hour, int minute) {
            selectTime = hour + ":" + ((minute < 10)?("0"+minute):minute);
            addcouponStarttimeTx.setText(MyTools.transformationToStandard(selectDate+ " " +selectTime));
        }
    };

    //listeners
    DatePicker.OnChangeListener endOneListener = new DatePicker.OnChangeListener() {
        @Override
        public void onChange(int year, int month, int day, int day_of_week, int num) {
            selectDate = year + "-" + month + "-" + day;
            numDataTwo = num;
            addcouponEndtimeTx.setText(MyTools.transformationToStandard(selectDate+ " " +selectTime));
        }
    };
    TimePicker.OnChangeListener endTwoListener = new TimePicker.OnChangeListener() {
        @Override
        public void onChange(int hour, int minute) {
            selectTime = hour + ":" + ((minute < 10)?("0"+minute):minute);
            addcouponEndtimeTx.setText(MyTools.transformationToStandard(selectDate+ " " +selectTime));
        }
    };
}
