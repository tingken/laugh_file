package com.quark.api.auto.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-28 16:08:34
 */
public class Course implements Serializable {
    //
    public int course_id;
    //发布者
    public String user_id;
    //课程标题
    public String title;
    //评论总分【每次评价更新，最高分5分】
    public String total_score;

    public String totalRow;
    //课程费用
    public String session_rate;
    //课程时长
    public String session_length;
    //第一大类Id
    public int category_01_id;
    //第二大类Id
    public int category_02_id;
    //第二类名称
    public String category_02_name;
    //是否上门服务:1-是，0-否【如果选择no
    public int travel_to_session;
    public String distance;  //（距离）、trafic surcharge（交通费）变灰，无法操作】 travel_to_session;
    //上门服务距离【miles】【可以接受多大范围内的上门服务】
    public int travel_to_session_distance;
    public String teaching_since;//教学以来
    public String teaching_age; //教龄
    public String Overview;       //简介
    public String achievements;  //新增 成果
    public String specialist;    //新增 特长
    //上门服务交通费
    public String travel_to_session_trafic_surcharge;
    //州【上课地址】
    public String city;
    //城市【上课地址】
    public String area;
    //街道【上课地址】
    public String street;
    //详细地址【上课地址】
    public String address;
    //维度【上课地址】
    public String latitude;
    //经度【上课地址】
    public String longitude;
    //额外的最多人【打折幅度】
    public int additional_partner;
    //每个人的附加费用【打折幅度】
    public String surcharge_for_each;
    //打折类型：1-by_total
    // public by_account discount_type;
    //A:选择 By account的意思：一次性购买多少课程，打折多少B:选择 By total的意思：一次性购买多少钱，打折多少
    public String discount_onetion_pur_money_01;
    //打折价格
    public String discount_price_01;
    //如果选中by total:One-time Purchase $XX
    //public discount price $XX如果选中by account:One-time Purchase XX discount_onetion_pur_money_02;
    //打折价格
    public String discount_price_02;
    //
    public String discount_onetion_pur_money_03;
    //打折价格
    public String discount_price_03;
    //教练是否官方认证：0-否，1-是
    public String course_is_official;
    //教练用户头像
    public String user_image_01;
    //教练昵称
    public String nickname;

    public String course_nickname;
    //教练电话
    public String course_telephone;

    public String collection_id;//收藏id

    public String videos;   //视频

    public String coach_name;

    public String buy_nickname;

    public String buy_image_01;

    public String is_collection;
    public String course_email;
    public String buy_email;
    public String coach_image;

    private List<ListCourseBanner> CourseBanners;
    private List<CourseVedios> CourseVedios;
    private List<ListCourseCertification> CourseCertifications;
    //未选择时间的课程数
    public String hasnone_booking_course;
    public String buy_telephone;
    //评论总数
    public String total_coment_num;
    public String course_image_01;

    public String freeCourseDay;

    public String getFreeCourseDay() {
        return freeCourseDay;
    }

    public void setFreeCourseDay(String freeCourseDay) {
        this.freeCourseDay = freeCourseDay;
    }

    public String getCoach_image() {
        return coach_image;
    }

    public void setCoach_image(String coach_image) {
        this.coach_image = coach_image;
    }

    public String getBuy_email() {
        return buy_email;
    }

    public void setBuy_email(String buy_email) {
        this.buy_email = buy_email;
    }



    public String getCourse_email() {
        return course_email;
    }

    public void setCourse_email(String course_email) {
        this.course_email = course_email;
    }

    public int getTravel_to_session() {
        return travel_to_session;
    }

    public void setTravel_to_session(int travel_to_session) {
        this.travel_to_session = travel_to_session;
    }

    public String getTotal_coment_num() {
        return total_coment_num;
    }

    public void setTotal_coment_num(String total_coment_num) {
        this.total_coment_num = total_coment_num;
    }

    public String getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(String totalRow) {
        this.totalRow = totalRow;
    }

    public String getIs_collection() {
        return is_collection;
    }

    public void setIs_collection(String is_collection) {
        this.is_collection = is_collection;
    }


    public String getHasnone_booking_course() {
        return hasnone_booking_course;
    }

    public void setHasnone_booking_course(String hasnone_booking_course) {
        this.hasnone_booking_course = hasnone_booking_course;
    }

    public String getBuy_telephone() {
        return buy_telephone;
    }

    public void setBuy_telephone(String buy_telephone) {
        this.buy_telephone = buy_telephone;
    }

    public String getBuy_nickname() {
        return buy_nickname;
    }

    public void setBuy_nickname(String buy_nickname) {
        this.buy_nickname = buy_nickname;
    }

    public String getBuy_image_01() {
        return buy_image_01;
    }

    public void setBuy_image_01(String buy_image_01) {
        this.buy_image_01 = buy_image_01;
    }

