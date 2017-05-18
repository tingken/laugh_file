package com.quark.api.auto.bean;
import java.util.ArrayList;
import java.util.List;
import com.quark.api.auto.bean.*;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-08-04 16:13:30
 *
 */
public class CityListRequest{
   public String url = "/app/Home/cityList";
   public String method = "get";
   private String invoke;//Infer
   public void setUrl(String url){this.url = url;}
   public String getUrl(){return this.url;}
   public void setMethod(String method){this.method = method;}
   public String getMethod(){return this.method;}

   public void setInvoke(String invoke){
     this.invoke = invoke;
   }
   public String getInvoke(){
     return this.invoke;
   }

}
