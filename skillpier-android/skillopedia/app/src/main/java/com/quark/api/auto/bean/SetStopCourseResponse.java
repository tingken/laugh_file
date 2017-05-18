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
public class SetStopCourseResponse{
   //1-unavaliable，2-busy,3-有空
   public String schedule_state;
   //状态文字
   public String schedule_state_message;
   //
   public String message;
   //0-失败，1-操作成功，2-不能设置历史时间
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public SetStopCourseResponse() {
    }
    public SetStopCourseResponse(String json) {
      Map<String, SetStopCourseResponse> map = JSON.parseObject(json, new TypeReference<Map<String, SetStopCourseResponse>>() {
      });
      this.schedule_state = map.get("SetStopCourseResponse").getSchedule_state();
      this.schedule_state_message = map.get("SetStopCourseResponse").getSchedule_state_message();
      this.message = map.get("SetStopCourseResponse").getMessage();
      this.status = map.get("SetStopCourseResponse").getStatus();
      this.code = map.get("SetStopCourseResponse").getCode();
    }

   public void setSchedule_state(String schedule_state){
     this.schedule_state = schedule_state;
   }
   public String getSchedule_state(){
     return this.schedule_state;
   }
   public void setSchedule_state_message(String schedule_state_message){
     this.schedule_state_message = schedule_state_message;
   }
   public String getSchedule_state_message(){
     return this.schedule_state_message;
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