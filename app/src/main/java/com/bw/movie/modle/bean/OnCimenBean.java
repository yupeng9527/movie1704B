package com.bw.movie.modle.bean;

import java.util.List;


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
