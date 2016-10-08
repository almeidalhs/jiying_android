package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/4/28 10:45
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class UpdateGoodsClassOrderModel {
    /**
     * storeId : 2
     * id : 70
     * stcSort : 1
     */

    private int storeId;
    private long id;
    private int stcSort;

    public UpdateGoodsClassOrderModel(){}

    public UpdateGoodsClassOrderModel(int storeId, long id, int stcSort){
        this.storeId = storeId;
        this.id = id;
        this.stcSort = stcSort;
    }
}
