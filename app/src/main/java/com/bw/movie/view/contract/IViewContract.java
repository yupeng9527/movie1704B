package com.bw.movie.view.contract;

import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.base.IBaseVIew;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;

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
    public abstract void SoonMovieList(Map<String,Object> map,int page);
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
//    预约
    public abstract void doResert(Map<String,Object> map,Map<String,Object> omap);
//    评论
    public abstract void doCommit(Map<String,Object> map,Map<String,Object> omap);
//    修改密码
    public abstract void doUserPwd(Map<String,Object> map,Map<String,String> omap);
//添加用户对影片的评论
    public abstract void doMoviComm(Map<String,Object> map,Map<String,Object> omap);
//意见反馈
    public abstract void doFeedBack(Map<String,Object> map,Map<String,Object> omap);
//位置
    public abstract void doSeatle(int hallId);
//电影位置
    public abstract void doByRegin(Map<String,Object> map);
//电位置
    public abstract void doSchedule(Map<String,Object> map);
//微信登录
    public abstract void doWxLog(String code);
//下单
public abstract void doTickets(Map<String,Object> map,Map<String,Object> omap);
//支付
    public abstract void doPay(Map<String,Object> map,Map<String,Object> omap);

    //购票记录
    public abstract void doTicter(Map<String,Object> map,Map<String,Object> omap);

    //详情
    public abstract void doRecordBy(Map<String,Object> map,Map<String,Object> omap);

    //查询用户预约电影信息
    public abstract void doUserBean(Map<String,Object> map);

    //我的电影票
    public abstract void doMyBean(Map<String,Object> map);

    //查询用户关注电影列表
    public abstract void doMOvieList(Map<String,Object> map,Map<String,Object> omap);


    //查询用户关注电影列表
    public abstract void doCinemaList(Map<String,Object> map,Map<String,Object> omap);


    //查询看过的电影
    public abstract void doSeenMovie(Map<String,Object> map);

    //查询系统消息列表
    public abstract void doSysMsgList(Map<String,Object> map,Map<String,Object> omap);

    //查询一周排期的时间
    public abstract void doDateList();

    //查询看过的电影
    public abstract void doVersion(Map<String,Object> map);
    //修改用户生日
    public abstract void doBirthday(Map<String,Object> map,Map<String,String> smap);

    //上传用户头像
    public abstract void doloadHeadPic(Map<String,Object> map, MultipartBody.Part file);
    //修改用户手机号
    public abstract void doUserPhone(Map<String,Object> map, Map<String,String> smap);

    //修改用户手机号
    public abstract void doRegionList();
    //修改用户手机号
    public abstract void dofindCinemaByRegion(int regionId);
}

}
