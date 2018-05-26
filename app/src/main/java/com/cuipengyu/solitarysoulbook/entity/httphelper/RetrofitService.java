package com.cuipengyu.solitarysoulbook.entity.httphelper;


import com.cuipengyu.solitarysoulbook.entity.bean.AutomaticBean;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterLink;
import com.cuipengyu.solitarysoulbook.entity.bean.HotWord;

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
    Observable<ChapterLink> getChapterList(@Path("bookId") String bookId);

    /**
     * 小说搜索推荐
     *
     * @return
     */
    @GET("book/hot-word")
    Observable<HotWord> getHotWord();

    /**
     * 小说搜索关键字自动补全
     */
    @GET("/book/auto-complete")
    Single<AutomaticBean> getAutoMaticSearch(@Query("query") String query);
}
