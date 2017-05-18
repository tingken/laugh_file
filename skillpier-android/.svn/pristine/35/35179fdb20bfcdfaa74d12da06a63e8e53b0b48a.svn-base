package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-13 18:43:33
 */
public class CourseCertificationInfoResponse {
    //
    public String message;
    //0-失败，1-操作成功
    public int status;
    //200-正常返回，405-重新登陆
    public int code;
    CourseCertificationInfoResult courseCertificationInfoResult;

    public CourseCertificationInfoResponse() {
    }

    public CourseCertificationInfoResponse(String json) {
        Map<String, CourseCertificationInfoResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CourseCertificationInfoResponse>>() {
        });
        this.message = map.get("CourseCertificationInfoResponse").getMessage();
        this.status = map.get("CourseCertificationInfoResponse").getStatus();
        this.code = map.get("CourseCertificationInfoResponse").getCode();
        this.courseCertificationInfoResult = map.get("CourseCertificationInfoResponse").getCourseCertificationInfoResult();
    }

    public CourseCertificationInfoResult getCourseCertificationInfoResult() {
        return courseCertificationInfoResult;
    }

    public void setCourseCertificationInfoResult(CourseCertificationInfoResult courseCertificationInfoResult) {
        this.courseCertificationInfoResult = courseCertificationInfoResult;
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