package com.quark.api.auto.bean;
import java.util.ArrayList;
import java.util.List;
import com.quark.api.auto.bean.*;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-08-19 16:17:17
 *
 */
public class IsFulfilTravelDistanceRequest{
   public String url = "/app/CourseManage/isFulfilTravelDistance";
   public String method = "get";
   private String course_id;//
   private String latitude;//维度【上课地址】
   private String longitude;//经度【上课地址】
   private String app_sign;//app的签名
   private String invoke;//Infer
   public void setUrl(String url){this.url = url;}
   public String getUrl(){return this.url;}
   public void setMethod(String method){this.method = method;}
   public String getMethod(){return this.method;}

   public void setCourse_id(String course_id){
     this.course_id = course_id;
   }
   public String getCourse_id(){
     return this.course_id;
   }

   public void setLatitude(String latitude){
     this.latitude = latitude;
   }
   public String getLatitude(){
     return this.latitude;
   }

   public void setLongitude(String longitude){
     this.longitude = longitude;
   }
   public String getLongitude(){
     return this.longitude;
   }

   public void setApp_sign(String app_sign){
     this.app_sign = app_sign;
   }
   public String getApp_sign(){
     return this.app_sign;
   }

   public void setInvoke(String invoke){
     this.invoke = invoke;
   }
   public String getInvoke(){
     return this.invoke;
   }

}
