package com.quark.api.auto.bean;
/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-28 16:08:34
 *
 */
public class CatetoryCourseListResult{
   //page number
   public Courses Courses;

   public void setCourses(Courses courses){
     this.Courses = courses;
   }
   public Courses getCourses(){
     return this.Courses;
   }
}