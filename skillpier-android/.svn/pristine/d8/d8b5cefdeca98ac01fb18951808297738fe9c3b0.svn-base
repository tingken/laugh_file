package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-02 11:36:43
 *
 */
public class CourseListResponse{
   //page number
   public CourseListResult courseListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public CourseListResponse() {
    }
    public CourseListResponse(String json) {
      Map<String, CourseListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CourseListResponse>>() {
      });
      this.courseListResult = map.get("CourseListResponse").getCourseListResult();
      this.message = map.get("CourseListResponse").getMessage();
      this.status = map.get("CourseListResponse").getStatus();
      this.code = map.get("CourseListResponse").getCode();
    }

    public CourseListResult getCourseListResult() {
        return courseListResult;
    }

    public void setCourseListResult(CourseListResult courseListResult) {
        this.courseListResult = courseListResult;
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