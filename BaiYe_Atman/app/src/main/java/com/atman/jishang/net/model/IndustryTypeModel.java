package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述 行业类型
 * 作者 tangbingliang
 * 时间 16/4/18 09:44
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class IndustryTypeModel {
    /**
     * result : 1
     * body : [{"id":65,"scName":"其他","scParentId":0,"scSort":11},{"id":12,"scName":"生活/服务","scParentId":0,"scSort":10},{"id":11,"scName":"收藏/爱好","scParentId":0,"scSort":9},{"id":1,"scName":"珠宝/首饰","scParentId":0,"scSort":8},{"id":10,"scName":"文体/汽车","scParentId":0,"scSort":7},{"id":9,"scName":"母婴用品","scParentId":0,"scSort":6},{"id":8,"scName":"食品/保健","scParentId":0,"scSort":5},{"id":7,"scName":"家居用品","scParentId":0,"scSort":4},{"id":6,"scName":"美容护理","scParentId":0,"scSort":3},{"id":5,"scName":"3C数码","scParentId":0,"scSort":2},{"id":4,"scName":"服装鞋包","scParentId":0,"scSort":1}]
     */

    private String result;
    private List<BodyEntity> body;

    public void setResult(String result) {
        this.result = result;
    }

    public void setBody(List<BodyEntity> body) {
        this.body = body;
    }

    public String getResult() {
        return result;
    }

    public List<BodyEntity> getBody() {
        return body;
    }

    public static class BodyEntity {
        /**
         * id : 65
         * scName : 其他
         * scParentId : 0
         * scSort : 11
         */

        private int id;
        private String scName;
        private int scParentId;
        private int scSort;
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setScName(String scName) {
            this.scName = scName;
        }

        public void setScParentId(int scParentId) {
            this.scParentId = scParentId;
        }

        public void setScSort(int scSort) {
            this.scSort = scSort;
        }

        public int getId() {
            return id;
        }

        public String getScName() {
            return scName;
        }

        public int getScParentId() {
            return scParentId;
        }

        public int getScSort() {
            return scSort;
        }
    }
}
