package com.atman.jishang.ui.member;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.BirthdayAdapter;
import com.atman.jishang.interfaces.MyTextWatcher;
import com.atman.jishang.net.Urls;
import com.atman.jishang.net.model.CreateMemberModel;
import com.atman.jishang.net.model.GoodsUpLoadImgMpdel;
import com.atman.jishang.net.model.UpdateHeadImgModel;
import com.atman.jishang.net.upload.UpLoadPicture;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.atman.jishang.ui.base.BaiYeBaseFragment;
import com.atman.jishang.utils.MyTools;
import com.atman.jishang.utils.UiHelper;
import com.atman.jishang.widget.BottomDialog;
import com.atman.jishang.widget.WheelView.OnWheelChangedListener;
import com.atman.jishang.widget.WheelView.OnWheelScrollListener;
import com.atman.jishang.widget.WheelView.WheelView;
import com.choicepicture_library.ImageGridActivity;
import com.choicepicture_library.PhotoActivity;
import com.choicepicture_library.adapters.GridAdapter;
import com.choicepicture_library.model.SelectImageItem;
import com.choicepicture_library.tools.Bimp;
import com.choicepicture_library.tools.UploadUtil;
import com.corelib.util.LogUtils;
import com.corelib.widget.MyCleanEditText;
import com.corelib.widget.MyGridView;
import com.corelib.widget.RoundImageView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 单个添加会员
 * 作者 tangbingliang
 * 时间 16/5/25 10:50
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AddSingleMemberFragment extends BaiYeBaseFragment
        implements UpLoadPicture.uploadListener, UploadUtil.OnUploadProcessListener {


    @Bind(R.id.addsinglemember_head_iv)
    RoundImageView addsinglememberHeadIv;
    @Bind(R.id.addsinglemember_name_et)
    MyCleanEditText addsinglememberNameEt;
    @Bind(R.id.addsinglemember_mobile_et)
    MyCleanEditText addsinglememberMobileEt;
    @Bind(R.id.addsinglemember_sex_tv)
    TextView addsinglememberSexTv;
    @Bind(R.id.addmenber_normal)
    RadioButton addmenberNormal;
    @Bind(R.id.addmenber_vip)
    RadioButton addmenberVip;
    @Bind(R.id.addsinglemember_addr_et)
    MyCleanEditText addsinglememberAddrEt;
    @Bind(R.id.addsinglemember_birthday_tv)
    TextView addsinglememberBirthdayTv;
    @Bind(R.id.addsinglemember_weixin_et)
    MyCleanEditText addsinglememberWeixinEt;
    @Bind(R.id.addsinglemember_qq_et)
    MyCleanEditText addsinglememberQqEt;
    @Bind(R.id.addsinglemember_remarks_et)
    MyCleanEditText addsinglememberRemarksEt;
    @Bind(R.id.noScrollgridview)
    MyGridView noScrollgridview;
    @Bind(R.id.addmenber_more_ll)
    LinearLayout addmenberMoreLl;
    @Bind(R.id.addmenber_display_tx)
    TextView addmenberDisplayTx;


    private String path = "";
    private String mAvatarUrl = "";
    private List<String> listImg = new ArrayList<>();
    private GridAdapter adapter;
    private String memberImage;

    private String name;
    private String mobile;
    private boolean sex = false;//false：女 ，true：男
    private long birthday;
    private String address;
    private String weixin;
    private String qq;
    private String remark;
    private int type = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addsinglemember, null);
        ButterKnife.bind(this, view);
        Bimp.drr.clear();
        Bimp.drr_or.clear();
        Bimp.bmp.clear();
        return view;
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);

        addsinglememberNameEt.addTextChangedListener(new MyTextWatcher(getActivity(), addsinglememberNameEt,true,9,"您输入内容的长度不能超过10个字"));
        addsinglememberMobileEt.addTextChangedListener(new MyTextWatcher(getActivity(), addsinglememberMobileEt,true,19,"您输入内容的长度不能超过20个字"));
        addsinglememberAddrEt.addTextChangedListener(new MyTextWatcher(getActivity(), addsinglememberAddrEt,true,49,"您输入内容的长度不能超过50个字"));
        addsinglememberWeixinEt.addTextChangedListener(new MyTextWatcher(getActivity(), addsinglememberWeixinEt,true,19,"您输入内容的长度不能超过20个字"));
        addsinglememberQqEt.addTextChangedListener(new MyTextWatcher(getActivity(), addsinglememberQqEt,true,14,"您输入内容的长度不能超过15个字"));
        addsinglememberRemarksEt.addTextChangedListener(new MyTextWatcher(getActivity(), addsinglememberRemarksEt,true,255,"您输入内容的长度不能超过256个字"));
        addsinglememberBirthdayTv.setText(MyTools.getLacalDate());

        initGridView();
    }

    private void initGridView() {
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));//选中的时候为透明色

        adapter = new GridAdapter(getActivity());//初始化适配器
        adapter.update();

        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg2 == Bimp.bmp.size()) {
                    if (getActivity().getCurrentFocus() != null) {
                        ((InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus()
                                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    showAddrPopupWindow(arg1, 2);
                } else {
                    Intent intent = new Intent(getActivity(), PhotoActivity.class);
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            noScrollgridview.setAdapter(adapter);
            adapter.update();
        }
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof UpdateHeadImgModel) {
            UpdateHeadImgModel mUpdateHeadImgModel = (UpdateHeadImgModel) response;
            if (mUpdateHeadImgModel.getResult().equals("1")) {
                BaiYeBaseApplication.mLoginResultModel.getBody().setMemberAvatar(mAvatarUrl);
                ImageLoader.getInstance().displayImage(Urls.HEADIMG_BEFOR + mAvatarUrl,
                        addsinglememberHeadIv, BaiYeBaseApplication.getApp().getOptionsHead());
                showToast("头像上传成功！");
            } else {
                showToast(mUpdateHeadImgModel.getBody().getMessage());
            }
        } else if (response instanceof CreateMemberModel) {
            CreateMemberModel mCreateMemberModel = (CreateMemberModel) response;
            if (mCreateMemberModel.getResult().equals("1")) {
                showToast("会员添加成功");
                Intent intent=new Intent();
                getActivity().setResult(getActivity().RESULT_OK, intent);
                getActivity().finish();
            } else {
                showToast(mCreateMemberModel.getBody().getMessage());
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.addsinglemember_head_iv, R.id.addsinglemember_head_tx, R.id.addmenber_vip,
            R.id.addmenber_normal, R.id.addsinglemember_birthday_tv, R.id.addmember_finish_ll,
            R.id.addmenber_gender_ll, R.id.addmenber_display_ll, R.id.addmember_telephone_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addmember_telephone_iv:
                // 跳转到系统的通讯录界面
                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 999);
                break;
            case R.id.addsinglemember_head_iv:
            case R.id.addsinglemember_head_tx:
                showAddrPopupWindow(view, 1);
                break;
            case R.id.addmenber_vip:
                type = 2;
                break;
            case R.id.addmenber_normal:
                type = 1;
                break;
            case R.id.addsinglemember_birthday_tv:
                showTypePopupWindow(view);
                break;
            case R.id.addmember_finish_ll:
                if (initPrams()) {
                    return;
                }
                forUpLoadPic();
                break;
            case R.id.addmenber_gender_ll:
                showAddrPopupWindow();
                break;
            case R.id.addmenber_display_ll:
                if (addmenberMoreLl.getVisibility() == View.VISIBLE) {
                    addmenberMoreLl.setVisibility(View.GONE);
                    addmenberDisplayTx.setText("更多信息");
                } else {
                    addmenberMoreLl.setVisibility(View.VISIBLE);
                    addmenberDisplayTx.setText("收起更多信息");
                }
                break;
        }
    }

    private void showAddrPopupWindow() {
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setTitle(Html.fromHtml("<font color=\"#f9464a\">选择性别</font>"));
        builder.setItems(new String[]{"女", "男"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    sex = false;
                    addsinglememberSexTv.setText("女");
                } else {
                    sex = true;
                    addsinglememberSexTv.setText("男");
                }
            }
        });
        builder.setNeutralButton("取消", null);
        builder.show();
    }

    private boolean initPrams() {
        name = addsinglememberNameEt.getText().toString().trim();
        mobile = addsinglememberMobileEt.getText().toString().trim();
        birthday = MyTools.transformationToUnix(addsinglememberBirthdayTv.getText().toString(),"yyyy-MM-dd");
        address = addsinglememberAddrEt.getText().toString().trim();
        weixin = addsinglememberWeixinEt.getText().toString().trim();
        qq = addsinglememberQqEt.getText().toString().trim();
        remark = addsinglememberRemarksEt.getText().toString();

//        if (mAvatarUrl.isEmpty()) {
//            showToast("请设置会员头像");
//            return true;
//        }
        if (name.isEmpty()) {
            showToast("请输入姓名");
            return true;
        }
        return false;
    }

    private void forUpLoadPic() {
        showLoading();
        if (Bimp.drr.size()==0) {
            getDataManager().createMember(birthday, mAvatarUrl, sex, remark, weixin, address, name,
                    qq, mobile, type, listImg, CreateMemberModel.class, true);
            return;
        }
        UploadUtil uploadUtil = UploadUtil.getInstance();
        uploadUtil.setOnUploadProcessListener(this);  //设置监听器监听上传状态
        Map<String, String> params = new HashMap<String, String>();
        uploadUtil.uploadFile(Bimp.drr, Urls.RWH_HOST_UP + Urls.UP_MEMBERIMG, params);
    }

    private int tagImg = 1;
    private Uri imageUri;
    private final int CHOOSE_BIG_PICTURE_BOTTOM = 333;
    private final int CHOOSE_BIG_PICTURE = 444;
    private final int TAKE_BIG_PICTURE = 555;
    private final int CROP_BIG_PICTURE = 666;
    private static final int TAKE_PICTURE = 0x000000;
    private int outputX = 200;
    private int outputY = 200;

    private void showAddrPopupWindow(View view, final int tag) {
        tagImg = tag;
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setTitle(Html.fromHtml("<font color=\"#f9464a\">选择头像</font>"));
        builder.setItems(new String[]{"拍照", "从相册中获取"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {//拍照
                    if (tagImg == 1) {
                        path = UiHelper.photo(getActivity(), path, TAKE_BIG_PICTURE);
                    } else {
                        path = UiHelper.photo(getActivity(), path, TAKE_PICTURE);
                    }
                } else {//选择照片
                    if (tagImg == 1) {
                        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
                        getAlbum.setType("image/*");
                        startActivityForResult(getAlbum, CHOOSE_BIG_PICTURE);
                    } else {
                        startActivityForResult(new Intent(getActivity(), ImageGridActivity.class), CHOOSE_BIG_PICTURE_BOTTOM);
                    }

                }
            }
        });
        builder.setNeutralButton("取消", null);
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == CHOOSE_BIG_PICTURE) {//选择照片
            imageUri = data.getData();
            LogUtils.e("CHOOSE_BIG_PICTURE>>>>imageUri:" + imageUri);
            cropImageUri(imageUri, outputX, outputX, CROP_BIG_PICTURE);
        } else if (requestCode == TAKE_BIG_PICTURE) {
            imageUri = Uri.parse("file:///" + path);
            LogUtils.e("TAKE_BIG_PICTURE>>>>imageUri:" + imageUri);
            cropImageUri(imageUri, outputX, outputX, CROP_BIG_PICTURE);
        } else if (requestCode == CROP_BIG_PICTURE) {
            LogUtils.e("CROP_BIG_PICTURE>>>>imageUri:" + imageUri);
            if (imageUri != null) {
                new UpLoadPicture(getActivity(), Urls.UP_MEMBER_HEADIMG, this).execute(imageUri);
            }
        } else if (requestCode == TAKE_PICTURE) {
            if (Bimp.drr.size() < Bimp.max && resultCode == -1) {
                LogUtils.e("path:" + path);
                Bimp.drr.add(path);
                Bimp.num += 1;
                Bitmap bm = null;
                try {
                    bm = Bimp.revitionImageSize(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bimp.bmp.add(bm);
                SelectImageItem mSelectImageItem = new SelectImageItem("", "", "");
                Bimp.drr_or.add(mSelectImageItem);
                adapter.update();
            }
        } else if (requestCode == 999) {
            if (data != null) {
                ContentResolver reContentResolverol = getActivity().getContentResolver();
                Uri contactData = data.getData();
                @SuppressWarnings("deprecation")
                Cursor cursor = getActivity().managedQuery(contactData, null, null, null, null);
                cursor.moveToFirst();     // 获取联系人的姓名
                try {
                    String username = cursor  .getString(cursor .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    // 获取用户名
                    String contactId = cursor.getString(cursor .getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor phone = reContentResolverol.query(  ContactsContract.CommonDataKinds.Phone.CONTENT_URI  , null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID  + " = " + contactId, null, null);
                    while (phone.moveToNext()) {
                        // 获取联系人号码
                        String usernumber = phone .getString(phone .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        // text.setText(usernumber+" ("+username+")");
                        addsinglememberMobileEt.setText(usernumber); // 显示返回的号码
                        addsinglememberNameEt.setText(username); // 显示返回的联系人姓名
                    }
                } catch (Exception e) {//此处为了防止通讯录提示是否点击禁止而抛出异常
                    addsinglememberNameEt.setText(""); //设置空
                    addsinglememberMobileEt.setText("");
                }
            }
        }
    }

    //裁减照片
    private void cropImageUri(Uri uri, int outputX, int outputY, int requestCode) {
        if (uri == null) {
            return;
        }
        LogUtils.e("outputX:" + outputX + ",outputY:" + outputY);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        imageUri = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void success(String imageUrl) {
        mAvatarUrl = imageUrl;
        LogUtils.e("success>>>>mAvatarUrl:" + mAvatarUrl);
        getDataManager().updateAvatar(mAvatarUrl, UpdateHeadImgModel.class, true);
    }

    @Override
    public void fail(String failInfo) {
        showToast(failInfo);
    }

    @Override
    public void onUploadDone(int responseCode, String message) {
        LogUtils.e(">>>>responseCode:" + responseCode + ",message:" + message);
        if (message.isEmpty()) {
            showToast("新增会员失败");
            return;
        }
        GoodsUpLoadImgMpdel mGoodsUpLoadImgMpdel = new GoodsUpLoadImgMpdel();
        mGoodsUpLoadImgMpdel = new Gson().fromJson(message, GoodsUpLoadImgMpdel.class);
        for (int i = 0; i < mGoodsUpLoadImgMpdel.getBody().size(); i++) {
            if (i == 0) {
                memberImage = mGoodsUpLoadImgMpdel.getBody().get(i).getUrl();
            }
            listImg.add(mGoodsUpLoadImgMpdel.getBody().get(i).getUrl());
        }

        getDataManager().createMember(birthday, mAvatarUrl, sex, remark, weixin, address, name,
                qq, mobile, type, listImg, CreateMemberModel.class, true);
    }

    @Override
    public void onUploadProcess(int uploadSize) {

    }

    @Override
    public void initUpload(int fileSize) {

    }

    @Override
    public void onUploadFail(String e) {
        showToast(e);
        cancelLoading();
    }

    private WheelView mTypeWheel_year;
    private WheelView mTypeWheel_month;
    private WheelView mTypeWheel_day;
    private BirthdayAdapter mShopTypeAdapterYear;
    private BirthdayAdapter mShopTypeAdapterMonth;
    private BirthdayAdapter mShopTypeAdapterDay;
    private List<Integer> yearList = new ArrayList<>();
    private List<Integer> monthList = new ArrayList<>();
    private List<Integer> dayList = new ArrayList<>();
    private int[] num = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private String y, m, d;

    public void showTypePopupWindow(View view) {

        yearList.clear();
        monthList.clear();
        dayList.clear();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        year -= 40;
        for (int i = 0; i <= 40; i++) {
            if (i == 40) {
                y = year + "";
            }
            yearList.add(year);
            year += 1;
        }

        for (int j = 1; j <= 12; j++) {
            monthList.add(j);
            if (j == 1) {
                m = j + "";
            }
        }

        for (int day = 1; day <= 31; day++) {
            dayList.add(day);
            if (day == 1) {
                d = day + "";
            }
        }

        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_select_birthday, null);

        final PopupWindow mSelectTypePop = new PopupWindow(contentView,
                RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT, true);

        mTypeWheel_year = (WheelView) contentView.findViewById(R.id.popwin_select_year);
        mTypeWheel_month = (WheelView) contentView.findViewById(R.id.popwin_select_month);
        mTypeWheel_day = (WheelView) contentView.findViewById(R.id.popwin_select_day);

        TextView pop_cancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        TextView pop_ok = (TextView) contentView.findViewById(R.id.tv_ok);
        final TextView tv_now = (TextView) contentView.findViewById(R.id.tv_now);
        tv_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addsinglememberBirthdayTv.setText(MyTools.getLacalDate("yyyy-MM-dd"));
                if (mSelectTypePop != null) {
                    toNow(false);
                }
            }
        });

        mShopTypeAdapterYear = new BirthdayAdapter(getActivity(), mTypeWheel_year, yearList, 0, 0, 0);
        mShopTypeAdapterMonth = new BirthdayAdapter(getActivity(), mTypeWheel_month, monthList, 0, 0, 0);
        mShopTypeAdapterDay = new BirthdayAdapter(getActivity(), mTypeWheel_day, dayList, 0, 0, 0);

        mTypeWheel_year.setVisibleItems(5);
        mTypeWheel_month.setVisibleItems(5);
        mTypeWheel_day.setVisibleItems(5);

        mTypeWheel_year.setViewAdapter(mShopTypeAdapterYear);
        mTypeWheel_month.setViewAdapter(mShopTypeAdapterMonth);
        mTypeWheel_day.setViewAdapter(mShopTypeAdapterDay);

        mTypeWheel_year.setCurrentItem(0);
        mTypeWheel_month.setCurrentItem(0);
        mTypeWheel_day.setCurrentItem(0);

        mTypeWheel_year.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                mTypeWheel_year.setViewAdapter(mShopTypeAdapterYear);
                displayTime(tv_now, (String) mShopTypeAdapterYear.getItemText(wheel.getCurrentItem()), "", "");
                dayList.clear();
                for (int day = 1; day <= num[Integer.parseInt(m) - 1]; day++) {
                    dayList.add(day);
                }
                if (Integer.parseInt(y) % 4 == 0 && Integer.parseInt(m) == 2) {
                    dayList.add(29);
                }
                mShopTypeAdapterDay = new BirthdayAdapter(getActivity(), mTypeWheel_day, dayList, 0, 0, 0);
                mTypeWheel_day.setViewAdapter(mShopTypeAdapterDay);
            }
        });
        mTypeWheel_month.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                mTypeWheel_month.setViewAdapter(mShopTypeAdapterMonth);
                displayTime(tv_now, "", (String) mShopTypeAdapterMonth.getItemText(wheel.getCurrentItem()), "");
                dayList.clear();
                for (int day = 1; day <= num[wheel.getCurrentItem()]; day++) {
                    dayList.add(day);
                }
                if (Integer.parseInt(y) % 4 == 0 && Integer.parseInt(m) == 2) {
                    dayList.add(29);
                }
                mShopTypeAdapterDay = new BirthdayAdapter(getActivity(), mTypeWheel_day, dayList, 0, 0, 0);
                mTypeWheel_day.setViewAdapter(mShopTypeAdapterDay);
                mTypeWheel_day.setCurrentItem(dayList.size());
            }
        });


        mTypeWheel_day.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                mTypeWheel_day.setViewAdapter(mShopTypeAdapterDay);
                displayTime(tv_now, "", "", (String) mShopTypeAdapterDay.getItemText(wheel.getCurrentItem()));
            }
        });

        OnWheelScrollListener mOnWheelScrollListener = new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
            }
        };

        mTypeWheel_year.addScrollingListener(mOnWheelScrollListener);
        mTypeWheel_month.addScrollingListener(mOnWheelScrollListener);
        mTypeWheel_day.addScrollingListener(mOnWheelScrollListener);

        pop_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectTypePop != null) {
                    mSelectTypePop.dismiss();
                }
            }
        });
        pop_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectTypePop != null) {
                    addsinglememberBirthdayTv.setText(MyTools.transformationToStandard(y + "-" + m + "-" + d, "yyyy-MM-dd"));
                    mSelectTypePop.dismiss();
                }
            }
        });

        mSelectTypePop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);
        mSelectTypePop.setTouchable(true);
        mSelectTypePop.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                return false;
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        mSelectTypePop.setBackgroundDrawable(getResources().getDrawable(R.drawable.pop_bg));
        // 设置好参数之后再show
        mSelectTypePop.showAtLocation(view, Gravity.BOTTOM, 0, 0);

        toNow(true);
    }

    private void toNow(boolean b) {
        String str = addsinglememberBirthdayTv.getText().toString();
        for (int i=0;i<yearList.size();i++) {
            if (str.substring(0,4).equals(yearList.get(i)+"")) {
                if (b) {
                    mTypeWheel_year.setCurrentItem(i);
                } else {
                    mTypeWheel_year.setCurrentItem(i, true);
                }
            }
        }
        for (int i=0;i<monthList.size();i++) {
            if (Integer.parseInt(str.substring(5,7))==monthList.get(i)) {
                if (b) {
                    mTypeWheel_month.setCurrentItem(i);
                } else {
                    mTypeWheel_month.setCurrentItem(i, true);
                }
            }
        }
        for (int i=0;i<dayList.size();i++) {
            if (Integer.parseInt(str.substring(8,10))==dayList.get(i)) {
                if (b) {
                    mTypeWheel_day.setCurrentItem(i);
                } else {
                    mTypeWheel_day.setCurrentItem(i, true);
                }
            }
        }
    }

    private void displayTime(TextView tv_now, String year, String month, String day) {
        if (year != "") {
            y = year;
        }
        if (month != "") {
            m = month;
        }
        if (day != "") {
            d = day;
        }
    }
}
