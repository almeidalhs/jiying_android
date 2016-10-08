package com.atman.jishang.ui.manager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.DragListAdapter;
import com.atman.jishang.adapter.OtherListAdapter;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.model.HomeGridViewDataModel;
import com.atman.jishang.net.model.HomeListPostModel;
import com.atman.jishang.net.model.UpdateListOrderModel;
import com.corelib.util.LogUtils;
import com.google.gson.Gson;
import com.mobeta.android.draglistview.DragSortListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述 频道编辑
 * 作者 tangbingliang
 * 时间 16/4/21 15:37
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class ChannelEditorActivity extends SimpleTitleBarActivity implements AdapterInterface{

    @Bind(R.id.channeleditor_listview)
    DragSortListView channeleditorListview;
    @Bind(R.id.channeleditor_other_listview)
    ListView channeleditorOtherListview;

    private Context mContext = ChannelEditorActivity.this;
    private DragListAdapter mAdapter;
    private HomeGridViewDataModel mHomeGridViewDataModel;
    private TextView mTitleRightTx;
    private OtherListAdapter mOtherAdapter;

    private List<HomeGridViewDataModel.BodyEntity> dataAlreadyList = new ArrayList<>();
    private List<HomeGridViewDataModel.BodyEntity> dataOtherList = new ArrayList<>();
    private HomeGridViewDataModel.BodyEntity dataMore = new HomeGridViewDataModel.BodyEntity();
    private List<HomeListPostModel> homeListPostModel = new ArrayList<HomeListPostModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channeleditor);
        ButterKnife.bind(this);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        setToolbarTitle(R.string.channel_title);
        mTitleRightTx = showRightTV(R.string.channel_title_right_normal);
        mTitleRightTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter.isHidden) {
                    mTitleRightTx.setText(getResources().getString(R.string.channel_title_right_ing));
                    mAdapter.setHidden(false);
                } else {
                    mTitleRightTx.setText(getResources().getString(R.string.channel_title_right_normal));
                    mAdapter.setHidden(true);

                    upDateList();
                }
            }
        });
    }

    public void upDateList() {
        LogUtils.e(">>>mAdapter.getCount():"+mAdapter.getCount());
        homeListPostModel.clear();
        for (int i = 0; i < mAdapter.getCount(); i++) {
            HomeGridViewDataModel.BodyEntity dataSource = (HomeGridViewDataModel.BodyEntity) mAdapter.getItem(i);
            HomeListPostModel temp = new HomeListPostModel(dataSource.getConsoleId()
                    , i, dataSource.getUcConsoleSort(), dataSource.getUcState());
            homeListPostModel.add(temp);
        }
        HomeListPostModel temp_more = new HomeListPostModel(dataMore.getConsoleId()
                , mAdapter.getCount(), dataMore.getUcConsoleSort(), dataMore.getUcState());
        homeListPostModel.add(temp_more);
        String str = new Gson().toJson(homeListPostModel);
        LogUtils.e(">>>str:"+str);
        getDataManager().updateListOrder(str, UpdateListOrderModel.class, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        getDataManager().getHomeGridView(HomeGridViewDataModel.class, true);
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof HomeGridViewDataModel) {
            mHomeGridViewDataModel = (HomeGridViewDataModel) response;
            if (mHomeGridViewDataModel.getResult().equals("1")) {
                initListView();
            } else {
                showToast("功能列表获取失败");
            }
        } else if (response instanceof UpdateListOrderModel) {
            UpdateListOrderModel mUpdateListOrderModel = (UpdateListOrderModel) response;
            if (mUpdateListOrderModel.getResult().equals("1")) {
                showToast("更新成功");
            } else {
                showToast("更新失败");
            }
        }
    }

    private void initListView() {
        for (int i = 0; i < mHomeGridViewDataModel.getBody().size(); i++) {
            HomeGridViewDataModel.BodyEntity temp = mHomeGridViewDataModel.getBody().get(i);
            if (temp.getUcState() == 1) {
                dataAlreadyList.add(temp);
            } else if (temp.getUcState() == 0) {
                dataOtherList.add(temp);
            } else {
                dataMore = temp;
            }
        }

        //得到滑动listview并且设置监听器。
        channeleditorListview.setDropListener(onDrop);
        channeleditorListview.setRemoveListener(onRemove);
        channeleditorListview.setDragEnabled(true); //设置是否可拖动。

        mAdapter = new DragListAdapter(mContext, getImageLoader(), dataAlreadyList,this);
        channeleditorListview.setAdapter(mAdapter);

        mOtherAdapter = new OtherListAdapter(mContext, getImageLoader(), dataOtherList, this);
        channeleditorOtherListview.setAdapter(mOtherAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //监听器在手机拖动停下的时候触发
    private DragSortListView.DropListener onDrop = new DragSortListView.DropListener() {
        @Override
        public void drop(int from, int to) {//from to 分别表示 被拖动控件原位置 和目标位置
            if (from != to) {
                HomeGridViewDataModel.BodyEntity item = (HomeGridViewDataModel.BodyEntity) mAdapter.getItem(from);//得到listview的适配器
                mAdapter.remove(from);//在适配器中”原位置“的数据。
                mAdapter.insert(item, to);//在目标位置中插入被拖动的控件。
            }
        }
    };

    //删除监听器，点击左边差号就触发。删除item操作。
    private DragSortListView.RemoveListener onRemove = new DragSortListView.RemoveListener() {
        @Override
        public void remove(int which) {
            mAdapter.remove(which);
        }
    };

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.click_remove:
                mOtherAdapter.addItemToEnd(mAdapter.removeByPosition(position));
                channeleditorOtherListview.setSelection(channeleditorListview.getAdapter().getCount()-1);
                upDateList();
                break;
            case R.id.item_otherlistview_rl:
            case R.id.item_otherlistview_add:
                mAdapter.addItemToEnd(mOtherAdapter.removeByPosition(position));
                channeleditorListview.setSelection(channeleditorListview.getAdapter().getCount()-1);
                upDateList();
                break;
        }
    }
}
