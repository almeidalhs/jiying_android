package com.atman.jishang.ui.member;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.atman.jishang.R;
import com.atman.jishang.adapter.MyFragmentAdapter;
import com.atman.jishang.interfaces.IndustryTitleConfigInterface;
import com.atman.jishang.net.model.GetIndustryTitleConfigModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.corelib.util.LogUtils;
import com.corelib.widget.NoSwipeViewPager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/25 10:23
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AddMemberActivity extends SimpleTitleBarActivity {

    @Bind(R.id.addmenber_tab_left)
    RadioButton addmenberTabLeft;
    @Bind(R.id.addmenber_tab_right)
    RadioButton addmenberTabRight;
    @Bind(R.id.addmenber_tabs_rg)
    RadioGroup addmenberTabsRg;
    @Bind(R.id.addmenber_viewpager)
    NoSwipeViewPager addmenberViewpager;

    private Context mContext = AddMemberActivity.this;
    private int mSelectView = 0;
    private Fragment fg;
    private MyFragmentAdapter adapter;
    private final String LEFT_TAG = "on_left";
    private final String RIGHT_TAG = "on_right";
    private AddSingleMemberFragment oneFragment;
    private AddBatchMemberFragment twoFragment;

    public static boolean isSuccess = false;

    private String baseStr = "会员";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmember);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);

        if (BaiYeBaseApplication.mGetIndustryTitleConfigModel!=null) {
            List<GetIndustryTitleConfigModel.BodyBean> temp = BaiYeBaseApplication.mGetIndustryTitleConfigModel.getBody();
            for (int i=0;i< temp.size();i++) {
                if (temp.get(i).getPageNum() == IndustryTitleConfigInterface.ConfigMemberId
                        && temp.get(i).getTitle()!=null) {
                    baseStr = BaiYeBaseApplication.mGetIndustryTitleConfigModel.getBody().get(i).getTitle();
                }
            }
        }

        addmenberTabLeft.setText("增加"+baseStr);

        setToolbarTitle("新增"+baseStr);
        getLlBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        initViewpager();
        initBottomBar();
    }

    private void initViewpager() {
        addmenberViewpager.setPagingEnabled(true);//是否支持手势滑动
        adapter = new MyFragmentAdapter(getSupportFragmentManager());

        oneFragment = new AddSingleMemberFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLES", getResources().getString(R.string.goodsmanagement_onsale_tx));
        bundle.putString("baseStr", baseStr);
        oneFragment.setArguments(bundle);
        adapter.addFragment(oneFragment, LEFT_TAG);

        twoFragment = new AddBatchMemberFragment();
        Bundle bundle_two = new Bundle();
        bundle_two.putString("TITLES", getResources().getString(R.string.goodsmanagement_undershelf_tx));
        bundle.putString("baseStr", baseStr);
        twoFragment.setArguments(bundle_two);
        adapter.addFragment(twoFragment, RIGHT_TAG);

        addmenberViewpager.setOffscreenPageLimit(2);
        addmenberViewpager.setAdapter(adapter);
        addmenberViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mSelectView = position;
                addmenberTabsRg.getChildAt(position).performClick();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initBottomBar() {
        addmenberTabsRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.addmenber_tab_left:
                        mSelectView = 0;
                        selelctView();
                        break;
                    case R.id.addmenber_tab_right:
                        mSelectView = 1;
                        selelctView();
                        break;
                }
            }
        });
    }

    private void selelctView() {
        addmenberViewpager.setCurrentItem(mSelectView, false);
        fg = adapter.getItem(mSelectView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment*/
        Fragment f = null;
        if (mSelectView == 0) {
            f = oneFragment;
        } else {
            f = twoFragment;
        }
        if (f != null) {
            /*然后在碎片中调用重写的onActivityResult方法*/
            f.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 返回键
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void back() {
        if (isSuccess) {
            isSuccess = false;
            Intent intent=new Intent();
            setResult(RESULT_OK, intent);
        }
        finish();
    }
}
