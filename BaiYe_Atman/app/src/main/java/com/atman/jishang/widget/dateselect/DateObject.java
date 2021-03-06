package com.atman.jishang.widget.dateselect;

import java.util.Calendar;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/19 13:46
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class DateObject {
    private int year ;
    private int month;
    private int day;
    private int week;
    private int hour;
    private int minute;
    private String listItem;

    /**
     * 日期对象的4个参数构造器，用于设置日期
     * @param year2
     * @param month2
     * @param day2
     * @author
     */
    public DateObject(int year2, int month2, int day2,int week2) {
        super();
        this.year = year2;
        this.month = month2;
        this.day = day2;
        this.week = week2;

        if(day == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                && Calendar.getInstance().get(Calendar.YEAR) == year
                && Calendar.getInstance().get(Calendar.MONTH) == month){
            this.listItem = String.format("%02d", this.year) +"年"+String.format("%02d", this.month) +"月" + String.format("%02d", this.day)  +
                    "日 "+ "今天";
        }else{
            this.listItem = String.format("%02d", this.year) +"年"+String.format("%02d", this.month) +"月" + String.format("%02d", this.day)  +
                    "日 "+ getDayOfWeekCN(week);
        }

    }

    /**
     * 日期对象的2个参数构造器，用于设置时间
     * @param hour2
     * @param minute2
     * @param isHourType true:传入的是hour; false: 传入的是minute
     * @author sxzhang
     */
    public DateObject(int hour2,int minute2,boolean isHourType) {
        super();
        if(isHourType == true && hour2 != -1){      //设置小时
            if(hour2 > 24){
                this.hour = hour2 % 24;
            }else
                this.hour = hour2;
            this.listItem =  this.hour + "";
        }else if(isHourType == false && minute2 != -1){ //设置分钟
            if(minute2 > 60)
                this.minute = minute2 % 60;
            else
                this.minute = minute2;
            this.listItem =  this.minute + "";
        }
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public String getListItem() {
        return listItem;
    }
    public void setListItem(String listItem) {
        this.listItem = listItem;
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
}
