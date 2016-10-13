package com.atman.jishang.net.model;

/**
 * Created by tangbingliang on 16/10/13.
 */

public class EditCallServiceModel {
    /**
     * result : 1
     * body : {"updateTime":1473665590264,"createTime":1473665590264,"id":239,"storeId":14,"parentId":4,"moduleType":1,"moduleName":"加菜","moduleDesc":"客人需要加菜！","qrcodeName":"selfdefined","moduleClassify":6}
     */

    private String result;
    /**
     * updateTime : 1473665590264
     * createTime : 1473665590264
     * id : 239
     * storeId : 14
     * parentId : 4
     * moduleType : 1
     * moduleName : 加菜
     * moduleDesc : 客人需要加菜！
     * qrcodeName : selfdefined
     * moduleClassify : 6
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
        private int parentId;
        private int moduleType;
        private String moduleName;
        private String moduleDesc;
        private String qrcodeName;
        private int moduleClassify;

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
    }
}
