package com.atman.jishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.model.GetStoreClassModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/27 13:30
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GoodsClasesAdapter extends BaseAdapter {

    private Context mContext;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;
    private List<GetStoreClassModel.BodyEntity> list;

    public GoodsClasesAdapter(Context context, List<GetStoreClassModel.BodyEntity> list) {
        this.list = list;
        this.mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public GetStoreClassModel.BodyEntity getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_storeclass, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GetStoreClassModel.BodyEntity mBodyEntity = list.get(position);
        holder.itemStoreclassName.setText(mBodyEntity.getStcName());
        holder.itemStoreclassNum.setText("共"+mBodyEntity.getGoodsCount()+"件商品");

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_storeclass_name)
        TextView itemStoreclassName;
        @Bind(R.id.item_storeclass_num)
        TextView itemStoreclassNum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
