package com.cuipengyu.solitarysoulbook.entity.httphelper;


import com.cuipengyu.solitarysoulbook.entity.bean.AutomaticBean;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCityRecommendBean;
import com.cuipengyu.solitarysoulbook.entity.bean.BookCitySpread;
import com.cuipengyu.solitarysoulbook.entity.bean.BookDetailsBean;
import com.cuipengyu.solitarysoulbook.entity.bean.BookDetailsId;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterBody;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterLink;
import com.cuipengyu.solitarysoulbook.entity.bean.HotWord;
import com.cuipengyu.solitarysoulbook.entity.bean.RankingAllBean;
import com.cuipengyu.solitarysoulbook.entity.bean.RankingBookBean;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 网络请求服务接口
 * Created by cuipengyu on 2018/4/2
 */
public interface RetrofitService {
    @Deprecated
    @GET()
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String, String> map);

    @Deprecated
    @POST()
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String, String> map);

    /**
     * 获取小说章节
     *
     * @param bookId
     * @return
     */
    @GET("mix-atoc/{bookId}")
    Single<ChapterLink> getChapterList(@Path("bookId") String bookId);

    /**
     * 小说搜索推荐
     *
     * @return
     */
    @GET("book/hot-word")
    Single<HotWord> getHotWord();

    /**
     * 小说搜索关键字自动补全
     */
    @GET("/book/auto-complete")
    Single<AutomaticBean> getAutoMaticSearch(@Query("query") String query);

    /**
     * 书籍查询
     *
     * @param query:作者名或者书名
     * @return
     */
    @GET("/book/fuzzy-search")
    Single<BookDetailsBean> getSearchBookPackage(@Query("query") String query);

    @GET("spread")
    Single<BookCitySpread> getSpread();

    /**
     * 推荐书籍
     * @return
     */
    @GET("/book/recommend")
    Single<BookCityRecommendBean> getRecommendBookPackage();
    @GET()
    Single<ChapterBody> getChapterBody(@Url String url);

    /**
     * 获取所有排行榜
     *
     * @return
     */
    @GET("/ranking/gender")
    Single<RankingAllBean> getRankingAll();
    @GET("/ranking/{rankingId}")
    Single<RankingBookBean> getRankingBook(@Path("rankingId") String rankingId);
    /**
     * 书籍详情
     * @param bookId
     * @return
     */
    @GET("/book/{bookId}")
    Single<BookDetailsId> getBookDetailId(@Path("bookId") String bookId);


}
