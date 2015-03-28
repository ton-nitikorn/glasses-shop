package com.glassshop.model;

import com.glassshop.common.DateFormatUtil;

public class OrderSaleModel {
	
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
	private String goodsCostSum;
	private String saleNote;
	private String saleDate;
	private Double sellCost;
	private Double sellCostSum;
	private Integer sellQuantity;
	private String sellDate;
	private String fromDate;
	private String toDate;
	
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
	public String getGoodsCostSum() {
		return goodsCostSum;
	}
	public void setGoodsCostSum(String goodsCostSum) {
		this.goodsCostSum = goodsCostSum;
	}
	public String getSaleNote() {
		return saleNote;
	}
	public void setSaleNote(String saleNote) {
		this.saleNote = saleNote;
	}
	public String getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
	public Double getSellCost() {
		return sellCost;
	}
	public void setSellCost(Double sellCost) {
		this.sellCost = sellCost;
	}
	public Double getSellCostSum() {
		return sellCostSum;
	}
	public void setSellCostSum(Double sellCostSum) {
		this.sellCostSum = sellCostSum;
	}
	public Integer getSellQuantity() {
		return sellQuantity;
	}
	public void setSellQuantity(Integer sellQuantity) {
		this.sellQuantity = sellQuantity;
	}
	public String getSellDate() {
		return sellDate;
	}
	public String getSellDate2() {
		return DateFormatUtil.getFormatDate(sellDate);
	}
	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}
	public String getFromDate() {
		return DateFormatUtil.getFormatDate(fromDate,"yyyy/MM/dd");
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return DateFormatUtil.getFormatDate(toDate,"yyyy/MM/dd");
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
}
