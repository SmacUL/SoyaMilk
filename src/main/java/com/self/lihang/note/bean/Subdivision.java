package com.self.lihang.note.bean;

public class Subdivision {

    private Integer subdivisionId;
    private String subdivisionName;
    private Integer belongTop_id;

    public Integer getSubdivisionId() {
        return subdivisionId;
    }

    public void setSubdivisionId(Integer subdivisionId) {
        this.subdivisionId = subdivisionId;
    }

    public String getSubdivisionName() {
        return subdivisionName;
    }

    public void setSubdivisionName(String subdivisionName) {
        this.subdivisionName = subdivisionName;
    }

    public Integer getBelongTop_id() {
        return belongTop_id;
    }

    public void setBelongTop_id(Integer belongTop_id) {
        this.belongTop_id = belongTop_id;
    }
}
