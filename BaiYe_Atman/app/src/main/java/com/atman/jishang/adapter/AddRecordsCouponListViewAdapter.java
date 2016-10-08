package com.atman.jishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.model.AddRecordCouponListModel;
import com.atman.jishang.net.model.GetGoodsByClassIdModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.widget.YLBDialog;
import com.corelib.util.LogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/25 17:59
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AddRecordsCouponListViewAdapter extends BaseAdapter {

    private List<AddRecordCouponListModel.BodyEntity> body;
    private Context mContext;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;
    private ImageLoader mImageLoader;
    private AdapterInterface mOnBack;
    private DecimalFormat df;
    private LinearLayout.LayoutParams params;

    public AddRecordsCouponListViewAdapter(Context mContext, List<AddRecordCouponListModel.BodyEntity> body) {
        this.body = body;
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);
    }

    public void setBody(List<AddRecordCouponListModel.BodyEntity> body) {
        clearBody();
        this.body.addAll(body);
        notifyDataSetChanged();
    }

    public void clearBody() {
        this.body.clear();
    }

    @Override
    public int getCount() {
        return body.size();
    }

    @Override
    public AddRecordCouponListModel.BodyEntity getItem(int position) {
        return body.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_addrecordscouponlistview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AddRecordCouponListModel.BodyEntity mBodyEntity = body.get(position);

        LogUtils.e(">>>>>>>>>>>>");
        if (mBodyEntity.isUserd()) {
            holder.itemAddrecordCouponTx.setText(mBodyEntity.getCouponLimit() + "减" + mBodyEntity.getCouponPrice() + "元优惠券");
            holder.itemAddrecordCouponTx.setTextColor(mContext.getResources().getColor(R.color.color_black));
        } else {
            holder.itemAddrecordCouponTx.setText(mBodyEntity.getCouponLimit() + "减" + mBodyEntity.getCouponPrice() + "元优惠券(不满足使用条件)");
            holder.itemAddrecordCouponTx.setTextColor(mContext.getResources().getColor(R.color.main_title_orange_color));
        }

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_addrecord_coupon_tx)
        TextView itemAddrecordCouponTx;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
