package com.atman.jishang.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atman.jishang.R;

/**
 * Created by fanxi on 5/7/15.
 */
public class DialogAdapter extends BaseAdapter {

    private CharSequence[] mItems;

    private Context mContext;

    public DialogAdapter(Context context) {
        mContext = context;
    }

    public DialogAdapter(Context context, CharSequence[] items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems == null ? 0 : mItems.length;
    }

    @Override
    public CharSequence getItem(int position) {
        return mItems != null ? mItems[position] : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView labelView;
        if (convertView == null) {
            convertView = View.inflate(mContext,
                    R.layout.dialog_normal_item, null);
            labelView = (TextView) convertView.findViewById(R.id.popup_text);
            convertView.setTag(labelView);
        } else {
            labelView = (TextView) convertView.getTag();
        }

        labelView.setText(getItem(position));
        return convertView;
    }
}
