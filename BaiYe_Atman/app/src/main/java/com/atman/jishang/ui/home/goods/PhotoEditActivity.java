package com.atman.jishang.ui.home.goods;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.choicepicture_library.tools.Bimp;
import com.choicepicture_library.tools.FileUtils;
import com.corelib.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class PhotoEditActivity extends SimpleTitleBarActivity {

	private ArrayList<View> listViews = null;
	private ViewPager pager;
	private MyPageAdapter adapter;
	private int count;

	public List<Bitmap> bmp = new ArrayList<Bitmap>();
	public List<String> drr = new ArrayList<String>();
	public List<String> del = new ArrayList<String>();

	RelativeLayout photo_relativeLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.choicepicture_library.R.layout.activity_photo);

		photo_relativeLayout = (RelativeLayout) findViewById(com.choicepicture_library.R.id.photo_relativeLayout);
		photo_relativeLayout.setBackgroundColor(0x70000000);

		if (getIntent().getBooleanExtra("isHit",false)) {
			photo_relativeLayout.setVisibility(View.GONE);
		}

		for (int i = 0; i < Bimp.bmp.size(); i++) {
			bmp.add(Bimp.bmp.get(i));
		}
		for (int i = 0; i < Bimp.drr.size(); i++) {
			drr.add(Bimp.drr.get(i));
		}

		Button photo_bt_exit = (Button) findViewById(com.choicepicture_library.R.id.photo_bt_exit);
		photo_bt_exit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				finish();
			}
		});
		Button photo_bt_del = (Button) findViewById(com.choicepicture_library.R.id.photo_bt_del);
		photo_bt_del.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (listViews.size() == 1) {
					Bimp.bmp.clear();
					Bimp.drr.clear();
					Bimp.num = 0;
					FileUtils.deleteDir();
					finish();
				} else {
					String newStr = drr.get(count).substring(
							drr.get(count).lastIndexOf("/") + 1,
							drr.get(count).lastIndexOf("."));
					Bimp.bmp.remove(count);
					Bimp.drr.remove(count);
					Bimp.drr_or.remove(count);
					del.add(newStr);
					Bimp.num -=1;
					pager.removeAllViews();
					listViews.remove(count);
					adapter.setListViews(listViews);
				}
			}
		});
		Button photo_bt_enter = (Button) findViewById(com.choicepicture_library.R.id.photo_bt_enter);
		photo_bt_enter.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				for(int i=0;i<del.size();i++){
					FileUtils.delFile(del.get(i)+".JPEG");
				}
				finish();
			}
		});

		pager = (ViewPager) findViewById(com.choicepicture_library.R.id.viewpager);
		pager.setOnPageChangeListener(pageChangeListener);
		adapter = new MyPageAdapter();// 构�?adapter
		for (int i = 0; i < bmp.size(); i++) {
			initListViews(bmp.get(i), Bimp.drr.get(i));//
		}
		pager.setAdapter(adapter);// 设置适配�?
		Intent intent = getIntent();
		int id = intent.getIntExtra("ID", 0);
		pager.setCurrentItem(id);
	}

	@Override
	public void initWidget(View... v) {
		super.initWidget(v);
		hideTitleBar();
	}

	private void initListViews(Bitmap bm, String s) {
		if (listViews == null)
			listViews = new ArrayList<View>();
		ImageView img = new ImageView(this);// 构�?textView对象
		img.setBackgroundColor(0x000000);
		if (bm == null) {
			setBitmapToImageView(img, s, 0);
		} else {
			img.setImageBitmap(bm);
		}
		img.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		listViews.add(img);// 添加view
		adapter.setListViews(listViews);
	}

	private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

		public void onPageSelected(int arg0) {// 页面选择响应函数
			count = arg0;
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {// 滑动中�?。�?

		}

		public void onPageScrollStateChanged(int arg0) {// 滑动状�?改变

		}
	};

	class MyPageAdapter extends PagerAdapter {

		private ArrayList<View> listViews;// content

		private int size;// 页数

		public MyPageAdapter() {// 构�?函数
															// 初始化viewpager的时候给的一个页�?
			this.listViews = new ArrayList<>();
			size = listViews == null ? 0 : listViews.size();
		}

		public void setListViews(ArrayList<View> listViews) {// 自己写的�?��方法用来添加数据
			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
			notifyDataSetChanged();
		}

		public int getCount() {// 返回数量
			return size;
		}

		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		public void destroyItem(View arg0, int arg1, Object arg2) {// �?��view对象
			((ViewPager) arg0).removeView(listViews.get(arg1 % size));
		}

		public void finishUpdate(View arg0) {
		}

		public Object instantiateItem(View arg0, int arg1) {// 返回view对象
			try {
				((ViewPager) arg0).addView(listViews.get(arg1 % size), 0);

			} catch (Exception e) {
			}
			return listViews.get(arg1 % size);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}
}
