package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/11/29
 * author:贺少伟(盗)
 * function:
 */
public class CinmeaKeyBean {

    /**
     * result : [{"address":"建国路93号万达广场三层","commentTotal":0,"distance":0,"followCinema":0,"id":6,"logo":"http://172.17.8.100/images/movie/logo/wd.jpg","name":"北京CBD万达广场店"}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    @Override
    public String toString() {
        return "CinmeaKeyBean{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", result=" + result +
                '}';
    }

    public static class ResultBean {
        /**
         * address : 建国路93号万达广场三层
         * commentTotal : 0
         * distance : 0
         * followCinema : 0
         * id : 6
         * logo : http://172.17.8.100/images/movie/logo/wd.jpg
         * name : 北京CBD万达广场店
         */

        public String address;
        public int commentTotal;
        public int distance;
        public int followCinema;
        public int id;
        public String logo;
        public String name;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "address='" + address + '\'' +
                    ", commentTotal=" + commentTotal +
                    ", distance=" + distance +
                    ", followCinema=" + followCinema +
                    ", id=" + id +
                    ", logo='" + logo + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
