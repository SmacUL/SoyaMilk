package com.self.lihang.note.bean;


import java.sql.Timestamp;

public class Collect {
    private Integer folder_id;

    @Override
    public String toString() {
        return "Collect{" +
                "folder_id=" + folder_id +
                ", notebook_id=" + notebook_id +
                ", collectionTime=" + collectionTime +
                '}';
    }

    private Integer notebook_id;
    private Timestamp collectionTime;

    public Integer getFolder_id() {
        return folder_id;
    }

    public void setFolder_id(Integer folder_id) {
        this.folder_id = folder_id;
    }

    public Integer getNotebook_id() {
        return notebook_id;
    }

    public void setNotebook_id(Integer notebook_id) {
        this.notebook_id = notebook_id;
    }

    public Timestamp getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Timestamp collectionTime) {
        this.collectionTime = collectionTime;
    }
}
