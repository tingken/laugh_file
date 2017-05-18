package com.quark.api.auto.bean;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-08-24 13:47:10
 */
public class ListGuideBanner {
    //封面
    public String cover;
    //pc端的banner
    public String big_cover;
    //banner
    public int index_banner_id;

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return this.cover;
    }

    public void setBig_cover(String big_cover) {
        this.big_cover = big_cover;
    }

    public String getBig_cover() {
        return this.big_cover;
    }

    public void setIndex_banner_id(int index_banner_id) {
        this.index_banner_id = index_banner_id;
    }

    public int getIndex_banner_id() {
        return this.index_banner_id;
    }
}