package com.atman.jishang.ui.marketing.fullcut;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.adapter.FullCutAllAdapter;
import com.atman.jishang.net.model.AddFullCutResuiltModel;
import com.atman.jishang.net.model.DeleteFullCutByIdModel;
import com.atman.jishang.net.model.GetFullCutListModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.widget.YLBDialog;
import com.corelib.util.LogUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 满减主界面
 * 作者 tangbingliang
 * 时间 16/5/17 09:46
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class FullCutActivity extends SimpleTitleBarActivity
        implements FullCutAllAdapter.IonSlidingViewClickListener {

    @Bind(R.id.tab_fullcut_notstart)
    RadioButton tabFullcutNotstart;
    @Bind(R.id.tab_fullcut_now)
    RadioButton tabFullcutNow;
    @Bind(R.id.tab_fullcut_expired)
    RadioButton tabFullcutExpired;
    @Bind(R.id.tab_fullcut)
    RadioGroup tabFullcut;
    @Bind(R.id.pull_refresh_recycler)
    PullToRefreshRecyclerView pullRefreshRecycler;
    @Bind(R.id.fullcut_empty_iv)
    ImageView fullcutEmptyIv;

    private Context mContext = FullCutActivity.this;
    private int mPage = 0;
    private int mCount = 10;
    private String mState = "2";//满减活动的状态（1：未开始，2：进行中，3：已结束）

    private List<GetFullCutListModel.BodyEntity> mAllFullCutList = new ArrayList<>();

    private FullCutAllAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private int mDeleteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullcut);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdapter!=null) {
            mAdapter.clearData();
        }
        mPage = 0;
        doHttp(true);
    }

    private void doHttp(boolean b) {
        getDataManager().getFullCutList(mState, mPage, mCount, GetFullCutListModel.class, b);
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle(R.string.fullcut_title);
        initRefreshView(PullToRefreshBase.Mode.BOTH, pullRefreshRecycler);
        initBottomBar();
        initListView();
    }

    private void initBottomBar() {
        tabFullcut.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mAdapter.clearData();
                switch (checkedId) {
                    case R.id.tab_fullcut_notstart:
                        tabFullcutNotstart.performClick();
                        mState = "1";
                        mAdapter.setState(mState);
                        doHttp(true);
                        break;
                    case R.id.tab_fullcut_now:
                        tabFullcutNow.performClick();
                        mState = "2";
                        mAdapter.setState(mState);
                        doHttp(true);
                        break;
                    case R.id.tab_fullcut_expired:
                        tabFullcutExpired.performClick();
                        mState = "3";
                        mAdapter.setState(mState);
                        doHttp(true);
                        break;
                }
            }
        });
    }

    private void initListView() {
        mAdapter = new FullCutAllAdapter(mContext, getmWidth(), mState, this);

//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流

        mRecyclerView = pullRefreshRecycler.getRefreshableView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));//这里用线性显示 类似于listview
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof GetFullCutListModel) {
            GetFullCutListModel mGetFullCutListModel = (GetFullCutListModel) response;
            if (mGetFullCutListModel.getResult().equals("1")) {
                mAllFullCutList = mGetFullCutListModel.getBody();
                LogUtils.e("mAllFullCutList.size():" + mAllFullCutList.size());
                if (mAllFullCutList == null || mAllFullCutList.size() == 0) {
                    onLoad(PullToRefreshBase.Mode.PULL_FROM_START, pullRefreshRecycler);
                    if (mAdapter!=null && mAdapter.getItemCount() == 0) {
                        fullcutEmptyIv.setVisibility(View.VISIBLE);
                        pullRefreshRecycler.setVisibility(View.GONE);
                    } else {
                        showToast("没有更多");
                    }
                } else {
                    fullcutEmptyIv.setVisibility(View.GONE);
                    pullRefreshRecycler.setVisibility(View.VISIBLE);
                    onLoad(PullToRefreshBase.Mode.BOTH, pullRefreshRecycler);
                }
                mAdapter.addData(mAllFullCutList);
            } else {
                showToast("全部满减活动获取失败");
            }
        } else if (response instanceof DeleteFullCutByIdModel) {
            DeleteFullCutByIdModel mDeleteFullCutByIdModel = (DeleteFullCutByIdModel) response;
            if (mDeleteFullCutByIdModel.getResult().equals("1")) {
                mAdapter.removeData(mDeleteId);
                showEmptyView();
            }
            showToast(mDeleteFullCutByIdModel.getBody().getMessage());
        } else if (response instanceof AddFullCutResuiltModel) {
            AddFullCutResuiltModel mAddFullCutResuiltModel = (AddFullCutResuiltModel) response;
            if (mAddFullCutResuiltModel.getResult().equals("1")) {
                showToast("结束成功");
                mAdapter.removeData(mDeleteId);
            } else {
                showToast(mAddFullCutResuiltModel.getBody().getMessage());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.fullcut_add_ll,R.id.fullcut_empty_iv})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fullcut_add_ll:
                startActivity(new Intent(mContext, AddFullCutActivity.class));
                break;
            case R.id.fullcut_empty_iv:
                mPage = 0;
                doHttp(true);
                break;
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mPage = 0;
        mAdapter.clearData();
        doHttp(false);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        mPage += 1;
        doHttp(false);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        onLoad(PullToRefreshBase.Mode.BOTH, pullRefreshRecycler);
        showEmptyView();
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(FullCutDetailsActivity.buildIntent(mContext, mAdapter.getItemById(position).getId(), mState));
    }

    @Override
    public void onDeleteBtnCilck(View view, final int position) {
        switch (view.getId()) {
            case R.id.tv_edit:
                mDeleteId = position;
                getDataManager().finishFullCutById(mAdapter.getItemById(position).getId(), AddFullCutResuiltModel.class, true);
                break;
            case R.id.tv_delete:
                String str = "";
                if (mState.equals("1")) {
                    str = "您确定要删除该未开始的活动吗？";
                } else if (mState.equals("2")) {
                    str = "您确定要删除该进行中的活动吗？";
                } else if (mState.equals("3")) {
                    str = "您确定要删除该已结束的活动吗？";
                }
                YLBDialog.Builder builder = new YLBDialog.Builder(mContext);
                builder.setMessage(str);
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mDeleteId = position;
                        getDataManager().deleteFullCutById(mAdapter.getItemById(position).getId(), DeleteFullCutByIdModel.class, true);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
        }
    }

    private void showEmptyView() {
        if (mAdapter.getItemCount()==0) {
            fullcutEmptyIv.setVisibility(View.VISIBLE);
            pullRefreshRecycler.setVisibility(View.GONE);
        } else {
            fullcutEmptyIv.setVisibility(View.GONE);
            pullRefreshRecycler.setVisibility(View.VISIBLE);
        }
    }
}
