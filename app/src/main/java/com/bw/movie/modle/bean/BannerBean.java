package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/10/13
 * author:贺少伟(盗)
 * function:
 */
public class BannerBean {

    /**
     * result : [{"imageUrl":"http://172.17.8.100/images/movie/stills/dzd6qmwj/dzd6qmwj1_h.jpg","jumpUrl":"wd://movie?movieId=16","rank":1},{"imageUrl":"http://172.17.8.100/images/movie/stills/mtyj/mtyj1_h.jpg","jumpUrl":"wd://movie?movieId=2","rank":2},{"imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws1_h.jpg","jumpUrl":"wd://movie?movieId=20","rank":3},{"imageUrl":"http://172.17.8.100/images/movie/stills/sqmxtzdwbg/sqmxtzdwbg1_h.jpg","jumpUrl":"wd://movie?movieId=6","rank":4},{"imageUrl":"http://172.17.8.100/images/movie/stills/dwsj/dwsj1_h.jpg","jumpUrl":"wd://movie?movieId=10","rank":5}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * imageUrl : http://172.17.8.100/images/movie/stills/dzd6qmwj/dzd6qmwj1_h.jpg
         * jumpUrl : wd://movie?movieId=16
         * rank : 1
         */

        public String imageUrl;
        public String jumpUrl;
        public int rank;
    }
}
