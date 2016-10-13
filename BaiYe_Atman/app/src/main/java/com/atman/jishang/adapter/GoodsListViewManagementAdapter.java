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
import com.atman.jishang.net.Urls;
import com.atman.jishang.net.model.GetGoodsByClassIdModel;
import com.atman.jishang.net.model.ModifiedClassificationModel;
import com.atman.jishang.net.model.OrderManageListModel;
import com.atman.jishang.net.model.UnShelveOrShelveModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.widget.YLBDialog;
import com.corelib.base.DefaultImageListener;
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
public class GoodsListViewManagementAdapter extends BaseAdapter {

    private List<GetGoodsByClassIdModel.BodyEntity> body;
    private Context mContext;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;
    private ImageLoader mImageLoader;
    private DecimalFormat df;
    private LinearLayout.LayoutParams params;
    private AdapterInterface mOnBack;
    public boolean isAllSelect = false;

    public GoodsListViewManagementAdapter(Context mContext, int wight, ImageLoader imageLoader, AdapterInterface mOnBack) {
        this.body = new ArrayList<>();
        this.mContext = mContext;
        this.mImageLoader = imageLoader;
        this.mOnBack = mOnBack;
        layoutInflater = LayoutInflater.from(mContext);
        df = new DecimalFormat("##0.00");
        int w = (wight - YLBDialog.dip2px(mContext, 25)) / 2;
        params = new LinearLayout.LayoutParams(w, w);
    }

    public List<GetGoodsByClassIdModel.BodyEntity> getSelectedList() {
        List<GetGoodsByClassIdModel.BodyEntity> list = new ArrayList<>();
        for (int i=0;i<body.size();i++) {
            if (body.get(i).isSelect()) {
                list.add(body.get(i));
            }
        }
        return list;
    }

    public void setBody(List<GetGoodsByClassIdModel.BodyEntity> body) {
        this.body.addAll(body);
        if (isAllSelect) {
            selectAll();
        }
        this.notifyDataSetChanged();
    }

    public void deleteItemById(List<UnShelveOrShelveModel> deleteList){
        for (int i = 0; i < this.body.size(); i++) {
            for (int j=0;j<deleteList.size();j++) {
                if (deleteList.get(j).getId() == body.get(i).getId()) {
                    body.remove(i);
                    continue;
                }
            }
        }
        notifyDataSetChanged();
    }

    public void deleteItemByIdTwo(List<ModifiedClassificationModel> deleteList){
        for (int i = 0; i < this.body.size(); i++) {
            for (int j=0;j<deleteList.size();j++) {
                if (deleteList.get(j).getId() == body.get(i).getId()) {
                    body.remove(i);
                    continue;
                }
            }
        }
        notifyDataSetChanged();
    }

    public void selectAll() {
        for (int i = 0; i < this.body.size(); i++) {
            this.body.get(i).setSelect(true);
            isAllSelect = true;
        }
        notifyDataSetChanged();
    }

    public void unselectAll() {
        for (int i = 0; i < this.body.size(); i++) {
            this.body.get(i).setSelect(false);
            isAllSelect = false;
        }
        notifyDataSetChanged();
    }

    public void selectByPosition(int p) {
        for (int i = 0; i < this.body.size(); i++) {
            if (p == i) {
                if (this.body.get(i).isSelect()) {
                    this.body.get(i).setSelect(false);
                    isAllSelect = false;
                } else {
                    this.body.get(i).setSelect(true);
                }
            }
        }
        isAllSelect();
        notifyDataSetChanged();
    }

    public void isAllSelect() {
        int p = 0;
        for (int i = 0; i < this.body.size(); i++) {
            if (this.body.get(i).isSelect()) {
                p += 1;
            }
        }
        if (p == body.size()) {
            isAllSelect = true;
        } else {
            isAllSelect = false;
        }
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

    public List<GetGoodsByClassIdModel.BodyEntity> getSelectedItem() {
        List<GetGoodsByClassIdModel.BodyEntity> slectedItem = new ArrayList<>();
        for (int i = 0; i < this.body.size(); i++) {
            if (body.get(i).isSelect()) {
                slectedItem.add(body.get(i));
            }
        }
        return slectedItem;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        GetGoodsByClassIdModel.BodyEntity mBodyEntity = body.get(position);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_goodslistview_managerment, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

            String url = mBodyEntity.getGoodsImage();
            if (!url.startsWith("http")) {
                url =  Urls.RWH_HOST_IMG + "by/" + mBodyEntity.getGoodsImage();
            }
            ImageLoader.getInstance().displayImage(url, holder.itemGoodslistviewManagermentPicture,
                    BaiYeBaseApplication.getApp().getOptions());
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.itemGoodslistviewManagermentDescription.setText(mBodyEntity.getGoodsDescription());
        holder.itemGoodslistviewManagermentName.setText(mBodyEntity.getGoodsName());
        holder.itemGoodslistviewManagermentPrice.setText("¥ " + df.format(mBodyEntity.getPrice()));
        holder.itemGoodslistviewManagermentSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectByPosition(position);
                mOnBack.onItemClick(v, position);
            }
        });
        if (mBodyEntity.isSelect()) {
            holder.itemGoodslistviewManagermentSelect.setImageResource(R.mipmap.selected);
            holder.itemGoodslistviewManagermentLl.setBackgroundColor(mContext.getResources()
                    .getColor(R.color.goods_list_manage_selected));
        } else {
            holder.itemGoodslistviewManagermentSelect.setImageResource(R.mipmap.un_select);
            holder.itemGoodslistviewManagermentLl.setBackgroundColor(mContext.getResources()
                    .getColor(R.color.goods_list_manage_unselected));
        }

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_goodslistview_managerment_select)
        ImageView itemGoodslistviewManagermentSelect;
        @Bind(R.id.item_goodslistview_managerment_picture)
        ImageView itemGoodslistviewManagermentPicture;
        @Bind(R.id.item_goodslistview_managerment_name)
        TextView itemGoodslistviewManagermentName;
        @Bind(R.id.item_goodslistview_managerment_price)
        TextView itemGoodslistviewManagermentPrice;
        @Bind(R.id.item_goodslistview_managerment_description)
        TextView itemGoodslistviewManagermentDescription;
        @Bind(R.id.item_goodslistview_managerment_ll)
        LinearLayout itemGoodslistviewManagermentLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
