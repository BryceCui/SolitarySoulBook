package com.cuipengyu.solitarysoulbook.entity.bean;

import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/6
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BookCitySpread {

    /**
     * data : [{"_id":"5b150dfc2a3175e238cf6439","__v":0,"type":"c-booklist","link":"5b14fd424d27fd94398198f7","fullDes":"环境日书单","simpleDes":"环境日书单","img":"http://statics.zhuishushenqi.com/recommendPage/152816354506234","title":"环境日书单","order":1,"node":{"_id":"591284964e7760c035fec2cf","order":1,"sex":"none"},"page":"5910018c8094b1e228e5868f","platforms":["ios","android"]},{"_id":"5b149950b191923e73ad001e","__v":0,"type":"c-bookdetail","link":"52596091dd65cec13f0005af","fullDes":"同学两亿岁","simpleDes":"同学两亿岁","img":"http://statics.zhuishushenqi.com/recommendPage/152807654621844","title":"同学两亿岁","order":2,"node":{"_id":"591284964e7760c035fec2cf","order":1,"sex":"none"},"page":"5910018c8094b1e228e5868f","platforms":["android","ios"]},{"_id":"5b0f687841a1ff2059060c84","__v":0,"type":"c-bookdetail","link":"59c230b17212e3e3019eda06","fullDes":"极速闪婚：天价老公很耀眼","simpleDes":"极速闪婚：天价老公很耀眼","img":"http://statics.zhuishushenqi.com/recommendPage/152773643648112","title":"极速闪婚：天价老公很耀眼","order":3,"node":{"_id":"591284964e7760c035fec2cf","order":1,"sex":"none"},"page":"5910018c8094b1e228e5868f","platforms":["android","ios"]},{"_id":"5b0f68f80fbf21005ac86fca","__v":0,"type":"c-bookdetail","link":"59560cb103f1b121636456b1","fullDes":"神医凰后：傲娇暴君，强势宠！","simpleDes":"神医凰后：傲娇暴君，强势宠！","img":"http://statics.zhuishushenqi.com/recommendPage/152773654171217","title":"神医凰后：傲娇暴君，强势宠！","order":4,"node":{"_id":"591284964e7760c035fec2cf","order":1,"sex":"none"},"page":"5910018c8094b1e228e5868f","platforms":["android","ios"]}]
     * ok : true
     */

    private boolean ok;
    private List<DataBean> data;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * _id : 5b150dfc2a3175e238cf6439
         * __v : 0
         * type : c-booklist
         * link : 5b14fd424d27fd94398198f7
         * fullDes : 环境日书单
         * simpleDes : 环境日书单
         * img : http://statics.zhuishushenqi.com/recommendPage/152816354506234
         * title : 环境日书单
         * order : 1
         * node : {"_id":"591284964e7760c035fec2cf","order":1,"sex":"none"}
         * page : 5910018c8094b1e228e5868f
         * platforms : ["ios","android"]
         */

        private String _id;
        private int __v;
        private String type;
        private String link;
        private String fullDes;
        private String simpleDes;
        private String img;
        private String title;
        private int order;
        private NodeBean node;
        private String page;
        private List<String> platforms;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getFullDes() {
            return fullDes;
        }

        public void setFullDes(String fullDes) {
            this.fullDes = fullDes;
        }

        public String getSimpleDes() {
            return simpleDes;
        }

        public void setSimpleDes(String simpleDes) {
            this.simpleDes = simpleDes;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public NodeBean getNode() {
            return node;
        }

        public void setNode(NodeBean node) {
            this.node = node;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public List<String> getPlatforms() {
            return platforms;
        }

        public void setPlatforms(List<String> platforms) {
            this.platforms = platforms;
        }

        public static class NodeBean {
            /**
             * _id : 591284964e7760c035fec2cf
             * order : 1
             * sex : none
             */

            private String _id;
            private int order;
            private String sex;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }
        }
    }
}
