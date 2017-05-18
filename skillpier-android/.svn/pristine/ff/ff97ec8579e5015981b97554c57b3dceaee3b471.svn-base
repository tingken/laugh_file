package com.quark.api.auto.bean;
/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-06 15:41:07
 *
 */
public class ComfiyPayRequest{
   public String url = "/app/OrdersManage/comfiyPay";
   public String method = "get";
   private String token;//token
   private String total_money;//付款总额
   private String orders_ids;//多订单ID同时支付【11A22A1A23】A分割，支付一个就传一个值
   private String pay_type;//1-支付宝，2-visa，3-paypal
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

   public void setTotal_money(String total_money){
     this.total_money = total_money;
   }
   public String getTotal_money(){
     return this.total_money;
   }

   public void setOrders_ids(String orders_ids){
     this.orders_ids = orders_ids;
   }
   public String getOrders_ids(){
     return this.orders_ids;
   }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
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
