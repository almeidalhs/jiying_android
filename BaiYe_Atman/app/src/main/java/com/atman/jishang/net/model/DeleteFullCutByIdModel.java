package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/20 13:35
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class DeleteFullCutByIdModel {
    /**
     * result : 1
     * body : {"message":"删除成功"}
     */

    private String result;
    /**
     * message : 删除成功
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
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
