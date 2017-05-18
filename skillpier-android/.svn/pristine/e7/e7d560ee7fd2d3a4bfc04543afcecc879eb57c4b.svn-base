package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-28 16:08:34
 *
 */
public class AddExperienceResponse{
   //
   public String message;
   //1-操作成功，2-请上传图片
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public AddExperienceResponse() {
    }
    public AddExperienceResponse(String json) {
      Map<String, AddExperienceResponse> map = JSON.parseObject(json, new TypeReference<Map<String, AddExperienceResponse>>() {
      });
      this.message = map.get("AddExperienceResponse").getMessage();
      this.status = map.get("AddExperienceResponse").getStatus();
      this.code = map.get("AddExperienceResponse").getCode();
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