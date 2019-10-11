package com.bw.movie.persenter;

import android.widget.Toast;

import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.CodeBean;
import com.bw.movie.modle.bean.GuideBean;
import com.bw.movie.modle.bean.MoVieListBean;
import com.bw.movie.modle.bean.RegisterBean;
import com.bw.movie.modle.bean.SoonMovieBean;
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
    public void SoonMovieList(int page) {
        Map<String,String> map=new HashMap<>();
        map.put("userId","13686");
        map.put("sessionId","157069301241213686");
        Map<String,Object> omap=new HashMap<>();
        omap.put("page",page);
        omap.put("count",5);
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
                                iBaseVIew.onLogCurress(result);
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

}
