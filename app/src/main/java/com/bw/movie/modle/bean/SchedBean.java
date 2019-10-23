package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/10/22
 * author:贺少伟(盗)
 * function:
 */
public class SchedBean {

    /**
     * result : [{"beginTime":"18:30:00","endTime":"19:55:00","fare":0.22,"hallId":3,"id":24,"screeningHall":"3号厅"}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * beginTime : 18:30:00
         * endTime : 19:55:00
         * fare : 0.22
         * hallId : 3
         * id : 24
         * screeningHall : 3号厅
         */

        public String beginTime;
        public String endTime;
        public double fare;
        public int hallId;
        public int id;
        public String screeningHall;
    }
}
