package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/24 13:24
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class CouponRecordsModel {
    /**
     * result : 1
     * body : [{"id":3,"storeId":17,"storeName":"zhoudengwu的店","couponId":6,"phone":"15267891234","resource":1,"addTime":1322112881,"status":0}]
     */

    private String result;
    /**
     * id : 3
     * storeId : 17
     * storeName : zhoudengwu的店
     * couponId : 6
     * phone : 15267891234
     * resource : 1
     * addTime : 1322112881
     * status : 0
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
        private int storeId;
        private String storeName;
        private int couponId;
        private String phone;
        private int resource;
        private long addTime;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getResource() {
            return resource;
        }

        public void setResource(int resource) {
            this.resource = resource;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
