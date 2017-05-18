package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-04 17:59:30
 *
 */
public class OrdersListResponse{
   //page number
   public OrdersListResult ordersListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public OrdersListResponse() {
    }
    public OrdersListResponse(String json) {
      Map<String, OrdersListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, OrdersListResponse>>() {
      });
      this.ordersListResult = map.get("OrdersListResponse").getOrdersListResult();
      this.message = map.get("OrdersListResponse").getMessage();
      this.status = map.get("OrdersListResponse").getStatus();
      this.code = map.get("OrdersListResponse").getCode();
    }

    public OrdersListResult getOrdersListResult() {
        return ordersListResult;
    }

    public void setOrdersListResult(OrdersListResult ordersListResult) {
        this.ordersListResult = ordersListResult;
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