package com.bw.movie.persenter;

import android.util.Log;
import android.widget.Toast;

import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.BannerBean;
import com.bw.movie.modle.bean.ByRegionBean;
import com.bw.movie.modle.bean.CinemaBean;
import com.bw.movie.modle.bean.CinemaByBean;
import com.bw.movie.modle.bean.CinemaCommentBean;
import com.bw.movie.modle.bean.CinemaDiscussBean;
import com.bw.movie.modle.bean.CinemaInfoBean;
import com.bw.movie.modle.bean.CinmeaKeyBean;
import com.bw.movie.modle.bean.CodeBean;
import com.bw.movie.modle.bean.CommentBean;
import com.bw.movie.modle.bean.DateListBean;
import com.bw.movie.modle.bean.DetilBean;
import com.bw.movie.modle.bean.ExchangeCodeBean;
import com.bw.movie.modle.bean.FollowBean;
import com.bw.movie.modle.bean.GuideBean;
import com.bw.movie.modle.bean.HeadPicBean;
import com.bw.movie.modle.bean.HotBean;
import com.bw.movie.modle.bean.MoVieListBean;
import com.bw.movie.modle.bean.MovieByKeyBean;
import com.bw.movie.modle.bean.MovieDiscussBean;
import com.bw.movie.modle.bean.MyMovieBean;
import com.bw.movie.modle.bean.OnCimenBean;
import com.bw.movie.modle.bean.OnMovieBean;
import com.bw.movie.modle.bean.PriceByBean;
import com.bw.movie.modle.bean.RecommendBean;
import com.bw.movie.modle.bean.RecordByBean;
import com.bw.movie.modle.bean.RegionListBean;
import com.bw.movie.modle.bean.RegisterBean;
import com.bw.movie.modle.bean.SchedBean;
import com.bw.movie.modle.bean.ScheduleListBean;
import com.bw.movie.modle.bean.SeatleBean;
import com.bw.movie.modle.bean.SeenMovieBean;
import com.bw.movie.modle.bean.SoonMovieBean;
import com.bw.movie.modle.bean.SysMsgListBean;
import com.bw.movie.modle.bean.TICketBean;
import com.bw.movie.modle.bean.TicketsBean;
import com.bw.movie.modle.bean.UserBean;
import com.bw.movie.modle.bean.VerifyBean;
import com.bw.movie.modle.bean.VersionBean;
import com.bw.movie.modle.bean.WxLogBean;
import com.bw.movie.modle.utils.HttpUtil;
import com.bw.movie.view.base.IBaseVIew;
import com.bw.movie.view.contract.IViewContract;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * date:2019/9/27
 * author:贺少伟(盗)
 * function:
 */
public class Persenter extends IViewContract.doData {

    public Persenter(IBaseVIew iBaseVIew) {
        super(iBaseVIew);
    }

