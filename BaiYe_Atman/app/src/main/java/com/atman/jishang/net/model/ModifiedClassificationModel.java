package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/29 11:47
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class ModifiedClassificationModel {
    /**
     * id : 37
     * storeId : 4
     * stcId : 138
     */

    private int id;
    private int storeId;
    private long stcId;

    public ModifiedClassificationModel(int id, int storeId, long stcId){
        this.id = id;
        this.storeId = storeId;
        this.stcId = stcId;
    }

    public int getId() {
        return id;
    }

    public int getStoreId() {
        return storeId;
    }

    public long getStcId() {
        return stcId;
    }
}
