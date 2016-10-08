package com.atman.jishang.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.android.volley.VolleyError;
import com.atman.jishang.net.DataManager;
import com.atman.jishang.utils.UiHelper;
import com.corelib.base.BaseFragment;
import com.corelib.iimp.IInit;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/11 13:20
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class BaiYeBaseFragment extends BaseFragment implements IInit {

    private DataManager mDataManager = null;
    /**
     * 该标志位表示第一次进入先初始化init(包含基本数据和网络获取数据)
     */
    private boolean isFirstInto = true;
    /**
     * 是否返回界面时刷新数据 默认不刷新
     */
    public boolean isRefreshNetworkData = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = new DataManager(this, this);
    }

    /**
     * 初始化
     */
    protected void init() {
        initWidget();
        initIntentAndMemData();
        doInitBaseHttp();
    }

    /**
     * 该方法会重新刷新doInitBaseHttp中所请求的数据
     */
    protected void resumeToRefreshBaseData() {
        doInitBaseHttp();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isFirstInto) {
            init();
            isFirstInto = false;
        }
        if (isRefreshNetworkData && !isFirstInto) {
            resumeToRefreshBaseData();
        }
    }

    public int getmWidth() {
        return ((BaiYeBaseActivity) getActivity()).getmWidth();
    }

    public void toPhone(Context mContext,String phonenumber){
        if (!UiHelper.isTabletDevice(getActivity())) {
            ((BaiYeBaseActivity) getActivity()).toPhone(mContext,phonenumber);
        } else {
            showToast("您的设备不支持拨号");
        }
    }

    public void showToast(String msg) {
        getBaseAppCompatActivity().showToast(msg);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        getBaseAppCompatActivity().onErrorResponse(error);
    }

    @Override
    public void onResponse(Object response, String data) {
        cancelLoading();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    @Override
    public void initIntentAndMemData() {

    }

    @Override
    public void doInitBaseHttp() {

    }

    @Override
    public void onViewClick(View v) {

    }

    @Override
    public void initWidget(View... v) {

    }

    public boolean isLogin() {
        return ((BaiYeBaseActivity) getActivity()).isLogin();
    }

    public String getToken(){
        return ((BaiYeBaseActivity) getActivity()).getToken();
    }

    @Override
    public void onResponseNotCorrect(Object response) {
        ((BaiYeBaseActivity) getActivity()).onResponseNotCorrect(response);
    }
}
