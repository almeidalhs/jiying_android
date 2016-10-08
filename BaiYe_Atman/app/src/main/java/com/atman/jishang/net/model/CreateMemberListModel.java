package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/27 10:15
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class CreateMemberListModel {
    /**
     * result : 1
     * body : [{"storeId":18,"mobile":"15785236666","cardNumber":"10010000009","name":"aaaatOMTOMTOM","sex":false,"level":"暂未开通","status":0},{"storeId":18,"mobile":"15785236777","cardNumber":"10010000009","name":"bbbbJACAJCAJCAJ","sex":false,"level":"暂未开通","status":0}]
     */

    private String result;
    /**
     * storeId : 18
     * mobile : 15785236666
     * cardNumber : 10010000009
     * name : aaaatOMTOMTOM
     * sex : false
     * level : 暂未开通
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
        private int storeId;
        private String mobile;
        private String cardNumber;
        private String name;
        private boolean sex;
        private String level;
        private int status;

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSex() {
            return sex;
        }

        public void setSex(boolean sex) {
            this.sex = sex;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
