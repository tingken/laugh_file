package com.quark.api.auto.bean;
/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-06 10:16:17
 *
 */
public class TaCourseListResult{
   //page number
   public Courses Courses;

   public void setCourses(Courses courses){
     this.Courses = courses;
   }
   public Courses getCourses(){
     return this.Courses;
   }
}