package com.glassshop.dao;

import java.util.List;
import java.util.Vector;

import com.glassshop.model.GoodsType;

public interface GoodsTypeDao {
	
	public GoodsType selectById(String id);
	
	public List<GoodsType> selectAll();
	
	public List<GoodsType> selectComboAll();
	
	public Vector selectComboByGood(String id);
	
	public int insert(GoodsType goodsType);

	public int update(GoodsType goodsType);
	
	public int delete(String id);
}
