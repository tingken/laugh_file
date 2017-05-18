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
public class CoachOrdersScheduleListAResponse{
   //课程日期
   public List<ScheduleDatas> scheduleDatas;
   //
   public String message;
   //1-操作成功，2-客户未选择时间课程
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public CoachOrdersScheduleListAResponse() {
    }
    public CoachOrdersScheduleListAResponse(String json) {
      Map<String, CoachOrdersScheduleListAResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CoachOrdersScheduleListAResponse>>() {
      });
      this.scheduleDatas = map.get("CoachOrdersScheduleListAResponse").getScheduleDatas();
      this.message = map.get("CoachOrdersScheduleListAResponse").getMessage();
      this.status = map.get("CoachOrdersScheduleListAResponse").getStatus();
      this.code = map.get("CoachOrdersScheduleListAResponse").getCode();
    }

    public List<ScheduleDatas> getScheduleDatas() {
        return scheduleDatas;
    }

    public void setScheduleDatas(List<ScheduleDatas> scheduleDatas) {
        this.scheduleDatas = scheduleDatas;
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