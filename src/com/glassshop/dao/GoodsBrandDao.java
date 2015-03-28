package com.glassshop.dao;

import java.util.List;
import java.util.Vector;

import com.glassshop.model.GoodsBrand;

public interface GoodsBrandDao {

	public GoodsBrand selectById(String id);
	
	public List<GoodsBrand> selectAll();
	
	public List<GoodsBrand> selectBrandNameAll();
	
	public int insert(GoodsBrand goodsBrand);

	public int update(GoodsBrand goodsBrand);
	
	public int delete(String id);
	
//	public List<GoodsBrand> selectFillter(String vendorName, String VendorTel);
}
