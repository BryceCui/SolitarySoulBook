package com.cuipengyu.solitarysoulbook.model.httphelper;

import com.cuipengyu.solitarysoulbook.SelectBean;
import com.cuipengyu.solitarysoulbook.base.BaseBean;
import com.cuipengyu.solitarysoulbook.utils.LogUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

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
    private static JsoupHelper helper;

    public static JsoupHelper getHelper() {
        if (helper == null) return new JsoupHelper();
        return helper;
    }

    @Override
    public <T extends BaseBean> void getHtmlStringData(final String select, final int index, final CallBack<T> callBack) {
        Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                // TODO: 2018/4/11  添加switch进行选择判断
                SelectBean selectBean = new SelectBean();
                List<SelectBean.SelectBook> selectBook = new ArrayList<>();
                Document document = Jsoup.connect("http://www.zhuishushenqi.com/selection/" + select + "?page=" + index).get();
                Elements elements = document.select("div.books-list");
                Elements links = elements.select("a");
                for (int i = 0; i < links.size(); i++) {
                    String src = links.select("img").attr("src");
                    String id = links.select("a").attr("href");
                    String name = links.select("div.right").select("h4.name").first().text();
                    String name1 = links.select("div.right").select("p.desc").first().text();
                    String name2 = links.select("div.right").select("p.popularity").text();
                    SelectBean.SelectBook selectBook1 = new SelectBean.SelectBook();
                    selectBook1.setBook_Id(id);
                    selectBook.add(selectBook1);
                }
                selectBean.setSelectBooks_List(selectBook);
                LogUtils.e(selectBean.getSelectBooks_List().size() + "");
                emitter.onNext((T) selectBean);

            }
        }).subscribeOn(Schedulers.io()).subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(T element) {
                callBack.onSucces(element);
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
