package com.atman.jishang.widget.dateselect;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.atman.jishang.utils.MyTools;
import com.atman.jishang.widget.dateselect.wheel.widget.OnWheelChangedListener;
import com.atman.jishang.widget.dateselect.wheel.widget.WheelView;
import com.corelib.util.LogUtils;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/19 13:52
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class TimePicker extends LinearLayout {

    private Calendar calendar = Calendar.getInstance();
    private WheelView hours, mins; //Wheel picker
    private OnChangeListener onChangeListener; //onChangeListener
    private int MARGIN_RIGHT = 15;        //调整文字右端距离
    private ArrayList<DateObject> hourList,minuteList;
    private DateObject dateObject;      //时间数据对象
    private Context context;
    //Constructors
    public TimePicker(Context context) {
        super(context);
        this.context = context;
    }

    public TimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    /**
     * 初始化
     * @param  time
     */
    public void init(String time){
        MARGIN_RIGHT = dip2px(context, 15);
        hourList = new ArrayList<DateObject>();
        minuteList = new ArrayList<DateObject>();

        for (int i = 0; i < 24; i++) {
            dateObject = new DateObject(i,-1,true);
            hourList.add(dateObject);
        }

        for (int j = 0; j < 60; j++) {
            dateObject = new DateObject(-1,j,false);
            minuteList.add(dateObject);
        }

        //小时选择器
        hours = new WheelView(context);
        LayoutParams lparams_hours = new LayoutParams(dip2px(context, 40),LayoutParams.WRAP_CONTENT);
        lparams_hours.setMargins(0, 0, MARGIN_RIGHT, 0);
        hours.setLayoutParams(lparams_hours);
        hours.setAdapter(new StringWheelAdapter(hourList, 24));
        hours.setVisibleItems(5);
        hours.setCyclic(false);
        hours.addChangingListener(onHoursChangedListener);
        addView(hours);

        //分钟选择器
        mins = new WheelView(context);
        mins.setLayoutParams(new LayoutParams(dip2px(context, 40),LayoutParams.WRAP_CONTENT));
        mins.setAdapter(new StringWheelAdapter(minuteList,60));
        mins.setVisibleItems(5);
        mins.setCyclic(false);
        mins.addChangingListener(onMinsChangedListener);
        addView(mins);

        toNow(true, time);
    }

    public void toNow(boolean isThis, String time) {
        Calendar ca = Calendar.getInstance();
        for (int i=0;i<hourList.size();i++) {
            if (!time.isEmpty()) {
                if (time.length()==5) {
                    if (Integer.parseInt(time.substring(0, 2))==hourList.get(i).getHour()) {
                        hours.setCurrentItem(i);
                    }
                } else {
                    if (Integer.parseInt(time.substring(11, 13))==hourList.get(i).getHour()) {
                        hours.setCurrentItem(i);
                    }
                }
            } else {
                if (ca.get(Calendar.HOUR_OF_DAY)==hourList.get(i).getHour()) {
                    if (isThis) {
                        hours.setCurrentItem(i);
                    } else {
                        hours.setCurrentItem(i,true);
                    }
                }
            }
        }
        for (int i=0;i<minuteList.size();i++) {
            if (!time.isEmpty()) {
                if (time.length()==5) {
                    if (Integer.parseInt(time.substring(3, 5))==minuteList.get(i).getMinute()) {
                        mins.setCurrentItem(i);
                    }
                } else {
                    if (Integer.parseInt(time.substring(14, 16))==minuteList.get(i).getMinute()) {
                        mins.setCurrentItem(i);
                    }
                }
            } else {
                if (ca.get(Calendar.MINUTE)==minuteList.get(i).getMinute()) {
                    if (isThis) {
                        mins.setCurrentItem(i);
                    } else {
                        mins.setCurrentItem(i,true);
                    }
                }
            }
        }
    }

    //listeners
    private OnWheelChangedListener onHoursChangedListener = new OnWheelChangedListener(){
        @Override
        public void onChanged(WheelView hours, int oldValue, int newValue) {
            calendar.set(Calendar.HOUR_OF_DAY, newValue);
            change();
        }
    };
    private OnWheelChangedListener onMinsChangedListener = new OnWheelChangedListener(){
        @Override
        public void onChanged(WheelView mins, int oldValue, int newValue) {
            calendar.set(Calendar.MINUTE, newValue);
            change();
        }
    };

    /**
     * 滑动改变监听器回调的接口
     */
    public interface OnChangeListener {
        void onChange(int hour, int munite);
    }

    /**
     * 设置滑动改变监听器
     * @param onChangeListener
     */
    public void setOnChangeListener(OnChangeListener onChangeListener){
        this.onChangeListener = onChangeListener;
    }

    /**
     * 滑动最终调用的方法
     */
    private void change(){
        if(onChangeListener!=null){
            onChangeListener.onChange(getHourOfDay(), getMinute());
        }
    }


    /**
     * 获取小时
     * @return
     */
    public int getHourOfDay(){
        return hourList.get(hours.getCurrentItem()).getHour();
    }

    /**
     * 获取分钟
     * @return
     */
    public int getMinute(){
        return minuteList.get(mins.getCurrentItem()).getMinute();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
