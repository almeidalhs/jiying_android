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
import com.atman.jishang.net.model.GetStoreClassModel;

import java.util.Collections;
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
public class EditGoodsClasesAdapter extends BaseAdapter {

    private Context mContext;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;
    private List<GetStoreClassModel.BodyEntity> list;
    private AdapterInterface onClick;

    public EditGoodsClasesAdapter(Context context, List<GetStoreClassModel.BodyEntity> list, AdapterInterface onClick) {
        this.list = list;
        this.mContext = context;
        this.onClick = onClick;
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

    public void removeByPosition(int arg0) {//删除指定位置的item
        list.remove(arg0);
        this.notifyDataSetChanged();//不要忘记更改适配器对象的数据源
    }

    public void setNameByPositon(int p, String name){
        if (p==-1) {
            return;
        }
        list.get(p).setStcName(name);
        notifyDataSetChanged();
    }

    public void insert(GetStoreClassModel.BodyEntity item, int arg0) {//在指定位置插入item
        list.add(arg0, item);
        this.notifyDataSetChanged();
    }

    public void update(int to, int from) {
        GetStoreClassModel.BodyEntity temp = list.get(from);

        //这里的处理需要注意下
        if (from < to) {
            for (int i = from; i < to; i++) {
                Collections.swap(list, i, i + 1);
            }
        } else if (from > to) {
            for (int i = from; i > to; i--) {
                Collections.swap(list, i, i - 1);
            }
        }
        list.set(to, temp);
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_editlistview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position == 0) {
            holder.clickRemove.setVisibility(View.INVISIBLE);
            holder.editName.setVisibility(View.INVISIBLE);
            holder.dragHandle.setVisibility(View.INVISIBLE);
        } else {
            holder.clickRemove.setVisibility(View.VISIBLE);
            holder.editName.setVisibility(View.VISIBLE);
            holder.dragHandle.setVisibility(View.VISIBLE);
        }

        GetStoreClassModel.BodyEntity mBodyEntity = list.get(position);
        holder.itemDraglistviewName.setText(mBodyEntity.getStcName());
        holder.itemDraglistviewTitle.setText("共" + mBodyEntity.getGoodsCount() + "件商品");
        holder.clickRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(v,position);
            }
        });
        holder.editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(v,position);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.click_remove)
        ImageView clickRemove;
        @Bind(R.id.click_remove_ll)
        LinearLayout clickRemoveLl;
        @Bind(R.id.item_draglistview_name)
        TextView itemDraglistviewName;
        @Bind(R.id.item_draglistview_title)
        TextView itemDraglistviewTitle;
        @Bind(R.id.edit_name)
        ImageView editName;
        @Bind(R.id.edit_name_ll)
        LinearLayout editNameLl;
        @Bind(R.id.drag_handle)
        ImageView dragHandle;
        @Bind(R.id.drag_handle_ll)
        LinearLayout dragHandleLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
