package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/6/7 18:04
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GetUpdateVersionModel {
    /**
     * result : 1
     * body : {"warn":"发现新版本,是否立即升级? \n ","force":"0","url":"http://www.justing.com/baiye/jishang.apk"}
     */

    private String result;
    /**
     * warn : 发现新版本,是否立即升级?

     * force : 0
     * url : http://www.justing.com/baiye/jishang.apk
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
        private String warn;
        private String force;
        private String url;

        public String getWarn() {
            return warn;
        }

        public void setWarn(String warn) {
            this.warn = warn;
        }

        public String getForce() {
            return force;
        }

        public void setForce(String force) {
            this.force = force;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "BodyEntity{" +
                    "force='" + force + '\'' +
                    ", warn='" + warn + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GetUpdateVersionModel{" +
                "body=" + body +
                ", result='" + result.toString() + '\'' +
                '}';
    }
}
