package com.atman.jishang.ui.service.wifi;

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
import com.atman.jishang.adapter.WifiAdapter;
import com.atman.jishang.net.model.AddWifiModel;
import com.atman.jishang.net.model.CommonStringModel;
import com.atman.jishang.net.model.EditWifiModel;
import com.atman.jishang.net.model.GetWifiListModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.widget.YLBDialog;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tangbingliang on 16/10/10.
 */

public class WifiActivity extends SimpleTitleBarActivity implements WifiAdapter.IonSlidingViewClickListener {

    @Bind(R.id.wifi_name_et)
    EditText wifiNameEt;
    @Bind(R.id.wifi_password_et)
    EditText wifiPasswordEt;
    @Bind(R.id.wifi_save_bt)
    Button wifiSaveBt;
    @Bind(R.id.wifi_empty_tx)
    TextView wifiEmptyTx;
    @Bind(R.id.pull_refresh_recycler)
    PullToRefreshRecyclerView pullRefreshRecycler;
    @Bind(R.id.wifi_notempty_ll)
    LinearLayout wifiNotemptyLl;

    private Context mContext = WifiActivity.this;
    private GetWifiListModel mGetWifiListModel;
    private WifiAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private int mPosition;
    private int mEditWifiId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        ButterKnife.bind(this);
    }

    public static Intent bulidIntent(Context context, String title) {
        Intent intent = new Intent(context, WifiActivity.class);
        intent.putExtra("title", title);
        return intent;
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);

        setToolbarTitle(getIntent().getStringExtra("title"));
        showRightTV("制码").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("制码");
            }
        });

        initListView();
    }

    private void initListView() {
        mAdapter = new WifiAdapter(mContext, getmWidth(), this);
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
        getDataManager().getWifiList(GetWifiListModel.class, true);

        initRefreshView(PullToRefreshBase.Mode.DISABLED, pullRefreshRecycler);
        mRecyclerView = pullRefreshRecycler.getRefreshableView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));//这里用线性显示 类似于listview
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof GetWifiListModel) {
            mGetWifiListModel = (GetWifiListModel) response;
            if (mGetWifiListModel.getBody().size() == 0) {
                wifiEmptyTx.setVisibility(View.VISIBLE);
                wifiNotemptyLl.setVisibility(View.GONE);
            } else {
                wifiEmptyTx.setVisibility(View.GONE);
                wifiNotemptyLl.setVisibility(View.VISIBLE);
            }
            mAdapter.setmList(mGetWifiListModel.getBody());
        } else if (response instanceof CommonStringModel) {
            CommonStringModel mCommonModel = (CommonStringModel) response;
            showToast(mCommonModel.getBody());
            if (mCommonModel.getResult().equals("1")) {
                mAdapter.deleteItemById(mPosition);
                if (mAdapter.getItemCount() == 0) {
                    wifiEmptyTx.setVisibility(View.VISIBLE);
                    wifiNotemptyLl.setVisibility(View.GONE);
                } else {
                    wifiEmptyTx.setVisibility(View.GONE);
                    wifiNotemptyLl.setVisibility(View.VISIBLE);
                }
            }
        } else if (response instanceof AddWifiModel) {
            AddWifiModel mAddWifiModel = (AddWifiModel) response;
            if (mAddWifiModel.getResult().equals("1")) {
                showToast("添加成功");
                GetWifiListModel.BodyBean temp = new GetWifiListModel.BodyBean();
                temp.setId(mAddWifiModel.getBody().getId());
                temp.setState(mAddWifiModel.getBody().getState());
                temp.setStoreId(mAddWifiModel.getBody().getStoreId());
                temp.setWifiName(mAddWifiModel.getBody().getWifiName());
                temp.setWifiPassword(mAddWifiModel.getBody().getWifiPassword());
                mAdapter.addItem(temp);
            }
        } else if (response instanceof EditWifiModel) {
            EditWifiModel mEditWifiModel = (EditWifiModel) response;
            if (mEditWifiModel.getResult().equals("1")) {
                showToast("修改wifi成功");
                mAdapter.updataItem(mEditWifiModel.getBody(), mPosition);
                mEditWifiId = -1;
                wifiNameEt.setText("");
                wifiPasswordEt.setText("");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {
        mPosition = position;
        switch (view.getId()) {
            case R.id.tv_code:
                showToast("tv_code:" + position);
                break;
            case R.id.tv_edit:
                mEditWifiId = mAdapter.getItem(mPosition).getId();
                wifiNameEt.setText(mAdapter.getItem(mPosition).getWifiName());
                wifiPasswordEt.setText(mAdapter.getItem(mPosition).getWifiPassword());
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
                        getDataManager().deleteWifiById(mAdapter.getItem(mPosition).getId(), CommonStringModel.class, true);
                    }
                });
                builder.show();
                break;
        }
    }

    @OnClick({R.id.wifi_save_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wifi_save_bt:
                if (checkEdit()) {
                    showToast("wifi名称或者密码没有填写哟");
                    return;
                }
                if (mEditWifiId == -1) {
                    getDataManager().addWifi(wifiNameEt.getText().toString().trim()
                            , wifiPasswordEt.getText().toString(), AddWifiModel.class, true);
                } else {
                    getDataManager().editWifi(mEditWifiId, wifiNameEt.getText().toString().trim()
                            , wifiPasswordEt.getText().toString(), EditWifiModel.class, true);
                }
                break;
        }
    }

    private boolean checkEdit() {
        if (wifiNameEt.getText().toString().trim().isEmpty()) {
            return true;
        }
        if (wifiPasswordEt.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }
}
