package com.atman.jishang.net;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/5/24 16:39
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class GetMemberListModel {
    /**
     * result : 1
     * body : {"dataList":[{"id":343,"storeId":18,"weixin":"zhshj","cardNumber":"10010000003","name":"公民","sex":false,"birthday":706360953,"memo":"我婆婆好\n破破 in\n\n婆婆哦","qq":"786764","totalConsume":0,"level":"暂未开通","status":0,"integral":0,"img1":"Member/06/91/0691b63621a211e6b31074d02ba07f83.jpg","img2":"Member/06/a9/06a95ce721a211e6b31074d02ba07f83.jpg","img3":"Member/06/cf/06cfa99821a211e6b31074d02ba07f83.jpg","img4":"Member/06/df/06dffd4921a211e6b31074d02ba07f83.jpg","img5":"Member/06/ef/06ef3f8a21a211e6b31074d02ba07f83.jpg","type":0,"imgList":["Member/06/91/0691b63621a211e6b31074d02ba07f83.jpg","Member/06/a9/06a95ce721a211e6b31074d02ba07f83.jpg","Member/06/cf/06cfa99821a211e6b31074d02ba07f83.jpg","Member/06/df/06dffd4921a211e6b31074d02ba07f83.jpg","Member/06/ef/06ef3f8a21a211e6b31074d02ba07f83.jpg"]},{"id":145,"storeId":18,"mobile":"13497646","weixin":"jajdjshsjs","cardNumber":"10010000002","name":"明明","sex":true,"birthday":1464083938,"address":"吗dhmgwmhmgm","memo":"m那个命佛名模您\nMr Mr 名哦","qq":"576764346","totalConsume":0,"level":"暂未开通","status":0,"integral":0,"avatar":"MAvatar/20/d2/20d268bb219611e6914d74d02ba07f83.jpg","img1":"Member/b1/6f/b16fd18d21a311e6b31074d02ba07f83.jpg","img2":"Member/b1/80/b180c17e21a311e6b31074d02ba07f83.jpg","img3":"Member/b1/8e/b18ea42f21a311e6b31074d02ba07f83.jpg","img4":"Member/b1/91/b1913c4021a311e6b31074d02ba07f83.jpg","img5":"Member/b1/a1/b1a1b70121a311e6b31074d02ba07f83.jpg","type":2,"imgList":["Member/b1/6f/b16fd18d21a311e6b31074d02ba07f83.jpg","Member/b1/80/b180c17e21a311e6b31074d02ba07f83.jpg","Member/b1/8e/b18ea42f21a311e6b31074d02ba07f83.jpg","Member/b1/91/b1913c4021a311e6b31074d02ba07f83.jpg","Member/b1/a1/b1a1b70121a311e6b31074d02ba07f83.jpg"]},{"id":137,"storeId":18,"mobile":"13817673794","cardNumber":"10010000001","name":"工作","sex":false,"birthday":1464078997,"totalConsume":7680,"level":"暂未开通","status":0,"integral":7680,"type":0,"avatar":"1231313","isSelect":false,"imgList":[]}],"dataSize":3}
     */

    private String result;
    /**
     * dataList : [{"id":343,"storeId":18,"weixin":"zhshj","cardNumber":"10010000003","name":"公民","sex":false,"birthday":706360953,"memo":"我婆婆好\n破破 in\n\n婆婆哦","qq":"786764","totalConsume":0,"level":"暂未开通","status":0,"integral":0,"img1":"Member/06/91/0691b63621a211e6b31074d02ba07f83.jpg","img2":"Member/06/a9/06a95ce721a211e6b31074d02ba07f83.jpg","img3":"Member/06/cf/06cfa99821a211e6b31074d02ba07f83.jpg","img4":"Member/06/df/06dffd4921a211e6b31074d02ba07f83.jpg","img5":"Member/06/ef/06ef3f8a21a211e6b31074d02ba07f83.jpg","type":0,"imgList":["Member/06/91/0691b63621a211e6b31074d02ba07f83.jpg","Member/06/a9/06a95ce721a211e6b31074d02ba07f83.jpg","Member/06/cf/06cfa99821a211e6b31074d02ba07f83.jpg","Member/06/df/06dffd4921a211e6b31074d02ba07f83.jpg","Member/06/ef/06ef3f8a21a211e6b31074d02ba07f83.jpg"]},{"id":145,"storeId":18,"mobile":"13497646","weixin":"jajdjshsjs","cardNumber":"10010000002","name":"明明","sex":true,"birthday":1464083938,"address":"吗dhmgwmhmgm","memo":"m那个命佛名模您\nMr Mr 名哦","qq":"576764346","totalConsume":0,"level":"暂未开通","status":0,"integral":0,"avatar":"MAvatar/20/d2/20d268bb219611e6914d74d02ba07f83.jpg","img1":"Member/b1/6f/b16fd18d21a311e6b31074d02ba07f83.jpg","img2":"Member/b1/80/b180c17e21a311e6b31074d02ba07f83.jpg","img3":"Member/b1/8e/b18ea42f21a311e6b31074d02ba07f83.jpg","img4":"Member/b1/91/b1913c4021a311e6b31074d02ba07f83.jpg","img5":"Member/b1/a1/b1a1b70121a311e6b31074d02ba07f83.jpg","type":2,"imgList":["Member/b1/6f/b16fd18d21a311e6b31074d02ba07f83.jpg","Member/b1/80/b180c17e21a311e6b31074d02ba07f83.jpg","Member/b1/8e/b18ea42f21a311e6b31074d02ba07f83.jpg","Member/b1/91/b1913c4021a311e6b31074d02ba07f83.jpg","Member/b1/a1/b1a1b70121a311e6b31074d02ba07f83.jpg"]},{"id":137,"storeId":18,"mobile":"13817673794","cardNumber":"10010000001","name":"工作","sex":false,"birthday":1464078997,"totalConsume":7680,"level":"暂未开通","status":0,"integral":7680,"type":0,"avatar":"1231313","isSelect":false,"imgList":[]}]
     * dataSize : 3
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
        private int dataSize;
        /**
         * id : 343
         * storeId : 18
         * weixin : zhshj
         * cardNumber : 10010000003
         * name : 公民
         * sex : false
         * birthday : 706360953
         * memo : 我婆婆好
         破破 in

         婆婆哦
         * qq : 786764
         * totalConsume : 0
         * level : 暂未开通
         * status : 0
         * integral : 0
         * img1 : Member/06/91/0691b63621a211e6b31074d02ba07f83.jpg
         * img2 : Member/06/a9/06a95ce721a211e6b31074d02ba07f83.jpg
         * img3 : Member/06/cf/06cfa99821a211e6b31074d02ba07f83.jpg
         * img4 : Member/06/df/06dffd4921a211e6b31074d02ba07f83.jpg
         * img5 : Member/06/ef/06ef3f8a21a211e6b31074d02ba07f83.jpg
         * type : 0
         * imgList : ["Member/06/91/0691b63621a211e6b31074d02ba07f83.jpg","Member/06/a9/06a95ce721a211e6b31074d02ba07f83.jpg","Member/06/cf/06cfa99821a211e6b31074d02ba07f83.jpg","Member/06/df/06dffd4921a211e6b31074d02ba07f83.jpg","Member/06/ef/06ef3f8a21a211e6b31074d02ba07f83.jpg"]
         */

        private List<DataListEntity> dataList;

        public int getDataSize() {
            return dataSize;
        }

        public void setDataSize(int dataSize) {
            this.dataSize = dataSize;
        }

        public List<DataListEntity> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListEntity> dataList) {
            this.dataList = dataList;
        }

        public static class DataListEntity {
            private int id;
            private int storeId;
            private String weixin;
            private String cardNumber;
            private String name;
            private String mobile;
            private String avatar;
            private boolean sex;
            private boolean isSelect;
            private int birthday;
            private String memo;
            private String qq;
            private double totalConsume;
            private String level;
            private int status;
            private double integral;
            private String img1;
            private String img2;
            private String img3;
            private String img4;
            private String img5;
            private int type;
            private List<String> imgList;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
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

            public double getIntegral() {
                return integral;
            }

            public void setIntegral(double integral) {
                this.integral = integral;
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

            public String getImg5() {
                return img5;
            }

            public void setImg5(String img5) {
                this.img5 = img5;
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
}
