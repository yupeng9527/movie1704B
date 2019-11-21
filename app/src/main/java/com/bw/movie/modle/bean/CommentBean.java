package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/10/18
 * author:贺少伟(盗)
 * function:
 */
public class CommentBean {

    /**
     * result : [{"commentContent":"1","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":2156,"commentTime":1573782791000,"commentUserId":13584,"commentUserName":"打死","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4},{"commentContent":"乌鸦坐飞机，谷天乐上天","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-09-07/20190907140903.jpg","commentId":2126,"commentTime":1573003218000,"commentUserId":13472,"commentUserName":"红衣佳人白衣友","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":0},{"commentContent":"这是在陆路","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-23/20191023184812.jpg","commentId":2077,"commentTime":1572869453000,"commentUserId":13708,"commentUserName":"郭转晨","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":10},{"commentContent":"电影好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-11-07/20191107204917.png","commentId":1911,"commentTime":1572260844000,"commentUserId":13617,"commentUserName":"牛杰","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4},{"commentContent":"非常好","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-25/20191025152747.jpg","commentId":1864,"commentTime":1572003174000,"commentUserId":13610,"commentUserName":"诺","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":5.5},{"commentContent":"好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-28/20191028092656.jpg","commentId":1859,"commentTime":1571988104000,"commentUserId":13595,"commentUserName":"爱你的小哥哥","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4.5},{"commentContent":"很好","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-25/20191025160324.jpg","commentId":1812,"commentTime":1571982626000,"commentUserId":13609,"commentUserName":"wanggangwang","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4.5},{"commentContent":"很好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-25/20191025114604.jpg","commentId":1802,"commentTime":1571973188000,"commentUserId":13499,"commentUserName":"阿里巴巴","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":4.5},{"commentContent":"好看","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1745,"commentTime":1571626749000,"commentUserId":13665,"commentUserName":"123456789","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":5},{"commentContent":"大大","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":1685,"commentTime":1571229608000,"commentUserId":13585,"commentUserName":"席贵","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":3}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * commentContent : 1
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/bwjy.jpg
         * commentId : 2156
         * commentTime : 1573782791000
         * commentUserId : 13584
         * commentUserName : 打死
         * greatNum : 0
         * isGreat : 0
         * replyHeadPic : []
         * replyNum : 0
         * score : 4
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
        public double score;
        public List<?> replyHeadPic;
    }
}
