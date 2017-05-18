package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-02 17:44:37
 *
 */
public class MyCouponListResponse{
   //page number
   public MyCouponListResult myCouponListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public MyCouponListResponse() {
    }
    public MyCouponListResponse(String json) {
      Map<String, MyCouponListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, MyCouponListResponse>>() {
      });
      this.myCouponListResult = map.get("MyCouponListResponse").getMyCouponListResult();
      this.message = map.get("MyCouponListResponse").getMessage();
      this.status = map.get("MyCouponListResponse").getStatus();
      this.code = map.get("MyCouponListResponse").getCode();
    }

    public MyCouponListResult getMyCouponListResult() {
        return myCouponListResult;
    }

    public void setMyCouponListResult(MyCouponListResult myCouponListResult) {
        this.myCouponListResult = myCouponListResult;
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