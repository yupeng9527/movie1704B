package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/10/22
 * author:贺少伟(盗)
 * function:
 */
public class SeatleBean {

    /**
     * result : [{"row":"1","seat":"1","status":2},{"row":"1","seat":"2","status":1},{"row":"1","seat":"3","status":1},{"row":"1","seat":"4","status":1},{"row":"1","seat":"5","status":1},{"row":"1","seat":"6","status":1},{"row":"1","seat":"7","status":1},{"row":"1","seat":"8","status":1},{"row":"1","seat":"9","status":1},{"row":"2","seat":"1","status":1},{"row":"2","seat":"2","status":1},{"row":"2","seat":"3","status":1},{"row":"2","seat":"4","status":1},{"row":"2","seat":"5","status":1},{"row":"2","seat":"6","status":1},{"row":"2","seat":"7","status":1},{"row":"2","seat":"8","status":1},{"row":"2","seat":"9","status":1},{"row":"3","seat":"1","status":2},{"row":"3","seat":"2","status":1},{"row":"3","seat":"3","status":1},{"row":"3","seat":"4","status":1},{"row":"3","seat":"5","status":2},{"row":"3","seat":"6","status":1},{"row":"3","seat":"7","status":2},{"row":"3","seat":"8","status":2},{"row":"3","seat":"9","status":1},{"row":"4","seat":"1","status":1},{"row":"4","seat":"2","status":1},{"row":"4","seat":"3","status":1},{"row":"4","seat":"4","status":2},{"row":"4","seat":"5","status":2},{"row":"4","seat":"6","status":1},{"row":"4","seat":"7","status":1},{"row":"4","seat":"8","status":1},{"row":"4","seat":"9","status":1},{"row":"5","seat":"1","status":1},{"row":"5","seat":"2","status":1},{"row":"5","seat":"3","status":1},{"row":"5","seat":"4","status":1},{"row":"5","seat":"5","status":1},{"row":"5","seat":"6","status":1},{"row":"5","seat":"7","status":1},{"row":"5","seat":"8","status":1},{"row":"5","seat":"9","status":1},{"row":"6","seat":"1","status":1},{"row":"6","seat":"2","status":1},{"row":"6","seat":"3","status":1},{"row":"6","seat":"4","status":1},{"row":"6","seat":"5","status":1},{"row":"6","seat":"6","status":1},{"row":"6","seat":"7","status":2},{"row":"6","seat":"8","status":1},{"row":"6","seat":"9","status":1},{"row":"7","seat":"1","status":1},{"row":"7","seat":"2","status":1},{"row":"7","seat":"3","status":1},{"row":"7","seat":"4","status":1},{"row":"7","seat":"5","status":1},{"row":"7","seat":"6","status":1},{"row":"7","seat":"7","status":2},{"row":"7","seat":"8","status":1},{"row":"7","seat":"9","status":1},{"row":"8","seat":"1","status":1},{"row":"8","seat":"2","status":1},{"row":"8","seat":"3","status":1},{"row":"8","seat":"4","status":1},{"row":"8","seat":"5","status":1},{"row":"8","seat":"6","status":1},{"row":"8","seat":"7","status":2},{"row":"8","seat":"8","status":1},{"row":"8","seat":"9","status":2}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * row : 1
         * seat : 1
         * status : 2
         */

        public String row;
        public String seat;
        public int status;


    }
}
