package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/27 11:39
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GetMemberFilterModel {
    /**
     * result : 1
     * body : [{"id":1,"paramkey":"生日筛选","paramValue":["今天","七天内","30天内"]},{"id":2,"paramkey":"消费筛选","paramValue":["未消费","消费>0元"]},{"id":3,"paramkey":"标签筛选","paramValue":["nigzh","妹子","希望破后悔狗","您，您所以破","我破，明明让"]}]
     */

    private String result;
    /**
     * id : 1
     * paramkey : 生日筛选
     * paramValue : ["今天","七天内","30天内"]
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
        private int id;
        private String paramkey;
        private List<String> paramValue;
        private int childSelectId;
        private String childSelectStr;

        public int getChildSelectId() {
            return childSelectId;
        }

        public void setChildSelectId(int childSelectId) {
            this.childSelectId = childSelectId;
        }

        public String getChildSelectStr() {
            return childSelectStr;
        }

        public void setChildSelectStr(String childSelectStr) {
            this.childSelectStr = childSelectStr;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getParamkey() {
            return paramkey;
        }

        public void setParamkey(String paramkey) {
            this.paramkey = paramkey;
        }

        public List<String> getParamValue() {
            return paramValue;
        }

        public void setParamValue(List<String> paramValue) {
            this.paramValue = paramValue;
        }
    }
}
