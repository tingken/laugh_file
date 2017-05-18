package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-28 16:08:35
 *
 */
public class ExperienceInfoResponse{
   //
   public ExperienceInfoResult experienceInfoResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public ExperienceInfoResponse() {
    }
    public ExperienceInfoResponse(String json) {
      Map<String, ExperienceInfoResponse> map = JSON.parseObject(json, new TypeReference<Map<String, ExperienceInfoResponse>>() {
      });
      this.experienceInfoResult = map.get("ExperienceInfoResponse").getExperienceInfoResult();
      this.message = map.get("ExperienceInfoResponse").getMessage();
      this.status = map.get("ExperienceInfoResponse").getStatus();
      this.code = map.get("ExperienceInfoResponse").getCode();
    }

    public ExperienceInfoResult getExperienceInfoResult() {
        return experienceInfoResult;
    }

    public void setExperienceInfoResult(ExperienceInfoResult experienceInfoResult) {
        this.experienceInfoResult = experienceInfoResult;
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