package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述 商品分类
 * 作者 tangbingliang
 * 时间 16/4/25 13:58
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class StoreGoodsClassesModel {
    /**
     * result : 1
     * body : [{"id":118,"stcName":"咯拖女拖来","stcParentId":0,"stcState":1,"storeId":11,"stcSort":3,"goodsCount":1},{"id":119,"stcName":"福特福卡通","stcParentId":0,"stcState":1,"storeId":11,"stcSort":4},{"id":120,"stcName":"训练了某兔","stcParentId":0,"stcState":1,"storeId":11,"stcSort":5},{"id":121,"stcName":"做哦看看咯","stcParentId":0,"stcState":1,"storeId":11,"stcSort":6,"goodsCount":2},{"id":123,"stcName":"雨啊也有了","stcParentId":0,"stcState":1,"storeId":11,"stcSort":7,"goodsCount":32},{"id":124,"stcName":"独特头目阿","stcParentId":0,"stcState":1,"storeId":11,"stcSort":8,"goodsCount":2},{"id":125,"stcName":"他特鳄鱼纹","stcParentId":0,"stcState":1,"storeId":11,"stcSort":9,"goodsCount":5},{"id":126,"stcName":"不特有独特","stcParentId":0,"stcState":1,"storeId":11,"stcSort":10,"goodsCount":4},{"id":127,"stcName":"一独特特他","stcParentId":0,"stcState":1,"storeId":11,"stcSort":11,"goodsCount":5},{"id":128,"stcName":"略堵堵独特","stcParentId":0,"stcState":1,"storeId":11,"stcSort":12,"goodsCount":1},{"id":129,"stcName":"堵独特他特","stcParentId":0,"stcState":1,"storeId":11,"stcSort":13,"goodsCount":2},{"id":130,"stcName":"她嘟嘟嘟嘟","stcParentId":0,"stcState":1,"storeId":11,"stcSort":14,"goodsCount":34},{"id":131,"stcName":"不特堵独特","stcParentId":0,"stcState":1,"storeId":11,"stcSort":15,"goodsCount":34},{"id":132,"stcName":"帕克特多囤","stcParentId":0,"stcState":1,"storeId":11,"stcSort":16,"goodsCount":5},{"id":133,"stcName":" 虐堵堵堵","stcParentId":0,"stcState":1,"storeId":11,"stcSort":17,"goodsCount":6},{"id":134,"stcName":"特特特堵堵","stcParentId":0,"stcState":1,"storeId":11,"stcSort":18,"goodsCount":7},{"id":135,"stcName":"据特独特特","stcParentId":0,"stcState":1,"storeId":11,"stcSort":19,"goodsCount":788},{"id":136,"stcName":"帕克特多怒","stcParentId":0,"stcState":1,"storeId":11,"stcSort":20},{"id":137,"stcName":"嘟嘟父母考","stcParentId":0,"stcState":1,"storeId":11,"stcSort":21,"goodsCount":990},{"id":138,"stcName":"额度锯木头","stcParentId":0,"stcState":1,"storeId":11,"stcSort":22,"goodsCount":0},{"id":139,"stcName":"头特特恶独","stcParentId":0,"stcState":1,"storeId":11,"stcSort":23},{"id":140,"stcName":"他饿晕特耶","stcParentId":0,"stcState":1,"storeId":11,"stcSort":24}]
     */

    private String result;
    /**
     * id : 118
     * stcName : 咯拖女拖来
     * stcParentId : 0
     * stcState : 1
     * storeId : 11
     * stcSort : 3
     * goodsCount : 1
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
        private String stcName;
        private int stcParentId;
        private int stcState;
        private int storeId;
        private int stcSort;
        private int goodsCount;

        public BodyEntity(){}

        public BodyEntity(int id,String stcName,int stcParentId,int stcState
                ,int storeId,int stcSort,int goodsCount){
            this.id = id;
            this.stcName = stcName;
            this.stcParentId = stcParentId;
            this.stcState = stcState;
            this.storeId = storeId;
            this.stcSort = stcSort;
            this.goodsCount = goodsCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStcName() {
            return stcName;
        }

        public void setStcName(String stcName) {
            this.stcName = stcName;
        }

        public int getStcParentId() {
            return stcParentId;
        }

        public void setStcParentId(int stcParentId) {
            this.stcParentId = stcParentId;
        }

        public int getStcState() {
            return stcState;
        }

        public void setStcState(int stcState) {
            this.stcState = stcState;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getStcSort() {
            return stcSort;
        }

        public void setStcSort(int stcSort) {
            this.stcSort = stcSort;
        }

        public int getGoodsCount() {
            return goodsCount;
        }

        public void setGoodsCount(int goodsCount) {
            this.goodsCount = goodsCount;
        }
    }
}
