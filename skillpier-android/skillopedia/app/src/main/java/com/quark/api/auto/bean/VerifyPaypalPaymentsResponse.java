package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-10-10 09:25:02
 *
 */
public class VerifyPaypalPaymentsResponse{
   //1-操作成功
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public VerifyPaypalPaymentsResponse() {
    }
    public VerifyPaypalPaymentsResponse(String json) {
      Map<String, VerifyPaypalPaymentsResponse> map = JSON.parseObject(json, new TypeReference<Map<String, VerifyPaypalPaymentsResponse>>() {
      });
      this.message = map.get("VerifyPaypalPaymentsResponse").getMessage();
      this.status = map.get("VerifyPaypalPaymentsResponse").getStatus();
      this.code = map.get("VerifyPaypalPaymentsResponse").getCode();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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