package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/20 17:47
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GetCouponListModel {
    /**
     * result : 1
     * body : [{"id":37,"couponTitle":"现金优惠卷","couponDesc":"auto create hahahaha","couponStartDate":1462951900,"couponEndDate":1463951964,"couponPrice":21,"couponLimit":100,"storeId":18,"couponState":2,"couponStorage":100,"couponUsage":9,"couponUsedage":0,"couponLock":"1","couponClick":1,"couponPrintStyle":"4STYLE","couponRecommend":true,"couponAllowstate":true,"shareUrl":"37","userReceiveLimit":2},{"id":38,"couponTitle":"现金优惠卷","couponDesc":"auto create hahahaha","couponStartDate":1462951900,"couponEndDate":1463951964,"couponPrice":22,"couponLimit":100,"storeId":18,"couponState":2,"couponStorage":100,"couponUsage":3,"couponUsedage":0,"couponLock":"1","couponClick":1,"couponPrintStyle":"4STYLE","couponRecommend":true,"couponAllowstate":true,"shareUrl":"38","userReceiveLimit":2},{"id":39,"couponTitle":"现金优惠卷","couponDesc":"auto create hahahaha","couponStartDate":1462951900,"couponEndDate":1463951964,"couponPrice":23,"couponLimit":100,"storeId":18,"couponState":2,"couponStorage":100,"couponUsage":1,"couponUsedage":0,"couponLock":"1","couponClick":1,"couponPrintStyle":"4STYLE","couponRecommend":true,"couponAllowstate":true,"shareUrl":"39","userReceiveLimit":2},{"id":40,"couponTitle":"现金优惠卷","couponDesc":"auto create hahahaha","couponStartDate":1462951900,"couponEndDate":1463951964,"couponPrice":24,"couponLimit":100,"storeId":18,"couponState":2,"couponStorage":100,"couponUsage":0,"couponUsedage":0,"couponLock":"1","couponClick":1,"couponPrintStyle":"4STYLE","couponRecommend":true,"couponAllowstate":true,"shareUrl":"40","userReceiveLimit":2},{"id":41,"couponTitle":"现金优惠卷","couponDesc":"auto create hahahaha","couponStartDate":1462951900,"couponEndDate":1463951964,"couponPrice":25,"couponLimit":100,"storeId":18,"couponState":2,"couponStorage":100,"couponUsage":2,"couponUsedage":0,"couponLock":"1","couponClick":1,"couponPrintStyle":"4STYLE","couponRecommend":true,"couponAllowstate":true,"shareUrl":"41","userReceiveLimit":2},{"id":42,"couponTitle":"现金优惠卷","couponDesc":"auto create hahahaha","couponStartDate":1462951900,"couponEndDate":1463951964,"couponPrice":26,"couponLimit":100,"storeId":18,"couponState":2,"couponStorage":100,"couponUsage":0,"couponUsedage":0,"couponLock":"1","couponClick":1,"couponPrintStyle":"4STYLE","couponRecommend":true,"couponAllowstate":true,"shareUrl":"42","userReceiveLimit":2},{"id":43,"couponTitle":"现金优惠卷","couponDesc":"auto create hahahaha","couponStartDate":1462951900,"couponEndDate":1463951964,"couponPrice":27,"couponLimit":100,"storeId":18,"couponState":2,"couponStorage":100,"couponUsage":0,"couponUsedage":0,"couponLock":"1","couponClick":1,"couponPrintStyle":"4STYLE","couponRecommend":true,"couponAllowstate":true,"shareUrl":"43","userReceiveLimit":2},{"id":44,"couponTitle":"现金优惠卷","couponDesc":"auto create hahahaha","couponStartDate":1462951900,"couponEndDate":1463951964,"couponPrice":28,"couponLimit":100,"storeId":18,"couponState":2,"couponStorage":100,"couponUsage":0,"couponUsedage":0,"couponLock":"1","couponClick":1,"couponPrintStyle":"4STYLE","couponRecommend":true,"couponAllowstate":true,"shareUrl":"44","userReceiveLimit":2},{"id":45,"couponTitle":"现金优惠卷","couponDesc":"auto create hahahaha","couponStartDate":1462951900,"couponEndDate":1463951964,"couponPrice":29,"couponLimit":100,"storeId":18,"couponState":2,"couponStorage":100,"couponUsage":0,"couponUsedage":0,"couponLock":"1","couponClick":1,"couponPrintStyle":"4STYLE","couponRecommend":true,"couponAllowstate":true,"shareUrl":"45","userReceiveLimit":2}]
     */

    private String result;
    /**
     * id : 37
     * couponTitle : 现金优惠卷
     * couponDesc : auto create hahahaha
     * couponStartDate : 1462951900
     * couponEndDate : 1463951964
     * couponPrice : 21
     * couponLimit : 100
     * storeId : 18
     * couponState : 2
     * couponStorage : 100
     * couponUsage : 9
     * couponUsedage : 0
     * couponLock : 1
     * couponClick : 1
     * couponPrintStyle : 4STYLE
     * couponRecommend : true
     * couponAllowstate : true
     * shareUrl : 37
     * userReceiveLimit : 2
     */

    private List<BodyEntity> body;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<BodyEntity> getBody() {
        return body;
    }

    public void setBody(List<BodyEntity> body) {
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
        private String shareUrl;
        private int userReceiveLimit;

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
