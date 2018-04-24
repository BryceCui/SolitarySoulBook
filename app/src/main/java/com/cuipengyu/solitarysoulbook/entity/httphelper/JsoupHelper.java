package com.cuipengyu.solitarysoulbook.entity.httphelper;

import com.cuipengyu.solitarysoulbook.entity.Constants;
import com.cuipengyu.solitarysoulbook.entity.bean.SelectBean;
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
    private String Select_Featured = "";

    public static JsoupHelper getHelper() {
        if (helper == null) return new JsoupHelper();
        return helper;
    }

    @Override
    public void getHtmlStringData(final String select, final int index, final CallBack<SelectBean> callBack) {
        Observable.create(new ObservableOnSubscribe<SelectBean>() {
            @Override
            public void subscribe(ObservableEmitter<SelectBean> emitter) throws Exception {
                // TODO: 2018/4/11  添加switch进行选择判断
                switch (select) {
                    case Constants.GIRL:
                        Select_Featured = Constants.GIRL;
                        break;
                    case Constants.GIRL_FINISHED_BOOK:
                        Select_Featured = Constants.GIRL_FINISHED_BOOK;
                        break;
                    case Constants.GIRL_NEW_BOOK:
                        Select_Featured = Constants.GIRL_NEW_BOOK;
                        break;
                    case Constants.MALE:
                        Select_Featured = Constants.MALE;
                        break;
                    case Constants.MALE_FINISHED_BOOK:
                        Select_Featured = Constants.MALE_FINISHED_BOOK;
                        break;
                    case Constants.MALE_NEW_BOOK:
                        Select_Featured = Constants.MALE_NEW_BOOK;
                        break;
                    case Constants.POPULAR_WEEK:
                        Select_Featured = Constants.POPULAR_WEEK;
                        break;
                    case Constants.PUBLISHING:
                        Select_Featured = Constants.PUBLISHING;
                        break;

                }
                SelectBean selectBean = null;
                selectBean = new SelectBean();
                List<SelectBean.SelectBook> selectBook_List = null;
                selectBook_List = new ArrayList<>();
                Document document = Jsoup.connect("http://www.zhuishushenqi.com/selection/" + Select_Featured + Constants.PAGE + index).get();
                Elements elements = document.select("div.books-list");
                Elements links = elements.select("a");
                Elements div_right = links.select("div.right");
                for (int i = 0; i < links.size(); i++) {
                    SelectBean.SelectBook selectBook = null;
                    String img_link = links.select("img").attr("src");
                    String book_id = links.select("a").attr("href");
                    String name = div_right.select("h4.name").first().text();
                    String Introduction = div_right.select("p.desc").first().text();
                    String author_Statua = div_right.select("p.popularity").text();
                    selectBook = new SelectBean.SelectBook();
                    selectBook.setBook_Id(book_id);
                    selectBook.setBook_Id(img_link);
                    selectBook.setBook_Id(name);
                    selectBook.setBook_Id(Introduction);
                    selectBook.setBook_Id(author_Statua);
                    selectBook_List.add(selectBook);
                }
                selectBean.setSelectBooks_List(selectBook_List);
                LogUtils.e(selectBean.getSelectBooks_List().size() + "");
                emitter.onNext(selectBean);

            }
        }).subscribeOn(Schedulers.io()).subscribe(new Observer<SelectBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SelectBean element) {
                callBack.onSucces(element);
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
