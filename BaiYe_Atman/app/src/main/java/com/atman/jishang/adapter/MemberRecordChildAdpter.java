package com.atman.jishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.Urls;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.net.model.GetMemberRecordModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;
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
public class MemberRecordChildAdpter extends BaseAdapter {

    private Context context;
    private List<GetMemberRecordModel.BodyEntity.GoodsBeanListEntity> list = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private ViewHolder holder;
    private DecimalFormat df;

    public MemberRecordChildAdpter(Context context, List<GetMemberRecordModel.BodyEntity.GoodsBeanListEntity> bodyEntity) {
        this.context = context;
        this.list = bodyEntity;
        df = new DecimalFormat("###.00");
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (list==null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public GetMemberRecordModel.BodyEntity.GoodsBeanListEntity getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.part_item_goods_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(Urls.HEADIMG_BEFOR+list.get(position).getGoodsImage(),
                    holder.partGoodsIv, BaiYeBaseApplication.getApp().getOptions());
        holder.partGoodsNameTv.setText(list.get(position).getGoodsName());
        holder.partGoodsIntrTv.setText(list.get(position).getGoodsInfo());
        holder.partGoodsNumberTv.setText("数量："+list.get(position).getGoodsCount());
        holder.partGoodsPriceTv.setText("¥"+df.format(list.get(position).getGoodsPrice()));

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.part_goods_iv)
        ImageView partGoodsIv;
        @Bind(R.id.part_goods_name_tv)
        TextView partGoodsNameTv;
        @Bind(R.id.part_goods_intr_tv)
        TextView partGoodsIntrTv;
        @Bind(R.id.part_goods_number_tv)
        TextView partGoodsNumberTv;
        @Bind(R.id.part_goods_price_tv)
        TextView partGoodsPriceTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
