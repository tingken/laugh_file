package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-14 13:59:07
 *
 */
public class AddCourseCertificationResponse{
   //ID
   public String course_certification_id;
   //
   public String message;
   //0-失败，1-操作成功，2-请上传证书图片
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public AddCourseCertificationResponse() {
    }
    public AddCourseCertificationResponse(String json) {
      Map<String, AddCourseCertificationResponse> map = JSON.parseObject(json, new TypeReference<Map<String, AddCourseCertificationResponse>>() {
      });
      this.course_certification_id = map.get("AddCourseCertificationResponse").getCourse_certification_id();
      this.message = map.get("AddCourseCertificationResponse").getMessage();
      this.status = map.get("AddCourseCertificationResponse").getStatus();
      this.code = map.get("AddCourseCertificationResponse").getCode();
    }

    public String getCourse_certification_id() {
        return course_certification_id;
    }

    public void setCourse_certification_id(String course_certification_id) {
        this.course_certification_id = course_certification_id;
    }

    public void setMessage(String message){
     this.message = message;
   }
   public String getMessage(){
     return this.message;
   }
   public void setStatus(int status){
     this.status = status;
   }
   public int getStatus(){
     return this.status;
   }
   public void setCode(int code){
     this.code = code;
   }
   public int getCode(){
     return this.code;
   }
}