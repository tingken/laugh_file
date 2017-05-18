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
public class MyCourseListResponse{
   //page number
   public MyCourseListResult myCourseListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public MyCourseListResponse() {
    }
    public MyCourseListResponse(String json) {
      Map<String, MyCourseListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, MyCourseListResponse>>() {
      });
      this.myCourseListResult = map.get("MyCourseListResponse").getMyCourseListResult();
      this.message = map.get("MyCourseListResponse").getMessage();
      this.status = map.get("MyCourseListResponse").getStatus();
      this.code = map.get("MyCourseListResponse").getCode();
    }

    public MyCourseListResult getMyCourseListResult() {
        return myCourseListResult;
    }

    public void setMyCourseListResult(MyCourseListResult myCourseListResult) {
        this.myCourseListResult = myCourseListResult;
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