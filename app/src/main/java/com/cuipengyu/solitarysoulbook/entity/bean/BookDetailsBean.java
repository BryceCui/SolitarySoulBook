package com.cuipengyu.solitarysoulbook.entity.bean;

import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/6
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class BookDetailsBean {

    /**
     * books : [{"_id":"5a2a540043041f4615957423","hasCp":true,"title":"捡了本天书","aliases":"","cat":"武侠","author":"麻烦","site":"zhuishuvip","cover":"/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F2189371%2F2189371_d41f7b98b2d64a16ab110a9e0fa94e04.jpg%2F","shortIntro":"我叫二一，二是\u2026\u2026好吧，这不重要！重要的是，我被一本天上掉下来的书给砸破了头，然后我就开挂了！翻开了封面，我学会了[阅读术]、[解读术]、[概括术]、[解析术]\u2026\u2026妈妈再也不用担心我的学习了。翻开第一页，我学会了[医疗动物]、[动物交谈术]、[动物变异术]\u2026\u2026我是不是该去当个兽医，或者去国外买个农场？再翻到后面看看？咦？[即兴创作]、[美妙歌喉]、[完美容颜]\u2026\u2026我是不是该去混一下演艺圈？还有[治愈术]、[复活术]、[占卜术]、[预言术]\u2026\u2026我是不是应该去当个老军医，或者冒充神棍骗点钱花花？[蛛丝术]和[蛛行术]是什么鬼？逼着我我COS蜘蛛侠？那再学个[构装启智术]，岂不是要逼着我去当钢铁侠？","lastChapter":"第578章 贰弎支线：孙悟空","retentionRatio":55.46,"banned":0,"allowMonthly":false,"latelyFollower":8743,"wordCount":1256539,"contentType":"txt","superscript":"","sizetype":-1,"highlight":{"title":["捡","了","本","天","书"]}}]
     * total : 1
     * ok : true
     */

    private int total;
    private boolean ok;
    private List<BooksBean> books;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<BooksBean> getBooks() {
        return books;
    }

    public void setBooks(List<BooksBean> books) {
        this.books = books;
    }

    public static class BooksBean {
        /**
         * _id : 5a2a540043041f4615957423
         * hasCp : true
         * title : 捡了本天书
         * aliases :
         * cat : 武侠
         * author : 麻烦
         * site : zhuishuvip
         * cover : /agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F2189371%2F2189371_d41f7b98b2d64a16ab110a9e0fa94e04.jpg%2F
         * shortIntro : 我叫二一，二是……好吧，这不重要！重要的是，我被一本天上掉下来的书给砸破了头，然后我就开挂了！翻开了封面，我学会了[阅读术]、[解读术]、[概括术]、[解析术]……妈妈再也不用担心我的学习了。翻开第一页，我学会了[医疗动物]、[动物交谈术]、[动物变异术]……我是不是该去当个兽医，或者去国外买个农场？再翻到后面看看？咦？[即兴创作]、[美妙歌喉]、[完美容颜]……我是不是该去混一下演艺圈？还有[治愈术]、[复活术]、[占卜术]、[预言术]……我是不是应该去当个老军医，或者冒充神棍骗点钱花花？[蛛丝术]和[蛛行术]是什么鬼？逼着我我COS蜘蛛侠？那再学个[构装启智术]，岂不是要逼着我去当钢铁侠？
         * lastChapter : 第578章 贰弎支线：孙悟空
         * retentionRatio : 55.46
         * banned : 0
         * allowMonthly : false
         * latelyFollower : 8743
         * wordCount : 1256539
         * contentType : txt
         * superscript :
         * sizetype : -1
         * highlight : {"title":["捡","了","本","天","书"]}
         */

        private String _id;
        private boolean hasCp;
        private String title;
        private String aliases;
        private String cat;
        private String author;
        private String site;
        private String cover;
        private String shortIntro;
        private String lastChapter;
        private double retentionRatio;
        private int banned;
        private boolean allowMonthly;
        private int latelyFollower;
        private int wordCount;
        private String contentType;
        private String superscript;
        private int sizetype;
        private HighlightBean highlight;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public boolean isHasCp() {
            return hasCp;
        }

        public void setHasCp(boolean hasCp) {
            this.hasCp = hasCp;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAliases() {
            return aliases;
        }

        public void setAliases(String aliases) {
            this.aliases = aliases;
        }

        public String getCat() {
            return cat;
        }

        public void setCat(String cat) {
            this.cat = cat;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getShortIntro() {
            return shortIntro;
        }

        public void setShortIntro(String shortIntro) {
            this.shortIntro = shortIntro;
        }

        public String getLastChapter() {
            return lastChapter;
        }

        public void setLastChapter(String lastChapter) {
            this.lastChapter = lastChapter;
        }

        public double getRetentionRatio() {
            return retentionRatio;
        }

        public void setRetentionRatio(double retentionRatio) {
            this.retentionRatio = retentionRatio;
        }

        public int getBanned() {
            return banned;
        }

        public void setBanned(int banned) {
            this.banned = banned;
        }

        public boolean isAllowMonthly() {
            return allowMonthly;
        }

        public void setAllowMonthly(boolean allowMonthly) {
            this.allowMonthly = allowMonthly;
        }

        public int getLatelyFollower() {
            return latelyFollower;
        }

        public void setLatelyFollower(int latelyFollower) {
            this.latelyFollower = latelyFollower;
        }

        public int getWordCount() {
            return wordCount;
        }

        public void setWordCount(int wordCount) {
            this.wordCount = wordCount;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getSuperscript() {
            return superscript;
        }

        public void setSuperscript(String superscript) {
            this.superscript = superscript;
        }

        public int getSizetype() {
            return sizetype;
        }

        public void setSizetype(int sizetype) {
            this.sizetype = sizetype;
        }

        public HighlightBean getHighlight() {
            return highlight;
        }

        public void setHighlight(HighlightBean highlight) {
            this.highlight = highlight;
        }

        public static class HighlightBean {
            private List<String> title;

            public List<String> getTitle() {
                return title;
            }

            public void setTitle(List<String> title) {
                this.title = title;
            }
        }
    }
}
