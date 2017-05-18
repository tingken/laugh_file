package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-09 17:35:02
 *
 */
public class CoachOrderInfoResponse{
   //
   public Orders Orders;
   //
   public String message;
   //0-失败，1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public CoachOrderInfoResponse() {
    }
    public CoachOrderInfoResponse(String json) {
      Map<String, CoachOrderInfoResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CoachOrderInfoResponse>>() {
      });
      this.Orders = map.get("CoachOrderInfoResponse").getOrders();
      this.message = map.get("CoachOrderInfoResponse").getMessage();
      this.status = map.get("CoachOrderInfoResponse").getStatus();
      this.code = map.get("CoachOrderInfoResponse").getCode();
    }

   public void setOrders(Orders orders){
     this.Orders = orders;
   }
   public Orders getOrders(){
     return this.Orders;
   }
   public void setMessage(String message){
     this.message = message;
   }
   public String getMessage(){
     return this.message;
   }
   public void setStatus(int status){
     this.status = status;
   }
   public int getStatus(){
     return this.status;
   }
   public void setCode(int code){
     this.code = code;
   }
   public int getCode(){
     return this.code;
   }
}