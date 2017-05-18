package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-05-30 11:06:00
 */
public class CourseInfoResponse {
    //
    public CourseInfoResult courseInfoResult;
    public String message;
    //0-失败，1-操作成功
    public int status;
    //200-正常返回，405-重新登陆
    public int code;

    public CourseInfoResponse() {
    }

    public CourseInfoResponse(String json) {
        Map<String, CourseInfoResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CourseInfoResponse>>() {
        });
        this.courseInfoResult = map.get("CourseInfoResponse").getCourseInfoResult();
        this.message = map.get("CourseInfoResponse").getMessage();
        this.status = map.get("CourseInfoResponse").getStatus();
        this.code = map.get("CourseInfoResponse").getCode();
    }

    public CourseInfoResult getCourseInfoResult() {
        return courseInfoResult;
    }

    public void setCourseInfoResult(CourseInfoResult courseInfoResult) {
        this.courseInfoResult = courseInfoResult;
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