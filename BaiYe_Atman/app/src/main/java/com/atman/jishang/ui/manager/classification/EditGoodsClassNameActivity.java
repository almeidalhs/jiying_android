package com.atman.jishang.ui.manager.classification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.LimitSizeTextWatcher;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.net.model.EditClassNameModel;
import com.corelib.widget.MyCleanEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/28 09:17
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class EditGoodsClassNameActivity extends SimpleTitleBarActivity {

    @Bind(R.id.edit_goodsclass_name)
    MyCleanEditText editGoodsclassName;

    private Context mContext = EditGoodsClassNameActivity.this;
    private TextView mBarRight;
    private int position;
    private long id;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editgoodsclassname);
        ButterKnife.bind(this);
        editGoodsclassName.addTextChangedListener(
                new LimitSizeTextWatcher(mContext,editGoodsclassName,5,"商品分类不能超过5个汉字"));
    }

    public static Intent buildIntent(Context context,int position, String name, long id){
        Intent intent = new Intent(context, EditGoodsClassNameActivity.class);
        intent.putExtra("position",position);
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle(R.string.edit_goodsclass_name_title);
        mBarRight = showRightTV(R.string.edit_goodsclass_name_right);
        mBarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id != -1 && position != -1) {
                    name = editGoodsclassName.getText().toString().trim();
                    if (name.isEmpty()) {
                        showToast(getResources().getString(R.string.edit_goodsclass_hint));
                        return;
                    }
                    getDataManager().editClassName(id, name, EditClassNameModel.class, true);
                }
            }
        });
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        name = getIntent().getStringExtra("name");
        id = getIntent().getLongExtra("id",-1);
        position = getIntent().getIntExtra("position",-1);
        editGoodsclassName.setText(name);
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof EditClassNameModel) {
            EditClassNameModel mEditClassNameModel = (EditClassNameModel) response;
            if (mEditClassNameModel.getResult().equals("1")) {
                showToast("修改成功");
                Intent mIntent = new Intent();
                mIntent.putExtra("position",position);
                mIntent.putExtra("name",name);
                setResult(RESULT_OK,mIntent);
                finish();
            } else {
                showToast(mEditClassNameModel.getBody().getMessage());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
