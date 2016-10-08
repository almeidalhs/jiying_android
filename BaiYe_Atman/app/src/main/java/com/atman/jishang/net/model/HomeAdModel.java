package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/21 15:03
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class HomeAdModel {
    /**
     * result : 1
     * body : [{"id":1,"name":"新开店铺有礼","description":"新开店铺有礼","adPic":"http://192.168.1.141:8000/by/Console/ad0.png","adUrl":"http://www.53to.com/item/164","sort":1,"series":1,"type":1},{"id":2,"name":"上传商品满五送一","description":"上传商品满五送一","adPic":"http://192.168.1.134:8001/imageServer/prop/temp/a4.jpg","adUrl":"http://www.53to.com/item/165","sort":2,"series":1,"type":2},{"id":3,"name":"分享就有积分","description":"分享就有积分","adPic":"http://192.168.1.134:8001/imageServer/prop/temp/a7.jpg","adUrl":"http://www.53to.com/item/166","sort":3,"series":1,"type":3}]
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
         * id : 1
         * name : 新开店铺有礼
         * description : 新开店铺有礼
         * adPic : http://192.168.1.141:8000/by/Console/ad0.png
         * adUrl : http://www.53to.com/item/164
         * sort : 1
         * series : 1
         * type : 1
         */

        private int id;
        private String name;
        private String description;
        private String adPic;
        private String adUrl;
        private int sort;
        private int series;
        private int type;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setAdPic(String adPic) {
            this.adPic = adPic;
        }

        public void setAdUrl(String adUrl) {
            this.adUrl = adUrl;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public void setSeries(int series) {
            this.series = series;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getAdPic() {
            return adPic;
        }

        public String getAdUrl() {
            return adUrl;
        }

        public int getSort() {
            return sort;
        }

        public int getSeries() {
            return series;
        }

        public int getType() {
            return type;
        }
    }
}
