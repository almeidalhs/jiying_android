package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/5 13:36
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class ConfigModel {
    /**
     * api_url : http://www.jiplaza.net
     * api_img_url : http://www.jiplaza.net:8000/by
     * launch_ad_imgback_url : http://api.5ys7.com:8001/imageServer/prop/temp/bybg.png
     * launch_ad_imgcontent_url : http://api.5ys7.com:8001/imageServer/prop/temp/byttext.png
     * launch_ad_web_url : http://m.ebrun.com/c_2184.html?eb=hp_home_nav_zdh
     * launch_ad_type : -1
     * shop : [{"description":"","title":"12金币","price":"楼6.00","name":"com.atman.wysq.coin.1"},{"description":"+閫�5%","title":"25金币","price":"楼12.00","name":"com.atman.wysq.coin.2"},{"description":"+閫�10%","title":"66金币","price":"楼30.00","name":"com.atman.wysq.coin.3"},{"description":"+閫�15%","title":"156金币","price":"楼68.00","name":"com.atman.wysq.coin.4"},{"description":"+閫�20%","title":"403金币","price":"楼168.00","name":"com.atman.wysq.coin.5"},{"description":"+閫�25%","title":"820金币","price":"楼328.00","name":"com.atman.wysq.coin.6"},{"description":"+閫�30%","title":"1685金币","price":"楼648.00","name":"com.atman.wysq.coin.7"}]
     * kStartUpAdGoodsId : 235975
     * kStartUpAdType : 6
     * kDemoAccount : 13916065195
     * kDemoAccountPW : 343b1c4a3ea721b2d640fc8700db0f36
     * kAndroidUrl : http://www.justing.com/baiye/download.html
     */

    private String api_url;
    private String api_img_url;
    private String launch_ad_imgback_url;
    private String launch_ad_imgcontent_url;
    private String launch_ad_web_url;
    private String launch_ad_type;
    private String kStartUpAdGoodsId;
    private String kStartUpAdType;
    private String kDemoAccount;
    private String kDemoAccountPW;
    private String kAndroidUrl;
    /**
     * description :
     * title : 12金币
     * price : 楼6.00
     * name : com.atman.wysq.coin.1
     */

    private List<ShopEntity> shop;

    public String getApi_url() {
        return api_url;
    }

    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }

    public String getApi_img_url() {
        return api_img_url;
    }

    public void setApi_img_url(String api_img_url) {
        this.api_img_url = api_img_url;
    }

    public String getLaunch_ad_imgback_url() {
        return launch_ad_imgback_url;
    }

    public void setLaunch_ad_imgback_url(String launch_ad_imgback_url) {
        this.launch_ad_imgback_url = launch_ad_imgback_url;
    }

    public String getLaunch_ad_imgcontent_url() {
        return launch_ad_imgcontent_url;
    }

    public void setLaunch_ad_imgcontent_url(String launch_ad_imgcontent_url) {
        this.launch_ad_imgcontent_url = launch_ad_imgcontent_url;
    }

    public String getLaunch_ad_web_url() {
        return launch_ad_web_url;
    }

    public void setLaunch_ad_web_url(String launch_ad_web_url) {
        this.launch_ad_web_url = launch_ad_web_url;
    }

    public String getLaunch_ad_type() {
        return launch_ad_type;
    }

    public void setLaunch_ad_type(String launch_ad_type) {
        this.launch_ad_type = launch_ad_type;
    }

    public String getKStartUpAdGoodsId() {
        return kStartUpAdGoodsId;
    }

    public void setKStartUpAdGoodsId(String kStartUpAdGoodsId) {
        this.kStartUpAdGoodsId = kStartUpAdGoodsId;
    }

    public String getKStartUpAdType() {
        return kStartUpAdType;
    }

    public void setKStartUpAdType(String kStartUpAdType) {
        this.kStartUpAdType = kStartUpAdType;
    }

    public String getKDemoAccount() {
        return kDemoAccount;
    }

    public void setKDemoAccount(String kDemoAccount) {
        this.kDemoAccount = kDemoAccount;
    }

    public String getKDemoAccountPW() {
        return kDemoAccountPW;
    }

    public void setKDemoAccountPW(String kDemoAccountPW) {
        this.kDemoAccountPW = kDemoAccountPW;
    }

    public String getKAndroidUrl() {
        return kAndroidUrl;
    }

    public void setKAndroidUrl(String kAndroidUrl) {
        this.kAndroidUrl = kAndroidUrl;
    }

    public List<ShopEntity> getShop() {
        return shop;
    }

    public void setShop(List<ShopEntity> shop) {
        this.shop = shop;
    }

    public static class ShopEntity {
        private String description;
        private String title;
        private String price;
        private String name;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
