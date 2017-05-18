package com.quark.api.auto.bean;
/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-28 16:08:34
 *
 */
public class ListExperience{
   //
   public int experience_id;
   //标题
   public String title;
   //内容
   public String content;
   //
   public String post_time;
   //经验图片封面
   public String image_01;

    public String images;

    public int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setExperience_id(int experience_id){
     this.experience_id = experience_id;
   }
   public int getExperience_id(){
     return this.experience_id;
   }
   public void setTitle(String title){
     this.title = title;
   }
   public String getTitle(){
     return this.title;
   }
   public void setContent(String content){
     this.content = content;
   }
   public String getContent(){
     return this.content;
   }
   public void setPost_time(String post_time){
     this.post_time = post_time;
   }
   public String getPost_time(){
     return this.post_time;
   }
   public void setImage_01(String image_01){
     this.image_01 = image_01;
   }
   public String getImage_01(){
     return this.image_01;
   }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}