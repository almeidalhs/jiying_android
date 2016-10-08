package com.atman.jishang.net.model;

/**
 * 描述 接口调用失败后的信息
 * 作者 tangbingliang
 * 时间 16/4/13 10:30
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class RequestResultModel {
    /**
     * code : 10102
     * message : 登录失败
     */

    private int code;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
