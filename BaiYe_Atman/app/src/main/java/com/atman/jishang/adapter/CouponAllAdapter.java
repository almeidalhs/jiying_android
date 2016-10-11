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
import com.atman.jishang.net.model.GetCouponListModel;
import com.atman.jishang.utils.MyTools;
import com.atman.jishang.widget.SlidingButtonView;
import com.atman.jishang.widget.YLBDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/17 17:31
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class CouponAllAdapter extends RecyclerView.Adapter<CouponAllAdapter.MyViewHolder>
        implements SlidingButtonView.IonSlidingButtonListener {

    private Context mContext;
    private int widht;

    private IonSlidingViewClickListener mIDeleteBtnClickListener;

    private List<GetCouponListModel.BodyEntity> mAllCouponList;

    private SlidingButtonView mMenu = null;
    private String state;
    private DecimalFormat df;

    public CouponAllAdapter(Context context, int widht, String state, IonSlidingViewClickListener mListener) {
        this.widht = widht;
        this.mContext = context;
        this.mIDeleteBtnClickListener = mListener;
        this.state = state;
        df = new DecimalFormat("###");
        mAllCouponList = new ArrayList<>();
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public int getItemCount() {
        return mAllCouponList.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.item_coupon_denomination_tx.setText(""+df.format(mAllCouponList.get(position).getCouponPrice()));
//        if (String.valueOf(mAllCouponList.get(position).getCouponPrice()).length()>5) {
//            holder.item_coupon_denomination_tx.setTextSize(YLBDialog.dip2px(mContext, 12));
//        } else {
//            holder.item_coupon_denomination_tx.setTextSize(YLBDialog.dip2px(mContext, 16));
//        }
        holder.item_coupon_achieve_tx.setText("满"+mAllCouponList.get(position).getCouponLimit()+"可用");
        holder.item_coupon_surplus_tx.setText("剩余"+mAllCouponList.get(position).getCouponStorage()+"张");
        holder.item_coupon_time_tx.setText(MyTools.convertTimeThree(mAllCouponList.get(position).getCouponStartDate())
                +"-"+MyTools.convertTimeThree(mAllCouponList.get(position).getCouponEndDate()));

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

        holder.mSlidingButtonView.changeWidth(2, 80);
        if (state.equals("2")) {
            holder.btn_Edit.setText("结 束");
        } else {
            holder.btn_Edit.setText("编 辑");
        }

        if (state.equals("3")) {
            holder.item_coupon_iv.setVisibility(View.VISIBLE);
        } else {
            holder.item_coupon_iv.setVisibility(View.GONE);
        }

        holder.item_coupon_share_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else if (!state.equals("3")) {
                    int n = holder.getLayoutPosition();
                    mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
                }
            }
        });
        holder.mSlidingButtonView.closeMenu();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_coupon_listview, arg0,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView btn_Delete;
        public TextView btn_Edit;
        public SlidingButtonView mSlidingButtonView;
        public ViewGroup layout_content;
        public TextView item_coupon_denomination_tx;
        public TextView item_coupon_achieve_tx;
        public TextView item_coupon_surplus_tx;
        public TextView item_coupon_time_tx;
        public ImageView item_coupon_share_iv;
        public ImageView item_coupon_iv;

        public RelativeLayout item_fullcut_root_rl;

        public MyViewHolder(View itemView) {
            super(itemView);
            btn_Delete = (TextView) itemView.findViewById(R.id.tv_delete);
            btn_Edit = (TextView) itemView.findViewById(R.id.tv_edit);
            item_coupon_denomination_tx = (TextView) itemView.findViewById(R.id.item_coupon_denomination_tx);
            item_coupon_achieve_tx = (TextView) itemView.findViewById(R.id.item_coupon_achieve_tx);
            item_coupon_surplus_tx = (TextView) itemView.findViewById(R.id.item_coupon_surplus_tx);
            item_coupon_time_tx = (TextView) itemView.findViewById(R.id.item_coupon_time_tx);
            item_coupon_share_iv = (ImageView) itemView.findViewById(R.id.item_coupon_share_iv);
            item_coupon_iv = (ImageView) itemView.findViewById(R.id.item_coupon_iv);

            item_fullcut_root_rl = (RelativeLayout) itemView.findViewById(R.id.item_fullcut_root_rl);

            layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);
            mSlidingButtonView = (SlidingButtonView) itemView;
            mSlidingButtonView.setSlidingButtonListener(CouponAllAdapter.this);
        }
    }

    public void addData(List<GetCouponListModel.BodyEntity> mList) {
        mAllCouponList.addAll(mList);
        notifyDataSetChanged();
    }

    public GetCouponListModel.BodyEntity getItemById(int n) {
        return mAllCouponList.get(n);
    }

    public void removeData(int position){
        mAllCouponList.remove(position);
        notifyItemRemoved(position);
    }

    public void clearData(){
        mAllCouponList.clear();
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
        void onItemClick(View view, int position);
        void onDeleteBtnCilck(View view, int position);
    }
}

