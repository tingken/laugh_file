package com.quark.api.auto.bean;
import java.util.ArrayList;
import java.util.List;
import com.quark.api.auto.bean.*;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-05-28 14:14:05
 *
 */
public class ListCategory01{
   //
   public int category_01_id;
   //封面
   public String image_01;
   //pc封面
   public String big_image_01;
   //描述
   public String category_describe;
   //名称
   public String category_01_name;

   public void setCategory_01_id(int category_01_id){
     this.category_01_id = category_01_id;
   }
   public int getCategory_01_id(){
     return this.category_01_id;
   }
   public void setImage_01(String image_01){
     this.image_01 = image_01;
   }
   public String getImage_01(){
     return this.image_01;
   }
   public void setBig_image_01(String big_image_01){
     this.big_image_01 = big_image_01;
   }
   public String getBig_image_01(){
     return this.big_image_01;
   }
   public void setCategory_describe(String category_describe){
     this.category_describe = category_describe;
   }
   public String getCategory_describe(){
     return this.category_describe;
   }
   public void setCategory_01_name(String category_01_name){
     this.category_01_name = category_01_name;
   }
   public String getCategory_01_name(){
     return this.category_01_name;
   }
}