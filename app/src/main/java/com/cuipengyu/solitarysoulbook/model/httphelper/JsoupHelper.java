package com.cuipengyu.solitarysoulbook.model.httphelper;

import android.util.Log;

import com.cuipengyu.solitarysoulbook.base.BaseBean;
import com.cuipengyu.solitarysoulbook.utils.LogUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/11
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class JsoupHelper implements JsoupEngine {
    @Override
    public <T extends BaseBean> void getHtmlStringData(final Class<T> bean, final String select, final int index, final CallBack<T> callBack) {
        Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                // TODO: 2018/4/11  添加switch进行选择判断
                Document document = Jsoup.connect("http://www.zhuishushenqi.com/selection/" + select + "?page=" + index).get();
                Elements elements = document.select("div.books-list");
                Elements links = elements.select("a");
                for (Element element : links) {
                    String src = element.select("img").attr("src");
                    String id = element.select("a").attr("href");
                    String name = element.select("div.right").select("h4.name").first().text();
                    String name1 = element.select("div.right").select("p.desc").first().text();
                    String name2 = element.select("div.right").select("p.popularity").text();
                    Log.e("recommend--src-", src);
                    Log.e("recommend--id-", id);
                    Log.e("recommend--name-", name);
                    Log.e("recommend--name1-", name1);
                    Log.e("recommend--name2-", name2);
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(T element) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
