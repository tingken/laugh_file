package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-05-27 10:26:42
 *
 */
public class getCodeResponse{
   //
   public String message;
   //0-获取验证码失败，1-获取成功,2-邮箱不正确
   public int status;
   //验证码
   public String email_code;
    public getCodeResponse() {
    }
    public getCodeResponse(String json) {
      Map<String, getCodeResponse> map = JSON.parseObject(json, new TypeReference<Map<String, getCodeResponse>>() {
      });
      this.message = map.get("getCodeResponse").getMessage();
      this.status = map.get("getCodeResponse").getStatus();
      this.email_code = map.get("getCodeResponse").getEmail_code();
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
   public void setEmail_code(String email_code){
     this.email_code = email_code;
   }
   public String getEmail_code(){
     return this.email_code;
   }
}