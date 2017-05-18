package com.quark.api.auto.bean;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-06 18:50:32
 */
public class RefundOrdersRequest {
    public String url = "/app/OrdersManage/refundOrders";
    public String method = "get";
    private String token;//token
    private String orders_id;//
    private String refund_success_money;//成功退款金额
    private String orders_schedule_ids;//多退款订单同时申请【11#22#1#23】，申请一个就传一个值
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

    public void setRefund_success_money(String refund_success_money) {
        this.refund_success_money = refund_success_money;
    }

    public String getRefund_success_money() {
        return this.refund_success_money;
    }

    public void setOrders_schedule_ids(String orders_schedule_ids) {
        this.orders_schedule_ids = orders_schedule_ids;
    }

    public String getOrders_schedule_ids() {
        return this.orders_schedule_ids;
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
