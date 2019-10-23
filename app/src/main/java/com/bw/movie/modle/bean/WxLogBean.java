package com.bw.movie.modle.bean;

/**
 * date:2019/10/23
 * author:贺少伟(盗)
 * function:
 */
public class WxLogBean {


    /**
     * result : {"sessionId":"157181797774713752","userId":13752,"userInfo":{"headPic":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKmcYngnB5v6a5Kk4k8nDRcFCQOzibMfgQuFM2sRZ8AVGXmIuic7jqIjVzRc4WQ10WNaTJo8wvdGyag/132","id":13752,"lastLoginTime":1571817977000,"nickName":"茶叶_N1i","sex":1}}
     * message : 登陆成功
     * status : 0000
     */

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {
        /**
         * sessionId : 157181797774713752
         * userId : 13752
         * userInfo : {"headPic":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKmcYngnB5v6a5Kk4k8nDRcFCQOzibMfgQuFM2sRZ8AVGXmIuic7jqIjVzRc4WQ10WNaTJo8wvdGyag/132","id":13752,"lastLoginTime":1571817977000,"nickName":"茶叶_N1i","sex":1}
         */

        public String sessionId;
        public int userId;
        public UserInfoBean userInfo;

        public static class UserInfoBean {
            /**
             * headPic : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKmcYngnB5v6a5Kk4k8nDRcFCQOzibMfgQuFM2sRZ8AVGXmIuic7jqIjVzRc4WQ10WNaTJo8wvdGyag/132
             * id : 13752
             * lastLoginTime : 1571817977000
             * nickName : 茶叶_N1i
             * sex : 1
             */

            public String headPic;
            public int id;
            public long lastLoginTime;
            public String nickName;
            public int sex;
        }
    }
}
