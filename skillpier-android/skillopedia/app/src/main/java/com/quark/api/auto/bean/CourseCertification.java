package com.quark.api.auto.bean;
/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-13 18:43:33
 *
 */
public class CourseCertification{
   //
   public int course_certification_id;
   //课程id
   public int course_id;
   //证书名称
   public String name;
   //获取时间
   public String get_time;
   //机构名称
   public String institue;
   //图片
   public String image_01;
   //上传时间
   public String post_time;


   public void setCourse_certification_id(int course_certification_id){
     this.course_certification_id = course_certification_id;
   }
   public int getCourse_certification_id(){
     return this.course_certification_id;
   }
   public void setCourse_id(int course_id){
     this.course_id = course_id;
   }
   public int getCourse_id(){
     return this.course_id;
   }
   public void setName(String name){
     this.name = name;
   }
   public String getName(){
     return this.name;
   }
   public void setGet_time(String get_time){
     this.get_time = get_time;
   }
   public String getGet_time(){
     return this.get_time;
   }
   public void setInstitue(String institue){
     this.institue = institue;
   }
   public String getInstitue(){
     return this.institue;
   }
   public void setImage_01(String image_01){
     this.image_01 = image_01;
   }
   public String getImage_01(){
     return this.image_01;
   }
   public void setPost_time(String post_time){
     this.post_time = post_time;
   }
   public String getPost_time(){
     return this.post_time;
   }
}