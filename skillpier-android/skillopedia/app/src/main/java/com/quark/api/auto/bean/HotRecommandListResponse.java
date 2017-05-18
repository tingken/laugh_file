package com.quark.api.auto.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-05-28 17:24:31
 *
 */
public class HotRecommandListResponse{
   //
   public HotRecommandListResult hotRecommandListResult;
   //
   public String message;
   //1-操作成功
   public int status;
   //200-正常返回，405-重新登陆
   public int code;
    public HotRecommandListResponse() {
    }
    public HotRecommandListResponse(String json) {
      Map<String, HotRecommandListResponse> map = JSON.parseObject(json, new TypeReference<Map<String, HotRecommandListResponse>>() {
      });
      this.hotRecommandListResult = map.get("HotRecommandListResponse").getHotRecommandListResult();
      this.message = map.get("HotRecommandListResponse").getMessage();
      this.status = map.get("HotRecommandListResponse").getStatus();
      this.code = map.get("HotRecommandListResponse").getCode();
    }

    public HotRecommandListResult getHotRecommandListResult() {
        return hotRecommandListResult;
    }

    public void setHotRecommandListResult(HotRecommandListResult hotRecommandListResult) {
        this.hotRecommandListResult = hotRecommandListResult;
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