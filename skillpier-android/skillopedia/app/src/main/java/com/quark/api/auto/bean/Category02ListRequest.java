package com.quark.api.auto.bean;
import java.util.ArrayList;
import java.util.List;
import com.quark.api.auto.bean.*;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-05-28 18:53:18
 *
 */
public class Category02ListRequest{
   public String url = "/app/Home/category02List";
   public String method = "get";
   private String category_01_id;//
   private String app_sign;//app的签名
   private String invoke;//Infer
   public void setUrl(String url){this.url = url;}
   public String getUrl(){return this.url;}
   public void setMethod(String method){this.method = method;}
   public String getMethod(){return this.method;}

   public void setCategory_01_id(String category_01_id){
     this.category_01_id = category_01_id;
   }
   public String getCategory_01_id(){
     return this.category_01_id;
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
