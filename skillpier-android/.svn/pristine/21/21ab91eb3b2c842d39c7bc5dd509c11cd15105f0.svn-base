package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-04 17:59:30
 */
public class CourseCouponListResponse {
    //
    public CourseCouponListResult courseCouponList2Result;
    //
    public String message;
    //1-操作成功
    public int status;
    //200-正常返回，405-重新登陆
    public int code;

    public CourseCouponListResponse() {
    }

    public CourseCouponListResponse(String json) {
        Map<String, CourseCouponListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CourseCouponListResponse>>() {
        });
        this.courseCouponList2Result = map.get("CourseCouponListResponse").getCourseCouponList2Result();
        this.message = map.get("CourseCouponListResponse").getMessage();
        this.status = map.get("CourseCouponListResponse").getStatus();
        this.code = map.get("CourseCouponListResponse").getCode();
    }

    public CourseCouponListResult getCourseCouponList2Result() {
        return courseCouponList2Result;
    }

    public void setCourseCouponList2Result(CourseCouponListResult courseCouponList2Result) {
        this.courseCouponList2Result = courseCouponList2Result;
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