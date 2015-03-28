package com.glassshop.dao;

import java.util.List;
import java.util.Vector;

import com.glassshop.model.OrderSaleModel;
import com.glassshop.model.Sell;

public interface OrderSaleDao {
	
	public OrderSaleModel selectById(String id);
	
	public Sell selectByOrderId(String id);
		
	public List<OrderSaleModel> selectAll();
	
	public int insert(OrderSaleModel order);

	public int update(OrderSaleModel order);
	
	public boolean delete(String id, String quantity);
	
	public boolean insertSell(OrderSaleModel order);
	
	public Vector selectFillterOrder(String orderNo, String vendorId
			, String goodsId, String goodsTypeId, String goodsBrandId, String goodsSub, String searchType, String fromDt, String toDt);
	
	public Vector selectSellReport(String orderNo, String fromDt, String toDt, String vendorId
			, String goodsId, String goodsTypeId, String goodsBrandId, String goodsSub);
}
