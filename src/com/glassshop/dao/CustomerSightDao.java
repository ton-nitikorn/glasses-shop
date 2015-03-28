package com.glassshop.dao;

import java.util.List;

import com.glassshop.model.CustomerSight;

public interface CustomerSightDao {
	
	public CustomerSight selectById(String id);
	
	public List<CustomerSight> selectAll();
	
	public List<CustomerSight> selectByIdAll(String id);
	
	public int insert(CustomerSight custSight);

	public int update(CustomerSight custSight);
	
	public int delete(String id);
	
	public int updateAppointmentDate(String custId, String appointDate);
}
