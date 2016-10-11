package com.atman.jishang.net.model;

import java.util.List;

/**
 * Created by tangbingliang on 16/10/11.
 */

public class GetWifiListModel {
    /**
     * result : 1
     * body : [{"id":101,"storeId":24,"wifiName":"jjj","wifiPassword":"123456","state":1}]
     */

    private String result;
    /**
     * id : 101
     * storeId : 24
     * wifiName : jjj
     * wifiPassword : 123456
     * state : 1
     */

    private List<BodyBean> body;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        private int id;
        private int storeId;
        private String wifiName;
        private String wifiPassword;
        private int state;

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
