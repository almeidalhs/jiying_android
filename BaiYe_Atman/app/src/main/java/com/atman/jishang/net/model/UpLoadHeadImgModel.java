package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/14 11:13
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class UpLoadHeadImgModel {
    /**
     * body : [{"name":"20160414111154.jpg","type":"image/jpeg","size":44084,"successful":true,"url":"Avatar/88/85/88859a0101ee11e6b5d774d02ba07f83.jpg"}]
     * result : 1
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
         * name : 20160414111154.jpg
         * type : image/jpeg
         * size : 44084
         * successful : true
         * url : Avatar/88/85/88859a0101ee11e6b5d774d02ba07f83.jpg
         */

        private String name;
        private String type;
        private int size;
        private boolean successful;
        private String url;

        public void setName(String name) {
            this.name = name;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setSuccessful(boolean successful) {
            this.successful = successful;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public int getSize() {
            return size;
        }

        public boolean getSuccessful() {
            return successful;
        }

        public String getUrl() {
            return url;
        }
    }
}
