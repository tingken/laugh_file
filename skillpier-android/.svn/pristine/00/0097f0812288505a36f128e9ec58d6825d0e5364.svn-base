package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-04 17:16:18
 *
 */
public class MyCouponInfoResponse{
   //
   public MyCoupon MyCoupon;
   //
   public String message;
   //0-失败，1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public MyCouponInfoResponse() {
    }
    public MyCouponInfoResponse(String json) {
      Map<String, MyCouponInfoResponse> map = JSON.parseObject(json, new TypeReference<Map<String, MyCouponInfoResponse>>() {
      });
      this.MyCoupon = map.get("MyCouponInfoResponse").getMyCoupon();
      this.message = map.get("MyCouponInfoResponse").getMessage();
      this.status = map.get("MyCouponInfoResponse").getStatus();
      this.code = map.get("MyCouponInfoResponse").getCode();
    }

    public com.quark.api.auto.bean.MyCoupon getMyCoupon() {
        return MyCoupon;
    }

    public void setMyCoupon(com.quark.api.auto.bean.MyCoupon myCoupon) {
        MyCoupon = myCoupon;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}