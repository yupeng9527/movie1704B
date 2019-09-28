package com.bw.movie.modle.bean;

/**
 * date:2019/9/28
 * author:贺少伟(盗)
 * function:
 */
public class GuideBean {

    /**
     * result : {"sessionId":"156965719651213584","userId":13584,"userInfo":{"email":"715220171@qq.com","headPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","id":13584,"lastLoginTime":1569576738000,"nickName":"打死","sex":1}}
     * message : 登陆成功
     * status : 0000
     */

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {
        /**
         * sessionId : 156965719651213584
         * userId : 13584
         * userInfo : {"email":"715220171@qq.com","headPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","id":13584,"lastLoginTime":1569576738000,"nickName":"打死","sex":1}
         */

        public String sessionId;
        public int userId;
        public UserInfoBean userInfo;

        public static class UserInfoBean {
            /**
             * email : 715220171@qq.com
             * headPic : http://172.17.8.100/images/movie/head_pic/bwjy.jpg
             * id : 13584
             * lastLoginTime : 1569576738000
             * nickName : 打死
             * sex : 1
             */

            public String email;
            public String headPic;
            public int id;
            public long lastLoginTime;
            public String nickName;
            public int sex;
        }
    }
}
