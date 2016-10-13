package com.atman.jishang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.model.CallServiceModel;
import com.atman.jishang.net.model.EditCallServiceModel;
import com.atman.jishang.widget.SlidingButtonView;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/17 17:31
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class CallServiceAdapter extends RecyclerView.Adapter<CallServiceAdapter.MyViewHolder>
        implements SlidingButtonView.IonSlidingButtonListener {

    private Context mContext;
    private int widht;

    private IonSlidingViewClickListener mIDeleteBtnClickListener;

    private List<CallServiceModel.BodyBean> mList;

    private SlidingButtonView mMenu = null;

    public CallServiceAdapter(Context context, int widht, IonSlidingViewClickListener mListener) {
        this.widht = widht;
        this.mContext = context;
        this.mIDeleteBtnClickListener = mListener;
        mList = new ArrayList<>();
    }

    public void setmList(List<CallServiceModel.BodyBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public CallServiceModel.BodyBean getItem(int num) {
        return mList.get(num);
    }

    public void deleteItemById (int num) {
        mList.remove(num);
        notifyDataSetChanged();
    }

    public void addItem (CallServiceModel.BodyBean temp) {
        mList.add(0,temp);
        notifyDataSetChanged();
    }

    public void updataItem (EditCallServiceModel.BodyBean temp, int num) {
        mList.get(num).setModuleName(temp.getModuleName());
        mList.get(num).setModuleDesc(temp.getModuleDesc());
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.itemCallservicelistNameTx.setText("服务名称:   "+mList.get(position).getModuleName());
        holder.itemCallservicelistPasswordTx.setText("服务备注:   "+mList.get(position).getModuleDesc());

        //设置内容布局的宽为屏幕宽度
        holder.layout_content.getLayoutParams().width = widht;

        holder.item_fullcut_root_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    int n = holder.getLayoutPosition();
                    mIDeleteBtnClickListener.onItemClick(v, n);
                }
            }
        });

        holder.btn_Code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                }
                int n = holder.getLayoutPosition();
                mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
            }
        });

        holder.btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                }
                int n = holder.getLayoutPosition();
                mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
            }
        });

        holder.btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                }
                int n = holder.getLayoutPosition();
                mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
            }
        });

        holder.mSlidingButtonView.changeWidth(3, 60);
        holder.mSlidingButtonView.closeMenu();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_callservicelist_listview, arg0,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView itemCallservicelistNameTx;
        public TextView itemCallservicelistPasswordTx;

        public TextView btn_Code;
        public TextView btn_Delete;
        public TextView btn_Edit;
        public ViewGroup layout_content;
        public SlidingButtonView mSlidingButtonView;
        public RelativeLayout item_fullcut_root_rl;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemCallservicelistNameTx = (TextView) itemView.findViewById(R.id.item_callservicelist_name_tx);
            itemCallservicelistPasswordTx = (TextView) itemView.findViewById(R.id.item_callservicelist_password_tx);

            btn_Code = (TextView) itemView.findViewById(R.id.tv_code);
            btn_Delete = (TextView) itemView.findViewById(R.id.tv_delete);
            btn_Edit = (TextView) itemView.findViewById(R.id.tv_edit);
            item_fullcut_root_rl = (RelativeLayout) itemView.findViewById(R.id.item_fullcut_root_rl);
            layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);
            mSlidingButtonView = (SlidingButtonView) itemView;
            mSlidingButtonView.setSlidingButtonListener(CallServiceAdapter.this);
        }
    }

    /**
     * 删除菜单打开信息接收
     */
    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;
    }

    /**
     * 滑动或者点击了Item监听
     * @param slidingButtonView
     */
    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if(menuIsOpen()){
            if(mMenu != slidingButtonView){
                closeMenu();
            }
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;

    }
    /**
     * 判断是否有菜单打开
     */
    public Boolean menuIsOpen() {
        if(mMenu != null){
            return true;
        }
        return false;
    }



    public interface IonSlidingViewClickListener {
        void onItemClick(View view, int position);
        void onDeleteBtnCilck(View view, int position);
    }
}

