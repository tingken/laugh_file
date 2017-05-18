package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-05-30 11:06:00
 *
 */
public class SendForgetEmailResponse{
   //1-获取成功，0-失败，2-没注册
   public int status;
   //邮箱验证码
   public String message;
   //200-正常返回，405-重新登陆
   public String code;
    public SendForgetEmailResponse() {
    }
    public SendForgetEmailResponse(String json) {
      Map<String, SendForgetEmailResponse> map = JSON.parseObject(json, new TypeReference<Map<String, SendForgetEmailResponse>>() {
      });
      this.status = map.get("SendForgetEmailResponse").getStatus();
      this.message = map.get("SendForgetEmailResponse").getMessage();
      this.code = map.get("SendForgetEmailResponse").getCode();
    }

   public void setStatus(int status){
     this.status = status;
   }
   public int getStatus(){
     return this.status;
   }
   public void setMessage(String message){
     this.message = message;
   }
   public String getMessage(){
     return this.message;
   }
   public void setCode(String code){
     this.code = code;
   }
   public String getCode(){
     return this.code;
   }
}