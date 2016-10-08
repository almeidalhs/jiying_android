package com.atman.jishang.ui.member.tag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.LimitSizeTextWatcher;
import com.atman.jishang.net.model.CreateTagModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.corelib.widget.MyCleanEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/31 09:05
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class CreateTagActivity extends SimpleTitleBarActivity {

    @Bind(R.id.add_tag_name)
    MyCleanEditText addTagName;

    private Context mContext = CreateTagActivity.this;
    private int memberId;
    private String tagName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createtag);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context, int memberId) {
        Intent intent = new Intent(context, CreateTagActivity.class);
        intent.putExtra("memberId", memberId);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle("添加标签");
        showRightTV("保存").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagName = addTagName.getText().toString().trim();
                if (tagName.isEmpty()) {
                    showToast("请输入标签名");
                    return;
                }
                getDataManager().createTag(memberId, tagName, 0, CreateTagModel.class, true);
            }
        });

        addTagName.addTextChangedListener(
                new LimitSizeTextWatcher(mContext,addTagName,6,"标签名不能超过6个汉字"));
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        memberId = getIntent().getIntExtra("memberId", -1);
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof CreateTagModel) {
            CreateTagModel mCreateTagModel = (CreateTagModel) response;
            showToast(mCreateTagModel.getBody().getMessage());
            if (mCreateTagModel.getResult().equals("1")) {
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
