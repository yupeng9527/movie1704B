package com.bw.movie.view.contract;

import com.bw.movie.view.base.BasePersenter;
import com.bw.movie.view.base.IBaseVIew;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;

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

    //查询区域列表
    public abstract void doRegionList();
    //根据区域查询影院
    public abstract void dofindCinemaByRegion(int regionId);
    //查询电影信息明细
    public abstract void doCinemaInfo(Map<String,Object> map,int regionId);

    //关注影院
    public abstract void onFollow(Map<String,Object> map,int regionId);

    //取消关注影院
    public abstract void onCancel(Map<String,Object> map,int regionId);

    //取消关注影院
    public abstract void onCinemaComment(Map<String,Object> map,Map<String,Object> omap);

    //取消关注影院
    public abstract void onScheduleList(Map<String,Object> map);

    //根据电影id，时间 查询播放影院信息
    public abstract void onByCineamDate(Map<String,Object> map);

//根据电影价格查询播放影院信息
    public abstract void onPriceBy(Map<String,Object> map);
//查询我对电影的评论列表
    public abstract void onCommentList(Map<String,Object> map,Map<String,Object> omap);
//查询我对影院评论列表
    public abstract void onCinemaDiscussList(Map<String,Object> map,Map<String,Object> omap);
//电影评论点赞
    public abstract void doGreat(Map<String,Object> map,Map<String,Object> omap);
//查询取票码
    public abstract void doExchangeCode(Map<String,Object> map,int recordId);
//查询取票码
    public abstract void doMsgStatus(Map<String,Object> map,int id);
    //查询取票码
    public abstract void doMovieByKey(Map<String,Object> map);
    //根据电影院名称模糊查询电影院
    public abstract void doCinemaByKey(Map<String,Object> map);
    //影院评论
    public abstract void doCinemaCom(Map<String,Object> map,Map<String,Object> omap);

}

}
