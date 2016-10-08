package com.atman.jishang.utils;

import android.text.TextUtils;

import com.corelib.util.LogUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/18 10:37
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MyTools {

    /**
     * 返回格式
     * @param fee
     * @return
     */
    public static String formatfloat(float fee) {
        DecimalFormat format = new DecimalFormat("#.##");
        return format.format(fee);
    }

    //时间格式转换 yyyy-MM-dd
    public static String convertTime(long unixTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(unixTime * 1000);
        return sdf.format(date);
    }

    public static String convertTime(long unixTime, String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        Date date = new Date(unixTime * 1000);
        return sdf.format(date);
    }

    public static String convertTimeTwo(long unixTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(unixTime * 1000);
        return sdf.format(date);
    }

    public static String convertTimeThree(long unixTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        Date date = new Date(unixTime * 1000);
        return sdf.format(date);
    }

    public static String convertTimeForYear(long unixTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date(unixTime * 1000);
        return sdf.format(date);
    }

    public static String convertTimeForMonth(long unixTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Date date = new Date(unixTime * 1000);
        String label = sdf.format(date);
        if (!TextUtils.isEmpty(label)) {
            if (label.startsWith("0")) {
                return label.substring(1);
            }
        }
        return label;
    }

    public static String getLacalDate() {
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    public static String getLacalDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat (str);
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    public static String getLacalTime() {
        SimpleDateFormat formatter = new SimpleDateFormat ("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }

    public static String transformationToUnix(String s){
        String str = "";

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(s);
            str = String.valueOf(date.getTime());
            if (str.length()>10) {
                str = str.substring(0,10);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static long transformationToUnix(String s, String tt){
        long str = System.currentTimeMillis() / 1000;

        try {
            Date date = new SimpleDateFormat(tt).parse(s);
            str = Long.parseLong(String.valueOf(date.getTime())) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String transformationToStandard(String s){
        long l = 0;
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(s);
            l = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(l);
        return sdf.format(date);
    }

    public static String transformationToStandard(String s, String str){
        long l = 0;
        try {
            Date date = new SimpleDateFormat(str).parse(s);
            l = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        Date date = new Date(l);
        return sdf.format(date);
    }

    public static String RandomColor(){
        String r,g,b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length()==1 ? "0" + r : r ;
        g = g.length()==1 ? "0" + g : g ;
        b = b.length()==1 ? "0" + b : b ;

        return "#"+r+g+b;
    }

    /**
     * 获取两个日期之间的间隔天数
     * @return
     */
    public static int getGapCount(String startDate, String endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        Date s1 = null;
        Date s2 = null;
        try {
            s1= new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            s2= new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fromCalendar.setTime(s1);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(s2);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }
}
