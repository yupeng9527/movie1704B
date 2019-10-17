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
//      热门
    public abstract void HotMovieList(int page);
//    推荐影院信息
    public abstract void DoBanner();

//    推荐影院信息
    public abstract void Cinema(Map<String,Object> map,Map<String,Object> omap);
//    电影信息详情
    public abstract void doDetail(Map<String,Object> map,Map<String,Object> omap);
//    Banner轮播图
    public abstract void Recommend();
//    取消关注
    public abstract void doCancel(Map<String,Object> map,Map<String,Object> omap);
//    关注
    public abstract void doFollow(Map<String,Object> map,Map<String,Object> omap);
}

}
