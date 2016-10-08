package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/6/1 13:57
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AddRecordParamsModel {
    /**
     * total : 1000
     * memo : 很好
     * received : 500
     * memberId : 80
     * couponList : [{"id":1}]
     * mansongRuleList : [{"id":1}]
     * goodsBeanList : [{"goodsId":286,"goodsPrice":1111,"goodsCount":146,"goodsName":"发送到发送到","goodsInfo":"123456","goodsImage":"123456fsd"}]
     */

    private int total;
    private String memo;
    private int received;
    private String memberId;
    /**
     * id : 1
     */

    private List<CouponListEntity> couponList;
    /**
     * id : 1
     */

    private List<MansongRuleListEntity> mansongRuleList;
    /**
     * goodsId : 286
     * goodsPrice : 1111
     * goodsCount : 146
     * goodsName : 发送到发送到
     * goodsInfo : 123456
     * goodsImage : 123456fsd
     */

    private List<GoodsBeanListEntity> goodsBeanList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getReceived() {
        return received;
    }

    public void setReceived(int received) {
        this.received = received;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public List<CouponListEntity> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponListEntity> couponList) {
        this.couponList = couponList;
    }

    public List<MansongRuleListEntity> getMansongRuleList() {
        return mansongRuleList;
    }

    public void setMansongRuleList(List<MansongRuleListEntity> mansongRuleList) {
        this.mansongRuleList = mansongRuleList;
    }

    public List<GoodsBeanListEntity> getGoodsBeanList() {
        return goodsBeanList;
    }

    public void setGoodsBeanList(List<GoodsBeanListEntity> goodsBeanList) {
        this.goodsBeanList = goodsBeanList;
    }

    public static class CouponListEntity {
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

    public static class GoodsBeanListEntity {
        private int goodsId;
        private double goodsPrice;
        private int goodsCount;
        private String goodsName;
        private String goodsInfo;
        private String goodsImage;

        public GoodsBeanListEntity(){}
        public GoodsBeanListEntity(int goodsId, double goodsPrice, int goodsCount
                , String goodsName, String goodsInfo, String goodsImage){
            this.goodsId = goodsId;
            this.goodsPrice = goodsPrice;
            this.goodsCount = goodsCount;
            this.goodsName = goodsName;
            this.goodsInfo = goodsInfo;
            this.goodsImage = goodsImage;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
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

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
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
}
