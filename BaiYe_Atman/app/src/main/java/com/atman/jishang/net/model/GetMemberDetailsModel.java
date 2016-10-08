package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/30 13:25
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GetMemberDetailsModel {
    /**
     * result : 1
     * body : {"id":396,"storeId":18,"mobile":"18578909061","weixin":"卫星","cardNumber":"10010000011","name":"小唐","sex":true,"address":"杨思","memo":"测试","qq":"1543888654","telephone":"18578909061","totalConsume":0,"level":"暂未开通","status":0,"integral":0,"avatar":"/39/cf/39cfbbc7263b11e6b64b74d02ba07f83.jpg","img1":"Goods/53/15/531575c9263b11e6b64b74d02ba07f83.jpg","type":1,"tagList":[{"id":341,"tagName":"嗯额"},{"id":342,"tagName":"阳光"}],"storeTagList":[{"id":288,"storeId":18,"tagName":"学生"},{"id":289,"storeId":18,"tagName":"白领"},{"id":290,"storeId":18,"tagName":"青年"},{"id":291,"storeId":18,"tagName":"中老年"},{"id":292,"storeId":18,"tagName":"大码"},{"id":293,"storeId":18,"tagName":"小码"},{"id":294,"storeId":18,"tagName":"运动"},{"id":295,"storeId":18,"tagName":"时尚"},{"id":296,"storeId":18,"tagName":"休闲"},{"id":297,"storeId":18,"tagName":"低消费"},{"id":298,"storeId":18,"tagName":"高消费"},{"id":299,"storeId":18,"tagName":"单身"},{"id":300,"storeId":18,"tagName":"情侣"},{"id":301,"storeId":18,"tagName":"已婚"},{"id":302,"storeId":18,"tagName":"新顾客"},{"id":303,"storeId":18,"tagName":"老客户"},{"id":304,"storeId":18,"tagName":"彩色系"},{"id":305,"storeId":18,"tagName":"日韩系"},{"id":306,"storeId":18,"tagName":"欧美系"},{"id":307,"storeId":18,"tagName":"冲动消费"},{"id":308,"storeId":18,"tagName":"谨慎消费"},{"id":309,"storeId":18,"tagName":"注重性价比"},{"id":310,"storeId":18,"tagName":"注重质量"},{"id":311,"storeId":18,"tagName":"注重外观"},{"id":312,"storeId":18,"tagName":"注重品牌"},{"id":313,"storeId":18,"tagName":"素色系"},{"id":340,"storeId":18,"tagName":"高品质"}],"imgList":["Goods/53/15/531575c9263b11e6b64b74d02ba07f83.jpg"]}
     */

    private String result;
    /**
     * id : 396
     * storeId : 18
     * mobile : 18578909061
     * weixin : 卫星
     * cardNumber : 10010000011
     * name : 小唐
     * sex : true
     * address : 杨思
     * memo : 测试
     * qq : 1543888654
     * telephone : 18578909061
     * totalConsume : 0
     * level : 暂未开通
     * status : 0
     * integral : 0
     * avatar : /39/cf/39cfbbc7263b11e6b64b74d02ba07f83.jpg
     * img1 : Goods/53/15/531575c9263b11e6b64b74d02ba07f83.jpg
     * type : 1
     * tagList : [{"id":341,"tagName":"嗯额"},{"id":342,"tagName":"阳光"}]
     * storeTagList : [{"id":288,"storeId":18,"tagName":"学生"},{"id":289,"storeId":18,"tagName":"白领"},{"id":290,"storeId":18,"tagName":"青年"},{"id":291,"storeId":18,"tagName":"中老年"},{"id":292,"storeId":18,"tagName":"大码"},{"id":293,"storeId":18,"tagName":"小码"},{"id":294,"storeId":18,"tagName":"运动"},{"id":295,"storeId":18,"tagName":"时尚"},{"id":296,"storeId":18,"tagName":"休闲"},{"id":297,"storeId":18,"tagName":"低消费"},{"id":298,"storeId":18,"tagName":"高消费"},{"id":299,"storeId":18,"tagName":"单身"},{"id":300,"storeId":18,"tagName":"情侣"},{"id":301,"storeId":18,"tagName":"已婚"},{"id":302,"storeId":18,"tagName":"新顾客"},{"id":303,"storeId":18,"tagName":"老客户"},{"id":304,"storeId":18,"tagName":"彩色系"},{"id":305,"storeId":18,"tagName":"日韩系"},{"id":306,"storeId":18,"tagName":"欧美系"},{"id":307,"storeId":18,"tagName":"冲动消费"},{"id":308,"storeId":18,"tagName":"谨慎消费"},{"id":309,"storeId":18,"tagName":"注重性价比"},{"id":310,"storeId":18,"tagName":"注重质量"},{"id":311,"storeId":18,"tagName":"注重外观"},{"id":312,"storeId":18,"tagName":"注重品牌"},{"id":313,"storeId":18,"tagName":"素色系"},{"id":340,"storeId":18,"tagName":"高品质"}]
     * imgList : ["Goods/53/15/531575c9263b11e6b64b74d02ba07f83.jpg"]
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
        private String address;
        private String memo;
        private String qq;
        private String telephone;
        private double totalConsume;
        private String level;
        private int status;
        private int integral;
        private String avatar;
        private String img1;
        private int type;
        private long birthday;
        /**
         * id : 341
         * tagName : 嗯额
         */

        private List<TagListEntity> tagList;
        /**
         * id : 288
         * storeId : 18
         * tagName : 学生
         */

        private List<StoreTagListEntity> storeTagList;
        private List<String> imgList;
        private String message;

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

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

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public double getTotalConsume() {
            return totalConsume;
        }

        public void setTotalConsume(double totalConsume) {
            this.totalConsume = totalConsume;
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

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<TagListEntity> getTagList() {
            return tagList;
        }

        public void setTagList(List<TagListEntity> tagList) {
            this.tagList = tagList;
        }

        public List<StoreTagListEntity> getStoreTagList() {
            return storeTagList;
        }

        public void setStoreTagList(List<StoreTagListEntity> storeTagList) {
            this.storeTagList = storeTagList;
        }

        public List<String> getImgList() {
            return imgList;
        }

        public void setImgList(List<String> imgList) {
            this.imgList = imgList;
        }

        public static class TagListEntity {
            private int id;
            private String tagName;

            public TagListEntity(int id, String tagName){
                this.id = id;
                this.tagName = tagName;
            }
            public TagListEntity(){}

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }
        }

        public static class StoreTagListEntity {
            private int id;
            private int storeId;
            private String tagName;

            public StoreTagListEntity(int id, int storeId, String tagName){
                this.id = id;
                this.storeId = storeId;
                this.tagName = tagName;
            }
            public StoreTagListEntity(){}

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

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }
        }
    }
}
