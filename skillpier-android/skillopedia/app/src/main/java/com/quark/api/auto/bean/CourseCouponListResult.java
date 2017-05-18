package com.quark.api.auto.bean;

import java.util.List;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-04 17:59:30
 *
 */
public class CourseCouponListResult{
   //
   public List<ListMyCoupon> CourseCoupons;

    public List<ListMyCoupon> getCourseCoupons() {
        return CourseCoupons;
    }

    public void setCourseCoupons(List<ListMyCoupon> courseCoupons) {
        CourseCoupons = courseCoupons;
    }
}