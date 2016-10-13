package com.atman.jishang.ui.member;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.MemberDetailsGridViewAdapter;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.interfaces.IndustryTitleConfigInterface;
import com.atman.jishang.net.Urls;
import com.atman.jishang.net.model.DeleteMemberModel;
import com.atman.jishang.net.model.GetIndustryTitleConfigModel;
import com.atman.jishang.net.model.GetMemberDetailsModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.ui.member.records.MemberRecordsActivity;
import com.atman.jishang.ui.member.tag.MemberTagManagementActivity;
import com.atman.jishang.utils.MyTools;
import com.atman.jishang.utils.UiHelper;
import com.atman.jishang.widget.BottomDialog;
import com.atman.jishang.widget.XCFlowLayout;
import com.atman.jishang.widget.YLBDialog;
import com.atman.jishang.widget.showimage.ShowWebImageActivity;
import com.choicepicture_library.model.SelectImageItem;
import com.choicepicture_library.tools.Bimp;
import com.corelib.util.LogUtils;
import com.corelib.widget.MyGridView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/27 16:25
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MemberDetailsActivity extends SimpleTitleBarActivity implements AdapterInterface{

    @Bind(R.id.memberdetails_level_tx)
    TextView memberdetailsLevelTx;
    @Bind(R.id.memberdetails_head_iv)
    ImageView memberdetailsHeadIv;
    @Bind(R.id.memberdetails_sex_iv)
    ImageView memberdetailsSexIv;
    @Bind(R.id.memberdetails_money_tx)
    TextView memberdetailsMoneyTx;
    @Bind(R.id.memberdetails_name_tx)
    TextView memberdetailsNameTx;
    @Bind(R.id.memberdetails_phone_tx)
    TextView memberdetailsPhoneTx;
    @Bind(R.id.memberdetails_gender_tx)
    TextView memberdetailsGenderTx;
    @Bind(R.id.memberdetails_birthday_tx)
    TextView memberdetailsBirthdayTx;
    @Bind(R.id.memberdetails_addr_tx)
    TextView memberdetailsAddrTx;
    @Bind(R.id.memberdetails_weixin_tx)
    TextView memberdetailsWeixinTx;
    @Bind(R.id.memberdetails_qq_tx)
    TextView memberdetailsQqTx;
    @Bind(R.id.memberdetails_remark_tx)
    TextView memberdetailsRemarkTx;
    @Bind(R.id.flowlayout)
    XCFlowLayout flowlayout;
    @Bind(R.id.memberdetails_mobile_ll)
    LinearLayout memberdetailsMobileLl;
    @Bind(R.id.memberdetails_sex_ll)
    LinearLayout memberdetailsSexLl;
    @Bind(R.id.memberdetails_birthday_ll)
    LinearLayout memberdetailsBirthdayLl;
    @Bind(R.id.memberdetails_addr_ll)
    LinearLayout memberdetailsAddrLl;
    @Bind(R.id.memberdetails_weixin_ll)
    LinearLayout memberdetailsWeixinLl;
    @Bind(R.id.memberdetails_qq_ll)
    LinearLayout memberdetailsQqLl;
    @Bind(R.id.memberdetails_remark_ll)
    LinearLayout memberdetailsRemarkLl;
    @Bind(R.id.noScrollgridview)
    MyGridView noScrollgridview;
    @Bind(R.id.memberdetails_image_ll)
    LinearLayout memberdetailsImageLl;
    @Bind(R.id.memberdetails_scrollview_ll)
    ScrollView memberdetailsScrollviewLl;

    private Context mContext = MemberDetailsActivity.this;
    private int id;
    private final static int TO_EDITMEMBER = 222;
    private boolean isEdit = false;
    private GetMemberDetailsModel.BodyEntity mMemberInfo;
    private MemberDetailsGridViewAdapter adapter;

    private String baseStr = "会员";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberdetails);
        ButterKnife.bind(this);
    }

    public static Intent buildIntent(Context context, int id) {
        Intent intent = new Intent(context, MemberDetailsActivity.class);
        intent.putExtra("id", id);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bimp.drr.clear();
        Bimp.drr_or.clear();
        Bimp.bmp.clear();
        id = getIntent().getIntExtra("id", -1);
        memberdetailsScrollviewLl.scrollTo(0,0);
        getDataManager().getMemberDetails(id, GetMemberDetailsModel.class, true);
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);

        if (BaiYeBaseApplication.mGetIndustryTitleConfigModel!=null) {
            List<GetIndustryTitleConfigModel.BodyBean> temp = BaiYeBaseApplication.mGetIndustryTitleConfigModel.getBody();
            for (int i=0;i< temp.size();i++) {
                if (temp.get(i).getPageNum() == IndustryTitleConfigInterface.ConfigMemberId
                        && temp.get(i).getTitle()!=null) {
                    baseStr = temp.get(i).getTitle();
                }
            }
        }

        setToolbarTitle(baseStr+"详情");
        getIvRightOk().setImageResource(R.mipmap.coupon_details_topright);
        showRightLl().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddrPopupWindow(v);
            }
        });
        getLlBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        initGriaView();
    }

    private void back() {
        if (isEdit) {
            Intent mIntent = new Intent();
            setResult(RESULT_OK,mIntent);
        }
        finish();
    }

    private void showAddrPopupWindow(View view) {
        BottomDialog.Builder builder = new BottomDialog.Builder(mContext);
        builder.setTitle(Html.fromHtml("<font color=\"#8F8F8F\">更多</font>"));
        builder.setItems(new String[]{"编辑"+baseStr, "删除"+baseStr}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {//编辑
                    isEdit = false;
                    startActivityForResult(EditMemberInformationActivity.buildIntent(mContext, id), TO_EDITMEMBER);
                } else {//删除
                    YLBDialog.Builder builder = new YLBDialog.Builder(mContext);
                    builder.setMessage("您确定要删除当前"+baseStr+"吗？");
                    builder.setPositiveButton("删除"+baseStr, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            getDataManager().deleteMember(id, DeleteMemberModel.class, true);
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });
        builder.setNeutralButton("取消", null);
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == TO_EDITMEMBER) {
            isEdit = true;
        }

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
        if (response instanceof GetMemberDetailsModel) {
            GetMemberDetailsModel mGetMemberDetailsModel = (GetMemberDetailsModel) response;
            if (mGetMemberDetailsModel.getResult().equals("1")) {
                mMemberInfo = mGetMemberDetailsModel.getBody();
                displayView();
            } else {
                showToast(mGetMemberDetailsModel.getBody().getMessage());
            }
        } else if (response instanceof DeleteMemberModel) {
            DeleteMemberModel mDeleteMemberModel = (DeleteMemberModel) response;
            if (mDeleteMemberModel.getResult().equals("1")) {
                Intent mIntent = new Intent();
                setResult(RESULT_OK,mIntent);
                finish();
            }
            showToast(mDeleteMemberModel.getBody().getMessage());
        }
    }

    private void displayView() {
        for (int i=0;i<mMemberInfo.getImgList().size();i++) {
            Bimp.num += 1;
            Bimp.bmp.add(null);
            Bimp.drr.add(Urls.HEADIMG_BEFOR+mMemberInfo.getImgList().get(i));
            SelectImageItem mSelectImageItem = new SelectImageItem("","","");
            Bimp.drr_or.add(mSelectImageItem);
        }
        if (mMemberInfo.getType() == 1) {
            memberdetailsLevelTx.setText("普通"+baseStr);
        } else {
            memberdetailsLevelTx.setText("VIP"+baseStr);
        }
        ImageLoader.getInstance().displayImage(Urls.HEADIMG_BEFOR + mMemberInfo.getAvatar(), memberdetailsHeadIv,
                BaiYeBaseApplication.getApp().getOptionsHead());
        if (mMemberInfo.isSex()) {
            memberdetailsSexIv.setImageResource(R.mipmap.member_boy_ic);
            memberdetailsGenderTx.setText("男");
        } else {
            memberdetailsSexIv.setImageResource(R.mipmap.member_girl_ic);
            memberdetailsGenderTx.setText("女");
        }

        if (mMemberInfo.getMobile() != null && !mMemberInfo.getMobile().isEmpty()) {
            memberdetailsMobileLl.setVisibility(View.VISIBLE);
            memberdetailsPhoneTx.setText(mMemberInfo.getMobile());
        } else {
            memberdetailsMobileLl.setVisibility(View.GONE);
        }

        if (mMemberInfo.getBirthday() != 0) {
            memberdetailsBirthdayLl.setVisibility(View.VISIBLE);
            memberdetailsBirthdayTx.setText(MyTools.convertTime(mMemberInfo.getBirthday(), "yyyy-MM-dd"));
        } else {
            memberdetailsBirthdayLl.setVisibility(View.GONE);
        }

        if (mMemberInfo.getAddress() != null && !mMemberInfo.getAddress().isEmpty()) {
            memberdetailsAddrLl.setVisibility(View.VISIBLE);
            memberdetailsAddrTx.setText(mMemberInfo.getAddress());
        } else {
            memberdetailsAddrLl.setVisibility(View.GONE);
        }

        if (mMemberInfo.getWeixin() != null && !mMemberInfo.getWeixin().isEmpty()) {
            memberdetailsWeixinLl.setVisibility(View.VISIBLE);
            memberdetailsWeixinTx.setText(mMemberInfo.getWeixin());
        } else {
            memberdetailsWeixinLl.setVisibility(View.GONE);
        }

        if (mMemberInfo.getQq() != null && !mMemberInfo.getQq().isEmpty()) {
            memberdetailsQqLl.setVisibility(View.VISIBLE);
            memberdetailsQqTx.setText(mMemberInfo.getQq());
        } else {
            memberdetailsQqLl.setVisibility(View.GONE);
        }

        if (mMemberInfo.getMemo() != null && !mMemberInfo.getMemo().isEmpty()) {
            memberdetailsRemarkLl.setVisibility(View.VISIBLE);
            memberdetailsRemarkTx.setText(mMemberInfo.getMemo());
        } else {
            memberdetailsRemarkLl.setVisibility(View.GONE);
        }
        if (mMemberInfo.getImgList() != null && mMemberInfo.getImgList().size() > 0) {
            memberdetailsImageLl.setVisibility(View.VISIBLE);
            adapter.setImgList(mMemberInfo.getImgList());
        } else {
            memberdetailsImageLl.setVisibility(View.GONE);
        }

        memberdetailsNameTx.setText(mMemberInfo.getName());
        memberdetailsMoneyTx.setText("¥ " + new DecimalFormat("##0.00").format(mMemberInfo.getTotalConsume()));

        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = YLBDialog.dip2px(mContext, 2);
        lp.rightMargin = YLBDialog.dip2px(mContext, 2);
        lp.topMargin = YLBDialog.dip2px(mContext, 2);
        lp.bottomMargin = YLBDialog.dip2px(mContext, 2);
        flowlayout.removeAllViews();
        for (int i = 0; i < mMemberInfo.getTagList().size(); i++) {
            TextView view = new TextView(this);
            view.setText(mMemberInfo.getTagList().get(i).getTagName());
            view.setTextColor(Color.WHITE);
            view.setPadding(YLBDialog.dip2px(mContext, 5), YLBDialog.dip2px(mContext, 5),
                    YLBDialog.dip2px(mContext, 5), YLBDialog.dip2px(mContext, 5));
            view.setTag(i);
            view.setTextSize(10);
            view.setOnClickListener(myOnClickListener);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.tag_bg));
            GradientDrawable mGradientDrawable = (GradientDrawable) view.getBackground();
            mGradientDrawable.setColor(Color.parseColor(MyTools.RandomColor()));
            flowlayout.addView(view, lp);
        }

        TextView viewMore = new TextView(this);
        viewMore.setText("添加标签");
        viewMore.setTextColor(Color.BLACK);
        viewMore.setPadding(YLBDialog.dip2px(mContext, 5), YLBDialog.dip2px(mContext, 5),
                YLBDialog.dip2px(mContext, 5), YLBDialog.dip2px(mContext, 5));
        viewMore.setTag(-1);
        viewMore.setTextSize(10);
        viewMore.setOnClickListener(myOnClickListener);
        viewMore.setBackgroundDrawable(getResources().getDrawable(R.drawable.add_tag_bg));
        flowlayout.addView(viewMore, lp);

        memberdetailsScrollviewLl.scrollTo(0,0);

    }

    private void initGriaView() {
        ImageLoader iL = ImageLoader.getInstance();
//        noScrollgridview = (MyGridView) findViewById(R.id.noScrollgridview);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));//选中的时候为透明色

        adapter = new MemberDetailsGridViewAdapter(this, memberdetailsScrollviewLl, iL, this);//初始化适配器

        noScrollgridview.setAdapter(adapter);
    }

    View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int num = (int) v.getTag();
            startActivity(MemberTagManagementActivity.buildIntent(mContext, id));
        }
    };

    @Override
    protected void onDestroy() {
        Bimp.num = 0;
        Bimp.drr.clear();
        Bimp.drr_or.clear();
        Bimp.bmp.clear();
        super.onDestroy();
    }

    @OnClick({R.id.memberdetails_phone_ll, R.id.memberdetails_message_ll, R.id.memberdetails_record_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.memberdetails_phone_ll:
                if (mMemberInfo !=null && mMemberInfo.getMobile().isEmpty()) {
                    showToast("该用户尚未添加号码");
                } else {
                    if (!UiHelper.isTabletDevice(mContext)) {
                        toPhone(mContext, mMemberInfo.getMobile());
                    } else {
                        showToast("您的设备不支持拨号");
                    }
                }
                break;
            case R.id.memberdetails_message_ll:
                if (mMemberInfo !=null) {
                    UiHelper.sendSMS(mContext, "", mMemberInfo.getMobile());
                }
                break;
            case R.id.memberdetails_record_ll:
                if (mMemberInfo !=null) {
                    startActivity(MemberRecordsActivity.buildIntent(mContext, id, mMemberInfo.getTelephone()));
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 返回键
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onItemClick(View view, int position) {
        LogUtils.e("position:"+position);
        String str = "";
        for (int i=0;i<mMemberInfo.getImgList().size();i++) {
            if (i!=0) {
                str += ",";
            }
            str += Urls.HEADIMG_BEFOR + mMemberInfo.getImgList().get(i);
        }
        Intent intent = new Intent(mContext, ShowWebImageActivity.class);
//        intent.putExtra("ID", position);
        intent.putExtra("isHit", true);
        intent.putExtra("image", str);
        startActivity(intent);
    }
}
