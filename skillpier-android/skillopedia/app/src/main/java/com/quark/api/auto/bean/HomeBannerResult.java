package com.quark.api.auto.bean;
import java.util.List;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-05-28 17:24:31
 *
 */
public class HomeBannerResult{
   //封面
   public List<ListhomeBanner> homeBanner;

    public List<ListhomeBanner> getHomeBanner() {
        return homeBanner;
    }

    public void setHomeBanner(List<ListhomeBanner> homeBanner) {
        this.homeBanner = homeBanner;
    }
}