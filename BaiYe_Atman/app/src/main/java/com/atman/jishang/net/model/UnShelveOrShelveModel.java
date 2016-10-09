package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/29 11:17
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class UnShelveOrShelveModel {
    /**
     * id : 44
     * storeId : 4
     */

    private long id;
    private int storeId;

    public UnShelveOrShelveModel(long id, int storeId){
        this.id = id;
        this.storeId = storeId;
    }

    public long getId() {
        return id;
    }

    public int getStoreId() {
        return storeId;
    }
}
