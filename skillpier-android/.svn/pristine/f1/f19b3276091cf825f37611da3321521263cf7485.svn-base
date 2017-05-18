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
public class PayMoneyResponse{
   //实际支付价格
   public String total_total_session_rate;
   //原价
   public String total_original_total_session_rate;
   //
   public String message;
   //0-失败，1-操作成功，2-不存在
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public PayMoneyResponse() {
    }
    public PayMoneyResponse(String json) {
      Map<String, PayMoneyResponse> map = JSON.parseObject(json, new TypeReference<Map<String, PayMoneyResponse>>() {
      });
      this.total_total_session_rate = map.get("PayMoneyResponse").getTotal_total_session_rate();
      this.total_original_total_session_rate = map.get("PayMoneyResponse").getTotal_original_total_session_rate();
      this.message = map.get("PayMoneyResponse").getMessage();
      this.status = map.get("PayMoneyResponse").getStatus();
      this.code = map.get("PayMoneyResponse").getCode();
    }

   public void setTotal_total_session_rate(String total_total_session_rate){
     this.total_total_session_rate = total_total_session_rate;
   }
   public String getTotal_total_session_rate(){
     return this.total_total_session_rate;
   }
   public void setTotal_original_total_session_rate(String total_original_total_session_rate){
     this.total_original_total_session_rate = total_original_total_session_rate;
   }
   public String getTotal_original_total_session_rate(){
     return this.total_original_total_session_rate;
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