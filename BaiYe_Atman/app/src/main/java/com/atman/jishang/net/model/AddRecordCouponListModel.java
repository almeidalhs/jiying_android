package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/6/1 17:45
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AddRecordCouponListModel {
    /**
     * result : 1
     * body : [{"id":20,"couponTitle":"现金优惠卷","couponDesc":"auto create hahahaha","couponStartDate":1462951900,"couponEndDate":1463951964,"couponPrice":22,"couponLimit":100,"storeId":17,"couponState":2,"couponStorage":100,"couponUsage":3,"couponUsedage":0,"couponLock":"1","couponClick":1,"couponPrintStyle":"4STYLE","couponRecommend":true,"couponAllowstate":true,"shareUrl":"http://www.atman.com:8000/cp/20","userReceiveLimit":2}]
     */

    private String result;
    /**
     * id : 20
     * couponTitle : 现金优惠卷
     * couponDesc : auto create hahahaha
     * couponStartDate : 1462951900
     * couponEndDate : 1463951964
     * couponPrice : 22
     * couponLimit : 100
     * storeId : 17
     * couponState : 2
     * couponStorage : 100
     * couponUsage : 3
     * couponUsedage : 0
     * couponLock : 1
     * couponClick : 1
     * couponPrintStyle : 4STYLE
     * couponRecommend : true
     * couponAllowstate : true
     * shareUrl : http://www.atman.com:8000/cp/20
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
        private int couponStartDate;
        private int couponEndDate;
        private int couponPrice;
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
        private boolean select;
        private boolean isUserd;

        public boolean isUserd() {
            return isUserd;
        }

        public void setUserd(boolean userd) {
            isUserd = userd;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
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
