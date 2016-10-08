package com.atman.jishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atman.jishang.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/27 13:46
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MemberConditionFilterChildAdpter extends BaseAdapter {

    private Context context;
    private List<itemModel> list = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private ViewHolder holder;
    private int selectId = -1;

    public MemberConditionFilterChildAdpter(Context context, List<String> list) {
        this.context = context;
        if (list!=null) {
            for (int i=0;i<list.size();i++) {
                this.list.add(new itemModel(list.get(i)));
            }
        }
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getSelectId() {
        return selectId;
    }

    public void setSelectId(int selectId) {
        this.selectId = selectId;
        for (int i=0;i<list.size();i++) {
            if (i==selectId) {
                list.get(selectId).setSelect(true);
            } else {
                list.get(i).setSelect(false);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public itemModel getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_memberconditionfilter_child_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.itemMemberconditionChildTx.setText(list.get(position).getStr());

        if (position == selectId) {
            holder.itemMemberconditionChildTx.setBackgroundResource(R.drawable.memberfilter_item_select_bg);
            holder.itemMemberconditionChildTx.setTextColor(context.getResources().getColor(R.color.color_white));
        } else {
            holder.itemMemberconditionChildTx.setBackgroundResource(R.drawable.memberfilter_item_unselect_bg);
            holder.itemMemberconditionChildTx.setTextColor(context.getResources().getColor(R.color.color_black));
        }

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_membercondition_child_tx)
        TextView itemMemberconditionChildTx;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class itemModel {
        private String str;
        private boolean select;

        public itemModel(String str){
            this.str = str;
            select = false;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public String getStr() {
            return str;
        }
    }
}
