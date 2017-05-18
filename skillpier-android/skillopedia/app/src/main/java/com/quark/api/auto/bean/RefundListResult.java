package com.quark.api.auto.bean;

import java.util.List;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-06 18:50:32
 *
 */
public class RefundListResult {
   //
   public List<Refunds> Refunds;
    public RefundOrders Orders;

    public List<com.quark.api.auto.bean.Refunds> getRefunds() {
        return Refunds;
    }

    public void setRefunds(List<com.quark.api.auto.bean.Refunds> refunds) {
        Refunds = refunds;
    }

    public RefundOrders getOrders() {
        return Orders;
    }

    public void setOrders(RefundOrders orders) {
        Orders = orders;
    }
}