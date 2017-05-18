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
public class AddCollectionCourseResponse{
   //收藏ID
   public String collection_id;
   //
   public String message;
   //0-失败，1-操作成功，2-不存在
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public AddCollectionCourseResponse() {
    }
    public AddCollectionCourseResponse(String json) {
      Map<String, AddCollectionCourseResponse> map = JSON.parseObject(json, new TypeReference<Map<String, AddCollectionCourseResponse>>() {
      });
      this.collection_id = map.get("AddCollectionCourseResponse").getCollection_id();
      this.message = map.get("AddCollectionCourseResponse").getMessage();
      this.status = map.get("AddCollectionCourseResponse").getStatus();
      this.code = map.get("AddCollectionCourseResponse").getCode();
    }

   public void setCollection_id(String collection_id){
     this.collection_id = collection_id;
   }
   public String getCollection_id(){
     return this.collection_id;
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