package com.atman.jishang.net.model;

/**
 * 描述 更新头像model
 * 作者 tangbingliang
 * 时间 16/4/20 10:58
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class UpdateHeadImgModel {
    /**
     * result : 1
     * body : {"updateTime":1461121012506,"createTime":1459905911000,"id":17,"memberName":"13854725536","memberTrueName":"商友-10","atmanUserId":100003783,"memberMobile":"13854725536","storeId":11,"memberAvatar":"Avatar/6a/75/6a750d25b789420baf794de71446ca1a.jpg","memberSex":0,"memberBirthday":631123200000,"memberPasswd":"123456","memberEmail":"13854725536@jimall.com","memberLoginNum":1,"memberGoldNum":200,"memberGoldCount":0,"memberGoldNumMinus":0,"memberPoints":0,"availablePredeposit":0,"freezePredeposit":0,"informAllow":1,"isBuy":1,"isAllowTalk":1,"memberState":1,"memberCredit":0,"memberSnsVisitNum":0}
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
         * updateTime : 1461121012506
         * createTime : 1459905911000
         * id : 17
         * memberName : 13854725536
         * memberTrueName : 商友-10
         * atmanUserId : 100003783
         * memberMobile : 13854725536
         * storeId : 11
         * memberAvatar : Avatar/6a/75/6a750d25b789420baf794de71446ca1a.jpg
         * memberSex : 0
         * memberBirthday : 631123200000
         * memberPasswd : 123456
         * memberEmail : 13854725536@jimall.com
         * memberLoginNum : 1
         * memberGoldNum : 200
         * memberGoldCount : 0
         * memberGoldNumMinus : 0
         * memberPoints : 0
         * availablePredeposit : 0
         * freezePredeposit : 0
         * informAllow : 1
         * isBuy : 1
         * isAllowTalk : 1
         * memberState : 1
         * memberCredit : 0
         * memberSnsVisitNum : 0
         */

        private long updateTime;
        private long createTime;
        private int id;
        private String memberName;
        private String memberTrueName;
        private int atmanUserId;
        private String memberMobile;
        private int storeId;
        private String memberAvatar;
        private int memberSex;
        private long memberBirthday;
        private String memberPasswd;
        private String memberEmail;
        private int memberLoginNum;
        private int memberGoldNum;
        private int memberGoldCount;
        private int memberGoldNumMinus;
        private int memberPoints;
        private int availablePredeposit;
        private int freezePredeposit;
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

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
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

        public void setMemberBirthday(long memberBirthday) {
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

        public void setMemberPoints(int memberPoints) {
            this.memberPoints = memberPoints;
        }

        public void setAvailablePredeposit(int availablePredeposit) {
            this.availablePredeposit = availablePredeposit;
        }

        public void setFreezePredeposit(int freezePredeposit) {
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

        public long getUpdateTime() {
            return updateTime;
        }

        public long getCreateTime() {
            return createTime;
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

        public long getMemberBirthday() {
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

        public int getMemberPoints() {
            return memberPoints;
        }

        public int getAvailablePredeposit() {
            return availablePredeposit;
        }

        public int getFreezePredeposit() {
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
