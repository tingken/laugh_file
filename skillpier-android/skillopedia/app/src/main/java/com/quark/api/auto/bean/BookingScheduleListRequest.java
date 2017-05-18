package com.quark.api.auto.bean;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-02 14:02:22
 */
public class BookingScheduleListRequest {
    public String url = "/app/TimeOrdersManage/bookingScheduleList";
    public String method = "get";
    private String user_id;//购买者
    private String course_id;//课程id
    private String orders_id;//
    private String choice_currentdate;//选择日期【2016-06-04】
    private String app_sign;//app的签名
    private String invoke;//Infer

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return this.method;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_id() {
        return this.course_id;
    }

    public String getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(String orders_id) {
        this.orders_id = orders_id;
    }

    public void setChoice_currentdate(String choice_currentdate) {
        this.choice_currentdate = choice_currentdate;
    }

    public String getChoice_currentdate() {
        return this.choice_currentdate;
    }

    public void setApp_sign(String app_sign) {
        this.app_sign = app_sign;
    }

    public String getApp_sign() {
        return this.app_sign;
    }

    public void setInvoke(String invoke) {
        this.invoke = invoke;
    }

    public String getInvoke() {
        return this.invoke;
    }

}
