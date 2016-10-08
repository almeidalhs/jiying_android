package com.atman.jishang.widget.dateselect;

import com.atman.jishang.widget.dateselect.wheel.widget.WheelAdapter;

import java.util.ArrayList;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/19 13:48
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class StringWheelAdapter implements WheelAdapter {

    /**
     * The default items length
     */
    public static final int DEFAULT_LENGTH = -1;

    // items
    private ArrayList<DateObject> list;

    // length
    private int length;

    /**
     * Constructor
     *
     * @param list   the items
     * @param length the max items length
     */
    public StringWheelAdapter(ArrayList<DateObject> list, int length) {
        this.list = list;
        this.length = length;
    }


    @Override
    public String getItem(int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index).getListItem();
        }
        return null;
    }

    @Override
    public int getItemsCount() {
        return list.size();
    }

    @Override
    public int getMaximumLength() {
        return length;
    }
}
