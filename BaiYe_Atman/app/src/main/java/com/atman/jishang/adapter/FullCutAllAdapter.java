package com.atman.jishang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.model.GetFullCutListModel;
import com.atman.jishang.utils.MyTools;
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
public class FullCutAllAdapter extends RecyclerView.Adapter<FullCutAllAdapter.MyViewHolder>
        implements SlidingButtonView.IonSlidingButtonListener {

    private Context mContext;
    private int widht;

    private IonSlidingViewClickListener mIDeleteBtnClickListener;

    private List<GetFullCutListModel.BodyEntity> mAllFullCutList;

    private SlidingButtonView mMenu = null;
    private String state;

    public FullCutAllAdapter(Context context, int widht, String state, IonSlidingViewClickListener mListener) {
        this.widht = widht;
        this.mContext = context;
        this.mIDeleteBtnClickListener = mListener;
        this.state = state;
        mAllFullCutList = new ArrayList<>();
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public int getItemCount() {
        return mAllFullCutList.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.item_fullcut_title_tx.setText(mAllFullCutList.get(position).getMansongName());
        holder.item_fullcut_starttime_tx.setText(MyTools.convertTimeTwo(mAllFullCutList.get(position).getStartTime()));
        holder.item_fullcut_endtime_tx.setText(MyTools.convertTimeTwo(mAllFullCutList.get(position).getEndTime()));
        String str = "";
        if (mAllFullCutList.get(position).getMansongRuleBeanList()!=null) {
            for (int i=0;i<mAllFullCutList.get(position).getMansongRuleBeanList().size();i++) {
                if (i!=0) {
                    str += ",";
                }
                str +=  "满"+mAllFullCutList.get(position).getMansongRuleBeanList().get(i).getPrice()
                        +"减"+mAllFullCutList.get(position).getMansongRuleBeanList().get(i).getDiscount();
            }
            if (mAllFullCutList.get(position).getState()==1) {
                holder.item_fullcut_iv.setImageResource(R.mipmap.item_fullcut_notstart);
            } else if (mAllFullCutList.get(position).getState()==2) {
                holder.item_fullcut_iv.setImageResource(R.mipmap.item_fullcut_now);
            } else if (mAllFullCutList.get(position).getState()==3) {
                holder.item_fullcut_iv.setImageResource(R.mipmap.item_fullcut_expired);
            }
        }
        holder.item_fullcut_remark_tx.setText(str);
        //设置内容布局的宽为屏幕宽度
        holder.layout_content.getLayoutParams().width = widht;

        //1：未开始，2：进行中，3：已结束

        holder.item_fullcut_iv.setVisibility(View.INVISIBLE);

        if (state.equals("3")) {
            holder.item_coupon_iv.setVisibility(View.VISIBLE);
        } else {
            holder.item_coupon_iv.setVisibility(View.GONE);
        }
        holder.item_coupon_iv.setOnClickListener(new View.OnClickListener() {
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

        holder.btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();
                mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
            }
        });

        holder.btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();
                mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
            }
        });

        if (state.equals("2")) {
            holder.btn_Edit.setText("结 束");
            holder.btn_Edit.setVisibility(View.VISIBLE);
            holder.mSlidingButtonView.changeWidth(2, 80);
        } else {
            holder.btn_Edit.setText("编 辑");
            holder.btn_Edit.setVisibility(View.GONE);
            holder.mSlidingButtonView.changeWidth(1, 80);
        }
        holder.mSlidingButtonView.closeMenu();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fullcut_listview, arg0,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView btn_Delete;
        public TextView btn_Edit;
        public TextView item_fullcut_title_tx;
        public TextView item_fullcut_remark_tx;
        public TextView item_fullcut_starttime_tx;
        public TextView item_fullcut_endtime_tx;
        public ImageView item_fullcut_iv;
        public ImageView item_coupon_iv;
        public ViewGroup layout_content;
        public RelativeLayout item_fullcut_root_rl;
        public SlidingButtonView mSlidingButtonView;

        public MyViewHolder(View itemView) {
            super(itemView);
            btn_Delete = (TextView) itemView.findViewById(R.id.tv_delete);
            btn_Edit = (TextView) itemView.findViewById(R.id.tv_edit);
            item_fullcut_root_rl = (RelativeLayout) itemView.findViewById(R.id.item_fullcut_root_rl);
            item_fullcut_title_tx = (TextView) itemView.findViewById(R.id.item_fullcut_title_tx);
            item_fullcut_remark_tx = (TextView) itemView.findViewById(R.id.item_fullcut_remark_tx);
            item_fullcut_starttime_tx = (TextView) itemView.findViewById(R.id.item_fullcut_starttime_tx);
            item_fullcut_endtime_tx = (TextView) itemView.findViewById(R.id.item_fullcut_endtime_tx);
            item_fullcut_iv = (ImageView) itemView.findViewById(R.id.item_fullcut_iv);
            item_coupon_iv = (ImageView) itemView.findViewById(R.id.item_coupon_iv);
            layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);
            mSlidingButtonView = (SlidingButtonView) itemView;
            mSlidingButtonView.setSlidingButtonListener(FullCutAllAdapter.this);
        }
    }

    public void addData(List<GetFullCutListModel.BodyEntity> mList) {
        mAllFullCutList.addAll(mList);
        notifyDataSetChanged();
    }

    public GetFullCutListModel.BodyEntity getItemById(int n) {
        return mAllFullCutList.get(n);
    }

    public void removeData(int position){
        mAllFullCutList.remove(position);
        notifyItemRemoved(position);
    }

    public void clearData(){
        mAllFullCutList.clear();
        notifyDataSetChanged();
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
        void onItemClick(View view,int position);
        void onDeleteBtnCilck(View view,int position);
    }
}

