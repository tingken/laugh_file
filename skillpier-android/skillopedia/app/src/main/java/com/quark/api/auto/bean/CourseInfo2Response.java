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
public class CourseInfo2Response{
   //
   public CourseInfo2Result courseInfo2Result;
   //
   public String message;
   //0-失败，1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public CourseInfo2Response() {
    }
    public CourseInfo2Response(String json) {
      Map<String, CourseInfo2Response> map = JSON.parseObject(json, new TypeReference<Map<String, CourseInfo2Response>>() {
      });
      this.courseInfo2Result = map.get("CourseInfo2Response").getCourseInfo2Result();
      this.message = map.get("CourseInfo2Response").getMessage();
      this.status = map.get("CourseInfo2Response").getStatus();
      this.code = map.get("CourseInfo2Response").getCode();
    }

    public CourseInfo2Result getCourseInfo2Result() {
        return courseInfo2Result;
    }

    public void setCourseInfo2Result(CourseInfo2Result courseInfo2Result) {
        this.courseInfo2Result = courseInfo2Result;
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