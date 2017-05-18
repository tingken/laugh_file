package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-28 16:08:34
 *
 */
public class CatetoryCourseListResponse{
   //page number
   public CatetoryCourseListResult catetoryCourseListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public CatetoryCourseListResponse() {
    }
    public CatetoryCourseListResponse(String json) {
      Map<String, CatetoryCourseListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CatetoryCourseListResponse>>() {
      });
      this.catetoryCourseListResult = map.get("CatetoryCourseListResponse").getCatetoryCourseListResult();
      this.message = map.get("CatetoryCourseListResponse").getMessage();
      this.status = map.get("CatetoryCourseListResponse").getStatus();
      this.code = map.get("CatetoryCourseListResponse").getCode();
    }

    public CatetoryCourseListResult getCatetoryCourseListResult() {
        return catetoryCourseListResult;
    }

    public void setCatetoryCourseListResult(CatetoryCourseListResult catetoryCourseListResult) {
        this.catetoryCourseListResult = catetoryCourseListResult;
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