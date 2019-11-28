package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/11/28
 * author:贺少伟(盗)
 * function:
 */
public class MovieByKeyBean {

    /**
     * result : [{"director":"陈凯歌","imageUrl":"http://172.17.8.100/images/movie/stills/whwdzg/whwdzg1.jpg","movieId":23,"name":"我和我的祖国","score":9.7,"starring":"黄渤,张译,杜江,葛优,刘昊然,吴京"},{"director":"文牧野","imageUrl":"http://172.17.8.100/images/movie/stills/wbsys/wbsys1.jpg","movieId":1,"name":"我不是药神","score":8.9,"starring":"徐峥,周一围,王传君,谭卓,章宇,杨新鸣,王砚辉"}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * director : 陈凯歌
         * imageUrl : http://172.17.8.100/images/movie/stills/whwdzg/whwdzg1.jpg
         * movieId : 23
         * name : 我和我的祖国
         * score : 9.7
         * starring : 黄渤,张译,杜江,葛优,刘昊然,吴京
         */

        public String director;
        public String imageUrl;
        public int movieId;
        public String name;
        public double score;
        public String starring;
    }
}
