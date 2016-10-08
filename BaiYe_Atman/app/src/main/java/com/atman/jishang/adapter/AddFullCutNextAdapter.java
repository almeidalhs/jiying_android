package com.atman.jishang.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.model.AddFullCutNextModel;
import com.corelib.util.LogUtils;
import com.corelib.widget.MyCleanEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/18 17:20
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AddFullCutNextAdapter extends BaseAdapter {

    private Context mContext;
    private List<AddFullCutNextModel> mList;
    private AdapterInterface onClick;
    private String state = "1";

    public AddFullCutNextAdapter(Context mContext, AdapterInterface onClick) {
        this.mContext = mContext;
        this.onClick = onClick;
        mList = new ArrayList<>();
    }

    public AddFullCutNextAdapter(Context mContext, String state, AdapterInterface onClick) {
        this.mContext = mContext;
        this.onClick = onClick;
        this.state = state;
        mList = new ArrayList<>();
    }

    public void addList(AddFullCutNextModel list){
        mList.add(list);
        notifyDataSetChanged();
    }

    public void addList(List<AddFullCutNextModel> list){
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void deleteListById(int num){
        mList.remove(num);
        notifyDataSetChanged();
    }

    public List<AddFullCutNextModel> getList(){
        return mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_addfullcutnext, null);
        final EditText itemAddfullcutNextPriceEt = (EditText) view.findViewById(R.id.item_addfullcut_next_price_et);
        itemAddfullcutNextPriceEt.addTextChangedListener(new LimitSizeTextWatcher(mContext,
                itemAddfullcutNextPriceEt,7,0,position,"您输入的价格过大"));
        final EditText itemAddfullcutNextDiscountEt = (EditText) view.findViewById(R.id.item_addfullcut_next_discount_et);
        itemAddfullcutNextDiscountEt.addTextChangedListener(new LimitSizeTextWatcher(mContext,
                itemAddfullcutNextDiscountEt,7,1,position,"您输入的价格过大"));
        ImageView itemAddfullcutNextDeleteIv = (ImageView) view.findViewById(R.id.item_addfullcut_next_delete_iv);
        itemAddfullcutNextPriceEt.setText(mList.get(position).getPrice());
        itemAddfullcutNextDiscountEt.setText(mList.get(position).getDiscount());
        itemAddfullcutNextDeleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(v, position);
            }
        });
        if (state.equals("2")) {
            itemAddfullcutNextDeleteIv.setVisibility(View.INVISIBLE);
        } else {
            itemAddfullcutNextDeleteIv.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public class LimitSizeTextWatcher implements TextWatcher {
        private EditText myCleanEditText;
        private Context mContext;
        private int max;
        private String toastStr;
        private int num;
        private int index;

        public LimitSizeTextWatcher(Context mContext, EditText myCleanEditText, int max, int index, int num, String toastStr){
            this.myCleanEditText = myCleanEditText;
            this.mContext = mContext;
            this.toastStr = toastStr;
            this.max = max;
            this.num = num;
            this.index = index;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String content = s == null ? null : s.toString();
            if (s == null || s.length() == 0) {
                return;
            }
            int size = content.trim().length();
            if (size > max && !toastStr.isEmpty()) {
                s.delete(max , size);
                Toast.makeText(mContext, toastStr, Toast.LENGTH_SHORT).show();
            }

            if (index==0) {
                mList.get(num).setPrice(myCleanEditText.getText().toString());
            } else {
                mList.get(num).setDiscount(myCleanEditText.getText().toString());
            }
        }
    }

}
