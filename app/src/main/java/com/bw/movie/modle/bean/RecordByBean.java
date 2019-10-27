package com.bw.movie.modle.bean;

/**
 * date:2019/10/24
 * author:贺少伟(盗)
 * function:
 */
public class RecordByBean {

    /**
     * result : {"amount":1,"beginTime":"16:26:00","cinemaName":"青春光线电影院","createTime":1571837171000,"endTime":"17:55:00","id":1405,"movieName":"邪不压正","orderId":"20191023212611425","price":0.18,"screeningHall":"IMAX厅","seat":"6-2","status":2,"userId":13686}
     * message : 查询成功
     * status : 0000
     */

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {
        /**
         * amount : 1
         * beginTime : 16:26:00
         * cinemaName : 青春光线电影院
         * createTime : 1571837171000
         * endTime : 17:55:00
         * id : 1405
         * movieName : 邪不压正
         * orderId : 20191023212611425
         * price : 0.18
         * screeningHall : IMAX厅
         * seat : 6-2
         * status : 2
         * userId : 13686
         */

        public int amount;
        public String beginTime;
        public String cinemaName;
        public long createTime;
        public String endTime;
        public int id;
        public String movieName;
        public String orderId;
        public double price;
        public String screeningHall;
        public String seat;
        public int status;
        public int userId;
    }
}
