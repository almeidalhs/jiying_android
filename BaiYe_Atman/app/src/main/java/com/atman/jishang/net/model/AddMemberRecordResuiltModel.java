package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/6/2 13:49
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AddMemberRecordResuiltModel {
    /**
     * result : 1
     * body : {"storeId":17,"title":"消费24","total":1000,"received":500,"memo":"很好","memberId":80,"addTime":1463951964,"goodsBeanList":[{"smcId":50,"goodsId":286,"goodsName":"发送到发送到","goodsPrice":1111,"goodsCount":146,"goodsInfo":"123456","goodsImage":"123456fsd"}],"mansongRuleList":[{"id":1}],"couponList":[{"id":1,"shareUrl":"1"}]}
     */

    private String result;
    /**
     * storeId : 17
     * title : 消费24
     * total : 1000
     * received : 500
     * memo : 很好
     * memberId : 80
     * addTime : 1463951964
     * goodsBeanList : [{"smcId":50,"goodsId":286,"goodsName":"发送到发送到","goodsPrice":1111,"goodsCount":146,"goodsInfo":"123456","goodsImage":"123456fsd"}]
     * mansongRuleList : [{"id":1}]
     * couponList : [{"id":1,"shareUrl":"1"}]
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
        private int storeId;
        private String title;
//        private double total;
//        private double received;
        private String memo;
        private int memberId;
        private int addTime;
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        /**
         * smcId : 50
         * goodsId : 286
         * goodsName : 发送到发送到
         * goodsPrice : 1111
         * goodsCount : 146
         * goodsInfo : 123456
         * goodsImage : 123456fsd
         */


        private List<GoodsBeanListEntity> goodsBeanList;
        /**
         * id : 1
         */

        private List<MansongRuleListEntity> mansongRuleList;
        /**
         * id : 1
         * shareUrl : 1
         */

        private List<CouponListEntity> couponList;

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

//        public double getTotal() {
//            return total;
//        }
//
//        public void setTotal(double total) {
//            this.total = total;
//        }
//
//        public double getReceived() {
//            return received;
//        }
//
//        public void setReceived(double received) {
//            this.received = received;
//        }

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

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
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
            private int smcId;
            private int goodsId;
            private String goodsName;
            private double goodsPrice;
            private int goodsCount;
            private String goodsInfo;
            private String goodsImage;

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

            public String getGoodsImage() {
                return goodsImage;
            }

            public void setGoodsImage(String goodsImage) {
                this.goodsImage = goodsImage;
            }
        }

        public static class MansongRuleListEntity {
            private int id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public static class CouponListEntity {
            private int id;
            private String shareUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }
        }
    }
}
