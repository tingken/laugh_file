package com.quark.api.auto.bean;
import java.util.ArrayList;
import java.util.List;
import com.quark.api.auto.bean.*;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-30 18:23:10
 *
 */
public class SetStopCourseRequest{
   public String url = "/app/ScheduleManage/setStopCourse";
   public String method = "get";
   private String token;//token
   private String is_stop_course;//今后暂停课程 0-否，1-是
   private String app_sign;//app的签名
   private String invoke;//Infer
   public void setUrl(String url){this.url = url;}
   public String getUrl(){return this.url;}
   public void setMethod(String method){this.method = method;}
   public String getMethod(){return this.method;}

   public void setToken(String token){
     this.token = token;
   }
   public String getToken(){
     return this.token;
   }

   public void setIs_stop_course(String is_stop_course){
     this.is_stop_course = is_stop_course;
   }
   public String getIs_stop_course(){
     return this.is_stop_course;
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
