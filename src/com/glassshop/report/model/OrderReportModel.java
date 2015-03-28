package com.glassshop.report.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.glassshop.common.DateFormatUtil;

public class OrderReportModel {
	
	private String fromDate;
	private String toDate;
	private String orderNo;
	private String orderDate;
	private String vendorName;
	private String salemanName;
	private Integer totalQty;
	private Double totalPrice;
	private String goodsName;
	private String goodsType;
	private String goodsBrand;
	private String goodsSub;
	private Double goodsCost;
	private Double goodsCostLabel;
	private Integer goodsQuantity;
	private Double goodsCostSum;
	private Integer stockGoodsQty;
	private Integer stockSellQty;
	private Integer stockQty;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderDate() {
		return DateFormatUtil.getFormatDate(orderDate);
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getSalemanName() {
		return salemanName;
	}
	public void setSalemanName(String salemanName) {
		this.salemanName = salemanName;
	}
	public Integer getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(Integer totalQty) {
		this.totalQty = totalQty;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
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
	public Double getGoodsCost() {
		return goodsCost;
	}
	public void setGoodsCost(Double goodsCost) { 
		this.goodsCost = goodsCost;
	}
	public Double getGoodsCostLabel() {
		return goodsCostLabel;
	}
	public void setGoodsCostLabel(Double goodsCostLabel) {
		this.goodsCostLabel = goodsCostLabel;
	}
	public Integer getGoodsQuantity() {
		return goodsQuantity;
	}
	public void setGoodsQuantity(Integer goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}
	public Double getGoodsCostSum() {
		return goodsCostSum;
	}
	public void setGoodsCostSum(Double goodsCostSum) {
		this.goodsCostSum = goodsCostSum;
	}
	public Integer getStockGoodsQty() {
		return stockGoodsQty;
	}
	public void setStockGoodsQty(Integer stockGoodsQty) {
		this.stockGoodsQty = stockGoodsQty;
	}
	public Integer getStockSellQty() {
		return stockSellQty;
	}
	public void setStockSellQty(Integer stockSellQty) {
		this.stockSellQty = stockSellQty;
	}
	public Integer getStockQty() {
		return stockQty;
	}
	public void setStockQty(Integer stockQty) {
		this.stockQty = stockQty;
	}
}
