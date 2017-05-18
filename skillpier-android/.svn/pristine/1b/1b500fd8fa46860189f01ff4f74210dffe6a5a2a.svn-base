package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-06 10:16:17
 *
 */
public class BookingResponse{
   //
   public String message;
   //0-失败，1-操作成功，2-请选择课程时间，3-选择课程时间数必须小于
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public BookingResponse() {
    }
    public BookingResponse(String json) {
      Map<String, BookingResponse> map = JSON.parseObject(json, new TypeReference<Map<String, BookingResponse>>() {
      });
      this.message = map.get("BookingResponse").getMessage();
      this.status = map.get("BookingResponse").getStatus();
      this.code = map.get("BookingResponse").getCode();
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