package com.atman.jishang.ui.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.ui.base.BaiYeBaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tangbingliang on 16/10/8.
 */

public class MessageFragment extends BaiYeBaseFragment {

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, null);
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
        tvTitle.setText(R.string.tab_message);
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
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }
}
