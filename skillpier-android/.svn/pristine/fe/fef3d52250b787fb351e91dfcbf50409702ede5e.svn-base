package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-28 16:08:35
 */
public class ScheduleListResponse {
    //课程id
    public List<ListScheduleBean> ScheduleBeans;
    //今后暂停课程 0-否，1-是
    public String is_stop_course;
    //全天没有空  0-否，1-是
    public String is_busy_24;
    //
    public String message;
    //0-失败，1-操作成功
    public int status;
    //200-正常返回，405-重新登陆
    public int code;

    public ScheduleListResponse() {
    }

    public ScheduleListResponse(String json) {
        Map<String, ScheduleListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, ScheduleListResponse>>() {
        });
        this.ScheduleBeans = map.get("ScheduleListResponse").getScheduleBeans();
        this.is_stop_course = map.get("ScheduleListResponse").getIs_stop_course();
        this.is_busy_24 = map.get("ScheduleListResponse").getIs_busy_24();
        this.message = map.get("ScheduleListResponse").getMessage();
        this.status = map.get("ScheduleListResponse").getStatus();
        this.code = map.get("ScheduleListResponse").getCode();
    }

    public List<ListScheduleBean> getScheduleBeans() {
        return ScheduleBeans;
    }

    public void setScheduleBeans(List<ListScheduleBean> scheduleBeans) {
        ScheduleBeans = scheduleBeans;
    }

    public void setIs_stop_course(String is_stop_course) {
        this.is_stop_course = is_stop_course;
    }

    public String getIs_stop_course() {
        return this.is_stop_course;
    }

    public void setIs_busy_24(String is_busy_24) {
        this.is_busy_24 = is_busy_24;
    }

    public String getIs_busy_24() {
        return this.is_busy_24;
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