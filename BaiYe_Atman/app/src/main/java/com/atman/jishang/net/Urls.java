package com.atman.jishang.net;

/**
 * 描述 访问路径统一设置类
 * 作者 tangbingliang
 * 时间 16/4/12 10:28
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class Urls {
    /**
     * 百业服务器
     */
//    public static final String RWH_HOST = "http://www.jiplaza.net";
//    public static final String RWH_HOST_UP = "http://www.jiplaza.net/";
//    public static final String RWH_HOST_IMG = "http://www.jiplaza.net:8000/";

    public static final String RWH_HOST = "http://192.168.1.141:8089/baiye";
    public static final String RWH_HOST_UP = "http://192.168.1.141:8089/";
    public static final String RWH_HOST_IMG = "http://192.168.1.141:8000/";

    /**
     * 登陆
     */
    public static final String LOGIN = "/login";

    /**
     * 注册
     */
    public static final String REGISTER = "/rest/user";

    /**
     * 发送验证码--注册
     */
    public static final String SEED_MEESAGE = "/rest/checkcode/mobile/";
    public static final String SEED_MEESAGE_AFTER = "?type=1&language=cn";

    /**
     * 验证码校验
     */
    public static final String CODE_CHECK = "/rest/checkcode/mobile/";

    /**
     * 上传头像
     */
    public static final String UP_HEADIMG = "upload?uploadType=avatar";
    public static final String HEADIMG_BEFOR = RWH_HOST_IMG +"by/";

    /**
     * 上传二维码分享图片
     */
    public static final String UP_CODE = "upload?uploadType=introduction";

    /**
     * 上传商品图片
     */
    public static final String UP_GOODSIMG = "upload?uploadType=goods";

    /**
     * 上传商铺背景
     */
    public static final String UP_STOREIMG = "upload?uploadType=store";

    /**
     * 上传会员头像
     */
    public static final String UP_MEMBER_HEADIMG = "upload?uploadType=mAvatar";

    /**
     * 上传会员图片
     */
    public static final String UP_MEMBERIMG = "upload?uploadType=mmeber";

    /**
     * 发送验证码--忘记密码
     */
    public static final String FORGOT = "/rest/user/forgotpwd/";
    public static final String SEED_MEESAGE_AFTER_FORGOT = "?type=0&language=cn";

    /**
     * 获取商铺信息
     */
    public static final String SHOP_INFORMATION = "/rest/store/my";

    /**
     * 获取行业类型
     */
    public static final String GET_INDSTRY_TRPE = "/rest/store/storeclass/0";

    /**
     * 创建店铺
     */
    public static final String CREATE_SHOP = "/rest/store/create";

    /**
     * 修改店铺信息
     */
    public static final String UPDATE_SHOP = "/rest/store/update";

    /**
     * 修改密码
     */
    public static final String RESET_PASSWORD = "/rest/user/password/";

    /**
     * 修改密码
     */
    public static final String FEEDBACK = "/rest/ad/feedback";

    /**
     * 更新头像
     */
    public static final String UPDATA_HEADIMG = "/rest/user/avatar";

    /**
     * 获取首页gridview数据
     */
    public static final String GET_HOME_GRIDVIEW_DATA = "/rest/userconsole/list";

    /**
     * 获取首页广告数据
     */
    public static final String GET_HOME_AD_DATA = "/rest/ad/list/1";

    /**
     * 获取首页gridview数据
     */
    public static final String UPDATE_LIST_ORDER = "/rest/userconsole/sort";

    /**
     * 获取店铺商品分类
     */
    public static final String GET_GOODSCLASS = "/rest/store/goodsclass";

    /**
     * 获取店铺某分类下面的商品
     */
    public static final String GET_GOODS_BY_CLASSID = "/rest/goods/mystore/";

    /**
     * 获取商品详情
     */
    public static final String GET_GOODS_DETAILS = "/rest/goods/";

    /**
     * 获取商铺的商品分类
     */
    public static final String GET_STORECLASS = "/rest/store/goodsclass";

    /**
     * 新建商铺分类
     */
    public static final String CREATE_STORECLASS = "/rest/store/goodsclass/create";

    /**
     * 删除商铺分类
     */
    public static final String DELETE_STORECLASS = "/rest/store/goodsclass/";

    /**
     * 修改商铺分类名
     */
    public static final String EDIT_STORECLASS = "/rest/store/goodsclass/modify";

    /**
     * 修改商铺分类顺序
     */
    public static final String EDIT_STORECLASS_ORDER = "/rest/store/goodsclass/sort";

    /**
     * 商品批量下架
     */
    public static final String BATCH_UNSHELVE = "/rest/goods/show/0";

    /**
     * 商品批量上架
     */
    public static final String BATCH_SHELVE = "/rest/goods/show/1";

    /**
     * 修改分类
     */
    public static final String MODIFIED_CLASSIFICATION = "/rest/goods/modifyclass/";

    /**
     * 创建商品
     */
    public static final String CREATE_GOODS = "/rest/goods/create";

    /**
     * 编辑商品
     */
    public static final String UPDATE_GOODS = "/rest/goods/update";

    /**
     * 删除商品
     */
    public static final String DELETE_GOODS = "/rest/goods/delete";

    /**
     * 资讯
     */
    public static final String GET_News = "/rest/news/list";

    /**
     * 获取营销管理功能列表
     */
    public static final String GET_MARKET = "/rest/market/getAll";

    /**
     * 获取满减活动列表
     */
    public static final String GET_FULLCUT = "/rest/mansong/getMansong";

    /**
     * 获取满减活动列表
     */
    public static final String GET_FULLCUT_BYID = "/rest/mansong/getDetial";

    /**
     * 添加或者修改满减活动
     */
    public static final String ADD_FULLCUT = "/rest/mansong/create";

    /**
     * 删除满减活动
     */
    public static final String DELETE_FULLCUT = "/rest/mansong/delMansong";

    /**
     * 结束满减活动
     */
    public static final String FINISH_FULLCUT = "/rest/mansong/updateMansongState";

    /**
     * 获取优惠券
     */
    public static final String GET_COUPON = "/rest/coupon/getCoupons";

    /**
     * 删除优惠券
     */
    public static final String DELETE_COUPON = "/rest/coupon/delete";

    /**
     * 添加优惠券
     */
    public static final String ADD_COUPON = "/rest/coupon/create";

    /**
     * 结束优惠券
     */
    public static final String FINISH_COUPON = "/rest/coupon/updateCouponState";

    /**
     * 获取优惠券详情
     */
    public static final String GET_COUPON_DETAILS = "/rest/coupon/getCouponById";

    /**
     * 编辑优惠券
     */
    public static final String EDIT_COUPON = "/rest/coupon/updateCoupon";

    /**
     * 优惠券领用记录
     */
    public static final String GET_COUPON_RECORDS = "/rest/coupon/getRecordByStoreCoupon";

    /**
     * 获取店铺会员列表
     */
    public static final String GET_STORE_MEMBER = "/rest/storemember/storemembers";

    /**
     * 添加店铺会员列表
     */
    public static final String CRAETE_MEMBER = "/rest/storemember/create";

    /**
     * 批量添加店铺会员列表
     */
    public static final String CRAETE_MEMBER_LIST = "/rest/storemember/insertList";

    /**
     * 获取会员筛选条件
     */
    public static final String GET_MEMBER_FILTER = "/rest/storemember/getSearchParams";

    /**
     * 获取会员详情
     */
    public static final String GET_MEMBER_DETAILS = "/rest/storemember/getMemberInfo";

    /**
     * 删除会员
     */
    public static final String DELETE_MEMBER = "/rest/storemember/delMember";

    /**
     * 编辑会员信息
     */
    public static final String EDIT_MEMBER_DETAILS = "/rest/storemember/updateMember";

    /**
     * 创建／添加标签
     */
    public static final String CREATE_TAG = "/rest/storemember/addTag";

    /**
     * 删除标签
     */
    public static final String DELETE_TAG = "/rest/storemember/removeTag";

    /**
     * 获取会员消费记录
     */
    public static final String GET_MEMBER_RECORD = "/rest/smconsume/getConsumes";

    /**
     * 删除会员消费记录
     */
    public static final String DELETE_MEMBER_RECORD = "/rest/smconsume/delconsume";

    /**
     * 获取用户拥有当前店铺里的所有优惠卷列表
     */
    public static final String GET_COUPON_BY_MEMBER = "/rest/coupon/getUserCouponByStore";

    /**
     * 获取当前店铺所有满减列表 （所有进行中+过期（15天内））
     */
    public static final String GET_FULLCUT_LIST = "/rest/mansong/getAllMansongRuelList";

    /**
     * 添加会员消费记录
     */
    public static final String ADD_MEMBER_RECORD = "/rest/smconsume/addconsume";

    /**
     * 更新版本
     */
    public static final String UPDATE_VERSION = "/rest/common/androidneedUpdate?version=";

    /**
     * 绑定设备号
     */
    public static final String BINGDING_ACOUNT = "/rest/user/checkns/";

    /**
     * 获取订单详情
     */
    public static final String GET_ORDER_DETAIL = "/rest/order/";

    /**
     * 获取订单列表
     */
    public static final String GET_ORDER_LIST = "/rest/order/my";

    /**
     * 获取可支付的订单列表
     */
    public static final String GET_PAYORDER_LIST = "/rest/order/unpay/1";

    /**
     * 合并订单
     */
    public static final String UNION_ORDER = "/rest/order/union";

    /**
     * 微信预支付
     */
    public static final String WEIXIN_PREPAY = "/rest/pay/weixin/prepay";

    /**
     * 获取服务配置
     */
    public static final String SERVICE_COMMONCOF = "/rest/commconf/main";

    /**
     * 设置服务开关
     */
    public static final String SET_SERVICE_COMMONCOF = "/rest/commconf/updateModuleStatus";

    /**
     * 获取已经设置的wifi
     */
    public static final String GET_WIFI_LIST = "/rest/store/getwifilist";

    /**
     * 删除已有的wifi
     */
    public static final String DELETE_WIFI = "/rest/store/delwifi";

    /**
     * 添加wifi
     */
    public static final String ADD_WIFI = "/rest/store/settingwifi";

    /**
     * 编辑wifi
     */
    public static final String EDIT_WIFI = "/rest/store/settingwifi";

    /**
     * 制码
     */
    public static final String CRESCRESTE_CODE = "/rest/commconf/createQRCode";

    /**
     * 获取已设置的呼叫服务列表
     */
    public static final String GET_CALLSERVICE_LIST = "/rest/commconf/getCallService";

    /**
     * 添加呼叫服务列表
     */
    public static final String ADD_CALLSERVICE = "/rest/commconf/createCallService";

    /**
     * 删除呼叫服务列表
     */
    public static final String DELETE_CALLSERVICE = "/rest/commconf/delete";
}
