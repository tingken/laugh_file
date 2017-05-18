package com.quark.api.auto.bean;

import android.graphics.Bitmap;

import java.io.File;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-14 17:32:25
 */
public class ListCourseBanners {
    //banner图片
    public String image_01;
    //vedio_url
    public String vedio_url;
    Bitmap bitmap;
    File file1;
    boolean ismodify; //true  编辑了
    boolean isadd;//增加 按钮
    String sdpath;

    public String getSdpath() {
        return sdpath;
    }

    public void setSdpath(String sdpath) {
        this.sdpath = sdpath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public File getFile1() {
        return file1;
    }

    public void setFile1(File file1) {
        this.file1 = file1;
    }

    public boolean ismodify() {
        return ismodify;
    }

    public void setIsmodify(boolean ismodify) {
        this.ismodify = ismodify;
    }

    public boolean isadd() {
        return isadd;
    }

    public void setIsadd(boolean isadd) {
        this.isadd = isadd;
    }

    public void setImage_01(String image_01) {
        this.image_01 = image_01;
    }

    public String getImage_01() {
        return this.image_01;
    }

    public void setVedio_url(String vedio_url) {
        this.vedio_url = vedio_url;
    }

    public String getVedio_url() {
        return this.vedio_url;
    }
}