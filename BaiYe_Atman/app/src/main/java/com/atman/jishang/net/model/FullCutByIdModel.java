package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/20 11:19
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class FullCutByIdModel {
    /**
     * result : 1
     * body : {"id":17,"mansongName":"zhoudengwu周年庆7777","startTime":1322112803,"endTime":1463018784,"memberId":39,"storeId":17,"memberName":"商友-34","storeName":"zhoudengwu的店","state":2,"remark":"zhoudengwu周年庆55","mansongRuleBeanList":[{"id":47,"mansongId":17,"price":100,"discount":10},{"id":48,"mansongId":17,"price":200,"discount":30}]}
     */

    private String result;
    /**
     * id : 17
     * mansongName : zhoudengwu周年庆7777
     * startTime : 1322112803
     * endTime : 1463018784
     * memberId : 39
     * storeId : 17
     * memberName : 商友-34
     * storeName : zhoudengwu的店
     * state : 2
     * remark : zhoudengwu周年庆55
     * mansongRuleBeanList : [{"id":47,"mansongId":17,"price":100,"discount":10},{"id":48,"mansongId":17,"price":200,"discount":30}]
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
        private String mansongName;
        private int startTime;
        private int endTime;
        private int memberId;
        private int storeId;
        private String memberName;
        private String storeName;
        private int state;
        private String remark;
        /**
         * id : 47
         * mansongId : 17
         * price : 100
         * discount : 10
         */

        private List<MansongRuleBeanListEntity> mansongRuleBeanList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
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
            private int id;
            private int mansongId;
            private int price;
            private int discount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMansongId() {
                return mansongId;
            }

            public void setMansongId(int mansongId) {
                this.mansongId = mansongId;
            }

            public String getPrice() {
                return String.valueOf(price);
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getDiscount() {
                return String.valueOf(discount);
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }
        }
    }
}
