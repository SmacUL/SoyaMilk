package com.self.lihang.note.bean;

import java.sql.Timestamp;

public class FollowUser {

    private Integer id;
    private Integer fans_id;
    private Integer idol_id;
    private Timestamp follow_time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFans_id() {
        return fans_id;
    }

    public void setFans_id(Integer fans_id) {
        this.fans_id = fans_id;
    }

    public Integer getIdol_id() {
        return idol_id;
    }

    public void setIdol_id(Integer idol_id) {
        this.idol_id = idol_id;
    }

    public Timestamp getFollow_time() {
        return follow_time;
    }

    public void setFollow_time(Timestamp follow_time) {
        this.follow_time = follow_time;
    }
}
