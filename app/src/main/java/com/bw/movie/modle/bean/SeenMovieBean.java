package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/11/4
 * author:贺少伟(盗)
 * function:
 */
public class SeenMovieBean {


    /**
     * result : [{"beginTime":30360000,"cinemaId":1,"endTime":35700000,"imageUrl":"http://172.17.8.100/images/movie/stills/xbyz/xbyz1.jpg","movieId":5,"movieName":"邪不压正","recordId":1405,"whetherComment":1}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * beginTime : 30360000
         * cinemaId : 1
         * endTime : 35700000
         * imageUrl : http://172.17.8.100/images/movie/stills/xbyz/xbyz1.jpg
         * movieId : 5
         * movieName : 邪不压正
         * recordId : 1405
         * whetherComment : 1
         */

        public int beginTime;
        public int cinemaId;
        public int endTime;
        public String imageUrl;
        public int movieId;
        public String movieName;
        public int recordId;
        public int whetherComment;
    }
}
