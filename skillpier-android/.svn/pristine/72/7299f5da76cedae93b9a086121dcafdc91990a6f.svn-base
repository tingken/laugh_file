package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-02 16:17:22
 */
public class OrderCourseInfoResponse {
    //
    public CourseBuyDetail Course;
    //
    public String message;
    //0-失败，1-操作成功
    public int status;
    //200-正常返回，405-重新登陆
    public int code;

    public OrderCourseInfoResponse() {
    }

    public OrderCourseInfoResponse(String json) {
        Map<String, OrderCourseInfoResponse> map = JSON.parseObject(json, new TypeReference<Map<String, OrderCourseInfoResponse>>() {
        });
        this.Course = map.get("OrderCourseInfoResponse").getCourse();
        this.message = map.get("OrderCourseInfoResponse").getMessage();
        this.status = map.get("OrderCourseInfoResponse").getStatus();
        this.code = map.get("OrderCourseInfoResponse").getCode();
    }

    public CourseBuyDetail getCourse() {
        return Course;
    }

    public void setCourse(CourseBuyDetail course) {
        Course = course;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}