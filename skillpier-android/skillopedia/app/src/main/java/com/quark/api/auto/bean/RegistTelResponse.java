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
public class RegistTelResponse{
   //message
   public String message;
   //token
   public User user;
   //1-操作成功,2-验证码错误,3-请输入正确的邮箱,4-请输入昵称，5-请选择性别,6-请上传头像,7-手机号码已注册,8-登陆密码长度不合格，必须要大于6位以上，9-邮箱已注册，请重新输入
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public RegistTelResponse() {
    }
    public RegistTelResponse(String json) {
      Map<String, RegistTelResponse> map = JSON.parseObject(json, new TypeReference<Map<String, RegistTelResponse>>() {
      });
      this.message = map.get("RegistTelResponse").getMessage();
      this.user = map.get("RegistTelResponse").getUser();
      this.status = map.get("RegistTelResponse").getStatus();
      this.code = map.get("RegistTelResponse").getCode();
    }

   public void setMessage(String message){
     this.message = message;
   }
   public String getMessage(){
     return this.message;
   }
   public void setUser(User user){
     this.user = user;
   }
   public User getUser(){
     return this.user;
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