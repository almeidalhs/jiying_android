package com.atman.jishang.net.model;

/**
 * Created by tangbingliang on 16/10/11.
 */

public class EditWifiModel {
    /**
     * result : 1
     * body : {"wifiName":"wifi123444","wifiPassword":"123456"}
     */

    private String result;
    /**
     * wifiName : wifi123444
     * wifiPassword : 123456
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
        private String wifiName;
        private String wifiPassword;

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
    }
}
