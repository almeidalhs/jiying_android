package com.atman.jishang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.model.GetCouponListModel;
import com.atman.jishang.utils.MyTools;

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
public class StorePreviewCouponListviewAdapter extends BaseAdapter {

    private List<GetCouponListModel.BodyEntity> body;
    private Context mContext;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;

    public StorePreviewCouponListviewAdapter(Context mContext, List<GetCouponListModel.BodyEntity> body) {
        this.body = body;
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);
    }

    public void setBody(List<GetCouponListModel.BodyEntity> body) {
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
    public GetCouponListModel.BodyEntity getItem(int position) {
        return body.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_storepreview_coupon_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GetCouponListModel.BodyEntity mBodyEntity = body.get(position);

        holder.itemStorepreviewFullcutDiscountTx.setText(mBodyEntity.getCouponPrice() + "");
        holder.itemStorepreviewFullcutPriceTx.setText("满" + mBodyEntity.getCouponLimit() + "可用");

        GradientDrawable mGradientDrawable = (GradientDrawable) holder.itemStorepreviewFullcutRootLl.getBackground();
        mGradientDrawable.setColor(Color.parseColor(MyTools.RandomColor()));

        holder.itemStorepreviewFullcutSurplusTx.setText("剩余"+(mBodyEntity.getCouponStorage()-mBodyEntity.getCouponUsage())+"张");
        holder.itemStorepreviewFullcutLimitTx.setText("每人限领"+mBodyEntity.getUserReceiveLimit()+"张");
        holder.itemStorepreviewFullcutStartTx.setText("开始："+MyTools.convertTime(mBodyEntity.getCouponStartDate(), "yyyy-MM-dd HH:mm"));
        holder.itemStorepreviewFullcutEndTx.setText("结束："+MyTools.convertTime(mBodyEntity.getCouponEndDate(), "yyyy-MM-dd HH:mm"));

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_storepreview_fullcut_discount_tx)
        TextView itemStorepreviewFullcutDiscountTx;
        @Bind(R.id.item_storepreview_fullcut_price_tx)
        TextView itemStorepreviewFullcutPriceTx;
        @Bind(R.id.item_storepreview_fullcut_root_ll)
        LinearLayout itemStorepreviewFullcutRootLl;
        @Bind(R.id.item_storepreview_fullcut_surplus_tx)
        TextView itemStorepreviewFullcutSurplusTx;
        @Bind(R.id.item_storepreview_fullcut_limit_tx)
        TextView itemStorepreviewFullcutLimitTx;
        @Bind(R.id.item_storepreview_fullcut_start_tx)
        TextView itemStorepreviewFullcutStartTx;
        @Bind(R.id.item_storepreview_fullcut_end_tx)
        TextView itemStorepreviewFullcutEndTx;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
