package com.atman.jishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.interfaces.CompoundButtonInterface;
import com.atman.jishang.net.Urls;
import com.atman.jishang.net.model.CommconfModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.corelib.widget.switchbutton.SwitchButton;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tangbingliang on 16/10/10.
 */

public class ServiceAdapter extends BaseAdapter {

    private Context context;
    private ViewHolder holder;
    protected LayoutInflater layoutInflater;
    protected AdapterInterface onClick;
    protected CompoundButtonInterface mCompoundButtonInterface;
    private List<CommconfModel.BodyBean> mBody;

    public ServiceAdapter(Context context, AdapterInterface onClick, CompoundButtonInterface mCompoundButtonInterface) {
        this.context = context;
        this.onClick = onClick;
        this.mCompoundButtonInterface = mCompoundButtonInterface;
        mBody = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }

    public void setmBody(List<CommconfModel.BodyBean> mBody) {
        this.mBody = mBody;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mBody.size();
    }

    @Override
    public CommconfModel.BodyBean getItem(int position) {
        return mBody.get(position);
    }

    public void changStatusById(int num, int status) {
        mBody.get(num).setModuleStatus(status);
    }

    public void updataView(int posi, ListView listView, int headNum) {
        int num = headNum;
        int visibleFirstPosi = listView.getFirstVisiblePosition();
        int visibleLastPosi = listView.getLastVisiblePosition();
        if (visibleFirstPosi != 0) {
            visibleFirstPosi -= headNum;
            visibleLastPosi -= headNum;
            num = 0;
        }
        if (posi >= visibleFirstPosi && posi <= visibleLastPosi) {
            View view = listView.getChildAt(posi - visibleFirstPosi+num);
            ViewHolder holder = (ViewHolder) view.getTag();

            if (holder==null) {
                return;
            }
            if (mBody.get(posi).getModuleStatus() == 1) {
                holder.settingOpenSb.setCheckedImmediately(true);
            } else {
                holder.settingOpenSb.setCheckedImmediately(false);
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_service_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.settingOpenSb.setCanMoveChang(false);
        holder.itemServiceNameTv.setText(mBody.get(position).getModuleName());
        holder.itemServiceDescriptionTv.setText(mBody.get(position).getModuleDesc());
        String url = "";
        if (mBody.get(position).getModuleIcon() != null) {
            if (mBody.get(position).getModuleIcon().startsWith("http")) {
                url = mBody.get(position).getModuleIcon();
            } else {
                url = Urls.RWH_HOST_IMG + "by/" + mBody.get(position).getModuleIcon();
            }
        }
        ImageLoader.getInstance().displayImage(url, holder.itemServiceIv
                , BaiYeBaseApplication.getApp().getOptionsHead());

        if (mBody.get(position).getModuleSetup() == 1) {
            holder.itemServiceSettingTv.setText("已设置");
        } else {
            holder.itemServiceSettingTv.setText("未设置");
        }

        if (mBody.get(position).getModuleStatus() == 1) {
            holder.settingOpenSb.setCheckedImmediately(true);
        } else {
            holder.settingOpenSb.setCheckedImmediately(false);
        }

        if (position == getCount() - 1) {
            holder.itemServiceLineIv.setVisibility(View.GONE);
        } else {
            holder.itemServiceLineIv.setVisibility(View.VISIBLE);
        }

        holder.itemServiceRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(v, position);
            }
        });

        holder.settingOpenSb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(v, position);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_service_iv)
        ImageView itemServiceIv;
        @Bind(R.id.item_service_name_tv)
        TextView itemServiceNameTv;
        @Bind(R.id.item_service_description_tv)
        TextView itemServiceDescriptionTv;
        @Bind(R.id.setting_open_sb)
        SwitchButton settingOpenSb;
        @Bind(R.id.item_service_setting_tv)
        TextView itemServiceSettingTv;
        @Bind(R.id.item_service_line_iv)
        ImageView itemServiceLineIv;
        @Bind(R.id.item_service_root)
        LinearLayout itemServiceRoot;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
