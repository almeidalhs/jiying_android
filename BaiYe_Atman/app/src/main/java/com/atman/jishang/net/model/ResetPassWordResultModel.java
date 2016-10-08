package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/14 09:46
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class ResetPassWordResultModel {
    private String result;
    /**
     * message : 该手机号已注册“阿特门通行证”，请直接登录。如您忘记密码，请到登录页下点击“忘记密码？”找回。
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
