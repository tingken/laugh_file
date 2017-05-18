package com.quark.api.auto.bean;

import java.io.Serializable;

/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-06-20 16:42:27
 */
public class AddCourseRequest implements Serializable {

    public String url = "/app/CourseManage/addCourse";
    public String method = "get";
    private String token;//token
    private String course_id;//
    private String title;//课程标题
    private String category_01_id;//第一大类Id
    private String category_01_name;//第一类名称
    private String category_02_id;//第二大类Id
    private String category_02_name;//第二类名称
    private String overview;//简介
    private String fileName;//拼接如：11.jpg#22.jpg#11.jpg#22.jpg【图片名称#图片名称】
    private String vedioURL;//拼接如：http://#http://#http://#http://【url名称#url名称】
    private String session_length;//课程时长60min  以60min为单位的
    private String session_rate;//课程费用
    private String teaching_age;//教育年限【age 5 and up】
    private String teaching_since;//开始教育时间【since Jun 2007】
    private String travel_to_session;//是否上门服务:1-是，0-否【如果选择no
    private String travel_to_session_distance;//上门服务距离【miles】【可以接受多大范围内的上门服务】
    private String travel_to_session_trafic_surcharge;//上门服务交通费
    private String city;//州【上课地址】
    private String area;//城市【上课地址】
    private String street;//街道【上课地址】
    private String address;//详细地址【上课地址】
    private String latitude;//维度【上课地址】
    private String longitude;//经度【上课地址】
    private String zipcode;//城市编码
    private String additional_partner;//额外的最多人【打折幅度】
    private String surcharge_for_each;//每个人的附加费用【打折幅度】
    private String discount_type;//打折类型：1-by_total
    private String discount_onetion_pur_money_01;//A:选择 By account的意思：一次性购买多少课程，打折多少B:选择 By total的意思：一次性购买多少钱，打折多少
    private String discount_price_01;//打折价格
    private String discount_onetion_pur_money_02;//如果选中by total:One-time Purchase $XX
    private String discount_price_02;//打折价格
    private String discount_onetion_pur_money_03;//
    private String discount_price_03;//打折价格
    private String start_time_slot;//{1、2、3、4、5、6、7、8、9、10、11、12、13、14、15、16、17、18、19、20、21、22、23、24}
    private String end_time_slot;//{1、2、3、4、5、6、7、8、9、10、11、12、13、14、15、16、17、18、19、20、21、22、23、24}
    private String weeks;//拼接：1#2#3，如1-代表星期日，2-星期一{1、2、3、4、5、6、7}
    private String app_sign;//app的签名
    private String invoke;//Infer
    private String user_images_01;//Infer

    private String hours;

    private String achievements;
    private String specialist;

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory_01_id() {
        return category_01_id;
    }

    public void setCategory_01_id(String category_01_id) {
        this.category_01_id = category_01_id;
    }

    public String getCategory_01_name() {
        return category_01_name;
    }

    public void setCategory_01_name(String category_01_name) {
        this.category_01_name = category_01_name;
    }

    public String getCategory_02_id() {
        return category_02_id;
    }

    public void setCategory_02_id(String category_02_id) {
        this.category_02_id = category_02_id;
    }

    public String getCategory_02_name() {
        return category_02_name;
    }

    public void setCategory_02_name(String category_02_name) {
        this.category_02_name = category_02_name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getVedioURL() {
        return vedioURL;
    }

    public void setVedioURL(String vedioURL) {
        this.vedioURL = vedioURL;
    }

    public String getSession_length() {
        return session_length;
    }

    public void setSession_length(String session_length) {
        this.session_length = session_length;
    }

    public String getSession_rate() {
        return session_rate;
    }

    public void setSession_rate(String session_rate) {
        this.session_rate = session_rate;
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

    public String getTravel_to_session() {
        return travel_to_session;
    }

    public void setTravel_to_session(String travel_to_session) {
        this.travel_to_session = travel_to_session;
    }

    public String getTravel_to_session_distance() {
        return travel_to_session_distance;
    }

    public void setTravel_to_session_distance(String travel_to_session_distance) {
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAdditional_partner() {
        return additional_partner;
    }

    public void setAdditional_partner(String additional_partner) {
        this.additional_partner = additional_partner;
    }

    public String getSurcharge_for_each() {
        return surcharge_for_each;
    }

    public void setSurcharge_for_each(String surcharge_for_each) {
        this.surcharge_for_each = surcharge_for_each;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
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

    public String getDiscount_onetion_pur_money_02() {
        return discount_onetion_pur_money_02;
    }

    public void setDiscount_onetion_pur_money_02(String discount_onetion_pur_money_02) {
        this.discount_onetion_pur_money_02 = discount_onetion_pur_money_02;
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

    public String getStart_time_slot() {
        return start_time_slot;
    }

    public void setStart_time_slot(String start_time_slot) {
        this.start_time_slot = start_time_slot;
    }

    public String getEnd_time_slot() {
        return end_time_slot;
    }

    public void setEnd_time_slot(String end_time_slot) {
        this.end_time_slot = end_time_slot;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getApp_sign() {
        return app_sign;
    }

    public void setApp_sign(String app_sign) {
        this.app_sign = app_sign;
    }

    public String getInvoke() {
        return invoke;
    }

    public void setInvoke(String invoke) {
        this.invoke = invoke;
    }

    public String getUser_images_01() {
        return user_images_01;
    }

    public void setUser_images_01(String user_images_01) {
        this.user_images_01 = user_images_01;
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
