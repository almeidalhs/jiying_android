package com.atman.jishang.net.model;

/**
 * Created by tangbingliang on 16/10/11.
 */

public class AddWifiModel {
    /**
     * result : 1
     * body : {"updateTime":1473394844082,"createTime":1473394844081,"id":8,"storeId":45,"wifiName":"wifi1235","wifiPassword":"123456","state":1}
     */

    private String result;
    /**
     * updateTime : 1473394844082
     * createTime : 1473394844081
     * id : 8
     * storeId : 45
     * wifiName : wifi1235
     * wifiPassword : 123456
     * state : 1
     */

    private BodyBean body;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        private long updateTime;
        private long createTime;
        private int id;
        private int storeId;
        private String wifiName;
        private String wifiPassword;
        private int state;

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

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

        public String getWifiName() {
            return wifiName;
        }

        public void setWifiName(String wifiName) {
            this.wifiName = wifiName;
        }

        public String getWifiPassword() {
            return wifiPassword;
        }

        public void setWifiPassword(String wifiPassword) {
            this.wifiPassword = wifiPassword;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
