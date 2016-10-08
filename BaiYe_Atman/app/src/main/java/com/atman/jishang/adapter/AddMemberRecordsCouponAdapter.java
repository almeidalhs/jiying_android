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
import com.atman.jishang.net.model.AddRecordCouponListModel;
import com.atman.jishang.utils.MyTools;

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
public class AddMemberRecordsCouponAdapter extends BaseAdapter {

    private List<AddRecordCouponListModel.BodyEntity> body;
    private Context mContext;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;
    private AdapterInterface mOnClick;

    public AddMemberRecordsCouponAdapter(Context mContext, List<AddRecordCouponListModel.BodyEntity> body
            , AdapterInterface mOnClick) {
        this.body = body;
        this.mContext = mContext;
        this.mOnClick = mOnClick;
        layoutInflater = LayoutInflater.from(mContext);
    }

    public void setBody(List<AddRecordCouponListModel.BodyEntity> body) {
        this.body.addAll(body);
        this.notifyDataSetChanged();
    }

    public void clearBody() {
        this.body.clear();
    }

    @Override
    public int getCount() {
        return body.size();
    }

    public List<AddRecordCouponListModel.BodyEntity> getSelect() {

        List<AddRecordCouponListModel.BodyEntity> tempList = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            if (body.get(i).isSelect()) {
                tempList.add(body.get(i));
            }
        }
        return tempList;
    }

    @Override
    public AddRecordCouponListModel.BodyEntity getItem(int position) {
        return body.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void selectItem(int num) {
        for (int i = 0; i < body.size(); i++) {
            if (i == num) {
                if (body.get(i).isSelect()) {
                    body.get(i).setSelect(false);
                } else {
                    body.get(i).setSelect(true);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void selectItemById (int num) {
        for (int i=0;i<body.size();i++) {
            if (body.get(i).getId()==num) {
                if (body.get(i).isSelect()) {
                    body.get(i).setSelect(false);
                } else {
                    body.get(i).setSelect(true);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_addrecord_coupon_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AddRecordCouponListModel.BodyEntity mBodyEntity = body.get(position);

        holder.itemAddrecordCouponNameTv.setText(mBodyEntity.getCouponLimit() + "减" + mBodyEntity.getCouponPrice() + "元优惠券");
        holder.itemAddrecordCouponTimeTv.setText("(" + MyTools.convertTime(mBodyEntity.getCouponStartDate(), "yyyy-MM-dd hh:mm")
                + "-" + MyTools.convertTime(mBodyEntity.getCouponEndDate(), "yyyy-MM-dd hh:mm") + ")");

        if (mBodyEntity.isSelect()) {
            holder.itemAddrecordCouponSelectIv.setBackgroundResource(R.mipmap.addrecord_active_select);
        } else {
            holder.itemAddrecordCouponSelectIv.setBackgroundResource(R.mipmap.addrecord_active_unselect);
        }

        holder.itemAddrecordCouponSelectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClick.onItemClick(v, position);
            }
        });

        if (mBodyEntity.getCouponState() == 3) {
            holder.itemAddrecordCouponRootLl.setBackgroundColor(mContext.getResources().getColor(R.color.overdue_bg));
        } else {
            holder.itemAddrecordCouponRootLl.setBackgroundColor(mContext.getResources().getColor(R.color.color_white));
        }

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_addrecord_coupon_name_tv)
        TextView itemAddrecordCouponNameTv;
        @Bind(R.id.item_addrecord_coupon_time_tv)
        TextView itemAddrecordCouponTimeTv;
        @Bind(R.id.item_addrecord_coupon_select_iv)
        ImageView itemAddrecordCouponSelectIv;
        @Bind(R.id.item_addrecord_coupon_root_ll)
        LinearLayout itemAddrecordCouponRootLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
