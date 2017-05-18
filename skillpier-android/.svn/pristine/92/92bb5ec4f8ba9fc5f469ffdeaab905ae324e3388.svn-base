package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-20 16:42:27
 *
 */
public class AddCourseResponse{
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public AddCourseResponse() {
    }
    public AddCourseResponse(String json) {
      Map<String, AddCourseResponse> map = JSON.parseObject(json, new TypeReference<Map<String, AddCourseResponse>>() {
      });
      this.message = map.get("AddCourseResponse").getMessage();
      this.status = map.get("AddCourseResponse").getStatus();
      this.code = map.get("AddCourseResponse").getCode();
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