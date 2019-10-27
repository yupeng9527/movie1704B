package com.bw.movie.persenter;

import android.widget.Toast;

import com.bw.movie.modle.ap.App;
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
import com.bw.movie.modle.bean.RecordByBean;
import com.bw.movie.modle.bean.RegisterBean;
import com.bw.movie.modle.bean.SchedBean;
import com.bw.movie.modle.bean.SeatleBean;
import com.bw.movie.modle.bean.SoonMovieBean;
import com.bw.movie.modle.bean.TICketBean;
import com.bw.movie.modle.bean.TicketsBean;
import com.bw.movie.modle.bean.VerifyBean;
import com.bw.movie.modle.bean.WxLogBean;
import com.bw.movie.modle.utils.HttpUtil;
import com.bw.movie.view.base.IBaseVIew;
import com.bw.movie.view.contract.IViewContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
        omap.put("count",7);
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
                        if (iBaseVIew!=null) {
                            List<RecommendBean.ResultBean> result = commentBean.result;
                            if ("0000".equals(commentBean.status)) {
                                iBaseVIew.onLogCurress(result);
                            }else{
                                Toast.makeText(App.context, commentBean.message, Toast.LENGTH_SHORT).show();
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
                                iBaseVIew.onMyCurress(byRegionBean);
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

}
