package com.quark.api.auto.bean;
/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-30 18:23:10
 *
 */
public class ListCollection{
   //是否官方认证：0-否，1-是
   public Course Course;
   //
   public int collection_id;
   //
   public int course_id;

   public void setCourse(Course course){
     this.Course = course;
   }
   public Course getCourse(){
     return this.Course;
   }
   public void setCollection_id(int collection_id){
     this.collection_id = collection_id;
   }
   public int getCollection_id(){
     return this.collection_id;
   }
   public void setCourse_id(int course_id){
     this.course_id = course_id;
   }
   public int getCourse_id(){
     return this.course_id;
   }
}