package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-09 17:35:02
 *
 */
public class CommentCourseInfoResponse{
   //
   public CommentCourseInfo CommentCourseInfo;
   //
   public String message;
   //0-失败，1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public CommentCourseInfoResponse() {
    }
    public CommentCourseInfoResponse(String json) {
      Map<String, CommentCourseInfoResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CommentCourseInfoResponse>>() {
      });
      this.CommentCourseInfo = map.get("CommentCourseInfoResponse").getCommentCourseInfo();
      this.message = map.get("CommentCourseInfoResponse").getMessage();
      this.status = map.get("CommentCourseInfoResponse").getStatus();
      this.code = map.get("CommentCourseInfoResponse").getCode();
    }

   public void setCommentCourseInfo(CommentCourseInfo commentcourseinfo){
     this.CommentCourseInfo = commentcourseinfo;
   }
   public CommentCourseInfo getCommentCourseInfo(){
     return this.CommentCourseInfo;
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