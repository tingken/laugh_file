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
public class ComfiyPayResponse{
   //付款Id
//   public String charge_log_id;
   public String orders_ids;
   //支付流水号
//   public String pay_id;
   //
   public String message;
   //-1-交易密码为空，1-支付成功,2-资金不足,3-账户异常锁定,4-交易密码不正确,5-任务异常锁定
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public ComfiyPayResponse() {
    }
    public ComfiyPayResponse(String json) {
      Map<String, ComfiyPayResponse> map = JSON.parseObject(json, new TypeReference<Map<String, ComfiyPayResponse>>() {
      });
      this.orders_ids = map.get("ComfiyPayResponse").getOrders_ids();
//      this.pay_id = map.get("ComfiyPayResponse").getPay_id();
      this.message = map.get("ComfiyPayResponse").getMessage();
      this.status = map.get("ComfiyPayResponse").getStatus();
      this.code = map.get("ComfiyPayResponse").getCode();
    }

    public String getOrders_ids() {
        return orders_ids;
    }

    public void setOrders_ids(String orders_ids) {
        this.orders_ids = orders_ids;
    }

//    public void setPay_id(String pay_id){
//     this.pay_id = pay_id;
//   }
//   public String getPay_id(){
//     return this.pay_id;
//   }
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