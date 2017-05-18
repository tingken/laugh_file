package com.quark.api.auto.bean;
import java.util.ArrayList;
import java.util.List;
import com.quark.api.auto.bean.*;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-07 15:34:21
 *
 */
public class OrderInfoRequest{
   public String url = "/app/OrdersManage/orderInfo";
   public String method = "get";
   private String token;//token
   private String orders_id;//
   private String latitude;//维度【上课地址】
   private String longitude;//经度【上课地址】
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

   public void setOrders_id(String orders_id){
     this.orders_id = orders_id;
   }
   public String getOrders_id(){
     return this.orders_id;
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

   public void setInvoke(String invoke){
     this.invoke = invoke;
   }
   public String getInvoke(){
     return this.invoke;
   }

}
