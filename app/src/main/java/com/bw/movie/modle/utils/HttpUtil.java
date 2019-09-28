package com.bw.movie.modle.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bw.movie.modle.ap.Api;
import com.bw.movie.modle.ap.App;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class HttpUtil {
    private static HttpUtil httpUtil=new HttpUtil();
    private final Api api;

    public static HttpUtil getHttpUtil(){
        return httpUtil;
    }
    private HttpUtil(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        Retrofit build = new Retrofit.Builder()
                .baseUrl(Api.DA_TA)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        api = build.create(Api.class);
    }

    public Api getApi() {
        return api;
    }

    public boolean doHttp(){
        if (App.context!=null) {
            ConnectivityManager systemService = (ConnectivityManager) App.context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
            if (activeNetworkInfo!=null) {
                return activeNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
