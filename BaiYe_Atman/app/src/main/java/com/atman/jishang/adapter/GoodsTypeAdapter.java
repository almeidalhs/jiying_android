package com.atman.jishang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.model.GetStoreClassModel;
import com.atman.jishang.widget.WheelView.WheelView;
import com.atman.jishang.widget.WheelView.adapter.AbstractWheelTextAdapter;
import com.corelib.util.DensityUtil;

import java.util.List;

/**
 * 描述：
 * 作者：唐炳良 on 2016/1/6 19:35
 * 邮箱：tangbingliang@yonglibao.com
 */
public class GoodsTypeAdapter extends AbstractWheelTextAdapter {
    List<GetStoreClassModel.BodyEntity> list;
    WheelView mSchoolWheel;
    Context context;

    public void setList(List<GetStoreClassModel.BodyEntity> list) {
        this.list = list;
        notifyDataChangedEvent();
    }

    public GoodsTypeAdapter(Context context, WheelView mSchoolWheel, List<GetStoreClassModel.BodyEntity> list
            , int currentItem, int maxsize, int minsize) {
        super(context, R.layout.adater_item, NO_RESOURCE, currentItem,maxsize, minsize);
        this.context = context;
        this.list = list;
        this.mSchoolWheel = mSchoolWheel;
        setItemTextResource(R.id.textview);
    }

    @Override
    public View getItem(int index, View cachedView, ViewGroup parent) {
        View view = super.getItem(index, cachedView, parent);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setTextColor(context.getResources().getColor(R.color.color_gray));
        textView.setTextSize(DensityUtil.dp2px(context, 5));
        if(mSchoolWheel.getCurrentItem() == index) {
            textView.setTextColor(context.getResources().getColor(R.color.color_black));
            textView.setTextSize(DensityUtil.dp2px(context,8));
        }
        return view;
    }

    @Override
    public int getItemsCount() {
        if(list==null){
            return 0;
        }
        return list.size();
    }

    @Override
    public CharSequence getItemText(int index) {
        return list.get(index).getStcName() + "";
    }
}
