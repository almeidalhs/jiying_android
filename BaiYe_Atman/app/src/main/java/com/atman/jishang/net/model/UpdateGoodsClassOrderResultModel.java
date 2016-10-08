package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/28 10:51
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class UpdateGoodsClassOrderResultModel {

    /**
     * result : 1
     * body : {"id":70,"stcName":"10101","stcParentId":0,"stcState":1,"storeId":2,"stcSort":1}
     */

    private String result;
    /**
     * id : 70
     * stcName : 10101
     * stcParentId : 0
     * stcState : 1
     * storeId : 2
     * stcSort : 1
     */

    private BodyEntity body;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public BodyEntity getBody() {
        return body;
    }

    public void setBody(BodyEntity body) {
        this.body = body;
    }

    public static class BodyEntity {
        private int id;
        private String stcName;
        private int stcParentId;
        private int stcState;
        private int storeId;
        private int stcSort;
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStcName() {
            return stcName;
        }

        public void setStcName(String stcName) {
            this.stcName = stcName;
        }

        public int getStcParentId() {
            return stcParentId;
        }

        public void setStcParentId(int stcParentId) {
            this.stcParentId = stcParentId;
        }

        public int getStcState() {
            return stcState;
        }

        public void setStcState(int stcState) {
            this.stcState = stcState;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStcSort() {
            return stcSort;
        }

        public void setStcSort(int stcSort) {
            this.stcSort = stcSort;
        }
    }
}
