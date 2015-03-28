package com.glassshop.dao;

import java.util.List;
import java.util.Vector;

import com.glassshop.model.Customer;
import com.glassshop.model.CustomerSight;

public interface CustomerDao {
	
	public Customer selectById(String id);
	
	public String selectCustNo();
			
	public List<Customer> selectAll();
	
	public List<Customer> selectPrintReportAll();
	
	public List<Customer> selectReport(String fromDt, String toDt);
	
	public int insert(Customer customer);
	
	public int insertSight(CustomerSight customerSight);

	public int update(Customer customer);
	
	public int delete(String id);
	
	public boolean deleteSight(String custId);
	
	public boolean deleteCustomer(String custId);
	
	public boolean insertCustomerAndSight(Customer customer, CustomerSight custSight);
		
	public boolean updateCustomer(Customer customer);
	
	public boolean updateSight( Customer customer, CustomerSight custSight);
	
	public boolean updateCustomerAndSight(Customer customer, CustomerSight custSight);
	
	public List<Customer> selectFillter(String custNo, String custName,String fromDt, String toDt);
	
	public Vector selectFillterCustomerReport(String custNo, String custName, String custSurname, String custPlace
			, String custAddress, String custSoi, String custRoad, String custDistric, String custCity
			, String custProvince, String custZipCode, String custTel, String custMobile, String custTarget
			, String custNews, String lensType, String monthBirthday, String searchType, String fromDt, String toDt);
	
	public Vector selectFillterCustomer(String custNo, String custName, String custSurname, String custPlace
			, String custAddress, String custSoi, String custRoad, String custDistric, String custCity
			, String custProvince, String custZipCode, String custTel, String custMobile, String custTarget
			, String custNews, String lensType, String monthBirthday, String searchType, String fromDt, String toDt);
}
