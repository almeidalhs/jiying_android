package com.atman.jishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.atman.jishang.R;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.model.HomeGridViewDataModel;
import com.corelib.base.DefaultImageListener;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/21 17:42
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class DragListAdapter extends BaseAdapter {

    private Context mContext;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;
    private List<HomeGridViewDataModel.BodyEntity> dataSourceList;
    private ImageLoader mImageLoader;
    public boolean isHidden = true;
    private AdapterInterface onClick;

    public DragListAdapter(Context mContext, ImageLoader mImageLoader
            , List<HomeGridViewDataModel.BodyEntity> list, AdapterInterface onClick) {
        this.mContext = mContext;
        this.dataSourceList = list;
        this.mImageLoader = mImageLoader;
        this.onClick = onClick;
        layoutInflater = LayoutInflater.from(mContext);
    }

    public HomeGridViewDataModel.BodyEntity removeByPosition(int position) {
        HomeGridViewDataModel.BodyEntity temp = dataSourceList.get(position);
        temp.setUcState(0);
        dataSourceList.remove(position);
        this.notifyDataSetChanged();
        return temp;
    }

    public void addItemToEnd(HomeGridViewDataModel.BodyEntity body) {
        dataSourceList.add(body);
        this.notifyDataSetChanged();
    }

    public List<HomeGridViewDataModel.BodyEntity> getDataSourceList() {
        return dataSourceList;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
        this.notifyDataSetChanged();
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void remove(int arg0) {//删除指定位置的item
        dataSourceList.remove(arg0);
        this.notifyDataSetChanged();//不要忘记更改适配器对象的数据源
    }

    public void insert(HomeGridViewDataModel.BodyEntity item, int arg0) {//在指定位置插入item
        dataSourceList.add(arg0, item);
        this.notifyDataSetChanged();
    }

    public void update(int to, int from) {
        HomeGridViewDataModel.BodyEntity temp = dataSourceList.get(from);

        //这里的处理需要注意下
        if (from < to) {
            for (int i = from; i < to; i++) {
                Collections.swap(dataSourceList, i, i + 1);
            }
        } else if (from > to) {
            for (int i = from; i > to; i--) {
                Collections.swap(dataSourceList, i, i - 1);
            }
        }
        dataSourceList.set(to, temp);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataSourceList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSourceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_draglistview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HomeGridViewDataModel.BodyEntity.ConsoleBeanEntity mConsoleBean = dataSourceList.get(position).getConsoleBean();

        holder.itemDraglistviewName.setText(mConsoleBean.getConsoleName());
        holder.itemDraglistviewTitle.setText(mConsoleBean.getConsoleTitle());
        mImageLoader.get(mConsoleBean.getConsoleImageUrl(),
                new DefaultImageListener(holder.itemDraglistviewIc, R.mipmap.ic_launcher));

        if (isHidden) {
            holder.clickRemoveLl.setVisibility(View.GONE);
            holder.dragHandleLl.setVisibility(View.GONE);
        } else {
            holder.clickRemoveLl.setVisibility(View.VISIBLE);
            holder.dragHandleLl.setVisibility(View.VISIBLE);
        }

        holder.clickRemove.setOnClickListener(new View.OnClickListener() {
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
        @Bind(R.id.item_draglistview_ic)
        ImageView itemDraglistviewIc;
        @Bind(R.id.item_draglistview_name)
        TextView itemDraglistviewName;
        @Bind(R.id.item_draglistview_title)
        TextView itemDraglistviewTitle;
        @Bind(R.id.drag_handle)
        ImageView dragHandle;
        @Bind(R.id.drag_handle_ll)
        LinearLayout dragHandleLl;
//        @Bind(R.id.item_listview_rl)
//        RelativeLayout itemListviewRl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
