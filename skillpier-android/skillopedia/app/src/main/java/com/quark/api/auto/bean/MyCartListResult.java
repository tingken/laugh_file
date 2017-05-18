package com.quark.api.auto.bean;
/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-04 10:45:29
 *
 */
public class MyCartListResult{
   //是否官方认证：0-否，1-是
   public Carts Carts;

    public com.quark.api.auto.bean.Carts getCarts() {
        return Carts;
    }

    public void setCarts(com.quark.api.auto.bean.Carts carts) {
        Carts = carts;
    }
}