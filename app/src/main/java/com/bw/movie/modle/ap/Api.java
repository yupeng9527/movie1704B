package com.bw.movie.modle.ap;

import com.bw.movie.modle.bean.CodeBean;
import com.bw.movie.modle.bean.GuideBean;
import com.bw.movie.modle.bean.RegisterBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * date:2019/9/27
 * author:贺少伟(盗)
 * function:
 */
public interface Api {
    String DA_TA="http://172.17.8.100/";
    @FormUrlEncoded
    @POST("movieApi/user/v2/register")
    Observable<RegisterBean> register(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("movieApi/user/v2/sendOutEmailCode")
    Observable<CodeBean> code(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("movieApi/user/v2/login")
    Observable<GuideBean> guide(@FieldMap Map<String,String> map);
}
