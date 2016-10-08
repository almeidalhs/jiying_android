package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/23 16:54
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GetDetailsModel {
    /**
     * result : 1
     * body : {"id":13,"couponTitle":"现金优惠卷BBB","couponDesc":"ffffffRRRRR","couponStartDate":1322112801,"couponEndDate":1322112881,"couponPrice":5,"couponLimit":600,"storeId":17,"couponState":3,"couponStorage":100,"couponUsage":50,"couponUsedage":0,"couponLock":"1","couponClick":1,"couponPrintStyle":"4STYLE","couponRecommend":true,"couponAllowstate":true,"couponAllowremark":"现金优惠卷ffffff","shareUrl":"http://www.atman.com:8000/cp/13","storeName":"zhoudengwu的店"}
     */

    private String result;
    /**
     * id : 13
     * couponTitle : 现金优惠卷BBB
     * couponDesc : ffffffRRRRR
     * couponStartDate : 1322112801
     * couponEndDate : 1322112881
     * couponPrice : 5
     * couponLimit : 600
     * storeId : 17
     * couponState : 3
     * couponStorage : 100
     * couponUsage : 50
     * couponUsedage : 0
     * couponLock : 1
     * couponClick : 1
     * couponPrintStyle : 4STYLE
     * couponRecommend : true
     * couponAllowstate : true
     * couponAllowremark : 现金优惠卷ffffff
     * shareUrl : http://www.atman.com:8000/cp/13
     * storeName : zhoudengwu的店
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
        private String couponTitle;
        private String couponDesc;
        private long couponStartDate;
        private long couponEndDate;
        private double couponPrice;
        private int couponLimit;
        private int storeId;
        private int couponState;
        private int couponStorage;
        private int couponUsage;
        private int couponUsedage;
        private String couponLock;
        private int couponClick;
        private String couponPrintStyle;
        private boolean couponRecommend;
        private boolean couponAllowstate;
        private String couponAllowremark;
        private String shareUrl;
        private String storeName;
        private int userReceiveLimit;

        public int getUserReceiveLimit() {
            return userReceiveLimit;
        }

        public void setUserReceiveLimit(int userReceiveLimit) {
            this.userReceiveLimit = userReceiveLimit;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCouponTitle() {
            return couponTitle;
        }

        public void setCouponTitle(String couponTitle) {
            this.couponTitle = couponTitle;
        }

        public String getCouponDesc() {
            return couponDesc;
        }

        public void setCouponDesc(String couponDesc) {
            this.couponDesc = couponDesc;
        }

        public long getCouponStartDate() {
            return couponStartDate;
        }

        public void setCouponStartDate(long couponStartDate) {
            this.couponStartDate = couponStartDate;
        }

        public long getCouponEndDate() {
            return couponEndDate;
        }

        public void setCouponEndDate(long couponEndDate) {
            this.couponEndDate = couponEndDate;
        }

        public double getCouponPrice() {
            return couponPrice;
        }

        public void setCouponPrice(double couponPrice) {
            this.couponPrice = couponPrice;
        }

        public int getCouponLimit() {
            return couponLimit;
        }

        public void setCouponLimit(int couponLimit) {
            this.couponLimit = couponLimit;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getCouponState() {
            return couponState;
        }

        public void setCouponState(int couponState) {
            this.couponState = couponState;
        }

        public int getCouponStorage() {
            return couponStorage;
        }

        public void setCouponStorage(int couponStorage) {
            this.couponStorage = couponStorage;
        }

        public int getCouponUsage() {
            return couponUsage;
        }

        public void setCouponUsage(int couponUsage) {
            this.couponUsage = couponUsage;
        }

        public int getCouponUsedage() {
            return couponUsedage;
        }

        public void setCouponUsedage(int couponUsedage) {
            this.couponUsedage = couponUsedage;
        }

        public String getCouponLock() {
            return couponLock;
        }

        public void setCouponLock(String couponLock) {
            this.couponLock = couponLock;
        }

        public int getCouponClick() {
            return couponClick;
        }

        public void setCouponClick(int couponClick) {
            this.couponClick = couponClick;
        }

        public String getCouponPrintStyle() {
            return couponPrintStyle;
        }

        public void setCouponPrintStyle(String couponPrintStyle) {
            this.couponPrintStyle = couponPrintStyle;
        }

        public boolean isCouponRecommend() {
            return couponRecommend;
        }

        public void setCouponRecommend(boolean couponRecommend) {
            this.couponRecommend = couponRecommend;
        }

        public boolean isCouponAllowstate() {
            return couponAllowstate;
        }

        public void setCouponAllowstate(boolean couponAllowstate) {
            this.couponAllowstate = couponAllowstate;
        }

        public String getCouponAllowremark() {
            return couponAllowremark;
        }

        public void setCouponAllowremark(String couponAllowremark) {
            this.couponAllowremark = couponAllowremark;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }
    }
}
