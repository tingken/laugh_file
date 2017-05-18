package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-07 15:34:21
 *
 */
public class CoachOrdersScheduleListBResponse{
   //课程id
   public List<ListScheduleBean> ScheduleBeans;
   //日期
   public String schedule_data;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public CoachOrdersScheduleListBResponse() {
    }
    public CoachOrdersScheduleListBResponse(String json) {
      Map<String, CoachOrdersScheduleListBResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CoachOrdersScheduleListBResponse>>() {
      });
      this.ScheduleBeans = map.get("CoachOrdersScheduleListBResponse").getScheduleBeans();
      this.schedule_data = map.get("CoachOrdersScheduleListBResponse").getSchedule_data();
      this.message = map.get("CoachOrdersScheduleListBResponse").getMessage();
      this.status = map.get("CoachOrdersScheduleListBResponse").getStatus();
      this.code = map.get("CoachOrdersScheduleListBResponse").getCode();
    }

    public List<ListScheduleBean> getScheduleBeans() {
        return ScheduleBeans;
    }

    public void setScheduleBeans(List<ListScheduleBean> scheduleBeans) {
        ScheduleBeans = scheduleBeans;
    }

    public void setSchedule_data(String schedule_data){
     this.schedule_data = schedule_data;
   }
   public String getSchedule_data(){
     return this.schedule_data;
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