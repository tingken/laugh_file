package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-05-28 18:53:18
 *
 */
public class Category02ListResponse{
   //封面
   public Category02ListResult category02ListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public Category02ListResponse() {
    }
    public Category02ListResponse(String json) {
      Map<String, Category02ListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, Category02ListResponse>>() {
      });
      this.category02ListResult = map.get("Category02ListResponse").getCategory02ListResult();
      this.message = map.get("Category02ListResponse").getMessage();
      this.status = map.get("Category02ListResponse").getStatus();
      this.code = map.get("Category02ListResponse").getCode();
    }

    public Category02ListResult getCategory02ListResult() {
        return category02ListResult;
    }

    public void setCategory02ListResult(Category02ListResult category02ListResult) {
        this.category02ListResult = category02ListResult;
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