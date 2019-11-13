package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/11/13
 * author:贺少伟(盗)
 * function:
 */
public class RegionListBean {

    /**
     * result : [{"regionId":1,"regionName":"海淀区"},{"regionId":2,"regionName":"朝阳区"},{"regionId":3,"regionName":"东城区"},{"regionId":4,"regionName":"西城区"},{"regionId":5,"regionName":"石景山区"},{"regionId":6,"regionName":"丰台区"},{"regionId":7,"regionName":"昌平区"},{"regionId":8,"regionName":"房山区"},{"regionId":9,"regionName":"大兴区"},{"regionId":10,"regionName":"通州区"},{"regionId":11,"regionName":"顺义区"}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * regionId : 1
         * regionName : 海淀区
         */

        public int regionId;
        public String regionName;
    }
}
