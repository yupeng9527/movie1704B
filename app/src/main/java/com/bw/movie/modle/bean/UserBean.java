package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/10/28
 * author:贺少伟(盗)
 * function:
 */
public class UserBean {

    /**
     * result : [{"imageUrl":"http://172.17.8.100/images/movie/stills/jmyx/jmyx1.jpg","movieId":14,"name":"解码游戏","releaseTime":1599062400000,"wantSeeNum":2}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * imageUrl : http://172.17.8.100/images/movie/stills/jmyx/jmyx1.jpg
         * movieId : 14
         * name : 解码游戏
         * releaseTime : 1599062400000
         * wantSeeNum : 2
         */

        public String imageUrl;
        public int movieId;
        public String name;
        public long releaseTime;
        public int wantSeeNum;
    }
}
