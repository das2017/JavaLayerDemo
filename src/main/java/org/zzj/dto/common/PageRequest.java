package org.zzj.dto.common;

public class PageRequest {
    /**
     * 第几页
     */
    private int pageIndex;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * LIMIT行开始
     */
    private transient int rowBegin;

    public int getPageIndex() {
        return pageIndex > 1 ? pageIndex : 1;
    }

    public int getPageSize() {
        return pageSize > 1 ? pageSize : 10;

    }

    public int getRowBegin() {
        return (getPageIndex() - 1) * getPageSize();
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    private void setRowBegin(int rowBegin) {
        this.rowBegin = rowBegin;
    }
}