package com.atman.jishang.net.model;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/18 17:23
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AddFullCutNextModel {
    private String price;
    private String discount;

    public AddFullCutNextModel(){}

    public AddFullCutNextModel(String price, String discount){
        this.price = price;
        this.discount = discount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
