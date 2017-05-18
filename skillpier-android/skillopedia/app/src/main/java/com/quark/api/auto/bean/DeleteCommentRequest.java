package com.quark.api.auto.bean;
import java.util.ArrayList;
import java.util.List;
import com.quark.api.auto.bean.*;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-07 15:34:21
 *
 */
public class DeleteCommentRequest{
   public String url = "/app/CommentManage/deleteComment";
   public String method = "get";
   private String token;//token
   private String delete_type;//1-用户，2-教练
   private String comment_id;//评价ID
   private String app_sign;//app的签名
   private String invoke;//Infer
   public void setUrl(String url){this.url = url;}
   public String getUrl(){return this.url;}
   public void setMethod(String method){this.method = method;}
   public String getMethod(){return this.method;}

   public void setToken(String token){
     this.token = token;
   }
   public String getToken(){
     return this.token;
   }

   public void setDelete_type(String delete_type){
     this.delete_type = delete_type;
   }
   public String getDelete_type(){
     return this.delete_type;
   }

   public void setComment_id(String comment_id){
     this.comment_id = comment_id;
   }
   public String getComment_id(){
     return this.comment_id;
   }

   public void setApp_sign(String app_sign){
     this.app_sign = app_sign;
   }
   public String getApp_sign(){
     return this.app_sign;
   }

   public void setInvoke(String invoke){
     this.invoke = invoke;
   }
   public String getInvoke(){
     return this.invoke;
   }

}
