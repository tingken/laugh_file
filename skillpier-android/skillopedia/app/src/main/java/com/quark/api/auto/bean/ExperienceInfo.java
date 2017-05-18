package com.quark.api.auto.bean;

import java.util.List;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-28 16:08:35
 *
 */
public class ExperienceInfo{
   //
   public int experience_id;

    public String images;
   //标题
   public String title;
   //内容
   public String content;

    public String post_time;
   //证书图片


    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public List<ListFileNameBean> exBanners;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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

    public List<ListFileNameBean> getExBanners() {
        return exBanners;
    }

    public void setExBanners(List<ListFileNameBean> exBanners) {
        this.exBanners = exBanners;
    }
}