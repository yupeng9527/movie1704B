package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/11/1
 * author:贺少伟(盗)
 * function:
 */
public class OnCimenBean {


    /**
     * message : 无关注影院
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;
    public static class ResultBean {
        public int cinemaId;
        public String name;
        public String logo;
        public String address;

    }
}
