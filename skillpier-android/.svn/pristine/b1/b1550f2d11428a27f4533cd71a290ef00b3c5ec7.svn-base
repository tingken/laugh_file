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
public class TaCourseListResponse{
   //page number
   public TaCourseListResult taCourseListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public TaCourseListResponse() {
    }
    public TaCourseListResponse(String json) {
      Map<String, TaCourseListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, TaCourseListResponse>>() {
      });
      this.taCourseListResult = map.get("TaCourseListResponse").getTaCourseListResult();
      this.message = map.get("TaCourseListResponse").getMessage();
      this.status = map.get("TaCourseListResponse").getStatus();
      this.code = map.get("TaCourseListResponse").getCode();
    }

   public void setTaCourseListResult(TaCourseListResult tacourselistresult){
     this.taCourseListResult = tacourselistresult;
   }
   public TaCourseListResult getTaCourseListResult(){
     return this.taCourseListResult;
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