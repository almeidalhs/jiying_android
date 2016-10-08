package com.atman.jishang.net.model;

/**
 * 描述 编辑店铺信息返回结果
 * 作者 tangbingliang
 * 时间 16/4/19 10:22
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class EditShopInfoModel {
    /**
     * result : 1
     * body : {"id":11,"storeName":"My Store","storeAuth":0,"nameAuth":0,"gradeId":1,"memberId":17,"memberName":"13854725536","storeOwnerCard":"1234567890","scId":10,"areaId":48,"areaInfo":"上海市","storeAddress":"毕昇路299弄123号102室","storeZip":"200201","storeTel":"13988476354","storeState":1,"storeSort":0,"storeTime":"123466","storeBanner":"http://192.168.1.141:8000/by/http://192.168.1.141:8000/by/Store/c2/ec/c2ec9ce2f61911e5b57674d02ba07f83.png","storeKeywords":"","storeDescription":"","description":"这就是我的店","storeDomainTime":0,"storeRecommend":0,"storeTheme":"default","storeCredit":0,"praiseRate":0,"storeDescCredit":0,"storeServiceCredit":0,"storeDeliveryCredit":0,"storeCode":"default_qrcode.png","storeCollect":0,"storeSales":0}
     */

    private String result;
    private BodyEntity body;

    public void setResult(String result) {
        this.result = result;
    }

    public void setBody(BodyEntity body) {
        this.body = body;
    }

    public String getResult() {
        return result;
    }

    public BodyEntity getBody() {
        return body;
    }

    public static class BodyEntity {
        /**
         * id : 11
         * storeName : My Store
         * storeAuth : 0
         * nameAuth : 0
         * gradeId : 1
         * memberId : 17
         * memberName : 13854725536
         * storeOwnerCard : 1234567890
         * scId : 10
         * areaId : 48
         * areaInfo : 上海市
         * storeAddress : 毕昇路299弄123号102室
         * storeZip : 200201
         * storeTel : 13988476354
         * storeState : 1
         * storeSort : 0
         * storeTime : 123466
         * storeBanner : http://192.168.1.141:8000/by/http://192.168.1.141:8000/by/Store/c2/ec/c2ec9ce2f61911e5b57674d02ba07f83.png
         * storeKeywords :
         * storeDescription :
         * description : 这就是我的店
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
         */

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
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public void setStoreAuth(int storeAuth) {
            this.storeAuth = storeAuth;
        }

        public void setNameAuth(int nameAuth) {
            this.nameAuth = nameAuth;
        }

        public void setGradeId(int gradeId) {
            this.gradeId = gradeId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public void setStoreOwnerCard(String storeOwnerCard) {
            this.storeOwnerCard = storeOwnerCard;
        }

        public void setScId(int scId) {
            this.scId = scId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public void setAreaInfo(String areaInfo) {
            this.areaInfo = areaInfo;
        }

        public void setStoreAddress(String storeAddress) {
            this.storeAddress = storeAddress;
        }

        public void setStoreZip(String storeZip) {
            this.storeZip = storeZip;
        }

        public void setStoreTel(String storeTel) {
            this.storeTel = storeTel;
        }

        public void setStoreState(int storeState) {
            this.storeState = storeState;
        }

        public void setStoreSort(int storeSort) {
            this.storeSort = storeSort;
        }

        public void setStoreTime(String storeTime) {
            this.storeTime = storeTime;
        }

        public void setStoreBanner(String storeBanner) {
            this.storeBanner = storeBanner;
        }

        public void setStoreKeywords(String storeKeywords) {
            this.storeKeywords = storeKeywords;
        }

        public void setStoreDescription(String storeDescription) {
            this.storeDescription = storeDescription;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setStoreDomainTime(int storeDomainTime) {
            this.storeDomainTime = storeDomainTime;
        }

        public void setStoreRecommend(int storeRecommend) {
            this.storeRecommend = storeRecommend;
        }

        public void setStoreTheme(String storeTheme) {
            this.storeTheme = storeTheme;
        }

        public void setStoreCredit(int storeCredit) {
            this.storeCredit = storeCredit;
        }

        public void setPraiseRate(int praiseRate) {
            this.praiseRate = praiseRate;
        }

        public void setStoreDescCredit(int storeDescCredit) {
            this.storeDescCredit = storeDescCredit;
        }

        public void setStoreServiceCredit(int storeServiceCredit) {
            this.storeServiceCredit = storeServiceCredit;
        }

        public void setStoreDeliveryCredit(int storeDeliveryCredit) {
            this.storeDeliveryCredit = storeDeliveryCredit;
        }

        public void setStoreCode(String storeCode) {
            this.storeCode = storeCode;
        }

        public void setStoreCollect(int storeCollect) {
            this.storeCollect = storeCollect;
        }

        public void setStoreSales(int storeSales) {
            this.storeSales = storeSales;
        }

        public int getId() {
            return id;
        }

        public String getStoreName() {
            return storeName;
        }

        public int getStoreAuth() {
            return storeAuth;
        }

        public int getNameAuth() {
            return nameAuth;
        }

        public int getGradeId() {
            return gradeId;
        }

        public int getMemberId() {
            return memberId;
        }

        public String getMemberName() {
            return memberName;
        }

        public String getStoreOwnerCard() {
            return storeOwnerCard;
        }

        public int getScId() {
            return scId;
        }

        public int getAreaId() {
            return areaId;
        }

        public String getAreaInfo() {
            return areaInfo;
        }

        public String getStoreAddress() {
            return storeAddress;
        }

        public String getStoreZip() {
            return storeZip;
        }

        public String getStoreTel() {
            return storeTel;
        }

        public int getStoreState() {
            return storeState;
        }

        public int getStoreSort() {
            return storeSort;
        }

        public String getStoreTime() {
            return storeTime;
        }

        public String getStoreBanner() {
            return storeBanner;
        }

        public String getStoreKeywords() {
            return storeKeywords;
        }

        public String getStoreDescription() {
            return storeDescription;
        }

        public String getDescription() {
            return description;
        }

        public int getStoreDomainTime() {
            return storeDomainTime;
        }

        public int getStoreRecommend() {
            return storeRecommend;
        }

        public String getStoreTheme() {
            return storeTheme;
        }

        public int getStoreCredit() {
            return storeCredit;
        }

        public int getPraiseRate() {
            return praiseRate;
        }

        public int getStoreDescCredit() {
            return storeDescCredit;
        }

        public int getStoreServiceCredit() {
            return storeServiceCredit;
        }

        public int getStoreDeliveryCredit() {
            return storeDeliveryCredit;
        }

        public String getStoreCode() {
            return storeCode;
        }

        public int getStoreCollect() {
            return storeCollect;
        }

        public int getStoreSales() {
            return storeSales;
        }
    }
}
