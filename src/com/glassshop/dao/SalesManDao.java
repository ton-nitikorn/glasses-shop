package com.glassshop.dao;

import java.util.List;
import java.util.Vector;

import com.glassshop.model.SalesMan;;

public interface SalesManDao {
	
	public SalesMan selectById(String id);
	
	public List<SalesMan> selectAll();
	
	public List<SalesMan> selectSalesNameAll();
	
	public Vector selectSalesNameByVendor(String vendorid);
	
	public int insert(SalesMan salesMan);

	public int update(SalesMan salesMan);
	
	public int delete(String id);
	
	public Vector selectFillter(String vendorId, String saleName, String saleTel);
}
