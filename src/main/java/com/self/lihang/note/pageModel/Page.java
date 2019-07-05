package com.self.lihang.note.pageModel;

import java.util.List;

public class Page {

    public Integer totalCount;
    public Integer pageSize;
    public Integer pageNo;
    public Integer startRow;
    public List list;

    public void computeStartRow() {
        this.startRow = (pageNo - 1) * pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public List getList() { return list; }

    public void setList(List list) {
        this.list = list;
    }
}
