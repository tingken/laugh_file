package com.quark.api.auto.bean;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-04 17:59:30
 */
public class CourseCouponList2Request {
    public String url = "/app/OrdersManage/courseCouponList2";
    public String method = "get";
    private String token;//token
    private String category_01_id;//第一大类Id
    private String category_02_id;//第二大类Id
    private String user_id;//商家ID：发布者
    private String course_id;//课程ID：指定的课程
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

    public void setCategory_01_id(String category_01_id) {
        this.category_01_id = category_01_id;
    }

    public String getCategory_01_id() {
        return this.category_01_id;
    }

    public void setCategory_02_id(String category_02_id) {
        this.category_02_id = category_02_id;
    }

    public String getCategory_02_id() {
        return this.category_02_id;
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

    public void setInvoke(String invoke) {
        this.invoke = invoke;
    }

    public String getInvoke() {
        return this.invoke;
    }

}
