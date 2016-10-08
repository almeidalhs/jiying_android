package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/26 15:35
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GoodsDetailInfoModel {

    /**
     * result : 1
     * body : {"id":175,"goodsName":"我的新的8","gcName":"","brandId":0,"typeId":0,"storeId":4,"specOpen":0,"specName":"","goodsImage":"Goods/2/fdde88fceb45f66e35d9da05b23e3e40.jpg","fullGoodsImage":"http://www.jiplaza.net:8000/by/Goods/2/fdde88fceb45f66e35d9da05b23e3e40.jpg","goodsImageMore":"Goods/2/fdde88fceb45f66e35d9da05b23e3e40.jpg,Goods/2/f59bc0e47742392a1e737f60fcf90a18.jpg,Goods/2/f49d92932c787fd90b2daf299d95615c.jpg","goodsStorePrice":100,"goodsStorePriceInterval":"","goodsSerial":"132346634634","goodsShow":1,"goodsClick":1,"goodsState":0,"goodsCommend":0,"goodsAddTime":0,"goodsKeywords":"","goodsDescription":"我的商品的描述","goodsBody":"","goodsSpec":"添加我的规格,比如大小,颜色","goodsStartTime":0,"goodsEndTime":0,"goodsForm":1,"transportId":0,"pyPrice":0,"kdPrice":0,"esPrice":0,"cityId":0,"provinceId":0,"goodsStoreState":0,"commentnum":0,"salenum":0,"goodsCollect":0,"goodsGoldNum":0,"goodsIsztc":0,"goodsZtcState":1,"goodsZtcStartDate":0,"groupFlag":0,"groupPrice":0,"xianshiFlag":0,"xianshiDiscount":0,"goodsTransfeeCharge":0,"memberMobile":"13916065195","stcId":12,"price":45,"storage":75,"goodsWebUrl":"http://www.jiplaza.net:8011/#/goods/175","goodsImageMoreList":["http://www.jiplaza.net:8000/by/Goods/2/fdde88fceb45f66e35d9da05b23e3e40.jpg","http://www.jiplaza.net:8000/by/Goods/2/f59bc0e47742392a1e737f60fcf90a18.jpg","http://www.jiplaza.net:8000/by/Goods/2/f49d92932c787fd90b2daf299d95615c.jpg"],"goodsSpecBeanList":[{"id":257,"goodsId":175,"specGoodsPrice":45,"specGoodsStorage":75,"specSalenum":0}],"goodsImageUrlPre":""}
     */

    private String result;
    /**
     * id : 175
     * goodsName : 我的新的8
     * gcName :
     * brandId : 0
     * typeId : 0
     * storeId : 4
     * specOpen : 0
     * specName :
     * goodsImage : Goods/2/fdde88fceb45f66e35d9da05b23e3e40.jpg
     * fullGoodsImage : http://www.jiplaza.net:8000/by/Goods/2/fdde88fceb45f66e35d9da05b23e3e40.jpg
     * goodsImageMore : Goods/2/fdde88fceb45f66e35d9da05b23e3e40.jpg,Goods/2/f59bc0e47742392a1e737f60fcf90a18.jpg,Goods/2/f49d92932c787fd90b2daf299d95615c.jpg
     * goodsStorePrice : 100
     * goodsStorePriceInterval :
     * goodsSerial : 132346634634
     * goodsShow : 1
     * goodsClick : 1
     * goodsState : 0
     * goodsCommend : 0
     * goodsAddTime : 0
     * goodsKeywords :
     * goodsDescription : 我的商品的描述
     * goodsBody :
     * goodsSpec : 添加我的规格,比如大小,颜色
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
     * memberMobile : 13916065195
     * stcId : 12
     * price : 45
     * storage : 75
     * goodsWebUrl : http://www.jiplaza.net:8011/#/goods/175
     * goodsImageMoreList : ["http://www.jiplaza.net:8000/by/Goods/2/fdde88fceb45f66e35d9da05b23e3e40.jpg","http://www.jiplaza.net:8000/by/Goods/2/f59bc0e47742392a1e737f60fcf90a18.jpg","http://www.jiplaza.net:8000/by/Goods/2/f49d92932c787fd90b2daf299d95615c.jpg"]
     * goodsSpecBeanList : [{"id":257,"goodsId":175,"specGoodsPrice":45,"specGoodsStorage":75,"specSalenum":0}]
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
        private String gcName;
        private int brandId;
        private int typeId;
        private int storeId;
        private int specOpen;
        private String specName;
        private String goodsImage;
        private String fullGoodsImage;
        private String goodsImageMore;
        private String goodsStorePrice;
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
        private String goodsSpec;
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
        private int xianshiDiscount;
        private int goodsTransfeeCharge;
        private String memberMobile;
        private int stcId;
        private double price;
        private int storage;
        private String goodsWebUrl;
        private String goodsImageUrlPre;
        private List<String> goodsImageMoreList;
        /**
         * id : 257
         * goodsId : 175
         * specGoodsPrice : 45
         * specGoodsStorage : 75
         * specSalenum : 0
         */

        private List<GoodsSpecBeanListEntity> goodsSpecBeanList;
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

        public String getGoodsImageMore() {
            return goodsImageMore;
        }

        public void setGoodsImageMore(String goodsImageMore) {
            this.goodsImageMore = goodsImageMore;
        }

        public String getGoodsStorePrice() {
            return goodsStorePrice;
        }

        public void setGoodsStorePrice(String goodsStorePrice) {
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

        public String getGoodsSpec() {
            return goodsSpec;
        }

        public void setGoodsSpec(String goodsSpec) {
            this.goodsSpec = goodsSpec;
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

        public int getXianshiDiscount() {
            return xianshiDiscount;
        }

        public void setXianshiDiscount(int xianshiDiscount) {
            this.xianshiDiscount = xianshiDiscount;
        }

        public int getGoodsTransfeeCharge() {
            return goodsTransfeeCharge;
        }

        public void setGoodsTransfeeCharge(int goodsTransfeeCharge) {
            this.goodsTransfeeCharge = goodsTransfeeCharge;
        }

        public String getMemberMobile() {
            return memberMobile;
        }

        public void setMemberMobile(String memberMobile) {
            this.memberMobile = memberMobile;
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
