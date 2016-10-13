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
import com.atman.jishang.net.model.CommonStringModel;
import com.atman.jishang.net.model.ModuleListModel;
import com.atman.jishang.net.model.SetServiceStatusModel;
import com.atman.jishang.ui.base.BaiYeBaseFragment;
import com.atman.jishang.ui.service.callservice.CallServiceActivity;
import com.atman.jishang.ui.service.code.CreateQRCodeActivity;
import com.atman.jishang.ui.service.wifi.WifiActivity;
import com.atman.jishang.widget.YLBDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        getDataManager().getServiceCommonConf(CommconfModel.class, false);
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
                serviceUIHelp(mAdapter.getItem(position).getModuleId()
                        , mAdapter.getItem(position).getId()
                        , mAdapter.getItem(position).getModuleName()
                        , mAdapter.getItem(position).getModuleStatus());
                break;
            case R.id.setting_open_sb:
                mPosition = position;
                if (mAdapter.getItem(position).getModuleSetup() == 0) {
                    mAdapter.updataView(mPosition, serviceModelListview, 0);
                    YLBDialog.Builder builder = new YLBDialog.Builder(getActivity());
                    builder.setMessage("你尚未设置" + mAdapter.getItem(position).getModuleName() + "服务");
                    builder.setPositiveButton("暂不设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("立即设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            serviceUIHelp(mAdapter.getItem(mPosition).getModuleId()
                                    , mAdapter.getItem(mPosition).getId()
                                    , mAdapter.getItem(mPosition).getModuleName()
                                    , mAdapter.getItem(mPosition).getModuleStatus());
                        }
                    });
                    builder.show();
                    return;
                }
                if (mAdapter.getItem(position).getModuleStatus() == 1) {
                    status = 0;
                } else {
                    status = 1;
                }
                getDataManager().setServiceStatusByModelId(mAdapter.getItem(position).getId(), status,
                        SetServiceStatusModel.class, false);
                break;
        }
    }

    private void serviceUIHelp(int moduleId, int id, String title, int moduleStatus) {
        switch (moduleId) {
            case ServiceTypeInterface.moduleTypeWifi:
                startActivity(WifiActivity.bulidIntent(getActivity(), title, id, moduleStatus));
                break;
            case ServiceTypeInterface.moduleTypeCallSerivce:
                startActivity(CallServiceActivity.bulidIntent(getActivity(), title, id, moduleId, moduleStatus));
                break;
        }
    }

    @Override
    public void onCheckedChanged(int position, CompoundButton buttonView, boolean isChecked) {
        mPosition = position;
        if (mAdapter.getItem(position).getModuleStatus() == 1) {
            status = 0;
        } else {
            status = 1;
        }
        getDataManager().setServiceStatusByModelId(mAdapter.getItem(position).getId(), status,
                SetServiceStatusModel.class, true);
    }

    @OnClick({R.id.service_nav_right_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.service_nav_right_ll:
                List<CommconfModel.BodyBean> data = mAdapter.getmBody();
                List<ModuleListModel> listModel = new ArrayList<>();
                for (int i=0;i<data.size();i++) {
                    if (data.get(i).getModuleSetup()==1 && data.get(i).getModuleStatus()==1) {
                        ModuleListModel temp = new ModuleListModel(data.get(i).getModuleName()
                                , data.get(i).getModuleId(), data.get(i).getModuleStatus());
                        listModel.add(temp);
                    }
                }
                if (listModel.size()==0) {
                    YLBDialog.Builder builder = new YLBDialog.Builder(getActivity());
                    builder.setMessage("请选择开启的功能");
                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                    return;
                }
                startActivity(CreateQRCodeActivity.buildIntent(getActivity(), 1, 0, listModel));
                break;
        }
    }
}
