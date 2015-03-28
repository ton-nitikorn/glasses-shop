package com.glassshop.model;

public class CustomerUpdate {

	private String custId;
	private String custNo;
	private String custRecordDate;
	private String custNews;
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
	
	public CustomerUpdate(String custId, String custNo, String custRecordDate,
			String custNews, String custName, String custSurname,
			String custPlace, String custAddress, String custSoi,
			String custRoad, String custDistrict, String custCity,
			String custProvince, String custZipcode, String custTel,
			String custMobile, String custBirthday, String custTarget,
			String lensType) {

		this.custId = custId;
		this.custNo = custNo;
		this.custRecordDate = custRecordDate;
		this.custNews = custNews;
		this.custName = custName;
		this.custSurname = custSurname;
		this.custPlace = custPlace;
		this.custAddress = custAddress;
		this.custSoi = custSoi;
		this.custRoad = custRoad;
		this.custDistrict = custDistrict;
		this.custCity = custCity;
		this.custProvince = custProvince;
		this.custZipcode = custZipcode;
		this.custTel = custTel;
		this.custMobile = custMobile;
		this.custBirthday = custBirthday;
		this.custTarget = custTarget;
		this.lensType = lensType;
	}
	public String getCustId() {
		return custId;
	}
	public String getCustNo() {
		return custNo;
	}
	public String getCustRecordDate() {
		return custRecordDate;
	}
	public String getCustNews() {
		return custNews;
	}
	public String getCustName() {
		return custName;
	}
	public String getCustSurname() {
		return custSurname;
	}
	public String getCustPlace() {
		return custPlace;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public String getCustSoi() {
		return custSoi;
	}
	public String getCustRoad() {
		return custRoad;
	}
	public String getCustDistrict() {
		return custDistrict;
	}
	public String getCustCity() {
		return custCity;
	}
	public String getCustProvince() {
		return custProvince;
	}
	public String getCustZipcode() {
		return custZipcode;
	}
	public String getCustTel() {
		return custTel;
	}
	public String getCustMobile() {
		return custMobile;
	}
	public String getCustBirthday() {
		return custBirthday;
	}
	public String getCustTarget() {
		return custTarget;
	}
	public String getLensType() {
		return lensType;
	}
	
	
	
	
}
