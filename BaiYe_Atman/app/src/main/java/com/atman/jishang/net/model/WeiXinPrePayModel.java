package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/6/29 16:26
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class WeiXinPrePayModel {
    /**
     * result : 1
     * body : {"appid":"wxb7ed76c833633acb","mch_id":"1353113902","body":"18616348991","nonce_str":"bf73c8994e004a0199153a6376b9f036","sign":"63B467B8C245C0CC67689C9D0EE714FD","out_trade_no":"182A0629162308","total_fee":1,"spbill_create_ip":"192.168.1.141","notify_url":"http://api.5ys7.com/wechatcallback","trade_type":"APP","order_id":"182","prepayId":"wx20160629162356c162e3ee5d0529181527","resultCode":"SUCCESS","returnCode":"SUCCESS"}
     */

    private String result;
    /**
     * appid : wxb7ed76c833633acb
     * mch_id : 1353113902
     * body : 18616348991
     * nonce_str : bf73c8994e004a0199153a6376b9f036
     * sign : 63B467B8C245C0CC67689C9D0EE714FD
     * out_trade_no : 182A0629162308
     * total_fee : 1
     * spbill_create_ip : 192.168.1.141
     * notify_url : http://api.5ys7.com/wechatcallback
     * trade_type : APP
     * order_id : 182
     * prepayId : wx20160629162356c162e3ee5d0529181527
     * resultCode : SUCCESS
     * returnCode : SUCCESS
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
        private String appid;
        private String mch_id;
        private String body;
        private String nonce_str;
        private String sign;
        private String out_trade_no;
        private int total_fee;
        private String spbill_create_ip;
        private String notify_url;
        private String trade_type;
        private String order_id;
        private String prepayId;
        private String resultCode;
        private String returnCode;
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public int getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(int total_fee) {
            this.total_fee = total_fee;
        }

        public String getSpbill_create_ip() {
            return spbill_create_ip;
        }

        public void setSpbill_create_ip(String spbill_create_ip) {
            this.spbill_create_ip = spbill_create_ip;
        }

        public String getNotify_url() {
            return notify_url;
        }

        public void setNotify_url(String notify_url) {
            this.notify_url = notify_url;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getPrepayId() {
            return prepayId;
        }

        public void setPrepayId(String prepayId) {
            this.prepayId = prepayId;
        }

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getReturnCode() {
            return returnCode;
        }

        public void setReturnCode(String returnCode) {
            this.returnCode = returnCode;
        }
    }
}
