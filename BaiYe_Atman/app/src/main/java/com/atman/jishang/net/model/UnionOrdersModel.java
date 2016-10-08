package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/6/24 15:13
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class UnionOrdersModel {
    /**
     * result : 1
     * body : {"id":264,"orderId":"182,183,184","receiptUserName":"汪洋","locationId":340827,"receiptAddress":"第二中学小汪店铺","mobile":"18616348991","orderGoodsPrice":363,"orderState":1,"createTime":1458293689000,"isValid":1,"orderType":1,"remark":"18616348991","out_trade_no":"182,183,184","total_fee":363,"orderStateName":"已提交","orderGoodses":[{"id":2310900,"goodsId":11306,"goodsName":"海尔对开门冰箱BCD-6901018053723","buyerId":10128,"price":12,"goodsCount":1,"goodsTotalPrice":12,"createTime":1458293691000,"updateTime":1458293691000,"goodsImages":["http://192.168.1.183:8000/imageServer/D073f488b04a5e684b07ba27fb118604316e.jpg","http://192.168.1.183:8000/imageServer/91844c72cde7730d4ce19e5da4fe9a86c481.jpg","http://192.168.1.183:8000/imageServer/D65Ab63e6d58292548f2b2b1cac05b707546.jpg"]},{"id":2309900,"goodsId":11306,"goodsName":"海尔对开门冰箱BCD-6901018053723","buyerId":10128,"price":12,"goodsCount":1,"goodsTotalPrice":12,"createTime":1458293689000,"updateTime":1458293689000,"goodsImages":["http://192.168.1.183:8000/imageServer/D073f488b04a5e684b07ba27fb118604316e.jpg","http://192.168.1.183:8000/imageServer/91844c72cde7730d4ce19e5da4fe9a86c481.jpg","http://192.168.1.183:8000/imageServer/D65Ab63e6d58292548f2b2b1cac05b707546.jpg"]},{"id":2308000,"goodsId":12020,"goodsName":"零售：自动化脚本_195252","buyerId":10128,"price":339,"goodsCount":1,"goodsTotalPrice":339,"createTime":1458206982000,"updateTime":1458206982000,"goodsImages":["http://192.168.1.183:8000/imageServer/C7298394ba08d5294de6baa4b1d01bcdabda.jpg"]}]}
     */

    private String result;
    /**
     * id : 264
     * orderId : 182,183,184
     * receiptUserName : 汪洋
     * locationId : 340827
     * receiptAddress : 第二中学小汪店铺
     * mobile : 18616348991
     * orderGoodsPrice : 363
     * orderState : 1
     * createTime : 1458293689000
     * isValid : 1
     * orderType : 1
     * remark : 18616348991
     * out_trade_no : 182,183,184
     * total_fee : 363
     * orderStateName : 已提交
     * orderGoodses : [{"id":2310900,"goodsId":11306,"goodsName":"海尔对开门冰箱BCD-6901018053723","buyerId":10128,"price":12,"goodsCount":1,"goodsTotalPrice":12,"createTime":1458293691000,"updateTime":1458293691000,"goodsImages":["http://192.168.1.183:8000/imageServer/D073f488b04a5e684b07ba27fb118604316e.jpg","http://192.168.1.183:8000/imageServer/91844c72cde7730d4ce19e5da4fe9a86c481.jpg","http://192.168.1.183:8000/imageServer/D65Ab63e6d58292548f2b2b1cac05b707546.jpg"]},{"id":2309900,"goodsId":11306,"goodsName":"海尔对开门冰箱BCD-6901018053723","buyerId":10128,"price":12,"goodsCount":1,"goodsTotalPrice":12,"createTime":1458293689000,"updateTime":1458293689000,"goodsImages":["http://192.168.1.183:8000/imageServer/D073f488b04a5e684b07ba27fb118604316e.jpg","http://192.168.1.183:8000/imageServer/91844c72cde7730d4ce19e5da4fe9a86c481.jpg","http://192.168.1.183:8000/imageServer/D65Ab63e6d58292548f2b2b1cac05b707546.jpg"]},{"id":2308000,"goodsId":12020,"goodsName":"零售：自动化脚本_195252","buyerId":10128,"price":339,"goodsCount":1,"goodsTotalPrice":339,"createTime":1458206982000,"updateTime":1458206982000,"goodsImages":["http://192.168.1.183:8000/imageServer/C7298394ba08d5294de6baa4b1d01bcdabda.jpg"]}]
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
        private String orderId;
        private String receiptUserName;
        private int locationId;
        private String receiptAddress;
        private String mobile;
        private int orderGoodsPrice;
        private int orderState;
        private long createTime;
        private int isValid;
        private int orderType;
        private String remark;
        private String out_trade_no;
        private int total_fee;
        private String orderStateName;
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        /**
         * id : 2310900
         * goodsId : 11306
         * goodsName : 海尔对开门冰箱BCD-6901018053723
         * buyerId : 10128
         * price : 12
         * goodsCount : 1
         * goodsTotalPrice : 12
         * createTime : 1458293691000
         * updateTime : 1458293691000
         * goodsImages : ["http://192.168.1.183:8000/imageServer/D073f488b04a5e684b07ba27fb118604316e.jpg","http://192.168.1.183:8000/imageServer/91844c72cde7730d4ce19e5da4fe9a86c481.jpg","http://192.168.1.183:8000/imageServer/D65Ab63e6d58292548f2b2b1cac05b707546.jpg"]
         */

        private List<OrderGoodsesEntity> orderGoodses;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getReceiptUserName() {
            return receiptUserName;
        }

        public void setReceiptUserName(String receiptUserName) {
            this.receiptUserName = receiptUserName;
        }

        public int getLocationId() {
            return locationId;
        }

        public void setLocationId(int locationId) {
            this.locationId = locationId;
        }

        public String getReceiptAddress() {
            return receiptAddress;
        }

        public void setReceiptAddress(String receiptAddress) {
            this.receiptAddress = receiptAddress;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getOrderGoodsPrice() {
            return orderGoodsPrice;
        }

        public void setOrderGoodsPrice(int orderGoodsPrice) {
            this.orderGoodsPrice = orderGoodsPrice;
        }

        public int getOrderState() {
            return orderState;
        }

        public void setOrderState(int orderState) {
            this.orderState = orderState;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getIsValid() {
            return isValid;
        }

        public void setIsValid(int isValid) {
            this.isValid = isValid;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public int getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(int total_fee) {
            this.total_fee = total_fee;
        }

        public String getOrderStateName() {
            return orderStateName;
        }

        public void setOrderStateName(String orderStateName) {
            this.orderStateName = orderStateName;
        }

        public List<OrderGoodsesEntity> getOrderGoodses() {
            return orderGoodses;
        }

        public void setOrderGoodses(List<OrderGoodsesEntity> orderGoodses) {
            this.orderGoodses = orderGoodses;
        }

        public static class OrderGoodsesEntity {
            private int id;
            private int goodsId;
            private String goodsName;
            private int buyerId;
            private int price;
            private int goodsCount;
            private int goodsTotalPrice;
            private long createTime;
            private long updateTime;
            private List<String> goodsImages;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getBuyerId() {
                return buyerId;
            }

            public void setBuyerId(int buyerId) {
                this.buyerId = buyerId;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(int goodsCount) {
                this.goodsCount = goodsCount;
            }

            public int getGoodsTotalPrice() {
                return goodsTotalPrice;
            }

            public void setGoodsTotalPrice(int goodsTotalPrice) {
                this.goodsTotalPrice = goodsTotalPrice;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public List<String> getGoodsImages() {
                return goodsImages;
            }

            public void setGoodsImages(List<String> goodsImages) {
                this.goodsImages = goodsImages;
            }
        }
    }
}
