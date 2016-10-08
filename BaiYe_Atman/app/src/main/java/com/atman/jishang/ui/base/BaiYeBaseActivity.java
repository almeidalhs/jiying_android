package com.atman.jishang.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.ui.MainActivity;
import com.atman.jishang.net.DataManager;
import com.atman.jishang.ui.personal.loginandregister.LoginActivity;
import com.corelib.base.BaseAppCompatActivity;
import com.corelib.util.YLBConstacts;
import com.corelib.util.YLBMsgTextUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 描述 基础activity类
 * 作者 tangbingliang
 * 时间 16/4/11 09:39
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class BaiYeBaseActivity extends BaseAppCompatActivity{

    protected Activity mAty;
    private boolean mShouldLogin = true;
    private static long lastClickTime = 0;
    private final String TAG = "BaiYeBaseActivity";
    public int mWidth,mHight;
    private Display display;
    private DataManager mDataManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        display = getWindowManager().getDefaultDisplay();
        mAty = this;
        //umen
        PushAgent.getInstance(this).onAppStart();
        mDataManager = new DataManager(this, this);
    }

    /**
     * 所有网络请求接口的入口
     *
     * @return
     */
    public DataManager getDataManager() {
        return mDataManager;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (mShouldLogin) {
            if (!isLogin()) {
                //需要登陆状态，跳转到登陆界面
                startActivity(LoginActivity.createIntent(this, getIntent()));
                finish();
            } else {
                //1已注册 2已实名 3已学信认证 0用户未登录暂不处理
//                if (getRegStageId() == 2) {
//                    showToast(getString(R.string.login_school));
//                    startActivity(new Intent(this, CHSIActivity.class));
//                    finish();
//                } else if (getRegStageId() == 1) {
//                    showToast(getString(R.string.login_member));
//                    startActivity(new Intent(this, AuthActivity.class));
//                    finish();
//                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void handlerBaseVolleyError(VolleyError error) {
        cancelLoading();
        int errorCode = 0;
        if (error instanceof NetworkError) {
            errorCode = YLBConstacts.Error.NETWORKERROR;
        } else if (error instanceof ServerError) {
            errorCode = YLBConstacts.Error.SERVERERROR;
        } else if (error instanceof AuthFailureError) {
            errorCode = YLBConstacts.Error.AUTHFAILUREERROR;
        } else if (error instanceof ParseError) {
            errorCode = YLBConstacts.Error.PARSEERROR;
        } else if (error instanceof NoConnectionError) {
            errorCode = YLBConstacts.Error.NOCONNECTIONERROR;
        } else if (error instanceof TimeoutError) {
            errorCode = YLBConstacts.Error.TIMEOUTERROR;
        }
        switch (errorCode) {
            case YLBConstacts.Error.NETWORKERROR:
            case YLBConstacts.Error.CLIENTERROR:
            case YLBConstacts.Error.SERVERERROR:
            case YLBConstacts.Error.AUTHFAILUREERROR:
            case YLBConstacts.Error.PARSEERROR:
            case YLBConstacts.Error.NOCONNECTIONERROR:
            case YLBConstacts.Error.TIMEOUTERROR:
            case YLBConstacts.Error.MESSAGEERROR:
            case YLBConstacts.Loading.LOADING_CANCEL:
                showToast(YLBMsgTextUtils.getMsg(errorCode));
                break;
            default:
                break;
        }
    }

    /**
     * 网络请求失败，没有网络，超时等等错误
     * @param error
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        cancelLoading();
        handlerBaseVolleyError(error);
    }

    /**
     * 网络请求成功，并且返回的数据也是要的 code = 1000
     * @param response
     */
    @Override
    public void onResponse(Object response) {
        cancelLoading();
    }

    /**
     * 网络请求成功，但是数据是错误的，code ！= 1000
     * @param response
     */
    @Override
    public void onResponseNotCorrect(Object response) {
        cancelLoading();
        try {
            Class result = (Class) response.getClass();
            Field filed = result.getDeclaredField("message");
            filed.setAccessible(true);
            String errorMsg = (String) filed.get(response);//得到此属性的值
            if (errorMsg != null) {
                showToast(errorMsg);
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        showToast(getString(R.string.response_not_correct));
    }

    /**
     * 判断用户是否登陆
     *
     * @return
     */
    public boolean isLogin() {
        return BaiYeBaseApplication.mLoginResultModel != null;
    }

    /**
     * 防止快速点击,启动多个同样的界面
     *
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isFastDoubleClick()) {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 防止快速点击,启动多个同样的界面
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        long now = System.currentTimeMillis();
        long timeD = now - lastClickTime;
        lastClickTime = now;
        return timeD <= 500;
    }

    public void toPhone(Context context, String phoneNumber) {
        if (!phoneNumber.startsWith("tel:")){
            phoneNumber = "tel:" + phoneNumber;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse(phoneNumber);
        intent.setData(data);
        context.startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    /**
     * 如果activity需要开启 登陆状态验证
     * 有需要在public
     */
    private void enableLoginCheck() {
        mShouldLogin = true;
    }

    /**
     * 关闭登陆检查
     */
    public void disableLoginCheck() {
        mShouldLogin = false;
    }

    public String getToken(){
        return BaiYeBaseApplication.mToken;
    }

    public int getmHight() {
        return display.getHeight();
    }

    public int getmWidth() {
        return display.getWidth();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK &&
                (this instanceof LoginActivity || this instanceof MainActivity)) {// 返回键
            exitBy2Click();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}
