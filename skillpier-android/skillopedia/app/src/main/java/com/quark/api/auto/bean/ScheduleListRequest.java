package com.quark.api.auto.bean;
import java.util.ArrayList;
import java.util.List;
import com.quark.api.auto.bean.*;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-28 16:08:35
 *
 */
public class ScheduleListRequest{
   public String url = "/app/ScheduleManage/scheduleList";
   public String method = "get";
   private String user_id;//
   private String choice_currentdate;//选择日期【2016-06-04】
   private String app_sign;//app的签名
   private String invoke;//Infer
   public void setUrl(String url){this.url = url;}
   public String getUrl(){return this.url;}
   public void setMethod(String method){this.method = method;}
   public String getMethod(){return this.method;}

   public void setUser_id(String user_id){
     this.user_id = user_id;
   }
   public String getUser_id(){
     return this.user_id;
   }

   public void setChoice_currentdate(String choice_currentdate){
     this.choice_currentdate = choice_currentdate;
   }
   public String getChoice_currentdate(){
     return this.choice_currentdate;
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
