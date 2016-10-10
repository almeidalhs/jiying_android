package com.atman.jishang.ui.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.ServiceAdapter;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.model.CommconfModel;
import com.atman.jishang.ui.base.BaiYeBaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tangbingliang on 16/10/8.
 */

public class ServiceFragment extends BaiYeBaseFragment implements AdapterInterface {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.service_model_listview)
    ListView serviceModelListview;

    private CommconfModel mCommconfModel;
    private ServiceAdapter mAdapter;

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

        mAdapter = new ServiceAdapter(getActivity(), this);
        serviceModelListview.setAdapter(mAdapter);
        serviceModelListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
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

    }
}
