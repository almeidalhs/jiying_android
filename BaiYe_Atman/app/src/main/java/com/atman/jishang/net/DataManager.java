package com.atman.jishang.net;

import android.widget.ListView;

import com.atman.jishang.net.model.AddFullCutNextModel;
import com.atman.jishang.net.model.AddRecordCouponListModel;
import com.atman.jishang.net.model.AddRecordFullCutListModel;
import com.atman.jishang.net.model.AddRecordParamsModel;
import com.atman.jishang.net.model.MemberFilterModel;
import com.atman.jishang.ui.base.BaiYeBaseApplication;
import com.corelib.net.IHttpInterface;
import com.corelib.net.IVolleyListener;
import com.corelib.util.DeviceUtils;
import com.corelib.util.PackageInfoUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by loyee on 15-12-18.
 */
public class DataManager {
    private IHttpInterface mHttpRequest = null;
    private IVolleyListener mListener = null;

    public DataManager(IHttpInterface httpRequest, IVolleyListener listener) {
        mHttpRequest = httpRequest;
        mListener = listener;
    }

    private void request(String api, Map<String, Object> p, Class clazz) {
        mHttpRequest.sendHttpRequest(getCmpApi(api), getBaseHashMap(p, api), clazz, mListener);
    }

    private void request(String api, Map<String, Object> p, Class clazz, boolean showloding) {
        mHttpRequest.sendHttpRequest(getCmpApi(api), getBaseHashMap(p, api), clazz, showloding, mListener);
    }

    private void request(String api, String p, Class clazz, boolean showloding) {
        mHttpRequest.sendHttpRequest(getCmpApi(api), p, clazz, showloding, mListener);
    }

    private void requestnot(String api, Map<String, Object> p, Class clazz, boolean showloding) {
        mHttpRequest.sendHttpRequest(getCmpApi(api), p, clazz, showloding, mListener);
    }

    private void request(String api, Class clazz, boolean showloding) {
        mHttpRequest.sendHttpGetRequest(getCmpApi(api), clazz, showloding, mListener);
    }

    private void requestDelete(String api, Class clazz, boolean showloding) {
        mHttpRequest.sendHttpDeleteRequest(getCmpApi(api), clazz, showloding, mListener);
    }

    private void requestPut(String api, Class clazz, boolean showloding) {
        mHttpRequest.sendHttpPutRequest(getCmpApi(api), clazz, showloding, mListener);
    }

    /**
     * 获取添加默认参数的HashMap
     */
    private Map<String, Object> getBaseHashMap(Map<String, Object> paramsMap, String api) {
        if (BaiYeBaseApplication.mDeviceToken != null && BaiYeBaseApplication.mDeviceToken.isEmpty()) {
            paramsMap.put("deviceToken", DeviceUtils.getDeviceId());
        } else {
            paramsMap.put("deviceToken", BaiYeBaseApplication.mDeviceToken);
        }
        paramsMap.put("channel", BaiYeBaseApplication.mChannel);
        paramsMap.put("version", PackageInfoUtil.getVersionName());
        paramsMap.put("deveicType", "Androidzhon");
        paramsMap.put("platform", "Android");
        paramsMap.put("language", "en");
        paramsMap.put("idfa", "i_am_android");
        paramsMap.put("isTestToken", false);
        return paramsMap;
    }

    /**
     * 获取完整Api
     */
    private String getCmpApi(String api) {
        return Urls.RWH_HOST + api;
    }


