package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/14 09:46
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class CodeCheckModel {
    /**
     * result : 0
     * body : 验证码错误!
     */

    private String result;
    private String body;

    public void setResult(String result) {
        this.result = result;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getResult() {
        return result;
    }

    public String getBody() {
        return body;
    }
}
