package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-04 10:45:29
 *
 */
public class GetCouponResponse{
   //
   public String message;
   //0-领取失败，1-操作成功,2-please Enter your coupon number,3-优惠券不存在，请检查,4-已被领取完,5-你已领取完，每个人最多能领取
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public GetCouponResponse() {
    }
    public GetCouponResponse(String json) {
      Map<String, GetCouponResponse> map = JSON.parseObject(json, new TypeReference<Map<String, GetCouponResponse>>() {
      });
      this.message = map.get("GetCouponResponse").getMessage();
      this.status = map.get("GetCouponResponse").getStatus();
      this.code = map.get("GetCouponResponse").getCode();
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