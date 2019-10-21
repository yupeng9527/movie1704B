package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/10/18
 * author:贺少伟(盗)
 * function:
 */
public class CommentBean {

    /**
     * result : [{"commentContent":"027","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-09-23/20190923191522.unknown","commentId":879,"commentTime":1569377787000,"commentUserId":13643,"commentUserName":"������ҵ","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":3},{"commentContent":"�̼�","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":195,"commentTime":1569377416000,"commentUserId":13458,"commentUserName":"23","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4.5},{"commentContent":"啊啊啊","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-09-17/20190917142448.jpeg","commentId":166,"commentTime":1569033578000,"commentUserId":13455,"commentUserName":"温文尔雅","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":0},{"commentContent":"444","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":162,"commentTime":1568987392000,"commentUserId":13578,"commentUserName":"清风","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":3},{"commentContent":"my comments","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":127,"commentTime":1568767478000,"commentUserId":13655,"commentUserName":"tester7","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":0},{"commentContent":"郭欣昊大哥666","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-09-23/20190923201747.jpg","commentId":104,"commentTime":1568549870000,"commentUserId":13557,"commentUserName":"你的昊哥","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":0},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-10/20191010164424.png","commentId":45,"commentTime":1567993176000,"commentUserId":13514,"commentUserName":"乔总","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4.5}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<RecommendBean.ResultBean> result;

    public static class ResultBean {
        /**
         * commentContent : 027
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/2019-09-23/20190923191522.unknown
         * commentId : 879
         * commentTime : 1569377787000
         * commentUserId : 13643
         * commentUserName : ������ҵ
         * greatNum : 0
         * isGreat : 0
         * replyHeadPic : []
         * replyNum : 0
         * score : 3
         */

        public String commentContent;
        public String commentHeadPic;
        public int commentId;
        public long commentTime;
        public int commentUserId;
        public String commentUserName;
        public int greatNum;
        public int isGreat;
        public int replyNum;
        public int score;
        public List<?> replyHeadPic;
    }
}
