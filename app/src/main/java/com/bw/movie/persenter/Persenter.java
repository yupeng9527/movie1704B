package com.bw.movie.persenter;

import android.widget.Toast;

import com.bw.movie.modle.ap.App;
import com.bw.movie.modle.bean.CodeBean;
import com.bw.movie.modle.bean.GuideBean;
import com.bw.movie.modle.bean.RegisterBean;
import com.bw.movie.modle.utils.HttpUtil;
import com.bw.movie.view.base.IBaseVIew;
import com.bw.movie.view.contract.IViewContract;

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

}
