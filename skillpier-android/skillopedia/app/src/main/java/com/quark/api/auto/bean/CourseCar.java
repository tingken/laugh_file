package com.quark.api.auto.bean;import java.io.Serializable;public class CourseCar implements Serializable{	private static final long serialVersionUID = 608379404L;	private String city;	private String address;	private String street;	private String title;	private String category_02_name;	private String catetory_name;	private String category_01_name;	private String session_rate;	private String area;	private String total_score;	private String course_id;	private String user_id;	public static long getSerialVersionUID() {		return serialVersionUID;	}	public String getCity() {		return city;	}	public void setCity(String city) {		this.city = city;	}	public String getAddress() {		return address;	}	public void setAddress(String address) {		this.address = address;	}	public String getStreet() {		return street;	}	public void setStreet(String street) {		this.street = street;	}	public String getTitle() {		return title;	}	public void setTitle(String title) {		this.title = title;	}	public String getCategory_02_name() {		return category_02_name;	}	public void setCategory_02_name(String category_02_name) {		this.category_02_name = category_02_name;	}	public String getCatetory_name() {		return catetory_name;	}	public void setCatetory_name(String catetory_name) {		this.catetory_name = catetory_name;	}	public String getCategory_01_name() {		return category_01_name;	}	public void setCategory_01_name(String category_01_name) {		this.category_01_name = category_01_name;	}	public String getSession_rate() {		return session_rate;	}	public void setSession_rate(String session_rate) {		this.session_rate = session_rate;	}	public String getArea() {		return area;	}	public void setArea(String area) {		this.area = area;	}	public String getTotal_score() {		return total_score;	}	public void setTotal_score(String total_score) {		this.total_score = total_score;	}	public String getCourse_id() {		return course_id;	}	public void setCourse_id(String course_id) {		this.course_id = course_id;	}	public String getUser_id() {		return user_id;	}	public void setUser_id(String user_id) {		this.user_id = user_id;	}}