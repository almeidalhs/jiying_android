package com.atman.jishang.net.model;

/**
 * Created by tangbingliang on 16/10/12.
 */

public class CreateQRCodeModel {
    /**
     * result : 1
     * body : {"updateTime":1473325541008,"createTime":1473325540978,"id":34,"storeId":0,"img":"http://192.168.1.141:8000/by/Barcode/1c/d8/1cd8_0.png"}
     */

    private String result;
    /**
     * updateTime : 1473325541008
     * createTime : 1473325540978
     * id : 34
     * storeId : 0
     * img : http://192.168.1.141:8000/by/Barcode/1c/d8/1cd8_0.png
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
        private String img;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
