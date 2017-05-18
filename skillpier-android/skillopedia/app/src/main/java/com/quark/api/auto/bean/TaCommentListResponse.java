package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-06 15:41:07
 *
 */
public class TaCommentListResponse{
   //用户昵称
   public TaCommentListResult taCommentListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public TaCommentListResponse() {
    }
    public TaCommentListResponse(String json) {
      Map<String, TaCommentListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, TaCommentListResponse>>() {
      });
      this.taCommentListResult = map.get("TaCommentListResponse").getTaCommentListResult();
      this.message = map.get("TaCommentListResponse").getMessage();
      this.status = map.get("TaCommentListResponse").getStatus();
      this.code = map.get("TaCommentListResponse").getCode();
    }

   public void setTaCommentListResult(TaCommentListResult tacommentlistresult){
     this.taCommentListResult = tacommentlistresult;
   }
   public TaCommentListResult getTaCommentListResult(){
     return this.taCommentListResult;
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