package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-08-19 16:17:17
 */
public class IsFulfilTravelDistanceResponse {
    //
    public String message;
    //0-失败，1-操作成功，2-不存在
    public int status;
    //200-正常返回，405-重新登陆
    public int code;

    public IsFulfilTravelDistanceResponse() {
    }

    public IsFulfilTravelDistanceResponse(String json) {
        Map<String, IsFulfilTravelDistanceResponse> map = JSON.parseObject(json, new TypeReference<Map<String, IsFulfilTravelDistanceResponse>>() {
        });
        this.message = map.get("IsFulfilTravelDistanceResponse").getMessage();
        this.status = map.get("IsFulfilTravelDistanceResponse").getStatus();
        this.code = map.get("IsFulfilTravelDistanceResponse").getCode();
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