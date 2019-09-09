package com.self.lihang.note.bean;

import java.sql.Timestamp;

public class NoteBook {

    private Integer notebookId;
    private Integer author_id;

    @Override
    public String toString() {
        return "NoteBook{" +
                "notebookId=" + notebookId +
                ", author_id=" + author_id +
                ", isPublic=" + isPublic +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", likeNumber=" + likeNumber +
                ", collectionNumber=" + collectionNumber +
                ", publicTime=" + publicTime +
                ", createTime=" + createTime +
                ", belongBook_id=" + belongBook_id +
                ", dislikeNumber=" + dislikeNumber +
                '}';
    }

    private Boolean isPublic;
    private String title;
    private String content;
    private Integer likeNumber;
    private Integer collectionNumber;
    private Timestamp publicTime;
    private Timestamp createTime;
    private Integer belongBook_id;
    private Integer dislikeNumber;

    public Integer getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(Integer notebookId) {
        this.notebookId = notebookId;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Integer getCollectionNumber() {
        return collectionNumber;
    }

    public void setCollectionNumber(Integer collectionNumber) {
        this.collectionNumber = collectionNumber;
    }

    public Timestamp getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(Timestamp publicTime) {
        this.publicTime = publicTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getBelongBook_id() {
        return belongBook_id;
    }

    public void setBelongBook_id(Integer belongBook_id) {
        this.belongBook_id = belongBook_id;
    }

    public Integer getDislikeNumber() {
        return dislikeNumber;
    }

    public void setDislikeNumber(Integer dislikeNumber) {
        this.dislikeNumber = dislikeNumber;
    }


}
