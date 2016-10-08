package com.atman.jishang.widget.showimage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoView;

/**
 * 描述：
 * 时间：2015/11/18 15:00
 * 作者：炳良
 * 邮箱：tangbingliang@yonglibao.com
 * 版本：1.0
 */

public class ShowWebImageActivity extends SimpleTitleBarActivity {

    @Bind(R.id.image_num_tv)
    TextView imageNumTv;

    private List<String> imagePath = null;
    private String[] mImageUrl = null;
    private HackyViewPager webViewPager_id;
    private int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showwebimage);
        ButterKnife.bind(this);

        setupViews();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        hideTitleBar();
    }

    protected void setupViews() {

        imagePath = new ArrayList<String>();
        mImageUrl = getIntent().getStringExtra("image").split(",");
        num = getIntent().getIntExtra("ID", 1);
        for (int i = 0, j = mImageUrl.length; i < j; i++) {
            System.out.println(">>>>>>mImageUrl[i]:" + mImageUrl[i]);
            if (mImageUrl[i].startsWith("http")) {
                imagePath.add(mImageUrl[i]);
            } else {
                String str = mImageUrl[i];
                System.out.println(">>>>>>str:" + str);
                if (str.startsWith("http")) {
                    imagePath.add(str);
                }
            }
        }
        imageNumTv.setText("（"+num+"/"+ imagePath.size()+"）");
        webViewPager_id = (HackyViewPager) findViewById(R.id.webViewPager_id);
        webViewPager_id.setCurrentItem(num);
        webViewPager_id.setAdapter(new SamplePagerAdapter(ShowWebImageActivity.this));
        webViewPager_id.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                imageNumTv.setText("（"+(position+1)+"/"+imagePath.size()+"）");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.image_back_iv, R.id.image_back_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back_iv:
            case R.id.image_back_ll:
                finish();
                break;
        }
    }

    private class SamplePagerAdapter extends PagerAdapter {

        public SamplePagerAdapter(Context context) {
        }

        @Override
        public int getCount() {
            return imagePath.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            System.out.println("imagePath[position]:" + imagePath.get(position));
            ImageLoader.getInstance().displayImage(imagePath.get(position), photoView, BaiYeBaseApplication.getApp().getOptionsNot());
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

}
