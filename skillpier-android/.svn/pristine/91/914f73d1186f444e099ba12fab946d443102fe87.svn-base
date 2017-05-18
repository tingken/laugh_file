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
public class checkEmailResponse{
   //
   public String message;
   //1-成功(无需提示):2-邮箱已经被注册,3-邮箱不正确
   public int status;
   //200-正常返回，405-重新登陆
   public String code;
   //验证码
   public String tel_code;
    public checkEmailResponse() {
    }
    public checkEmailResponse(String json) {
      Map<String, checkEmailResponse> map = JSON.parseObject(json, new TypeReference<Map<String, checkEmailResponse>>() {
      });
      this.message = map.get("checkEmailResponse").getMessage();
      this.status = map.get("checkEmailResponse").getStatus();
      this.code = map.get("checkEmailResponse").getCode();
      this.tel_code = map.get("checkEmailResponse").getTel_code();
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
   public void setCode(String code){
     this.code = code;
   }
   public String getCode(){
     return this.code;
   }
   public void setTel_code(String tel_code){
     this.tel_code = tel_code;
   }
   public String getTel_code(){
     return this.tel_code;
   }
}