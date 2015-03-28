package com.glassshop.model;

public class OrderSaleUpdate {
	
	private String orderId;
	private String orderNo;
	private String orderGoodsId;
	private String vendorName;
	private String goodsName;
	private String goodsType;
	private String goodsBrand;
	private String goodsSub;
	private String goodsCost;
	private String goodsCostLabel;
	private String goodsQuantity;
	private String sellCost;
	private String sellCostSum;
	private String sellQuantity;
	private String sellDate;
	private String sellNote;
	
	public OrderSaleUpdate(String orderGoodsId, String orderNo, String vendorName, String goodsName, String goodsType, 
			String goodsBrand, String goodsSub, String goodsCost, String goodsCostLabel, String goodsQuantity){
			//String sellCost, String sellCostSum, String Quantity, String sellDate, String sellNote){
		this.orderGoodsId = orderGoodsId;
		this.orderNo=orderNo;
		this.vendorName=vendorName;
		this.goodsName=goodsName;
		this.goodsType=goodsType;
		this.goodsBrand=goodsBrand;
		this.goodsSub=goodsSub;
		this.goodsCost=goodsCost;
		this.goodsCostLabel=goodsCostLabel;
		this.goodsQuantity=goodsQuantity;
//		this.sellCost=sellCost;
//		this.sellCostSum=sellCostSum;
//		this.sellQuantity=sellQuantity;
//		this.sellDate=sellDate;
//		this.sellNote=sellNote;
		
		
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderGoodsId() {
		return orderGoodsId;
	}
	public void setOrderGoodsId(String orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsBrand() {
		return goodsBrand;
	}
	public void setGoodsBrand(String goodsBrand) {
		this.goodsBrand = goodsBrand;
	}
	public String getGoodsSub() {
		return goodsSub;
	}
	public void setGoodsSub(String goodsSub) {
		this.goodsSub = goodsSub;
	}
	public String getGoodsCost() {
		return goodsCost;
	}
	public void setGoodsCost(String goodsCost) {
		this.goodsCost = goodsCost;
	}
	public String getGoodsCostLabel() {
		return goodsCostLabel;
	}
	public void setGoodsCostLabel(String goodsCostLabel) {
		this.goodsCostLabel = goodsCostLabel;
	}
	public String getGoodsQuantity() {
		return goodsQuantity;
	}
	public void setGoodsQuantity(String goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}
	
}
