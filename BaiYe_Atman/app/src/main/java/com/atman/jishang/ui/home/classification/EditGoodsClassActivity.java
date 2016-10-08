package com.atman.jishang.ui.home.classification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atman.jishang.R;
import com.atman.jishang.adapter.EditGoodsClasesAdapter;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.model.DeleteGoodsClassmodel;
import com.atman.jishang.net.model.GetStoreClassModel;
import com.atman.jishang.net.model.UpdateGoodsClassOrderModel;
import com.atman.jishang.net.model.UpdateGoodsClassOrderResultModel;
import com.corelib.util.LogUtils;
import com.google.gson.Gson;
import com.mobeta.android.draglistview.DragSortListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 编辑商品分类
 * 作者 tangbingliang
 * 时间 16/4/27 15:44
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class EditGoodsClassActivity extends SimpleTitleBarActivity implements AdapterInterface {

    @Bind(R.id.channeleditor_listview)
    DragSortListView channeleditorListview;
    @Bind(R.id.editgoods_ok_ll)
    LinearLayout editgoodsOkLl;

    private Context mContext = EditGoodsClassActivity.this;
    private List<GetStoreClassModel.BodyEntity> list = new ArrayList<>();
    private EditGoodsClasesAdapter mAdapter;
    private final int toEditName = 1002;
    private boolean isEdit = false;
    private ImageView iBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editgoodsclass);
        ButterKnife.bind(this);
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle(R.string.edit_goodsclass_title);
        iBack = getIvBack();
        iBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        getDataManager().getGoodsClass(GetStoreClassModel.class, true);
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof GetStoreClassModel) {
            GetStoreClassModel mGetStoreClassModel = (GetStoreClassModel) response;
            if (mGetStoreClassModel.getResult().equals("1")) {
                list = mGetStoreClassModel.getBody();
                int num = 0;
                for(int i=0;i<list.size();i++){
                    num += list.get(i).getGoodsCount();
                }
                GetStoreClassModel.BodyEntity temp = new GetStoreClassModel.BodyEntity(
                        0,"全部",0,list.get(0).getStcState(),list.get(0).getStoreId(),-1,num);
                list.add(0,temp);
                initListView();
            } else {
                showToast("获取商品分类失败");
            }
        } else if (response instanceof DeleteGoodsClassmodel) {
            DeleteGoodsClassmodel mDeleteGoodsClassmodel = (DeleteGoodsClassmodel) response;
            if (mDeleteGoodsClassmodel.getResult().equals("1")) {
                mAdapter.removeByPosition(mPosition);
                showToast("删除成功");
            } else {
                showToast(mDeleteGoodsClassmodel.getBody().getMessage());
            }
        } else if (response instanceof UpdateGoodsClassOrderResultModel) {
            UpdateGoodsClassOrderResultModel mUpdateGoodsClassOrderResultModel = (UpdateGoodsClassOrderResultModel) response;
            if (mUpdateGoodsClassOrderResultModel.getResult().equals("1")) {
                showToast("修改成功");
                isEdit = true;
            } else {
                showToast(mUpdateGoodsClassOrderResultModel.getBody().getMessage());
            }
        }
    }

    private void initListView() {
        //得到滑动listview并且设置监听器。
        channeleditorListview.setDropListener(onDrop);
        channeleditorListview.setRemoveListener(onRemove);
        channeleditorListview.setDragEnabled(true); //设置是否可拖动。

        mAdapter = new EditGoodsClasesAdapter(mContext ,list, this);
        channeleditorListview.setAdapter(mAdapter);
    }

    //监听器在手机拖动停下的时候触发
    private DragSortListView.DropListener onDrop = new DragSortListView.DropListener() {
        @Override
        public void drop(int from, int to) {//from to 分别表示 被拖动控件原位置 和目标位置
            if (from != to && to != 0) {
                LogUtils.e("form:"+from+"to:"+to);
                GetStoreClassModel.BodyEntity from_item = mAdapter.getItem(from);//得到listview的适配器
                GetStoreClassModel.BodyEntity to_item = mAdapter.getItem(to);//得到listview的适配器
                mAdapter.removeByPosition(from);//在适配器中”原位置“的数据。
                mAdapter.insert(from_item, to);//在目标位置中插入被拖动的控件。
                int sort = to_item.getStcSort();
                if (from < to) {
                    sort += 1;
                } else {
                    sort -= 1;
                }
                upDateList(from_item, sort);
            }
        }
    };

    //删除监听器，点击左边差号就触发。删除item操作。
    private DragSortListView.RemoveListener onRemove = new DragSortListView.RemoveListener() {
        @Override
        public void remove(int which) {
            mAdapter.removeByPosition(which);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.editgoods_ok_ll})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editgoods_ok_ll:
                back();
                break;
        }
    }

    public void upDateList(GetStoreClassModel.BodyEntity item, int po) {
        UpdateGoodsClassOrderModel temp = new UpdateGoodsClassOrderModel(
                item.getStoreId(), item.getId(), po);
        String str = new Gson().toJson(temp);
        LogUtils.e("str:"+str);
        getDataManager().editClassOrder(str, UpdateGoodsClassOrderResultModel.class, true);
    }

    private int mPosition = -1;
    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.click_remove:
                if (mAdapter.getItem(position).getGoodsCount()!=0) {
                    showToast("该分类下含有商品，无法删除");
                    return;
                }
                mPosition = position;
                getDataManager().deleteGoodsClass(mAdapter.getItem(position).getStoreId(),
                        mAdapter.getItem(position).getId(), DeleteGoodsClassmodel.class, true);
                break;
            case R.id.edit_name:
                Intent intent = EditGoodsClassNameActivity.buildIntent(
                        mContext,
                        position,
                        mAdapter.getItem(position).getStcName(),
                        mAdapter.getItem(position).getId());
                startActivityForResult(intent,toEditName);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == toEditName) {
            isEdit = true;
            mAdapter.setNameByPositon(data.getIntExtra("position",-1), data.getStringExtra("name"));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 返回键
            back();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void back(){
        if (isEdit) {
            Intent mIntent = new Intent();
            setResult(RESULT_OK,mIntent);
        }
        finish();
    }
}
