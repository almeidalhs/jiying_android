package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/17 15:42
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GetFullCutListModel {
    /**
     * result : 1
     * body : [{"id":17,"mansongName":"zhoudengwu周年庆7777","startTime":1463951964,"endTime":1463952964,"memberId":39,"storeId":17,"memberName":"商友-34","storeName":"zhoudengwu的店","state":1,"remark":"zhoudengwu周年庆55","mansongRuleBeanList":[{"id":51,"mansongId":17,"price":100,"discount":10},{"id":52,"mansongId":17,"price":200,"discount":30}]},{"id":18,"mansongName":"zhoudengwu周年庆666","startTime":1462950036,"endTime":1462954436,"memberId":39,"storeId":17,"memberName":"商友-34","storeName":"zhoudengwu的店","state":3,"remark":"zhoudengwu周年庆66","mansongRuleBeanList":[{"id":39,"mansongId":18,"price":60,"discount":5},{"id":40,"mansongId":18,"price":210,"discount":10}]},{"id":10,"mansongName":"zhoudengwu周年庆2","quotaId":17,"startTime":1322112801,"endTime":1322112881,"memberId":39,"storeId":17,"memberName":"132","storeName":"zhoudengwu的店","state":3,"remark":"zhoudengwu周年庆2","mansongRuleBeanList":[{"id":5,"mansongId":10,"price":50,"discount":5}]},{"id":16,"mansongName":"zhoudengwu周年庆2222","quotaId":17,"startTime":1322112803,"endTime":1322112881,"memberId":39,"storeId":17,"memberName":"商友-34","storeName":"zhoudengwu的店","state":3,"remark":"zhoudengwu周年庆2222","mansongRuleBeanList":[{"id":26,"mansongId":16,"price":60,"discount":5},{"id":27,"mansongId":16,"price":210,"discount":10},{"id":28,"mansongId":16,"price":300,"discount":20}]},{"id":9,"mansongName":"zhoudengwu周年庆","quotaId":17,"startTime":1322112701,"endTime":1322112781,"memberId":39,"storeId":17,"memberName":"132","storeName":"zhoudengwu的店","state":3,"remark":"zhoudengwu周年庆","mansongRuleBeanList":[{"id":3,"mansongId":9,"price":100,"discount":5},{"id":4,"mansongId":9,"price":200,"discount":20}]}]
     */

    private String result;
    /**
     * id : 17
     * mansongName : zhoudengwu周年庆7777
     * startTime : 1463951964
     * endTime : 1463952964
     * memberId : 39
     * storeId : 17
     * memberName : 商友-34
     * storeName : zhoudengwu的店
     * state : 1
     * remark : zhoudengwu周年庆55
     * mansongRuleBeanList : [{"id":51,"mansongId":17,"price":100,"discount":10},{"id":52,"mansongId":17,"price":200,"discount":30}]
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
        private int id;
        private String mansongName;
        private long startTime;
        private long endTime;
        private int memberId;
        private int storeId;
        private String memberName;
        private String storeName;
        private int state;
        private String remark;
        /**
         * id : 51
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

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
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

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }
        }
    }
}
