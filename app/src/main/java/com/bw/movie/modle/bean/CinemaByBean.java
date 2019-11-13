package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/11/13
 * author:贺少伟(盗)
 * function:
 */
public class CinemaByBean {

    /**
     * result : [{"id":1,"name":"青春光线电影院"},{"id":2,"name":"大观楼电影院"},{"id":3,"name":"首都电影院"},{"id":4,"name":"魔影国际影城"}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * id : 1
         * name : 青春光线电影院
         */

        public int id;
        public String name;
    }
}
