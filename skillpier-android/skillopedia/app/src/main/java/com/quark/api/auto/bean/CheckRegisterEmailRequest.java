package com.quark.api.auto.bean;
import java.util.ArrayList;
import java.util.List;
import com.quark.api.auto.bean.*;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-05-27 10:26:42
 *
 */
public class CheckRegisterEmailRequest{
   public String url = "/app/UserCenter/checkRegisterEmail";
   public String method = "get";
   private String email;//邮件登陆
   private String invoke;//Infer
   public void setUrl(String url){this.url = url;}
   public String getUrl(){return this.url;}
   public void setMethod(String method){this.method = method;}
   public String getMethod(){return this.method;}

   public void setEmail(String email){
     this.email = email;
   }
   public String getEmail(){
     return this.email;
   }

   public void setInvoke(String invoke){
     this.invoke = invoke;
   }
   public String getInvoke(){
     return this.invoke;
   }

}
