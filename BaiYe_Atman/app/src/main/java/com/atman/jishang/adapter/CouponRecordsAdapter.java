package com.atman.jishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.model.CouponRecordsModel;
import com.atman.jishang.utils.MyTools;

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
public class CouponRecordsAdapter extends BaseAdapter {

    private Context mContext;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;

    private List<CouponRecordsModel.BodyEntity> mListData;

    public CouponRecordsAdapter(Context context) {
        this.mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
        mListData = new ArrayList<>();
    }

    public List<CouponRecordsModel.BodyEntity> getmListData() {
        return mListData;
    }

    public void setmListData(List<CouponRecordsModel.BodyEntity> mListData) {
        this.mListData.addAll(mListData);
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
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_couponrecords, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CouponRecordsModel.BodyEntity mBodyEntity = mListData.get(position);

        holder.itemRecordsDate.setText(MyTools.convertTime(mBodyEntity.getAddTime(),"yyyy-MM-dd hh:mm"));
        holder.itemRecordsNumber.setText(mBodyEntity.getPhone());
        if (mBodyEntity.getStatus() == 1) {
            holder.itemRecordsIsuser.setText("是");
        } else {
            holder.itemRecordsIsuser.setText("否");
        }

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_records_date)
        TextView itemRecordsDate;
        @Bind(R.id.item_records_number)
        TextView itemRecordsNumber;
        @Bind(R.id.item_records_code)
        TextView itemRecordsCode;
        @Bind(R.id.item_records_isuser)
        TextView itemRecordsIsuser;
        @Bind(R.id.item_records_line)
        View itemRecordsLine;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
