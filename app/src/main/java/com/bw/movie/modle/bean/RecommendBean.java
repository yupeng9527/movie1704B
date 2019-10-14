package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/10/14
 * author:贺少伟(盗)
 * function:
 */
public class RecommendBean {

    /**
     * result : [{"address":"湖景东路11号新奥购物中心B1(东A口)","commentTotal":5,"distance":0,"followCinema":2,"id":5,"logo":"http://172.17.8.100/images/movie/logo/CGVxx.jpg","name":"CGV星星影城"},{"address":"建国路93号万达广场三层","commentTotal":4,"distance":0,"followCinema":2,"id":6,"logo":"http://172.17.8.100/images/movie/logo/wd.jpg","name":"北京CBD万达广场店"},{"address":"建国门外大街1号国贸商城区域三地下一层3B120","commentTotal":2,"distance":0,"followCinema":2,"id":7,"logo":"http://172.17.8.100/images/movie/logo/blg.jpg","name":"北京百丽宫影城"},{"address":"三丰北里2号楼悠唐广场B1层","commentTotal":2,"distance":0,"followCinema":2,"id":8,"logo":"http://172.17.8.100/images/movie/logo/bn.jpg","name":"北京博纳影城朝阳门旗舰店"},{"address":"崇文门外大街18号国瑞城首层、地下二层","commentTotal":1,"distance":0,"followCinema":2,"id":9,"logo":"http://172.17.8.100/images/movie/logo/blh.jpg","name":"北京百老汇影城国瑞购物中心店"},{"address":"广顺北大街16号望京华彩商业中心B1","commentTotal":3,"distance":0,"followCinema":2,"id":10,"logo":"http://172.17.8.100/images/movie/logo/hyxd.jpg","name":"华谊兄弟影院"},{"address":"远大路1号金源时代购物中心5层东首","commentTotal":1,"distance":0,"followCinema":2,"id":11,"logo":"http://172.17.8.100/images/movie/logo/xmgj.jpg","name":"星美国际影城"}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * address : 湖景东路11号新奥购物中心B1(东A口)
         * commentTotal : 5
         * distance : 0
         * followCinema : 2
         * id : 5
         * logo : http://172.17.8.100/images/movie/logo/CGVxx.jpg
         * name : CGV星星影城
         */

        public String address;
        public int commentTotal;
        public int distance;
        public int followCinema;
        public int id;
        public String logo;
        public String name;
    }
}
