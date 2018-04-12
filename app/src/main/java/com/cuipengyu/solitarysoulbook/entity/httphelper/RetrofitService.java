package com.cuipengyu.solitarysoulbook.entity.httphelper;


import com.cuipengyu.solitarysoulbook.entity.bean.MixTocBeanCon;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 网络请求服务接口
 * Created by cuipengyu on 2018/4/2
 */
public interface RetrofitService {
    @GET()
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String, String> map);

    @POST()
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String, String> map);

    @GET("mix-atoc/{bookId}")
    Observable<MixTocBeanCon> getChapter(@Path("bookId") String path);
}
