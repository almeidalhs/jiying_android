package com.atman.jishang.net.model;

/**
 * 描述 注册
 * 作者 tangbingliang
 * 时间 16/4/13 17:42
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class RegisterModel {
    /**
     * result : 0
     * body : {"error":"Unknown error","error_code":"99999","error_description":"未知错误"}
     */

    private String result;
    private BodyEntity body;

    public void setResult(String result) {
        this.result = result;
    }

    public void setBody(BodyEntity body) {
        this.body = body;
    }

    public String getResult() {
        return result;
    }

    public BodyEntity getBody() {
        return body;
    }

    public static class BodyEntity {
        /**
         * error : Unknown error
         * error_code : 99999
         * error_description : 未知错误
         */

        private String error;
        private String error_code;
        private String error_description;
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setError(String error) {
            this.error = error;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }

        public void setError_description(String error_description) {
            this.error_description = error_description;
        }

        public String getError() {
            return error;
        }

        public String getError_code() {
            return error_code;
        }

        public String getError_description() {
            return error_description;
        }
    }
}
