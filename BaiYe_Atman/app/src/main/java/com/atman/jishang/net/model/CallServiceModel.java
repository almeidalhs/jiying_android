package com.atman.jishang.net.model;

import java.util.List;

/**
 * Created by tangbingliang on 16/10/13.
 */

public class CallServiceModel {
    /**
     * result : 1
     * body : [{"id":239,"storeId":14,"parentId":4,"moduleType":1,"moduleStatus":0,"moduleSetup":0,"moduleName":"加菜","qrcodeName":"selfdefined","moduleClassify":6,"moduleDesc":"客人需要加菜！"},{"id":245,"moduleId":6,"storeId":14,"parentId":4,"sort":41,"moduleType":0,"moduleStatus":0,"moduleSetup":0,"moduleName":"催促上菜","moduleClassify":0},{"id":246,"moduleId":7,"storeId":14,"parentId":4,"sort":42,"moduleType":0,"moduleStatus":0,"moduleSetup":0,"moduleName":"添加茶水","moduleClassify":0},{"id":247,"moduleId":8,"storeId":14,"parentId":4,"sort":43,"moduleType":0,"moduleStatus":0,"moduleSetup":0,"moduleName":"面纸毛巾","moduleClassify":0},{"id":248,"moduleId":9,"storeId":14,"parentId":4,"sort":44,"moduleType":0,"moduleStatus":0,"moduleSetup":0,"moduleName":"空调调节","moduleClassify":0},{"id":249,"moduleId":10,"storeId":14,"parentId":4,"sort":45,"moduleType":0,"moduleStatus":0,"moduleSetup":0,"moduleName":"现金买单","moduleClassify":0},{"id":250,"moduleId":11,"storeId":14,"parentId":4,"sort":46,"moduleType":0,"moduleStatus":0,"moduleSetup":0,"moduleName":"刷卡买单","moduleClassify":0},{"id":251,"moduleId":12,"storeId":14,"parentId":4,"sort":47,"moduleType":0,"moduleStatus":0,"moduleSetup":0,"moduleName":"线上买单","moduleClassify":0}]
     */

    private String result;
    /**
     * id : 239
     * storeId : 14
     * parentId : 4
     * moduleType : 1
     * moduleStatus : 0
     * moduleSetup : 0
     * moduleName : 加菜
     * qrcodeName : selfdefined
     * moduleClassify : 6
     * moduleDesc : 客人需要加菜！
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
        private int parentId;
        private int moduleType;
        private int moduleStatus;
        private int moduleSetup;
        private String moduleName;
        private String qrcodeName;
        private int moduleClassify;
        private String moduleDesc;

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

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getModuleType() {
            return moduleType;
        }

        public void setModuleType(int moduleType) {
            this.moduleType = moduleType;
        }

        public int getModuleStatus() {
            return moduleStatus;
        }

        public void setModuleStatus(int moduleStatus) {
            this.moduleStatus = moduleStatus;
        }

        public int getModuleSetup() {
            return moduleSetup;
        }

        public void setModuleSetup(int moduleSetup) {
            this.moduleSetup = moduleSetup;
        }

        public String getModuleName() {
            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }

        public String getQrcodeName() {
            return qrcodeName;
        }

        public void setQrcodeName(String qrcodeName) {
            this.qrcodeName = qrcodeName;
        }

        public int getModuleClassify() {
            return moduleClassify;
        }

        public void setModuleClassify(int moduleClassify) {
            this.moduleClassify = moduleClassify;
        }

        public String getModuleDesc() {
            return moduleDesc;
        }

        public void setModuleDesc(String moduleDesc) {
            this.moduleDesc = moduleDesc;
        }
    }
}
