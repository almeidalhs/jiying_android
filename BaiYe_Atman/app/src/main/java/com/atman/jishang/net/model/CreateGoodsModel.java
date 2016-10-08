package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/3 15:54
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class CreateGoodsModel {
    /**
     * result : 1
     * body : {"id":370,"goodsName":"测","storeId":18,"goodsImage":"Goods/70/8e/708eb4c0224f11e68b8f74d02ba07f83.jpg","fullGoodsImage":"http://192.168.1.141:8000/by/Goods/70/8e/708eb4c0224f11e68b8f74d02ba07f83.jpg","goodsImageMore":"Goods/70/8e/708eb4c0224f11e68b8f74d02ba07f83.jpg","goodsStorePrice":0,"goodsShow":1,"goodsDescription":"","goodsBody":"","goodsSpec":"","stcId":158,"price":66,"storage":0,"goodsWebUrl":"http://www.atman.com:8000/cp/370","goodsImageMoreList":["Goods/70/8e/708eb4c0224f11e68b8f74d02ba07f83.jpg"],"goodsImageUrlPre":""}
     */

    private String result;
    /**
     * id : 370
     * goodsName : 测
     * storeId : 18
     * goodsImage : Goods/70/8e/708eb4c0224f11e68b8f74d02ba07f83.jpg
     * fullGoodsImage : http://192.168.1.141:8000/by/Goods/70/8e/708eb4c0224f11e68b8f74d02ba07f83.jpg
     * goodsImageMore : Goods/70/8e/708eb4c0224f11e68b8f74d02ba07f83.jpg
     * goodsStorePrice : 0
     * goodsShow : 1
     * goodsDescription :
     * goodsBody :
     * goodsSpec :
     * stcId : 158
     * price : 66
     * storage : 0
     * goodsWebUrl : http://www.atman.com:8000/cp/370
     * goodsImageMoreList : ["Goods/70/8e/708eb4c0224f11e68b8f74d02ba07f83.jpg"]
     * goodsImageUrlPre :
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
        private String goodsName;
        private int storeId;
        private String goodsImage;
        private String fullGoodsImage;
        private String goodsImageMore;
        private double goodsStorePrice;
        private int goodsShow;
        private String goodsDescription;
        private String goodsBody;
        private String goodsSpec;
        private int stcId;
        private double price;
        private int storage;
        private String goodsWebUrl;
        private String goodsImageUrlPre;
        private List<String> goodsImageMoreList;
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

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getGoodsImage() {
            return goodsImage;
        }

        public void setGoodsImage(String goodsImage) {
            this.goodsImage = goodsImage;
        }

        public String getFullGoodsImage() {
            return fullGoodsImage;
        }

        public void setFullGoodsImage(String fullGoodsImage) {
            this.fullGoodsImage = fullGoodsImage;
        }

        public String getGoodsImageMore() {
            return goodsImageMore;
        }

        public void setGoodsImageMore(String goodsImageMore) {
            this.goodsImageMore = goodsImageMore;
        }

        public double getGoodsStorePrice() {
            return goodsStorePrice;
        }

        public void setGoodsStorePrice(double goodsStorePrice) {
            this.goodsStorePrice = goodsStorePrice;
        }

        public int getGoodsShow() {
            return goodsShow;
        }

        public void setGoodsShow(int goodsShow) {
            this.goodsShow = goodsShow;
        }

        public String getGoodsDescription() {
            return goodsDescription;
        }

        public void setGoodsDescription(String goodsDescription) {
            this.goodsDescription = goodsDescription;
        }

        public String getGoodsBody() {
            return goodsBody;
        }

        public void setGoodsBody(String goodsBody) {
            this.goodsBody = goodsBody;
        }

        public String getGoodsSpec() {
            return goodsSpec;
        }

        public void setGoodsSpec(String goodsSpec) {
            this.goodsSpec = goodsSpec;
        }

        public int getStcId() {
            return stcId;
        }

        public void setStcId(int stcId) {
            this.stcId = stcId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStorage() {
            return storage;
        }

        public void setStorage(int storage) {
            this.storage = storage;
        }

        public String getGoodsWebUrl() {
            return goodsWebUrl;
        }

        public void setGoodsWebUrl(String goodsWebUrl) {
            this.goodsWebUrl = goodsWebUrl;
        }

        public String getGoodsImageUrlPre() {
            return goodsImageUrlPre;
        }

        public void setGoodsImageUrlPre(String goodsImageUrlPre) {
            this.goodsImageUrlPre = goodsImageUrlPre;
        }

        public List<String> getGoodsImageMoreList() {
            return goodsImageMoreList;
        }

        public void setGoodsImageMoreList(List<String> goodsImageMoreList) {
            this.goodsImageMoreList = goodsImageMoreList;
        }
    }
}
