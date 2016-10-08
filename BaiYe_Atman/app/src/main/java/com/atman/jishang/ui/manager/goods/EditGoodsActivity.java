package com.atman.jishang.ui.manager.goods;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.atman.jishang.R;
import com.atman.jishang.adapter.GoodsTypeAdapter;
import com.atman.jishang.adapter.GridEditAdapter;
import com.atman.jishang.interfaces.LimitSizeTextWatcher;
import com.atman.jishang.ui.base.SimpleTitleBarActivity;
import com.atman.jishang.interfaces.MyTextWatcher;
import com.atman.jishang.net.Urls;
import com.atman.jishang.net.model.EditGoodsModel;
import com.atman.jishang.net.model.GetStoreClassModel;
import com.atman.jishang.net.model.GoodsDetailInfoModel;
import com.atman.jishang.net.model.GoodsUpLoadImgMpdel;
import com.atman.jishang.ui.manager.classification.AddGoodsClassActivity;
import com.atman.jishang.utils.UiHelper;
import com.atman.jishang.widget.BottomDialog;
import com.atman.jishang.widget.WheelView.OnWheelChangedListener;
import com.atman.jishang.widget.WheelView.OnWheelScrollListener;
import com.atman.jishang.widget.WheelView.WheelView;
import com.choicepicture_library.ImageGridActivity;
import com.choicepicture_library.model.SelectImageItem;
import com.choicepicture_library.tools.Bimp;
import com.choicepicture_library.tools.UploadUtil;
import com.corelib.util.LogUtils;
import com.corelib.widget.MyCleanEditText;
import com.corelib.widget.MyGridView;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/28 13:26
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class EditGoodsActivity extends SimpleTitleBarActivity implements UploadUtil.OnUploadProcessListener {

    @Bind(R.id.add_goods_code_tx)
    MyCleanEditText addGoodsCodeTx;
    @Bind(R.id.add_goods_name_et)
    MyCleanEditText addGoodsNameEt;
    @Bind(R.id.add_goods_class_tx)
    TextView addGoodsClassTx;
    @Bind(R.id.add_goods_purprice_et)
    MyCleanEditText addGoodsPurpriceEt;
    @Bind(R.id.add_goods_price_et)
    MyCleanEditText addGoodsPriceEt;
    @Bind(R.id.add_goods_specifications_et)
    MyCleanEditText addGoodsSpecificationsEt;
    @Bind(R.id.add_goods_stock_et)
    MyCleanEditText addGoodsStockEt;
    @Bind(R.id.add_goods_describe_et)
    MyCleanEditText addGoodsDescribeEt;
    @Bind(R.id.noScrollgridview)
    MyGridView noScrollgridview;

    private Context mContext = EditGoodsActivity.this;
    private List<GetStoreClassModel.BodyEntity> list = new ArrayList<>();
    private GridEditAdapter adapter;
    private boolean isGoOn;
    private List<String> listImg = new ArrayList<>();
    private GoodsDetailInfoModel mGoodsDetailInfoModel;
    private GetStoreClassModel mGetStoreClassModel;

    private int goodsId;//商品id
    private String goodsSerial;//商品条码
    private String goodsName;//商品名
    private long stcId=-1;//分类id
    private String goodsImage;//商品列表图
    private String goodsSpec;//商品规格
    private String goodsDescription;//商品描述
    private String goodsStorePrice;//商品售价
    private String price;//商品进价
    private int storage;//商品库存

    private int mGoodsId;
    protected ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editgoods);
        ButterKnife.bind(this);
        Bimp.drr.clear();
        Bimp.drr_or.clear();
        Bimp.bmp.clear();
        imageLoader = ImageLoader.getInstance();
    }

    public static Intent buildIntent(Context context, int goodsId){
        Intent intent = new Intent(context, EditGoodsActivity.class);
        intent.putExtra("mGoodsId", goodsId);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter!=null) {
            noScrollgridview.setAdapter(adapter);
            adapter.update();
        }
        isGoOn = false;

    }

    @Override
    public void initWidget(View... v) {
        super.initWidget(v);
        setToolbarTitle("编辑商品");

        addGoodsSpecificationsEt.addTextChangedListener(new LimitSizeTextWatcher(mContext,addGoodsSpecificationsEt,19,"输入的内容不能超过20个字"));
        addGoodsDescribeEt.addTextChangedListener(new LimitSizeTextWatcher(mContext,addGoodsDescribeEt,255,"输入的内容不能超过256个字"));
        addGoodsStockEt.addTextChangedListener(new LimitSizeTextWatcher(mContext,addGoodsStockEt,9,"输入的内容不能超过10个数字"));

        addGoodsPurpriceEt.addTextChangedListener(new MyTextWatcher(mContext, addGoodsPurpriceEt,true,7,"您输入的价格过高，请重新输入"));
        addGoodsPriceEt.addTextChangedListener(new MyTextWatcher(mContext, addGoodsPriceEt,true,7,"您输入的价格过高，请重新输入"));
        addGoodsNameEt.addTextChangedListener(new LimitSizeTextWatcher(mContext,addGoodsNameEt,19,"输入的内容不能超过20个字"));
    }

    private void initGridView() {
        noScrollgridview = (MyGridView) findViewById(R.id.noScrollgridview);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));//选中的时候为透明色

        adapter = new GridEditAdapter(this, imageLoader);//初始化适配器
        adapter.update();

        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg2 == Bimp.bmp.size()) {
                    if (getCurrentFocus() != null) {
                        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus()
                                .getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    showAddrPopupWindow(arg1);
                } else {
                    Intent intent = new Intent(mContext,PhotoEditActivity.class);
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });
    }

    private Uri imageUri;//The Uri to store the big bitmap
    private final int CHOOSE_BIG_PICTURE = 1001;
    private final int TAKE_BIG_PICTURE = 1002;
    private final int CROP_BIG_PICTURE = 1003;
    private void showAddrPopupWindow(View view) {
        BottomDialog.Builder builder = new BottomDialog.Builder(mContext);
        builder.setTitle(Html.fromHtml("<font color=\"#f9464a\">选择商品图片</font>"));
        builder.setItems(new String[]{"拍照", "从相册中获取"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {//拍照
                    path = UiHelper.photo(mContext, path, TAKE_PICTURE);
                } else {//选择照片
                    startActivityForResult(new Intent(mContext, ImageGridActivity.class), CHOOSE_BIG_PICTURE);
                }
            }
        });
        builder.setNeutralButton("取消", null);
        builder.show();
    }

    private static final int TAKE_PICTURE = 0x000000;
    private String path = "";

    @Override
    public void initIntentAndMemData() {
        super.initIntentAndMemData();
    }

    @Override
    public void doInitBaseHttp() {
        super.doInitBaseHttp();
        getDataManager().getGoodsClass(GetStoreClassModel.class, true);
        mGoodsId = getIntent().getIntExtra("mGoodsId", 0);
    }

    @Override
    public void onResponse(Object response, String data) {
        if (response instanceof GetStoreClassModel) {
            mGetStoreClassModel = (GetStoreClassModel) response;
            if (mGetStoreClassModel.getResult().equals("1")) {
                list.clear();
                list.addAll(mGetStoreClassModel.getBody());
                getDataManager().getGoodsByClassId(mGoodsId, GoodsDetailInfoModel.class, false);
            } else {
                showToast("分类获取失败");
            }
        } else if (response instanceof EditGoodsModel) {
            super.onResponse(response,data);
            EditGoodsModel mCreateGoodsModel = (EditGoodsModel) response;
            if (mCreateGoodsModel.getResult().equals("1")) {
                showToast("商品修改成功");
                clearPrams();
                if (!isGoOn) {
                    finish();
                }
            } else {
                showToast(mCreateGoodsModel.getBody().getMessage());
            }
        } else if (response instanceof GoodsDetailInfoModel) {
            super.onResponse(response, data);
            mGoodsDetailInfoModel = (GoodsDetailInfoModel) response;
            if (mGoodsDetailInfoModel.getResult().equals("1")) {
                updateUI();
            } else {
                showToast(mGoodsDetailInfoModel.getBody().getMessage());
            }
        }
    }

    private void updateUI() {
        initGridView();
        addGoodsCodeTx.setText(mGoodsDetailInfoModel.getBody().getGoodsSerial());
        addGoodsNameEt.setText(mGoodsDetailInfoModel.getBody().getGoodsName());
        for (int i=0;i<mGetStoreClassModel.getBody().size();i++) {
            if (mGoodsDetailInfoModel.getBody().getStcId() == mGetStoreClassModel.getBody().get(i).getId()) {
                stcId = mGetStoreClassModel.getBody().get(i).getId();
                mScName = mGetStoreClassModel.getBody().get(i).getStcName();
                addGoodsClassTx.setText(mScName);
            }
        }
        DecimalFormat df = new DecimalFormat("##0.00");
        addGoodsPurpriceEt.setText(df.format(Float.parseFloat(mGoodsDetailInfoModel.getBody().getGoodsStorePrice()))+"");
        addGoodsPriceEt.setText(df.format(mGoodsDetailInfoModel.getBody().getPrice())+"");
        addGoodsSpecificationsEt.setText(mGoodsDetailInfoModel.getBody().getGoodsSpec());
        addGoodsStockEt.setText(mGoodsDetailInfoModel.getBody().getStorage()+"");
        addGoodsDescribeEt.setText(mGoodsDetailInfoModel.getBody().getGoodsDescription());
        for (int i=0;i<mGoodsDetailInfoModel.getBody().getGoodsImageMoreList().size();i++) {
            Bimp.num += 1;
            Bimp.bmp.add(null);
            Bimp.drr.add(mGoodsDetailInfoModel.getBody().getGoodsImageMoreList().get(i));
            SelectImageItem mSelectImageItem = new SelectImageItem("","","");
            Bimp.drr_or.add(mSelectImageItem);
        }
    }

    private void clearPrams() {
        goodsSerial = "";
        goodsName = "";
        stcId=-1;//分类id
        goodsImage = "";//商品列表图
        listImg.clear();
        goodsSpec = "";//商品规格
        goodsDescription = "";//商品描述
        goodsStorePrice = "0";//商品售价
        price = "0";//商品进价
        storage = 0;//商品库存
        goodsId = 0;

        addGoodsCodeTx.setText("");
        addGoodsNameEt.setText("");
        addGoodsClassTx.setText("");
        addGoodsPurpriceEt.setText("");
        addGoodsPriceEt.setText("");
        addGoodsSpecificationsEt.setText("");
        addGoodsStockEt.setText("");
        addGoodsDescribeEt.setText("");

        Bimp.num = 0;
        Bimp.drr.clear();
        Bimp.drr_or.clear();
        Bimp.bmp.clear();

        if (adapter!=null) {
            adapter.update();
        }
    }

    @Override
    protected void onDestroy() {
        Bimp.num = 0;
        Bimp.drr.clear();
        Bimp.drr_or.clear();
        Bimp.bmp.clear();
        super.onDestroy();
    }

    @OnClick({R.id.add_goods_code_bt, R.id.add_goods_class_rl,
            R.id.add_goods_finish_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_goods_code_bt:
                new IntentIntegrator(this).setCaptureActivity(ScanCodeActivity.class).initiateScan();
                break;
            case R.id.add_goods_class_rl:
                if (list.size() == 0) {
                    showToast("您还没有商品分类，请先添加商品分类");
                } else {
                    showTypePopupWindow(view, list);
                }
                break;
            case R.id.add_goods_finish_ll:
                if (initPrams()) {
                    return;
                }
                isGoOn = false;
                forUpLoadPic();
                break;
        }
    }

    private boolean initPrams() {
        goodsId = mGoodsDetailInfoModel.getBody().getId();
        goodsName = addGoodsNameEt.getText().toString().trim();
        if (goodsName.isEmpty()) {
            showToast("请输入商品名称");
            return true;
        }
        goodsSpec = addGoodsSpecificationsEt.getText().toString().trim();
        goodsDescription = addGoodsDescribeEt.getText().toString().trim();
        if (stcId==-1) {
            showToast("请选择商品所属的分类");
            return true;
        }
        if (addGoodsPriceEt.getText().toString().trim().isEmpty()) {
            showToast("请输入商品售价");
            return true;
        } else {
            if (Float.parseFloat(addGoodsPriceEt.getText().toString().trim())<=0){
                showToast("商品售价要大于0");
                return true;
            }
            price = addGoodsPriceEt.getText().toString().trim();
        }

//        if (!addGoodsPurpriceEt.getText().toString().trim().isEmpty()
//                && Float.parseFloat(addGoodsPurpriceEt.getText().toString().trim())<=0) {
//            showToast("商品进价要大于0");
//            return true;
//        }
        if (addGoodsPurpriceEt.getText().toString().trim().isEmpty()) {
            goodsStorePrice = "0";
        } else {
            goodsStorePrice = addGoodsPurpriceEt.getText().toString().trim();
        }
        if (addGoodsStockEt.getText().toString().trim().isEmpty()) {
            storage = 0;
        } else {
            storage = Integer.parseInt(addGoodsStockEt.getText().toString().trim());
        }
        if (Bimp.drr.size()==0) {
            showToast("请选择商品图片");
            return true;
        }
        return false;
    }

    private void forUpLoadPic() {
        showLoading();
        listImg.clear();
        List<String> filePath = new ArrayList<>();
        for (int i=0;i<Bimp.drr.size();i++) {
            if (!Bimp.drr.get(i).startsWith("http")) {
//                String Str = Bimp.drr.get(i).substring(Bimp.drr.get(i).lastIndexOf("/") + 1,Bimp.drr.get(i).lastIndexOf("."));
//                String pathImg = FileUtils.SDPATH+Str+".JPEG";
                filePath.add(Bimp.drr.get(i));
            } else {
                String Str = Bimp.drr.get(i).split("by/")[1];
                LogUtils.e("Str:"+Str);
                if (i==0) {
                    goodsImage = Str;
                }
                listImg.add(Str);
            }
        }
        if (filePath.size()>0) {
            LogUtils.e("filePath.size():"+filePath.size());
            UploadUtil uploadUtil = UploadUtil.getInstance();;
            uploadUtil.setOnUploadProcessListener(EditGoodsActivity.this);  //设置监听器监听上传状态
            Map<String, String> params = new HashMap<String, String>();
            uploadUtil.uploadFile(filePath, Urls.RWH_HOST_UP + Urls.UP_GOODSIMG, params);
        } else {
            getDataManager().updateGoods(goodsId, goodsSerial, goodsName, stcId, goodsImage, listImg,
                    goodsSpec, goodsDescription, goodsStorePrice, price, storage, EditGoodsModel.class, true);
        }
    }

    private WheelView mTypeWheel;
    private GoodsTypeAdapter mShopTypeAdapter;
    private String mScName;
    private int selcteId = 0;
    private final int toAddClass = 1500;
    public void showTypePopupWindow(View view, final List<GetStoreClassModel.BodyEntity> mList) {

        // 一个自定义的布局，作为显示的内容
        final View contentView = LayoutInflater.from(mContext).inflate(R.layout.wheel_select_goods_type_view, null);

        final PopupWindow mSelectTypePop = new PopupWindow(contentView,
                RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT, true);

        mTypeWheel = (WheelView) contentView.findViewById(R.id.popwin_select_wv);
        Button pop_cancel = (Button) contentView.findViewById(R.id.pop_cancel);
        Button pop_ok = (Button) contentView.findViewById(R.id.pop_ok);
        TextView pop_addgoodsclass = (TextView) contentView.findViewById(R.id.pop_addgoodsclass);
        mShopTypeAdapter = new GoodsTypeAdapter(mContext, mTypeWheel, mList, 0, 0, 0);
        mTypeWheel.setVisibleItems(5);
        mTypeWheel.setViewAdapter(mShopTypeAdapter);
        mTypeWheel.setCurrentItem(0);
        mTypeWheel.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                mTypeWheel.setViewAdapter(mShopTypeAdapter);
            }
        });
        mTypeWheel.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                mScName = (String) mShopTypeAdapter.getItemText(wheel.getCurrentItem());
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).getStcName().equals(mScName)) {
                        stcId = mList.get(i).getId();
                        LogUtils.e("mScName:"+mScName);
                        addGoodsClassTx.setText(mScName);
                        selcteId = i;
                        break;
                    }
                }
            }
        });
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
                    mSelectTypePop.dismiss();
                }
                initPopwindowData(mList.get(selcteId));
            }
        });
        pop_addgoodsclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectTypePop != null) {
                    mSelectTypePop.dismiss();
                }
                startActivityForResult(new Intent(mContext, AddGoodsClassActivity.class), toAddClass);
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
        if (mList != null && mList.size() > 0) {
            selcteId = 0;
            if (addGoodsClassTx.getText().toString().isEmpty()) {
                initPopwindowData(mList.get(selcteId));
            }
            mSelectTypePop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }

    }

    private void initPopwindowData(GetStoreClassModel.BodyEntity mList) {
        mScName = mList.getStcName();
        stcId = mList.getId();
        LogUtils.e("initPopwindowData:mScName:"+mScName);
        addGoodsClassTx.setText(mScName);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
//                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                goodsSerial = "" + result.getContents();
                addGoodsCodeTx.setText(goodsSerial);
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }

        switch (requestCode) {
            case TAKE_PICTURE:
                if (Bimp.drr.size() < Bimp.max && resultCode == -1) {
                    Bimp.drr.add(path);
                    Bimp.num += 1;
                    Bimp.bmp.add(null);
                    SelectImageItem mSelectImageItem = new SelectImageItem("","","");
                    Bimp.drr_or.add(mSelectImageItem);
                }
                break;
            case toAddClass:
                getDataManager().getGoodsClass(GetStoreClassModel.class, true);
                break;
        }
    }

    @Override
    public void onUploadDone(int responseCode, String message) {
        LogUtils.e(">>>>responseCode:"+responseCode+",message:"+message);
        GoodsUpLoadImgMpdel mGoodsUpLoadImgMpdel = new GoodsUpLoadImgMpdel();
        mGoodsUpLoadImgMpdel = new Gson().fromJson(message, GoodsUpLoadImgMpdel.class);
        for(int i=0;i<mGoodsUpLoadImgMpdel.getBody().size();i++){
            if (i==0 && listImg.size() == 0) {
                goodsImage = mGoodsUpLoadImgMpdel.getBody().get(i).getUrl();
            }
            listImg.add(mGoodsUpLoadImgMpdel.getBody().get(i).getUrl());
        }
        getDataManager().updateGoods(goodsId, goodsSerial, goodsName, stcId, goodsImage, listImg,
                goodsSpec, goodsDescription, goodsStorePrice, price, storage, EditGoodsModel.class, true);
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
}
