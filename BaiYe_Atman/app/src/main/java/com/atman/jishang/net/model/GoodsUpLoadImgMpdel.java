package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/9 13:45
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GoodsUpLoadImgMpdel {
    /**
     * body : [{"name":"2015-12-28_11-15-04.JPEG","type":"image/pjpeg","size":53129,"successful":true,"url":"Avatar/da/ec/daec313915a811e6b5ca74d02ba07f83.JPEG"},{"name":"2015-12-28_11-15-49.JPEG","type":"image/pjpeg","size":43735,"successful":true,"url":"Avatar/da/ee/daeea23a15a811e6b5ca74d02ba07f83.JPEG"}]
     * result : 1
     */

    private String result;
    /**
     * name : 2015-12-28_11-15-04.JPEG
     * type : image/pjpeg
     * size : 53129
     * successful : true
     * url : Avatar/da/ec/daec313915a811e6b5ca74d02ba07f83.JPEG
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
        private String name;
        private String type;
        private int size;
        private boolean successful;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public boolean isSuccessful() {
            return successful;
        }

        public void setSuccessful(boolean successful) {
            this.successful = successful;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
