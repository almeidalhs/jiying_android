package com.corelib.widget;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;
import com.seawind.corelib.R;
import com.corelib.base.BaseAppCompatActivity;

import java.util.concurrent.CopyOnWriteArrayList;

public class AutoScrollViewPagerAdapter extends PagerAdapter {

    private int COUNT;
    private BaseAppCompatActivity mContext = null;
    private TopOnBack topOnBack;

    public AutoScrollViewPagerAdapter(BaseAppCompatActivity context, TopOnBack topOnBack) {
        super();
        mContext = context;
        COUNT = Integer.MAX_VALUE;
        this.topOnBack = topOnBack;
    }

    public AutoScrollViewPagerAdapter(BaseAppCompatActivity context,int COUNT, TopOnBack topOnBack) {
        super();
        mContext = context;
        this.COUNT = COUNT;
        this.topOnBack = topOnBack;
    }

    private CopyOnWriteArrayList<String> mUrlsList = new CopyOnWriteArrayList<>();

    public void addUrls(String url) {
        mUrlsList.add(url);
        notifyDataSetChanged();
    }

    // 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
    @Override
    public int getCount() {
        return COUNT;
    }

    // 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    // PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View) object);
    }

    // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        position = position% mUrlsList.size();
        final NetworkImageView networkImageView = new NetworkImageView(mContext);
        networkImageView.setDefaultImageResId(0);
        networkImageView.setErrorImageResId(0);
        networkImageView.setImageUrl(mUrlsList.get(position), mContext.getImageLoader());
        networkImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        networkImageView.setTag(position);
        view.addView(networkImageView);
        networkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topOnBack.onBack((int) v.getTag());
            }
        });
        return networkImageView;
    }

    public interface TopOnBack {
        public void onBack(int num);
    };
}
