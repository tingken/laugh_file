package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-06 18:50:32
 *
 */
public class RefundOrdersResponse{
   //
   public String message;
   //0-失败，1-操作成功，2-不存在
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public RefundOrdersResponse() {
    }
    public RefundOrdersResponse(String json) {
      Map<String, RefundOrdersResponse> map = JSON.parseObject(json, new TypeReference<Map<String, RefundOrdersResponse>>() {
      });
      this.message = map.get("RefundOrdersResponse").getMessage();
      this.status = map.get("RefundOrdersResponse").getStatus();
      this.code = map.get("RefundOrdersResponse").getCode();
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