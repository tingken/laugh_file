package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-08-08 15:31:51
 */
public class IsThirdLoginRelatedResponse {
    //
    public String message;
    //1-已关联，并登陆,2-uid获取失败,3-您的账号因异常，4-未关联
    public int status;
    //200-正常返回，405-重新登陆
    public int code;
    public String server_telephone;//":"15989899590",
    public String image_01;//":"",
    public String token;//":""

    public String user_id;
    public String agent_level;

    public IsThirdLoginRelatedResponse() {
    }

    public IsThirdLoginRelatedResponse(String json) {
        Map<String, IsThirdLoginRelatedResponse> map = JSON.parseObject(json, new TypeReference<Map<String, IsThirdLoginRelatedResponse>>() {
        });
        this.message = map.get("IsThirdLoginRelatedResponse").getMessage();
        this.status = map.get("IsThirdLoginRelatedResponse").getStatus();
        this.code = map.get("IsThirdLoginRelatedResponse").getCode();
        this.server_telephone = map.get("IsThirdLoginRelatedResponse").getServer_telephone();
        this.image_01 = map.get("IsThirdLoginRelatedResponse").getImage_01();
        this.token = map.get("IsThirdLoginRelatedResponse").getToken();
        this.user_id = map.get("IsThirdLoginRelatedResponse").getUser_id();
        this.agent_level = map.get("IsThirdLoginRelatedResponse").getAgent_level();
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAgent_level() {
        return agent_level;
    }

    public void setAgent_level(String agent_level) {
        this.agent_level = agent_level;
    }

    public String getServer_telephone() {
        return server_telephone;
    }

    public void setServer_telephone(String server_telephone) {
        this.server_telephone = server_telephone;
    }

    public String getImage_01() {
        return image_01;
    }

    public void setImage_01(String image_01) {
        this.image_01 = image_01;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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