package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-08-09 15:38:51
 */
public class ThirdLoginResponse {
    //
//   public String message;
    //1-已关联，并登陆,2-uid获取失败{用户账号不存在或者密码错误},3-您的账号因异常
//   public int status;
    //200-正常返回，405-重新登陆
//   public int code;

    public String code;//":200,
    public String user_id;//":16,
    public String server_telephone;//":"15989899590",
    public String image_01;//":"1467272918274.jpg",
    public String agent_level;//":2,
    public String message;//":"恭喜，登陆成功",
    public String status;//":1,
    public String token;//":"8cebfb841093686fde98553ee2cf3595"

    public ThirdLoginResponse() {
    }

    public ThirdLoginResponse(String json) {
        Map<String, ThirdLoginResponse> map = JSON.parseObject(json, new TypeReference<Map<String, ThirdLoginResponse>>() {
        });
        this.message = map.get("ThirdLoginResponse").getMessage();
        this.status = map.get("ThirdLoginResponse").getStatus();
        this.code = map.get("ThirdLoginResponse").getCode();
        this.user_id = map.get("ThirdLoginResponse").getUser_id();
        this.server_telephone = map.get("ThirdLoginResponse").getServer_telephone();
        this.image_01 = map.get("ThirdLoginResponse").getImage_01();
        this.agent_level = map.get("ThirdLoginResponse").getAgent_level();
        this.token = map.get("ThirdLoginResponse").getToken();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getAgent_level() {
        return agent_level;
    }

    public void setAgent_level(String agent_level) {
        this.agent_level = agent_level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}