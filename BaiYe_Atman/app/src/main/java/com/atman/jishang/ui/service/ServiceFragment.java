package com.atman.jishang.ui.service;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.ServiceAdapter;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.interfaces.CompoundButtonInterface;
import com.atman.jishang.interfaces.ServiceTypeInterface;
import com.atman.jishang.net.model.CommconfModel;
import com.atman.jishang.net.model.SetServiceStatusModel;
import com.atman.jishang.ui.base.BaiYeBaseFragment;
import com.atman.jishang.ui.service.wifi.WifiActivity;
import com.atman.jishang.widget.YLBDialog;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tangbingliang on 16/10/8.
 */

public class ServiceFragment extends BaiYeBaseFragment implements AdapterInterface, CompoundButtonInterface {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.service_model_listview)
    ListView serviceModelListview;

    private CommconfModel mCommconfModel;
    private ServiceAdapter mAdapter;
    private int mPosition;
    private int status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        tvTitle.setText(R.string.tab_service);

        mAdapter = new ServiceAdapter(getActivity(), this, this);
        serviceModelListview.setAdapter(mAdapter);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof CommconfModel) {
            mCommconfModel = (CommconfModel) response;
            mAdapter.setmBody(mCommconfModel.getBody());
        } else if (response instanceof SetServiceStatusModel) {
            SetServiceStatusModel mSetServiceStatusModel = (SetServiceStatusModel) response;
            if (mSetServiceStatusModel.getResult().equals("1")) {
                mAdapter.changStatusById(mPosition, status);
                mAdapter.updataView(mPosition, serviceModelListview, 0);
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getDataManager().getServiceCommonConf(CommconfModel.class, false);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.item_service_root:
                serviceUIHelp(mAdapter.getItem(position).getModuleId(), mAdapter.getItem(position).getModuleName());
                break;
            case R.id.setting_open_sb:
                mPosition = position;
                if (mAdapter.getItem(position).getModuleSetup()==0) {
                    mAdapter.updataView(mPosition, serviceModelListview, 0);
                    YLBDialog.Builder builder = new YLBDialog.Builder(getActivity());
                    builder.setMessage("你尚未设置"+mAdapter.getItem(position).getModuleName()+"服务");
                    builder.setPositiveButton("暂不设置", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("立即设置", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            serviceUIHelp(mAdapter.getItem(mPosition).getModuleId(), mAdapter.getItem(mPosition).getModuleName());
                        }
                    });
                    builder.show();
                    return;
                }
                if (mAdapter.getItem(position).getModuleStatus()==1) {
                    status = 0;
                } else {
                    status = 1;
                }
                getDataManager().setServiceStatusByModelId(mAdapter.getItem(position).getId(), status,
                        SetServiceStatusModel.class, false);
                break;
        }
    }

    private void serviceUIHelp(int id, String title) {
        switch (id) {
            case ServiceTypeInterface.moduleTypeWifi:
                startActivity(WifiActivity.bulidIntent(getActivity(), title));
                break;
        }
    }

    @Override
    public void onCheckedChanged(int position, CompoundButton buttonView, boolean isChecked) {
        mPosition = position;
        if (mAdapter.getItem(position).getModuleStatus()==1) {
            status = 0;
        } else {
            status = 1;
        }
        getDataManager().setServiceStatusByModelId(mAdapter.getItem(position).getId(), status,
                SetServiceStatusModel.class, true);
    }
}
