package com.bw.movie.modle.bean;


public class GuideBean {

    /**
     * result : {"sessionId":"15320592619803","userId":3,"userInfo":{"birthday":320256000000,"id":3,"lastLoginTime":1532059192000,"nickName":"你的益达","phone":"18600151568","sex":1,"headPic":"http://172.17.8.100/images/head_pic/bwjy.jpg"}}
     * message : 登陆成功
     * status : 0000
     */

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {
        /**
         * sessionId : 15320592619803
         * userId : 3
         * userInfo : {"birthday":320256000000,"id":3,"lastLoginTime":1532059192000,"nickName":"你的益达","phone":"18600151568","sex":1,"headPic":"http://172.17.8.100/images/head_pic/bwjy.jpg"}
         */

        public String sessionId;
        public int userId;
        public UserInfoBean userInfo;

        public static class UserInfoBean {
            /**
             * birthday : 320256000000
             * id : 3
             * lastLoginTime : 1532059192000
             * nickName : 你的益达
             * phone : 18600151568
             * sex : 1
             * headPic : http://172.17.8.100/images/head_pic/bwjy.jpg
             */

            public long birthday;
            public int id;
            public long lastLoginTime;
            public String nickName;
            public String phone;
            public int sex;
            public String headPic;
        }
    }
}
