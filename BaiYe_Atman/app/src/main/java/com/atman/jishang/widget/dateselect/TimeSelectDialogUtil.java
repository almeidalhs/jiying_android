package com.atman.jishang.widget.dateselect;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.atman.jishang.R;
import com.corelib.util.LogUtils;

import java.util.Calendar;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/19 14:09
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class TimeSelectDialogUtil {

    private Calendar calendar;
    private TimePicker dp_test;
    private TimePicker tp_test;
    private TextView tv_ok,tv_cancel;	//确定、取消button
    private PopupWindow pw;
    private String selectDate,selectTime;
    //选择时间与当前时间，用于判断用户选择的是否是以前的时间
    private int currentHour,currentMinute,currentDay,selectHour,selectMinute,selectDay;
    //整体布局
    private View view;
    private Context mContext;
    private TimePicker.OnChangeListener tp_onchanghelistenerOne, tp_onchanghelistenerTwo;

    public TimeSelectDialogUtil(Context mContext, View view,TimePicker.OnChangeListener tp_onchanghelistenerOne,
                                TimePicker.OnChangeListener tp_onchanghelistenerTwo){
        this.mContext = mContext;
        this.tp_onchanghelistenerOne = tp_onchanghelistenerOne;
        this.tp_onchanghelistenerTwo = tp_onchanghelistenerTwo;
        this.view = view;
        calendar = Calendar.getInstance();
    }

    public void ShowDialog (String dataNum, String time) {
        LogUtils.e("dataNum:"+dataNum);
        LogUtils.e("time:"+time);
        View view = View.inflate(mContext, R.layout.dialog_select_time, null);
        LogUtils.e("view:"+view);
        selectDate = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-"
                + calendar.get(Calendar.DAY_OF_MONTH)
                + DatePicker.getDayOfWeekCN(calendar.get(Calendar.DAY_OF_WEEK));
        //选择时间与当前时间的初始化，用于判断用户选择的是否是以前的时间，如果是，弹出toss提示不能选择过去的时间
        selectDay = currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        selectMinute = currentMinute = calendar.get(Calendar.MINUTE);
        selectHour = currentHour = calendar.get(Calendar.HOUR_OF_DAY);

        selectTime = currentHour + ":" + ((currentMinute < 10)?("0"+currentMinute):currentMinute);
        LogUtils.e("selectTime:"+selectTime);
        dp_test = (TimePicker)view.findViewById(R.id.dp_test);
        dp_test.init(dataNum);
        tp_test = (TimePicker)view.findViewById(R.id.tp_test);
        tp_test.init(time);
        tv_ok = (TextView) view.findViewById(R.id.tv_ok);
        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);

        //设置滑动改变监听器
        dp_test.setOnChangeListener(tp_onchanghelistenerOne);
        tp_test.setOnChangeListener(tp_onchanghelistenerTwo);

        pw = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.WRAP_CONTENT, true);
        //设置这2个使得点击pop以外区域可以去除pop
        pw.setOutsideTouchable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        pw.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);

        LogUtils.e(">>>>>>11111");
        //出现在布局底端
        pw.showAtLocation(view, 0, 0,  Gravity.END);
        LogUtils.e(">>>>>>22222");
        //点击确定
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                pw.dismiss();
            }
        });

        //点击取消
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                pw.dismiss();
            }
        });
    }
}
