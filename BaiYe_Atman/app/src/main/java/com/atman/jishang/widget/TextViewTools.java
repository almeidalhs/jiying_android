package com.atman.jishang.widget;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.atman.jishang.ui.circle.PublishActivity;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/6/20 16:16
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class TextViewTools {

    private Context context;
    private int position;
    private TextView mTextView;
    private textviewListener mListener;

    public TextViewTools(Context context, int position, TextView mTextView, textviewListener mListener){
        this.context = context;
        this.position = position;
        this.mTextView = mTextView;
        this.mListener = mListener;
    }

    public void replyOne(String str1){
        SpannableString spanOne = new SpannableString(str1);
        ClickableSpan clickOne = new NoLineCllikcSpan();
        spanOne.setSpan(clickOne, 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTextView.setText("");
        mTextView.append(spanOne);
        mTextView.append("：");
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void replyTwo(String str1, String str2){
        SpannableString spanStr1 = new SpannableString(str1);
        SpannableString spanStr2 = new SpannableString(str2);
        ClickableSpan clickStr1 = new NoLineCllikcSpan();
        ClickableSpan clickStr2 = new NoLineCllikcSpan();
        spanStr1.setSpan(clickStr1, 0, str1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spanStr2.setSpan(clickStr2, 0, str2.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTextView.setText("");
        mTextView.append(spanStr1);
        mTextView.append("回复");
        mTextView.append(spanStr2);
        mTextView.append("：");
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public class NoLineCllikcSpan extends ClickableSpan {

        public NoLineCllikcSpan() {
            super();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            /**set textColor**/
            ds.setColor(ds.linkColor);
            /**Remove the underline**/
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(View widget) {
            if (mListener!=null) {
                mListener.onClick(widget);
            }
        }
    }

    public interface textviewListener {
        void onClick(View widget);
    }
}
