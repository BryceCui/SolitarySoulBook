package com.cuipengyu.solitarysoulbook.entity.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/5/16
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class HotWord {

    private boolean ok;
    private List<String> hotWords;
    private List<NewHotWordsBean> newHotWords;

    public String getTitle() {
        /*
      hotWords : ["宠妾作死日常","谢家皇后","龙族2悼亡者之瞳","谢家皇后","魔鬼总裁的小新娘：豪门弃妇","斗罗大陆","庶女当嫁：盛宠世子妃","重生嫡妃：农女有点田","蜜桃小情人之烈爱知夏","狼性总裁勾上门","二婚皇后：魅颜嫡女乱后宫","凤谋天下：王爷休想逃","极品透视学生","元尊","阴间神探","剑来","无敌剑域","大魏宫廷","逆天邪神","美食供应商","我只想当一个安静的学霸"]
      newHotWords : [{"word":"宠妾作死日常","book":"582d06e012810b59753fd918"},{"word":"谢家皇后","book":"5677bc0f63d473b424407652"},{"word":"龙族2悼亡者之瞳","book":"59f9fd80954940412fb430a6"},{"word":"谢家皇后","book":"5677bc0f63d473b424407652"},{"word":"魔鬼总裁的小新娘：豪门弃妇","book":"56f4ef012705bd123dcae632"},{"word":"斗罗大陆","book":"5953237a7d5497c2703972ac"},{"word":"庶女当嫁：盛宠世子妃","book":"585cd68f0118775450da9139"},{"word":"重生嫡妃：农女有点田","book":"5ab228376223c87b54aee86f"},{"word":"蜜桃小情人之烈爱知夏","book":"59efedf4ff1e0648403a1cc2"},{"word":"狼性总裁勾上门","book":"598abc0d2e33ce5d678f0b58"},{"word":"二婚皇后：魅颜嫡女乱后宫","book":"5863b5cbc610df8c364b436c"},{"word":"凤谋天下：王爷休想逃","book":"5874d88de3dab1ae1d0cf751"},{"word":"极品透视学生","book":"58205164915478b501b731e6"},{"word":"元尊","book":"59ba0dbb017336e411085a4e"},{"word":"阴间神探","book":"5a1fe108c6fc8dda02ec682f"},{"word":"剑来","book":"592fe687c60e3c4926b040ca"},{"word":"无敌剑域","book":"54ad1582ad74f37426dd961e"},{"word":"大魏宫廷","book":"5662ca66b8cb23ce21ba25f2"},{"word":"逆天邪神","book":"542a5838a5ae10f815039a7f"},{"word":"美食供应商","book":"5790bc09e72826f52571f63c"},{"word":"我只想当一个安静的学霸","book":"5a534e9d6c81b81b7030beb5"}]
      ok : true
     */
        String title = "热门搜索";
        return title == null ? "" : title;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public int getHotSize() {
        if (getHotWords() == null) {
            return 0;
        } else {
            return getHotWords().size();
        }
    }

    public List<String> getHotWords() {
        return hotWords;
    }

    public void setHotWords(List<String> hotWords) {
        this.hotWords = hotWords;
    }

    public List<NewHotWordsBean> getNewHotWords() {
        if (newHotWords == null) return new ArrayList<>();
        return newHotWords;
    }

//    public List<NewHotWordsBean> getNewHotWords() {
//        return newHotWords;
//    }

    public void setNewHotWords(List<NewHotWordsBean> newHotWords) {
        this.newHotWords = newHotWords;
    }

    public static class NewHotWordsBean {
        /**
         * word : 宠妾作死日常
         * book : 582d06e012810b59753fd918
         */

        private String word;
        private String book;

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getBook() {
            return book;
        }

        public void setBook(String book) {
            this.book = book;
        }
    }
}
