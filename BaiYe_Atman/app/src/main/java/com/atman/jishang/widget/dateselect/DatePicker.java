package com.atman.jishang.widget.dateselect;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.utils.MyTools;
import com.atman.jishang.widget.dateselect.wheel.widget.OnWheelChangedListener;
import com.atman.jishang.widget.dateselect.wheel.widget.WheelView;
import com.corelib.util.LogUtils;
import com.nostra13.universalimageloader.utils.L;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/19 13:50
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class DatePicker extends LinearLayout {

    private int[] num = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private Calendar calendar;
    private WheelView newDays;
    private ArrayList<DateObject> dateList ;
    private OnChangeListener onChangeListener; //onChangeListener
    private int MARGIN_RIGHT;
    private DateObject dateObject;      //日期数据对象
    private Context context;

    //Constructors
    public DatePicker(Context context) {
        super(context);
        this.context = context;
    }

    public DatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    /**
     * 初始化
     * @param dataNum
     */
    public void init(int dataNum){
        MARGIN_RIGHT = dip2px(context, 20);
        calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int w;
//        dateList = new ArrayList<DateObject>();
//
//        for (int y=year-1;y<=(year+4);y++) {
//            for (int m=1;m<=num.length;m++) {
//                int max = num[m-1];
//                if (y%4==0 && m==2) {
//                    max = num[m-1]+1;
//                }
//                for (int d=1;d<=max;d++) {
//                    w = getWeek(y+"-"+m+"-"+d);
//                    dateObject = new DateObject(y, m, d, w);
//                    dateList.add(dateObject);
//                }
//            }
//        }
        dateList = BaiYeBaseApplication.getApp().getDateList();
        newDays = new WheelView(context);
        LayoutParams newDays_param = new LayoutParams(dip2px(context, 200),LayoutParams.WRAP_CONTENT);
        newDays_param.setMargins(0, 0, MARGIN_RIGHT, 0);
        newDays.setLayoutParams(newDays_param);
        newDays.setAdapter(new StringWheelAdapter(dateList, 7));
        newDays.setVisibleItems(5);
        newDays.setCyclic(false);
        newDays.addChangingListener(onDaysChangedListener);
        addView(newDays);

        toNow(true, dataNum);
    }

    public void toNow(boolean isThis, int dataNum){
        Calendar ca = Calendar.getInstance();
        if (dataNum == -1) {
            for (int i=0;i<dateList.size();i++) {
                if (ca.get(Calendar.YEAR)==dateList.get(i).getYear()
                        && (ca.get(Calendar.MONTH) + 1)==dateList.get(i).getMonth()
                        && ca.get(Calendar.DAY_OF_MONTH)==dateList.get(i).getDay()) {
                    if (isThis) {
                        newDays.setCurrentItem(i);
                    } else {
                        newDays.setCurrentItem(i, true);
                    }
                }
            }
        } else {
            newDays.setCurrentItem(dataNum);
        }
    }

    /**
     * 滑动改变监听器
     */
    private OnWheelChangedListener onDaysChangedListener = new OnWheelChangedListener(){
        @Override
        public void onChanged(WheelView mins, int oldValue, int newValue) {
            calendar.set(Calendar.DAY_OF_MONTH, newValue + 1);
            change();
        }
    };

    /**
     * 滑动改变监听器回调的接口
     */
    public interface OnChangeListener {
        void onChange(int year, int month, int day, int day_of_week, int num);
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
            onChangeListener.onChange(
                    dateList.get(newDays.getCurrentItem()).getYear(),
                    dateList.get(newDays.getCurrentItem()).getMonth(),
                    dateList.get(newDays.getCurrentItem()).getDay(),
                    dateList.get(newDays.getCurrentItem()).getWeek(),
                    newDays.getCurrentItem());
        }
    }


    /**
     * 根据day_of_week得到汉字星期
     * @return
     */
    public static String getDayOfWeekCN(int day_of_week){
        String result = null;
        switch(day_of_week){
            case 1:
                result = "周日";
                break;
            case 2:
                result = "周一";
                break;
            case 3:
                result = "周二";
                break;
            case 4:
                result = "周三";
                break;
            case 5:
                result = "周四";
                break;
            case 6:
                result = "周五";
                break;
            case 7:
                result = "周六";
                break;
            default:
                break;
        }
        return result;
    }

    private int getWeek(String pTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c.get(Calendar.DAY_OF_WEEK);
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
