package com.bw.movie.view.contract;

import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.base.IBaseVIew;

import java.util.Map;

/**
 * date:2019/9/27
 * author:贺少伟(盗)
 * function:  契约类
 */
public interface IViewContract {
//    契约回调
    interface doView extends IBaseVIew{}
//      契约persenter
    abstract class doData extends BasePersenter {
    public doData(IBaseVIew iBaseVIew) {
        super(iBaseVIew);
    }
//   注册
    public abstract void doRegister(Map<String,String> map);
//    发送邮箱验证码
    public abstract void doCode(Map<String,String> map);
//    登录
    public abstract void doGuild(Map<String,String> map);
//    热映数据
    public abstract void doMovieList(int page);
//      即将上映
    public abstract void SoonMovieList(int page);
}

}
