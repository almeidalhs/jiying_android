package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/23 16:41
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class FinishCouponModel {

    /**
     * result : 0
     * body : {"message":"只能删除未开始的优惠券"}
     */

    private String result;
    /**
     * message : 只能删除未开始的优惠券
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
