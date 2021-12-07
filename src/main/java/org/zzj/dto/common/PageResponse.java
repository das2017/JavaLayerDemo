package org.zzj.dto.common;

import java.io.Serializable;
import java.util.List;
/**
 * 分页结果类
 */
public class PageResponse<T> implements Serializable {
    /**
     * 第几页
     */
    private int pageIndex;

    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 总页数
     */
    private int pages;


    /**
     * 结果集
     */
    private List<T> list;

    public int getPageIndex() {
        return pageIndex > 1 ? pageIndex : 1;
    }

    public int getPageSize() {
        return pageSize > 1 ? pageSize : 10;
    }

    public long getTotal() {
        return total;
    }

    public int getPages() {
        return Math.toIntExact((total + pageSize - 1) / pageSize);
    }

    public List<T> getList() {
        return list;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    private void setPages(int pages) {
        this.pages = pages;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
