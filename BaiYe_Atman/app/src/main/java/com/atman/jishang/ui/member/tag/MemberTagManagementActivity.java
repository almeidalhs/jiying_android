package com.atman.jishang.ui.member.tag;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.model.CreateTagModel;
import com.atman.jishang.net.model.DeleteTagModel;
import com.atman.jishang.net.model.GetMemberDetailsModel;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.utils.MyTools;
import com.atman.jishang.widget.XCFlowLayout;
import com.atman.jishang.widget.YLBDialog;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 描述 会员标签管理
 * 作者 tangbingliang
 * 时间 16/5/30 11:17
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MemberTagManagementActivity extends SimpleTitleBarActivity {

    @Bind(R.id.membertag_name_tx)
    TextView membertagNameTx;
    @Bind(R.id.flowlayout_top)
    XCFlowLayout flowlayoutTop;
    @Bind(R.id.flowlayout_bottom)
    XCFlowLayout flowlayoutBottom;

    private Context mContext = MemberTagManagementActivity.this;
    private int id;
    private GetMemberDetailsModel.BodyEntity mMemberInfo;
    private ViewGroup.MarginLayoutParams lp;
    private int mTopNum,mBottomNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membertagmanagement);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context, int id) {
        Intent intent = new Intent(context, MemberTagManagementActivity.class);
        intent.putExtra("id", id);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        id = getIntent().getIntExtra("id", -1);
        getDataManager().getMemberDetails(id, GetMemberDetailsModel.class, true);
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle("标签管理");
        lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = YLBDialog.dip2px(mContext, 2);
        lp.rightMargin = YLBDialog.dip2px(mContext, 2);
        lp.topMargin = YLBDialog.dip2px(mContext, 2);
        lp.bottomMargin = YLBDialog.dip2px(mContext, 2);
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
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof GetMemberDetailsModel) {
            GetMemberDetailsModel mGetMemberDetailsModel = (GetMemberDetailsModel) response;
            if (mGetMemberDetailsModel.getResult().equals("1")) {
                mMemberInfo = mGetMemberDetailsModel.getBody();

                membertagNameTx.setText(mMemberInfo.getName());
                displayViewTop();
                displayViewBottom();
            } else {
                showToast(mGetMemberDetailsModel.getBody().getMessage());
            }
        } else if (response instanceof CreateTagModel) {
            CreateTagModel mCreateTagModel = (CreateTagModel) response;
            if (mCreateTagModel.getResult().equals("1")) {
                mMemberInfo.getTagList().add(new GetMemberDetailsModel.BodyEntity.TagListEntity(
                        mMemberInfo.getStoreTagList().get(mBottomNum).getId(),
                        mMemberInfo.getStoreTagList().get(mBottomNum).getTagName()
                ));
                mMemberInfo.getStoreTagList().remove(mBottomNum);
                displayViewTop();
                displayViewBottom();
            } else {
                showToast(mCreateTagModel.getBody().getMessage());
            }
        } else if (response instanceof DeleteTagModel) {
            DeleteTagModel mDeleteTagModel = (DeleteTagModel) response;
            if (mDeleteTagModel.getResult().equals("1")) {
                mMemberInfo.getStoreTagList().add(new GetMemberDetailsModel.BodyEntity.StoreTagListEntity(
                        mMemberInfo.getTagList().get(mTopNum).getId(),id,
                        mMemberInfo.getTagList().get(mTopNum).getTagName()
                ));
                mMemberInfo.getTagList().remove(mTopNum);
                displayViewTop();
                displayViewBottom();
            } else {
                showToast(mDeleteTagModel.getBody().getMessage());
            }
        }
    }

    private void displayViewTop() {
        flowlayoutTop.removeAllViews();
        for (int i = 0; i < mMemberInfo.getTagList().size(); i++) {
            TextView view = new TextView(this);
            view.setText(mMemberInfo.getTagList().get(i).getTagName());
            view.setTextColor(Color.WHITE);
            view.setPadding(YLBDialog.dip2px(mContext, 5), YLBDialog.dip2px(mContext, 5),
                    YLBDialog.dip2px(mContext, 5), YLBDialog.dip2px(mContext, 5));
            view.setTag(i);
            view.setTextSize(10);
            view.setOnClickListener(myOnClickListenerTop);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.tag_bg));
            GradientDrawable mGradientDrawable = (GradientDrawable) view.getBackground();
            mGradientDrawable.setColor(Color.parseColor(MyTools.RandomColor()));
            flowlayoutTop.addView(view, lp);
        }

        TextView viewMore = new TextView(this);
        viewMore.setText("添加标签");
        viewMore.setTextColor(Color.BLACK);
        viewMore.setPadding(YLBDialog.dip2px(mContext, 5), YLBDialog.dip2px(mContext, 5),
                YLBDialog.dip2px(mContext, 5), YLBDialog.dip2px(mContext, 5));
        viewMore.setTag(-1);
        viewMore.setTextSize(10);
        viewMore.setOnClickListener(myOnClickListenerTop);
        viewMore.setBackgroundDrawable(getResources().getDrawable(R.drawable.add_tag_bg));
        flowlayoutTop.addView(viewMore, lp);

    }

    private void displayViewBottom() {

        flowlayoutBottom.removeAllViews();
        for (int i = 0; i < mMemberInfo.getStoreTagList().size(); i++) {
            TextView view = new TextView(this);
            view.setText(mMemberInfo.getStoreTagList().get(i).getTagName());
            view.setTextColor(Color.parseColor(MyTools.RandomColor()));
            view.setPadding(YLBDialog.dip2px(mContext, 5), YLBDialog.dip2px(mContext, 5),
                    YLBDialog.dip2px(mContext, 5), YLBDialog.dip2px(mContext, 5));
            view.setTag(i);
            view.setTextSize(12);
            view.setOnClickListener(myOnClickListenerBottom);
            flowlayoutBottom.addView(view, lp);
        }

    }

    View.OnClickListener myOnClickListenerTop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mTopNum = (int) v.getTag();
            if (mTopNum == -1) {
                startActivity(CreateTagActivity.buildIntent(mContext, id));
            } else {
                getDataManager().deleteTag(id, mMemberInfo.getTagList().get(mTopNum).getId(), DeleteTagModel.class, true);
            }
        }
    };

    View.OnClickListener myOnClickListenerBottom = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mBottomNum = (int) v.getTag();
            getDataManager().createTag(id, "", mMemberInfo.getStoreTagList().get(mBottomNum).getId(),
                    CreateTagModel.class, true);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
