package com.atman.jishang.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.model.GetGoodsByClassIdModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.widget.YLBDialog;
import com.corelib.base.DefaultImageListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

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
public class GoodsListViewAdapter extends BaseAdapter {

    private List<GetGoodsByClassIdModel.BodyEntity> body;
    private Context mContext;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;
    private ImageLoader mImageLoader;
    private AdapterInterface mOnBack;
    private DecimalFormat df;
    private LinearLayout.LayoutParams params;

    public GoodsListViewAdapter(Context mContext, int wight, ImageLoader imageLoader
            , AdapterInterface mOnBack) {
        this.body = new ArrayList<>();
        this.mContext = mContext;
        this.mImageLoader = imageLoader;
        this.mOnBack = mOnBack;
        layoutInflater = LayoutInflater.from(mContext);
        df = new DecimalFormat("##0.00");
        int w = (wight - YLBDialog.dip2px(mContext, 25)) / 2;
        params = new LinearLayout.LayoutParams(w, w);
    }

    public void setBody(List<GetGoodsByClassIdModel.BodyEntity> body) {
        this.body.addAll(body);
        this.notifyDataSetChanged();
    }

    public void clearBody() {
        this.body.clear();
    }

    @Override
    public int getCount() {
        return body.size();
    }

    @Override
    public GetGoodsByClassIdModel.BodyEntity getItem(int position) {
        return body.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_goodslistview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GetGoodsByClassIdModel.BodyEntity mBodyEntity = body.get(position);

        ImageLoader.getInstance().displayImage(mBodyEntity.getFullGoodsImage(),
                holder.itemGoodslistviewPicture,
                BaiYeBaseApplication.getApp().getOptions());
        if(mBodyEntity.getGoodsDescription() == null || mBodyEntity.getGoodsDescription().isEmpty()){//#B7B7B7
            holder.itemGoodslistviewDescription.setText("暂未添加商品说明");
            holder.itemGoodslistviewDescription.setTextColor(mContext.getResources().getColor(R.color.goodslist_dec_color));
        } else {
            holder.itemGoodslistviewDescription.setText(mBodyEntity.getGoodsDescription());
        }
        holder.itemGoodslistviewName.setText(mBodyEntity.getGoodsName());
        holder.itemGoodslistviewPrice.setText("¥ " + df.format(mBodyEntity.getPrice()));

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_goodslistview_picture)
        ImageView itemGoodslistviewPicture;
        @Bind(R.id.item_goodslistview_name)
        TextView itemGoodslistviewName;
        @Bind(R.id.item_goodslistview_price)
        TextView itemGoodslistviewPrice;
        @Bind(R.id.item_goodslistview_description)
        TextView itemGoodslistviewDescription;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
