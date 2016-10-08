package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述
 * 作者 tangbingliang
 * 时间 16/6/1 17:38
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class AddRecordFullCutListModel {
    /**
     * result : 1
     * body : [{"id":54,"mansongId":25,"price":100,"discount":10,"startTime":1464404640,"endTime":1464836640}]
     */

    private String result;
    /**
     * id : 54
     * mansongId : 25
     * price : 100
     * discount : 10
     * startTime : 1464404640
     * endTime : 1464836640
     */

    private List<BodyEntity> body;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<BodyEntity> getBody() {
        return body;
    }

    public void setBody(List<BodyEntity> body) {
        this.body = body;
    }

    public static class BodyEntity {
        private int id;
        private int mansongId;
        private int price;
        private int state;
        private int discount;
        private int startTime;
        private int endTime;
        private boolean isSelect;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMansongId() {
            return mansongId;
        }

        public void setMansongId(int mansongId) {
            this.mansongId = mansongId;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }
    }
}
