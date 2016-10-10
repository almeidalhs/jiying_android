package com.atman.jishang.net.model;

import java.util.List;

/**
 * Created by tangbingliang on 16/10/10.
 */

public class CommconfModel {
    /**
     * result : 1
     * body : [{"id":212,"moduleId":1,"storeId":12,"parentId":0,"sort":10,"moduleType":0,"moduleStatus":1,"moduleSetup":0,"moduleName":"领取优惠券","moduleDesc":"设置顾客消费后可领取的优惠券面额和规则，促进再次消费","moduleIcon":"http://192.168.1.141:8000/by/Barcode/9d/1e/9d1e_0.png"},{"id":213,"moduleId":2,"storeId":12,"parentId":0,"sort":20,"moduleType":0,"moduleStatus":1,"moduleSetup":0,"moduleName":"我要点餐","moduleIcon":"http://192.168.1.141:8000/by/Barcode/9d/1e/9d1e_0.png"},{"id":214,"moduleId":3,"storeId":12,"parentId":0,"sort":30,"moduleType":0,"moduleStatus":0,"moduleSetup":0,"moduleName":"WIFIi账号/密码","moduleDesc":"填写店内可连接的wifi名称及密码，方便顾客一键连接wifi","moduleIcon":"http://192.168.1.141:8000/by/Barcode/9d/1e/9d1e_0.png"},{"id":215,"moduleId":4,"storeId":12,"parentId":0,"sort":40,"moduleType":0,"moduleStatus":0,"moduleSetup":0,"moduleName":"叫服务员","moduleDesc":"设置店内除商品外可提供的服务类型和名称，优化顾客体验","moduleIcon":"http://192.168.1.141:8000/by/Barcode/9d/1e/9d1e_0.png"},{"id":216,"moduleId":5,"storeId":12,"parentId":0,"sort":50,"moduleType":0,"moduleStatus":0,"moduleSetup":0,"moduleName":"企业介绍","moduleDesc":"设置视频音频或图文内容介绍本店或活动等，方便营销展示","moduleIcon":"http://192.168.1.141:8000/by/Barcode/9d/1e/9d1e_0.png"}]
     */

    private String result;
    /**
     * id : 212
     * moduleId : 1
     * storeId : 12
     * parentId : 0
     * sort : 10
     * moduleType : 0
     * moduleStatus : 1
     * moduleSetup : 0
     * moduleName : 领取优惠券
     * moduleDesc : 设置顾客消费后可领取的优惠券面额和规则，促进再次消费
     * moduleIcon : http://192.168.1.141:8000/by/Barcode/9d/1e/9d1e_0.png
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
        private int moduleId;
        private int storeId;
        private int parentId;
        private int sort;
        private int moduleType;
        private int moduleStatus;
        private int moduleSetup;
        private String moduleName;
        private String moduleDesc;
        private String moduleIcon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getModuleId() {
            return moduleId;
        }

        public void setModuleId(int moduleId) {
            this.moduleId = moduleId;
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

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
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

        public String getModuleDesc() {
            return moduleDesc;
        }

        public void setModuleDesc(String moduleDesc) {
            this.moduleDesc = moduleDesc;
        }

        public String getModuleIcon() {
            return moduleIcon;
        }

        public void setModuleIcon(String moduleIcon) {
            this.moduleIcon = moduleIcon;
        }
    }
}
