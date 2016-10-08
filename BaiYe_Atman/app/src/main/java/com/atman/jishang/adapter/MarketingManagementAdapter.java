package com.atman.jishang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.model.MarketListModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.widget.YLBDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述 营销管理
 * 作者 tangbingliang
 * 时间 16/5/16 17:17
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MarketingManagementAdapter extends BaseAdapter {

    private List<MarketListModel.BodyEntity> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private LinearLayout.LayoutParams params;
    private RelativeLayout.LayoutParams paramsIcon;
    private ViewHolder holder;
    private ImageLoader mImageLoader;

    public MarketingManagementAdapter(Context context, int wight, List<MarketListModel.BodyEntity> list) {
        this.context = context;
        this.list = list;
        mImageLoader = ImageLoader.getInstance();
        layoutInflater = LayoutInflater.from(context);
        int w = wight ;
        int h = (w * 170 / 724) ;
        params = new LinearLayout.LayoutParams(w, h);
        h = h/2;
        w = h * 103 /86;
        paramsIcon = new RelativeLayout.LayoutParams(w, h);
        paramsIcon.setMargins(YLBDialog.dip2px(context, 25),h/2,YLBDialog.dip2px(context, 25),h/2);
    }

    public void setBody(List<MarketListModel.BodyEntity> body) {
        this.list.addAll(body);
        this.notifyDataSetChanged();
    }

    public void clearBody() {
        this.list.clear();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public MarketListModel.BodyEntity getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_marketlist, null);
            holder = new ViewHolder(convertView);
            holder.itemMarketlistRootRl.setLayoutParams(params);
            holder.itemMarketlistIconIv.setLayoutParams(paramsIcon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MarketListModel.BodyEntity mBodyEntity = list.get(position);

        holder.itemMarketlistTitleTx.setText(mBodyEntity.getTitle());
        holder.itemMarketlistRemarkTx.setText(mBodyEntity.getRemark());
        mImageLoader.displayImage(mBodyEntity.getIcon(), holder.itemMarketlistIconIv,
                BaiYeBaseApplication.getApp().getOptions());
        mImageLoader.displayImage(mBodyEntity.getBackgroundImg(), holder.itemMarketlistBgIv,
                BaiYeBaseApplication.getApp().getOptionsNot());
        holder.itemMarketlistRootRl.setBackgroundColor(Color.parseColor("#" + mBodyEntity.getBackgroundColor().trim()));

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_marketlist_bg_iv)
        ImageView itemMarketlistBgIv;
        @Bind(R.id.item_marketlist_icon_iv)
        ImageView itemMarketlistIconIv;
        @Bind(R.id.item_marketlist_title_tx)
        TextView itemMarketlistTitleTx;
        @Bind(R.id.item_marketlist_remark_tx)
        TextView itemMarketlistRemarkTx;
        @Bind(R.id.item_marketlist_root_rl)
        RelativeLayout itemMarketlistRootRl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
