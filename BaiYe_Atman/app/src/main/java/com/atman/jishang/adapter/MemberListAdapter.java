package com.atman.jishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.GetMemberListModel;
import com.atman.jishang.net.Urls;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.utils.MyTools;
import com.corelib.widget.ShapeImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/24 13:33
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MemberListAdapter extends BaseAdapter {

    private Context mContext;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;
    private ImageLoader mImageLoader;
    private AdapterInterface onItemClick;
    private List<GetMemberListModel.BodyEntity.DataListEntity> mListData;
    private boolean isSelected = false;
    private DecimalFormat df;
    private boolean isSetData = true;

    public MemberListAdapter(Context context, AdapterInterface onItemClick) {
        this.mContext = context;
        this.onItemClick = onItemClick;
        layoutInflater = LayoutInflater.from(mContext);
        mListData = new ArrayList<>();
        mImageLoader = ImageLoader.getInstance();
        df = new DecimalFormat("##0.00");
    }

    public void setSetData(boolean setData) {
        isSetData = setData;
    }

    public List<GetMemberListModel.BodyEntity.DataListEntity> getmListData() {
        return mListData;
    }

    public void setmListData(List<GetMemberListModel.BodyEntity.DataListEntity> mListData) {
        this.mListData.addAll(mListData);
        setSetData(true);
        notifyDataSetChanged();
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        for (int i=0;i<mListData.size();i++) {
            mListData.get(i).setSelect(isSelected);
        }
        notifyDataSetChanged();
    }

    public void clearData() {
        mListData.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public GetMemberListModel.BodyEntity.DataListEntity getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        GetMemberListModel.BodyEntity.DataListEntity mBodyEntity = mListData.get(position);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_memberlist, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (isSetData) {
            String url = "";
            if (mBodyEntity.getAvatar() != null) {
                if (mBodyEntity.getAvatar().startsWith("http")) {
                    url = mBodyEntity.getAvatar();
                } else {
                    url = Urls.RWH_HOST_IMG + "by/" + mBodyEntity.getAvatar();
                }
            }
            mImageLoader.displayImage(url, holder.itemMemberlistHeadIv, BaiYeBaseApplication.getApp().getOptionsHead());
        }

        holder.itemMemberlistNameTx.setText(mBodyEntity.getName());
        holder.itemMemberlistPhoneTx.setText(mBodyEntity.getMobile());
        holder.itemMemberlistIntegralTx.setText("消费："+df.format(mBodyEntity.getTotalConsume()));
        if (mBodyEntity.isSelect()) {
            holder.itemMemberlistSelectIv.setImageResource(R.mipmap.member_one_item_select);
        } else {
            holder.itemMemberlistSelectIv.setImageResource(R.mipmap.member_one_item_unselect);
        }

        holder.itemMemberlistSelectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(v, position);
            }
        });

        if (mBodyEntity.isSex()) {
            holder.itemMemberlistSexIv.setImageResource(R.mipmap.member_boy_ic);
        } else {
            holder.itemMemberlistSexIv.setImageResource(R.mipmap.member_girl_ic);
        }

        return convertView;
    }

    public boolean setSelectById(int position) {
        setSetData(false);
        if (mListData.get(position).isSelect()) {
            mListData.get(position).setSelect(false);
        } else {
            mListData.get(position).setSelect(true);
        }
        return isSelectedAll();
    }

    public boolean isSelectedAll(){
        int num = 0;
        for (int i=0;i<mListData.size();i++) {
            if (mListData.get(i).isSelect()) {
                num += 1;
            }
        }
        notifyDataSetChanged();
        return num==mListData.size();
    }

    static class ViewHolder {
        @Bind(R.id.item_memberlist_head_iv)
        ShapeImageView itemMemberlistHeadIv;
        @Bind(R.id.item_memberlist_sex_iv)
        ImageView itemMemberlistSexIv;
        @Bind(R.id.item_memberlist_name_tx)
        TextView itemMemberlistNameTx;
        @Bind(R.id.item_memberlist_phone_tx)
        TextView itemMemberlistPhoneTx;
        @Bind(R.id.item_memberlist_integral_tx)
        TextView itemMemberlistIntegralTx;
        @Bind(R.id.item_memberlist_tag_tx)
        TextView itemMemberlistTagTx;
        @Bind(R.id.item_memberlist_select_iv)
        ImageView itemMemberlistSelectIv;
        @Bind(R.id.item_memberlist_line_iv)
        ImageView itemMemberlistLineIv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
