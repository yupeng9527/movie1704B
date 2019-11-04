package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/10/28
 * author:贺少伟(盗)
 * function:
 */
public class MyMovieBean {

    /**
     * result : [{"amount":0,"beginTime":"16:26:00","cinemaName":"青春光线电影院","createTime":1571837171000,"endTime":"17:55:00","id":1405,"movieName":"邪不压正","price":0,"screeningHall":"IMAX厅","seat":"6-2","status":0,"userId":0}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * amount : 0
         * beginTime : 16:26:00
         * cinemaName : 青春光线电影院
         * createTime : 1571837171000
         * endTime : 17:55:00
         * id : 1405
         * movieName : 邪不压正
         * price : 0
         * screeningHall : IMAX厅
         * seat : 6-2
         * status : 0
         * userId : 0
         */

        public int amount;
        public String beginTime;
        public String cinemaName;
        public long createTime;
        public String endTime;
        public int id;
        public String movieName;
        public int price;
        public String screeningHall;
        public String seat;
        public int status;
        public int userId;
    }
}
