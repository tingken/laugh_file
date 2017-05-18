package com.quark.api.auto.bean;
/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-30 18:23:10
 *
 */
public class MyCourseListResult{
   //page number
   public Courses Courses;

    public com.quark.api.auto.bean.Courses getCourses() {
        return Courses;
    }

    public void setCourses(com.quark.api.auto.bean.Courses courses) {
        Courses = courses;
    }
}