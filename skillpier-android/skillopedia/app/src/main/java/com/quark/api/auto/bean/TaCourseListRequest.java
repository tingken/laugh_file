package com.quark.api.auto.bean;
/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-04 17:59:30
 *
 */
public class TaCourseListRequest{
   public String url = "/app/PersonalHomepage/taCourseList";
   public String method = "get";
   private String user_id;//
   private int pn;//Infer
   private int page_size;//Infer
   private String latitude;//维度【上课地址】
   private String longitude;//经度【上课地址】
   private String app_sign;//app的签名
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

    public int getPn() {
        return pn;
    }

    public void setPn(int pn) {
        this.pn = pn;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
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

}