    @Override
    public void doRegister(Map<String, String> map) {
        HttpUtil.getHttpUtil()
                .getApi()
                .register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean!=null) {
                            if (iBaseVIew!=null) {
                                iBaseVIew.onLogCurress(registerBean);
                            }
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iBaseVIew!=null) {
                         iBaseVIew.onLogExurr(throwable.getMessage());
                        }
                    }
                });

    }

    @Override
    public void doCode(Map<String, String> map) {
        HttpUtil.getHttpUtil().getApi()
                .code(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeBean>() {
                    @Override
                    public void accept(CodeBean codeBean) throws Exception {
                        if (codeBean.message.equals("0000")){
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doGuild(Map<String, String> map) {
        HttpUtil.getHttpUtil().getApi()
                .guide(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GuideBean>() {
                    @Override
                    public void accept(GuideBean guideBean) throws Exception {
                        if (guideBean!=null) {
                            if (iBaseVIew!=null) {
                                iBaseVIew.onLogCurress(guideBean);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                            if (iBaseVIew!=null) {
                                iBaseVIew.onLogExurr(throwable.getMessage());
                            }

                    }
                });
    }

    @Override
    public void doMovieList(int page) {
        Map<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("count",5);
        HttpUtil.getHttpUtil().getApi()
                .movie(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MoVieListBean>() {
                    @Override
                    public void accept(MoVieListBean moVieListBean) throws Exception {
                        if ("0000".equals(moVieListBean.status)) {
                            List<MoVieListBean.ResultBean> result = moVieListBean.result;
                            if (iBaseVIew!=null) {
                                iBaseVIew.onLogCurress(result);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iBaseVIew!=null){
                            iBaseVIew.onLogExurr(throwable.getMessage());
                        }
                    }
                });
    }

    @Override
    public void SoonMovieList(Map<String,Object> map,int page) {
        Map<String,Object> omap=new HashMap<>();
        omap.put("page",page);
        omap.put("count",3);
        HttpUtil.getHttpUtil().getApi()
                .Soonmovie(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SoonMovieBean>() {
                    @Override
                    public void accept(SoonMovieBean soonMovieBean) throws Exception {
                        if ("0000".equals(soonMovieBean.status)) {
                            List<SoonMovieBean.ResultBean> result = soonMovieBean.result;
                            if (iBaseVIew!=null) {
                                iBaseVIew.onShapeCurress(result);
                            }
                        } else {
                            Toast.makeText(App.context, soonMovieBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void HotMovieList(int page) {
        Map<String,Object> omap=new HashMap<>();
        omap.put("page",page);
        omap.put("count",6);
        HttpUtil.getHttpUtil().getApi()
                .Hotmovie(omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HotBean>() {
                    @Override
                    public void accept(HotBean hotBean) throws Exception {
                        if ("0000".equals(hotBean.status)) {
                            if (iBaseVIew!=null) {
                                List<HotBean.ResultBean> result = hotBean.result;
                                iBaseVIew.onMyCurress(result);
                            }
                        } else {
                            Toast.makeText(App.context, hotBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void DoBanner() {
        HttpUtil.getHttpUtil().getApi()
                .Banner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BannerBean>() {
                    @Override
                    public void accept(BannerBean bannerBean) throws Exception {
                        if (iBaseVIew!=null) {
                            List<BannerBean.ResultBean> result = bannerBean.result;
                            if ("0000".equals(bannerBean.status)) {
                                iBaseVIew.onBannerCurress(result);
                            }else{
                                Toast.makeText(App.context, bannerBean.message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void Cinema(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .Cinema(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CinemaBean>() {
                    @Override
                    public void accept(CinemaBean cinemaBean) throws Exception {
                        if (iBaseVIew!=null) {
                            List<CinemaBean.ResultBean> result = cinemaBean.result;
                            if ("0000".equals(cinemaBean.status)) {
                                iBaseVIew.onLogCurress(result);
                            }else{
                                Toast.makeText(App.context, cinemaBean.message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void doDetail(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .Detail(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DetilBean>() {
                    @Override
                    public void accept(DetilBean detilBean) throws Exception {
                        if (iBaseVIew!=null) {
                            if ("0000".equals(detilBean.status)) {
                                iBaseVIew.onLogCurress(detilBean);
                            }else{
                                Toast.makeText(App.context, detilBean.message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void Recommend() {
        Map<String,Object> omap=new HashMap<>();
        omap.put("userId",18);
        omap.put("sessionId","15320748258726");
        Map<String,Integer> map=new HashMap<>();
        map.put("page",1);
        map.put("count",10);
        HttpUtil.getHttpUtil().getApi()
                .Recommend(omap,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RecommendBean>() {
                    @Override
                    public void accept(RecommendBean recommendBean) throws Exception {
                        if (iBaseVIew!=null) {
                            List<RecommendBean.ResultBean> result = recommendBean.result;
                            if ("0000".equals(recommendBean.status)) {
                                iBaseVIew.onLogCurress(result);
                            }else{
                                Toast.makeText(App.context, recommendBean.message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doCancel(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .Cancel(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FollowBean>() {
                    @Override
                    public void accept(FollowBean followBean) throws Exception {
                        if ("0000".equals(followBean.status)) {
                            Toast.makeText(App.context, followBean.message, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(App.context, followBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doFollow(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .Follow(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FollowBean>() {
                    @Override
                    public void accept(FollowBean followBean) throws Exception {
                        if ("0000".equals(followBean.status)) {
                            Toast.makeText(App.context, followBean.message, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(App.context, followBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doResert(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .Reserve(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeBean>() {
                    @Override
                    public void accept(CodeBean codeBean) throws Exception {
                        if ("0000".equals(codeBean.status)) {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doCommit(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .Comment(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CommentBean>() {
                    @Override
                    public void accept(CommentBean commentBean) throws Exception {
                        if (iBaseVIew != null) {
                            if ("0000".equals(commentBean.status)) {
                                iBaseVIew.onLogCurress(commentBean);
                            } else {
                                Toast.makeText(App.context, commentBean.message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        iBaseVIew.onLogExurr(throwable.toString());
                    }
                });
    }

    @Override
    public void doUserPwd(Map<String, Object> map, Map<String, String> omap) {
        HttpUtil.getHttpUtil().getApi()
                .UserPwd(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeBean>() {
                    @Override
                    public void accept(CodeBean codeBean) throws Exception {
                        if ("0000".equals(codeBean.status)) {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                iBaseVIew.onLogCurress("0");
                            }
                        } else {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doMoviComm(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .MovComm(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeBean>() {
                    @Override
                    public void accept(CodeBean codeBean) throws Exception {
                        if ("0000".equals(codeBean.status)) {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                iBaseVIew.onBannerCurress("0");
                            }
                        } else {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doFeedBack(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .FeedBack(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeBean>() {
                    @Override
                    public void accept(CodeBean codeBean) throws Exception {
                        if ("0000".equals(codeBean.status)) {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                iBaseVIew.onBannerCurress("0");
                            }
                        } else {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doSeatle(int hallId) {
        HttpUtil.getHttpUtil().getApi()
                .Seatle(hallId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SeatleBean>() {
                    @Override
                    public void accept(SeatleBean seatleBean) throws Exception {
                        if ("0000".equals(seatleBean.status)) {
                            Toast.makeText(App.context, seatleBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                iBaseVIew.onLogCurress(seatleBean);
                            }
                        } else {
                            Toast.makeText(App.context, seatleBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doByRegin(Map<String, Object> map) {
        HttpUtil.getHttpUtil().getApi()
                .ByRegion(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ByRegionBean>() {
                    @Override
                    public void accept(ByRegionBean byRegionBean) throws Exception {
                        if ("0000".equals(byRegionBean.status)) {
                            Toast.makeText(App.context, byRegionBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                iBaseVIew.onShapeCurress(byRegionBean);
                            }
                        } else {
                            Toast.makeText(App.context, byRegionBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public void doSchedule(Map<String, Object> map) {
        HttpUtil.getHttpUtil().getApi()
                .BySchedule(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SchedBean>() {
                    @Override
                    public void accept(SchedBean schedBean) throws Exception {
                        if ("0000".equals(schedBean.status)) {
                            Toast.makeText(App.context, schedBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                iBaseVIew.onShapeCurress(schedBean);
                            }
                        } else {
                            Toast.makeText(App.context, schedBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public void doWxLog(String code) {
        HttpUtil.getHttpUtil().getApi()
                .WX(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WxLogBean>() {
                    @Override
                    public void accept(WxLogBean wxLogBean) throws Exception {
                        if ("0000".equals(wxLogBean.status)) {
                            Toast.makeText(App.context, wxLogBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                iBaseVIew.onShapeCurress(wxLogBean);
                            }
                        } else {
                            Toast.makeText(App.context, wxLogBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
//  订单
    @Override
    public void doTickets(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .Tickets(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TicketsBean>() {
                    @Override
                    public void accept(TicketsBean ticketsBean) throws Exception {
                        if ("0000".equals(ticketsBean.status)) {
                            Toast.makeText(App.context, ticketsBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                iBaseVIew.onMyCurress(ticketsBean);
                            }
                        } else {
                            Toast.makeText(App.context, ticketsBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
//支付
    @Override
    public void doPay(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .Pay(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<VerifyBean>() {
                    @Override
                    public void accept(VerifyBean verifyBean) throws Exception {
                        if ("0000".equals(verifyBean.status)) {
                            if (iBaseVIew!=null) {
                                iBaseVIew.onBannerCurress(verifyBean);
                            }
                        } else {
                            Toast.makeText(App.context, verifyBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doTicter(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .onTicket(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TICketBean>() {
                    @Override
                    public void accept(TICketBean tiCketBean) throws Exception {
                        if ("0000".equals(tiCketBean.status)) {
                            if (iBaseVIew!=null) {
                                iBaseVIew.onLogCurress(tiCketBean);
                            }
                        } else {
                            Toast.makeText(App.context, tiCketBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public void doRecordBy(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .onRecordBy(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RecordByBean>() {
                    @Override
                    public void accept(RecordByBean recordByBean) throws Exception {
                        if ("0000".equals(recordByBean.status)) {
                            if (iBaseVIew!=null) {
                                iBaseVIew.onLogCurress(recordByBean);
                            }
                        } else {
                            Toast.makeText(App.context, recordByBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doUserBean(Map<String, Object> map) {
        HttpUtil.getHttpUtil().getApi()
                .onUserBean(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(UserBean userBean) throws Exception {
                        if ("0000".equals(userBean.status)) {
                            if (iBaseVIew!=null) {
                                iBaseVIew.onLogCurress(userBean);
                            }
                        } else {
                            Toast.makeText(App.context, userBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doMyBean(Map<String, Object> map) {
        HttpUtil.getHttpUtil().getApi()
                .onMyBean(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyMovieBean>() {
                    @Override
                    public void accept(MyMovieBean myMovieBean) throws Exception {
                        if ("0000".equals(myMovieBean.status)) {
                            if (iBaseVIew!=null) {
                                iBaseVIew.onLogCurress(myMovieBean);
                            }
                        } else {
                            Toast.makeText(App.context, myMovieBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public void doMOvieList(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .onMOvieList(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OnMovieBean>() {
                    @Override
                    public void accept(OnMovieBean onMovieBean) throws Exception {
                        if ("0000".equals(onMovieBean.status)) {
                            if (iBaseVIew!=null) {
                                iBaseVIew.onLogCurress(onMovieBean);
                            }
                        } else {
                            Toast.makeText(App.context, onMovieBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doCinemaList(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .onCinemaList(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OnCimenBean>() {
                    @Override
                    public void accept(OnCimenBean onCimenBean) throws Exception {
                        if ("0000".equals(onCimenBean.status)) {
                            if (iBaseVIew!=null) {
                                iBaseVIew.onLogCurress(onCimenBean);
                            }
                        } else {
                            Toast.makeText(App.context, onCimenBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void doSeenMovie(Map<String, Object> map) {
        HttpUtil.getHttpUtil().getApi()
                .onSeenMovie(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SeenMovieBean>() {
                    @Override
                    public void accept(SeenMovieBean seenMovieBean) throws Exception {
                        if ("0000".equals(seenMovieBean.status)) {
                            if (iBaseVIew != null) {
                                iBaseVIew.onLogCurress(seenMovieBean);
                            }
                        } else {
                            Toast.makeText(App.context, seenMovieBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doSysMsgList(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .onSysMsgList(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SysMsgListBean>() {
                    @Override
                    public void accept(SysMsgListBean sysMsgListBean) throws Exception {
                        if ("0000".equals(sysMsgListBean.status)) {
                            if (iBaseVIew != null) {
                                iBaseVIew.onLogCurress(sysMsgListBean);
                            }
                        } else {
                            Toast.makeText(App.context, sysMsgListBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doDateList() {
        HttpUtil.getHttpUtil().getApi()
                .onDateList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DateListBean>() {
                    @Override
                    public void accept(DateListBean dateListBean) throws Exception {
                        if ("0000".equals(dateListBean.status)) {
                            if (iBaseVIew!=null) {
                                iBaseVIew.onBannerCurress(dateListBean);
                            }
                        } else {
                            Toast.makeText(App.context, dateListBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doVersion(Map<String, Object> map) {
        HttpUtil.getHttpUtil().getApi()
                .onVersion(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<VersionBean>() {
                    @Override
                    public void accept(VersionBean versionBean) throws Exception {
                        if ("0000".equals(versionBean.status)) {
                            if (iBaseVIew!=null) {
                                iBaseVIew.onLogCurress(versionBean);
                            }
                        } else {
                            Toast.makeText(App.context, versionBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doBirthday(Map<String, Object> map, Map<String, String> smap) {
        HttpUtil.getHttpUtil().getApi()
                .onBirthday(map,smap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeBean>() {
                    @Override
                    public void accept(CodeBean codeBean) throws Exception {
                        if ("0000".equals(codeBean.status)) {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                            iBaseVIew.onMyCurress("0");
                        } else {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doloadHeadPic(Map<String, Object> map, MultipartBody.Part file) {
        HttpUtil.getHttpUtil().getApi()
                .onloadHeadPic(map,file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HeadPicBean>() {
                    @Override
                    public void accept(HeadPicBean headPicBean) throws Exception {
                        if ("0000".equals(headPicBean.status)) {
                            iBaseVIew.onShapeCurress(headPicBean);
                        } else {
                            Toast.makeText(App.context, headPicBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        iBaseVIew.onLogExurr(throwable.toString());
                    }
                });
    }

    @Override
    public void doUserPhone(Map<String, Object> map, Map<String, String> smap) {
        HttpUtil.getHttpUtil().getApi()
                .onUserPhone(map,smap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeBean>() {
                    @Override
                    public void accept(CodeBean codeBean) throws Exception {
                        if ("0000".equals(codeBean.status)) {
                            iBaseVIew.onBannerCurress("0");
                        } else {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doRegionList() {
        HttpUtil.getHttpUtil().getApi()
                .onRegionList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegionListBean>() {
                    @Override
                    public void accept(RegionListBean regionListBean) throws Exception {
                        if ("0000".equals(regionListBean.status)) {
                            iBaseVIew.onMyCurress(regionListBean);
                        } else {
                            Toast.makeText(App.context, regionListBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void dofindCinemaByRegion(int regionId) {
        HttpUtil.getHttpUtil().getApi()
                .onByRegion(regionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CinemaByBean>() {
                    @Override
                    public void accept(CinemaByBean cinemaByBean) throws Exception {
                        if ("0000".equals(cinemaByBean.status)) {
                            iBaseVIew.onShapeCurress(cinemaByBean);
                        } else {
                            Toast.makeText(App.context, cinemaByBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doCinemaInfo(Map<String,Object> map,int regionId) {
        HttpUtil.getHttpUtil().getApi()
                .onCinemaInfo(map,regionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CinemaInfoBean>() {
                    @Override
                    public void accept(CinemaInfoBean cinemaInfoBean) throws Exception {
                        if ("0000".equals(cinemaInfoBean.status)) {
                            iBaseVIew.onLogCurress(cinemaInfoBean);
                        } else {
                            Toast.makeText(App.context, cinemaInfoBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onFollow(Map<String, Object> map, int regionId) {
        HttpUtil.getHttpUtil().getApi()
                .onFollow(map,regionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FollowBean>() {
                    @Override
                    public void accept(FollowBean followBean) throws Exception {
                        if ("0000".equals(followBean.status)) {
                            Toast.makeText(App.context, followBean.message, Toast.LENGTH_SHORT).show();
                            iBaseVIew.onShapeCurress("0");
                        } else {
                            Toast.makeText(App.context, followBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onCancel(Map<String, Object> map, int regionId) {
        HttpUtil.getHttpUtil().getApi()
                .onCancel(map,regionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FollowBean>() {
                    @Override
                    public void accept(FollowBean followBean) throws Exception {
                        if ("0000".equals(followBean.status)) {
                            Toast.makeText(App.context, followBean.message, Toast.LENGTH_SHORT).show();
                            iBaseVIew.onMyCurress("0");
                        } else {
                            Toast.makeText(App.context, followBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onCinemaComment(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .onCinemaComment(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CinemaCommentBean>() {
                    @Override
                    public void accept(CinemaCommentBean cinemaCommentBean) throws Exception {
                        if ("0000".equals(cinemaCommentBean.status)) {
                            Toast.makeText(App.context, cinemaCommentBean.message, Toast.LENGTH_SHORT).show();
                            iBaseVIew.onLogCurress(cinemaCommentBean);
                        } else {
                            Toast.makeText(App.context, cinemaCommentBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onScheduleList(Map<String, Object> map) {
        HttpUtil.getHttpUtil().getApi()
                .onScheduleList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ScheduleListBean>() {
                    @Override
                    public void accept(ScheduleListBean scheduleListBean) throws Exception {
                        if ("0000".equals(scheduleListBean.status)) {
                            iBaseVIew.onMyCurress(scheduleListBean);
                        } else {
                            Toast.makeText(App.context, scheduleListBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        iBaseVIew.onLogExurr(throwable.getMessage());
                    }
                });
    }

    @Override
    public void onByCineamDate(Map<String, Object> map) {
        HttpUtil.getHttpUtil().getApi()
                .onCinemasByDate(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ByRegionBean>() {
                    @Override
                    public void accept(ByRegionBean byRegionBean) throws Exception {
                        if ("0000".equals(byRegionBean.status)) {
                            Toast.makeText(App.context, byRegionBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                iBaseVIew.onShapeCurress(byRegionBean);
                            }
                        } else {
                            Toast.makeText(App.context, byRegionBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public void onPriceBy(Map<String, Object> map) {
        HttpUtil.getHttpUtil().getApi()
                .onPriceBy(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ByRegionBean>() {
                    @Override
                    public void accept(ByRegionBean byRegionBean) throws Exception {
                        if ("0000".equals(byRegionBean.status)) {
                            Toast.makeText(App.context, byRegionBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                iBaseVIew.onShapeCurress(byRegionBean);
                            }
                        } else {
                            Toast.makeText(App.context, byRegionBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public void onCommentList(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .onCommentList(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieDiscussBean>() {
                    @Override
                    public void accept(MovieDiscussBean movieDiscussBean) throws Exception {
                        if ("0000".equals(movieDiscussBean.status)) {
                            Toast.makeText(App.context, movieDiscussBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                iBaseVIew.onLogCurress(movieDiscussBean);
                            }
                        } else {
                            Toast.makeText(App.context, movieDiscussBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onCinemaDiscussList(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .onCinemaDiscussList(map, omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CinemaDiscussBean>() {
                    @Override
                    public void accept(CinemaDiscussBean cinemaDiscussBean) throws Exception {
                        if ("0000".equals(cinemaDiscussBean.status)) {
                            Toast.makeText(App.context, cinemaDiscussBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                iBaseVIew.onLogCurress(cinemaDiscussBean);
                            }
                        } else {
                            Toast.makeText(App.context, cinemaDiscussBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doGreat(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .doGreat(map, omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeBean>() {
                    @Override
                    public void accept(CodeBean codeBean) throws Exception {
                        if ("0000".equals(codeBean.status)) {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public void doExchangeCode(Map<String, Object> map, int recordId) {
        HttpUtil.getHttpUtil().getApi()
                .onExchangeCode(map,recordId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ExchangeCodeBean>() {
                    @Override
                    public void accept(ExchangeCodeBean exchangeCodeBean) throws Exception {
                        if ("0000".equals(exchangeCodeBean.status)) {
                            Toast.makeText(App.context, exchangeCodeBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                iBaseVIew.onShapeCurress(exchangeCodeBean);
                            }
                        } else {
                            Toast.makeText(App.context, exchangeCodeBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doMsgStatus(Map<String, Object> map, int id) {
        HttpUtil.getHttpUtil().getApi()
                .onMsgStatus(map,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeBean>() {
                    @Override
                    public void accept(CodeBean codeBean) throws Exception {
                        if ("0000".equals(codeBean.status)) {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                iBaseVIew.onShapeCurress("0");
                            }
                        } else {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doMovieByKey(Map<String, Object> map) {
        HttpUtil.getHttpUtil().getApi()
                .onMovieByKey(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieByKeyBean>() {
                    @Override
                    public void accept(MovieByKeyBean movieByKeyBean) throws Exception {
                        if ("0000".equals(movieByKeyBean.status)) {
                            Toast.makeText(App.context, movieByKeyBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                iBaseVIew.onMovieCinema(movieByKeyBean);
                            }
                        } else {
                            Toast.makeText(App.context, movieByKeyBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doCinemaByKey(Map<String, Object> map) {
        HttpUtil.getHttpUtil().getApi()
                .onCinemaByKey(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CinmeaKeyBean>() {
                    @Override
                    public void accept(CinmeaKeyBean cinmeaKeyBean) throws Exception {
                        if ("0000".equals(cinmeaKeyBean.status)) {
                            Toast.makeText(App.context, cinmeaKeyBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                Log.i("qqq", "accept: "+cinmeaKeyBean.toString());
                                iBaseVIew.onMovieCinema(cinmeaKeyBean);
                            }
                        } else {
                            Toast.makeText(App.context, cinmeaKeyBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void doCinemaCom(Map<String, Object> map, Map<String, Object> omap) {
        HttpUtil.getHttpUtil().getApi()
                .onCinemaCom(map,omap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CodeBean>() {
                    @Override
                    public void accept(CodeBean codeBean) throws Exception {
                        if ("0000".equals(codeBean.status)) {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                            if (iBaseVIew!=null) {
                                Log.i("qqq", "accept: "+codeBean.toString());
                                iBaseVIew.onShapeCurress(codeBean);
                            }
                        } else {
                            Toast.makeText(App.context, codeBean.message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(App.context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
