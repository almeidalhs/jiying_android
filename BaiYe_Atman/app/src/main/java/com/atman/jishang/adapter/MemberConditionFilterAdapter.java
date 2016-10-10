package com.atman.jishang.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.model.GetMemberFilterModel;
import com.atman.jishang.ui.member.MemberListActivity;
import com.atman.jishang.widget.YLBDialog;
import com.corelib.util.LogUtils;
import com.corelib.widget.MyGridView;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/27 11:45
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MemberConditionFilterAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<GetMemberFilterModel.BodyEntity> list;
    private LayoutInflater layoutInflater,layoutInflaterGroup;
    private AdapterInterface onItemClick;
    private List<MemberConditionFilterChildAdpter> adptersList = new ArrayList<>();

    public MemberConditionFilterAdapter(Context context, List<GetMemberFilterModel.BodyEntity> body
            , AdapterInterface onItemClick) {
        this.context = context;
        this.list = body;
        this.onItemClick = onItemClick;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflaterGroup = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i=0;i<list.size();i++) {
            list.get(i).setChildSelectId(-1);
            list.get(i).setChildSelectStr("-1");
        }
    }

    public List<GetMemberFilterModel.BodyEntity> getList() {
        return list;
    }

    public void clearParms () {
        for (int i=0;i<list.size();i++) {
            list.get(i).setChildSelectId(-1);
            list.get(i).setChildSelectStr("-1");
            adptersList.get(i).setSelectId(-1);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public GetMemberFilterModel.BodyEntity getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public GetMemberFilterModel.BodyEntity getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        TextView textView = getTextView(context);
//        textView.setText(getGroup(groupPosition).getParamkey());
//        textView.setPadding(YLBDialog.dip2px(context, 10), 0, 0, 0);
//        return textView;

        if (convertView == null) {
            convertView = layoutInflaterGroup.inflate(R.layout.item_group_memberfilter_view, null);
            TextView itemGroupTx = (TextView) convertView.findViewById(R.id.item_group_tx);
            TextView itemGroupRefreshTx = (TextView) convertView.findViewById(R.id.item_group_refresh_tx);

            itemGroupTx.setText(getGroup(groupPosition).getParamkey());
            itemGroupRefreshTx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onItemClick(v, groupPosition);
                }
            });

            if (getGroup(groupPosition).getId()==3) {
                itemGroupRefreshTx.setVisibility(View.VISIBLE);
            } else {
                itemGroupRefreshTx.setVisibility(View.GONE);
            }
        } else {

        }

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_child_memberfilter_view, null);
            MyGridView toolbarGrid = (MyGridView) convertView.findViewById(R.id.GridView_toolbar);
            final MemberConditionFilterChildAdpter adpter = new MemberConditionFilterChildAdpter(context, list.get(groupPosition).getParamValue());
            toolbarGrid.setAdapter(adpter);// 设置菜单Adapter
            toolbarGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position==adpter.getSelectId()) {
                        adpter.setSelectId(-1);
                        list.get(groupPosition).setChildSelectId(-1);
                        list.get(groupPosition).setChildSelectStr("-1");
                    } else {
                        adpter.setSelectId(position);
                        list.get(groupPosition).setChildSelectId(position);
                        list.get(groupPosition).setChildSelectStr(list.get(groupPosition)
                                .getParamValue().get(position));
                    }
                }
            });
            for (int i=0;i<list.get(groupPosition).getParamValue().size();i++) {
                for (int j=0;j<MemberListActivity.listFilter.size();j++) {
                    if (MemberListActivity.listFilter.get(j).getParamkey().equals(list.get(groupPosition).getParamValue().get(i))) {
                        LogUtils.e("groupPosition:"+groupPosition+",i:"+i+",j:"+j);
                        adpter.setSelectId(i);
                        list.get(groupPosition).setChildSelectId(i);
                        list.get(groupPosition).setChildSelectStr(list.get(groupPosition).getParamValue().get(i));
                    }
                }
            }
            adptersList.add(adpter);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static public TextView getTextView(Context context) {
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, YLBDialog.dip2px(context, 40));
        TextView textView = new TextView(context);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        return textView;
    }

}
