package com.atman.jishang.ui.marketing.fullcut;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.AddFullCutNextAdapter;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.interfaces.LimitSizeTextWatcher;
import com.atman.jishang.net.model.AddFullCutNextModel;
import com.atman.jishang.net.model.AddFullCutResuiltModel;
import com.atman.jishang.net.model.NextFullCutModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.MyTools;
import com.atman.jishang.utils.save.Tools;
import com.atman.jishang.widget.dateselect.DatePicker;
import com.atman.jishang.widget.dateselect.DateSelectDialogUtil;
import com.atman.jishang.widget.dateselect.TimePicker;
import com.corelib.util.LogUtils;
import com.corelib.widget.MyCleanEditText;
import com.corelib.widget.MyListView;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 添加满减活动
 * 作者 tangbingliang
 * 时间 16/5/18 16:26
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AddFullCutActivity extends SimpleTitleBarActivity implements AdapterInterface {

    @Bind(R.id.addfullcut_ex)
    MyCleanEditText addfullcutEx;
    @Bind(R.id.addfullcut_starttime_tx)
    TextView addfullcutStarttimeTx;
    @Bind(R.id.addfullcut_endtime_tx)
    TextView addfullcutEndtimeTx;
    @Bind(R.id.addfullcut_describe_et)
    MyCleanEditText addfullcutDescribeEt;
    @Bind(R.id.fullcut_next_lv)
    MyListView fullcutNextLv;
    @Bind(R.id.fullcut_add_ll)
    LinearLayout fullcutAddLl;

    private Context mContext = AddFullCutActivity.this;
    private AddFullCutNextAdapter mAdapter;
    private List<AddFullCutNextModel> mNextList = new ArrayList<>();
    private View buttomView;
    private LinearLayout fullcutAddNextLl;
    private String selectDate;
    private String selectTime;

    private String name;
    private String startTime;
    private String endTime;
    private String remark;
    private List<AddFullCutNextModel> mList = new ArrayList<>();

    private int numDataOne = -1;
    private int numDataTwo = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfullcut);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle(R.string.fullcut_add_title);
        initListView();
        selectDate = MyTools.getLacalDate();
        selectTime = MyTools.getLacalTime();
        addfullcutStarttimeTx.setText(selectDate+" "+selectTime);
        addfullcutEndtimeTx.setText(selectDate+" "+selectTime);

        addfullcutEx.addTextChangedListener(
                new LimitSizeTextWatcher(mContext,addfullcutEx,49,"活动名称不能超过50个汉字"));
        addfullcutDescribeEt.addTextChangedListener(
                new LimitSizeTextWatcher(mContext,addfullcutDescribeEt,200,"活动说明不能超过200个汉字"));
    }

    private void initListView() {
        buttomView = LayoutInflater.from(mContext).inflate(R.layout.part_fullcut_next_add_view, null);
        fullcutAddNextLl = (LinearLayout) buttomView.findViewById(R.id.fullcut_add_next_ll);
        fullcutAddNextLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFullCutNextModel mAddFullCutNextModel = new AddFullCutNextModel("","");
                mAdapter.addList(mAddFullCutNextModel);
                if (mAdapter.getCount()>=10) {
                    fullcutAddNextLl.setVisibility(View.GONE);
                }
            }
        });
        mAdapter = new AddFullCutNextAdapter(mContext,this);
        fullcutNextLv.addFooterView(buttomView);
        fullcutNextLv.setAdapter(mAdapter);
        AddFullCutNextModel mAddFullCutNextModel = new AddFullCutNextModel("","");
        mAdapter.addList(mAddFullCutNextModel);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof AddFullCutResuiltModel) {
            AddFullCutResuiltModel mAddFullCutResuiltModel = (AddFullCutResuiltModel) response;
            if (mAddFullCutResuiltModel.getResult().equals("1")) {
                showToast("添加成功");
                finish();
            } else {
                showToast(mAddFullCutResuiltModel.getBody().getMessage());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.addfullcut_starttime_ll, R.id.addfullcut_endtime_ll, R.id.fullcut_add_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addfullcut_starttime_ll:
                DateSelectDialogUtil mStart = new DateSelectDialogUtil(mContext, view
                        ,startOneListener,startTwoListener, new DateSelectDialogUtil.OnNowListener(){
                    @Override
                    public void onNow() {
                        addfullcutStarttimeTx.setText(MyTools.getLacalDate("yyyy-MM-dd HH:mm"));
                    }
                });
                mStart.ShowDialog(numDataOne, addfullcutStarttimeTx.getText().toString());
                break;
            case R.id.addfullcut_endtime_ll:
                DateSelectDialogUtil mEnd = new DateSelectDialogUtil(mContext, view
                        ,endOneListener,endTwoListener, new DateSelectDialogUtil.OnNowListener(){
                    @Override
                    public void onNow() {
                        addfullcutEndtimeTx.setText(MyTools.getLacalDate("yyyy-MM-dd HH:mm"));
                    }
                });
                mEnd.ShowDialog(numDataTwo, addfullcutEndtimeTx.getText().toString());
                break;
            case R.id.fullcut_add_ll:
                if (initParms()) {
                    return;
                }
                getDataManager().addFullCut(startTime,endTime,name, remark, mList, AddFullCutResuiltModel.class,true);
                break;
        }
    }

    private boolean initParms() {
        name = addfullcutEx.getText().toString().trim();
        if (name.isEmpty()) {
            showToast("活动名称不能为空");
            return true;
        }
        startTime = MyTools.transformationToUnix(addfullcutStarttimeTx.getText().toString());
        endTime = MyTools.transformationToUnix(addfullcutEndtimeTx.getText().toString());
        if (Double.valueOf(endTime) <= Double.valueOf(startTime)) {
            showToast("结束时间要大于开始时间");
            return true;
        }
        if (Double.valueOf(endTime) -
                Double.valueOf(MyTools.transformationToUnix(MyTools.getLacalDate()+" "+MyTools.getLacalTime())) < 3600) {
            showToast("活动生效时间不能小于1小时");
            return true;
        }
        if (Double.valueOf(endTime) - Double.valueOf(startTime) <= 3600) {
            showToast("活动生效时间不能小于1小时");
            return true;
        }
        mList = mAdapter.getList();
        if (mList.size()<=0) {
            showToast("至少添加一个活动");
            return true;
        }
        for (int i=0;i<mList.size();i++) {
            if (mList.get(i).getPrice().isEmpty()) {
                showToast("请设置所有活动金额");
                return true;
            }
            if (Integer.parseInt(mList.get(i).getPrice())<=0) {
                showToast("活动金额必须大于0");
                return true;
            }
            if (mList.get(i).getDiscount().isEmpty()) {
                showToast("请设置所有活动金额");
                return true;
            }
            if (Integer.parseInt(mList.get(i).getDiscount())<=0) {
                showToast("活动金额必须大于0");
                return true;
            }
            if (Integer.parseInt(mList.get(i).getPrice())<Integer.parseInt(mList.get(i).getDiscount())) {
                showToast("优惠金额应不能大于消费金额");
                return true;
            }
        }
        remark = addfullcutDescribeEt.getText().toString().trim();
        return false;
    }

    //listeners
    DatePicker.OnChangeListener startOneListener = new DatePicker.OnChangeListener() {
        @Override
        public void onChange(int year, int month, int day, int day_of_week, int num) {
            selectDate = year + "-" + month + "-" + day;
            numDataOne = num;
            addfullcutStarttimeTx.setText(MyTools.transformationToStandard(selectDate+ " " +selectTime));
        }
    };
    TimePicker.OnChangeListener startTwoListener = new TimePicker.OnChangeListener() {
        @Override
        public void onChange(int hour, int minute) {
            selectTime = hour + ":" + ((minute < 10)?("0"+minute):minute);
            addfullcutStarttimeTx.setText(MyTools.transformationToStandard(selectDate+ " " +selectTime));
        }
    };

    //listeners
    DatePicker.OnChangeListener endOneListener = new DatePicker.OnChangeListener() {
        @Override
        public void onChange(int year, int month, int day, int day_of_week, int num) {
            selectDate = year + "-" + month + "-" + day;
            numDataTwo = num;
            addfullcutEndtimeTx.setText(MyTools.transformationToStandard(selectDate+ " " +selectTime));
        }
    };
    TimePicker.OnChangeListener endTwoListener = new TimePicker.OnChangeListener() {
        @Override
        public void onChange(int hour, int minute) {
            selectTime = hour + ":" + ((minute < 10)?("0"+minute):minute);
            addfullcutEndtimeTx.setText(MyTools.transformationToStandard(selectDate+ " " +selectTime));
        }
    };

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.item_addfullcut_next_delete_iv:
                mAdapter.deleteListById(position);
                if (mAdapter.getCount()<10) {
                    fullcutAddNextLl.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
