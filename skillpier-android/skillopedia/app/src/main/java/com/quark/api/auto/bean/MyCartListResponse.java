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
public class MyCartListResponse{
   //是否官方认证：0-否，1-是
   public MyCartListResult myCartListResult;
   //
    String has_invalid_car_num;
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public MyCartListResponse() {
    }
    public MyCartListResponse(String json) {
      Map<String, MyCartListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, MyCartListResponse>>() {
      });
      this.myCartListResult = map.get("MyCartListResponse").getMyCartListResult();
      this.message = map.get("MyCartListResponse").getMessage();
      this.status = map.get("MyCartListResponse").getStatus();
      this.code = map.get("MyCartListResponse").getCode();
        this.has_invalid_car_num = map.get("MyCartListResponse").getHas_invalid_car_num();
    }

    public String getHas_invalid_car_num() {
        return has_invalid_car_num;
    }

    public void setHas_invalid_car_num(String has_invalid_car_num) {
        this.has_invalid_car_num = has_invalid_car_num;
    }

    public MyCartListResult getMyCartListResult() {
        return myCartListResult;
    }

    public void setMyCartListResult(MyCartListResult myCartListResult) {
        this.myCartListResult = myCartListResult;
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