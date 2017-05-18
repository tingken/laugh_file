package com.quark.api.auto.bean;
/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-04 17:16:18
 *
 */
public class MyCoupon{
   //
   public int my_coupon_id;
   //提供者
   public String provider;
   //优惠券单价
   public float coupon_money;
   //优惠券数量
   public int coupon_num;
   //有效期 开始时间
   public String begin_time;
   //结束时间
   public String end_time;
   //优惠券规则
   public String coupon_rule;

    public int getMy_coupon_id() {
        return my_coupon_id;
    }

    public void setMy_coupon_id(int my_coupon_id) {
        this.my_coupon_id = my_coupon_id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public float getCoupon_money() {
        return coupon_money;
    }

    public void setCoupon_money(float coupon_money) {
        this.coupon_money = coupon_money;
    }

    public int getCoupon_num() {
        return coupon_num;
    }

    public void setCoupon_num(int coupon_num) {
        this.coupon_num = coupon_num;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCoupon_rule() {
        return coupon_rule;
    }

    public void setCoupon_rule(String coupon_rule) {
        this.coupon_rule = coupon_rule;
    }
}