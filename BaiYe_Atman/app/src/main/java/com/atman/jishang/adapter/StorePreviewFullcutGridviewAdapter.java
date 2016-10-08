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
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.model.GetCouponListModel;
import com.atman.jishang.utils.MyTools;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;
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
public class StorePreviewFullcutGridviewAdapter extends BaseAdapter {

    private List<GetCouponListModel.BodyEntity> body;
    private Context mContext;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;
    private ImageLoader mImageLoader;
    private AdapterInterface mOnBack;
    private DecimalFormat df;
    private LinearLayout.LayoutParams params;

    public StorePreviewFullcutGridviewAdapter(Context mContext, List<GetCouponListModel.BodyEntity> body) {
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
            convertView = layoutInflater.inflate(R.layout.item_storepreview_coupon_gridview, null);
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

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_storepreview_fullcut_discount_tx)
        TextView itemStorepreviewFullcutDiscountTx;
        @Bind(R.id.item_storepreview_fullcut_price_tx)
        TextView itemStorepreviewFullcutPriceTx;
        @Bind(R.id.item_storepreview_fullcut_root_ll)
        LinearLayout itemStorepreviewFullcutRootLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
