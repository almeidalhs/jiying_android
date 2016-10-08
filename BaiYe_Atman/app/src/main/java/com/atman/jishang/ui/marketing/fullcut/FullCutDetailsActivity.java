package com.atman.jishang.ui.marketing.fullcut;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.AddFullCutNextAdapter;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.interfaces.LimitSizeTextWatcher;
import com.atman.jishang.net.model.AddFullCutNextModel;
import com.atman.jishang.net.model.AddFullCutResuiltModel;
import com.atman.jishang.net.model.DeleteFullCutByIdModel;
import com.atman.jishang.net.model.FullCutByIdModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.MyTools;
import com.atman.jishang.widget.YLBDialog;
import com.atman.jishang.widget.dateselect.DatePicker;
import com.atman.jishang.widget.dateselect.DateSelectDialogUtil;
import com.atman.jishang.widget.dateselect.TimePicker;
import com.corelib.util.LogUtils;
import com.corelib.widget.MyCleanEditText;
import com.corelib.widget.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 满减活动详情
 * 作者 tangbingliang
 * 时间 16/5/18 16:26
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class FullCutDetailsActivity extends SimpleTitleBarActivity implements AdapterInterface {

    @Bind(R.id.editfullcut_ex)
    MyCleanEditText editfullcutEx;
    @Bind(R.id.editfullcut_starttime_tx)
    TextView editfullcutStarttimeTx;
    @Bind(R.id.editfullcut_endtime_tx)
    TextView editfullcutEndtimeTx;
    @Bind(R.id.fullcut_next_lv)
    MyListView fullcutNextLv;
    @Bind(R.id.editfullcut_describe_et)
    MyCleanEditText editfullcutDescribeEt;
    @Bind(R.id.fullcut_edit_tx)
    TextView fullcutEditTx;

    private Context mContext = FullCutDetailsActivity.this;
    private AddFullCutNextAdapter mAdapter;
    private List<AddFullCutNextModel> mNextList = new ArrayList<>();
    private View buttomView;
    private LinearLayout fullcutAddNextLl;
    private String selectDate;
    private String selectTime;
    private ImageView mDeleteIV;
    private LinearLayout mDeleteLL;

    private String name;
    private String startTime;
    private String endTime;
    private String remark;
    private List<AddFullCutNextModel> mList = new ArrayList<>();
    private int id = -1;
    private String mState;
    private String str = "";
    private String resultstr = "";

    private int numDataOne = -1;
    private int numDataTwo = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullcutdetails);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context, int id, String state) {
        Intent intent = new Intent(context, FullCutDetailsActivity.class);
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
        setToolbarTitle("满减活动详情");
        mDeleteIV = getIvRightOk();
        mDeleteIV.setImageResource(R.mipmap.fullcut_delete);
        mDeleteLL = showRightLl();
        mDeleteLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YLBDialog.Builder builder = new YLBDialog.Builder(mContext);
                builder.setMessage(str);
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        getDataManager().deleteFullCutById(id, DeleteFullCutByIdModel.class, true);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                if (id == -1) {
                    showToast("满减活动详情获取失败，不能提交修改");
                    return;
                }
                builder.show();
            }
        });

        mState = getIntent().getStringExtra("state");
        id = getIntent().getIntExtra("id", -1);
        if (mState.equals("1")) {
            str = "您确定要删除该未开始的活动吗？";
            resultstr = "活动编辑成功";
            fullcutEditTx.setText("完成编辑");
        } else if (mState.equals("2")) {
            str = "您确定要删除该进行中的活动吗？";
            fullcutEditTx.setText("结束活动");
            resultstr = "结束活动成功";
            mDeleteLL.setVisibility(View.GONE);
        } else if (mState.equals("3")) {
            str = "您确定要删除该已结束的活动吗？";
            resultstr = "编辑并创建活动成功";
            fullcutEditTx.setText("编辑并创建活动");
        }

        initListView();
        selectDate = MyTools.getLacalDate();
        selectTime = MyTools.getLacalTime();
        editfullcutStarttimeTx.setText(selectDate + " " + selectTime);
        editfullcutEndtimeTx.setText(selectDate + " " + selectTime);

        editfullcutEx.addTextChangedListener(
                new LimitSizeTextWatcher(mContext,editfullcutEx,49,"活动名称不能超过50个汉字"));
        editfullcutDescribeEt.addTextChangedListener(
                new LimitSizeTextWatcher(mContext,editfullcutDescribeEt,255,"活动说明不能超过256个汉字"));
    }

    private void initListView() {
        buttomView = LayoutInflater.from(mContext).inflate(R.layout.part_fullcut_next_add_view, null);
        fullcutAddNextLl = (LinearLayout) buttomView.findViewById(R.id.fullcut_add_next_ll);
        fullcutAddNextLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFullCutNextModel mAddFullCutNextModel = new AddFullCutNextModel("", "");
                mAdapter.addList(mAddFullCutNextModel);
                if (mAdapter.getCount()>=10) {
                    fullcutAddNextLl.setVisibility(View.GONE);
//                    showToast("最多只能添加十个活动");
                    return;
                }
            }
        });
        mAdapter = new AddFullCutNextAdapter(mContext, mState, this);
        fullcutNextLv.addFooterView(buttomView);
        fullcutNextLv.setAdapter(mAdapter);
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
        getDataManager().getFullCutById(id, FullCutByIdModel.class, true);
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof AddFullCutResuiltModel) {
            AddFullCutResuiltModel mAddFullCutResuiltModel = (AddFullCutResuiltModel) response;
            if (mAddFullCutResuiltModel.getResult().equals("1")) {
                showToast(resultstr);
                finish();
            } else {
                showToast(mAddFullCutResuiltModel.getBody().getMessage());
            }
        } else if (response instanceof FullCutByIdModel) {
            FullCutByIdModel mFullCutByIdModel = (FullCutByIdModel) response;
            if (mFullCutByIdModel.getResult().equals("1")) {

                for (int i = 0; i < mFullCutByIdModel.getBody().getMansongRuleBeanList().size(); i++) {
                    AddFullCutNextModel mAddFullCutNextModel =
                            new AddFullCutNextModel(mFullCutByIdModel.getBody().getMansongRuleBeanList().get(i).getPrice(),
                                    mFullCutByIdModel.getBody().getMansongRuleBeanList().get(i).getDiscount());
                    mAdapter.addList(mAddFullCutNextModel);
                    if (mAdapter.getCount()>=10) {
                        fullcutAddNextLl.setVisibility(View.GONE);
                    }
                }
                editfullcutEx.setText(mFullCutByIdModel.getBody().getMansongName());
                editfullcutStarttimeTx.setText(MyTools.convertTimeTwo(mFullCutByIdModel.getBody().getStartTime()));
                editfullcutEndtimeTx.setText(MyTools.convertTimeTwo(mFullCutByIdModel.getBody().getEndTime()));
                editfullcutDescribeEt.setText(mFullCutByIdModel.getBody().getRemark());
                numDataOne = MyTools.getGapCount(BaiYeBaseApplication.getApp().dateList.get(0).getYear()
                                +"-"+BaiYeBaseApplication.getApp().dateList.get(0).getMonth()
                                +"-"+BaiYeBaseApplication.getApp().dateList.get(0).getDay()
                        , MyTools.convertTimeTwo(mFullCutByIdModel.getBody().getStartTime()));
                numDataTwo = MyTools.getGapCount(BaiYeBaseApplication.getApp().dateList.get(0).getYear()
                                +"-"+BaiYeBaseApplication.getApp().dateList.get(0).getMonth()
                                +"-"+BaiYeBaseApplication.getApp().dateList.get(0).getDay()
                        , MyTools.convertTimeTwo(mFullCutByIdModel.getBody().getEndTime()));
            } else {
                showToast("满减活动详情获取失败");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.editfullcut_starttime_ll, R.id.editfullcut_endtime_ll, R.id.fullcut_edit_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editfullcut_starttime_ll:
                DateSelectDialogUtil mStart = new DateSelectDialogUtil(mContext, view
                        , startOneListener, startTwoListener, new DateSelectDialogUtil.OnNowListener(){
                    @Override
                    public void onNow() {
                        editfullcutStarttimeTx.setText(MyTools.getLacalDate("yyyy-MM-dd HH:mm"));
                    }
                });
                mStart.ShowDialog(numDataOne, editfullcutStarttimeTx.getText().toString());
                break;
            case R.id.editfullcut_endtime_ll:
                DateSelectDialogUtil mEnd = new DateSelectDialogUtil(mContext, view
                        , endOneListener, endTwoListener, new DateSelectDialogUtil.OnNowListener(){
                    @Override
                    public void onNow() {
                        editfullcutStarttimeTx.setText(MyTools.getLacalDate("yyyy-MM-dd HH:mm"));
                    }
                });
                mEnd.ShowDialog(numDataTwo, editfullcutStarttimeTx.getText().toString());
                break;
            case R.id.fullcut_edit_ll:
                if (initParms()) {
                    return;
                }
                if (mState.equals("1")) {
                    getDataManager().editFullCut(id, startTime, endTime, name, remark,mList, AddFullCutResuiltModel.class, true);
                } else if (mState.equals("2")) {
                    getDataManager().finishFullCutById(id, AddFullCutResuiltModel.class, true);
                } else if (mState.equals("3")) {
                    getDataManager().addFullCut(startTime, endTime, name, remark, mList, AddFullCutResuiltModel.class, true);
                }
                break;
        }
    }

    private boolean initParms() {
        if (id == -1) {
            showToast("满减活动详情获取失败，不能提交修改");
            return true;
        }
        name = editfullcutEx.getText().toString().trim();
        if (name.isEmpty()) {
            showToast("活动名称不能为空");
            return true;
        }

        startTime = MyTools.transformationToUnix(editfullcutStarttimeTx.getText().toString());
        endTime = MyTools.transformationToUnix(editfullcutEndtimeTx.getText().toString());
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
        remark = editfullcutDescribeEt.getText().toString().trim();
        return false;
    }

    //listeners
    DatePicker.OnChangeListener startOneListener = new DatePicker.OnChangeListener() {
        @Override
        public void onChange(int year, int month, int day, int day_of_week, int num) {
            selectDate = year + "-" + month + "-" + day;
            numDataOne = num;
            editfullcutStarttimeTx.setText(MyTools.transformationToStandard(selectDate + " " + editfullcutStarttimeTx.getText().toString().substring(11,16)));
        }
    };
    TimePicker.OnChangeListener startTwoListener = new TimePicker.OnChangeListener() {
        @Override
        public void onChange(int hour, int minute) {
            selectTime = hour + ":" + ((minute < 10) ? ("0" + minute) : minute);
            editfullcutStarttimeTx.setText(MyTools.transformationToStandard(editfullcutStarttimeTx.getText().toString().substring(0,11) + " " + selectTime));
        }
    };

    //listeners
    DatePicker.OnChangeListener endOneListener = new DatePicker.OnChangeListener() {
        @Override
        public void onChange(int year, int month, int day, int day_of_week, int num) {
            selectDate = year + "-" + month + "-" + day;
            numDataTwo = num;
            editfullcutEndtimeTx.setText(MyTools.transformationToStandard(selectDate + " " + editfullcutEndtimeTx.getText().toString().substring(11,16)));
        }
    };
    TimePicker.OnChangeListener endTwoListener = new TimePicker.OnChangeListener() {
        @Override
        public void onChange(int hour, int minute) {
            selectTime = hour + ":" + ((minute < 10) ? ("0" + minute) : minute);
            editfullcutEndtimeTx.setText(MyTools.transformationToStandard(editfullcutEndtimeTx.getText().toString().substring(0,11) + " " + selectTime));
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
