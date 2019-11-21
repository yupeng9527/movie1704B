package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/11/19
 * author:贺少伟(盗)
 * function:
 */
public class PriceByBean {

    /**
     * result : [{"address":"建国门外大街1号国贸商城区域三地下一层3B120","cinemaId":7,"logo":"http://172.17.8.100/images/movie/logo/blg.jpg","name":"北京百丽宫影城","price":0.11},{"address":"崇文门外大街18号国瑞城首层、地下二层","cinemaId":9,"logo":"http://172.17.8.100/images/movie/logo/blh.jpg","name":"北京百老汇影城国瑞购物中心店","price":0.13},{"address":"天桥南大街3号楼一层、二层（天桥艺术大厦南侧）","cinemaId":3,"logo":"http://172.17.8.100/images/movie/logo/sd.jpg","name":"首都电影院","price":0.18},{"address":"滨河路乙1号雍和航星园74-76号楼","cinemaId":1,"logo":"http://172.17.8.100/images/movie/logo/qcgx.jpg","name":"青春光线电影院","price":0.21},{"address":"中关村大街28号","cinemaId":16,"logo":"http://172.17.8.100/images/movie/logo/hdjy.jpg","name":"海淀剧院","price":0.22}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * address : 建国门外大街1号国贸商城区域三地下一层3B120
         * cinemaId : 7
         * logo : http://172.17.8.100/images/movie/logo/blg.jpg
         * name : 北京百丽宫影城
         * price : 0.11
         */

        public String address;
        public int cinemaId;
        public String logo;
        public String name;
        public double price;
    }
}
