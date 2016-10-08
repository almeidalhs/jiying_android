package com.atman.jishang.net.model;

import java.util.List;

/**
 * 描述 首页gridview数据
 * 作者 tangbingliang
 * 时间 16/4/20 13:57
 * 邮箱 bltang@atman.com
 * 电话 18578909061
 */
public class HomeGridViewDataModel {
    /**
     * result : 1
     * body : [{"id":37,"userId":17,"consoleId":2,"ucSort":0,"ucConsoleSort":1,"ucState":1,"consoleBean":{"id":2,"consoleName":"新增商品","consoleTitle":"xzsp","consoleBgImage":"a4efff","consoleImageUrl":"http://192.168.1.141:8000/by/Console/1goods.png","consoleUrl":"","consoleSort":1,"consoleType":1,"consoleState":1}},{"id":38,"userId":17,"consoleId":3,"ucSort":1,"ucConsoleSort":2,"ucState":1,"consoleBean":{"id":3,"consoleName":"店铺预览","consoleTitle":"dpyl","consoleBgImage":"fedb6e","consoleImageUrl":"http://192.168.1.141:8000/by/Console/2goods.png","consoleSort":2,"consoleType":2,"consoleState":0}},{"id":36,"userId":17,"consoleId":1,"ucSort":2,"ucConsoleSort":0,"ucState":1,"consoleBean":{"id":1,"consoleName":"商品管理","consoleTitle":"spgl","consoleBgImage":"5cce8c","consoleImageUrl":"http://192.168.1.141:8000/by/Console/0goods.png","consoleSort":0,"consoleType":0,"consoleState":1}},{"id":40,"userId":17,"consoleId":5,"ucSort":3,"ucConsoleSort":4,"ucState":1,"consoleBean":{"id":5,"consoleName":"新增Url","consoleTitle":"addurl","consoleBgImage":"7B0066","consoleImageUrl":"http://192.168.1.141:8000/by/Console/1.png","consoleUrl":"http://www.sina.com.cn","consoleSort":4,"consoleType":4,"consoleState":1}},{"id":39,"userId":17,"consoleId":4,"ucSort":4,"ucConsoleSort":3,"ucState":2,"consoleBean":{"id":4,"consoleName":"更多","consoleTitle":"more","consoleBgImage":"a4efff","consoleImageUrl":"http://192.168.1.141:8000/by/Console/3goods.png","consoleSort":3,"consoleType":3,"consoleState":2}}]
     */

    private String result;
    private List<BodyEntity> body;

    public void setResult(String result) {
        this.result = result;
    }

    public void setBody(List<BodyEntity> body) {
        this.body = body;
    }

    public String getResult() {
        return result;
    }

    public List<BodyEntity> getBody() {
        return body;
    }

    public static class BodyEntity {
        /**
         * id : 37
         * userId : 17
         * consoleId : 2
         * ucSort : 0
         * ucConsoleSort : 1
         * ucState : 1
         * consoleBean : {"id":2,"consoleName":"新增商品","consoleTitle":"xzsp","consoleBgImage":"a4efff","consoleImageUrl":"http://192.168.1.141:8000/by/Console/1goods.png","consoleUrl":"","consoleSort":1,"consoleType":1,"consoleState":1}
         */

        private int id;
        private int userId;
        private int consoleId;
        private int ucSort;
        private int ucConsoleSort;
        private int ucState;
        private ConsoleBeanEntity consoleBean;

        public void setId(int id) {
            this.id = id;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setConsoleId(int consoleId) {
            this.consoleId = consoleId;
        }

        public void setUcSort(int ucSort) {
            this.ucSort = ucSort;
        }

        public void setUcConsoleSort(int ucConsoleSort) {
            this.ucConsoleSort = ucConsoleSort;
        }

        public void setUcState(int ucState) {
            this.ucState = ucState;
        }

        public void setConsoleBean(ConsoleBeanEntity consoleBean) {
            this.consoleBean = consoleBean;
        }

        public int getId() {
            return id;
        }

        public int getUserId() {
            return userId;
        }

        public int getConsoleId() {
            return consoleId;
        }

        public int getUcSort() {
            return ucSort;
        }

        public int getUcConsoleSort() {
            return ucConsoleSort;
        }

        public int getUcState() {
            return ucState;
        }

        public ConsoleBeanEntity getConsoleBean() {
            return consoleBean;
        }

        public static class ConsoleBeanEntity {
            /**
             * id : 2
             * consoleName : 新增商品
             * consoleTitle : xzsp
             * consoleBgImage : a4efff
             * consoleImageUrl : http://192.168.1.141:8000/by/Console/1goods.png
             * consoleUrl :
             * consoleSort : 1
             * consoleType : 1
             * consoleState : 1
             */

            private int id;
            private String consoleName;
            private String consoleTitle;
            private String consoleBgImage;
            private String consoleImageUrl;
            private String consoleUrl;
            private int consoleSort;
            private int consoleType;
            private int consoleState;

            public void setId(int id) {
                this.id = id;
            }

            public void setConsoleName(String consoleName) {
                this.consoleName = consoleName;
            }

            public void setConsoleTitle(String consoleTitle) {
                this.consoleTitle = consoleTitle;
            }

            public void setConsoleBgImage(String consoleBgImage) {
                this.consoleBgImage = consoleBgImage;
            }

            public void setConsoleImageUrl(String consoleImageUrl) {
                this.consoleImageUrl = consoleImageUrl;
            }

            public void setConsoleUrl(String consoleUrl) {
                this.consoleUrl = consoleUrl;
            }

            public void setConsoleSort(int consoleSort) {
                this.consoleSort = consoleSort;
            }

            public void setConsoleType(int consoleType) {
                this.consoleType = consoleType;
            }

            public void setConsoleState(int consoleState) {
                this.consoleState = consoleState;
            }

            public int getId() {
                return id;
            }

            public String getConsoleName() {
                return consoleName;
            }

            public String getConsoleTitle() {
                return consoleTitle;
            }

            public String getConsoleBgImage() {
                return consoleBgImage;
            }

            public String getConsoleImageUrl() {
                return consoleImageUrl;
            }

            public String getConsoleUrl() {
                return consoleUrl;
            }

            public int getConsoleSort() {
                return consoleSort;
            }

            public int getConsoleType() {
                return consoleType;
            }

            public int getConsoleState() {
                return consoleState;
            }
        }
    }
}
