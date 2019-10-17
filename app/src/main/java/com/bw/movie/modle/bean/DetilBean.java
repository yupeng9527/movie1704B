package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/10/15
 * author:贺少伟(盗)
 * function:
 */
public class DetilBean {

    /**
     * result : {"commentNum":20,"duration":"130分钟","imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws1.jpg","movieActor":[{"name":"周润发","photo":"http://172.17.8.100/images/movie/actor/ws/zhourunfa.jpg","role":"画家"},{"name":"郭富城","photo":"http://172.17.8.100/images/movie/actor/ws/guofucheng.jpg","role":"李问"},{"name":"张静初","photo":"http://172.17.8.100/images/movie/actor/ws/zhangjingchu.jpg","role":"阮文"},{"name":"冯文娟","photo":"http://172.17.8.100/images/movie/actor/ws/fengwenjuan.jpg","role":"秀清"},{"name":"廖启智","photo":"http://172.17.8.100/images/movie/actor/ws/liaoqizhi.jpg","role":"鑫叔"}],"movieDirector":[{"name":"庄文强","photo":"http://172.17.8.100/images/movie/director/ws/1.jpg"}],"movieId":20,"movieType":"动作 / 惊悚 / 犯罪","name":"无双","placeOrigin":"中国大陆,中国香港","posterList":["http://172.17.8.100/images/movie/stills/ws/ws1.jpg","http://172.17.8.100/images/movie/stills/ws/ws3.jpg","http://172.17.8.100/images/movie/stills/ws/ws2.jpg","http://172.17.8.100/images/movie/stills/ws/ws4.jpg","http://172.17.8.100/images/movie/stills/ws/ws5.jpg","http://172.17.8.100/images/movie/stills/ws/ws6.jpg"],"releaseTime":1537545600000,"score":8.6,"shortFilmList":[{"imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws3.jpg","videoUrl":"http://172.17.8.100/video/movie/ws/ws1.mp4"},{"imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws2.jpg","videoUrl":"http://172.17.8.100/video/movie/ws/ws2.mp4"},{"imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws4.jpg","videoUrl":"http://172.17.8.100/video/movie/ws/ws3.mp4"}],"summary":"以代号\u201c画家\u201d（周润发 饰）为首的犯罪团伙，掌握了制造伪钞技术，难辨真伪，并在全球进行交易获取利益，引起警方高度重视。然而\u201c画家\u201d和其他成员的身份一直成谜，警方的破案进度遭受到了前所未有的挑战。在关键时刻，擅长绘画的李问（郭富城 饰）打开了破案的突破口，而\u201c画家\u201d的真实身份却让众人意想不到\u2026\u2026","whetherFollow":2}
     * message : 查询成功
     * status : 0000
     */

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {
        /**
         * commentNum : 20
         * duration : 130分钟
         * imageUrl : http://172.17.8.100/images/movie/stills/ws/ws1.jpg
         * movieActor : [{"name":"周润发","photo":"http://172.17.8.100/images/movie/actor/ws/zhourunfa.jpg","role":"画家"},{"name":"郭富城","photo":"http://172.17.8.100/images/movie/actor/ws/guofucheng.jpg","role":"李问"},{"name":"张静初","photo":"http://172.17.8.100/images/movie/actor/ws/zhangjingchu.jpg","role":"阮文"},{"name":"冯文娟","photo":"http://172.17.8.100/images/movie/actor/ws/fengwenjuan.jpg","role":"秀清"},{"name":"廖启智","photo":"http://172.17.8.100/images/movie/actor/ws/liaoqizhi.jpg","role":"鑫叔"}]
         * movieDirector : [{"name":"庄文强","photo":"http://172.17.8.100/images/movie/director/ws/1.jpg"}]
         * movieId : 20
         * movieType : 动作 / 惊悚 / 犯罪
         * name : 无双
         * placeOrigin : 中国大陆,中国香港
         * posterList : ["http://172.17.8.100/images/movie/stills/ws/ws1.jpg","http://172.17.8.100/images/movie/stills/ws/ws3.jpg","http://172.17.8.100/images/movie/stills/ws/ws2.jpg","http://172.17.8.100/images/movie/stills/ws/ws4.jpg","http://172.17.8.100/images/movie/stills/ws/ws5.jpg","http://172.17.8.100/images/movie/stills/ws/ws6.jpg"]
         * releaseTime : 1537545600000
         * score : 8.6
         * shortFilmList : [{"imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws3.jpg","videoUrl":"http://172.17.8.100/video/movie/ws/ws1.mp4"},{"imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws2.jpg","videoUrl":"http://172.17.8.100/video/movie/ws/ws2.mp4"},{"imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws4.jpg","videoUrl":"http://172.17.8.100/video/movie/ws/ws3.mp4"}]
         * summary : 以代号“画家”（周润发 饰）为首的犯罪团伙，掌握了制造伪钞技术，难辨真伪，并在全球进行交易获取利益，引起警方高度重视。然而“画家”和其他成员的身份一直成谜，警方的破案进度遭受到了前所未有的挑战。在关键时刻，擅长绘画的李问（郭富城 饰）打开了破案的突破口，而“画家”的真实身份却让众人意想不到……
         * whetherFollow : 2
         */

        public int commentNum;
        public String duration;
        public String imageUrl;
        public int movieId;
        public String movieType;
        public String name;
        public String placeOrigin;
        public long releaseTime;
        public double score;
        public String summary;
        public int whetherFollow;
        public List<MovieActorBean> movieActor;
        public List<MovieDirectorBean> movieDirector;
        public List<String> posterList;
        public List<ShortFilmListBean> shortFilmList;

        public static class MovieActorBean {
            /**
             * name : 周润发
             * photo : http://172.17.8.100/images/movie/actor/ws/zhourunfa.jpg
             * role : 画家
             */

            public String name;
            public String photo;
            public String role;
        }

        public static class MovieDirectorBean {
            /**
             * name : 庄文强
             * photo : http://172.17.8.100/images/movie/director/ws/1.jpg
             */

            public String name;
            public String photo;
        }

        public static class ShortFilmListBean {
            /**
             * imageUrl : http://172.17.8.100/images/movie/stills/ws/ws3.jpg
             * videoUrl : http://172.17.8.100/video/movie/ws/ws1.mp4
             */

            public String imageUrl;
            public String videoUrl;
        }
    }
}
