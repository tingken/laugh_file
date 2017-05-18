package com.quark.api.auto.bean;
import java.util.List;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-04 18:15:52
 *
 */
public class CategoryListResult{
   //
   public List<Categorys> Categorys;

    public List<com.quark.api.auto.bean.Categorys> getCategorys() {
        return Categorys;
    }

    public void setCategorys(List<com.quark.api.auto.bean.Categorys> categorys) {
        Categorys = categorys;
    }
}