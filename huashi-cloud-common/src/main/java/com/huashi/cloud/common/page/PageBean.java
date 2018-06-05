package com.huashi.cloud.common.page;


/**
 * 分页Bean
 *
 * @author Peter
 * @version V1.0 创建时间：2016年3月2日 下午5:03:11
 *          Copyright 2016 by PreTang
 */
public class PageBean {

    private Integer currentPage = 1;// 当前页码
    private Integer pageSize = 10;// 每页记录数
    private Integer totalCount = 0;// 总记录数
    private Integer pageCount = 0;// 总页数


    private Object  val;//数据

    public int getSkip()
    {
        return  (currentPage-1) * pageSize;
    }

    public Integer getPage() {
        return currentPage;
    }
    public void setPage (Integer page ) {
        this.currentPage  = page ;

    }

    public Integer getOffset(){
        return (currentPage-1) * pageSize;
    }

    public Integer getRows() {
        return pageSize;
    }

    public void setRows(Integer rows) {
        if (rows < 1) {
            rows = 1;
        } else if(rows > 500) {
            rows = 500;
        }
        this.pageSize = rows;
    }

    public boolean hasPrevious() {
        return currentPage > 0;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageCount() {
        pageCount = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            pageCount ++;
        }
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}