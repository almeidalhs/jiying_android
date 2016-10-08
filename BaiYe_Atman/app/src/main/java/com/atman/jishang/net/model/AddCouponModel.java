package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/23 14:42
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AddCouponModel {
    /**
     * result : 1
     * body : {"couponTitle":"现金优惠卷BBB","couponDesc":"ffffff","couponStartDate":1322112801,"couponEndDate":1322112881,"couponPrice":20,"couponLimit":500,"storeId":17,"couponStorage":100,"couponUsage":50,"couponRecommend":true,"shareUrl":"https://www.baidu.com/","userReceiveLimit":2}
     */

    private String result;
    /**
     * couponTitle : 现金优惠卷BBB
     * couponDesc : ffffff
     * couponStartDate : 1322112801
     * couponEndDate : 1322112881
     * couponPrice : 20
     * couponLimit : 500
     * storeId : 17
     * couponStorage : 100
     * couponUsage : 50
     * couponRecommend : true
     * shareUrl : https://www.baidu.com/
     * userReceiveLimit : 2
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
        private String couponTitle;
        private String couponDesc;
        private int couponStartDate;
        private int couponEndDate;
        private int couponPrice;
        private int couponLimit;
        private int storeId;
        private int couponStorage;
        private int couponUsage;
        private boolean couponRecommend;
        private String shareUrl;
        private int userReceiveLimit;
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
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

        public int getCouponStartDate() {
            return couponStartDate;
        }

        public void setCouponStartDate(int couponStartDate) {
            this.couponStartDate = couponStartDate;
        }

        public int getCouponEndDate() {
            return couponEndDate;
        }

        public void setCouponEndDate(int couponEndDate) {
            this.couponEndDate = couponEndDate;
        }

        public int getCouponPrice() {
            return couponPrice;
        }

        public void setCouponPrice(int couponPrice) {
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

        public boolean isCouponRecommend() {
            return couponRecommend;
        }

        public void setCouponRecommend(boolean couponRecommend) {
            this.couponRecommend = couponRecommend;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public int getUserReceiveLimit() {
            return userReceiveLimit;
        }

        public void setUserReceiveLimit(int userReceiveLimit) {
            this.userReceiveLimit = userReceiveLimit;
        }
    }
}
