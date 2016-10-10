package com.atman.jishang.ui.member;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.MemberConditionFilterAdapter;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.model.GetMemberFilterModel;
import com.atman.jishang.net.model.MemberFilterModel;
import com.atman.jishang.ui.MainActivity;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.ui.personal.loginandregister.LoginActivity;
import com.corelib.util.LogUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/27 11:09
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MemberConditionFilterActivity extends SimpleTitleBarActivity implements AdapterInterface{

    @Bind(R.id.memberfilter_listview)
    ExpandableListView memberfilterListview;

    private MemberConditionFilterAdapter mAdapter;
    private Context mContext = MemberConditionFilterActivity.this;
    private GetMemberFilterModel mGetMemberFilterModel;
    private List<MemberFilterModel> listFilter = new ArrayList<>();

    private int from = 1;
    private int num = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberconditionfilter);
        ButterKnife.bind(this);
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle("会员筛选");
        getLlBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    private void back() {
        Intent intent=new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        getDataManager().getMenberFilterData(from, num, GetMemberFilterModel.class, true);
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof GetMemberFilterModel) {
            mGetMemberFilterModel = (GetMemberFilterModel) response;
            if (mGetMemberFilterModel.getResult().equals("1")) {
                initListView();
            } else {
                showToast("会员筛选条件获取失败");
            }
        }
    }

    private void initListView() {
        memberfilterListview.setGroupIndicator(null);
        mAdapter = new MemberConditionFilterAdapter(mContext, mGetMemberFilterModel.getBody(), this);
        memberfilterListview.setAdapter(mAdapter);
        for(int i = 0; i < mAdapter.getGroupCount(); i++){
            memberfilterListview.expandGroup(i);
        }
        memberfilterListview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.memberfilter_reset_ll, R.id.memberfilter_ok_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.memberfilter_reset_ll:
                MemberListActivity.listFilter.clear();
                mAdapter.clearParms();
                break;
            case R.id.memberfilter_ok_ll:
                listFilter.clear();
                List<GetMemberFilterModel.BodyEntity> mSList = mAdapter.getList();
                for (int i=0;i<mSList.size();i++) {
                    LogUtils.e("mSList.get(i).getChildSelectId():"+mSList.get(i).getChildSelectId());
                    if (mSList.get(i).getChildSelectId()!=-1) {
                        listFilter.add(new MemberFilterModel(mSList.get(i).getId(),mSList.get(i).getChildSelectStr()));
                    }
                }
                MemberListActivity.listFilter = listFilter;
                Intent intent=new Intent();
                intent.putExtra("params", new Gson().toJson(listFilter));
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.item_group_refresh_tx:
                from += 1;
                doInitBaseHttp();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 返回键
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
