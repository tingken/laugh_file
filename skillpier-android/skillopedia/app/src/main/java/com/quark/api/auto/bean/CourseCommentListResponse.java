package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-28 16:08:34
 *
 */
public class CourseCommentListResponse{
   //page number
   public CourseCommentListResult courseCommentListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public CourseCommentListResponse() {
    }
    public CourseCommentListResponse(String json) {
      Map<String, CourseCommentListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CourseCommentListResponse>>() {
      });
      this.courseCommentListResult = map.get("CourseCommentListResponse").getCourseCommentListResult();
      this.message = map.get("CourseCommentListResponse").getMessage();
      this.status = map.get("CourseCommentListResponse").getStatus();
      this.code = map.get("CourseCommentListResponse").getCode();
    }

   public void setCourseCommentListResult(CourseCommentListResult coursecommentlistresult){
     this.courseCommentListResult = coursecommentlistresult;
   }
   public CourseCommentListResult getCourseCommentListResult(){
     return this.courseCommentListResult;
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