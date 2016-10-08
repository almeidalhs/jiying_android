package com.atman.jishang.ui.personal;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.LimitSizeTextWatcher;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.net.model.FeedBackResultModel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述 反馈建议
 * 作者 tangbingliang
 * 时间 16/4/15 14:31
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class FeedbackSuggestionActivity extends SimpleTitleBarActivity {

    @Bind(R.id.feedback_sug_conent_et)
    EditText feedbackSugConentEt;

    private LinearLayout mNavRightLl;
    private Context mContext = FeedbackSuggestionActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacksuggestion);
        ButterKnife.bind(this);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        setToolbarTitle(R.string.personal_proposal);
        mNavRightLl = showRightLl();
        mNavRightLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = feedbackSugConentEt.getText().toString().trim();
                if (content.isEmpty()) {
                    showToast("反馈意见不能为空");
                    return;
                }
                getDataManager().feedback(content, FeedBackResultModel.class,true);
            }
        });
        feedbackSugConentEt.addTextChangedListener(
                new LimitSizeTextWatcher(mContext,feedbackSugConentEt,255,"反馈意见字数不能超过256个汉字"));
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof FeedBackResultModel) {
            FeedBackResultModel mFeedBackResultModel = (FeedBackResultModel) response;
            if (mFeedBackResultModel.getResult().equals("1")) {
                showToast("你的意见已发送成功，谢谢您的宝贵意见");
            }
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
