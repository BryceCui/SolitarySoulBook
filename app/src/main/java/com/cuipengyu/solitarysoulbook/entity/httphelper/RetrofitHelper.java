package com.cuipengyu.solitarysoulbook.entity.httphelper;

import com.alibaba.fastjson.JSON;
import com.cuipengyu.solitarysoulbook.base.BaseBean;
import com.cuipengyu.solitarysoulbook.base.Constants;
import com.cuipengyu.solitarysoulbook.utils.LogUtils;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求的封装
 * Created by cuipengyu on 2018/4/2
 */
public class RetrofitHelper implements HttpEngine {
    //写入超时
    private static final int WRITE_TIME_OUT = 30;
    //建立连接的超时时间
    private static final int CONNECT_TIME_OUT = 30;
    //读取超时
    private static final int READ_TIME_OUT = 30;
    private static RetrofitHelper mHelper;
    private Retrofit mRetrofit;
    private Map<String, String> mMapParams;
    private String BASE_URL = "";

    private RetrofitHelper() {
        BASE_URL = Constants.Api_baseUrl;
        init();
    }

    private void init() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder().writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS).connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS).readTimeout(READ_TIME_OUT, TimeUnit.SECONDS).addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                LogUtils.e(response.toString());
                return response;
            }
        }).build();
        mRetrofit = new Retrofit.Builder().client(mOkHttpClient).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
        mMapParams = new HashMap<>();
    }

    //选择baseurl
    public RetrofitHelper(int check) {
        switch (check) {
            case 0:
                BASE_URL = Constants.http_baseUrl;

                break;
            default:
                BASE_URL = Constants.Api_baseUrl;
                break;
        }
        init();
    }

    public static RetrofitHelper build() {
        if (mHelper == null) return mHelper = new RetrofitHelper();
        return mHelper;
    }
    public static RetrofitHelper build(int index) {
        if (mHelper == null) return mHelper = new RetrofitHelper(index);
        return mHelper;
    }

    public RetrofitService getmRetrofit() {
        RetrofitService service = mRetrofit.create(RetrofitService.class);
        return service;
    }

    @Override
    public <T extends BaseBean> void getServiceJson(String url, final Class<T> tClass, final CallBack<T> callBack) {
        //使用Gson解析
//        final Type[] types = callBack.getClass().getGenericInterfaces();
//        final Type finalNeedType = MethodHandler(types).get(0);
        RetrofitService mService = mRetrofit.create(RetrofitService.class);
        mService.get(url, mMapParams).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String s = responseBody.string();
                    //使用Gson解析
//                            T data = new Gson().fromJson(s, finalNeedType);
                    //使用阿里fastjson解析
                    T data = JSON.parseObject(s, tClass);
                    //请求是否成功
                    if (data.isOk()) callBack.onSuccess(data);
                    callBack.onFailure("请求失败");
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    //以Map形式添加字段传递给服务器
    @Override
    public HttpEngine param(String key, String value) {
        mMapParams.put(key, value);
        return mHelper;
    }

    @Override
    public HttpEngine param(Map<String, String> map) {
        mMapParams.putAll(map);
        return mHelper;
    }

    private List<Type> MethodHandler(Type[] types) {
        List<Type> needtypes = new ArrayList<>();

        for (Type paramType : types) {
            if (paramType instanceof ParameterizedType) {
                Type[] parentypes = ((ParameterizedType) paramType).getActualTypeArguments();
                for (Type childtype : parentypes) {
                    needtypes.add(childtype);
                    if (childtype instanceof ParameterizedType) {
                        Type[] childtypes = ((ParameterizedType) childtype).getActualTypeArguments();
                        Collections.addAll(needtypes, childtypes);
                    }
                }
            }
        }
        return needtypes;
    }
}
