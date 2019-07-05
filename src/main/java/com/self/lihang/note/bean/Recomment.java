package com.self.lihang.note.bean;

import java.sql.Timestamp;

public class Recomment {
        private Integer recommentId;
        private Integer user_id;
        private Integer comment_id;
        private String content;
        private Timestamp recommentTime;

    public Integer getRecommentId() {
        return recommentId;
    }

    public void setRecommentId(Integer recommentId) {
        this.recommentId = recommentId;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getRecommentTime() {
        return recommentTime;
    }

    public void setRecommentTime(Timestamp recommentTime) {
        this.recommentTime = recommentTime;
    }
}
