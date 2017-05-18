package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-28 16:08:35
 */
public class LoginResponse {
    //頭像
    public String image_01;
    //用户token
    public String token;
    //id
    public String user_id;
    //email
    public String email;
    //昵称
    public String nickname;
    //
    public String message;
    //200-正常返回，405-重新登陆
    public int code;
    //1-登陆成功,2-手机没注册,3-登陆失败，密码不对,4-账号被冻结,5-未激活
    public int status;

    public String agent_level;


    public LoginResponse() {
    }

    public LoginResponse(String json) {
        Map<String, LoginResponse> map = JSON.parseObject(json, new TypeReference<Map<String, LoginResponse>>() {
        });
        this.image_01 = map.get("LoginResponse").getImage_01();
        this.token = map.get("LoginResponse").getToken();
        this.user_id = map.get("LoginResponse").getUser_id();
        this.email = map.get("LoginResponse").getEmail();
        this.nickname = map.get("LoginResponse").getNickname();
        this.message = map.get("LoginResponse").getMessage();
        this.code = map.get("LoginResponse").getCode();
        this.status = map.get("LoginResponse").getStatus();
        this.agent_level = map.get("LoginResponse").getAgent_level();
    }

    public String getAgent_level() {
        return agent_level;
    }

    public void setAgent_level(String agent_level) {
        this.agent_level = agent_level;
    }

    public void setImage_01(String image_01) {
        this.image_01 = image_01;
    }

    public String getImage_01() {
        return this.image_01;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }
}