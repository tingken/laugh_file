package com.quark.api.auto.bean;
/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-02 17:44:37
 *
 */
public class MyCouponListResult{
   //page number
   public MyCoupons MyCoupons;

   public void setMyCoupons(MyCoupons mycoupons){
     this.MyCoupons = mycoupons;
   }
   public MyCoupons getMyCoupons(){
     return this.MyCoupons;
   }
}