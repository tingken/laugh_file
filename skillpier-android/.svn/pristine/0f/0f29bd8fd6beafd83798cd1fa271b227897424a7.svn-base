package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-11-24 15:45:01
 */
public class HotRedcommandListNewResponse {
    //page number
    public HotRedcommandListNewResult hotRedcommandListNewResult;
    //
    public String message;
    //1-操作成功
    public int status;
    //200-正常返回，405-重新登陆
    public int code;

    public HotRedcommandListNewResponse() {
    }

    public HotRedcommandListNewResponse(String json) {
        Map<String, HotRedcommandListNewResponse> map = JSON.parseObject(json, new TypeReference<Map<String, HotRedcommandListNewResponse>>() {
        });
        this.hotRedcommandListNewResult = map.get("HotRedcommandListNewResponse").getHotRedcommandListNewResult();
        this.message = map.get("HotRedcommandListNewResponse").getMessage();
        this.status = map.get("HotRedcommandListNewResponse").getStatus();
        this.code = map.get("HotRedcommandListNewResponse").getCode();
    }

    public HotRedcommandListNewResult getHotRedcommandListNewResult() {
        return hotRedcommandListNewResult;
    }

    public void setHotRedcommandListNewResult(HotRedcommandListNewResult hotRedcommandListNewResult) {
        this.hotRedcommandListNewResult = hotRedcommandListNewResult;
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