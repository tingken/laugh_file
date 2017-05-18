package com.quark.api.auto.bean;
/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-28 16:08:34
 *
 */
public class CourseBanners{
   //banner图片
   public ListCourseBanner listCourseBanner;
   //vedio_url
   public ListCourseVedio listCourseVedio;

    public ListCourseBanner getListCourseBanner() {
        return listCourseBanner;
    }

    public void setListCourseBanner(ListCourseBanner listCourseBanner) {
        this.listCourseBanner = listCourseBanner;
    }

    public ListCourseVedio getListCourseVedio() {
        return listCourseVedio;
    }

    public void setListCourseVedio(ListCourseVedio listCourseVedio) {
        this.listCourseVedio = listCourseVedio;
    }
}