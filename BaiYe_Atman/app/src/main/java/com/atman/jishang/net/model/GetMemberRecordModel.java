package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/31 11:19
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GetMemberRecordModel {

    /**
     * result : 1
     * body : [{"id":71,"storeId":18,"title":"消费1","total":967,"received":1569,"memo":"他呀呀","memberId":137,"goodsBeanList":[{"id":106,"smcId":71,"goodsId":212,"goodsName":"tkl","goodsPrice":85,"goodsCount":1,"goodsInfo":"老驴。\ntlu\n\n拖垮吐了","state":0,"goodsImage":"Goods/aa/82/aa8266280d3c11e6b4e074d02ba07f83.jpg"},{"id":107,"smcId":71,"goodsId":210,"goodsName":"途径","goodsPrice":455,"goodsCount":1,"goodsInfo":"地沟油，还以为我哦婆婆哦亏","state":0,"goodsImage":"Goods/85/46/854614b60d3c11e6b4e074d02ba07f83.jpg"},{"id":108,"smcId":71,"goodsId":342,"goodsName":"也设置","goodsPrice":656,"goodsCount":1,"goodsInfo":"你名字了我\n做loll我\n五天摩托\n腾讯偷摸\n\n外婆轻松","state":0,"goodsImage":"Goods/88/88/8888d5be182e11e6bb9474d02ba07f83.jpg"}],"mansongRuleList":[{"id":50,"mansongId":23,"price":222,"discount":111}],"couponList":[{"id":66,"couponDesc":"","couponStartDate":1463995620,"couponEndDate":1464211620,"couponPrice":30,"couponLimit":200,"storeId":18,"couponState":1,"couponStorage":50,"couponUsage":1,"couponUsedage":1,"couponLock":"1","couponClick":1,"couponPrintStyle":"4STYLE","couponRecommend":true,"couponAllowstate":true,"shareUrl":"http://www.atman.com:8000/cp/66","userReceiveLimit":200},{"id":67,"couponDesc":"","couponStartDate":1463996100,"couponEndDate":1464125700,"couponPrice":88,"couponLimit":280,"storeId":18,"couponState":1,"couponStorage":99,"couponUsage":5,"couponUsedage":1,"couponLock":"1","couponClick":1,"couponPrintStyle":"4STYLE","couponRecommend":true,"couponAllowstate":true,"shareUrl":"http://www.atman.com:8000/cp/67","userReceiveLimit":280}],"state":0,"addTime":1464086377}]
     */

    private String result;
    /**
     * id : 71
     * storeId : 18
     * title : 消费1
     * total : 967
     * received : 1569
     * memo : 他呀呀
     * memberId : 137
     * goodsBeanList : [{"id":106,"smcId":71,"goodsId":212,"goodsName":"tkl","goodsPrice":85,"goodsCount":1,"goodsInfo":"老驴。\ntlu\n\n拖垮吐了","state":0,"goodsImage":"Goods/aa/82/aa8266280d3c11e6b4e074d02ba07f83.jpg"},{"id":107,"smcId":71,"goodsId":210,"goodsName":"途径","goodsPrice":455,"goodsCount":1,"goodsInfo":"地沟油，还以为我哦婆婆哦亏","state":0,"goodsImage":"Goods/85/46/854614b60d3c11e6b4e074d02ba07f83.jpg"},{"id":108,"smcId":71,"goodsId":342,"goodsName":"也设置","goodsPrice":656,"goodsCount":1,"goodsInfo":"你名字了我\n做loll我\n五天摩托\n腾讯偷摸\n\n外婆轻松","state":0,"goodsImage":"Goods/88/88/8888d5be182e11e6bb9474d02ba07f83.jpg"}]
     * mansongRuleList : [{"id":50,"mansongId":23,"price":222,"discount":111}]
     * couponList : [{"id":66,"couponDesc":"","couponStartDate":1463995620,"couponEndDate":1464211620,"couponPrice":30,"couponLimit":200,"storeId":18,"couponState":1,"couponStorage":50,"couponUsage":1,"couponUsedage":1,"couponLock":"1","couponClick":1,"couponPrintStyle":"4STYLE","couponRecommend":true,"couponAllowstate":true,"shareUrl":"http://www.atman.com:8000/cp/66","userReceiveLimit":200},{"id":67,"couponDesc":"","couponStartDate":1463996100,"couponEndDate":1464125700,"couponPrice":88,"couponLimit":280,"storeId":18,"couponState":1,"couponStorage":99,"couponUsage":5,"couponUsedage":1,"couponLock":"1","couponClick":1,"couponPrintStyle":"4STYLE","couponRecommend":true,"couponAllowstate":true,"shareUrl":"http://www.atman.com:8000/cp/67","userReceiveLimit":280}]
     * state : 0
     * addTime : 1464086377
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
        private int storeId;
        private String title;
        private float total;
        private long received;
        private String memo;
        private int memberId;
        private int state;
        private long addTime;
        /**
         * id : 106
         * smcId : 71
         * goodsId : 212
         * goodsName : tkl
         * goodsPrice : 85
         * goodsCount : 1
         * goodsInfo : 老驴。
         tlu

         拖垮吐了
         * state : 0
         * goodsImage : Goods/aa/82/aa8266280d3c11e6b4e074d02ba07f83.jpg
         */

        private List<GoodsBeanListEntity> goodsBeanList;
        /**
         * id : 50
         * mansongId : 23
         * price : 222
         * discount : 111
         */

        private List<MansongRuleListEntity> mansongRuleList;
        /**
         * id : 66
         * couponDesc :
         * couponStartDate : 1463995620
         * couponEndDate : 1464211620
         * couponPrice : 30
         * couponLimit : 200
         * storeId : 18
         * couponState : 1
         * couponStorage : 50
         * couponUsage : 1
         * couponUsedage : 1
         * couponLock : 1
         * couponClick : 1
         * couponPrintStyle : 4STYLE
         * couponRecommend : true
         * couponAllowstate : true
         * shareUrl : http://www.atman.com:8000/cp/66
         * userReceiveLimit : 200
         */

        private List<CouponListEntity> couponList;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public float getTotal() {
            return total;
        }

        public void setTotal(float total) {
            this.total = total;
        }

        public long getReceived() {
            return received;
        }

        public void setReceived(long received) {
            this.received = received;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public List<GoodsBeanListEntity> getGoodsBeanList() {
            return goodsBeanList;
        }

        public void setGoodsBeanList(List<GoodsBeanListEntity> goodsBeanList) {
            this.goodsBeanList = goodsBeanList;
        }

        public List<MansongRuleListEntity> getMansongRuleList() {
            return mansongRuleList;
        }

        public void setMansongRuleList(List<MansongRuleListEntity> mansongRuleList) {
            this.mansongRuleList = mansongRuleList;
        }

        public List<CouponListEntity> getCouponList() {
            return couponList;
        }

        public void setCouponList(List<CouponListEntity> couponList) {
            this.couponList = couponList;
        }

        public static class GoodsBeanListEntity {
            private int id;
            private int smcId;
            private int goodsId;
            private String goodsName;
            private double goodsPrice;
            private int goodsCount;
            private String goodsInfo;
            private int state;
            private String goodsImage;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSmcId() {
                return smcId;
            }

            public void setSmcId(int smcId) {
                this.smcId = smcId;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public double getGoodsPrice() {
                return goodsPrice;
            }

            public void setGoodsPrice(double goodsPrice) {
                this.goodsPrice = goodsPrice;
            }

            public int getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(int goodsCount) {
                this.goodsCount = goodsCount;
            }

            public String getGoodsInfo() {
                return goodsInfo;
            }

            public void setGoodsInfo(String goodsInfo) {
                this.goodsInfo = goodsInfo;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getGoodsImage() {
                return goodsImage;
            }

            public void setGoodsImage(String goodsImage) {
                this.goodsImage = goodsImage;
            }
        }

        public static class MansongRuleListEntity {
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

        public static class CouponListEntity {
            private int id;
            private String couponDesc;
            private int couponStartDate;
            private int couponEndDate;
            private int couponPrice;
            private int couponLimit;
            private int storeId;
            private int couponState;
            private int couponStorage;
            private int couponUsage;
            private int couponUsedage;
            private String couponLock;
            private int couponClick;
            private String couponPrintStyle;
            private boolean couponRecommend;
            private boolean couponAllowstate;
            private String shareUrl;
            private int userReceiveLimit;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCouponDesc() {
                return couponDesc;
            }

            public void setCouponDesc(String couponDesc) {
                this.couponDesc = couponDesc;
            }

            public int getCouponStartDate() {
                return couponStartDate;
            }

            public void setCouponStartDate(int couponStartDate) {
                this.couponStartDate = couponStartDate;
            }

            public int getCouponEndDate() {
                return couponEndDate;
            }

            public void setCouponEndDate(int couponEndDate) {
                this.couponEndDate = couponEndDate;
            }

            public int getCouponPrice() {
                return couponPrice;
            }

            public void setCouponPrice(int couponPrice) {
                this.couponPrice = couponPrice;
            }

            public int getCouponLimit() {
                return couponLimit;
            }

            public void setCouponLimit(int couponLimit) {
                this.couponLimit = couponLimit;
            }

            public int getStoreId() {
                return storeId;
            }

            public void setStoreId(int storeId) {
                this.storeId = storeId;
            }

            public int getCouponState() {
                return couponState;
            }

            public void setCouponState(int couponState) {
                this.couponState = couponState;
            }

            public int getCouponStorage() {
                return couponStorage;
            }

            public void setCouponStorage(int couponStorage) {
                this.couponStorage = couponStorage;
            }

            public int getCouponUsage() {
                return couponUsage;
            }

            public void setCouponUsage(int couponUsage) {
                this.couponUsage = couponUsage;
            }

            public int getCouponUsedage() {
                return couponUsedage;
            }

            public void setCouponUsedage(int couponUsedage) {
                this.couponUsedage = couponUsedage;
            }

            public String getCouponLock() {
                return couponLock;
            }

            public void setCouponLock(String couponLock) {
                this.couponLock = couponLock;
            }

            public int getCouponClick() {
                return couponClick;
            }

            public void setCouponClick(int couponClick) {
                this.couponClick = couponClick;
            }

            public String getCouponPrintStyle() {
                return couponPrintStyle;
            }

            public void setCouponPrintStyle(String couponPrintStyle) {
                this.couponPrintStyle = couponPrintStyle;
            }

            public boolean isCouponRecommend() {
                return couponRecommend;
            }

            public void setCouponRecommend(boolean couponRecommend) {
                this.couponRecommend = couponRecommend;
            }

            public boolean isCouponAllowstate() {
                return couponAllowstate;
            }

            public void setCouponAllowstate(boolean couponAllowstate) {
                this.couponAllowstate = couponAllowstate;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public int getUserReceiveLimit() {
                return userReceiveLimit;
            }

            public void setUserReceiveLimit(int userReceiveLimit) {
                this.userReceiveLimit = userReceiveLimit;
            }
        }
    }
}
