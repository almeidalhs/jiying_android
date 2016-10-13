package com.atman.jishang.ui.member;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.adapter.MemberListAdapter;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.interfaces.IndustryTitleConfigInterface;
import com.atman.jishang.net.GetMemberListModel;
import com.atman.jishang.net.model.GetIndustryTitleConfigModel;
import com.atman.jishang.net.model.MemberFilterModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.UiHelper;
import com.corelib.util.LogUtils;
import com.corelib.widget.MyCleanEditText;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/24 15:15
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MemberListActivity extends SimpleTitleBarActivity implements AdapterInterface {

    @Bind(R.id.memberlist_search_et)
    MyCleanEditText memberlistSearchEt;
    @Bind(R.id.memberlist_total_tx)
    TextView memberlistTotalTx;
    @Bind(R.id.pullToRefreshListView)
    PullToRefreshListView pullToRefreshListView;
    @Bind(R.id.memberlist_allselect_iv)
    ImageView memberlistAllselectIv;
    @Bind(R.id.memberlist_message_root)
    LinearLayout memberlistMessageRoot;
    @Bind(R.id.memberlist_condition_tx)
    TextView memberlistConditionTx;
    @Bind(R.id.memberlist_add_tx)
    TextView memberlistAddTx;
    @Bind(R.id.memberlist_condition_ll)
    LinearLayout memberlistConditionLl;

    private Context mContext = MemberListActivity.this;
    private View mEmpty;
    private TextView mEmptyTX;
    private MemberListAdapter mAdapter;

    private String queryParam = "";
    public static List<MemberFilterModel> listFilter = new ArrayList<>();
    private GetMemberListModel mGetMemberListModel;
    private List<GetMemberListModel.BodyEntity.DataListEntity> mListData = new ArrayList<>();
    private int page = 1;
    private int pageSize = 10;

    private String baseStr = "会员";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberlist);
        ButterKnife.bind(this);
        listFilter.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayTopCondition();
    }

    private void displayTopCondition() {
        if (listFilter.size()>0) {
            memberlistSearchEt.setVisibility(View.GONE);
            memberlistConditionLl.setVisibility(View.VISIBLE);
            String str = "";
            for (int i=0;i<listFilter.size();i++) {
                if (i!=0) {
                    str += ",";
                }
                str += listFilter.get(i).getParamkey();
            }
            memberlistConditionTx.setText(str);
        } else {
            memberlistSearchEt.setVisibility(View.VISIBLE);
            memberlistConditionLl.setVisibility(View.GONE);
        }
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        if (BaiYeBaseApplication.mGetIndustryTitleConfigModel!=null) {
            List<GetIndustryTitleConfigModel.BodyBean> temp = BaiYeBaseApplication.mGetIndustryTitleConfigModel.getBody();
            for (int i=0;i< temp.size();i++) {
                if (temp.get(i).getPageNum() == IndustryTitleConfigInterface.ConfigMemberId
                        && temp.get(i).getTitle()!=null) {
                    baseStr = temp.get(i).getTitle();
                }
            }
        }

        memberlistSearchEt.setHint("输入"+baseStr+"姓名/电话/性别等等");
        memberlistAddTx.setText("新增"+baseStr);

        setToolbarTitle(baseStr+getResources().getString(R.string.memberlist_title));
        showRightTV(R.string.memberlist_title_topright).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(mContext, MemberConditionFilterActivity.class), 999);
            }
        });
        memberlistTotalTx.setText("共0位"+baseStr);
        memberlistSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = s == null ? null : s.toString();
                if (content == null) {
                    queryParam = "";
                } else {
                    queryParam = s.toString().trim();
                }
                page = 1;
                mAdapter.clearData();
                dohttp(false);
            }
        });

        initListView();
    }

    private void initListView() {
        initRefreshView(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
        mEmpty = LayoutInflater.from(mContext).inflate(R.layout.part_list_empty, null);
        mEmptyTX = (TextView) mEmpty.findViewById(R.id.empty_list_tx);
        mEmptyTX.setText("暂无"+baseStr);

        mAdapter = new MemberListAdapter(mContext, this);
        pullToRefreshListView.setEmptyView(mEmpty);
        pullToRefreshListView.setAdapter(mAdapter);
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivityForResult(MemberDetailsActivity.buildIntent(mContext, mAdapter.getItem(position - 1).getId()), 100);
            }
        });
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        dohttp(true);
    }

    private void dohttp(boolean b) {
        getDataManager().getStoreMember(queryParam, listFilter, page, pageSize, GetMemberListModel.class, b);
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof GetMemberListModel) {
            mGetMemberListModel = (GetMemberListModel) response;
            if (mGetMemberListModel.getResult().equals("1")) {
                mListData = mGetMemberListModel.getBody().getDataList();
                if (mListData == null || mListData.size() == 0) {
                    if (mAdapter!=null && mAdapter.getCount()>0) {
                        showToast("没有更多");
                    }
                    memberlistTotalTx.setText("共"+mAdapter.getCount()+"位"+baseStr);
                    onLoad(PullToRefreshBase.Mode.PULL_FROM_START, pullToRefreshListView);
                } else {
                    onLoad(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
                    mAdapter.setmListData(mListData);
                    memberlistTotalTx.setText("共"+mAdapter.getCount()+"位"+baseStr);
                    updataUi();
                }
            } else {
                showToast("获取"+baseStr+"列表失败");
            }
        }
    }

    private void updataUi() {
        memberlistTotalTx.setText("共" + mGetMemberListModel.getBody().getDataSize() + "位"+baseStr);
        isHitButton();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        if (page > 1) {
            page -= 1;
        }
        onLoad(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        super.onPullUpToRefresh(refreshView);
        page += 1;
        dohttp(false);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        super.onPullDownToRefresh(refreshView);
        page = 1;
        mAdapter.clearData();
        dohttp(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.memberlist_add_tx, R.id.memberlist_message_ll, R.id.memberlist_allselect_iv
            , R.id.memberlist_condition_delete_tx})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.memberlist_condition_delete_tx:
                listFilter.clear();
                displayTopCondition();
                page = 1;
                mAdapter.clearData();
                dohttp(false);
                break;
            case R.id.memberlist_add_tx:
                startActivityForResult(new Intent(mContext, AddMemberActivity.class), 1000);
                break;
            case R.id.memberlist_message_ll:
                if (!UiHelper.isTabletDevice(mContext)) {
                    sendSMS("我发现个帮你做生意的应用，非常好用，我现在生意像飞一样！");
                } else {
                    showToast("您的设备无法访问通讯录");
                }
                break;
            case R.id.memberlist_allselect_iv:
                if (mAdapter.isSelectedAll()) {
                    mAdapter.setSelected(false);
                    memberlistAllselectIv.setImageResource(R.mipmap.member_unselect_all);
                } else {
                    mAdapter.setSelected(true);
                    memberlistAllselectIv.setImageResource(R.mipmap.member_select_all);
                }

                isHitButton();
                break;
        }
    }

    private void sendSMS(String smsBody) {
        String str = "";
        int num = 0;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            if (mAdapter.getItem(i).isSelect()) {
                num += 1;
                if (mAdapter.getItem(i).getMobile() != null
                        && !mAdapter.getItem(i).getMobile().trim().isEmpty()) {
                    if (i != 0) {
                        str += ",";
                    }
                    str += mAdapter.getItem(i).getMobile().trim();
                }
            }
        }
        if (str.equals("")) {
            if (num != 0) {
                showToast("选择的"+baseStr+"没有手机号");
            } else {
                showToast("请选择要发送短信的"+baseStr);
            }
            return;
        }
        UiHelper.sendSMS(mContext, "", str);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.item_memberlist_select_iv:
                if (mAdapter.setSelectById(position)) {//全选
                    memberlistAllselectIv.setImageResource(R.mipmap.member_select_all);
                } else {//非全选
                    memberlistAllselectIv.setImageResource(R.mipmap.member_unselect_all);
                }

                isHitButton();
                break;
        }
    }

    private void isHitButton() {
        int num = 0;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            if (mAdapter.getItem(i).isSelect()) {
                num += 1;
            }
        }
        if (num != 0) {
            memberlistMessageRoot.setVisibility(View.VISIBLE);
        } else {
            memberlistMessageRoot.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 999) {//筛选
            String result = data.getStringExtra("params");
            page = 1;
            if (mAdapter != null) {
                mAdapter.clearData();
            }
            dohttp(true);
        } else if (requestCode == 1000 || requestCode == 100) {//添加和编辑
            page = 1;
            if (mAdapter != null) {
                mAdapter.clearData();
            }
            dohttp(true);
        }
    }
}
