package com.corelib.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.corelib.base.BaseApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SuppressLint("SimpleDateFormat")
public class YLBConversionUtils {

	/**
	 * dp转换成px
	 * 
	 * @param dpValue
	 * @return
	 */
	public static int dp2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * px转换成dp
	 * 
	 * @param dpValue
	 * @return
	 */
	public static int px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 判断字串是否为null或者为""
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isNullOrEmpter(String text) {
		if (text == null || text.isEmpty() || "null".equals(text)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取屏幕的宽高
	 * 
	 * @return
	 */
	public static int[] screenWidth() {
		WindowManager wm = (WindowManager) BaseApplication.getContext()
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		int[] screen = new int[2];
		screen[0] = outMetrics.widthPixels;
		screen[1] = outMetrics.heightPixels;
		return screen;
	}

	/**
	 * 根据ID获取文本内容
	 * 
	 * @param resId
	 * @return
	 */
	public static String getStringById(int resId) {
		return BaseApplication.getContext().getResources().getString(resId);
	}

	/**
	 * 获取颜色值
	 */
	public static int getColorById(int resId) {
		return BaseApplication.getContext().getResources().getColor(resId);
	}

	/**
	 * 根据ID获取数据大小
	 * 
	 * @param resId
	 * @return
	 */
	public static float getDimenById(int resId) {
		return BaseApplication.getContext().getResources().getDimension(resId);
	}

	/**
	 * 提示
	 * 
	 * @param text
	 */
	public static void showToast(String text) {
		Toast.makeText(BaseApplication.getContext(), text, Toast.LENGTH_LONG)
				.show();
	}

	/**
	 * 强制提示
	 * 
	 * @param text
	 */
	public static void showToastForce(final String text) {
		Looper.prepare();
		Toast.makeText(BaseApplication.getContext(), text, Toast.LENGTH_SHORT)
				.show();
		Looper.loop();
	}

	/**
	 * TextView添加图片通过代码
	 * 
	 * @param position
	 *            1 表示左 2表示上 3表示右 4表示下
	 */
	public static void addDrawableToTextView(Context context, TextView view,
			int resId, int position) {
		Drawable drawable = context.getResources().getDrawable(resId);
		drawable.setBounds(0, 0, drawable.getMinimumWidth(),
				drawable.getMinimumHeight());
		switch (position) {
		case 1:
			view.setCompoundDrawables(drawable, null, null, null);
			break;
		case 2:
			view.setCompoundDrawables(null, drawable, null, null);
			break;
		case 3:
			view.setCompoundDrawables(null, null, drawable, null);
			break;
		case 4:
			view.setCompoundDrawables(null, null, null, drawable);
			break;

		default:
			break;
		}
	}

	/**
	 * 更高Shape的顏色
	 * 
	 * @param color
	 *            例如"#ffefee"
	 */
	public static void setShapeColor(View view, String color) {
		GradientDrawable bgShape = (GradientDrawable) view.getBackground();
		bgShape.setColor(Color.parseColor(color));
	}

	/**
	 * 更改Shape的颜色
	 */
	public static void setShapeColor(View view, int resId) {
		GradientDrawable bgShape = (GradientDrawable) view.getBackground();
		bgShape.setColor(getColorById(resId));
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd
	 */
	public static String getNowDate(String inputDate) {
		long old = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (inputDate.contains(" ")) {
			try {
				old = sdf.parse(inputDate).getTime();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			old = Long.parseLong(inputDate) * 1000;
		}
		return (new SimpleDateFormat("yyyy-MM-dd")).format(new Date(old));
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowDate2(String inputDate) {
		long old = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (inputDate.contains(" ")) {
			try {
				old = sdf.parse(inputDate).getTime();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			old = Long.parseLong(inputDate) * 1000;
		}
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(
				old));
	}


	public static Date strToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将sp值转换为px值 保证文字大小不变
	 * 
	 * @param spValue
	 * @param fontScale
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().density;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 获取指定保留小数位数的字串
	 */
	@SuppressLint("DefaultLocale")
	public static String getStringByFloat(float value, int limit) {
		if (value != 0) {
			return String.format("%." + limit + "f", value);
		} else {
			return "0.00";
		}
	}

	/**
	 * 获取指定保留小数位数的字串
	 */
	@SuppressLint("DefaultLocale")
	public static String getStringByFloat(double value, int limit) {
		if (value != 0) {
			return String.format("%." + limit + "f", value);
		} else {
			return "0.00";
		}
	}



	/**
	 * 手机号
	 */
	public static String getProtectedMobile(String phoneNumber) {
		if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		builder.append(phoneNumber.subSequence(0, 3));
		builder.append("****");
		builder.append(phoneNumber.subSequence(7, 11));
		return builder.toString();
	}

	/**
	 * 计算list高度
	 * 
	 * @param listView
	 */
	public static void fixListViewHeight(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		int totalHeight = 0;
		if (listAdapter == null) {
			return;
		}
		for (int index = 0, len = listAdapter.getCount(); index < len; index++) {
			View listViewItem = listAdapter.getView(index, null, listView);
			listViewItem.measure(0, 0);
			totalHeight += listViewItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);

	}


	public static void listAllActivities(Context context)
			throws NameNotFoundException {
		List<PackageInfo> packages = context.getPackageManager()
				.getInstalledPackages(0);
		for (PackageInfo pack : packages) {
			ActivityInfo[] activityInfo = context.getPackageManager()
					.getPackageInfo(pack.packageName,
							PackageManager.GET_ACTIVITIES).activities;
			Log.i("Pranay", pack.packageName + " has total "
					+ ((activityInfo == null) ? 0 : activityInfo.length)
					+ " activities");
			if (activityInfo != null) {
				for (int i = 0; i < activityInfo.length; i++) {
					Log.i("PC", pack.packageName + " ::: "
							+ activityInfo[i].name);
				}
			}
		}
	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isBackground(Context context) {
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.processName.equals(context.getPackageName())) {
				if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public static boolean isApplicationBroughtToBackground(final Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasks = am.getRunningTasks(1);
		if (!tasks.isEmpty()) {
			ComponentName topActivity = tasks.get(0).topActivity;
			if (!topActivity.getPackageName().equals(context.getPackageName())) {
				return true;
			}
		}
		return false;
	}



	/**
	 * 获取application中指定的meta-data
	 * 
	 * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
	 */
	public static String getAppMetaData(Context ctx, String key) {
		if (ctx == null || TextUtils.isEmpty(key)) {
			return null;
		}
		String resultData = null;
		try {
			PackageManager packageManager = ctx.getPackageManager();
			if (packageManager != null) {
				ApplicationInfo applicationInfo = packageManager
						.getApplicationInfo(ctx.getPackageName(),
								PackageManager.GET_META_DATA);
				if (applicationInfo != null) {
					if (applicationInfo.metaData != null) {
						resultData = applicationInfo.metaData.getString(key);
					}
				}
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return resultData;
	}

}
