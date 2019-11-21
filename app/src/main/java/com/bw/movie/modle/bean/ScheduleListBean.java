package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/11/15
 * author:贺少伟(盗)
 * function:
 */
public class ScheduleListBean {


    /**
     * result : [{"director":"\r\n刘伟强","imageUrl":"http://172.17.8.100/images/movie/stills/zgjz/zgjz1.jpg","movieId":24,"name":"中国机长","score":9.4,"starring":"张涵予,欧豪,袁泉,张天爱,李沁","trailerUrl":"http://172.17.8.100/video/movie/zgjz/zgjz1.mp4"},{"director":"胡安·安东尼奥·巴亚纳","imageUrl":"http://172.17.8.100/images/movie/stills/zljgy/zljgy1.jpg","movieId":8,"name":"侏罗纪世界2","score":9.3,"starring":"克里斯·帕拉特,布莱丝·达拉斯·霍华德,泰德·拉文","trailerUrl":"http://172.17.8.100/video/movie/zljgy/zljgy1.ts"},{"director":"曾国祥","imageUrl":"http://172.17.8.100/images/movie/stills/sndn/sndn1.jpg","movieId":22,"name":"少年的你","score":9.5,"starring":"周冬雨,易烊千玺,张耀,周也,尹昉","trailerUrl":"http://172.17.8.100/video/movie/sndn/sndn1.mp4"},{"director":"陈凯歌","imageUrl":"http://172.17.8.100/images/movie/stills/whwdzg/whwdzg1.jpg","movieId":23,"name":"我和我的祖国","score":9.7,"starring":"黄渤,张译,杜江,葛优,刘昊然,吴京","trailerUrl":"http://172.17.8.100/video/movie/whwdzg/whwdzg1.mp4"},{"director":"\r\n李仁港","imageUrl":"http://172.17.8.100/images/movie/stills/pdz/pdz1.jpg","movieId":25,"name":"攀登者","score":9.4,"starring":"吴京,章子怡,井柏然,胡歌","trailerUrl":"http://172.17.8.100/video/movie/pdz/pdz1.mp4"},{"director":"拉加·高斯内尔","imageUrl":"http://172.17.8.100/images/movie/stills/wxwd/wxwd1.jpg","movieId":7,"name":"汪星卧底","score":9.2,"starring":"卢达克里斯,威尔·阿奈特,娜塔莎·雷昂","trailerUrl":"http://172.17.8.100/video/movie/wxwd/wxwd1.ts"},{"director":"徐克","imageUrl":"http://172.17.8.100/images/movie/stills/drjzsdtw/drjzsdtw1.jpg","movieId":4,"name":"狄仁杰之四大天王","score":8.7,"starring":"赵又廷,冯绍峰,林更新,刘嘉玲,阮经天,马思纯","trailerUrl":"http://172.17.8.100/video/movie/drjzsdtw/drjzsdtw1.ts"},{"director":"托尼·班克罗夫特","imageUrl":"http://172.17.8.100/images/movie/stills/sqmxtzdwbg/sqmxtzdwbg1.jpg","movieId":6,"name":"神奇马戏团之动物饼干","score":9.1,"starring":"艾米莉·布朗特,约翰·卡拉辛斯基,西尔维斯特·史泰龙","trailerUrl":"http://172.17.8.100/video/movie/sqmxtzdwsj/sqmxtzdwsj1.ts"},{"director":"闫非","imageUrl":"http://172.17.8.100/images/movie/stills/xhssf/xhssf1.jpg","movieId":3,"name":"西虹市首富","score":8.6,"starring":"沈腾,宋芸烨,张晨光,艾伦,常远","trailerUrl":"http://172.17.8.100/video/movie/xhssf/xhssf1.ts"}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * director :
         刘伟强
         * imageUrl : http://172.17.8.100/images/movie/stills/zgjz/zgjz1.jpg
         * movieId : 24
         * name : 中国机长
         * score : 9.4
         * starring : 张涵予,欧豪,袁泉,张天爱,李沁
         * trailerUrl : http://172.17.8.100/video/movie/zgjz/zgjz1.mp4
         */

        public String director;
        public String imageUrl;
        public int movieId;
        public String name;
        public double score;
        public String starring;
        public String trailerUrl;
    }
}
