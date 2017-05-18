package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-05-30 11:06:00
 *
 */
public class CatetoryListResponse{
   //
   public CatetoryListResult categoryListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public CatetoryListResponse() {
    }
    public CatetoryListResponse(String json) {
      Map<String, CatetoryListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CatetoryListResponse>>() {
      });
      this.categoryListResult = map.get("CategoryListResponse").getCategoryListResult();
      this.message = map.get("CategoryListResponse").getMessage();
      this.status = map.get("CategoryListResponse").getStatus();
      this.code = map.get("CategoryListResponse").getCode();
    }

    public CatetoryListResult getCategoryListResult() {
        return categoryListResult;
    }

    public void setCategoryListResult(CatetoryListResult categoryListResult) {
        this.categoryListResult = categoryListResult;
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