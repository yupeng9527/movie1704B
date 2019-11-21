package com.bw.movie.modle.bean;

import java.util.List;

/**
 * date:2019/11/14
 * author:贺少伟(盗)
 * function:
 */
public class CinemaCommentBean {

    /**
     * result : [{"commentContent":"很棒","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":94,"commentTime":1573480318000,"commentUserId":13771,"commentUserName":"tzy","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"咋的了","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-31/20191031165328.png","commentId":93,"commentTime":1572512635000,"commentUserId":13678,"commentUserName":"abnetming","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"耐思","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/bwjy.jpg","commentId":90,"commentTime":1571986952000,"commentUserId":13715,"commentUserName":"张乐乐","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"就很棒","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-08-24/20190824094957.jpg","commentId":85,"commentTime":1571826049000,"commentUserId":13501,"commentUserName":"哥哥","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0},{"commentContent":"�ܺ�","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-10-24/20191024191144.jpg","commentId":53,"commentTime":1570842026000,"commentUserId":13544,"commentUserName":"woailuo","greatHeadPic":[],"greatNum":0,"hotComment":0,"isGreat":0}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * commentContent : 很棒
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/bwjy.jpg
         * commentId : 94
         * commentTime : 1573480318000
         * commentUserId : 13771
         * commentUserName : tzy
         * greatHeadPic : []
         * greatNum : 0
         * hotComment : 0
         * isGreat : 0
         */

        public String commentContent;
        public String commentHeadPic;
        public int commentId;
        public long commentTime;
        public int commentUserId;
        public String commentUserName;
        public int greatNum;
        public int hotComment;
        public int isGreat;
        public List<?> greatHeadPic;
    }
}
