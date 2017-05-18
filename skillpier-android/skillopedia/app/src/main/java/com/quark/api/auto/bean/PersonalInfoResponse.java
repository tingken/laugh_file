package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-04 17:59:30
 *
 */
public class PersonalInfoResponse{
   //
   public PersonalInfoResult personalInfoResult;
   //
   public String message;
   //1-操作成功，0-失败
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public PersonalInfoResponse() {
    }
    public PersonalInfoResponse(String json) {
      Map<String, PersonalInfoResponse> map = JSON.parseObject(json, new TypeReference<Map<String, PersonalInfoResponse>>() {
      });
      this.personalInfoResult = map.get("PersonalInfoResponse").getPersonalInfoResult();
      this.message = map.get("PersonalInfoResponse").getMessage();
      this.status = map.get("PersonalInfoResponse").getStatus();
      this.code = map.get("PersonalInfoResponse").getCode();
    }

    public PersonalInfoResult getPersonalInfoResult() {
        return personalInfoResult;
    }

    public void setPersonalInfoResult(PersonalInfoResult personalInfoResult) {
        this.personalInfoResult = personalInfoResult;
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