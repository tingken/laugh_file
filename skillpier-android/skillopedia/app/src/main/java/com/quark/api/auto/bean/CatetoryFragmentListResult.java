package com.quark.api.auto.bean;
import java.util.List;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-05-30 11:06:00
 *
 */
public class CatetoryFragmentListResult {
   //
   public List<ListCatetory> Catetorys;

    public List<ListCatetory> getCatetorys() {
        return Catetorys;
    }

    public void setCatetorys(List<ListCatetory> catetorys) {
        Catetorys = catetorys;
    }
}