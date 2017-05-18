package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-20 16:42:27
 */
public class newCoursePremissResponse {
    //课程名称
    public String course_id;
    //
    public String message;
    //1-操作成功，2-上傳無null
    public int status;
    //200-正常返回，405-重新登陆
    public int code;

    public newCoursePremissResponse() {
    }

    public newCoursePremissResponse(String json) {
        Map<String, newCoursePremissResponse> map = JSON.parseObject(json, new TypeReference<Map<String, newCoursePremissResponse>>() {
        });
        this.course_id = map.get("newCoursePremissResponse").getCourse_id();
        this.message = map.get("newCoursePremissResponse").getMessage();
        this.status = map.get("newCoursePremissResponse").getStatus();
        this.code = map.get("newCoursePremissResponse").getCode();
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_id() {
        return this.course_id;
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