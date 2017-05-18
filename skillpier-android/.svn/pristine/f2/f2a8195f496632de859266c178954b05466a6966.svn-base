package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-06 17:15:52
 *
 */
public class TaExperienceListResponse{
   //
   public TaExperienceListResult taExperienceListResult;
   //标题
   //public TaExperiencesResult taExperiencesResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public TaExperienceListResponse() {
    }
    public TaExperienceListResponse(String json) {
      Map<String, TaExperienceListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, TaExperienceListResponse>>() {
      });
      this.taExperienceListResult = map.get("TaExperienceListResponse").getTaExperienceListResult();
      //this.taExperiencesResult = map.get("TaExperienceListResponse").getTaExperiencesResult();
      this.message = map.get("TaExperienceListResponse").getMessage();
      this.status = map.get("TaExperienceListResponse").getStatus();
      this.code = map.get("TaExperienceListResponse").getCode();
    }

    public TaExperienceListResult getTaExperienceListResult() {
        return taExperienceListResult;
    }

    public void setTaExperienceListResult(TaExperienceListResult taExperienceListResult) {
        this.taExperienceListResult = taExperienceListResult;
    }

//    public TaExperiencesResult getTaExperiencesResult() {
//        return taExperiencesResult;
//    }
//
//    public void setTaExperiencesResult(TaExperiencesResult taExperiencesResult) {
//        this.taExperiencesResult = taExperiencesResult;
//    }

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