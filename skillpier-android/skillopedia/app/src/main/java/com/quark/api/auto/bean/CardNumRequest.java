package com.quark.api.auto.bean;
import java.util.ArrayList;
import java.util.List;
import com.quark.api.auto.bean.*;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-08-15 16:16:15
 *
 */
public class CardNumRequest{
   public String url = "/app/OrdersManage/cardNum";
   public String method = "get";
   private String token;//token
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

   public void setInvoke(String invoke){
     this.invoke = invoke;
   }
   public String getInvoke(){
     return this.invoke;
   }

}
