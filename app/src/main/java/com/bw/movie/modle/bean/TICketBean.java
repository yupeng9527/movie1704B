package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/10/24
 * author:贺少伟(盗)
 * function:
 */
public class TICketBean {

    /**
     * result : [{"amount":1,"createTime":1571837171000,"id":1405,"imageUrl":"http://172.17.8.100/images/movie/stills/xbyz/xbyz1.jpg","movieName":"邪不压正","orderId":"20191023212611425","price":0.18}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * amount : 1
         * createTime : 1571837171000
         * id : 1405
         * imageUrl : http://172.17.8.100/images/movie/stills/xbyz/xbyz1.jpg
         * movieName : 邪不压正
         * orderId : 20191023212611425
         * price : 0.18
         */

        public int amount;
        public long createTime;
        public int id;
        public String imageUrl;
        public String movieName;
        public String orderId;
        public double price;
    }
}
