package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/11/20
 * author:贺少伟(盗)
 * function:
 */
public class MovieDiscussBean {

    /**
     * result : [{"commentTime":1574246105000,"director":"李海龙","imageUrl":"http://172.17.8.100/images/movie/stills/jmyx/jmyx1.jpg","movieId":14,"movieName":"解码游戏","movieScore":0,"myCommentContent":"","myCommentScore":1.5,"starring":"韩庚,凤小岳,李媛,山下智久"},{"commentTime":1574164367000,"director":"\r\n刘伟强","imageUrl":"http://172.17.8.100/images/movie/stills/zgjz/zgjz1.jpg","movieId":24,"movieName":"中国机长","movieScore":0,"myCommentContent":"动不动","myCommentScore":1.5,"starring":"张涵予,欧豪,袁泉,张天爱,李沁"},{"commentTime":1574164277000,"director":"克里斯托弗·麦奎里","imageUrl":"http://172.17.8.100/images/movie/stills/dzd6qmwj/dzd6qmwj1.jpg","movieId":16,"movieName":"碟中谍6：全面瓦解","movieScore":0,"myCommentContent":"好多好多话","myCommentScore":1.5,"starring":"汤姆·克鲁斯,亨利·卡维尔,丽贝卡·弗格森,西蒙·佩吉"}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * commentTime : 1574246105000
         * director : 李海龙
         * imageUrl : http://172.17.8.100/images/movie/stills/jmyx/jmyx1.jpg
         * movieId : 14
         * movieName : 解码游戏
         * movieScore : 0
         * myCommentContent :
         * myCommentScore : 1.5
         * starring : 韩庚,凤小岳,李媛,山下智久
         */

        public long commentTime;
        public String director;
        public String imageUrl;
        public int movieId;
        public String movieName;
        public int movieScore;
        public String myCommentContent;
        public double myCommentScore;
        public String starring;
    }
}
