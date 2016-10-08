package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/6 11:48
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class DeleteGoodsModel {
    /**
     * id : 37
     * storeId : 4
     */

    private int id;
    private int storeId;

    public DeleteGoodsModel (int id, int storeId){
        this.id = id;
        this.storeId = storeId;
    }

    public int getId() {
        return id;
    }

    public int getStoreId() {
        return storeId;
    }
}
