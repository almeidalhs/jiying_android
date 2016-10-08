package com.atman.jishang.ui.member;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.interfaces.AdapterInterface;
import com.atman.jishang.net.model.CreateMemberListModel;
import com.atman.jishang.ui.base.BaiYeBaseFragment;
import com.atman.jishang.widget.telephonebook.CharacterParser;
import com.atman.jishang.widget.telephonebook.ContactBean;
import com.atman.jishang.widget.telephonebook.GroupMemberBean;
import com.atman.jishang.widget.telephonebook.PinyinComparator;
import com.atman.jishang.widget.telephonebook.SideBar;
import com.atman.jishang.widget.telephonebook.SortGroupMemberAdapter;
import com.corelib.util.LogUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述 批量添加会员
 * 作者 tangbingliang
 * 时间 16/5/25 10:50
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AddBatchMemberFragment extends BaiYeBaseFragment implements AdapterInterface {

    @Bind(R.id.country_lvcountry)
    ListView countryLvcountry;
    @Bind(R.id.title_layout_no_friends)
    TextView titleLayoutNoFriends;
    @Bind(R.id.title_layout_catalog)
    TextView titleLayoutCatalog;
    @Bind(R.id.title_layout)
    LinearLayout titleLayout;
    @Bind(R.id.dialog)
    TextView dialog;
    @Bind(value = R.id.sidrbar)
    SideBar sidrbar;
    @Bind(R.id.addbatch_selectall_tv)
    TextView addbatchSelectallTv;

    /**
     * 上次第一个可见元素，用于滚动时记录标识。
     */
    private int lastFirstVisibleItem = -1;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<GroupMemberBean> SourceDateList;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    private SortGroupMemberAdapter adapter;
    private Map<Integer, ContactBean> contactIdMap = null;
    private List<ContactBean> list;
    private List<GroupMemberBean> mSelectList = new ArrayList<>();
    private AsyncQueryHandler asyncQueryHandler; // 异步查询数据库类对象

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addbatchmember, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        // 实例化
        asyncQueryHandler = new MyAsyncQueryHandler(getActivity().getContentResolver());
        initData();

    }

    /**
     * 初始化数据库查询参数
     */
    private void initData() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI; // 联系人Uri；
        // 查询的字段
        String[] projection = {ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.DATA1, "sort_key",
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.PHOTO_ID,
                ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY};
        // 按照sort_key升序查詢
        asyncQueryHandler.startQuery(0, null, uri, projection, null, null,
                "sort_key COLLATE LOCALIZED asc");

    }

    private void initViews() {
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        sidrbar.setTextView(dialog);

        // 设置右侧触摸监听
        sidrbar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    countryLvcountry.setSelection(position);
                }

            }
        });

        countryLvcountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 这里要利用adapter.getItem(position)来获取当前position所对应的对象
//                showToast(((GroupMemberBean) adapter.getItem(position)).getName());
                selectItem(position);
            }
        });

        SourceDateList = filledData(list);
        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);

        adapter = new SortGroupMemberAdapter(getActivity(), SourceDateList, this);
        countryLvcountry.setAdapter(adapter);
        countryLvcountry.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                int section = getSectionForPosition(firstVisibleItem);
                int nextSection = getSectionForPosition(firstVisibleItem + 1);
                int nextSecPosition = getPositionForSection(+nextSection);
                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                            .getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    titleLayoutCatalog.setText(SourceDateList.get(
                            getPositionForSection(section)).getSortLetters());
                }
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = titleLayout.getHeight();
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                .getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });
    }

    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<GroupMemberBean> filledData(List<ContactBean> date) {
        List<GroupMemberBean> mSortList = new ArrayList<GroupMemberBean>();

        for (int i = 0; i < date.size(); i++) {
            GroupMemberBean sortModel = new GroupMemberBean();
            sortModel.setName(date.get(i).getDesplayName());
            sortModel.setMobile(date.get(i).getPhoneNum());
            sortModel.setSex(false);
            sortModel.setSelect(false);
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date.get(i).getDesplayName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onResponse(Object response) {
        super.onResponse(response);
        if (response instanceof CreateMemberListModel) {
            CreateMemberListModel mCreateMemberListModel = (CreateMemberListModel) response;
            if (mCreateMemberListModel.getResult().equals("1")) {
                showToast("批量导入成功");
                AddMemberActivity.isSuccess = true;
                Intent intent=new Intent();
                getActivity().setResult(getActivity().RESULT_OK, intent);
                getActivity().finish();
            } else {
                showToast("批量导入失败");
                AddMemberActivity.isSuccess = false;
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

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        try {
            return SourceDateList.get(position).getSortLetters().charAt(0);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < SourceDateList.size(); i++) {
            String sortStr = SourceDateList.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.item_telephone_select_iv:
                selectItem(position);
                break;
        }
    }

    private void selectItem(int position) {
        if (adapter.setSelectById(position)) {//全选
            addbatchSelectallTv.setText("非全选");
        } else {//非全选
            addbatchSelectallTv.setText("全选");
        }
    }


    @OnClick({R.id.addbatch_selectall_ll, R.id.addbatchmember_add_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addbatch_selectall_ll:
                if (adapter.isSelectedAll()) {
                    adapter.setSelected(false);
                    addbatchSelectallTv.setText("全选");
                } else {
                    adapter.setSelected(true);
                    addbatchSelectallTv.setText("非全选");
                }
                break;
            case R.id.addbatchmember_add_ll:
                LogUtils.e(">>>>>addbatchmember_add_ll");
                mSelectList = adapter.getSelectedList();
                if (mSelectList.size()==0) {
                    showToast("请选择要添加会员的小伙伴");
                    return;
                }
                getDataManager().addMemberList(new Gson().toJson(mSelectList), CreateMemberListModel.class, true);
                break;
        }
    }

    /**
     * @author Administrator
     */
    private class MyAsyncQueryHandler extends AsyncQueryHandler {

        public MyAsyncQueryHandler(ContentResolver cr) {
            super(cr);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                contactIdMap = new HashMap<Integer, ContactBean>();
                list = new ArrayList<ContactBean>();
                cursor.moveToFirst(); // 游标移动到第一项
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);
                    String name = cursor.getString(1);
                    String number = cursor.getString(2);
                    String sortKey = cursor.getString(3);
                    int contactId = cursor.getInt(4);
                    Long photoId = cursor.getLong(5);
                    String lookUpKey = cursor.getString(6);

                    if (contactIdMap.containsKey(contactId)) {
                        // 无操作
                    } else {
                        // 创建联系人对象
                        ContactBean contact = new ContactBean();
                        contact.setDesplayName(name);
                        contact.setPhoneNum(number);
                        contact.setSortKey(sortKey);
                        contact.setPhotoId(photoId);
                        contact.setLookUpKey(lookUpKey);
                        list.add(contact);

                        contactIdMap.put(contactId, contact);
                    }
                }
                if (list.size() > 0) {
                    initViews();
                }
            }

            super.onQueryComplete(token, cookie, cursor);
        }

    }
}
