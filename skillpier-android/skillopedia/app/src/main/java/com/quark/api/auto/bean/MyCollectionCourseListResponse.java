package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-30 18:23:10
 *
 */
public class MyCollectionCourseListResponse{
   //page number
   public MyCollectionCourseListResult myCollectionCourseListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public MyCollectionCourseListResponse() {
    }
    public MyCollectionCourseListResponse(String json) {
      Map<String, MyCollectionCourseListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, MyCollectionCourseListResponse>>() {
      });
      this.myCollectionCourseListResult = map.get("MyCollectionCourseListResponse").getMyCollectionCourseListResult();
      this.message = map.get("MyCollectionCourseListResponse").getMessage();
      this.status = map.get("MyCollectionCourseListResponse").getStatus();
      this.code = map.get("MyCollectionCourseListResponse").getCode();
    }

    public MyCollectionCourseListResult getMyCollectionCourseListResult() {
        return myCollectionCourseListResult;
    }

    public void setMyCollectionCourseListResult(MyCollectionCourseListResult myCollectionCourseListResult) {
        this.myCollectionCourseListResult = myCollectionCourseListResult;
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