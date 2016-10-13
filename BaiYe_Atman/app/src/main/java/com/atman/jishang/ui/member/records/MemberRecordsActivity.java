package com.atman.jishang.ui.member.records;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.adapter.MemberRecordsAdapter;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.model.DeleteMemberRecordModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.net.model.GetMemberRecordModel;
import com.atman.jishang.widget.YLBDialog;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述 会员消费记录
 * 作者 tangbingliang
 * 时间 16/5/30 11:07
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MemberRecordsActivity extends SimpleTitleBarActivity implements AdapterInterface {

    @Bind(R.id.memberrecord_listview)
    PullToRefreshExpandableListView memberrecordListview;

    private Context mContext = MemberRecordsActivity.this;
    private int mMemberId;
    private int page = 1;
    private int pageSize = 10;
    private GetMemberRecordModel mGetMemberRecordModel;
    private View mEmpty;
    private TextView mEmptyTX;
    private MemberRecordsAdapter mAdapter;
    private ExpandableListView mListView;
    private int deleteId = -1;
    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberrecords);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context, int memberId, String mobile) {
        Intent intent = new Intent(context, MemberRecordsActivity.class);
        intent.putExtra("memberId", memberId);
        intent.putExtra("mobile", mobile);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMemberId = getIntent().getIntExtra("memberId", -1);
        mobile = getIntent().getStringExtra("mobile");
        page = 1;
        if (mAdapter!=null) {
            mAdapter.clearData();
        }
        dohttp(true);
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle("会员消费记录");
        getIvRightOk().setImageResource(R.mipmap.top_right_add_img);
        showRightLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddMemberRecordsActivity.buildIntent(mContext, mMemberId, mobile));
            }
        });
        initListView();
    }

    private void initListView() {
        initRefreshView(PullToRefreshBase.Mode.BOTH, memberrecordListview);
        mEmpty = LayoutInflater.from(mContext).inflate(R.layout.part_list_empty, null);
        mEmptyTX = (TextView) mEmpty.findViewById(R.id.empty_list_tx);
        mEmptyTX.setText("暂无消费记录");

        mAdapter = new MemberRecordsAdapter(mContext, this);
        memberrecordListview.setEmptyView(mEmpty);
        mListView = memberrecordListview.getRefreshableView();
        mListView.setGroupIndicator(null);
        mListView.setAdapter(mAdapter);
        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
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
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof GetMemberRecordModel) {
            mGetMemberRecordModel = (GetMemberRecordModel) response;
            if (mGetMemberRecordModel.getResult().equals("1")) {
                if (mGetMemberRecordModel.getBody() == null || mGetMemberRecordModel.getBody().size() == 0) {
                    onLoad(PullToRefreshBase.Mode.PULL_FROM_START, memberrecordListview);
                    if (mAdapter!=null && mAdapter.getGroupCount()>0) {
                        showToast("没有更多");
                    }
                } else {
                    onLoad(PullToRefreshBase.Mode.BOTH, memberrecordListview);
                    mAdapter.setmBodyList(mGetMemberRecordModel.getBody());
                    for(int i = 0; i < mAdapter.getGroupCount(); i++){
                        mListView.expandGroup(i);
                    }
                }
            } else {
                showToast("会员消费记录获取失败");
            }
        } else if (response instanceof DeleteMemberRecordModel) {
            DeleteMemberRecordModel mDeleteMemberRecordModel = (DeleteMemberRecordModel) response;
            if (mDeleteMemberRecordModel.getResult().equals("1")) {
                mAdapter.clearItemById(deleteId);
            }
            showToast(mDeleteMemberRecordModel.getBody().getMessage());
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        if (page > 0) {
            page -= 1;
        }
        onLoad(PullToRefreshBase.Mode.BOTH, memberrecordListview);
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

    private void dohttp(boolean b) {
        getDataManager().getMemberRecord(mMemberId, page, pageSize, GetMemberRecordModel.class, b);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(View view, final int position) {
        switch (view.getId()) {
            case R.id.item_memberrecord_delete:
                YLBDialog.Builder builder = new YLBDialog.Builder(MemberRecordsActivity.this);
                builder.setTitle("请问为什么要删除此订单？");
                builder.setMessage("订单错误：删除时，优惠券将返还给会员，可继续使用\n" +
                        "直接删除：删除后，优惠券当已使用处理，无法再使用");
                builder.setPositiveButton("订单错误", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteId = position;
                        getDataManager().deleteMemberRecord(mAdapter.getGroup(position).getId()
                                , 0, DeleteMemberRecordModel.class, true);
                    }
                });
                builder.setNeutralButton("直接删除", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteId = position;
                        getDataManager().deleteMemberRecord(mAdapter.getGroup(position).getId()
                                , 1, DeleteMemberRecordModel.class, true);
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
}
