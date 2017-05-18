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
public class RejectConfirmOrdersRequest{
   public String url = "/app/CoachOrdersManage/rejectConfirmOrders";
   public String method = "get";
   private String token;//token
   private String schedule_type;//1-reject，2-confirm
   private String refund_reason;//教练refuse用户时间的原因
   private String orders_schedule_ids;//orders_schedule_id:多订单同时reject-confirm【11#22#1#23】，如果reject-confirm一个就传一个值
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

   public void setSchedule_type(String schedule_type){
     this.schedule_type = schedule_type;
   }
   public String getSchedule_type(){
     return this.schedule_type;
   }

   public void setRefund_reason(String refund_reason){
     this.refund_reason = refund_reason;
   }
   public String getRefund_reason(){
     return this.refund_reason;
   }

   public void setOrders_schedule_ids(String orders_schedule_ids){
     this.orders_schedule_ids = orders_schedule_ids;
   }
   public String getOrders_schedule_ids(){
     return this.orders_schedule_ids;
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
