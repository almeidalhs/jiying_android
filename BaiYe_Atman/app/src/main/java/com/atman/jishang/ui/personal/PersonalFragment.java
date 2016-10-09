package com.atman.jishang.ui.personal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.net.Urls;
import com.atman.jishang.net.model.ShopInformationModel;
import com.atman.jishang.ui.MainActivity;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.BaiYeBaseFragment;
import com.atman.jishang.utils.UiHelper;
import com.corelib.util.DensityUtil;
import com.corelib.widget.ShapeImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 个人中心
 * 作者 tangbingliang
 * 时间 16/4/11 14:10
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class PersonalFragment extends BaiYeBaseFragment {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.personal_head_img)
    ShapeImageView personalHeadImg;
    @Bind(R.id.personal_shopname)
    TextView personalShopName;
    @Bind(R.id.personal_head_rl)
    RelativeLayout personalHeadRl;
    @Bind(R.id.personal_shopinfo_ll)
    LinearLayout personalShopinfoLl;
    @Bind(R.id.personal_recommend_ll)
    LinearLayout personalRecommendLl;
    @Bind(R.id.personal_proposal_ll)
    LinearLayout personalProposalLl;
    @Bind(R.id.personal_cs_ll)
    LinearLayout personalCsLl;
    @Bind(R.id.personal_about_ll)
    LinearLayout personalAboutLl;
    @Bind(R.id.personal_shopsignature_tx)
    TextView personalShopsignatureTx;
    @Bind(R.id.personal_headbg_img)
    ImageView personalHeadbgImg;
    @Bind(R.id.personal_shopaddre_tx)
    TextView personalShopaddreTx;

    private final int toPersonalInfo = 606;
    private final int toShopInfo = 607;
    private final int toCreateShop = 608;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
        tvTitle.setText(getActivity().getResources().getString(R.string.personal_title));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getmWidth(),
                (getmWidth() * 400 / 1504) + DensityUtil.dp2px(getActivity(), 80));
        personalHeadRl.setLayoutParams(params);
    }

    private void getShopInfo() {
        setBitmapToImageView(personalHeadImg,
                Urls.HEADIMG_BEFOR + BaiYeBaseApplication.mLoginResultModel.getBody().getMemberAvatar(),
                R.mipmap.personal_head_default);
        if (BaiYeBaseApplication.mShopInformationModel == null
                && BaiYeBaseApplication.mLoginResultModel.getBody().getStoreId() == 0) {
            personalShopName.setText("您尚未开店");
            personalShopaddreTx.setText("点击此处开始创建店铺");
        } else {
            getDataManager().getShopInformation(ShopInformationModel.class, true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getShopInfo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

        }
    }

    @Override
    public void onResponse(Object response, String data) {
        super.onResponse(response, data);
        if (response instanceof ShopInformationModel) {
            ShopInformationModel mShopInformationModel = (ShopInformationModel) response;
            if (!mShopInformationModel.getResult().equals("1")) {
                showToast(mShopInformationModel.getBody().getMessage());
            } else {
                BaiYeBaseApplication.mShopInformationModel = mShopInformationModel;
                personalShopName.setText(mShopInformationModel.getBody().getStoreName());
                personalShopaddreTx.setText("  "+mShopInformationModel.getBody().getStoreAddress());
                personalShopsignatureTx.setText(mShopInformationModel.getBody().getDescription());
                setBitmapToImageView(personalHeadbgImg,
                        Urls.HEADIMG_BEFOR + mShopInformationModel.getBody().getStoreBanner(),
                        R.mipmap.personal_top_bg);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.personal_head_img, R.id.personal_head_rl, R.id.personal_shopinfo_ll,
            R.id.personal_recommend_ll, R.id.personal_proposal_ll, R.id.personal_cs_ll,
            R.id.personal_about_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.personal_head_img:
                startActivityForResult(new Intent(getActivity(), PersonalInformationActivity.class), toPersonalInfo);
                break;
            case R.id.personal_head_rl:
            case R.id.personal_shopinfo_ll:
                if (BaiYeBaseApplication.mShopInformationModel == null) {
                    startActivityForResult(new Intent(getActivity(), CreateShopActivity.class), toShopInfo);
                } else {
                    startActivityForResult(new Intent(getActivity(), ShopInformationActivity.class), toCreateShop);
                }
                break;
            case R.id.personal_recommend_ll:
//                startActivity(new Intent(getActivity(), RecommendFriendsActivity.class));
                if (!UiHelper.isTabletDevice(getActivity())) {
                    sendSMS("我发现个帮你做生意的应用，非常好用，我现在生意像飞一样！" + BaiYeBaseApplication.mSMS_URL);
                } else {
                    showToast("您的设备无法访问通讯录");
                }
                break;
            case R.id.personal_proposal_ll:
                startActivity(new Intent(getActivity(), FeedbackSuggestionActivity.class));
                break;
            case R.id.personal_cs_ll:
                toPhone(getActivity(), getResources().getString(R.string.service_telephone));
                break;
            case R.id.personal_about_ll:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
        }
    }

    private void sendSMS(String smsBody) {
        Uri smsToUri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", smsBody);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == toPersonalInfo) {
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        } else if (requestCode == toShopInfo || requestCode == toCreateShop) {
            getDataManager().getShopInformation(ShopInformationModel.class, true);
        }
    }
}
