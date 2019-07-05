package com.self.lihang.note.bean;

public class FollowBook {

    private Integer FollowBookId;
    private Integer user_id;
    private Integer book_id;
    private long follow_time;

    public Integer getFollowBookId() {
        return FollowBookId;
    }

    public void setFollowBookId(Integer followBookId) {
        FollowBookId = followBookId;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public long getFollow_time() {
        return follow_time;
    }

    public void setFollow_time(long follow_time) {
        this.follow_time = follow_time;
    }
}
