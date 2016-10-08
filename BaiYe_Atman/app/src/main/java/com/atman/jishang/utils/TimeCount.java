package com.atman.jishang.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.atman.jishang.interfaces.TimeCountInterface;
import com.corelib.widget.MyCleanEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 倒计时
 * 作者 tangbingliang
 * 时间 16/4/13 13:42
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class TimeCount extends CountDownTimer {
    private TextView btn;
    private List<MyCleanEditText> mMyCleanEditTextList = new ArrayList<>();
    private TimeCountInterface mTimeCountInterface;

    public TimeCount(TextView btn, long millisInFuture, long countDownInterval,MyCleanEditText... mMyCleanEditText) {
        super(millisInFuture, countDownInterval);
        this.btn = btn;
        for (MyCleanEditText view:mMyCleanEditText) {

            this.mMyCleanEditTextList.add(view);
        }
    }

    public TimeCount(long millisInFuture, long countDownInterval, TimeCountInterface mTimeCountInterface, MyCleanEditText... mMyCleanEditText) {
        super(millisInFuture, countDownInterval);
        this.btn = btn;
        this.mTimeCountInterface = mTimeCountInterface;
        for (MyCleanEditText view:mMyCleanEditText) {

            this.mMyCleanEditTextList.add(view);
        }
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (mTimeCountInterface!=null) {
            mTimeCountInterface.onBackTick(millisUntilFinished);
        }
        if (btn!=null) {
            btn.setClickable(false);
            btn.setEnabled(false);
            btn.setText(millisUntilFinished / 1000 + "秒后重发");
        }
        for (MyCleanEditText view:mMyCleanEditTextList) {
            if (view!=null){
                view.clearFocus();
                view.setEnabled(false);
            }
        }
    }

    @Override
    public void onFinish() {
        if (mTimeCountInterface!=null) {
            mTimeCountInterface.onTimeOut();
        }
        if (btn!=null) {
            btn.setText("获取验证码");
            btn.setClickable(true);
            btn.setEnabled(true);
        }
        for (MyCleanEditText view:mMyCleanEditTextList) {
            if (view!=null){
                view.setEnabled(true);
            }
        }
    }
}
