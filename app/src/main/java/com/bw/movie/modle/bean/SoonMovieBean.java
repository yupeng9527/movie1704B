package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/10/10
 * author:贺少伟(盗)
 * function:
 */
public class SoonMovieBean {

    /**
     * result : [{"imageUrl":"http://172.17.8.100/images/movie/stills/dzd6qmwj/dzd6qmwj1.jpg","movieId":16,"name":"碟中谍6：全面瓦解","releaseTime":1600704000000,"wantSeeNum":23,"whetherReserve":2},{"imageUrl":"http://172.17.8.100/images/movie/stills/jmyx/jmyx1.jpg","movieId":14,"name":"解码游戏","releaseTime":1599062400000,"wantSeeNum":26,"whetherReserve":2},{"imageUrl":"http://172.17.8.100/images/movie/stills/drjzsdtw/drjzsdtw1.jpg","movieId":4,"name":"狄仁杰之四大天王","releaseTime":1598457600000,"wantSeeNum":20,"whetherReserve":2},{"imageUrl":"http://172.17.8.100/images/movie/stills/aqgy/aqgy1.jpg","movieId":15,"name":"爱情公寓","releaseTime":1596988800000,"wantSeeNum":10,"whetherReserve":2},{"imageUrl":"http://172.17.8.100/images/movie/stills/xhssf/xhssf1.jpg","movieId":3,"name":"西虹市首富","releaseTime":1595779200000,"wantSeeNum":13,"whetherReserve":2},{"imageUrl":"http://172.17.8.100/images/movie/stills/mtyj/mtyj1.jpg","movieId":2,"name":"摩天营救","releaseTime":1595174400000,"wantSeeNum":10,"whetherReserve":2},{"imageUrl":"http://172.17.8.100/images/movie/stills/xbyz/xbyz1.jpg","movieId":5,"name":"邪不压正","releaseTime":1594569600000,"wantSeeNum":8,"whetherReserve":2}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * imageUrl : http://172.17.8.100/images/movie/stills/dzd6qmwj/dzd6qmwj1.jpg
         * movieId : 16
         * name : 碟中谍6：全面瓦解
         * releaseTime : 1600704000000
         * wantSeeNum : 23
         * whetherReserve : 2
         */

        public String imageUrl;
        public int movieId;
        public String name;
        public long releaseTime;
        public int wantSeeNum;
        public int whetherReserve;
    }
}
