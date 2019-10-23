package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/10/22
 * author:贺少伟(盗)
 * function:
 */
public class ByRegionBean {

    /**
     * result : [{"address":"滨河路乙1号雍和航星园74-76号楼","cinemaId":1,"logo":"http://172.17.8.100/images/movie/logo/qcgx.jpg","name":"青春光线电影院","price":0},{"address":"远大路1号B座5层魔影国际影城","cinemaId":4,"logo":"http://172.17.8.100/images/movie/logo/mygj.jpg","name":"魔影国际影城","price":0}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * address : 滨河路乙1号雍和航星园74-76号楼
         * cinemaId : 1
         * logo : http://172.17.8.100/images/movie/logo/qcgx.jpg
         * name : 青春光线电影院
         * price : 0
         */

        public String address;
        public int cinemaId;
        public String logo;
        public String name;
        public int price;
    }
}
