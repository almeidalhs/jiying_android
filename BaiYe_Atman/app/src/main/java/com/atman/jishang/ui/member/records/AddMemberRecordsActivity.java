package com.atman.jishang.ui.member.records;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.AddMemberRecordsCouponAdapter;
import com.atman.jishang.adapter.AddMemberRecordsFullCutAdapter;
import com.atman.jishang.adapter.AddMemberRecordsGoodsAdapter;
import com.atman.jishang.adapter.AddRecordsCouponListViewAdapter;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.interfaces.MyTextWatcher;
import com.atman.jishang.net.model.AddMemberRecordResuiltModel;
import com.atman.jishang.net.model.AddRecordCouponListModel;
import com.atman.jishang.net.model.AddRecordFullCutListModel;
import com.atman.jishang.net.model.AddRecordParamsModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.ui.home.classification.GoodsClasesActivity;
import com.atman.jishang.widget.RecycleViewDivider;
import com.corelib.util.LogUtils;
import com.corelib.widget.MyListView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 增加消费记录
 * 作者 tangbingliang
 * 时间 16/5/30 11:11
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AddMemberRecordsActivity extends SimpleTitleBarActivity implements
        AddMemberRecordsGoodsAdapter.IonSlidingViewClickListener, AdapterInterface {

    @Bind(R.id.addrecord_goods_lv)
    RecyclerView addrecordGoodsLv;
    @Bind(R.id.addrecord_fullcut_tx)
    TextView addrecordFullcutTx;
    @Bind(R.id.addrecord_totalprice_tv)
    TextView addrecordTotalpriceTv;
    @Bind(R.id.addrecord_price_et)
    EditText addrecordPriceEt;
    @Bind(R.id.addrecord_remark_et)
    EditText addrecordRemarkEt;
    @Bind(R.id.addrecord_title_tv)
    TextView tvTitle;
    @Bind(R.id.addrecord_fullcut_not_tx)
    TextView addrecordFullcutNotTx;
    @Bind(R.id.addrecord_fullcut_listview)
    MyListView addrecordFullcutListview;
    @Bind(R.id.addrecord_coupon_not_tx)
    TextView addrecordCouponNotTx;
    @Bind(R.id.addrecord_coupon_listview)
    MyListView addrecordCouponListview;
    @Bind(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @Bind(R.id.right)
    LinearLayout right;
    @Bind(R.id.addrecord_coupon_lv)
    MyListView addrecordCouponLv;

    private Context mContext = AddMemberRecordsActivity.this;
    private int memberId;
    private final int toGoodsClass = 1003;
    private String mobile;
    private boolean isGoOn = false;

    private List<AddRecordParamsModel.GoodsBeanListEntity> mGoodsList;
    private AddMemberRecordsGoodsAdapter goodsAdapter;
    private AddMemberRecordsFullCutAdapter fullCutAdapter;
    private AddMemberRecordsCouponAdapter couponAdapter;

    private List<AddRecordFullCutListModel.BodyEntity> mFullCutList;
    private AddRecordFullCutListModel.BodyEntity mFullCut;
    private int mFullCutSelectId = -1;
    private List<AddRecordCouponListModel.BodyEntity> mCouponList;
    private List<AddRecordCouponListModel.BodyEntity> mCoupon;
    private AddRecordsCouponListViewAdapter mCouponAdapter;
    private int mCouponSelectId = -1;
    private int discount = 0;

    private double total;
    private String received;
    private String remark;
    private List<AddRecordCouponListModel.BodyEntity> couponList = new ArrayList<>();
    private List<AddRecordFullCutListModel.BodyEntity> mansongRuleList = new ArrayList<>();
    private List<AddRecordParamsModel.GoodsBeanListEntity> goodsBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmemberrecords);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context, int memberID, String mobile) {
        Intent intent = new Intent(context, AddMemberRecordsActivity.class);
        intent.putExtra("id", memberID);
        intent.putExtra("mobile", mobile);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mobile = getIntent().getStringExtra("mobile");
        memberId = getIntent().getIntExtra("id", -1);
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        hideTitleBar();
        tvTitle.setText("新增消费记录");

        addrecordPriceEt.addTextChangedListener(new MyTextWatcher(mContext, addrecordPriceEt, true, 8, "您输入的价格过高，请重新输入"));
        addrecordRemarkEt.addTextChangedListener(new MyTextWatcher(mContext, addrecordRemarkEt, true, 255, "您输入内容的长度不能超过256个字"));

        goodsAdapter = new AddMemberRecordsGoodsAdapter(mContext, getmWidth(), this);
        addrecordGoodsLv.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        addrecordGoodsLv.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, R.drawable.divider_mileage));
        addrecordGoodsLv.setAdapter(goodsAdapter);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        mGoodsList = new ArrayList<>();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof AddRecordFullCutListModel) {
            AddRecordFullCutListModel mAddRecordFullCutListModel = (AddRecordFullCutListModel) response;
            if (mAddRecordFullCutListModel.getResult().equals("1")) {
                mFullCutList = mAddRecordFullCutListModel.getBody();
                initFullCutListView();
            } else {
                showToast("满减活动信息获取失败");
            }
        } else if (response instanceof AddRecordCouponListModel) {
            AddRecordCouponListModel mAddRecordCouponListModel = (AddRecordCouponListModel) response;
            if (mAddRecordCouponListModel.getResult().equals("1")) {
                mCouponList = mAddRecordCouponListModel.getBody();
                initCouponListView();
            } else {
                showToast("优惠券信息获取失败");
            }
        } else if (response instanceof AddMemberRecordResuiltModel) {
            AddMemberRecordResuiltModel mAddMemberRecordResuiltModel = (AddMemberRecordResuiltModel) response;
            if (mAddMemberRecordResuiltModel.getResult().equals("1")) {
                showToast("添加成功");
                if (isGoOn) {
                    clearView();
                } else {
                    finish();
                }
            } else {
                showToast(mAddMemberRecordResuiltModel.getBody().getMessage());
            }
        }
    }

    private void clearView() {
        mGoodsList.clear();
        goodsAdapter.clearData();
        discount = 0;

        mFullCutSelectId = -1;
        mFullCutList.clear();
        fullCutAdapter.clearBody();
        addrecordFullcutNotTx.setVisibility(View.VISIBLE);

        mCouponSelectId = -1;
        mCouponList.clear();
        couponAdapter.clearBody();
        addrecordCouponNotTx.setVisibility(View.VISIBLE);

        addrecordPriceEt.setText("");
        addrecordTotalpriceTv.setText("总价：0.00元");
        addrecordRemarkEt.setText("");

        total = 0;
        received = "";
        remark = "";
        couponList.clear();
        mansongRuleList.clear();
        goodsBeanList.clear();

    }

    private void initCouponListView() {
        if (mCouponList.size() > 0) {
            addrecordCouponNotTx.setVisibility(View.GONE);
        } else {
            addrecordCouponNotTx.setVisibility(View.VISIBLE);
        }
        couponAdapter = new AddMemberRecordsCouponAdapter(mContext, mCouponList, this);
        addrecordCouponListview.setAdapter(couponAdapter);
        if (mCouponSelectId != -1) {
            couponAdapter.selectItemById(mCouponSelectId);
        }
        addrecordCouponListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                couponAdapter.selectItem(position);
                mCouponSelectId = couponAdapter.getItem(position).getId();
            }
        });
    }

    private void initFullCutListView() {
        if (mFullCutList.size() > 0) {
            addrecordFullcutNotTx.setVisibility(View.GONE);
        } else {
            addrecordFullcutNotTx.setVisibility(View.VISIBLE);
        }
        fullCutAdapter = new AddMemberRecordsFullCutAdapter(mContext, mFullCutList, this);
        addrecordFullcutListview.setAdapter(fullCutAdapter);
        if (mFullCutSelectId != -1) {
            fullCutAdapter.selectItemById(mFullCutSelectId);
        }
        addrecordFullcutListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fullCutAdapter.selectItem(position);
                mFullCutSelectId = fullCutAdapter.getItem(position).getId();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.addrecord_addgoods_tv, R.id.addrecord_addfullcut_tv, R.id.addrecord_addcoupon_tv,
            R.id.addrecord_finish_ll, R.id.addrecord_goon_ll, R.id.addrecord_back_ll, R.id.addrecord_back_iv
            , R.id.addrecord_active_finish_ll})
    public void onClick(View view) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("##0.00");
        switch (view.getId()) {
            case R.id.addrecord_back_iv:
            case R.id.addrecord_back_ll:
                finish();
                break;
            case R.id.addrecord_addgoods_tv:
                Intent intent = new Intent(mContext, GoodsClasesActivity.class);
                intent.putExtra("isRecord", true);
                startActivityForResult(intent, toGoodsClass);
                break;
            case R.id.addrecord_addfullcut_tv:
            case R.id.addrecord_addcoupon_tv:
                drawerlayout.openDrawer(right);
                getDataManager().getFullcutList(AddRecordFullCutListModel.class, true);
                if (mobile != null && !mobile.isEmpty()) {
                    getDataManager().getCouponByMember(mobile, AddRecordCouponListModel.class, false);
                }
                break;
            case R.id.addrecord_finish_ll:
                isGoOn = false;
                if (inParams()) {
                    return;
                }
                getDataManager().addMemberRecord(memberId, myformat.format(total), remark, received, couponList
                        , mansongRuleList, goodsBeanList, AddMemberRecordResuiltModel.class, true);
                break;
            case R.id.addrecord_goon_ll:
                isGoOn = true;
                if (inParams()) {
                    return;
                }
                getDataManager().addMemberRecord(memberId, myformat.format(total), remark, received, couponList
                        , mansongRuleList, goodsBeanList, AddMemberRecordResuiltModel.class, true);
                break;
            case R.id.addrecord_active_finish_ll:
                drawerlayout.closeDrawer(right);
                mFullCut = fullCutAdapter.getSelect();
                if (couponAdapter != null) {
                    mCoupon = couponAdapter.getSelect();
                    LogUtils.e("couponAdapter.getSelect():" + couponAdapter.getSelect());
                }
                displayMoney();
                break;
        }
    }

    private boolean inParams() {
        received = addrecordPriceEt.getText().toString().trim();
        total = goodsAdapter.getTotalMoney() - discount;
        if (total < 0) {
            total = 0;
        }
        remark = addrecordRemarkEt.getText().toString().trim();
        couponList.clear();
        mansongRuleList.clear();
        goodsBeanList.clear();

        goodsBeanList.addAll(mGoodsList);

        if (goodsBeanList.size() == 0) {
            showToast("请至少添加一个商品");
            return true;
        }
        if (received.isEmpty()) {
            showToast("请输入本次消费实收金额");
            return true;
        }

        if (mCoupon != null && mCoupon.size() > 0) {
            couponList.addAll(mCoupon);
        }
        if (mFullCut != null) {
            mansongRuleList.add(mFullCut);
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == toGoodsClass) {
            int num = mGoodsList.size();
            AddRecordParamsModel.GoodsBeanListEntity temp =
                    new AddRecordParamsModel.GoodsBeanListEntity(
                            data.getIntExtra("goodsId", -1),
                            data.getDoubleExtra("goodsPrice", 0), 1,
                            data.getStringExtra("goodsName"),
                            data.getStringExtra("goodsInfo"),
                            data.getStringExtra("goodsImage"));
            if (mGoodsList.size() == 0) {
                mGoodsList.add(temp);
            } else {
                for (int i = 0; i < num; i++) {
                    LogUtils.e("mGoodsList.get(i).getGoodsId():" + mGoodsList.get(i).getGoodsId());
                    if (mGoodsList.get(i).getGoodsId() == data.getIntExtra("goodsId", -1)) {
                        mGoodsList.get(i).setGoodsCount(mGoodsList.get(i).getGoodsCount() + 1);
                    } else {
                        if (i == (mGoodsList.size() - 1)) {
                            mGoodsList.add(temp);
                        }
                    }
                }
            }
            AddGoodsListView();
        }
    }

    private void AddGoodsListView() {
        goodsAdapter.clearData();
        goodsAdapter.addList(mGoodsList);
        displayMoney();
    }

    private void displayMoney() {
        discount = 0;
        if (mFullCut != null && mFullCut.isSelect()) {
            addrecordFullcutTx.setVisibility(View.VISIBLE);
            if (goodsAdapter.getTotalMoney() < mFullCut.getPrice()) {
//                discount += 0;
                addrecordFullcutTx.setText("全场满" + mFullCut.getPrice() + "减" + mFullCut.getDiscount() + "元(不满足使用条件)");
                addrecordFullcutTx.setTextColor(getResources().getColor(R.color.main_title_orange_color));
            } else {
//                discount += mFullCut.getDiscount();
                addrecordFullcutTx.setText("全场满" + mFullCut.getPrice() + "减" + mFullCut.getDiscount() + "元");
                addrecordFullcutTx.setTextColor(getResources().getColor(R.color.color_black));
            }
            discount += mFullCut.getDiscount();
        } else {
            mFullCutSelectId = -1;
            addrecordFullcutTx.setVisibility(View.GONE);
            addrecordFullcutTx.setText("");
        }
        if (mCoupon != null) {
            LogUtils.e(">>>>>mCoupon.size():" + mCoupon.size());
            for (int i = 0; i < mCoupon.size(); i++) {
                if (mCoupon.get(i) != null && mCoupon.get(i).isSelect()) {
//                if ((goodsAdapter.getTotalMoney()-discount) < mCoupon.get(i).getCouponLimit()) {
                    if (goodsAdapter.getTotalMoney() < mCoupon.get(i).getCouponLimit()) {
//                    discount += 0;
                        mCoupon.get(i).setUserd(false);
                    } else {
//                    discount += mCoupon.get(i).getCouponPrice();
                        mCoupon.get(i).setUserd(true);
                    }
                    discount += mCoupon.get(i).getCouponPrice();
                }
            }
//            if (mCouponAdapter == null) {
                mCouponAdapter = new AddRecordsCouponListViewAdapter(mContext, mCoupon);
//            } else {
//                mCouponAdapter.setBody(mCoupon);
//            }
            addrecordCouponLv.setAdapter(mCouponAdapter);
        }
        if (goodsAdapter.getItemCount() != 0) {
            if (goodsAdapter.getTotalMoney() - discount <= 0) {
                addrecordTotalpriceTv.setText("总价：0元");
            } else {
                DecimalFormat df = new DecimalFormat("##0.00");
                addrecordTotalpriceTv.setText("总价：" + df.format(goodsAdapter.getTotalMoney() - discount) + "元");
            }
        } else {
            addrecordTotalpriceTv.setText("总价：0元");
        }
    }


    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.item_addrecord_down_tx:
                goodsAdapter.down(position);
                displayMoney();
                break;
            case R.id.item_addrecord_up_tx:
                goodsAdapter.up(position);
                displayMoney();
                break;
            case R.id.item_addrecord_time_tv:
                fullCutAdapter.selectItem(position);
                mFullCutSelectId = fullCutAdapter.getItem(position).getId();
                break;
            case R.id.item_addrecord_coupon_select_iv:
                couponAdapter.selectItem(position);
                mCouponSelectId = couponAdapter.getItem(position).getId();
                break;
        }
    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {
        switch (view.getId()) {
            case R.id.tv_delete:
                mGoodsList.remove(position);
                goodsAdapter.removeData(position);
                displayMoney();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 返回键
            if (drawerlayout.isDrawerOpen(right)) {
                drawerlayout.closeDrawer(right);
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
