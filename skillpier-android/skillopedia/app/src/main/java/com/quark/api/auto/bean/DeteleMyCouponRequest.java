package com.quark.api.auto.bean;
import java.util.ArrayList;
import java.util.List;
import com.quark.api.auto.bean.*;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-04 10:45:29
 *
 */
public class DeteleMyCouponRequest{
   public String url = "/app/CouponManage/deteleMyCoupon";
   public String method = "get";
   private String token;//token
   private String my_coupon_id;//
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

   public void setMy_coupon_id(String my_coupon_id){
     this.my_coupon_id = my_coupon_id;
   }
   public String getMy_coupon_id(){
     return this.my_coupon_id;
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
