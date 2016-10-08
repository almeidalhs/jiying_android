package com.atman.jishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.atman.jishang.R;
import com.atman.jishang.net.model.NewsListModel;
import com.corelib.base.DefaultImageListener;

import java.util.ArrayList;
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
public class CircleListAdapter extends BaseAdapter {

    private Context mContext;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;
    private List<NewsListModel.BodyEntity> list;
    private ImageLoader mImageLoader;

    public CircleListAdapter(Context context, ImageLoader mImageLoader) {
        this.mContext = context;
        this.mImageLoader = mImageLoader;
        layoutInflater = LayoutInflater.from(mContext);
        list = new ArrayList<>();
    }

    public void setBody(List<NewsListModel.BodyEntity> list) {
        this.list.addAll(list);
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
    public NewsListModel.BodyEntity getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_circlelist, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewsListModel.BodyEntity mBodyEntity = list.get(position);
        mImageLoader.get(mBodyEntity.getFullTitleImage(),
                new DefaultImageListener(holder.itemNewslistIc, R.mipmap.ic_launcher));
        holder.itemNewslistTitle.setText(mBodyEntity.getTitle());
        holder.itemNewslistIntroduction.setText(mBodyEntity.getDescription());

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_newslist_ic)
        ImageView itemNewslistIc;
        @Bind(R.id.item_newslist_title)
        TextView itemNewslistTitle;
        @Bind(R.id.item_newslist_introduction)
        TextView itemNewslistIntroduction;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
