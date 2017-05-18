package com.quark.skillopedia.alipay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * 微信支付预订单
 * @author Administrator
 *
 */
public class ComfiyPayResponseWeixin  {

	String package_weixin;//": "Sign=WXPay",
	String code;//": 200, 
	String api_key;//": "uelives123uelives123uelives12345", 
	String appid;//": "wx4b0ab3ff218ae682", 
	String sign;//": "3974d40f0f43e1254e78eea6a15a707f50c7a5de", 
	String partnerid;//": "1281968701", 
	String prepayid;//": "wx201511091057302ba7e2c1180732381372", 
	String noncestr;//": "8d6a06b2f1208b59454a9a749928b0c0",
	String timestamp;//": "1447037807",
	String pay_money;
	int status;//": 1
	
	 public ComfiyPayResponseWeixin() {
	    }
	    public ComfiyPayResponseWeixin(String json) {
	      Map<String, ComfiyPayResponseWeixin> map = JSON.parseObject(json, new TypeReference<Map<String, ComfiyPayResponseWeixin>>() {
		  });
	      this.code = map.get("ComfiyPayResponse").getCode();
	      this.api_key = map.get("ComfiyPayResponse").getApi_key();
	      this.appid = map.get("ComfiyPayResponse").getAppid();
	      this.sign = map.get("ComfiyPayResponse").getSign();
	      this.partnerid = map.get("ComfiyPayResponse").getPartnerid();
	      this.prepayid = map.get("ComfiyPayResponse").getPrepayid();
	      this.noncestr = map.get("ComfiyPayResponse").getNoncestr();
	      this.timestamp = map.get("ComfiyPayResponse").getTimestamp();
	      this.status = map.get("ComfiyPayResponse").getStatus();
		this.pay_money = map.get("ComfiyPayResponse").getPay_money();
	    }

	public String getPackage_weixin() {
		return package_weixin;
	}

	public void setPackage_weixin(String package_weixin) {
		this.package_weixin = package_weixin;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPay_money() {
		return pay_money;
	}

	public void setPay_money(String pay_money) {
		this.pay_money = pay_money;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
