package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-04 17:59:30
 *
 */
public class CoachOrdersListResponse{
   //0-无按钮，1-cancel取消课程(退款)  ,2-confirm确认课程时间  ,3-finish确认课程已经完成,4-delete删除已完成订单
   public CoachOrdersListResult coachOrdersListResult;
    public String message;
    //0-失败，1-操作成功
    public int status;
    //200-正常返回，405-重新登陆
    public int code;
    public CoachOrdersListResponse() {
    }
    public CoachOrdersListResponse(String json) {
      Map<String, CoachOrdersListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, CoachOrdersListResponse>>() {
      });
      this.coachOrdersListResult = map.get("CoachOrdersListResponse").getCoachOrdersListResult();
        this.message = map.get("CoachOrdersListResponse").getMessage();
        this.status = map.get("CoachOrdersListResponse").getStatus();
        this.code = map.get("CoachOrdersListResponse").getCode();
    }

    public CoachOrdersListResult getCoachOrdersListResult() {
        return coachOrdersListResult;
    }

    public void setCoachOrdersListResult(CoachOrdersListResult coachOrdersListResult) {
        this.coachOrdersListResult = coachOrdersListResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}