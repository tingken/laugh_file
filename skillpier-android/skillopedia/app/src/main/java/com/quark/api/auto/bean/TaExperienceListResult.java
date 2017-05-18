package com.quark.api.auto.bean;

import java.util.List;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-06 17:15:52
 *
 */
public class TaExperienceListResult{
   //
//   public TaExperiences TaExperiences;
//
//   public void setTaExperiences(TaExperiences taexperiences){
//     this.TaExperiences = taexperiences;
//   }
//   public TaExperiences getTaExperiences(){
//     return this.TaExperiences;
//   }

    public List<ListExperience> TaExperiences;

    public List<ListExperience> getTaExperiences() {
        return TaExperiences;
    }

    public void setTaExperiences(List<ListExperience> taExperiences) {
        TaExperiences = taExperiences;
    }
}