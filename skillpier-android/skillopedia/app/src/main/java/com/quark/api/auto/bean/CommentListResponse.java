package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-06 18:50:32
 *
 */
public class CommentListResponse{
   //page number
   public CommentListResult commentListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public CommentListResponse() {
    }
    public CommentListResponse(String json) {
      Map<String, CommentListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CommentListResponse>>() {
      });
      this.commentListResult = map.get("CommentListResponse").getCommentListResult();
      this.message = map.get("CommentListResponse").getMessage();
      this.status = map.get("CommentListResponse").getStatus();
      this.code = map.get("CommentListResponse").getCode();
    }

   public void setCommentListResult(CommentListResult commentlistresult){
     this.commentListResult = commentlistresult;
   }
   public CommentListResult getCommentListResult(){
     return this.commentListResult;
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