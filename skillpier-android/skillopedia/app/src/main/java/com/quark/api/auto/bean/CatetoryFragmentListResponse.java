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
public class CatetoryFragmentListResponse {
   //
   public CatetoryFragmentListResult catetoryListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public CatetoryFragmentListResponse() {
    }
    public CatetoryFragmentListResponse(String json) {
      Map<String, CatetoryFragmentListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CatetoryFragmentListResponse>>() {
      });
      this.catetoryListResult = map.get("CatetoryListResponse").getCatetoryListResult();
      this.message = map.get("CatetoryListResponse").getMessage();
      this.status = map.get("CatetoryListResponse").getStatus();
      this.code = map.get("CatetoryListResponse").getCode();
    }

    public CatetoryFragmentListResult getCatetoryListResult() {
        return catetoryListResult;
    }

    public void setCatetoryListResult(CatetoryFragmentListResult catetoryListResult) {
        this.catetoryListResult = catetoryListResult;
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