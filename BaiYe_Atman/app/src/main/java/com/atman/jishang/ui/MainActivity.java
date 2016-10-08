package com.atman.jishang.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;

import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.adapter.MyFragmentAdapter;
import com.atman.jishang.net.model.GetUpdateVersionModel;
import com.atman.jishang.ui.base.BaiYeBaseActivity;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.data.DataFragment;
import com.atman.jishang.ui.manager.ManagerFragment;
import com.atman.jishang.ui.manager.WebPageActivity;
import com.atman.jishang.ui.message.MessageFragment;
import com.atman.jishang.ui.personal.PersonalFragment;
import com.atman.jishang.ui.service.ServiceFragment;
import com.atman.jishang.widget.updateversion.UpdateVersionHelp;
import com.corelib.widget.NoSwipeViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaiYeBaseActivity {

    private final String MESSAGE_TAG = "message";
    private final String SERVICE_TAG = "service";
    private final String MANAGER_TAG = "manager";
    private final String DATA_TAG = "data";
    private final String PERSONAL_TAG = "personal";
    private Fragment fg;
    private MyFragmentAdapter adapter;

    @Bind(R.id.tabs_rg)
    RadioGroup mRadioGroup;
    @Bind(R.id.viewpager)
    NoSwipeViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (getIntent().getBooleanExtra("isToWeb",false)) {
            startActivity(WebPageActivity.buildIntent(MainActivity.this, BaiYeBaseApplication.mWEB_URL, ""));
        }
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        getDataManager().updateVersion(BaiYeBaseApplication.mVersionName.split("-")[0], GetUpdateVersionModel.class, false);
//        getDataManager().updateVersion("1.0.0", GetUpdateVersionModel.class, false);
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof GetUpdateVersionModel) {
            GetUpdateVersionModel mGetUpdateVersionModel = (GetUpdateVersionModel) response;
            if (mGetUpdateVersionModel.getResult().equals("1")) {
                List<String> updateList = new ArrayList<>();
                updateList.add(mGetUpdateVersionModel.getBody().getWarn());
                UpdateVersionHelp.updateNewVersion(MainActivity.this,
                        mGetUpdateVersionModel.getBody().getForce(), "", "", mGetUpdateVersionModel.getBody().getUrl(),updateList);
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    public static Intent buildIntent(Context context, boolean isToWeb){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("isToWeb", isToWeb);
        return intent;
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        initViewpager();
        initBottomBar();
        setSwipeBackEnable(false);
    }

    private void initViewpager() {
        mViewPager.setPagingEnabled(false);//是否支持手势滑动
        adapter = new MyFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new MessageFragment(), MESSAGE_TAG);
        adapter.addFragment(new ServiceFragment(), SERVICE_TAG);
        adapter.addFragment(new ManagerFragment(), MANAGER_TAG);
        adapter.addFragment(new DataFragment(), DATA_TAG);
        adapter.addFragment(new PersonalFragment(), PERSONAL_TAG);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(adapter);
        fg = adapter.getItem(0);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mRadioGroup.getChildAt(position).performClick();
                fg = adapter.getItem(position);
//                if (position==0) {
//                    mViewPager.setPagingEnabled(false);//是否支持手势滑动
//                } else {
//                    mViewPager.setPagingEnabled(true);//是否支持手势滑动
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initBottomBar() {
        mRadioGroup = (RadioGroup) findViewById(R.id.tabs_rg);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_message:
                        mViewPager.setCurrentItem(0,false);
                        fg = adapter.getItem(0);
                        break;
                    case R.id.tab_service:
                        mViewPager.setCurrentItem(1,false);
                        fg = adapter.getItem(1);
                        break;
                    case R.id.tab_manager:
                        mViewPager.setCurrentItem(2,false);
                        fg = adapter.getItem(2);
                        break;
                    case R.id.tab_data:
                        mViewPager.setCurrentItem(3,false);
                        fg = adapter.getItem(3);
                        break;
                    case R.id.tab_personal:
                        mViewPager.setCurrentItem(4,false);
                        fg = adapter.getItem(4);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

//        if(fg instanceof NewsFragment){
//            boolean b = NewsFragment.onKeyDown(keyCode, event);
//            if (b) {
//                return true;
//            } else {
//                return super.onKeyDown(keyCode, event);
//            }
//        } else {
            return super.onKeyDown(keyCode, event);
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (fg instanceof ManagerFragment) {
            /*然后在碎片中调用重写的onActivityResult方法*/
            fg.onActivityResult(requestCode, resultCode, data);
        }
    }
}

