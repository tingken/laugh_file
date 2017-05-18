package com.quark.api.auto.bean;
import java.util.List;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-02 17:44:37
 *
 */
public class MyCoupons{
   //page number
   public int pageNumber;
   //result amount of this page
   public int pageSize;
   //total page
   public int totalPage;
   //total row
   public int totalRow;
   //优惠券价格
   public List<ListMyCoupon> list;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public List<ListMyCoupon> getList() {
        return list;
    }

    public void setList(List<ListMyCoupon> list) {
        this.list = list;
    }
}