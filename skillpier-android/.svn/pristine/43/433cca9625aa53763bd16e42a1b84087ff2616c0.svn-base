package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-02 14:02:22
 */
public class BookingScheduleListResponse {
    //时间段
    public List<ListScheduleBean> ScheduleBeans;
    //未选课程数量:刚下单未选课数=购买量
    public String under_select_course_num;
    //
    public int is_stop_course;
    public String message;
    //0-失败，1-操作成功
    public int status;
    //200-正常返回，405-重新登陆
    public int code;

    public BookingScheduleListResponse() {
    }

    public BookingScheduleListResponse(String json) {
        Map<String, BookingScheduleListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, BookingScheduleListResponse>>() {
        });
        this.ScheduleBeans = map.get("BookingScheduleListResponse").getScheduleBeans();
        this.under_select_course_num = map.get("BookingScheduleListResponse").getUnder_select_course_num();
        this.message = map.get("BookingScheduleListResponse").getMessage();
        this.status = map.get("BookingScheduleListResponse").getStatus();
        this.code = map.get("BookingScheduleListResponse").getCode();
        this.is_stop_course = map.get("BookingScheduleListResponse").getIs_stop_course();
    }

    public int getIs_stop_course() {
        return is_stop_course;
    }

    public void setIs_stop_course(int is_stop_course) {
        this.is_stop_course = is_stop_course;
    }

    public List<ListScheduleBean> getScheduleBeans() {
        return ScheduleBeans;
    }

    public void setScheduleBeans(List<ListScheduleBean> scheduleBeans) {
        ScheduleBeans = scheduleBeans;
    }

    public void setUnder_select_course_num(String under_select_course_num) {
        this.under_select_course_num = under_select_course_num;
    }

    public String getUnder_select_course_num() {
        return this.under_select_course_num;
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