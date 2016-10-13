package com.atman.jishang.net.model;

import java.util.List;

/**
 * Created by tangbingliang on 16/10/13.
 */

public class GetIndustryTitleConfigModel {

    private String result;

    private List<BodyBean> body;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        private int id;
        private int scId;
        private int pageNum;
        private String title;
        private int sort;
        /**
         * updateTime : 1473659231000
         * createTime : 1473659231000
         * id : 135
         * confPageId : 75
         * title : 店铺名称
         * icon : 0
         * sort : 1
         */

        private List<ConfPageBodyListBean> confPageBodyList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getScId() {
            return scId;
        }

        public void setScId(int scId) {
            this.scId = scId;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public List<ConfPageBodyListBean> getConfPageBodyList() {
            return confPageBodyList;
        }

        public void setConfPageBodyList(List<ConfPageBodyListBean> confPageBodyList) {
            this.confPageBodyList = confPageBodyList;
        }

        public static class ConfPageBodyListBean {
            private long updateTime;
            private long createTime;
            private int id;
            private int confPageId;
            private String title;
            private String icon;
            private int sort;

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getConfPageId() {
                return confPageId;
            }

            public void setConfPageId(int confPageId) {
                this.confPageId = confPageId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}
