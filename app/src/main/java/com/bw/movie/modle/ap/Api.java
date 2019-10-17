package com.bw.movie.modle.ap;

import com.bw.movie.modle.bean.BannerBean;
import com.bw.movie.modle.bean.CinemaBean;
import com.bw.movie.modle.bean.CodeBean;
import com.bw.movie.modle.bean.DetilBean;
import com.bw.movie.modle.bean.FollowBean;
import com.bw.movie.modle.bean.GuideBean;
import com.bw.movie.modle.bean.HotBean;
import com.bw.movie.modle.bean.MoVieListBean;
import com.bw.movie.modle.bean.RecommendBean;
import com.bw.movie.modle.bean.RegisterBean;
import com.bw.movie.modle.bean.SoonMovieBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
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

    @GET("movieApi/movie/v2/findHotMovieList")
    Observable<MoVieListBean> movie(@QueryMap Map<String,Object> map);

    @GET("movieApi/movie/v2/findComingSoonMovieList")
    Observable<SoonMovieBean> Soonmovie(@HeaderMap Map<String,String> map, @QueryMap Map<String,Object> omap);

    @GET("movieApi/movie/v2/findHotMovieList")
    Observable<HotBean> Hotmovie(@QueryMap Map<String,Object> omap);

    @GET("movieApi/tool/v2/banner")
    Observable<BannerBean> Banner();

    @GET("movieApi/movie/v2/findMoviesDetail")
    Observable<DetilBean> Detail(@HeaderMap Map<String,Object> map,@QueryMap Map<String,Object> imap);


    @GET("movieApi/cinema/v1/findRecommendCinemas")
    Observable<RecommendBean> Recommend(@HeaderMap Map<String,Object> map,@QueryMap Map<String,Integer> imap);

   @GET("movieApi/cinema/v1/findNearbyCinemas")
    Observable<CinemaBean> Cinema(@HeaderMap Map<String,Object> map, @QueryMap Map<String,Object> imap);
//  关注
   @GET("movieApi/movie/v1/verify/followMovie")
    Observable<FollowBean> Follow(@HeaderMap Map<String,Object> map, @QueryMap Map<String,Object> imap);
//  取消关注
   @GET("movieApi/movie/v1/verify/cancelFollowMovie")
    Observable<FollowBean> Cancel(@HeaderMap Map<String,Object> map, @QueryMap Map<String,Object> imap);


}
