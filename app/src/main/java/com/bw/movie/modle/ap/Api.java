package com.bw.movie.modle.ap;

import com.bw.movie.modle.bean.BannerBean;
import com.bw.movie.modle.bean.ByRegionBean;
import com.bw.movie.modle.bean.CinemaBean;
import com.bw.movie.modle.bean.CodeBean;
import com.bw.movie.modle.bean.CommentBean;
import com.bw.movie.modle.bean.DetilBean;
import com.bw.movie.modle.bean.FollowBean;
import com.bw.movie.modle.bean.GuideBean;
import com.bw.movie.modle.bean.HotBean;
import com.bw.movie.modle.bean.MoVieListBean;
import com.bw.movie.modle.bean.RecommendBean;
import com.bw.movie.modle.bean.RegisterBean;
import com.bw.movie.modle.bean.SchedBean;
import com.bw.movie.modle.bean.SeatleBean;
import com.bw.movie.modle.bean.SoonMovieBean;
import com.bw.movie.modle.bean.TICketBean;
import com.bw.movie.modle.bean.TicketsBean;
import com.bw.movie.modle.bean.VerifyBean;
import com.bw.movie.modle.bean.WxLogBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
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
    Observable<SoonMovieBean> Soonmovie(@HeaderMap Map<String,Object> map, @QueryMap Map<String,Object> omap);

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

//  预约
    @FormUrlEncoded
   @POST("movieApi/movie/v2/verify/reserve")
    Observable<CodeBean> Reserve(@HeaderMap Map<String,Object> map, @FieldMap Map<String,Object> imap);

    @GET("movieApi/movie/v2/findAllMovieComment")
    Observable<CommentBean> Comment(@HeaderMap Map<String,Object> map, @QueryMap Map<String,Object> imap);

    @FormUrlEncoded
    @POST("movieApi/user/v1/verify/modifyUserPwd")
    Observable<CodeBean> UserPwd(@HeaderMap Map<String,Object> map, @FieldMap Map<String,String> imap);
//    添加用户对影片的评论
    @FormUrlEncoded
    @POST("movieApi/movie/v1/verify/movieComment")
    Observable<CodeBean> MovComm(@FieldMap Map<String,Object> map,@FieldMap Map<String,Object> omap);
//    意见反馈
    @FormUrlEncoded
    @POST("movieApi/tool/v1/verify/recordFeedBack")
    Observable<CodeBean> FeedBack(@HeaderMap Map<String,Object> map,@FieldMap Map<String,Object> omap);

    @FormUrlEncoded
    @POST("movieApi/user/v1/weChatBindingLogin")
    Observable<WxLogBean> WX(@Field("code") String code);
//根据影厅id 查询座位信息
    @GET("movieApi/movie/v2/findSeatInfo")
    Observable<SeatleBean> Seatle(@Query("hallId") int hallId);

    @GET("movieApi/movie/v2/findCinemasInfoByRegion")
    Observable<ByRegionBean> ByRegion(@QueryMap Map<String,Object> map);


    @GET("movieApi/movie/v2/findMovieSchedule")
    Observable<SchedBean> BySchedule(@QueryMap Map<String,Object> map);
//购票下单
    @FormUrlEncoded
    @POST("movieApi/movie/v2/verify/buyMovieTickets")
    Observable<TicketsBean> Tickets(@HeaderMap Map<String,Object> map,@FieldMap Map<String,Object> omap);
//  支付
    @FormUrlEncoded
    @POST("movieApi/movie/v2/verify/pay")
    Observable<VerifyBean> Pay(@HeaderMap Map<String,Object> map, @FieldMap Map<String,Object> omap);
//  订单支付于未支付
    @POST("movieApi/user/v2/verify/findUserBuyTicketRecord")
    Observable<TICketBean> onTicket(@HeaderMap Map<String,Object> map, @QueryMap Map<String,Object> omap);

}