    /**
     *  登录接口
     *
     * @param userName   账号
     * @param password     登录密码
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void login(String userName, String password, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("userName", userName);
        p.put("password", password);
        request(Urls.LOGIN, p, clazz, showLoading);
    }

    /**
     *  发送验证码接口
     *
     * @param phonenumber 手机号
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void seedMessage(String phonenumber, Class clazz, boolean showLoading) {
        request(Urls.SEED_MEESAGE+phonenumber+Urls.SEED_MEESAGE_AFTER, clazz, showLoading);
    }

    /**
     *  验证码校验
     *
     * @param phonenumber 手机号
     * @param code        验证码
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void codeCheck(String phonenumber, String code, Class clazz, boolean showLoading) {
        requestPut(Urls.CODE_CHECK + phonenumber + "/" + code, clazz, showLoading);
    }

    /**
     *  注册接口
     *
     * @param userName   账号
     * @param password     登录密码
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void register(String userName, String password, String avatarUrl, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("userName", userName);
        p.put("password", password);
        p.put("nickName", userName);
        p.put("avatarUrl", avatarUrl);
        p.put("gender", "0");
        p.put("userLabel", "label1 label2");
        request(Urls.REGISTER, p, clazz, showLoading);
    }

    /**
     *  上传头像
     *
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void upHeadImg( Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        request(Urls.UP_HEADIMG, p, clazz, showLoading);
    }

    /**
     *  发送验证码接口--忘记密码
     *
     * @param phonenumber 手机号
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void seedMessageForgotPwd(String phonenumber, Class clazz, boolean showLoading) {
        request(Urls.SEED_MEESAGE+phonenumber+Urls.SEED_MEESAGE_AFTER_FORGOT, clazz, showLoading);
    }

    /**
     *  重新设置密码
     *
     * @param phonenumber 手机号
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void seedMessageForgotPwd(String phonenumber, String password , Class clazz, boolean showLoading) {
        requestPut(Urls.FORGOT+phonenumber+"/"+password, clazz, showLoading);
    }

    /**
     *  获取商铺信息
     *
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getShopInformation(Class clazz, boolean showLoading) {
        request(Urls.SHOP_INFORMATION, clazz, showLoading);
    }

    /**
     *  获取行业类型
     *
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getIndustryType(Class clazz, boolean showLoading) {
        request(Urls.GET_INDSTRY_TRPE, clazz, showLoading);
    }

    /**
     *  创建店铺
     *
     * @param storeName     店铺名
     * @param scId     行业id
     * @param storeBanner     店铺背景
     * @param storeAddress     店铺地址
     * @param storeTel     店铺电话
     * @param description     店铺简介
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void createShop(String storeName, int scId, String storeBanner
            ,String storeAddress,String storeTel, String description,Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("storeName", storeName);
        p.put("scId", scId);
        p.put("storeBanner", storeBanner);
        p.put("storeAddress", storeAddress);
        p.put("storeTel", storeTel);
        p.put("description", description);
        requestnot(Urls.CREATE_SHOP, p, clazz, showLoading);
    }

    /**
     *  更新店铺信息
     *
     * @param id   id
     * @param storeName     店铺名
     * @param scId     行业id
     * @param storeBanner     店铺背景
     * @param storeAddress     店铺地址
     * @param storeTel     店铺电话
     * @param description     店铺简介
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void updateShop(int id, String storeName, int scId, String storeBanner
            ,String storeAddress,String storeTel, String description,Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("id", id);
        p.put("storeName", storeName);
        p.put("scId", scId);
        p.put("storeBanner", storeBanner);
        p.put("storeAddress", storeAddress);
        p.put("storeTel", storeTel);
        p.put("description", description);
        requestnot(Urls.UPDATE_SHOP, p, clazz, showLoading);
    }

    /**
     *  修改密码接口
     *
     * @param oldPW       旧密码
     * @param newPW       新密码
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void resetPassWord(String oldPW, String newPW, Class clazz, boolean showLoading) {
        requestPut(Urls.RESET_PASSWORD + oldPW + "/" + newPW, clazz, showLoading);
    }

    /**
     *  更新店铺信息
     *
     * @param content     反馈意见内容
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void feedback(String content,Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("content", content);
        requestnot(Urls.FEEDBACK, p, clazz, showLoading);
    }

    /**
     *  更新头像
     *
     * @param avatar     头像路径
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void updateAvatar(String avatar,Class clazz, boolean showLoading) {
        request(Urls.UPDATA_HEADIMG, avatar, clazz, showLoading);
    }

    /**
     *  获取首页gridview数据
     *
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getHomeGridView(Class clazz, boolean showLoading) {
        request(Urls.GET_HOME_GRIDVIEW_DATA, clazz, showLoading);
    }

    /**
     *  上传改变后分类的顺序
     *
     * @param json        新的顺序json
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void updateListOrder(String json,Class clazz, boolean showLoading) {
        request(Urls.UPDATE_LIST_ORDER, json, clazz, showLoading);
    }

    /**
     *  获取首页广告数据
     *
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getHomeAd(Class clazz, boolean showLoading) {
        request(Urls.GET_HOME_AD_DATA, clazz, showLoading);
    }

    /**
     *  获取店铺商品分类
     *
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getGoodsClasses(Class clazz, boolean showLoading) {
        request(Urls.GET_GOODSCLASS, clazz, showLoading);
    }

    /**
     *  获取店铺某分类下面的商品
     *
     * @param ClassId     分类id
     * @param type        商品类别
     * @param page        页数
     * @param count       每页个数
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getGoodsByClassId(String ClassId, int type ,int page, int count, Class clazz, boolean showLoading) {
        request(Urls.GET_GOODS_BY_CLASSID+ClassId+"/"+type+"/"+page+"/"+count, clazz, showLoading);
    }

    /**
     *  获取商品详情
     *
     * @param GoodsId     分类id
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getGoodsByClassId(int GoodsId, Class clazz, boolean showLoading) {
        request(Urls.GET_GOODS_DETAILS + GoodsId, clazz, showLoading);
    }

    /**
     *  获取商品分类
     *
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getGoodsClass(Class clazz, boolean showLoading) {
        request(Urls.GET_STORECLASS, clazz, showLoading);
    }

    /**
     *  创建商品分类
     *
     * @param stcName     新建分类名
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void createGoodsClass(String stcName,Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("stcName", stcName);
        request(Urls.CREATE_STORECLASS, p, clazz, showLoading);
    }

    /**
     *  删除商品分类
     *
     * @param storeId     商铺id
     * @param stcId       分类id
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void deleteGoodsClass(int storeId, long stcId, Class clazz, boolean showLoading) {
        requestDelete(Urls.DELETE_STORECLASS + storeId +"/" + stcId, clazz, showLoading);
    }

    /**
     *  修改商铺分类名
     *
     * @param id          分类id
     * @param stcName     分类名
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void editClassName(long id, String stcName, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("id", id);
        p.put("stcName", stcName);
        requestnot(Urls.EDIT_STORECLASS, p, clazz, showLoading);
    }

    /**
     *  上传改变后分类的顺序
     *
     * @param json        新的顺序json
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void editClassOrder(String json,Class clazz, boolean showLoading) {
        request(Urls.EDIT_STORECLASS_ORDER, json, clazz, showLoading);
    }

    /**
     *  批量下架
     *
     * @param json        下架商品的json
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void batchUnShelve(String json,Class clazz, boolean showLoading) {
        request(Urls.BATCH_UNSHELVE, json, clazz, showLoading);
    }

    /**
     *  批量上架
     *
     * @param json        上架商品的json
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void batchShelve(String json,Class clazz, boolean showLoading) {
        request(Urls.BATCH_SHELVE, json, clazz, showLoading);
    }

    /**
     *  修改分类
     *
     * @param json        json
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void modifiedClassification(String json,Class clazz, boolean showLoading) {
        request(Urls.MODIFIED_CLASSIFICATION, json, clazz, showLoading);
    }

    /**
     *  创建商品
     *
     * @param goodsSerial       条码
     * @param goodsName     商品名
     * @param stcId     分类id
     * @param goodsImage     商品列表图
     * @param goodsImageMoreList     商品大图
     * @param goodsSpec     规格
     * @param goodsDescription     商品描述
     * @param goodsStorePrice     进价
     * @param price       售价
     * @param storage     库存
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void createGoods(String goodsSerial, String goodsName, long stcId, String goodsImage
            , List<String> goodsImageMoreList, String goodsSpec,
                            String goodsDescription, String goodsStorePrice, String price, int storage, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("goodsSerial", goodsSerial);
        p.put("goodsName", goodsName);
        p.put("stcId", stcId);
        p.put("goodsImage", goodsImage);
        p.put("goodsImageMoreList", goodsImageMoreList);
        p.put("goodsSpec", goodsSpec);
        p.put("goodsDescription", goodsDescription);
        p.put("goodsStorePrice", goodsStorePrice );
        p.put("price", price );
        p.put("storage", storage );
        requestnot(Urls.CREATE_GOODS, p, clazz, showLoading);
    }

    /**
     *  编辑商品
     *
     * @param id       商铺id
     * @param goodsSerial       条码
     * @param goodsName     商品名
     * @param stcId     分类id
     * @param goodsImage     商品列表图
     * @param goodsImageMoreList     商品大图
     * @param goodsSpec     规格
     * @param goodsDescription     商品描述
     * @param goodsStorePrice     进价
     * @param price       售价
     * @param storage     库存
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void updateGoods(int id, String goodsSerial, String goodsName, long stcId, String goodsImage
            , List<String> goodsImageMoreList, String goodsSpec,
                            String goodsDescription, String goodsStorePrice, String price, int storage, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("id", id);
        p.put("goodsSerial", goodsSerial);
        p.put("goodsName", goodsName);
        p.put("stcId", stcId);
        p.put("goodsImage", goodsImage);
        p.put("goodsImageMoreList", goodsImageMoreList);
        p.put("goodsSpec", goodsSpec);
        p.put("goodsDescription", goodsDescription);
        p.put("goodsStorePrice", goodsStorePrice );
        p.put("price", price );
        p.put("storage", storage );
        requestnot(Urls.UPDATE_GOODS, p, clazz, showLoading);
    }

    /**
     *  删除商品
     *
     * @param json        json
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void deleteGoods(String json,Class clazz, boolean showLoading) {
        request(Urls.DELETE_GOODS, json, clazz, showLoading);
    }

    /**
     *  获取咨询
     *
     * @param state       新闻状态
     * @param from        起始个数
     * @param count       每页个数
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getNews(int state ,int from, int count, Class clazz, boolean showLoading) {
        request(Urls.GET_News + "/"+state+"/"+from+"/"+count, clazz, showLoading);
    }

    /**
     *  获取店铺某分类下面的商品
     *
     * @param page        页码
     * @param count       每页个数
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getMarket(int page ,int count, Class clazz, boolean showLoading) {
        request(Urls.GET_MARKET + "/"+page+"/"+count, clazz, showLoading);
    }

    /**
     *  获取满减活动
     *
     * @param state       满减活动状态（0：所有，1：未开始，2：进行中，3：已结束）
     * @param page        页码
     * @param count       每页个数
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getFullCutList(String state, int page ,int count, Class clazz, boolean showLoading) {
        request(Urls.GET_FULLCUT + "/"+state+ "/"+page+"/"+count, clazz, showLoading);
    }

    /**
     *  获取满减活动
     *
     * @param id          满减活动id
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getFullCutById(int id, Class clazz, boolean showLoading) {
        request(Urls.GET_FULLCUT_BYID + "/"+id, clazz, showLoading);
    }

    /**
     *  添加满减活动
     *
     * @param startTime       开始时间
     * @param endTime         结束时间
     * @param mansongName     满减活动名称
     * @param mansongRuleBeanList     子活动list
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void addFullCut(String startTime, String endTime, String mansongName ,String remark
            , List<AddFullCutNextModel> mansongRuleBeanList, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("startTime", startTime);
        p.put("endTime", endTime);
        p.put("mansongName", mansongName);
        p.put("remark", remark);
        p.put("mansongRuleBeanList", mansongRuleBeanList);
        requestnot(Urls.ADD_FULLCUT, p, clazz, showLoading);
    }

    /**
     *  编辑满减活动
     *
     * @param id              满减活动id
     * @param startTime       开始时间
     * @param endTime         结束时间
     * @param mansongName     满减活动名称
     * @param mansongRuleBeanList     子活动list
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void editFullCut(int id, String startTime, String endTime, String mansongName,String remark
            , List<AddFullCutNextModel> mansongRuleBeanList, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("id", id);
        p.put("startTime", startTime);
        p.put("endTime", endTime);
        p.put("mansongName", mansongName);
        p.put("remark", remark);
        p.put("mansongRuleBeanList", mansongRuleBeanList);
        requestnot(Urls.ADD_FULLCUT, p, clazz, showLoading);
    }

    /**
     *  删除满减活动
     *
     * @param id          满减活动id
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void deleteFullCutById(int id, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        requestnot(Urls.DELETE_FULLCUT+"/"+id, p, clazz, showLoading);
    }

    /**
     *  结束满减活动
     *
     * @param id          满减活动id
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void finishFullCutById(int id, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        requestnot(Urls.FINISH_FULLCUT+"/"+id, p, clazz, showLoading);
    }

    /**
     *  获取优惠券
     *
     * @param state       状态（1：未开始，2：进行中，3：已结束）
     * @param page        页码
     * @param count       每页个数
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getCouponList(String state, int page ,int count, Class clazz, boolean showLoading) {
        request(Urls.GET_COUPON + "/"+state+ "/"+page+"/"+count, clazz, showLoading);
    }

    /**
     *  删除优惠券
     *
     * @param id          优惠券id
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void deleteCouponById(int id, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        requestnot(Urls.DELETE_COUPON+"/"+id, p, clazz, showLoading);
    }

    /**
     *  添加优惠券
     *
     * @param couponPrice           面额
     * @param couponStorage         可领用数量
     * @param couponLimit           满多少可用
     * @param couponStartDate       开始时间
     * @param couponEndDate         结束时间
     * @param userReceiveLimit      领取限制
     * @param couponDesc            描述
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void addCoupon(String couponPrice, String couponStorage, String couponLimit, String userReceiveLimit,
                          String couponStartDate, String couponEndDate, String couponDesc, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("couponStartDate", couponStartDate);
        p.put("couponEndDate", couponEndDate);
        p.put("couponPrice", couponPrice);
        p.put("couponDesc", couponDesc);
        p.put("couponLimit", couponLimit);
        p.put("couponStorage", couponStorage);
        p.put("couponRecommend", 1);
        p.put("userReceiveLimit", userReceiveLimit);
        requestnot(Urls.ADD_COUPON, p, clazz, showLoading);
    }

    /**
     *  结束优惠券
     *
     * @param id          优惠券id
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void finishCouponById(int id, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        requestnot(Urls.FINISH_COUPON+"/"+id, p, clazz, showLoading);
    }

    /**
     *  获取优惠券详情
     *
     * @param id          优惠券id
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getCouponById(int id, Class clazz, boolean showLoading) {
        request(Urls.GET_COUPON_DETAILS + "/"+id, clazz, showLoading);
    }

    /**
     *  添加优惠券
     *
     * @param id                    id
     * @param couponPrice           面额
     * @param couponStorage         可领用数量
     * @param couponLimit           满多少可用
     * @param couponStartDate       开始时间
     * @param couponEndDate         结束时间
     * @param userReceiveLimit      领取限制
     * @param couponDesc            描述
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void editCoupon(int id, String couponPrice, String couponStorage, String couponLimit, String userReceiveLimit,
                          String couponStartDate, String couponEndDate, String couponDesc, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("id", id);
        p.put("couponStartDate", couponStartDate);
        p.put("couponEndDate", couponEndDate);
        p.put("couponPrice", couponPrice);
        p.put("couponDesc", couponDesc);
        p.put("couponLimit", couponLimit);
        p.put("couponStorage", couponStorage);
        p.put("couponRecommend", 1);
        p.put("userReceiveLimit", userReceiveLimit);
        requestnot(Urls.EDIT_COUPON, p, clazz, showLoading);
    }

    /**
     *  优惠券领用记录
     *
     * @param id          优惠券id
     * @param page        页码
     * @param count       以获取条数
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getCouponRecordsById(int id, int page, int count, Class clazz, boolean showLoading) {
        request(Urls.GET_COUPON_RECORDS + "/" + id + "/" + page + "/" + count, clazz, showLoading);
    }

    /**
     *  获取店铺会员列表
     *
     * @param queryParam            搜索参数
     * @param filter                筛选参数
     * @param page                  页码
     * @param pageSize              每页数量
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void getStoreMember(String queryParam, List<MemberFilterModel> filter, int page, int pageSize, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("queryParam", queryParam);
        p.put("filter", filter);
        requestnot(Urls.GET_STORE_MEMBER+"/"+page+"/"+pageSize, p, clazz, showLoading);
    }

    /**
     *  创建店铺会员
     *
     * @param birthday              生日
     * @param avatar                头像
     * @param sex                   性别：0:女，1:男
     * @param memo                  备注
     * @param weixin                微信
     * @param address               地址
     * @param name                  姓名
     * @param qq                    qq
     * @param mobile                电话
     * @param type                  会员类型
     * @param imgList               生活照
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void createMember(long birthday, String avatar, boolean sex, String memo, String weixin,
                             String address, String name, String qq, String mobile, int type,
                             List<String> imgList, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("birthday", birthday);
        p.put("avatar", avatar);
        p.put("sex", sex);
        p.put("memo", memo);
        p.put("weixin", weixin);
        p.put("address", address);
        p.put("name", name);
        p.put("qq", qq);
        p.put("mobile", mobile);
        p.put("telephone", mobile);
        p.put("type", type);
        p.put("imgList", imgList);
        requestnot(Urls.CRAETE_MEMBER, p, clazz, showLoading);
    }

    /**
     *  批量添加会员
     *
     * @param json        json
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void addMemberList(String json,Class clazz, boolean showLoading) {
        request(Urls.CRAETE_MEMBER_LIST, json, clazz, showLoading);
    }

    /**
     *  获取会员筛选条件
     *
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getMenberFilterData(Class clazz, boolean showLoading) {
        request(Urls.GET_MEMBER_FILTER, clazz, showLoading);
    }

    /**
     *  获取店铺会员详情
     *
     * @param memberID              会员id
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void getMemberDetails(int memberID, Class clazz, boolean showLoading) {
        request(Urls.GET_MEMBER_DETAILS+"/"+memberID, clazz, showLoading);
    }

    /**
     *  删除会员
     *
     * @param memberID              会员id
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void deleteMember(int memberID, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        requestnot(Urls.DELETE_MEMBER+"/"+memberID, p, clazz, showLoading);
    }

    /**
     *  编辑店铺会员信息
     *
     * @param id                    会员id
     * @param birthday              生日
     * @param avatar                头像
     * @param sex                   性别：0:女，1:男
     * @param memo                  备注
     * @param weixin                微信
     * @param address               地址
     * @param name                  姓名
     * @param qq                    qq
     * @param mobile                电话
     * @param type                  会员类型
     * @param imgList               生活照
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void editMember(int id, long birthday, String avatar, boolean sex, String memo, String weixin,
                             String address, String name, String qq, String mobile, int type,
                             List<String> imgList, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("id", id);
        p.put("birthday", birthday);
        p.put("avatar", avatar);
        p.put("sex", sex);
        p.put("memo", memo);
        p.put("weixin", weixin);
        p.put("address", address);
        p.put("name", name);
        p.put("qq", qq);
        p.put("mobile", mobile);
        p.put("telephone", mobile);
        p.put("type", type);
        p.put("imgList", imgList);
        requestnot(Urls.EDIT_MEMBER_DETAILS, p, clazz, showLoading);
    }

    /**
     *  创建／添加会员标签
     *
     * @param memberId              会员id
     * @param tagName               tag名
     * @param sstId                 tagId
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void createTag(int memberId, String tagName, int sstId, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("memberId", memberId);
        if (!tagName.isEmpty()) {
            p.put("tagName", tagName);
        } else {
            p.put("sstId", sstId);
        }
        requestnot(Urls.CREATE_TAG, p, clazz, showLoading);
    }

    /**
     *  删除会员标签
     *
     * @param memberId              会员id
     * @param sstId                 tagId
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void deleteTag(int memberId, int sstId, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("memberId", memberId);
        p.put("sstId", sstId);
        requestnot(Urls.DELETE_TAG, p, clazz, showLoading);
    }

    /**
     *  删除会员标签
     *
     * @param memberId              会员id
     * @param page                  页码
     * @param pageSize              每页数量
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void getMemberRecord(int memberId, int page, int pageSize, Class clazz, boolean showLoading) {
        request(Urls.GET_MEMBER_RECORD + "/" + memberId + "/" + page + "/" +pageSize, clazz, showLoading);
    }

    /**
     *  删除会员消费记录
     *
     * @param id                    消费记录id
     * @param delType               删除类别
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void deleteMemberRecord(int id, int delType, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("id", id);
        p.put("delType", delType);
        requestnot(Urls.DELETE_MEMBER_RECORD, p, clazz, showLoading);
    }

    /**
     *  获取用户拥有当前店铺里的所有优惠卷列表
     *
     * @param member                会员手机号
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void getCouponByMember(String member, Class clazz, boolean showLoading) {
        request(Urls.GET_COUPON_BY_MEMBER + "/" + member, clazz, showLoading);
    }

    /**
     *  获取当前店铺所有满减列表 （所有进行中+过期（15天内））
     *
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void getFullcutList(Class clazz, boolean showLoading) {
        request(Urls.GET_FULLCUT_LIST , clazz, showLoading);
    }

    /**
     *  添加会员消费记录
     *
     * @param memberId              会员id
     * @param total                 总计加价
     * @param memo                  备注
     * @param received              实际收取金额
     * @param couponList            优惠券id
     * @param mansongRuleList       满减id
     * @param goodsBeanList         商品列表
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void addMemberRecord(int memberId, String total, String memo, String received
            , List<AddRecordCouponListModel.BodyEntity> couponList
            , List<AddRecordFullCutListModel.BodyEntity> mansongRuleList
            , List<AddRecordParamsModel.GoodsBeanListEntity> goodsBeanList, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("memberId", memberId);
        p.put("total", total);
        p.put("memo", memo);
        p.put("received", received);
        if (couponList.size()!=0 && couponList.get(0).getId()>0) {
            p.put("couponList", couponList);
        }
        if (mansongRuleList.size()!=0 && mansongRuleList.get(0).getId()>0) {
            p.put("mansongRuleList", mansongRuleList);
        }
        p.put("goodsBeanList", goodsBeanList);
        requestnot(Urls.ADD_MEMBER_RECORD, p, clazz, showLoading);
    }

    /**
     *  更新版本
     *
     * @param version               当前版本号
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void updateVersion(String version, Class clazz, boolean showLoading) {
        request(Urls.UPDATE_VERSION + version , clazz, showLoading);
    }

    /**
     *  绑定设备号
     *
     * @param acount                设备标识
     * @param pw                    设备密码
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void bindingAcount(String acount, String pw, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        requestnot(Urls.BINGDING_ACOUNT+acount+"/"+pw, p, clazz, showLoading);
    }

    /**
     *  获取用户拥有当前店铺里的所有优惠卷列表
     *
     * @param orderId               订单号
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void getOrderDetail(String orderId, Class clazz, boolean showLoading) {
        request(Urls.GET_ORDER_DETAIL + orderId, clazz, showLoading);
    }

    /**
     *  获取订单列表
     *
     * @param page        页码
     * @param count       每页个数
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getOrderList(int page ,int count, Class clazz, boolean showLoading) {
        request(Urls.GET_ORDER_LIST + "/"+page+"/"+count, clazz, showLoading);
    }

    /**
     *  获取可支付的订单列表
     *
     * @param clazz       返回Gson对象
     * @param showLoading 是否显示对话框
     **/
    public void getPayOrderList(Class clazz, boolean showLoading) {
        request(Urls.GET_PAYORDER_LIST , clazz, showLoading);
    }

    /**
     *  合并订单
     *
     * @param orderId               orderIdlist
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void unionOrder(String orderId, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("orderIds", orderId);
        requestnot(Urls.UNION_ORDER, p, clazz, showLoading);
    }

    /**
     *  微信预支付
     *
     * @param orderId               orderId
     * @param clazz                 返回Gson对象
     * @param showLoading           是否显示对话框
     **/
    public void prePayWeiXin(String orderId, Class clazz, boolean showLoading) {
        Map<String, Object> p = new HashMap<>();
        p.put("order_id", orderId);
        p.put("ip", "192.168.1.1");
        requestnot(Urls.WEIXIN_PREPAY, p, clazz, showLoading);
    }


}