    public String getCourse_nickname() {
        return course_nickname;
    }

    public String getCourse_image_01() {
        return course_image_01;
    }

    public void setCourse_image_01(String course_image_01) {
        this.course_image_01 = course_image_01;
    }

    public void setCourse_nickname(String course_nickname) {
        this.course_nickname = course_nickname;
    }
    public List<ListCourseBanner> getCourseBanners() {
        return CourseBanners;
    }

    public void setCourseBanners(List<ListCourseBanner> courseBanners) {
        CourseBanners = courseBanners;
    }

    public List<com.quark.api.auto.bean.CourseVedios> getCourseVedios() {
        return CourseVedios;
    }

    public void setCourseVedios(List<com.quark.api.auto.bean.CourseVedios> courseVedios) {
        CourseVedios = courseVedios;
    }

    public List<ListCourseCertification> getCourseCertifications() {
        return CourseCertifications;
    }

    public void setCourseCertifications(List<ListCourseCertification> courseCertifications) {
        CourseCertifications = courseCertifications;
    }

    public String getCoach_name() {
        return coach_name;
    }

    public void setCoach_name(String coach_name) {
        this.coach_name = coach_name;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public String getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(String collection_id) {
        this.collection_id = collection_id;
    }

    public String getTeaching_age() {
        return teaching_age;
    }

    public void setTeaching_age(String teaching_age) {
        this.teaching_age = teaching_age;
    }

    public String getTeaching_since() {
        return teaching_since;
    }

    public void setTeaching_since(String teaching_since) {
        this.teaching_since = teaching_since;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSession_length() {
        return session_length;
    }

    public void setSession_length(String session_length) {
        this.session_length = session_length;
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

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

    public String getTotal_score() {
        return this.total_score;
    }

    public void setSession_rate(String session_rate) {
        this.session_rate = session_rate;
    }

    public String getSession_rate() {
        return this.session_rate;
    }

    public void setCategory_01_id(int category_01_id) {
        this.category_01_id = category_01_id;
    }

    public int getCategory_01_id() {
        return this.category_01_id;
    }

    public void setCategory_02_id(int category_02_id) {
        this.category_02_id = category_02_id;
    }

    public int getCategory_02_id() {
        return this.category_02_id;
    }

    public void setCategory_02_name(String category_02_name) {
        this.category_02_name = category_02_name;
    }

    public String getCategory_02_name() {
        return category_02_name;
    }

    public int getTravel_to_session_distance() {
        return travel_to_session_distance;
    }

    public void setTravel_to_session_distance(int travel_to_session_distance) {
        this.travel_to_session_distance = travel_to_session_distance;
    }

    public String getTravel_to_session_trafic_surcharge() {
        return travel_to_session_trafic_surcharge;
    }

    public void setTravel_to_session_trafic_surcharge(String travel_to_session_trafic_surcharge) {
        this.travel_to_session_trafic_surcharge = travel_to_session_trafic_surcharge;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getAdditional_partner() {
        return additional_partner;
    }

    public void setAdditional_partner(int additional_partner) {
        this.additional_partner = additional_partner;
    }

    public String getSurcharge_for_each() {
        return surcharge_for_each;
    }

    public void setSurcharge_for_each(String surcharge_for_each) {
        this.surcharge_for_each = surcharge_for_each;
    }

    public String getDiscount_onetion_pur_money_01() {
        return discount_onetion_pur_money_01;
    }

    public void setDiscount_onetion_pur_money_01(String discount_onetion_pur_money_01) {
        this.discount_onetion_pur_money_01 = discount_onetion_pur_money_01;
    }

    public String getDiscount_price_01() {
        return discount_price_01;
    }

    public void setDiscount_price_01(String discount_price_01) {
        this.discount_price_01 = discount_price_01;
    }

    public String getDiscount_price_02() {
        return discount_price_02;
    }

    public void setDiscount_price_02(String discount_price_02) {
        this.discount_price_02 = discount_price_02;
    }

    public String getDiscount_onetion_pur_money_03() {
        return discount_onetion_pur_money_03;
    }

    public void setDiscount_onetion_pur_money_03(String discount_onetion_pur_money_03) {
        this.discount_onetion_pur_money_03 = discount_onetion_pur_money_03;
    }

    public String getDiscount_price_03() {
        return discount_price_03;
    }

    public void setDiscount_price_03(String discount_price_03) {
        this.discount_price_03 = discount_price_03;
    }

    public String getCourse_is_official() {
        return course_is_official;
    }

    public void setCourse_is_official(String course_is_official) {
        this.course_is_official = course_is_official;
    }

    public String getUser_image_01() {
        return user_image_01;
    }

    public void setUser_image_01(String user_image_01) {
        this.user_image_01 = user_image_01;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCourse_telephone() {
        return course_telephone;
    }

    public void setCourse_telephone(String course_telephone) {
        this.course_telephone = course_telephone;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

}