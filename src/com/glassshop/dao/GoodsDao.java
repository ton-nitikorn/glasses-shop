package com.glassshop.dao;

import java.util.List;

import com.glassshop.model.Goods;

public interface GoodsDao {
	
	public Goods selectById(String id);
	
	public List<Goods> selectAll();
	
	public List<Goods> selectGoodNameAll();
	
	public int insert(Goods goods);

	public int update(Goods goods);
	
	public int delete(String id);
	
//	public List<GoodsBrand> selectFillter(String vendorName, String VendorTel);
}
