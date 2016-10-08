package com.corelib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.seawind.corelib.R;

public class MyRatingBar extends LinearLayout {
    private View mView;
    private ImageView mImageView1;
    private ImageView mImageView2;
    private ImageView mImageView3;
    private ImageView mImageView4;
    private ImageView mImageView5;

    private Drawable defaultDrawble, defaultDrawblePressed;
    private int rate;

    public MyRatingBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
    }

    public MyRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public MyRatingBar(Context context) {
        super(context);
        initView(context, null);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray a = null;
        defaultDrawble = getResources().getDrawable(R.drawable.ip_comments);
        defaultDrawblePressed = getResources().getDrawable(R.drawable.ip_comments_pressed);
        rate = 0;
        if (attrs != null) {
            a = context.obtainStyledAttributes(attrs, R.styleable.MyRatingBar, 0, 0);
            defaultDrawblePressed = a.getDrawable(R.styleable.MyRatingBar_defaultImagePressed);
            defaultDrawble = a.getDrawable(R.styleable.MyRatingBar_defaultImage);
            rate = a.getInt(R.styleable.MyRatingBar_defaultImageNum, 0);
        }
        mView = LayoutInflater.from(context).inflate(R.layout.view_ratingbar,
                null);
        mImageView1 = (ImageView) mView.findViewById(R.id.imageview1);
        mImageView2 = (ImageView) mView.findViewById(R.id.imageview2);
        mImageView3 = (ImageView) mView.findViewById(R.id.imageview3);
        mImageView4 = (ImageView) mView.findViewById(R.id.imageview4);
        mImageView5 = (ImageView) mView.findViewById(R.id.imageview5);
        addView(mView);
        setRating(rate);
    }

    public void setRating(int rat) {
        mImageView1.setImageDrawable(rat < 1 ? defaultDrawble : defaultDrawblePressed);
        mImageView2.setImageDrawable(rat < 2 ? defaultDrawble : defaultDrawblePressed);
        mImageView3.setImageDrawable(rat < 3 ? defaultDrawble : defaultDrawblePressed);
        mImageView4.setImageDrawable(rat < 4 ? defaultDrawble : defaultDrawblePressed);
        mImageView5.setImageDrawable(rat < 5 ? defaultDrawble : defaultDrawblePressed);
    }
}
