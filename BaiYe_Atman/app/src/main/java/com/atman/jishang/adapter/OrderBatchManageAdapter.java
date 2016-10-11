package com.atman.jishang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.model.OrderManageListModel;
import com.atman.jishang.utils.MyTools;
import com.atman.jishang.widget.SlidingButtonView;

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
public class OrderBatchManageAdapter extends RecyclerView.Adapter<OrderBatchManageAdapter.MyViewHolder>
        implements SlidingButtonView.IonSlidingButtonListener {

    private Context mContext;
    private int widht;

    private IonSlidingViewClickListener mIDeleteBtnClickListener;

    private List<OrderManageListModel.BodyEntity> mAllList;

    private SlidingButtonView mMenu = null;
    private DecimalFormat df;

    public OrderBatchManageAdapter(Context context, int widht, IonSlidingViewClickListener mListener) {
        this.widht = widht;
        this.mContext = context;
        this.mIDeleteBtnClickListener = mListener;
        df = new DecimalFormat("###");
        mAllList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return mAllList.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.item_ordermanage_id_tx.setText(""+mAllList.get(position).getOut_trade_no());
        holder.item_ordermanage_money_tx.setText("金额："+df.format(mAllList.get(position).getTotal_fee()));
        String str="";
        for (int i=0;i<mAllList.get(position).getOrderGoodses().size();i++) {
            if (i!=0) {
                str += ",";
            }
            str += mAllList.get(position).getOrderGoodses().get(i).getGoodsName();
        }
        holder.item_ordermanage_goodsinfo_tx.setText(str);
        holder.item_ordermanage_time_tx.setText(MyTools.convertTimeTwo(mAllList.get(position).getCreateTime() / 1000));

        holder.item_ordermanage_pay_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    int n = holder.getLayoutPosition();
                    if (mAllList.get(n).getPayTime()<=0) {
                        mIDeleteBtnClickListener.onItemClick(v, n);
                    }
                }
            }
        });

        holder.item_ordermanage_select_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    int n = holder.getLayoutPosition();
                    if (mAllList.get(n).getPayTime()<=0) {
                        mIDeleteBtnClickListener.onItemClick(v, n);
                    }
                }
            }
        });

        if (mAllList.get(position).isSelect()) {
            holder.item_ordermanage_select_iv.setImageResource(R.mipmap.selected);
            holder.layout_content_ll.setBackgroundColor(mContext.getResources()
                    .getColor(R.color.goods_list_manage_selected));
        } else {
            holder.item_ordermanage_select_iv.setImageResource(R.mipmap.un_select);
            holder.layout_content_ll.setBackgroundColor(mContext.getResources()
                    .getColor(R.color.goods_list_manage_unselected));
        }

        if (mAllList.get(position).getPayTime()>0) {
            holder.item_ordermanage_pay_bt.setBackgroundResource(R.drawable.gray_hollow_bg);
            holder.item_ordermanage_pay_bt.setText(mAllList.get(position).getOrderStateName());
        } else {
            holder.item_ordermanage_pay_bt.setBackgroundResource(R.drawable.red_full_selector);
            holder.item_ordermanage_pay_bt.setText("支 付");
        }

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

        holder.mSlidingButtonView.changeWidth(0, 80);
        holder.mSlidingButtonView.closeMenu();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_orderbatchmanage_view, arg0,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView btn_Delete;
        public TextView btn_Edit;
        public SlidingButtonView mSlidingButtonView;
        public ViewGroup layout_content;

        public TextView item_ordermanage_id_tx;
        public TextView item_ordermanage_goodsinfo_tx;
        public TextView item_ordermanage_time_tx;
        public TextView item_ordermanage_money_tx;
        public Button item_ordermanage_pay_bt;
        public ImageView item_ordermanage_select_iv;
        public LinearLayout layout_content_ll;

        public RelativeLayout item_fullcut_root_rl;

        public MyViewHolder(View itemView) {
            super(itemView);
            btn_Delete = (TextView) itemView.findViewById(R.id.tv_delete);
            btn_Edit = (TextView) itemView.findViewById(R.id.tv_edit);
            item_ordermanage_id_tx = (TextView) itemView.findViewById(R.id.item_ordermanage_id_tx);
            item_ordermanage_goodsinfo_tx = (TextView) itemView.findViewById(R.id.item_ordermanage_goodsinfo_tx);
            item_ordermanage_time_tx = (TextView) itemView.findViewById(R.id.item_ordermanage_time_tx);
            item_ordermanage_money_tx = (TextView) itemView.findViewById(R.id.item_ordermanage_money_tx);
            item_ordermanage_pay_bt = (Button) itemView.findViewById(R.id.item_ordermanage_pay_bt);
            item_ordermanage_select_iv = (ImageView) itemView.findViewById(R.id.item_ordermanage_select_iv);
            layout_content_ll = (LinearLayout) itemView.findViewById(R.id.layout_content_ll);

            item_fullcut_root_rl = (RelativeLayout) itemView.findViewById(R.id.item_fullcut_root_rl);

            layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);
            mSlidingButtonView = (SlidingButtonView) itemView;
            mSlidingButtonView.setSlidingButtonListener(OrderBatchManageAdapter.this);
        }
    }

    public boolean selectById(int id) {
        if (mAllList.get(id).isSelect()) {
            mAllList.get(id).setSelect(false);
        } else {
            mAllList.get(id).setSelect(true);
        }
        notifyDataSetChanged();
        return isAllselected();
    }

    public boolean allSelectList(boolean bb){
        for (int i=0;i<mAllList.size();i++) {
            mAllList.get(i).setSelect(bb);
        }
        notifyDataSetChanged();
        return isAllselected();
    }

    private boolean isAllselected() {
        int n = 1;
        for (int i=0;i<mAllList.size();i++) {
            if (mAllList.get(i).isSelect()) {
                n += 1;
            }
        }
        return mAllList.size()==n;
    }

    public boolean isSelected() {
        int n = 1;
        for (int i=0;i<mAllList.size();i++) {
            if (mAllList.get(i).isSelect()) {
                n += 1;
            }
        }
        return n>0;
    }

    public List<OrderManageListModel.BodyEntity> getSelectedList() {
        List<OrderManageListModel.BodyEntity> list = new ArrayList<>();
        for (int i=0;i<mAllList.size();i++) {
            if (mAllList.get(i).isSelect()) {
                list.add(mAllList.get(i));
            }
        }
        return list;
    }

    public void addData(List<OrderManageListModel.BodyEntity> mList) {
        mAllList.addAll(mList);
        notifyDataSetChanged();
    }

    public OrderManageListModel.BodyEntity getItemById(int n) {
        return mAllList.get(n);
    }

    public void removeData(int position){
        mAllList.remove(position);
        notifyItemRemoved(position);
    }

    public void clearData(){
        mAllList.clear();
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

