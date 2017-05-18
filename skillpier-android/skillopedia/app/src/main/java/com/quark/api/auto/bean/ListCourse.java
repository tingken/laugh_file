package com.quark.api.auto.bean;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-28 16:08:34
 */
public class ListCourse {
    //是否官方认证：0-否，1-是
    public String is_official;
    //教练名称
    public String coach_name;
    //教练图片
    public String coach_image;
    //距离
    public String distance;
    //
    public int course_id;
    //发布者
    public String user_id;
    //课程标题
    public String title;
    //第二类名称
    public String category_02_name;

    //测试发现返回的是这个字段
    public String catetory_name;
    //课程费用
    public String session_rate;
    //评论总分【每次评价更新，最高分5分】
    public String total_score;
    //维度【上课地址】
    public String latitude;
    //经度【上课地址】
    public String longitude;
    //州【上课地址】
    public String city;
    //城市【上课地址】
    public String area;
    //街道【上课地址】
    public String street;
    //详细地址【上课地址】
    public String address;
    public String user_images_01;

    public String total_coment_num;//评论总数

    public String getTotal_coment_num() {
        return total_coment_num;
    }

    public void setTotal_coment_num(String total_coment_num) {
        this.total_coment_num = total_coment_num;
    }

    public String getUser_images_01() {
        return user_images_01;
    }

    public void setUser_images_01(String user_images_01) {
        this.user_images_01 = user_images_01;
    }

    public String getCatetory_name() {
        return catetory_name;
    }

    public void setCatetory_name(String catetory_name) {
        this.catetory_name = catetory_name;
    }

    public void setIs_official(String is_official) {
        this.is_official = is_official;
    }

    public String getIs_official() {
        return this.is_official;
    }

    public void setCoach_name(String coach_name) {
        this.coach_name = coach_name;
    }

    public String getCoach_name() {
        return this.coach_name;
    }

    public void setCoach_image(String coach_image) {
        this.coach_image = coach_image;
    }

    public String getCoach_image() {
        return this.coach_image;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDistance() {
        return this.distance;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getCourse_id() {
        return this.course_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setCategory_02_name(String category_02_name) {
        this.category_02_name = category_02_name;
    }

    public String getCategory_02_name() {
        return this.category_02_name;
    }

    public void setSession_rate(String session_rate) {
        this.session_rate = session_rate;
    }

    public String getSession_rate() {
        return this.session_rate;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

    public String getTotal_score() {
        return this.total_score;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return this.area;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return this.street;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }
}