package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述 商铺信息
 * 作者 tangbingliang
 * 时间 16/4/18 14:22
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class ShopInformationModel {
    /**
     * result : 1
     * body : {"id":32,"storeName":"tbl","storeAuth":0,"nameAuth":0,"gradeId":1,"memberId":71,"memberName":"18578909070","storeOwnerCard":"1234567890","scId":65,"areaId":48,"areaInfo":"上海市","storeAddress":"the","storeZip":"200201","storeTel":"18578909061","storeState":1,"storeSort":0,"storeTime":"123466","storeBanner":"Store/4f/72/4f728915174411e6908274d02ba07f83.jpg","fullStoreBanner":"http://192.168.1.141:8000/by/Store/4f/72/4f728915174411e6908274d02ba07f83.jpg","storeKeywords":"","storeDescription":"","description":"oktbl","storeDomainTime":0,"storeRecommend":0,"storeTheme":"default","storeCredit":0,"praiseRate":0,"storeDescCredit":0,"storeServiceCredit":0,"storeDeliveryCredit":0,"storeCode":"default_qrcode.png","storeCollect":0,"storeSales":0,"scName":"其他","storeWebUrl":"http://www.atman.com:8000/cp/32","storedGoodsClassBeanList":[{"id":222,"stcName":"分类1","stcParentId":0,"stcState":1,"storeId":32,"stcSort":1,"goodsCount":3},{"id":223,"stcName":"小破孩迷信","stcParentId":0,"stcState":1,"storeId":32,"stcSort":2,"goodsCount":-1}],"couponBeanList":[],"mansongRuleBeanList":[]}
     */

    private String result;
    /**
     * id : 32
     * storeName : tbl
     * storeAuth : 0
     * nameAuth : 0
     * gradeId : 1
     * memberId : 71
     * memberName : 18578909070
     * storeOwnerCard : 1234567890
     * scId : 65
     * areaId : 48
     * areaInfo : 上海市
     * storeAddress : the
     * storeZip : 200201
     * storeTel : 18578909061
     * storeState : 1
     * storeSort : 0
     * storeTime : 123466
     * storeBanner : Store/4f/72/4f728915174411e6908274d02ba07f83.jpg
     * fullStoreBanner : http://192.168.1.141:8000/by/Store/4f/72/4f728915174411e6908274d02ba07f83.jpg
     * storeKeywords :
     * storeDescription :
     * description : oktbl
     * storeDomainTime : 0
     * storeRecommend : 0
     * storeTheme : default
     * storeCredit : 0
     * praiseRate : 0
     * storeDescCredit : 0
     * storeServiceCredit : 0
     * storeDeliveryCredit : 0
     * storeCode : default_qrcode.png
     * storeCollect : 0
     * storeSales : 0
     * scName : 其他
     * storeWebUrl : http://www.atman.com:8000/cp/32
     * storedGoodsClassBeanList : [{"id":222,"stcName":"分类1","stcParentId":0,"stcState":1,"storeId":32,"stcSort":1,"goodsCount":3},{"id":223,"stcName":"小破孩迷信","stcParentId":0,"stcState":1,"storeId":32,"stcSort":2,"goodsCount":-1}]
     * couponBeanList : []
     * mansongRuleBeanList : []
     */

    private BodyEntity body;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public BodyEntity getBody() {
        return body;
    }

    public void setBody(BodyEntity body) {
        this.body = body;
    }

    public static class BodyEntity {
        private int id;
        private String storeName;
        private int storeAuth;
        private int nameAuth;
        private int gradeId;
        private int memberId;
        private String memberName;
        private String storeOwnerCard;
        private int scId;
        private int areaId;
        private String areaInfo;
        private String storeAddress;
        private String storeZip;
        private String storeTel;
        private int storeState;
        private int storeSort;
        private String storeTime;
        private String storeBanner;
        private String fullStoreBanner;
        private String storeKeywords;
        private String storeDescription;
        private String description;
        private int storeDomainTime;
        private int storeRecommend;
        private String storeTheme;
        private int storeCredit;
        private int praiseRate;
        private int storeDescCredit;
        private int storeServiceCredit;
        private int storeDeliveryCredit;
        private String storeCode;
        private int storeCollect;
        private int storeSales;
        private int todayStoreViewCount;
        private int storeViewCount;
        private String scName;
        private String storeWebUrl;
        /**
         * id : 222
         * stcName : 分类1
         * stcParentId : 0
         * stcState : 1
         * storeId : 32
         * stcSort : 1
         * goodsCount : 3
         */

        private List<StoredGoodsClassBeanListEntity> storedGoodsClassBeanList;
        private List<?> couponBeanList;
        private List<?> mansongRuleBeanList;

        private String message;

        public int getTodayStoreViewCount() {
            return todayStoreViewCount;
        }

        public void setTodayStoreViewCount(int todayStoreViewCount) {
            this.todayStoreViewCount = todayStoreViewCount;
        }

        public int getStoreViewCount() {
            return storeViewCount;
        }

        public void setStoreViewCount(int storeViewCount) {
            this.storeViewCount = storeViewCount;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public int getStoreAuth() {
            return storeAuth;
        }

        public void setStoreAuth(int storeAuth) {
            this.storeAuth = storeAuth;
        }

        public int getNameAuth() {
            return nameAuth;
        }

        public void setNameAuth(int nameAuth) {
            this.nameAuth = nameAuth;
        }

        public int getGradeId() {
            return gradeId;
        }

        public void setGradeId(int gradeId) {
            this.gradeId = gradeId;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getStoreOwnerCard() {
            return storeOwnerCard;
        }

        public void setStoreOwnerCard(String storeOwnerCard) {
            this.storeOwnerCard = storeOwnerCard;
        }

        public int getScId() {
            return scId;
        }

        public void setScId(int scId) {
            this.scId = scId;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getAreaInfo() {
            return areaInfo;
        }

        public void setAreaInfo(String areaInfo) {
            this.areaInfo = areaInfo;
        }

        public String getStoreAddress() {
            return storeAddress;
        }

        public void setStoreAddress(String storeAddress) {
            this.storeAddress = storeAddress;
        }

        public String getStoreZip() {
            return storeZip;
        }

        public void setStoreZip(String storeZip) {
            this.storeZip = storeZip;
        }

        public String getStoreTel() {
            return storeTel;
        }

        public void setStoreTel(String storeTel) {
            this.storeTel = storeTel;
        }

        public int getStoreState() {
            return storeState;
        }

        public void setStoreState(int storeState) {
            this.storeState = storeState;
        }

        public int getStoreSort() {
            return storeSort;
        }

        public void setStoreSort(int storeSort) {
            this.storeSort = storeSort;
        }

        public String getStoreTime() {
            return storeTime;
        }

        public void setStoreTime(String storeTime) {
            this.storeTime = storeTime;
        }

        public String getStoreBanner() {
            return storeBanner;
        }

        public void setStoreBanner(String storeBanner) {
            this.storeBanner = storeBanner;
        }

        public String getFullStoreBanner() {
            return fullStoreBanner;
        }

        public void setFullStoreBanner(String fullStoreBanner) {
            this.fullStoreBanner = fullStoreBanner;
        }

        public String getStoreKeywords() {
            return storeKeywords;
        }

        public void setStoreKeywords(String storeKeywords) {
            this.storeKeywords = storeKeywords;
        }

        public String getStoreDescription() {
            return storeDescription;
        }

        public void setStoreDescription(String storeDescription) {
            this.storeDescription = storeDescription;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getStoreDomainTime() {
            return storeDomainTime;
        }

        public void setStoreDomainTime(int storeDomainTime) {
            this.storeDomainTime = storeDomainTime;
        }

        public int getStoreRecommend() {
            return storeRecommend;
        }

        public void setStoreRecommend(int storeRecommend) {
            this.storeRecommend = storeRecommend;
        }

        public String getStoreTheme() {
            return storeTheme;
        }

        public void setStoreTheme(String storeTheme) {
            this.storeTheme = storeTheme;
        }

        public int getStoreCredit() {
            return storeCredit;
        }

        public void setStoreCredit(int storeCredit) {
            this.storeCredit = storeCredit;
        }

        public int getPraiseRate() {
            return praiseRate;
        }

        public void setPraiseRate(int praiseRate) {
            this.praiseRate = praiseRate;
        }

        public int getStoreDescCredit() {
            return storeDescCredit;
        }

        public void setStoreDescCredit(int storeDescCredit) {
            this.storeDescCredit = storeDescCredit;
        }

        public int getStoreServiceCredit() {
            return storeServiceCredit;
        }

        public void setStoreServiceCredit(int storeServiceCredit) {
            this.storeServiceCredit = storeServiceCredit;
        }

        public int getStoreDeliveryCredit() {
            return storeDeliveryCredit;
        }

        public void setStoreDeliveryCredit(int storeDeliveryCredit) {
            this.storeDeliveryCredit = storeDeliveryCredit;
        }

        public String getStoreCode() {
            return storeCode;
        }

        public void setStoreCode(String storeCode) {
            this.storeCode = storeCode;
        }

        public int getStoreCollect() {
            return storeCollect;
        }

        public void setStoreCollect(int storeCollect) {
            this.storeCollect = storeCollect;
        }

        public int getStoreSales() {
            return storeSales;
        }

        public void setStoreSales(int storeSales) {
            this.storeSales = storeSales;
        }

        public String getScName() {
            return scName;
        }

        public void setScName(String scName) {
            this.scName = scName;
        }

        public String getStoreWebUrl() {
            return storeWebUrl;
        }

        public void setStoreWebUrl(String storeWebUrl) {
            this.storeWebUrl = storeWebUrl;
        }

        public List<StoredGoodsClassBeanListEntity> getStoredGoodsClassBeanList() {
            return storedGoodsClassBeanList;
        }

        public void setStoredGoodsClassBeanList(List<StoredGoodsClassBeanListEntity> storedGoodsClassBeanList) {
            this.storedGoodsClassBeanList = storedGoodsClassBeanList;
        }

        public List<?> getCouponBeanList() {
            return couponBeanList;
        }

        public void setCouponBeanList(List<?> couponBeanList) {
            this.couponBeanList = couponBeanList;
        }

        public List<?> getMansongRuleBeanList() {
            return mansongRuleBeanList;
        }

        public void setMansongRuleBeanList(List<?> mansongRuleBeanList) {
            this.mansongRuleBeanList = mansongRuleBeanList;
        }

        public static class StoredGoodsClassBeanListEntity {
            private int id;
            private String stcName;
            private int stcParentId;
            private int stcState;
            private int storeId;
            private int stcSort;
            private int goodsCount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getStcName() {
                return stcName;
            }

            public void setStcName(String stcName) {
                this.stcName = stcName;
            }

            public int getStcParentId() {
                return stcParentId;
            }

            public void setStcParentId(int stcParentId) {
                this.stcParentId = stcParentId;
            }

            public int getStcState() {
                return stcState;
            }

            public void setStcState(int stcState) {
                this.stcState = stcState;
            }

            public int getStoreId() {
                return storeId;
            }

            public void setStoreId(int storeId) {
                this.storeId = storeId;
            }

            public int getStcSort() {
                return stcSort;
            }

            public void setStcSort(int stcSort) {
                this.stcSort = stcSort;
            }

            public int getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(int goodsCount) {
                this.goodsCount = goodsCount;
            }
        }
    }
}
