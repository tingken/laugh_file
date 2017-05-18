package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-08-15 16:16:15
 */
public class CardNumResponse {
    //购物车数量
    public String total_card_number;
    //
    public String message;
    //1-操作成功
    public int status;
    //200-正常返回，405-重新登陆
    public int code;

    public CardNumResponse() {
    }

    public CardNumResponse(String json) {
        Map<String, CardNumResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CardNumResponse>>() {
        });
        this.total_card_number = map.get("CardNumResponse").getTotal_card_number();
        this.message = map.get("CardNumResponse").getMessage();
        this.status = map.get("CardNumResponse").getStatus();
        this.code = map.get("CardNumResponse").getCode();
    }

    public void setTotal_card_number(String total_card_number) {
        this.total_card_number = total_card_number;
    }

    public String getTotal_card_number() {
        return this.total_card_number;
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