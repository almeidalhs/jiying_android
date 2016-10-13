package com.atman.jishang.ui.service.callservice;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.CallServiceAdapter;
import com.atman.jishang.interfaces.CreateQRCodeInterface;
import com.atman.jishang.net.model.AddCallServiceModel;
import com.atman.jishang.net.model.CallServiceModel;
import com.atman.jishang.net.model.CommonStringModel;
import com.atman.jishang.net.model.EditCallServiceModel;
import com.atman.jishang.net.model.ModuleListModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.ui.service.code.CreateQRCodeActivity;
import com.atman.jishang.widget.YLBDialog;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tangbingliang on 16/10/13.
 */

public class CallServiceActivity extends SimpleTitleBarActivity
        implements CallServiceAdapter.IonSlidingViewClickListener {

    @Bind(R.id.callservice_name_et)
    EditText callserviceNameEt;
    @Bind(R.id.callservice_remark_et)
    EditText callserviceRemarkEt;
    @Bind(R.id.callservice_save_bt)
    Button callserviceSaveBt;
    @Bind(R.id.callservice_empty_tx)
    TextView callserviceEmptyTx;
    @Bind(R.id.pull_refresh_recycler)
    PullToRefreshRecyclerView pullRefreshRecycler;
    @Bind(R.id.callservice_notempty_ll)
    LinearLayout callserviceNotemptyLl;

    private Context mContext = CallServiceActivity.this;
    private CallServiceModel mCallServiceModel;
    private CallServiceAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private int mPosition;
    private int mEditServiceId = -1;

    private int Id;
    private int moduleId;
    private int moduleStatus;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callservice);
        ButterKnife.bind(this);
    }

    public static Intent bulidIntent(Context context, String title, int Id, int moduleId, int moduleStatus) {
        Intent intent = new Intent(context, CallServiceActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("Id", Id);
        intent.putExtra("moduleId", moduleId);
        intent.putExtra("moduleStatus", moduleStatus);
        return intent;
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);

        moduleId = getIntent().getIntExtra("moduleId", -1);
        Id = getIntent().getIntExtra("Id", -1);
        moduleStatus = getIntent().getIntExtra("moduleStatus", -1);
        title = getIntent().getStringExtra("title");

        setToolbarTitle(title);
        showRightTV("制码").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallServiceModel.getBody().size() == 0) {
                    showToast("尚无设置呼叫服务");
                    return;
                }
                List<ModuleListModel> listModel = new ArrayList<>();
                ModuleListModel temp = new ModuleListModel(title, Id, moduleStatus);
                listModel.add(temp);
                startActivity(CreateQRCodeActivity.buildIntent(mContext,
                        CreateQRCodeInterface.QRCodeTypeCallService
                        , CreateQRCodeInterface.SingleType.All, listModel));
            }
        });

        initListView();
    }

    private void initListView() {
        mAdapter = new CallServiceAdapter(mContext, getmWidth(), this);
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
        getDataManager().getCallServiceList(moduleId, CallServiceModel.class, true);

        initRefreshView(PullToRefreshBase.Mode.DISABLED, pullRefreshRecycler);
        mRecyclerView = pullRefreshRecycler.getRefreshableView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));//这里用线性显示 类似于listview
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof CallServiceModel) {
            mCallServiceModel = (CallServiceModel) response;
            if (mCallServiceModel.getBody().size() == 0) {
                callserviceEmptyTx.setVisibility(View.VISIBLE);
                callserviceNotemptyLl.setVisibility(View.GONE);
            } else {
                callserviceEmptyTx.setVisibility(View.GONE);
                callserviceNotemptyLl.setVisibility(View.VISIBLE);
            }
            mAdapter.setmList(mCallServiceModel.getBody());
        } else if (response instanceof AddCallServiceModel) {
            AddCallServiceModel mAddCallServiceModel = (AddCallServiceModel) response;
            if (mAddCallServiceModel.getResult().equals("1")) {
                showToast("添加成功");
                CallServiceModel.BodyBean temp = new CallServiceModel.BodyBean();
                temp.setId(mAddCallServiceModel.getBody().getId());
                temp.setModuleClassify(mAddCallServiceModel.getBody().getModuleClassify());
                temp.setStoreId(mAddCallServiceModel.getBody().getStoreId());
                temp.setModuleName(mAddCallServiceModel.getBody().getModuleName());
                temp.setModuleDesc(mAddCallServiceModel.getBody().getModuleDesc());
                temp.setParentId(mAddCallServiceModel.getBody().getParentId());
                mAdapter.addItem(temp);
                callserviceEmptyTx.setVisibility(View.GONE);
                callserviceNotemptyLl.setVisibility(View.VISIBLE);
                callserviceNameEt.setText("");
                callserviceRemarkEt.setText("");
            }
        } else if (response instanceof CommonStringModel) {
            CommonStringModel mCommonModel = (CommonStringModel) response;
            if (mCommonModel.getResult().equals("1")) {
                showToast("删除成功");
                mAdapter.deleteItemById(mPosition);
                if (mAdapter.getItemCount() == 0) {
                    callserviceEmptyTx.setVisibility(View.VISIBLE);
                    callserviceNotemptyLl.setVisibility(View.GONE);
                } else {
                    callserviceEmptyTx.setVisibility(View.GONE);
                    callserviceNotemptyLl.setVisibility(View.VISIBLE);
                }
            } else {
                showToast("删除失败");
            }
        } else if (response instanceof EditCallServiceModel) {
            EditCallServiceModel mEditCallServiceModel = (EditCallServiceModel) response;
            if (mEditCallServiceModel.getResult().equals("1")) {
                showToast("修改成功");
                mAdapter.updataItem(mEditCallServiceModel.getBody(), mPosition);
                mEditServiceId = -1;
                callserviceNameEt.setText("");
                callserviceRemarkEt.setText("");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.callservice_save_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.callservice_save_bt:
                if (checkEdit()) {
                    showToast("服务名称或者服务描述没有填写哟");
                    return;
                }
                if (mEditServiceId == -1) {
                    getDataManager().addCallService(callserviceNameEt.getText().toString().trim()
                            , callserviceRemarkEt.getText().toString(), AddCallServiceModel.class, true);
                } else {
                    getDataManager().editCallService(mEditServiceId, callserviceNameEt.getText().toString().trim()
                            , callserviceRemarkEt.getText().toString(), EditCallServiceModel.class, true);
                }
                break;
        }
    }

    private boolean checkEdit() {
        if (callserviceNameEt.getText().toString().trim().isEmpty()) {
            return true;
        }
        if (callserviceRemarkEt.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {
        mPosition = position;
        switch (view.getId()) {
            case R.id.tv_code:
                List<ModuleListModel> listModel = new ArrayList<>();
                ModuleListModel temp = new ModuleListModel("服务名称:"+mAdapter.getItem(mPosition).getModuleName()
                        , mAdapter.getItem(mPosition).getId(), moduleStatus);
                listModel.add(temp);
                startActivity(CreateQRCodeActivity.buildIntent(mContext,
                        CreateQRCodeInterface.QRCodeTypeCallService, CreateQRCodeInterface.SingleType.Single, listModel));
                break;
            case R.id.tv_edit:
                mEditServiceId = mAdapter.getItem(mPosition).getId();
                callserviceNameEt.setText(mAdapter.getItem(mPosition).getModuleName());
                callserviceRemarkEt.setText(mAdapter.getItem(mPosition).getModuleDesc());
                break;
            case R.id.tv_delete:
                YLBDialog.Builder builder = new YLBDialog.Builder(mContext);
                builder.setMessage("确认删除该WIFI设置?");
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        getDataManager().deleteCallServiceById(mAdapter.getItem(mPosition).getId()
                                , CommonStringModel.class, true);
                    }
                });
                builder.show();
                break;
        }
    }
}
