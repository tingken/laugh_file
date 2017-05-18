package com.quark.api.auto.bean;
/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-04 17:59:30
 *
 */
public class PersonalInfoResult{
   //
   public UserInfo UserInfo;

   public void setUserInfo(UserInfo userinfo){
     this.UserInfo = userinfo;
   }
   public UserInfo getUserInfo(){
     return this.UserInfo;
   }
}