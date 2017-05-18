package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-05-28 14:14:05
 */
public class Category01ListResponse {
    //
    public Category01ListResult category01ListResult;
    //
    public String message;
    //1-操作成功
    public int status;
    //200-正常返回，405-重新登陆
    public int code;

    public Category01ListResponse() {
    }

    public Category01ListResponse(String json) {
        Map<String, Category01ListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, Category01ListResponse>>() {
        });
        this.category01ListResult = map.get("Category01ListResponse").getCategory01ListResult();
        this.message = map.get("Category01ListResponse").getMessage();
        this.status = map.get("Category01ListResponse").getStatus();
        this.code = map.get("Category01ListResponse").getCode();
    }

    public Category01ListResult getCategory01ListResult() {
        return category01ListResult;
    }

    public void setCategory01ListResult(Category01ListResult category01ListResult) {
        this.category01ListResult = category01ListResult;
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