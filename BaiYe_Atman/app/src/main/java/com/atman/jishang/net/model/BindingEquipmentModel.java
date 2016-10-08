package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/6/21 15:03
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class BindingEquipmentModel {
    /**
     * result : 0
     * body : {"message":"无效的设备标志或者密码错误"}
     */

    private String result;
    /**
     * message : 无效的设备标志或者密码错误
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
