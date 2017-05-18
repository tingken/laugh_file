package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-04 18:15:52
 *
 */
public class CategoryListResponse{
   //
   public CategoryListResult categoryListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public CategoryListResponse() {
    }
    public CategoryListResponse(String json) {
      Map<String, CategoryListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CategoryListResponse>>() {
      });
      this.categoryListResult = map.get("CategoryListResponse").getCategoryListResult();
      this.message = map.get("CategoryListResponse").getMessage();
      this.status = map.get("CategoryListResponse").getStatus();
      this.code = map.get("CategoryListResponse").getCode();
    }

    public CategoryListResult getCategoryListResult() {
        return categoryListResult;
    }

    public void setCategoryListResult(CategoryListResult categoryListResult) {
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