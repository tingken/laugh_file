package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-10-09 15:21:32
 */
public class VerifyVisaPaymentsResponse {
    //
    public String message;
    //1-支付成功，2-支付失败
    public int status;
    //200-正常返回，405-重新登陆
    public int code;

    public VerifyVisaPaymentsResponse() {
    }

    public VerifyVisaPaymentsResponse(String json) {
        Map<String, VerifyVisaPaymentsResponse> map = JSON.parseObject(json, new TypeReference<Map<String, VerifyVisaPaymentsResponse>>() {
        });
        this.message = map.get("VerifyVisaPaymentsResponse").getMessage();
        this.status = map.get("VerifyVisaPaymentsResponse").getStatus();
        this.code = map.get("VerifyVisaPaymentsResponse").getCode();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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