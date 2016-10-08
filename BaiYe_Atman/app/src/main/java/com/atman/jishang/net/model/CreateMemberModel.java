package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/26 09:27
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class CreateMemberModel {
    /**
     * result : 1
     * body : {"id":79,"storeId":17,"mobile":"15923969611","weixin":"12346522","cardNumber":"10001000050","name":"GGGGGGGGG","sex":false,"birthday":1461572638,"address":"浦东新区","memo":"DDDDDDDEEEEEEEE","qq":"12fds45625","email":"123456@ataman.com","telephone":"15923969611","tag":"都是范德萨手佛挡杀佛机控上海","level":"暂未开通","status":0,"avatar":"AAAAAAADDDDDDA","img1":"wwwwwwDDDDDD","img2":"1wwww234DDDDDDDDDDDDD","img3":"wwwwwwDDDDDDDDDDD","img4":"1wwww234DDDDDDDDDDDDDAAAA","type":1,"imgList":["wwwwwwDDDDDD","1wwww234DDDDDDDDDDDDD","wwwwwwDDDDDDDDDDD","1wwww234DDDDDDDDDDDDDAAAA"]}
     */

    private String result;
    /**
     * id : 79
     * storeId : 17
     * mobile : 15923969611
     * weixin : 12346522
     * cardNumber : 10001000050
     * name : GGGGGGGGG
     * sex : false
     * birthday : 1461572638
     * address : 浦东新区
     * memo : DDDDDDDEEEEEEEE
     * qq : 12fds45625
     * email : 123456@ataman.com
     * telephone : 15923969611
     * tag : 都是范德萨手佛挡杀佛机控上海
     * level : 暂未开通
     * status : 0
     * avatar : AAAAAAADDDDDDA
     * img1 : wwwwwwDDDDDD
     * img2 : 1wwww234DDDDDDDDDDDDD
     * img3 : wwwwwwDDDDDDDDDDD
     * img4 : 1wwww234DDDDDDDDDDDDDAAAA
     * type : 1
     * imgList : ["wwwwwwDDDDDD","1wwww234DDDDDDDDDDDDD","wwwwwwDDDDDDDDDDD","1wwww234DDDDDDDDDDDDDAAAA"]
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
        private int id;
        private int storeId;
        private String mobile;
        private String weixin;
        private String cardNumber;
        private String name;
        private boolean sex;
        private int birthday;
        private String address;
        private String memo;
        private String qq;
        private String email;
        private String telephone;
        private String tag;
        private String level;
        private int status;
        private String avatar;
        private String img1;
        private String img2;
        private String img3;
        private String img4;
        private int type;
        private List<String> imgList;
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSex() {
            return sex;
        }

        public void setSex(boolean sex) {
            this.sex = sex;
        }

        public int getBirthday() {
            return birthday;
        }

        public void setBirthday(int birthday) {
            this.birthday = birthday;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public String getImg2() {
            return img2;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public String getImg3() {
            return img3;
        }

        public void setImg3(String img3) {
            this.img3 = img3;
        }

        public String getImg4() {
            return img4;
        }

        public void setImg4(String img4) {
            this.img4 = img4;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<String> getImgList() {
            return imgList;
        }

        public void setImgList(List<String> imgList) {
            this.imgList = imgList;
        }
    }
}
