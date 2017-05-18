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
public class CancelOrdersResponse{
   //
   public String message;
   //0-失败，1-操作成功，2-不存在
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public CancelOrdersResponse() {
    }
    public CancelOrdersResponse(String json) {
      Map<String, CancelOrdersResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CancelOrdersResponse>>() {
      });
      this.message = map.get("CancelOrdersResponse").getMessage();
      this.status = map.get("CancelOrdersResponse").getStatus();
      this.code = map.get("CancelOrdersResponse").getCode();
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