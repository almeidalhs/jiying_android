package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/20 09:26
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AddFullCutResuiltModel {
    /**
     * result : 1
     * body : {"mansongName":"zhoudengwu周年庆55","startTime":1322112803,"endTime":1322112881,"memberId":39,"storeId":17,"memberName":"商友-34","storeName":"zhoudengwu的店","remark":"zhoudengwu周年庆55","mansongRuleBeanList":[{"price":60,"discount":5},{"price":210,"discount":10}]}
     */

    private String result;
    /**
     * mansongName : zhoudengwu周年庆55
     * startTime : 1322112803
     * endTime : 1322112881
     * memberId : 39
     * storeId : 17
     * memberName : 商友-34
     * storeName : zhoudengwu的店
     * remark : zhoudengwu周年庆55
     * mansongRuleBeanList : [{"price":60,"discount":5},{"price":210,"discount":10}]
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
        private String mansongName;
        private int startTime;
        private int endTime;
        private int memberId;
        private int storeId;
        private String memberName;
        private String storeName;
        private String remark;
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        /**
         * price : 60
         * discount : 5
         */



        private List<MansongRuleBeanListEntity> mansongRuleBeanList;

        public String getMansongName() {
            return mansongName;
        }

        public void setMansongName(String mansongName) {
            this.mansongName = mansongName;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<MansongRuleBeanListEntity> getMansongRuleBeanList() {
            return mansongRuleBeanList;
        }

        public void setMansongRuleBeanList(List<MansongRuleBeanListEntity> mansongRuleBeanList) {
            this.mansongRuleBeanList = mansongRuleBeanList;
        }

        public static class MansongRuleBeanListEntity {
            private float price;
            private float discount;

            public float getPrice() {
                return price;
            }

            public void setPrice(float price) {
                this.price = price;
            }

            public float getDiscount() {
                return discount;
            }

            public void setDiscount(float discount) {
                this.discount = discount;
            }
        }
    }
}
