package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-04 15:54:41
 *
 */
public class AuthenResponse{
   //
   public String message;
   //1-操作成功，0-失败
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public AuthenResponse() {
    }
    public AuthenResponse(String json) {
      Map<String, AuthenResponse> map = JSON.parseObject(json, new TypeReference<Map<String, AuthenResponse>>() {
      });
      this.message = map.get("AuthenResponse").getMessage();
      this.status = map.get("AuthenResponse").getStatus();
      this.code = map.get("AuthenResponse").getCode();
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