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
import com.atman.jishang.net.model.AddRecordFullCutListModel;
import com.atman.jishang.net.model.GetGoodsByClassIdModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.utils.MyTools;
import com.atman.jishang.widget.YLBDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;
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
public class AddMemberRecordsFullCutAdapter extends BaseAdapter {

    private List<AddRecordFullCutListModel.BodyEntity> body;
    private Context mContext;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;
    private AdapterInterface mOnClick;

    public AddMemberRecordsFullCutAdapter(Context mContext, List<AddRecordFullCutListModel.BodyEntity> body, AdapterInterface mOnClick) {
        this.body = body;
        this.mContext = mContext;
        this.mOnClick = mOnClick;
        layoutInflater = LayoutInflater.from(mContext);
    }

    public void setBody(List<AddRecordFullCutListModel.BodyEntity> body) {
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

    public AddRecordFullCutListModel.BodyEntity getSelect() {
        AddRecordFullCutListModel.BodyEntity temp = new AddRecordFullCutListModel.BodyEntity();
        for (int i=0;i<body.size();i++) {
            if (body.get(i).isSelect()) {
                temp = body.get(i);
            }
        }
        return temp;
    }

    @Override
    public AddRecordFullCutListModel.BodyEntity getItem(int position) {
        return body.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void selectItem (int num) {
        for (int i=0;i<body.size();i++) {
            if (i==num) {
                if (body.get(i).isSelect()) {
                    body.get(i).setSelect(false);
                } else {
                    body.get(i).setSelect(true);
                }
            } else {
                body.get(i).setSelect(false);
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
            } else {
                body.get(i).setSelect(false);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_addrecord_fullcut_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AddRecordFullCutListModel.BodyEntity mBodyEntity = body.get(position);

        holder.itemAddrecordNameTv.setText("全场满"+mBodyEntity.getPrice()+"减"+mBodyEntity.getDiscount()+"元");
        holder.itemAddrecordTimeTv.setText("("+MyTools.convertTime(mBodyEntity.getStartTime(), "yyyy-MM-dd hh:mm")
                +"-"+MyTools.convertTime(mBodyEntity.getEndTime(), "yyyy-MM-dd hh:mm")+")");

        if (mBodyEntity.isSelect()) {
            holder.itemAddrecordSelectIv.setBackgroundResource(R.mipmap.addrecord_active_select);
        } else {
            holder.itemAddrecordSelectIv.setBackgroundResource(R.mipmap.addrecord_active_unselect);
        }

        holder.itemAddrecordSelectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClick.onItemClick(v, position);
            }
        });

        if (mBodyEntity.getState()==3) {
            holder.itemAddrecordRootLl.setBackgroundColor(mContext.getResources().getColor(R.color.overdue_bg));
        } else {
            holder.itemAddrecordRootLl.setBackgroundColor(mContext.getResources().getColor(R.color.color_white));
        }

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_addrecord_name_tv)
        TextView itemAddrecordNameTv;
        @Bind(R.id.item_addrecord_time_tv)
        TextView itemAddrecordTimeTv;
        @Bind(R.id.item_addrecord_select_iv)
        ImageView itemAddrecordSelectIv;
        @Bind(R.id.item_addrecord_root_ll)
        LinearLayout itemAddrecordRootLl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
