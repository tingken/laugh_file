package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-02 11:36:43
 *
 */
public class EditCourseResponse{
   //
   public String message;
   //1-操作成功，2-请选择日期
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public EditCourseResponse() {
    }
    public EditCourseResponse(String json) {
      Map<String, EditCourseResponse> map = JSON.parseObject(json, new TypeReference<Map<String, EditCourseResponse>>() {
      });
      this.message = map.get("EditCourseResponse").getMessage();
      this.status = map.get("EditCourseResponse").getStatus();
      this.code = map.get("EditCourseResponse").getCode();
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