package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-30 18:23:10
 *
 */
public class DeleteCourseResponse{
   //
   public String message;
   //0-失败，1-操作成功，2-不存在
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public DeleteCourseResponse() {
    }
    public DeleteCourseResponse(String json) {
      Map<String, DeleteCourseResponse> map = JSON.parseObject(json, new TypeReference<Map<String, DeleteCourseResponse>>() {
      });
      this.message = map.get("DeleteCourseResponse").getMessage();
      this.status = map.get("DeleteCourseResponse").getStatus();
      this.code = map.get("DeleteCourseResponse").getCode();
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