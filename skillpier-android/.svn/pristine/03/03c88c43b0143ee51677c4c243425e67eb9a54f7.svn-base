package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-07 15:34:21
 *
 */
public class CommentReplyResponse{
   //
   public String message;
   //0-失败，1-操作成功，2-不存在
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public CommentReplyResponse() {
    }
    public CommentReplyResponse(String json) {
      Map<String, CommentReplyResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CommentReplyResponse>>() {
      });
      this.message = map.get("CommentReplyResponse").getMessage();
      this.status = map.get("CommentReplyResponse").getStatus();
      this.code = map.get("CommentReplyResponse").getCode();
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