package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-08-24 13:47:10
 */
public class GuideBannerResponse {
    //封面
    public List<ListGuideBanner> GuideBanners;
    //
    public String message;
    //1-操作成功
    public int status;
    //200-正常返回，405-重新登陆
    public int code;

    public GuideBannerResponse() {
    }

    public GuideBannerResponse(String json) {
        Map<String, GuideBannerResponse> map = JSON.parseObject(json, new TypeReference<Map<String, GuideBannerResponse>>() {
        });
        this.GuideBanners = map.get("GuideBannerResponse").getGuideBanners();
        this.message = map.get("GuideBannerResponse").getMessage();
        this.status = map.get("GuideBannerResponse").getStatus();
        this.code = map.get("GuideBannerResponse").getCode();
    }

    public List<ListGuideBanner> getGuideBanners() {
        return GuideBanners;
    }

    public void setGuideBanners(List<ListGuideBanner> guideBanners) {
        GuideBanners = guideBanners;
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