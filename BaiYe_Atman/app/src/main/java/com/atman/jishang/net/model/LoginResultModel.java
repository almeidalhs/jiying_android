package com.atman.jishang.net.model;

/**
 * 描述 登陆结果实体类
 * 作者 tangbingliang
 * 时间 16/4/11 10:05
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class LoginResultModel {

    /**
     * result : 1
     * body : {"id":17,"memberName":"13854725536","memberTrueName":"商友-10","atmanUserId":100003783,"memberMobile":"13854725536","storeId":11,"memberAvatar":"Avatar/17/f5/17f5d822030b11e6bd2e74d02ba07f83.jpg","memberSex":0,"memberBirthday":"1990-01-01 00:00:00","memberPasswd":"123456","memberEmail":"13854725536@jimall.com","memberLoginNum":1,"memberGoldNum":200,"memberGoldCount":0,"memberGoldNumMinus":0,"memberQqInfo":"4dc01e34054411e6873674d02ba07f83","memberPoints":0,"availablePredeposit":0,"freezePredeposit":0,"informAllow":1,"isBuy":1,"isAllowTalk":1,"memberState":1,"memberCredit":0,"memberSnsVisitNum":0}
     */

    private String result;
    private BodyEntity body;

    public void setResult(String result) {
        this.result = result;
    }

    public void setBody(BodyEntity body) {
        this.body = body;
    }

    public String getResult() {
        return result;
    }

    public BodyEntity getBody() {
        return body;
    }

    public static class BodyEntity {
        /**
         * id : 17
         * memberName : 13854725536
         * memberTrueName : 商友-10
         * atmanUserId : 100003783
         * memberMobile : 13854725536
         * storeId : 11
         * memberAvatar : Avatar/17/f5/17f5d822030b11e6bd2e74d02ba07f83.jpg
         * memberSex : 0
         * memberBirthday : 1990-01-01 00:00:00
         * memberPasswd : 123456
         * memberEmail : 13854725536@jimall.com
         * memberLoginNum : 1
         * memberGoldNum : 200
         * memberGoldCount : 0
         * memberGoldNumMinus : 0
         * memberQqInfo : 4dc01e34054411e6873674d02ba07f83
         * memberPoints : 0
         * availablePredeposit : 0.0
         * freezePredeposit : 0.0
         * informAllow : 1
         * isBuy : 1
         * isAllowTalk : 1
         * memberState : 1
         * memberCredit : 0
         * memberSnsVisitNum : 0
         */

        private int id;
        private String memberName;
        private String memberTrueName;
        private int atmanUserId;
        private String memberMobile;
        private int storeId;
        private String memberAvatar;
        private int memberSex;
        private String memberBirthday;
        private String memberPasswd;
        private String memberEmail;
        private int memberLoginNum;
        private int memberGoldNum;
        private int memberGoldCount;
        private int memberGoldNumMinus;
        private String memberQqInfo;
        private int memberPoints;
        private double availablePredeposit;
        private double freezePredeposit;
        private int informAllow;
        private int isBuy;
        private int isAllowTalk;
        private int memberState;
        private int memberCredit;
        private int memberSnsVisitNum;
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public void setMemberTrueName(String memberTrueName) {
            this.memberTrueName = memberTrueName;
        }

        public void setAtmanUserId(int atmanUserId) {
            this.atmanUserId = atmanUserId;
        }

        public void setMemberMobile(String memberMobile) {
            this.memberMobile = memberMobile;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public void setMemberAvatar(String memberAvatar) {
            this.memberAvatar = memberAvatar;
        }

        public void setMemberSex(int memberSex) {
            this.memberSex = memberSex;
        }

        public void setMemberBirthday(String memberBirthday) {
            this.memberBirthday = memberBirthday;
        }

        public void setMemberPasswd(String memberPasswd) {
            this.memberPasswd = memberPasswd;
        }

        public void setMemberEmail(String memberEmail) {
            this.memberEmail = memberEmail;
        }

        public void setMemberLoginNum(int memberLoginNum) {
            this.memberLoginNum = memberLoginNum;
        }

        public void setMemberGoldNum(int memberGoldNum) {
            this.memberGoldNum = memberGoldNum;
        }

        public void setMemberGoldCount(int memberGoldCount) {
            this.memberGoldCount = memberGoldCount;
        }

        public void setMemberGoldNumMinus(int memberGoldNumMinus) {
            this.memberGoldNumMinus = memberGoldNumMinus;
        }

        public void setMemberQqInfo(String memberQqInfo) {
            this.memberQqInfo = memberQqInfo;
        }

        public void setMemberPoints(int memberPoints) {
            this.memberPoints = memberPoints;
        }

        public void setAvailablePredeposit(double availablePredeposit) {
            this.availablePredeposit = availablePredeposit;
        }

        public void setFreezePredeposit(double freezePredeposit) {
            this.freezePredeposit = freezePredeposit;
        }

        public void setInformAllow(int informAllow) {
            this.informAllow = informAllow;
        }

        public void setIsBuy(int isBuy) {
            this.isBuy = isBuy;
        }

        public void setIsAllowTalk(int isAllowTalk) {
            this.isAllowTalk = isAllowTalk;
        }

        public void setMemberState(int memberState) {
            this.memberState = memberState;
        }

        public void setMemberCredit(int memberCredit) {
            this.memberCredit = memberCredit;
        }

        public void setMemberSnsVisitNum(int memberSnsVisitNum) {
            this.memberSnsVisitNum = memberSnsVisitNum;
        }

        public int getId() {
            return id;
        }

        public String getMemberName() {
            return memberName;
        }

        public String getMemberTrueName() {
            return memberTrueName;
        }

        public int getAtmanUserId() {
            return atmanUserId;
        }

        public String getMemberMobile() {
            return memberMobile;
        }

        public int getStoreId() {
            return storeId;
        }

        public String getMemberAvatar() {
            return memberAvatar;
        }

        public int getMemberSex() {
            return memberSex;
        }

        public String getMemberBirthday() {
            return memberBirthday;
        }

        public String getMemberPasswd() {
            return memberPasswd;
        }

        public String getMemberEmail() {
            return memberEmail;
        }

        public int getMemberLoginNum() {
            return memberLoginNum;
        }

        public int getMemberGoldNum() {
            return memberGoldNum;
        }

        public int getMemberGoldCount() {
            return memberGoldCount;
        }

        public int getMemberGoldNumMinus() {
            return memberGoldNumMinus;
        }

        public String getMemberQqInfo() {
            return memberQqInfo;
        }

        public int getMemberPoints() {
            return memberPoints;
        }

        public double getAvailablePredeposit() {
            return availablePredeposit;
        }

        public double getFreezePredeposit() {
            return freezePredeposit;
        }

        public int getInformAllow() {
            return informAllow;
        }

        public int getIsBuy() {
            return isBuy;
        }

        public int getIsAllowTalk() {
            return isAllowTalk;
        }

        public int getMemberState() {
            return memberState;
        }

        public int getMemberCredit() {
            return memberCredit;
        }

        public int getMemberSnsVisitNum() {
            return memberSnsVisitNum;
        }
    }
}
