package com.bw.movie.modle.bean;

import java.io.Serializable;

/**
 * date:2019/11/13
 * author:贺少伟(盗)
 * function:
 */
public class CinemaInfoBean{

    /**
     * result : {"address":"育知东路30号华联商厦4层","businessHoursContent":"星期一 至 星期日   早10:00:00 - 晚24:00:00","commentTotal":0,"distance":0,"followCinema":2,"id":20,"label":"儿童优惠,3D眼镜,4K厅","logo":"http://172.17.8.100/images/movie/logo/wmyc.jpg","name":"北京沃美影城","phone":"4006819819","vehicleRoute":"地铁13号线、460路公交到达"}
     * message : 查询成功
     * status : 0000
     */

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {
        /**
         * address : 育知东路30号华联商厦4层
         * businessHoursContent : 星期一 至 星期日   早10:00:00 - 晚24:00:00
         * commentTotal : 0
         * distance : 0
         * followCinema : 2
         * id : 20
         * label : 儿童优惠,3D眼镜,4K厅
         * logo : http://172.17.8.100/images/movie/logo/wmyc.jpg
         * name : 北京沃美影城
         * phone : 4006819819
         * vehicleRoute : 地铁13号线、460路公交到达
         */

        public String address;
        public String businessHoursContent;
        public int commentTotal;
        public int distance;
        public int followCinema;
        public int id;
        public String label;
        public String logo;
        public String name;
        public String phone;
        public String vehicleRoute;
    }
}
