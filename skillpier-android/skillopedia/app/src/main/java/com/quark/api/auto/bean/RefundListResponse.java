package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-06 18:50:32
 */
public class RefundListResponse {
    RefundListResult refundListResult;
    //未选择时间的课程数
    public String hasnone_booking_course;
    //可以退款数量
    public String can_refund_num;
    //
    public String message;
    //1-操作成功
    public int status;
    //200-正常返回，405-重新登陆
    public int code;

    public RefundListResponse() {
    }

    public RefundListResponse(String json) {
        Map<String, RefundListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, RefundListResponse>>() {
        });
        this.hasnone_booking_course = map.get("RefundListResponse").getHasnone_booking_course();
        this.can_refund_num = map.get("RefundListResponse").getCan_refund_num();
        this.message = map.get("RefundListResponse").getMessage();
        this.status = map.get("RefundListResponse").getStatus();
        this.code = map.get("RefundListResponse").getCode();
        this.refundListResult = map.get("RefundListResponse").getRefundListResult();
    }

    public RefundListResult getRefundListResult() {
        return refundListResult;
    }

    public void setRefundListResult(RefundListResult refundListResult) {
        this.refundListResult = refundListResult;
    }

    public void setHasnone_booking_course(String hasnone_booking_course) {
        this.hasnone_booking_course = hasnone_booking_course;
    }

    public String getHasnone_booking_course() {
        return this.hasnone_booking_course;
    }

    public void setCan_refund_num(String can_refund_num) {
        this.can_refund_num = can_refund_num;
    }

    public String getCan_refund_num() {
        return this.can_refund_num;
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