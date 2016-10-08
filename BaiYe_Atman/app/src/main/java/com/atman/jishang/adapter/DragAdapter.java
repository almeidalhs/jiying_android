package com.atman.jishang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.model.HomeGridViewDataModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.corelib.base.DefaultImageListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DragAdapter extends BaseAdapter {
    private Context context;
    private List<HomeGridViewDataModel.BodyEntity> strList;
    private int hidePosition = AdapterView.INVALID_POSITION;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;
    private ImageLoader mImageLoader;

    public DragAdapter(Context context, List<HomeGridViewDataModel.BodyEntity> strList) {
        this.context = context;
        this.strList = strList;
        layoutInflater = LayoutInflater.from(context);
        mImageLoader = ImageLoader.getInstance();
    }

    public List<HomeGridViewDataModel.BodyEntity> getList() {
        return strList;
    }

    @Override
    public int getCount() {
        return strList.size();
    }

    @Override
    public HomeGridViewDataModel.BodyEntity getItem(int position) {
        return strList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_home_gridview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HomeGridViewDataModel.BodyEntity.ConsoleBeanEntity mConsoleBean = strList.get(position).getConsoleBean();

        holder.itemHomeGvTx.setText(mConsoleBean.getConsoleName());
        ImageLoader.getInstance().displayImage(mConsoleBean.getConsoleImageUrl(),
                holder.itemHomeGvIv,
                BaiYeBaseApplication.getApp().getOptions());
        if (mConsoleBean.getConsoleBgImage()!=null
                && !mConsoleBean.getConsoleBgImage().trim().isEmpty()) {
            holder.itemHomeGvLl.setBackgroundColor(Color.parseColor("#" + mConsoleBean.getConsoleBgImage().trim()));
        }

        holder.itemHomeGvLl.setBackgroundColor(context.getResources().getColor(R.color.color_ffffff));

        return convertView;
    }

    public void hideView(int pos) {
        hidePosition = pos;
        notifyDataSetChanged();
    }

    public void showHideView() {
        hidePosition = AdapterView.INVALID_POSITION;
        notifyDataSetChanged();
    }

    public void removeView(int pos) {
        strList.remove(pos);
        notifyDataSetChanged();
    }

    //更新拖动时的gridView
    public void swapView(int draggedPos, int destPos) {
        //从前向后拖动，其他item依次前移
        if (draggedPos < destPos) {
            strList.add(destPos + 1, getItem(draggedPos));
            strList.remove(draggedPos);
        }
        //从后向前拖动，其他item依次后移
        else if (draggedPos > destPos) {
            strList.add(destPos, getItem(draggedPos));
            strList.remove(draggedPos + 1);
        }
        hidePosition = destPos;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @Bind(R.id.item_home_gv_iv)
        ImageView itemHomeGvIv;
        @Bind(R.id.item_home_gv_tx)
        TextView itemHomeGvTx;
        @Bind(R.id.item_home_gv_ll)
        LinearLayout itemHomeGvLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}