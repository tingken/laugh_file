package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-08-04 16:13:30
 *
 */
public class CityListResponse{
   //
   public CityListResult cityListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public CityListResponse() {
    }
    public CityListResponse(String json) {
      Map<String, CityListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CityListResponse>>() {
      });
      this.cityListResult = map.get("CityListResponse").getCityListResult();
      this.message = map.get("CityListResponse").getMessage();
      this.status = map.get("CityListResponse").getStatus();
      this.code = map.get("CityListResponse").getCode();
    }

    public CityListResult getCityListResult() {
        return cityListResult;
    }

    public void setCityListResult(CityListResult cityListResult) {
        this.cityListResult = cityListResult;
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