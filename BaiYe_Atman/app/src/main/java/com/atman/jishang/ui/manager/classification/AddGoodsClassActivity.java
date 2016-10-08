package com.atman.jishang.ui.manager.classification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.LimitSizeTextWatcher;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.net.model.CreateStoreClassModel;
import com.corelib.widget.MyCleanEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述 增加商品分类
 * 作者 tangbingliang
 * 时间 16/4/27 14:02
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AddGoodsClassActivity extends SimpleTitleBarActivity {

    @Bind(R.id.add_goodsclass_name)
    MyCleanEditText addGoodsclassName;

    private Context mContext = AddGoodsClassActivity.this;
    private TextView mBarRightTX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgoodsclass);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle(R.string.add_goodsclass_title);
        mBarRightTX = showRightTV(R.string.add_goodsclass_ok);
        mBarRightTX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = addGoodsclassName.getText().toString().trim();
                if (str.isEmpty()) {
                    showToast(getResources().getString(R.string.add_goodsclass_hint));
                    return;
                }
                getDataManager().createGoodsClass(str, CreateStoreClassModel.class, true);
            }
        });
        addGoodsclassName.addTextChangedListener(
                new LimitSizeTextWatcher(mContext,addGoodsclassName,5,"商品分类不能超过5个汉字"));
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof CreateStoreClassModel) {
            CreateStoreClassModel mCreateStoreClassModel = (CreateStoreClassModel) response;
            if (mCreateStoreClassModel.getResult().equals("1")) {
                showToast("分类创建成功");
                Intent mIntent = new Intent();
                setResult(RESULT_OK,mIntent);
                finish();
            } else {
                showToast(mCreateStoreClassModel.getBody().getMessage());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
