package com.atman.jishang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.ui.manager.goods.GoodsDetailsActivity;
import com.atman.jishang.net.model.GetMemberRecordModel;
import com.atman.jishang.utils.MyTools;
import com.atman.jishang.widget.XCFlowLayout;
import com.atman.jishang.widget.YLBDialog;
import com.corelib.widget.MyListView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/31 11:35
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MemberRecordsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private AdapterInterface onItemClick;
    private LayoutInflater layoutInflater;
    private List<GetMemberRecordModel.BodyEntity> mBodyList;
    private ViewHolderGroup holder;
    private ViewHolderChild holderChild;
    private float totalPrice;
    private ViewGroup.MarginLayoutParams lp;
    private DecimalFormat df;

    public MemberRecordsAdapter(Context context, AdapterInterface onItemClick) {
        this.context = context;
        this.onItemClick = onItemClick;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBodyList = new ArrayList<>();
        lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = YLBDialog.dip2px(context, 2);
        lp.rightMargin = YLBDialog.dip2px(context, 2);
        lp.topMargin = YLBDialog.dip2px(context, 0);
        lp.bottomMargin = YLBDialog.dip2px(context, 0);
        df = new DecimalFormat("###.00");
    }

    public void setmBodyList(List<GetMemberRecordModel.BodyEntity> mBodyList) {
        this.mBodyList.addAll(mBodyList);
//        getTotalPrice();
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return mBodyList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public GetMemberRecordModel.BodyEntity getGroup(int groupPosition) {
        return mBodyList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mBodyList.get(groupPosition).getGoodsBeanList();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_memberrecords_group, null);
            holder = new ViewHolderGroup(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderGroup) convertView.getTag();
        }

        holder.itemMemberrecordName.setText(mBodyList.get(groupPosition).getTitle());
        holder.itemMemberrecordDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(v, groupPosition);
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_memberrecord_child, null);
            holderChild = new ViewHolderChild(convertView);
            convertView.setTag(holderChild);
        } else {
            holderChild = (ViewHolderChild) convertView.getTag();
        }
        holderChild.itemMemberrecordTimeTx.setText(MyTools.convertTime(mBodyList.get(groupPosition).getAddTime(),"yyyy-MM-dd HH:mm"));

        if (mBodyList.get(groupPosition).getGoodsBeanList()!=null && mBodyList.get(groupPosition).getGoodsBeanList().size()>0) {
            int num = 0;
            for (int i=0;i<mBodyList.get(groupPosition).getGoodsBeanList().size();i++) {
                num += mBodyList.get(groupPosition).getGoodsBeanList().get(i).getGoodsCount();
            }
            holderChild.itemMemberrecordTotalTx.setText("共"+num+"件商品，合计¥"+df.format(mBodyList.get(groupPosition).getReceived()));
            MemberRecordChildAdpter adapter = new MemberRecordChildAdpter(context, mBodyList.get(groupPosition).getGoodsBeanList());
            holderChild.itemMemberrecordLv.setAdapter(adapter);
            holderChild.itemMemberrecordLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    context.startActivity(GoodsDetailsActivity.buildIntent(context,
                            mBodyList.get(groupPosition).getGoodsBeanList().get(position).getGoodsName(),
                            mBodyList.get(groupPosition).getGoodsBeanList().get(position).getGoodsId(),1,true));
                }
            });
        }

        holderChild.itemMemberrecordFlowlayout.removeAllViews();
        for (int i = 0; i < mBodyList.get(groupPosition).getMansongRuleList().size(); i++) {
            TextView view = new TextView(context);
            view.setText("全场满"+mBodyList.get(groupPosition).getMansongRuleList().get(i).getPrice()
                    +"减"+mBodyList.get(groupPosition).getMansongRuleList().get(i).getDiscount());
            view.setTextColor(Color.WHITE);
            view.setPadding(YLBDialog.dip2px(context, 5), YLBDialog.dip2px(context, 1),
                    YLBDialog.dip2px(context, 5), YLBDialog.dip2px(context, 1));
            view.setTextSize(10);
            view.setBackgroundColor(context.getResources().getColor(R.color.main_title_orange_color));
            holderChild.itemMemberrecordFlowlayout.addView(view, lp);
        }

        for (int i=0;i<mBodyList.get(groupPosition).getCouponList().size();i++) {
            TextView view = new TextView(context);
            view.setText("满"+mBodyList.get(groupPosition).getCouponList().get(i).getCouponLimit()
                    +"减"+mBodyList.get(groupPosition).getCouponList().get(i).getCouponPrice()+"优惠券");
            view.setTextColor(Color.WHITE);
            view.setPadding(YLBDialog.dip2px(context, 5), YLBDialog.dip2px(context, 1),
                    YLBDialog.dip2px(context, 5), YLBDialog.dip2px(context, 1));
            view.setTextSize(10);
            view.setBackgroundColor(context.getResources().getColor(R.color.main_title_orange_color));
            holderChild.itemMemberrecordFlowlayout.addView(view, lp);
        }

        if (mBodyList.get(groupPosition).getCouponList().size()>0
                || mBodyList.get(groupPosition).getMansongRuleList().size()>0) {
            holderChild.itemMemberrecordLl.setVisibility(View.VISIBLE);
        } else {
            holderChild.itemMemberrecordLl.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

//    private void getTotalPrice(){
//        totalPrice = 0;
//        totalGoods = 0;
//        for (int i=0;i<mBodyList.size();i++) {
//            totalPrice += mBodyList.get(i).getTotal();
//            if (mBodyList.get(i).getGoodsBeanList()!=null) {
//                totalGoods += mBodyList.get(i).getGoodsBeanList().size();
//            }
//        }
//    }

    public void clearData() {
        mBodyList.clear();
        notifyDataSetChanged();
    }

    public void clearItemById(int num) {
        mBodyList.remove(num);
        notifyDataSetChanged();
    }

    static class ViewHolderGroup {
        @Bind(R.id.item_memberrecord_name)
        TextView itemMemberrecordName;
        @Bind(R.id.item_memberrecord_delete)
        ImageView itemMemberrecordDelete;

        ViewHolderGroup(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderChild {
        @Bind(R.id.item_memberrecord_lv)
        MyListView itemMemberrecordLv;
        @Bind(R.id.item_memberrecord_flowlayout)
        XCFlowLayout itemMemberrecordFlowlayout;
        @Bind(R.id.item_memberrecord_time_tx)
        TextView itemMemberrecordTimeTx;
        @Bind(R.id.item_memberrecord_total_tx)
        TextView itemMemberrecordTotalTx;
        @Bind(R.id.item_memberrecord_ll)
        LinearLayout itemMemberrecordLl;

        ViewHolderChild(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
