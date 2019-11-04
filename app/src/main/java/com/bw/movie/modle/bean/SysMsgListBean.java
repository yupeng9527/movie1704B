package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/11/4
 * author:贺少伟(盗)
 * function:
 */
public class SysMsgListBean {

    /**
     * result : [{"content":"您已购买电影票,请尽快完成支付,以免影响到您的观影","id":4956,"pushTime":1572177317000,"status":0,"title":"系统通知","userId":13686},{"content":"您已购买电影票,请尽快完成支付,以免影响到您的观影","id":3429,"pushTime":1571988129000,"status":0,"title":"系统通知","userId":13686},{"content":"您已购买电影票,请尽快完成支付,以免影响到您的观影","id":3208,"pushTime":1571983874000,"status":0,"title":"系统通知","userId":13686},{"content":"您已购买电影票,请尽快完成支付,以免影响到您的观影","id":3109,"pushTime":1571971603000,"status":0,"title":"系统通知","userId":13686},{"content":"您已购买电影票,请尽快完成支付,以免影响到您的观影","id":3108,"pushTime":1571971573000,"status":0,"title":"系统通知","userId":13686},{"content":"您已购买电影票,请尽快完成支付,以免影响到您的观影","id":3106,"pushTime":1571971444000,"status":0,"title":"系统通知","userId":13686},{"content":"您已购买电影票,请尽快完成支付,以免影响到您的观影","id":3073,"pushTime":1571919130000,"status":0,"title":"系统通知","userId":13686},{"content":"您已购买电影票,请尽快完成支付,以免影响到您的观影","id":2913,"pushTime":1571907046000,"status":0,"title":"系统通知","userId":13686},{"content":"您已购买电影票,请尽快完成支付,以免影响到您的观影","id":2688,"pushTime":1571902856000,"status":0,"title":"系统通知","userId":13686},{"content":"您已购买电影票,请尽快完成支付,以免影响到您的观影","id":2655,"pushTime":1571901574000,"status":0,"title":"系统通知","userId":13686}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * content : 您已购买电影票,请尽快完成支付,以免影响到您的观影
         * id : 4956
         * pushTime : 1572177317000
         * status : 0
         * title : 系统通知
         * userId : 13686
         */

        public String content;
        public int id;
        public long pushTime;
        public int status;
        public String title;
        public int userId;
    }
}
