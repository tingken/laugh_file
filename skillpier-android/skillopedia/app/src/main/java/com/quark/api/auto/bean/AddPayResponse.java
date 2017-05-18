package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-02 17:23:45
 *
 */
public class AddPayResponse{
   //订单ID
   public int orders_id;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public AddPayResponse() {
    }
    public AddPayResponse(String json) {
      Map<String, AddPayResponse> map = JSON.parseObject(json, new TypeReference<Map<String, AddPayResponse>>() {
      });
      this.orders_id = map.get("AddPayResponse").getOrders_id();
      this.message = map.get("AddPayResponse").getMessage();
      this.status = map.get("AddPayResponse").getStatus();
      this.code = map.get("AddPayResponse").getCode();
    }

   public void setOrders_id(int orders_id){
     this.orders_id = orders_id;
   }
   public int getOrders_id(){
     return this.orders_id;
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