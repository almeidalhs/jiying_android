package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/24 16:44
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class MemberFilterModel {

    private int id;
    private String paramkey;

    public MemberFilterModel(int id, String paramkey) {
        this.id = id;
        this.paramkey = paramkey;
    }

    public String getParamkey() {
        return paramkey;
    }

    public void setParamkey(String paramkey) {
        this.paramkey = paramkey;
    }
}
