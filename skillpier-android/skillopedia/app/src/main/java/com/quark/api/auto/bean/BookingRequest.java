package com.quark.api.auto.bean;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-06 10:16:17
 */
public class BookingRequest {
    public String url = "/app/TimeOrdersManage/booking";
    public String method = "get";
    private String token;//token
    private String orders_id;//订单Id
    private String course_id;//课程ID
    private String choice_currentdates;//课程日期如：2015-06-11A1A2#2015-06-11A22A23#2015-06-14A12A13
    private String under_select_course_num;//未选课程数量
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

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setOrders_id(String orders_id) {
        this.orders_id = orders_id;
    }

    public String getOrders_id() {
        return this.orders_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_id() {
        return this.course_id;
    }

    public void setChoice_currentdates(String choice_currentdates) {
        this.choice_currentdates = choice_currentdates;
    }

    public String getChoice_currentdates() {
        return this.choice_currentdates;
    }

    public void setUnder_select_course_num(String under_select_course_num) {
        this.under_select_course_num = under_select_course_num;
    }

    public String getUnder_select_course_num() {
        return this.under_select_course_num;
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
