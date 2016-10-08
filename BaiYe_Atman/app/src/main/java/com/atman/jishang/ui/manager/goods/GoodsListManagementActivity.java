package com.atman.jishang.ui.manager.goods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.atman.jishang.R;
import com.atman.jishang.adapter.GoodsListViewManagementAdapter;
import com.atman.jishang.adapter.GoodsTypeAdapter;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.model.GetGoodsByClassIdModel;
import com.atman.jishang.net.model.GetStoreClassModel;
import com.atman.jishang.net.model.ModifiedClassificationModel;
import com.atman.jishang.net.model.ModifiedClassificationResultModel;
import com.atman.jishang.net.model.UnShelveOrShelveModel;
import com.atman.jishang.net.model.UnShelveOrShelveResultModel;
import com.atman.jishang.widget.WheelView.OnWheelChangedListener;
import com.atman.jishang.widget.WheelView.OnWheelScrollListener;
import com.atman.jishang.widget.WheelView.WheelView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 商品批量管理界面
 * 作者 tangbingliang
 * 时间 16/4/29 09:09
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GoodsListManagementActivity extends SimpleTitleBarActivity implements AdapterInterface {

    @Bind(R.id.pullToRefreshListView)
    PullToRefreshListView pullToRefreshListView;
    @Bind(R.id.goodsmanagement_shelce_tx)
    TextView goodsmanagementShelceTx;

    private Context mContext = GoodsListManagementActivity.this;
    private int goods_show = 0;
    private int page;
    private int mPageSize = 20;//每页个数

    private List<GetGoodsByClassIdModel.BodyEntity> mBodyEntityList;
    private GoodsListViewManagementAdapter mAdapter;
    private String str;
    private String strEmpty;
    private List<GetStoreClassModel.BodyEntity> listType = new ArrayList<>();

    private TextView barRightTv;
    protected ImageLoader imageLoader;
    private View mEmpty;
    private TextView mEmptyTX;
    private long mStcId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodslistmanagement);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context, int goods_show) {
        Intent intent = new Intent(context, GoodsListManagementActivity.class);
        intent.putExtra("goods_show", goods_show);
        return intent;
    }

    public static Intent buildIntent(Context context, int goods_show, long stcId, String stcName) {
        Intent intent = new Intent(context, GoodsListManagementActivity.class);
        intent.putExtra("goods_show", goods_show);
        intent.putExtra("stcId", stcId);
        intent.putExtra("stcName", stcName);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        imageLoader = ImageLoader.getInstance();
        mEmpty = LayoutInflater.from(mContext).inflate(R.layout.part_list_empty, null);
        mEmptyTX = (TextView) mEmpty.findViewById(R.id.empty_list_tx);
        String strTitle = "";
        mStcId = getIntent().getIntExtra("stcId",0);
        if (getIntent().getStringExtra("stcName") !=null
                && !getIntent().getStringExtra("stcName").isEmpty()) {
            strTitle = getIntent().getStringExtra("stcName");
        } else {
            strTitle = "全部";
        }
        if (getIntent().getIntExtra("goods_show", 0) == 0) {
            goods_show = 1;
            strTitle = "管理 "+ strTitle +"-出售中";
            str = "下架";
            strEmpty = "暂无出售中商品";
        } else {
            goods_show = 0;
            strTitle = "管理 "+ strTitle +"-已下架";
            str = "上架";
            strEmpty = "暂无下架商品";
        }
        setToolbarTitle(strTitle);
        goodsmanagementShelceTx.setText(str);
        initRefreshView(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
        barRightTv = showRightTV("全选");
        barRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter.getCount()==0){
                    showToast(strEmpty);
                    return;
                }
                changDisplayTx();
            }
        });
        mEmptyTX.setText(strEmpty);
        mAdapter = new GoodsListViewManagementAdapter(mContext, getmWidth(), imageLoader, this);
        pullToRefreshListView.setEmptyView(mEmpty);
        pullToRefreshListView.setAdapter(mAdapter);
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.selectByPosition(position-1);
                if (mAdapter.isAllSelect) {
                    barRightTv.setText("取消全选");
                } else {
                    barRightTv.setText("全选");
                }
            }
        });
    }

    private void changDisplayTx() {
        List<GetGoodsByClassIdModel.BodyEntity> list = mAdapter.getSelectedList();
        if (list.size() != mAdapter.getCount()) {
            barRightTv.setText("取消全选");
            mAdapter.selectAll();
        } else {
            mAdapter.unselectAll();
            barRightTv.setText("全选");
        }
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        getDataManager().getGoodsByClassId(String.valueOf(mStcId), goods_show,
                page, mPageSize, GetGoodsByClassIdModel.class, true);
        getDataManager().getGoodsClass(GetStoreClassModel.class, true);
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof GetGoodsByClassIdModel) {
            GetGoodsByClassIdModel mGetGoodsByClassIdModel = (GetGoodsByClassIdModel) response;
            if (mGetGoodsByClassIdModel.getResult().equals("1")) {
                mBodyEntityList = mGetGoodsByClassIdModel.getBody();
                if (mBodyEntityList == null || mBodyEntityList.size() == 0) {
                    if (mAdapter!=null && mAdapter.getCount()>0) {
                        showToast("没有更多");
                    }
                    onLoad(PullToRefreshBase.Mode.PULL_FROM_START, pullToRefreshListView);
                } else {
                    onLoad(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
                    mAdapter.setBody(mBodyEntityList);
                }
            } else {
                showToast("商品获取失败");
            }
        } else if (response instanceof UnShelveOrShelveResultModel) {
            UnShelveOrShelveResultModel mUnShelveOrShelveResultModel = (UnShelveOrShelveResultModel) response;
            if (mUnShelveOrShelveResultModel.getResult().equals("1")) {
                showToast(str + "成功");
                if (barRightTv.getText().toString().equals("取消全选")) {
                    barRightTv.setText("全选");
                }
                mAdapter.deleteItemById(list);
            } else {
                showToast(str + "失败");
            }
        } else if (response instanceof GetStoreClassModel) {
            GetStoreClassModel mGetStoreClassModel = (GetStoreClassModel) response;
            if (mGetStoreClassModel.getResult().equals("1")) {
                listType.addAll(mGetStoreClassModel.getBody());
            } else {
                showToast("分类获取失败");
            }
        } else if (response instanceof ModifiedClassificationResultModel) {
            ModifiedClassificationResultModel mModifiedClassificationResultModel = (ModifiedClassificationResultModel) response;
            if (mModifiedClassificationResultModel.getResult().equals("1")) {
                showToast("分类至"+mScName+"成功");
                mAdapter.unselectAll();
                mAdapter.deleteItemByIdTwo(listClass);
                changDisplayTx();
            } else {
                showToast("分类至"+mScName+"失败");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private List<UnShelveOrShelveModel> list;
    private List<ModifiedClassificationModel> listClass = new ArrayList<>();

    @OnClick({R.id.goodsmanagement_add_ll, R.id.goodsmanagement_manage_ll})
    public void onClick(View view) {
        list = new ArrayList<>();
//        if (mAdapter.getCount()==0){
//            showToast(strEmpty);
//            return;
//        }
        if (mAdapter.getSelectedItem().size() == 0) {
            showToast("请先选择商品");
            return;
        }
        switch (view.getId()) {
            case R.id.goodsmanagement_add_ll:
                for (int i = 0; i < mAdapter.getSelectedItem().size(); i++) {
                    UnShelveOrShelveModel mUnShelveOrShelveModel =
                            new UnShelveOrShelveModel(mAdapter.getSelectedItem().get(i).getId(),
                                    mAdapter.getSelectedItem().get(i).getStoreId());
                    list.add(mUnShelveOrShelveModel);
                }
                if (goods_show == 1) {
                    getDataManager().batchUnShelve(new Gson().toJson(list), UnShelveOrShelveResultModel.class, true);
                } else {
                    getDataManager().batchShelve(new Gson().toJson(list), UnShelveOrShelveResultModel.class, true);
                }
                break;
            case R.id.goodsmanagement_manage_ll:
                showTypePopupWindow(view, listType);
                break;
        }
    }

    private WheelView mTypeWheel;
    private GoodsTypeAdapter mShopTypeAdapter;
    private String mScName;
    private long mTypeId;
    private int selcteId = 0;
    public void showTypePopupWindow(View view, final List<GetStoreClassModel.BodyEntity> mList) {

        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(mContext).inflate(R.layout.wheel_select_shop_type_view, null);

        final PopupWindow mSelectTypePop = new PopupWindow(contentView,
                RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT, true);

        mTypeWheel = (WheelView) contentView.findViewById(R.id.popwin_select_wv);
        Button pop_cancel = (Button) contentView.findViewById(R.id.pop_cancel);
        Button pop_ok = (Button) contentView.findViewById(R.id.pop_ok);
        mShopTypeAdapter = new GoodsTypeAdapter(mContext, mTypeWheel, mList, 0, 0, 0);
        mTypeWheel.setVisibleItems(5);
        mTypeWheel.setViewAdapter(mShopTypeAdapter);
        mTypeWheel.setCurrentItem(0);
        mTypeWheel.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                mTypeWheel.setViewAdapter(mShopTypeAdapter);
            }
        });
        mTypeWheel.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                mScName = (String) mShopTypeAdapter.getItemText(wheel.getCurrentItem());
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).getStcName().equals(mScName)) {
                        mTypeId = mList.get(i).getId();
                        selcteId = i;
                        break;
                    }
                }
            }
        });
        pop_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectTypePop != null) {
                    mSelectTypePop.dismiss();
                }
            }
        });
        pop_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectTypePop != null) {
                    mSelectTypePop.dismiss();
                }
                initPopwindowData(mList.get(selcteId));
                for (int i = 0; i < mAdapter.getSelectedItem().size(); i++) {
                    ModifiedClassificationModel mUnShelveOrShelveModel =
                            new ModifiedClassificationModel(mAdapter.getSelectedItem().get(i).getId(),
                                    mAdapter.getSelectedItem().get(i).getStoreId(),mTypeId);
                    listClass.add(mUnShelveOrShelveModel);
                }
                getDataManager().modifiedClassification(new Gson().toJson(listClass),ModifiedClassificationResultModel.class,true);
            }
        });

        mSelectTypePop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);
        mSelectTypePop.setTouchable(true);
        mSelectTypePop.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                return false;
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        mSelectTypePop.setBackgroundDrawable(getResources().getDrawable(R.drawable.pop_bg));
        // 设置好参数之后再show
        if (mList!=null && mList.size()>0) {
            selcteId = 0;
            initPopwindowData(mList.get(selcteId));
            mSelectTypePop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }

    }

    private void initPopwindowData(GetStoreClassModel.BodyEntity mList){
        mScName = mList.getStcName();
        mTypeId = mList.getId();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        if (page>0) {
            page -= 1;
        }
        onLoad(PullToRefreshBase.Mode.BOTH, pullToRefreshListView);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page = 0;
        mAdapter.clearBody();
        doInitBaseHttp();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page += 1;
        doInitBaseHttp();
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.item_goodslistview_managerment_select:
                if (mAdapter.isAllSelect) {
                    barRightTv.setText("取消全选");
                } else {
                    barRightTv.setText("全选");
                }
                break;
        }
    }
}
