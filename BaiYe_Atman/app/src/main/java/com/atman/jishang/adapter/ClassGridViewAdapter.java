package com.atman.jishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.model.StoreGoodsClassesModel;

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
public class ClassGridViewAdapter extends BaseAdapter {

    private List<StoreGoodsClassesModel.BodyEntity> body;
    private Context mContext;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;

    public ClassGridViewAdapter(Context mContext, List<StoreGoodsClassesModel.BodyEntity> body) {
        this.body = body;
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return body.size();
    }

    @Override
    public StoreGoodsClassesModel.BodyEntity getItem(int position) {
        return body.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_class_popwin, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        StoreGoodsClassesModel.BodyEntity mBodyEntity = body.get(position);
        holder.itemClassPopwinTx.setText(mBodyEntity.getStcName());

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_class_popwin_tx)
        TextView itemClassPopwinTx;
        @Bind(R.id.item_class_popwin_ll)
        LinearLayout itemClassPopwinLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
