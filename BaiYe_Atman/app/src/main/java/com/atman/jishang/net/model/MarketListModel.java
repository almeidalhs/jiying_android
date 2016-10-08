package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/16 17:07
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MarketListModel {
    /**
     * result : 1
     * body : [{"id":1,"title":"全场满减","url":"","icon":"http://www.jiplaza.net:8000/by/Marketing/manjian.png","remark":"全场满减1","state":0,"backgroundColor":"FF7E66","backgroundImg":"http://www.jiplaza.net:8000/by/Marketing/manjian_bg.png","type":1},{"id":2,"title":"优惠券","url":"","icon":"http://www.jiplaza.net:8000/by/Marketing/coupon.png","remark":"优惠券","state":0,"backgroundColor":"5EBEEB","backgroundImg":"http://www.jiplaza.net:8000/by/Marketing/coupon_bg.png","type":2}]
     */

    private String result;
    /**
     * id : 1
     * title : 全场满减
     * url :
     * icon : http://www.jiplaza.net:8000/by/Marketing/manjian.png
     * remark : 全场满减1
     * state : 0
     * backgroundColor : FF7E66
     * backgroundImg : http://www.jiplaza.net:8000/by/Marketing/manjian_bg.png
     * type : 1
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
        private String title;
        private String url;
        private String icon;
        private String remark;
        private int state;
        private String backgroundColor;
        private String backgroundImg;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getBackgroundColor() {
            return backgroundColor;
        }

        public void setBackgroundColor(String backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        public String getBackgroundImg() {
            return backgroundImg;
        }

        public void setBackgroundImg(String backgroundImg) {
            this.backgroundImg = backgroundImg;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
