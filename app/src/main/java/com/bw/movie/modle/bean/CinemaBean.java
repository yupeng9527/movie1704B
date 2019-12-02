package com.bw.movie.modle.bean;

import java.util.List;

public class CinemaBean {

    /**
     * result : [{"address":"上地南口华联商厦4F","commentTotal":4,"distance":1647,"followCinema":2,"id":18,"logo":"http://172.17.8.100/images/movie/logo/ctjh.jpg","name":"橙天嘉禾影城北京上地店"},{"address":"清河中街68号五彩城购物中心东区7层","commentTotal":0,"distance":3315,"followCinema":2,"id":22,"logo":"http://172.17.8.100/images/movie/logo/CGVyc.jpg","name":"CGV影城"},{"address":"悦秀路99号二层大地影院","commentTotal":2,"distance":3416,"followCinema":2,"id":19,"logo":"http://172.17.8.100/images/movie/logo/ddyy.jpg","name":"大地影院-北京海淀西三旗物美"},{"address":"育知东路30号华联商厦4层","commentTotal":2,"distance":4838,"followCinema":2,"id":20,"logo":"http://172.17.8.100/images/movie/logo/wmyc.jpg","name":"北京沃美影城"},{"address":"黄平路19号院龙旗购物中心3层","commentTotal":4,"distance":5090,"followCinema":2,"id":17,"logo":"http://172.17.8.100/images/movie/logo/blgj.jpg","name":"保利国际影城北京龙旗广场店"},{"address":"中关村广场购物中心津乐汇三层（鼎好一期西侧）","commentTotal":3,"distance":6387,"followCinema":2,"id":12,"logo":"http://172.17.8.100/images/movie/logo/mjhlyc.jpg","name":"美嘉欢乐影城中关村店"},{"address":"中关村大街19号新中关购物中心B1层","commentTotal":3,"distance":6982,"followCinema":2,"id":15,"logo":"http://172.17.8.100/images/movie/logo/jy.jpg","name":"金逸北京中关村店"},{"address":"中关村大街28号","commentTotal":2,"distance":7224,"followCinema":2,"id":16,"logo":"http://172.17.8.100/images/movie/logo/hdjy.jpg","name":"海淀剧院"},{"address":"远大路1号B座5层魔影国际影城","commentTotal":3,"distance":8836,"followCinema":2,"id":4,"logo":"http://172.17.8.100/images/movie/logo/mygj.jpg","name":"魔影国际影城"},{"address":"远大路1号金源时代购物中心5层东首","commentTotal":2,"distance":9140,"followCinema":2,"id":11,"logo":"http://172.17.8.100/images/movie/logo/xmgj.jpg","name":"星美国际影城"},{"address":"湖景东路11号新奥购物中心B1(东A口)","commentTotal":5,"distance":9222,"followCinema":2,"id":5,"logo":"http://172.17.8.100/images/movie/logo/CGVxx.jpg","name":"CGV星星影城"},{"address":"新街口外大街25号","commentTotal":4,"distance":11416,"followCinema":2,"id":14,"logo":"http://172.17.8.100/images/movie/logo/zygj.jpg","name":"中影国际影城北京小西天店"},{"address":"复兴路69号五棵松卓展时代百货五层东侧","commentTotal":2,"distance":14011,"followCinema":2,"id":13,"logo":"http://172.17.8.100/images/movie/logo/bjalclgj.jpg","name":"北京耀莱成龙国际影城"},{"address":"滨河路乙1号雍和航星园74-76号楼","commentTotal":9,"distance":14604,"followCinema":2,"id":1,"logo":"http://172.17.8.100/images/movie/logo/qcgx.jpg","name":"青春光线电影院"},{"address":"广顺北大街16号望京华彩商业中心B1","commentTotal":4,"distance":14833,"followCinema":2,"id":10,"logo":"http://172.17.8.100/images/movie/logo/hyxd.jpg","name":"华谊兄弟影院"},{"address":"前门大街大栅栏街36号","commentTotal":3,"distance":17774,"followCinema":2,"id":2,"logo":"http://172.17.8.100/images/movie/logo/dgl.jpg","name":"大观楼电影院"},{"address":"三丰北里2号楼悠唐广场B1层","commentTotal":2,"distance":17782,"followCinema":2,"id":8,"logo":"http://172.17.8.100/images/movie/logo/bn.jpg","name":"北京博纳影城朝阳门旗舰店"},{"address":"崇文门外大街18号国瑞城首层、地下二层","commentTotal":2,"distance":18769,"followCinema":2,"id":9,"logo":"http://172.17.8.100/images/movie/logo/blh.jpg","name":"北京百老汇影城国瑞购物中心店"},{"address":"天桥南大街3号楼一层、二层（天桥艺术大厦南侧）","commentTotal":3,"distance":19201,"followCinema":2,"id":3,"logo":"http://172.17.8.100/images/movie/logo/sd.jpg","name":"首都电影院"},{"address":"建国门外大街1号国贸商城区域三地下一层3B120","commentTotal":2,"distance":19748,"followCinema":2,"id":7,"logo":"http://172.17.8.100/images/movie/logo/blg.jpg","name":"北京百丽宫影城"},{"address":"十八里店西直河商业中心东融国际影城","commentTotal":1,"distance":29202,"followCinema":2,"id":21,"logo":"http://172.17.8.100/images/movie/logo/drgjyc.jpg","name":"东融国际影城西直河店"},{"address":"建国路93号万达广场三层","commentTotal":4,"distance":857490,"followCinema":2,"id":6,"logo":"http://172.17.8.100/images/movie/logo/wd.jpg","name":"北京CBD万达广场店"}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * address : 上地南口华联商厦4F
         * commentTotal : 4
         * distance : 1647
         * followCinema : 2
         * id : 18
         * logo : http://172.17.8.100/images/movie/logo/ctjh.jpg
         * name : 橙天嘉禾影城北京上地店
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
