package com.bw.movie.modle.bean;

/**
 * date:2019/11/21
 * author:贺少伟(盗)
 * function:
 */
public class ExchangeCodeBean {

    /**
     * result : {"exchangeCode":"http://172.17.8.100/images/movie/movieCode/20191023212611425.jpg","id":0,"recordId":0,"status":0}
     * message : 查询成功
     * status : 0000
     */

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {
        /**
         * exchangeCode : http://172.17.8.100/images/movie/movieCode/20191023212611425.jpg
         * id : 0
         * recordId : 0
         * status : 0
         */

        public String exchangeCode;
        public int id;
        public int recordId;
        public int status;
    }
}
