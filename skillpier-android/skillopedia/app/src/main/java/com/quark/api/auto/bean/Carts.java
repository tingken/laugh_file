package com.quark.api.auto.bean;
import java.util.List;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-04 10:45:29
 *
 */
public class Carts{
    int totalRow;//":4,
    int pageNumber;//":1,
    int totalPage;//":1,
    int pageSize;//":10,
    List<CarLists> list;//"

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<CarLists> getList() {
        return list;
    }

    public void setList(List<CarLists> list) {
        this.list = list;
    }
}