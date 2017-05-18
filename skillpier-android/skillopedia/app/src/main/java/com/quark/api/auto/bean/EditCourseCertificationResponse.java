package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-20 16:42:27
 *
 */
public class EditCourseCertificationResponse{
   //
   public String message;
   //0-失败，1-操作成功，2-请上传证书图片
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public EditCourseCertificationResponse() {
    }
    public EditCourseCertificationResponse(String json) {
      Map<String, EditCourseCertificationResponse> map = JSON.parseObject(json, new TypeReference<Map<String, EditCourseCertificationResponse>>() {
      });
      this.message = map.get("EditCourseCertificationResponse").getMessage();
      this.status = map.get("EditCourseCertificationResponse").getStatus();
      this.code = map.get("EditCourseCertificationResponse").getCode();
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