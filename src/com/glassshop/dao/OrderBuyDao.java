package com.glassshop.dao;

import java.util.List;
import java.util.Vector;

import com.glassshop.model.OrderbuyModel;

public interface OrderBuyDao {
	
	public OrderbuyModel selectById(String id);
	
	public List<OrderbuyModel> selectAll();
	
	public Vector selectByOrderId(String orderId);
	
	public boolean insert(OrderbuyModel order, Vector vc);

	public boolean update(OrderbuyModel order, Vector vc);
	
	public boolean delete(String id);
	
	public Vector selectFillterOrder(String orderNo, String fromDt, String toDt, String vendorId, String SalesManId
			, String goodsId, String goodsTypeId, String goodsBrandId, String goodsSub);
	
	public Vector selectOrderReport(String orderNo, String fromDt, String toDt, String vendorId, String SalesManId
			, String goodsId, String goodsTypeId, String goodsBrandId, String goodsSub);
	
	public Vector selectRemainReport(String orderNo, String fromDt, String toDt, String vendorId
			, String goodsId, String goodsTypeId, String goodsBrandId, String goodsSub);

	public Vector selectOrderReport2(String orderNo, String fromDt, String toDt,
			String vendorId, String SalesManId, String goodsId,
			String goodsTypeId, String goodsBrandId, String goodsSub);

	public Vector selectRemainReport2(String orderNo, String fromDt, String toDt,
			String vendorId, String goodsId,
			String goodsTypeId, String goodsBrandId, String goodsSub);
	
}
