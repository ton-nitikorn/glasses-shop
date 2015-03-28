package com.glassshop.model;

import java.sql.Date;

public class Customer {
	
	private String custId;
	private String custNo;
	private String custRecordDate;
	private String custNews;
	private String custTitle;
	private String custName;
	private String custSurname;
	private String custPlace;
	private String custAddress;
	private String custSoi;
	private String custRoad;
	private String custDistrict;
	private String custCity;
	private String custProvince;
	private String custZipcode;
	private String custTel;
	private String custMobile;
	private String custBirthday;
	private String custTarget;
	private String lensType;
	
	
	public String getCustTitle() {
		return custTitle;
	}
	public void setCustTitle(String custTitle) {
		this.custTitle = custTitle;
	}
	public String getLensType() {
		return lensType;
	}
	public void setLensType(String lensType) {
		this.lensType = lensType;
	}
//	public Long getCustId() {
//		return custId;
//	}
//	public void setCustId(Long custId) {
//		this.custId = custId;
//	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustNo() {
		return custNo;
	}
	
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustNews() {
		return custNews;
	}
	public void setCustNews(String custNews) {
		this.custNews = custNews;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	public String getCustSurname() {
		return custSurname;
	}
	public void setCustSurname(String custSurname) {
		this.custSurname = custSurname;
	}
	public String getCustPlace() {
		return custPlace;
	}
	public void setCustPlace(String custPlace) {
		this.custPlace = custPlace;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getCustSoi() {
		return custSoi;
	}
	public void setCustSoi(String custSoi) {
		this.custSoi = custSoi;
	}
	public String getCustRoad() {
		return custRoad;
	}
	public void setCustRoad(String custRoad) {
		this.custRoad = custRoad;
	}
	public String getCustDistrict() {
		return custDistrict;
	}
	public void setCustDistrict(String custDistrict) {
		this.custDistrict = custDistrict;
	}
	public String getCustCity() {
		return custCity;
	}
	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}
	public String getCustProvince() {
		return custProvince;
	}
	public void setCustProvince(String custProvince) {
		this.custProvince = custProvince;
	}
	public String getCustZipcode() {
		return custZipcode;
	}
	public void setCustZipcode(String custZipcode) {
		this.custZipcode = custZipcode;
	}
	public String getCustTel() {
		if (custTel!=null) return custTel;
		return "";
	}
	public void setCustTel(String custTel) {
		
		this.custTel = custTel;
	}
	public String getCustMobile() {
		
		return custMobile;
	}
	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}
	
	public String getCustTarget() {
		return custTarget;
	}
	public void setCustTarget(String custTarget) {
		this.custTarget = custTarget;
	}
	public String getCustRecordDate() {
		return custRecordDate;
	}
	public void setCustRecordDate(String custRecordDate) {
		this.custRecordDate = custRecordDate;
	}
	public String getCustBirthday() {
		return custBirthday;
	}
	public void setCustBirthday(String custBirthday) {
		this.custBirthday = custBirthday;
	}

}
