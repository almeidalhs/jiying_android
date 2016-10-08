package com.atman.jishang.net.model;

/**
 * 描述 创建店铺
 * 作者 tangbingliang
 * 时间 16/4/18 10:50
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class CreateShopModel {
    /**
     * result : 1
     * body : {"id":34,"storeName":"测试","gradeId":1,"memberId":66,"memberName":"夜友-515840","storeOwnerCard":"1234567890","scId":65,"areaId":48,"areaInfo":"上海市","storeAddress":"哦哦","storeZip":"200201","storeTel":"13860000028","storeState":1,"storeTime":"123466","storeBanner":"Store/1e/f1/1ef1b59f174811e695c274d02ba07f83.jpg","fullStoreBanner":"http://192.168.1.141:8000/by/Store/1e/f1/1ef1b59f174811e695c274d02ba07f83.jpg","description":"哦哦","storeWebUrl":"http://www.atman.com:8000/cp/34"}
     */

    private String result;
    /**
     * id : 34
     * storeName : 测试
     * gradeId : 1
     * memberId : 66
     * memberName : 夜友-515840
     * storeOwnerCard : 1234567890
     * scId : 65
     * areaId : 48
     * areaInfo : 上海市
     * storeAddress : 哦哦
     * storeZip : 200201
     * storeTel : 13860000028
     * storeState : 1
     * storeTime : 123466
     * storeBanner : Store/1e/f1/1ef1b59f174811e695c274d02ba07f83.jpg
     * fullStoreBanner : http://192.168.1.141:8000/by/Store/1e/f1/1ef1b59f174811e695c274d02ba07f83.jpg
     * description : 哦哦
     * storeWebUrl : http://www.atman.com:8000/cp/34
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
        private String storeTime;
        private String storeBanner;
        private String fullStoreBanner;
        private String description;
        private String storeWebUrl;
        private String message;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStoreWebUrl() {
            return storeWebUrl;
        }

        public void setStoreWebUrl(String storeWebUrl) {
            this.storeWebUrl = storeWebUrl;
        }
    }
}
