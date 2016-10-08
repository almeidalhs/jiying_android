package com.atman.jishang.ui.manager.classification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.GoodsClasesAdapter;
import com.atman.jishang.net.model.GetStoreClassModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.ui.manager.goods.GoodsListByClassActivity;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 分类管理
 * 作者 tangbingliang
 * 时间 16/4/27 13:09
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GoodsClasesActivity extends SimpleTitleBarActivity {

    @Bind(R.id.pullToRefreshListView)
    PullToRefreshListView pullToRefreshListView;
    @Bind(R.id.goodsmanagement_add_ll)
    LinearLayout goodsmanagementAddLl;
    @Bind(R.id.goodsmanagement_manage_ll)
    LinearLayout goodsmanagementManageLl;
    @Bind(R.id.goodsclass_null_tx)
    TextView goodsclassNullTx;

    private Context mContext = GoodsClasesActivity.this;
    private GoodsClasesAdapter mAdapter;
    private List<GetStoreClassModel.BodyEntity> list = new ArrayList<>();
    private final int toAddClass = 1001;

    private boolean isRecord = false;
    private final int toGoodsList = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsclass);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRecord = getIntent().getBooleanExtra("isRecord", false);
        getDataManager().getGoodsClass(GetStoreClassModel.class, true);
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle(R.string.goodsclases_title);
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
        if (response instanceof GetStoreClassModel) {
            GetStoreClassModel mGetStoreClassModel = (GetStoreClassModel) response;
            if (mGetStoreClassModel.getResult().equals("1")) {
                list = mGetStoreClassModel.getBody();
                int num = 0;
                for (int i = 0; i < list.size(); i++) {
                    num += list.get(i).getGoodsCount();
                }
                if (list.size() == 0) {
                    goodsclassNullTx.setVisibility(View.VISIBLE);
                    pullToRefreshListView.setVisibility(View.GONE);
                    goodsmanagementManageLl.setVisibility(View.GONE);
                    return;
                }
                goodsclassNullTx.setVisibility(View.GONE);
                pullToRefreshListView.setVisibility(View.VISIBLE);
                goodsmanagementManageLl.setVisibility(View.VISIBLE);
                GetStoreClassModel.BodyEntity temp
                        = new GetStoreClassModel.BodyEntity(0, "全部", 0, list.get(0).getStcState(), list.get(0).getStoreId(), -1, num);
                list.add(0, temp);
                initListView();
            } else {
                showToast("获取商品分类失败");
            }
        }
    }

    private void initListView() {
        mAdapter = new GoodsClasesAdapter(mContext, list);
        pullToRefreshListView.setAdapter(mAdapter);
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivityForResult(GoodsListByClassActivity.buildIntent(
                        mContext, list.get(position - 1).getStcName(), list.get(position - 1).getId(),isRecord), toGoodsList);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.goodsmanagement_add_ll, R.id.goodsmanagement_manage_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goodsmanagement_add_ll:
                startActivityForResult(new Intent(mContext, AddGoodsClassActivity.class), toAddClass);
                break;
            case R.id.goodsmanagement_manage_ll:
                startActivityForResult(new Intent(mContext, EditGoodsClassActivity.class), toAddClass);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == toAddClass) {
            getDataManager().getGoodsClass(GetStoreClassModel.class, true);
        } else if (requestCode == toGoodsList) {
            if (isRecord) {
                Intent mIntent = new Intent();
                mIntent.putExtra("goodsId",data.getIntExtra("goodsId", -1));
                mIntent.putExtra("goodsPrice",data.getDoubleExtra("goodsPrice", 0));
                mIntent.putExtra("goodsName",data.getStringExtra("goodsName"));
                mIntent.putExtra("goodsInfo",data.getStringExtra("goodsInfo"));
                mIntent.putExtra("goodsImage",data.getStringExtra("goodsImage"));
                setResult(RESULT_OK,mIntent);
                finish();
            }
        }
    }
}
