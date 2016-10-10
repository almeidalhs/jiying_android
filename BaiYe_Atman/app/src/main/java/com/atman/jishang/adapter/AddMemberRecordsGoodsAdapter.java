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
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.Urls;
import com.atman.jishang.net.model.AddRecordParamsModel;
import com.atman.jishang.net.model.GetCouponListModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.utils.MyTools;
import com.atman.jishang.widget.SlidingButtonView;
import com.corelib.util.LogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

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
public class AddMemberRecordsGoodsAdapter extends RecyclerView.Adapter<AddMemberRecordsGoodsAdapter.MyViewHolder>
        implements SlidingButtonView.IonSlidingButtonListener {

    private Context mContext;
    private int widht;

    private IonSlidingViewClickListener mIDeleteBtnClickListener;

    private List<AddRecordParamsModel.GoodsBeanListEntity> mList;

    private SlidingButtonView mMenu = null;
    private DecimalFormat df;

    public AddMemberRecordsGoodsAdapter(Context context, int widht, IonSlidingViewClickListener mListener) {
        this.widht = widht;
        this.mContext = context;
        this.mIDeleteBtnClickListener = mListener;
        mList = new ArrayList<>();
        df = new DecimalFormat("##0.00");
    }

    public List<AddRecordParamsModel.GoodsBeanListEntity> getmList() {
        return mList;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        ImageLoader.getInstance().displayImage(Urls.HEADIMG_BEFOR+mList.get(position).getGoodsImage(),
                holder.itemAddrecordIv,
                BaiYeBaseApplication.getApp().getOptions());
        holder.itemAddrecordNameTx.setText(mList.get(position).getGoodsName());
        holder.itemAddrecordNumTx.setText(mList.get(position).getGoodsCount()+"");
        holder.itemAddrecordPriceTx.setText("¥ " + df.format(mList.get(position).getGoodsPrice()));
        holder.itemAddrecordDownTx.setOnClickListener(new View.OnClickListener() {
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
        holder.itemAddrecordUpTx.setOnClickListener(new View.OnClickListener() {
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

        holder.mSlidingButtonView.changeWidth(1);
        holder.mSlidingButtonView.closeMenu();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_addrecord_goods_view, arg0,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView btn_Delete;
        public TextView btn_Edit;
        public SlidingButtonView mSlidingButtonView;
        public ViewGroup layout_content;
        public ImageView itemAddrecordIv;
        public TextView itemAddrecordNameTx;
        public TextView itemAddrecordPriceTx;
        public TextView itemAddrecordDownTx;
        public TextView itemAddrecordNumTx;
        public TextView itemAddrecordUpTx;

        public RelativeLayout item_fullcut_root_rl;

        public MyViewHolder(View itemView) {
            super(itemView);
            btn_Delete = (TextView) itemView.findViewById(R.id.tv_delete);
            btn_Edit = (TextView) itemView.findViewById(R.id.tv_edit);
            itemAddrecordIv = (ImageView) itemView.findViewById(R.id.item_addrecord_iv);
            itemAddrecordNameTx = (TextView) itemView.findViewById(R.id.item_addrecord_name_tx);
            itemAddrecordPriceTx = (TextView) itemView.findViewById(R.id.item_addrecord_price_tx);
            itemAddrecordDownTx = (TextView) itemView.findViewById(R.id.item_addrecord_down_tx);
            itemAddrecordNumTx = (TextView) itemView.findViewById(R.id.item_addrecord_num_tx);
            itemAddrecordUpTx = (TextView) itemView.findViewById(R.id.item_addrecord_up_tx);

            item_fullcut_root_rl = (RelativeLayout) itemView.findViewById(R.id.item_fullcut_root_rl);

            layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);
            mSlidingButtonView = (SlidingButtonView) itemView;
            mSlidingButtonView.setSlidingButtonListener(AddMemberRecordsGoodsAdapter.this);
        }
    }

    public AddRecordParamsModel.GoodsBeanListEntity getItemById(int n) {
        return mList.get(n);
    }

    public void removeData(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void clearData(){
        mList.clear();
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

    public void addList(List<AddRecordParamsModel.GoodsBeanListEntity> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public float getTotalMoney() {
        float num = 0;
        for (int i=0;i<mList.size();i++) {
            num += mList.get(i).getGoodsCount()* mList.get(i).getGoodsPrice();
        }
        return num;
    }

    public void up(int num){
        if (mList.get(num).getGoodsCount()+1>=100) {
            mList.get(num).setGoodsCount(99);
        } else {
            mList.get(num).setGoodsCount(mList.get(num).getGoodsCount()+1);
        }
        notifyDataSetChanged();
    }

    public void down(int num){
        if (mList.get(num).getGoodsCount()-1<=0) {
            mList.get(num).setGoodsCount(0);
        } else {
            mList.get(num).setGoodsCount(mList.get(num).getGoodsCount()-1);
        }
        notifyDataSetChanged();
    }

    public void deleteListById(int num) {
        mList.remove(num);
        notifyDataSetChanged();
    }

    public interface IonSlidingViewClickListener {
        void onItemClick(View view, int position);
        void onDeleteBtnCilck(View view, int position);
    }
}

