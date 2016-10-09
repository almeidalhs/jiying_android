package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述 分类下的商品
 * 作者 tangbingliang
 * 时间 16/4/25 14:07
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GetGoodsByClassIdModel {
    /**
     * result : 1
     * body : [{"id":160,"goodsName":"更新","gcName":"","brandId":0,"typeId":0,"storeId":11,"specOpen":0,"specName":"","goodsImage":"Goods/e0/4b/e04bc06306ca11e6a5c874d02ba07f83.jpg","fullGoodsImage":"http://192.168.1.141:8000/by/Goods/e0/4b/e04bc06306ca11e6a5c874d02ba07f83.jpg","goodsStorePrice":0,"goodsStorePriceInterval":"","goodsSerial":"","goodsShow":1,"goodsClick":1,"goodsState":0,"goodsCommend":0,"goodsAddTime":0,"goodsKeywords":"","goodsDescription":"","goodsBody":"","goodsStartTime":0,"goodsEndTime":0,"goodsForm":1,"transportId":0,"pyPrice":0,"kdPrice":0,"esPrice":0,"cityId":0,"provinceId":0,"goodsStoreState":0,"commentnum":0,"salenum":0,"goodsCollect":0,"goodsGoldNum":0,"goodsIsztc":0,"goodsZtcState":1,"goodsZtcStartDate":0,"groupFlag":0,"groupPrice":0,"xianshiFlag":0,"xianshiDiscount":0,"goodsTransfeeCharge":0,"stcId":125,"price":79,"storage":0,"goodsSpecBeanList":[{"id":242,"goodsId":160,"specGoodsPrice":79,"specGoodsStorage":0,"specSalenum":0}],"goodsImageUrlPre":""},{"id":159,"goodsName":"同事","gcName":"","brandId":0,"typeId":0,"storeId":11,"specOpen":0,"specName":"","goodsImage":"Goods/d7/51/d7512ae206ca11e6a5c874d02ba07f83.jpg","fullGoodsImage":"http://192.168.1.141:8000/by/Goods/d7/51/d7512ae206ca11e6a5c874d02ba07f83.jpg","goodsStorePrice":0,"goodsStorePriceInterval":"","goodsSerial":"","goodsShow":1,"goodsClick":1,"goodsState":0,"goodsCommend":0,"goodsAddTime":0,"goodsKeywords":"","goodsDescription":"","goodsBody":"","goodsStartTime":0,"goodsEndTime":0,"goodsForm":1,"transportId":0,"pyPrice":0,"kdPrice":0,"esPrice":0,"cityId":0,"provinceId":0,"goodsStoreState":0,"commentnum":0,"salenum":0,"goodsCollect":0,"goodsGoldNum":0,"goodsIsztc":0,"goodsZtcState":1,"goodsZtcStartDate":0,"groupFlag":0,"groupPrice":0,"xianshiFlag":0,"xianshiDiscount":0,"goodsTransfeeCharge":0,"stcId":125,"price":9797,"storage":0,"goodsSpecBeanList":[{"id":241,"goodsId":159,"specGoodsPrice":9797,"specGoodsStorage":0,"specSalenum":0}],"goodsImageUrlPre":""}]
     */

    private String result;
    /**
     * id : 160
     * goodsName : 更新
     * gcName :
     * brandId : 0
     * typeId : 0
     * storeId : 11
     * specOpen : 0
     * specName :
     * goodsImage : Goods/e0/4b/e04bc06306ca11e6a5c874d02ba07f83.jpg
     * fullGoodsImage : http://192.168.1.141:8000/by/Goods/e0/4b/e04bc06306ca11e6a5c874d02ba07f83.jpg
     * goodsStorePrice : 0
     * goodsStorePriceInterval :
     * goodsSerial :
     * goodsShow : 1
     * goodsClick : 1
     * goodsState : 0
     * goodsCommend : 0
     * goodsAddTime : 0
     * goodsKeywords :
     * goodsDescription :
     * goodsBody :
     * goodsStartTime : 0
     * goodsEndTime : 0
     * goodsForm : 1
     * transportId : 0
     * pyPrice : 0
     * kdPrice : 0
     * esPrice : 0
     * cityId : 0
     * provinceId : 0
     * goodsStoreState : 0
     * commentnum : 0
     * salenum : 0
     * goodsCollect : 0
     * goodsGoldNum : 0
     * goodsIsztc : 0
     * goodsZtcState : 1
     * goodsZtcStartDate : 0
     * groupFlag : 0
     * groupPrice : 0
     * xianshiFlag : 0
     * xianshiDiscount : 0
     * goodsTransfeeCharge : 0
     * stcId : 125
     * price : 79
     * storage : 0
     * goodsSpecBeanList : [{"id":242,"goodsId":160,"specGoodsPrice":79,"specGoodsStorage":0,"specSalenum":0}]
     * goodsImageUrlPre :
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
        private long id;
        private String goodsName;
        private String gcName;
        private int brandId;
        private int typeId;
        private int storeId;
        private int specOpen;
        private String specName;
        private String goodsImage;
        private String fullGoodsImage;
        private float goodsStorePrice;
        private String goodsStorePriceInterval;
        private String goodsSerial;
        private int goodsShow;
        private int goodsClick;
        private int goodsState;
        private int goodsCommend;
        private int goodsAddTime;
        private String goodsKeywords;
        private String goodsDescription;
        private String goodsBody;
        private int goodsStartTime;
        private int goodsEndTime;
        private int goodsForm;
        private int transportId;
        private float pyPrice;
        private float kdPrice;
        private float esPrice;
        private int cityId;
        private int provinceId;
        private int goodsStoreState;
        private int commentnum;
        private int salenum;
        private int goodsCollect;
        private int goodsGoldNum;
        private int goodsIsztc;
        private int goodsZtcState;
        private int goodsZtcStartDate;
        private int groupFlag;
        private float groupPrice;
        private int xianshiFlag;
        private float xianshiDiscount;
        private int goodsTransfeeCharge;
        private int stcId;
        private double price;
        private int storage;
        private String goodsImageUrlPre;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        /**
         * id : 242
         * goodsId : 160
         * specGoodsPrice : 79
         * specGoodsStorage : 0
         * specSalenum : 0
         */



        private List<GoodsSpecBeanListEntity> goodsSpecBeanList;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGcName() {
            return gcName;
        }

        public void setGcName(String gcName) {
            this.gcName = gcName;
        }

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getSpecOpen() {
            return specOpen;
        }

        public void setSpecOpen(int specOpen) {
            this.specOpen = specOpen;
        }

        public String getSpecName() {
            return specName;
        }

        public void setSpecName(String specName) {
            this.specName = specName;
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

        public float getGoodsStorePrice() {
            return goodsStorePrice;
        }

        public void setGoodsStorePrice(float goodsStorePrice) {
            this.goodsStorePrice = goodsStorePrice;
        }

        public String getGoodsStorePriceInterval() {
            return goodsStorePriceInterval;
        }

        public void setGoodsStorePriceInterval(String goodsStorePriceInterval) {
            this.goodsStorePriceInterval = goodsStorePriceInterval;
        }

        public String getGoodsSerial() {
            return goodsSerial;
        }

        public void setGoodsSerial(String goodsSerial) {
            this.goodsSerial = goodsSerial;
        }

        public int getGoodsShow() {
            return goodsShow;
        }

        public void setGoodsShow(int goodsShow) {
            this.goodsShow = goodsShow;
        }

        public int getGoodsClick() {
            return goodsClick;
        }

        public void setGoodsClick(int goodsClick) {
            this.goodsClick = goodsClick;
        }

        public int getGoodsState() {
            return goodsState;
        }

        public void setGoodsState(int goodsState) {
            this.goodsState = goodsState;
        }

        public int getGoodsCommend() {
            return goodsCommend;
        }

        public void setGoodsCommend(int goodsCommend) {
            this.goodsCommend = goodsCommend;
        }

        public int getGoodsAddTime() {
            return goodsAddTime;
        }

        public void setGoodsAddTime(int goodsAddTime) {
            this.goodsAddTime = goodsAddTime;
        }

        public String getGoodsKeywords() {
            return goodsKeywords;
        }

        public void setGoodsKeywords(String goodsKeywords) {
            this.goodsKeywords = goodsKeywords;
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

        public int getGoodsStartTime() {
            return goodsStartTime;
        }

        public void setGoodsStartTime(int goodsStartTime) {
            this.goodsStartTime = goodsStartTime;
        }

        public int getGoodsEndTime() {
            return goodsEndTime;
        }

        public void setGoodsEndTime(int goodsEndTime) {
            this.goodsEndTime = goodsEndTime;
        }

        public int getGoodsForm() {
            return goodsForm;
        }

        public void setGoodsForm(int goodsForm) {
            this.goodsForm = goodsForm;
        }

        public int getTransportId() {
            return transportId;
        }

        public void setTransportId(int transportId) {
            this.transportId = transportId;
        }

        public float getPyPrice() {
            return pyPrice;
        }

        public void setPyPrice(float pyPrice) {
            this.pyPrice = pyPrice;
        }

        public float getKdPrice() {
            return kdPrice;
        }

        public void setKdPrice(float kdPrice) {
            this.kdPrice = kdPrice;
        }

        public float getEsPrice() {
            return esPrice;
        }

        public void setEsPrice(float esPrice) {
            this.esPrice = esPrice;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public int getGoodsStoreState() {
            return goodsStoreState;
        }

        public void setGoodsStoreState(int goodsStoreState) {
            this.goodsStoreState = goodsStoreState;
        }

        public int getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(int commentnum) {
            this.commentnum = commentnum;
        }

        public int getSalenum() {
            return salenum;
        }

        public void setSalenum(int salenum) {
            this.salenum = salenum;
        }

        public int getGoodsCollect() {
            return goodsCollect;
        }

        public void setGoodsCollect(int goodsCollect) {
            this.goodsCollect = goodsCollect;
        }

        public int getGoodsGoldNum() {
            return goodsGoldNum;
        }

        public void setGoodsGoldNum(int goodsGoldNum) {
            this.goodsGoldNum = goodsGoldNum;
        }

        public int getGoodsIsztc() {
            return goodsIsztc;
        }

        public void setGoodsIsztc(int goodsIsztc) {
            this.goodsIsztc = goodsIsztc;
        }

        public int getGoodsZtcState() {
            return goodsZtcState;
        }

        public void setGoodsZtcState(int goodsZtcState) {
            this.goodsZtcState = goodsZtcState;
        }

        public int getGoodsZtcStartDate() {
            return goodsZtcStartDate;
        }

        public void setGoodsZtcStartDate(int goodsZtcStartDate) {
            this.goodsZtcStartDate = goodsZtcStartDate;
        }

        public int getGroupFlag() {
            return groupFlag;
        }

        public void setGroupFlag(int groupFlag) {
            this.groupFlag = groupFlag;
        }

        public float getGroupPrice() {
            return groupPrice;
        }

        public void setGroupPrice(float groupPrice) {
            this.groupPrice = groupPrice;
        }

        public int getXianshiFlag() {
            return xianshiFlag;
        }

        public void setXianshiFlag(int xianshiFlag) {
            this.xianshiFlag = xianshiFlag;
        }

        public float getXianshiDiscount() {
            return xianshiDiscount;
        }

        public void setXianshiDiscount(float xianshiDiscount) {
            this.xianshiDiscount = xianshiDiscount;
        }

        public int getGoodsTransfeeCharge() {
            return goodsTransfeeCharge;
        }

        public void setGoodsTransfeeCharge(int goodsTransfeeCharge) {
            this.goodsTransfeeCharge = goodsTransfeeCharge;
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

        public String getGoodsImageUrlPre() {
            return goodsImageUrlPre;
        }

        public void setGoodsImageUrlPre(String goodsImageUrlPre) {
            this.goodsImageUrlPre = goodsImageUrlPre;
        }

        public List<GoodsSpecBeanListEntity> getGoodsSpecBeanList() {
            return goodsSpecBeanList;
        }

        public void setGoodsSpecBeanList(List<GoodsSpecBeanListEntity> goodsSpecBeanList) {
            this.goodsSpecBeanList = goodsSpecBeanList;
        }

        public static class GoodsSpecBeanListEntity {
            private int id;
            private int goodsId;
            private float specGoodsPrice;
            private int specGoodsStorage;
            private int specSalenum;

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

            public float getSpecGoodsPrice() {
                return specGoodsPrice;
            }

            public void setSpecGoodsPrice(float specGoodsPrice) {
                this.specGoodsPrice = specGoodsPrice;
            }

            public int getSpecGoodsStorage() {
                return specGoodsStorage;
            }

            public void setSpecGoodsStorage(int specGoodsStorage) {
                this.specGoodsStorage = specGoodsStorage;
            }

            public int getSpecSalenum() {
                return specSalenum;
            }

            public void setSpecSalenum(int specSalenum) {
                this.specSalenum = specSalenum;
            }
        }
    }
}
