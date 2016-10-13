package com.atman.jishang.ui.marketing.coupon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.adapter.CouponRecordsAdapter;
import com.atman.jishang.net.model.CouponRecordsModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.corelib.util.LogUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述 优惠券领取记录
 * 作者 tangbingliang
 * 时间 16/5/24 11:39
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class ReceiveCouponRecordsActivity extends SimpleTitleBarActivity {

    @Bind(R.id.pullToRefreshListView)
    PullToRefreshListView pullToRefreshListView;
    @Bind(R.id.receive_coupon_left)
    TextView receiveCouponLeft;
    @Bind(R.id.receive_coupon_right)
    TextView receiveCouponRight;
    @Bind(R.id.receive_coupon_top_ll)
    LinearLayout receiveCouponTopLl;

    private Context mContext = ReceiveCouponRecordsActivity.this;
    private List<CouponRecordsModel.BodyEntity> mListData = new ArrayList<>();
    private List<CouponRecordsModel.BodyEntity> mList = new ArrayList<>();
    private CouponRecordsAdapter mAdapter;
    private View mEmpty;
    private TextView mEmptyTX;

    private int storage;
    private int used = 0;
    private int id;
    private int page = 1;
    private int count = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receivecouponrecords);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context, int id, int storage) {
        Intent intent = new Intent(context, ReceiveCouponRecordsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("storage", storage);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle(R.string.receive_coupon_records_title);

        initRefreshView(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
        mEmpty = LayoutInflater.from(mContext).inflate(R.layout.part_list_empty, null);
        mEmptyTX = (TextView) mEmpty.findViewById(R.id.empty_list_tx);
        mEmptyTX.setText("暂无领取记录");

        initListView();
    }

    private void initListView() {
        mAdapter = new CouponRecordsAdapter(mContext);
        pullToRefreshListView.setEmptyView(mEmpty);
        pullToRefreshListView.setAdapter(mAdapter);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();

        storage = getIntent().getIntExtra("storage", 0);
        id = getIntent().getIntExtra("id", -1);

        receiveCouponLeft.setText("0/" + storage);
        receiveCouponRight.setText("0/0");
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        getDataManager().getCouponRecordsById(id, page, count, CouponRecordsModel.class, true);
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof CouponRecordsModel) {
            CouponRecordsModel mCouponRecordsModel = (CouponRecordsModel) response;
            if (mCouponRecordsModel.getResult().equals("1")) {
                mListData = mCouponRecordsModel.getBody();
                LogUtils.e("mListData.size():"+mListData.size());
                if (mListData == null || mListData.size() == 0) {
                    if (mAdapter!=null && mAdapter.getCount()>0) {
                        showToast("没有更多");
                    }
                    onLoad(PullToRefreshBase.Mode.PULL_FROM_START, pullToRefreshListView);
                } else {
                    onLoad(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
                    mAdapter.setmListData(mListData);
                    updataUi();
                }
            } else {
                showToast("优惠券领取记录获取失败");
            }
        }
    }

    private void updataUi() {
        mList = mAdapter.getmListData();
        if (mList.size() > 0) {
            receiveCouponTopLl.setVisibility(View.VISIBLE);
        } else {
            receiveCouponTopLl.setVisibility(View.GONE);
        }
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getStatus() == 1) {
                used += 1;
            }
        }
        receiveCouponLeft.setText(mList.size() + "/" + storage);
        receiveCouponRight.setText(used + "/" + mList.size());
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page = 1;
        count = 0;
        mAdapter.clearData();
        doInitBaseHttp();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page += 1;
        count = mAdapter.getCount();
        doInitBaseHttp();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        if (page > 0) {
            page -= 1;
        }
        onLoad(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
