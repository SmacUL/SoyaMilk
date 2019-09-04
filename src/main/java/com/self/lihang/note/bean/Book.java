package com.self.lihang.note.bean;

public class Book {

    public Integer bookId;
    public String bookName;
    public String  bookContributors;
    public String bookCover;
    public Integer belongSubdivision_id;

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookContributors='" + bookContributors + '\'' +
                ", bookCover='" + bookCover + '\'' +
                ", belongSubdivision_id=" + belongSubdivision_id +
                ", bookBriefIntroduction='" + bookBriefIntroduction + '\'' +
                ", noteNumber=" + noteNumber +
                ", authorInfo='" + authorInfo + '\'' +
                ", catalog='" + catalog + '\'' +
                '}';
    }

    public String bookBriefIntroduction;
    public Integer noteNumber;
    public String authorInfo;
    public String catalog;



    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookContributors() {
        return bookContributors;
    }

    public void setBookContributors(String bookContributors) {
        this.bookContributors = bookContributors;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getBookBriefIntroduction() {
        return bookBriefIntroduction;
    }

    public void setBookBriefIntroduction(String bookBriefIntroduction) {
        this.bookBriefIntroduction = bookBriefIntroduction;
    }

    public Integer getBelongSubdivision_id() {
        return belongSubdivision_id;
    }

    public void setBelongSubdivision_id(Integer belongSubdivision_id) {
        this.belongSubdivision_id = belongSubdivision_id;
    }

    public Integer getNoteNumber() {
        return noteNumber;
    }

    public void setNoteNumber(Integer noteNumber) {
        this.noteNumber = noteNumber;
    }
}

