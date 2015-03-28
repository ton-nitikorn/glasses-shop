package com.glassshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.glassshop.dao.OrderSaleDao;
import com.glassshop.model.Customer;
import com.glassshop.model.OrderSaleModel;
import com.glassshop.model.OrderbuyModel;
import com.glassshop.model.Sell;

public class OrderSaleDaoImpl extends JdbcDaoSupport implements OrderSaleDao {

	static Logger log = Logger.getLogger(CustomerDaoImpl.class.getName());
	
	@Override
	public OrderSaleModel selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Sell selectByOrderId(String id) {
		String sql = "SELECT SELL_ID, ORDERGOODS_ID, SELL_DATE, SELL_QUANTITY, SELL_COST, SELL_COST_SUM, SELL_NOTE, SELL_RECORD_DATE FROM SELL_TB WHERE ORDERGOODS_ID = ?";
		log.info(sql);
		Sell sell = (Sell) getJdbcTemplate().queryForObject(sql,
				new Object[] { id }, new BeanPropertyRowMapper(Sell.class));
		return sell;
	}
	@Override
	public List<OrderSaleModel> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(OrderSaleModel order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(OrderSaleModel order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(String id, String quantity) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean isSuccess = false;
		try {
			
			conn = getConnection();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer();
			StringBuffer stocksql = new StringBuffer();
			
			sql.append("DELETE FROM SELL_TB WHERE ORDERGOODS_ID = ? ");
			ps = conn.prepareStatement(sql.toString());
			
			ps.setString(1,id);
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				isSuccess = true;
			}else{
				isSuccess = false;
			}
			stocksql.append("UPDATE STOCK_TB SET SELL_QUANTITY = SELL_QUANTITY - ?, STOCK_QUANTITY = STOCK_QUANTITY + ? ");
			stocksql.append(" WHERE ORDERGOODS_ID = ? ");
			
			log.info("Stock Sql " + stocksql.toString());
			ps = conn.prepareStatement(stocksql.toString());
			int IndexStock = 1;
			ps.setDouble(IndexStock++, Double.valueOf(quantity));
			ps.setDouble(IndexStock++, Double.valueOf(quantity));
			ps.setString(IndexStock++,id);
			
			rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				isSuccess = true;
			}else{
				isSuccess = false;
			}
			
		} catch (Exception e) {
			logger.error("----------ERROR", e);
			try {
				if (conn != null)
					conn.rollback();
					conn.close();
					conn = null;
				} catch (Exception error) {
				}
		} finally {

			try {
				if (conn != null)
					conn.commit();
			} catch (Exception e) {
			}
			try {
				if (rs != null)
					rs.close();
				rs = null;
			} catch (Exception e) {
			}
			try {
				if (ps != null)
					ps.close();
				ps = null;
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
				conn = null;
			} catch (Exception e) {
				
			}
		}
		return isSuccess;
	}
	

	@Override
	public Vector selectFillterOrder(String orderNo, String vendorId, String goodsId, String goodsTypeId
			, String goodsBrandId, String goodsSub, String searchType, String fromDt, String toDt) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Vector vc = new Vector();
		try {
			
			conn = getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select A.ORDERGOODS_ID, C.ORDER_NO,(SELECT VENDOR_NAME FROM VENDOR_TB WHERE VENDOR_TB.VENDOR_ID = C.VENDOR_ID) AS VNAME , ");
			sql.append("(SELECT GOODS_NAME FROM GOODS_TB WHERE GOODS_TB.GOODS_ID = A.GOODS_ID) AS GNAME, ");
			sql.append("(SELECT GOODSTYPE_NAME FROM GOODSTYPE_TB WHERE GOODSTYPE_TB.GOODSTYPE_ID = A.GOODSTYPE_ID) AS GTNAME, ");
			sql.append("(SELECT GOODSBRAND_NAME FROM GOODSBRAND_TB WHERE GOODSBRAND_TB.GOODSBRAND_ID = A.GOODSBRAND_ID) AS GBNAME, ");
			sql.append("A.GOODSSUB, A.ORDERGOODS_COST,	A.ORDERGOODS_COST_LABEL,	A.ORDERGOODS_QUANTITY ");
//			sql.append(" B.SELL_DATE, B.SELL_QUANTITY, B.SELL_COST, B.SELL_COST_SUM, B.SELL_NOTE ");
			sql.append(" from ordergoods_tb a, stock_tb b, ORDER_TB C where a.ORDERGOODS_ID = b.ORDERGOODS_ID AND C.ORDER_ID = A.ORDER_ID ");
			
			if ((fromDt != null && !fromDt.equals("")) && (toDt != null && !toDt.equals(""))){
				sql.append(" AND FORMAT(C.ORDER_DATE,'YYYY/MM/DD') between ? and ? ");
			}
			if (searchType.equalsIgnoreCase("ADD")){
				sql.append(" AND B.STOCK_QUANTITY > 0 ");
			}else if (searchType.equalsIgnoreCase("DEL")){
				sql.append(" AND B.SELL_QUANTITY > 0 ");
			}
			if((orderNo!=null && orderNo.length()>0)){
				sql.append(" AND C.ORDER_NO LIKE ? ");
			}
			if((vendorId!=null && vendorId.length()>0)){
				sql.append(" AND C.VENDOR_ID = ? ");
			}
			if((goodsId!=null && goodsId.length()>0)){
				sql.append(" AND a.GOODS_ID = ? ");
			}
			if((goodsTypeId!=null && goodsTypeId.length()>0)){
				sql.append(" AND a.GOODSTYPE_ID = ? ");
			}
			if((goodsBrandId!=null && goodsBrandId.length()>0)){
				sql.append(" AND a.GOODSBRAND_ID = ? ");
			}
			if((goodsSub!=null && goodsSub.length()>0)){
				sql.append(" AND a.GOODSSUB LIKE ? ");
			}
			
			StringBuffer dsql = removeWasteSQL(sql);
			log.info("dsql " + dsql);
			ps = conn.prepareStatement(dsql.toString());
			int index = 0;
			if (fromDt != null && !fromDt.equals("")){
				ps.setString(++index, fromDt);
			}
			if (toDt != null && !toDt.equals("")) {
				ps.setString(++index, toDt);
			}
			if((orderNo!=null && orderNo.length()>0)){
				ps.setString(++index, "%" + orderNo.toUpperCase() + "%");
			}
			
			if((vendorId!=null && vendorId.length()>0)){
				ps.setString(++index, vendorId);
			}
			if((goodsId!=null && goodsId.length()>0)){
				ps.setString(++index, goodsId);
			}
			if((goodsTypeId!=null && goodsTypeId.length()>0)){
				ps.setString(++index, goodsTypeId);
			}
			if((goodsBrandId!=null && goodsBrandId.length()>0)){
				ps.setString(++index, goodsBrandId);
			}
			if((goodsSub!=null && goodsSub.length()>0)){
				ps.setString(++index,"%" + goodsSub +"%" );
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				OrderSaleModel orderModel = new OrderSaleModel();
				orderModel.setOrderGoodsId(rs.getString("ORDERGOODS_ID"));
				orderModel.setOrderNo(rs.getString("ORDER_NO"));
				orderModel.setVendorName(rs.getString("VNAME"));
				orderModel.setGoodsName(rs.getString("GNAME"));
				orderModel.setGoodsType(rs.getString("GTNAME"));
				orderModel.setGoodsBrand(rs.getString("GBNAME"));
				orderModel.setGoodsSub(rs.getString("GOODSSUB"));
				orderModel.setGoodsCost(rs.getString("ORDERGOODS_COST"));
				orderModel.setGoodsCostLabel(rs.getString("ORDERGOODS_COST_LABEL"));
				orderModel.setGoodsQuantity(rs.getString("ORDERGOODS_QUANTITY"));
//				orderModel.setSellCost(rs.getString("SELL_COST"));
//				orderModel.setSellDate(rs.getString("SELL_DATE"));
//				orderModel.setSellQuantity(rs.getString("SELL_QUANTITY"));
//				orderModel.setSellCostSum(rs.getString("SELL_COST_SUM"));
//				orderModel.setSaleNote(rs.getString("SELL_NOTE"));
				
				vc.add(orderModel);
			}
		} catch (Exception e) {
			logger.error("----------ERROR", e);
		} finally {

			try {
				if (conn != null)
					conn.commit();
			} catch (Exception e) {
			}
			try {
				if (rs != null)
					rs.close();
				rs = null;
			} catch (Exception e) {
			}
			try {
				if (ps != null)
					ps.close();
				ps = null;
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
				conn = null;
			} catch (Exception e) {
				
			}
		}	
		return vc;
	}
	StringBuffer removeWasteSQL( StringBuffer dSqlBuffer ){
		//remove AND 
		if(dSqlBuffer.toString().trim().endsWith("AND")) 
			dSqlBuffer.delete(dSqlBuffer.toString().lastIndexOf("AND"), dSqlBuffer.toString().length());
			
		//remove WHERE 			
		if(dSqlBuffer.toString().trim().endsWith("WHERE")) 
			dSqlBuffer.delete(dSqlBuffer.toString().lastIndexOf("WHERE"), dSqlBuffer.toString().length());

		//remove Comma
		if(dSqlBuffer.toString().trim().endsWith(",")) 
			dSqlBuffer.delete(dSqlBuffer.toString().lastIndexOf(","), dSqlBuffer.toString().length());
		
		//remove OR
		if(dSqlBuffer.toString().trim().endsWith("OR")) 
			dSqlBuffer.delete(dSqlBuffer.toString().lastIndexOf("OR"), dSqlBuffer.toString().length());
				
		return dSqlBuffer;
	}

	@Override
	public boolean insertSell(OrderSaleModel order) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		double sellId = 0;
		boolean isSuccess = false;
		try {
			
			conn = getConnection();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer();
			StringBuffer sellsql = new StringBuffer();
			StringBuffer stocksql = new StringBuffer();
			sql.append("SELECT MAX(SELL_ID)+1 AS SELLID FROM SELL_TB");
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				sellId = rs.getDouble("SELLID");
			}
			
			rs.close();
			
			
			sellsql.append("INSERT INTO SELL_TB (SELL_ID, ORDERGOODS_ID, SELL_DATE, SELL_QUANTITY, SELL_COST, SELL_COST_SUM, SELL_NOTE, SELL_RECORD_DATE)");
			sellsql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");
			
			log.info("sellsql.toString() >> " + sellsql.toString());
			
			ps = conn.prepareStatement(sellsql.toString());
			int Index = 1;
			ps.setDouble(Index++, sellId);
			ps.setString(Index++, order.getOrderGoodsId());
			ps.setString(Index++, order.getSaleDate());
			ps.setString(Index++, order.getGoodsQuantity());
			ps.setString(Index++, order.getGoodsCost());
			ps.setString(Index++, order.getGoodsCostSum());
			ps.setString(Index++, order.getSaleNote());
			ps.setString(Index++, null);
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				isSuccess = true;
			}else{
				isSuccess = false;
			}
			stocksql.append("UPDATE STOCK_TB SET SELL_QUANTITY = SELL_QUANTITY + ?, STOCK_QUANTITY = STOCK_QUANTITY - ? ");
			stocksql.append(" WHERE ORDERGOODS_ID = ? ");
			
			log.info("Stock Sql " + stocksql.toString());
			ps = conn.prepareStatement(stocksql.toString());
			int IndexStock = 1;
			log.info("Double.valueOf(order.getGoodsQuantity()) " +Double.valueOf(order.getGoodsQuantity()) + ":"+order.getOrderGoodsId());
			ps.setDouble(IndexStock++, Double.valueOf(order.getGoodsQuantity()));
			ps.setDouble(IndexStock++, Double.valueOf(order.getGoodsQuantity()));
			ps.setString(IndexStock++, order.getOrderGoodsId());
			
			rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				isSuccess = true;
			}else{
				isSuccess = false;
			}
			
		} catch (Exception e) {
			logger.error("----------ERROR", e);
			try {
				if (conn != null)
					conn.rollback();
					conn.close();
					conn = null;
				} catch (Exception error) {
				}
		} finally {

			try {
				if (conn != null)
					conn.commit();
			} catch (Exception e) {
			}
			try {
				if (rs != null)
					rs.close();
				rs = null;
			} catch (Exception e) {
			}
			try {
				if (ps != null)
					ps.close();
				ps = null;
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
				conn = null;
			} catch (Exception e) {
				
			}
		}
		return isSuccess;
	}
	@Override
	public Vector selectSellReport(String orderNo, String fromDt, String toDt, String vendorId
			, String goodsId, String goodsTypeId, String goodsBrandId, String goodsSub) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Vector vc = new Vector();
		try {
			
			conn = getConnection();
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT B.ORDER_ID, A.SELL_ID, A.ORDERGOODS_ID, A.SELL_DATE, A.SELL_QUANTITY, B.ORDER_NO, ");
			//sql.append("(SELECT SALESMAN_NAME FROM SALESMAN_TB WHERE SALESMAN_TB.SALESMAN_ID = B.SALESMAN_ID) AS SNAME, ");
			sql.append("(SELECT VENDOR_NAME FROM VENDOR_TB WHERE VENDOR_TB.VENDOR_ID = B.VENDOR_ID) AS VNAME, ");
			sql.append("(SELECT GOODS_NAME FROM GOODS_TB WHERE GOODS_TB.GOODS_ID = C.GOODS_ID) AS GNAME,  ");
			sql.append("(SELECT GOODSTYPE_NAME FROM GOODSTYPE_TB WHERE GOODSTYPE_TB.GOODSTYPE_ID = C.GOODSTYPE_ID) AS GTNAME, ");
			sql.append("(SELECT GOODSBRAND_NAME FROM GOODSBRAND_TB WHERE GOODSBRAND_TB.GOODSBRAND_ID = C.GOODSBRAND_ID) AS GBNAME, ");
			sql.append(" C.GOODSSUB, A.SELL_COST, A.SELL_COST_SUM FROM SELL_TB A, ORDER_TB B, ORDERGOODS_TB C ");
			sql.append(" WHERE B.ORDER_ID = C.ORDER_ID AND C.ORDERGOODS_ID = A.ORDERGOODS_ID ");
			
			
			if((orderNo!=null && orderNo.length()>0)){
				sql.append(" AND B.ORDER_NO LIKE ? ");
			}
			if ((fromDt != null && !fromDt.equals("")) && (toDt != null && !toDt.equals(""))){
				sql.append(" AND FORMAT(A.SELL_DATE,'YYYY/MM/DD') between ? and ? ");
			}
			if((vendorId!=null && vendorId.length()>0)){
				sql.append(" AND B.VENDOR_ID = ? ");
			}
			if((goodsId!=null && goodsId.length()>0)){
				sql.append(" AND C.GOODS_ID = ? ");
			}
			if((goodsTypeId!=null && goodsTypeId.length()>0)){
				sql.append(" AND C.GOODSTYPE_ID = ? ");
			}
			if((goodsBrandId!=null && goodsBrandId.length()>0)){
				sql.append(" AND C.GOODSBRAND_ID = ? ");
			}
			if((goodsSub!=null && goodsSub.length()>0)){
				sql.append(" AND C.GOODSSUB LIKE ? ");
			}
			
			StringBuffer dsql = removeWasteSQL(sql);
			log.info("dsql " + dsql);
			
			ps = conn.prepareStatement(dsql.toString());
			int index = 0;
			
			if((orderNo!=null && orderNo.length()>0)){
				ps.setString(++index, "%" + orderNo.toUpperCase() + "%");
			}
			if (fromDt != null && !fromDt.equals("")){
				ps.setString(++index, fromDt);
			}
			if (toDt != null && !toDt.equals("")) {
				ps.setString(++index, toDt);
			}
			
			if((vendorId!=null && vendorId.length()>0)){
				ps.setString(++index, vendorId);
			}
			if((goodsId!=null && goodsId.length()>0)){
				ps.setString(++index, goodsId);
			}
			if((goodsTypeId!=null && goodsTypeId.length()>0)){
				ps.setString(++index, goodsTypeId);
			}
			if((goodsBrandId!=null && goodsBrandId.length()>0)){
				ps.setString(++index, goodsBrandId);
			}
			if((goodsSub!=null && goodsSub.length()>0)){
				ps.setString(++index,"%" + goodsSub +"%" );
			}
			
			
			rs = ps.executeQuery();
			while (rs.next()) {
				OrderSaleModel orderModel = new OrderSaleModel();
				orderModel.setOrderId(rs.getString("ORDER_ID"));
				orderModel.setOrderNo(rs.getString("ORDER_NO"));
				orderModel.setSellDate(rs.getString("SELL_DATE"));
				orderModel.setVendorName(rs.getString("VNAME"));
				orderModel.setGoodsName(rs.getString("GNAME"));
				orderModel.setGoodsType(rs.getString("GTNAME"));
				orderModel.setGoodsBrand(rs.getString("GBNAME"));
				orderModel.setGoodsSub(rs.getString("GOODSSUB"));
				orderModel.setSellQuantity(Integer.parseInt(rs.getString("SELL_QUANTITY")));
				orderModel.setSellCostSum(Double.parseDouble(rs.getString("SELL_COST_SUM")));
				orderModel.setSellCost(Double.parseDouble(rs.getString("SELL_COST")));
				orderModel.setFromDate(fromDt);
				orderModel.setToDate(toDt);
				
				vc.add(orderModel);
			}
		} catch (Exception e) {
			logger.error("----------ERROR", e);
		} finally {

			try {
				if (conn != null)
					conn.commit();
			} catch (Exception e) {
			}
			try {
				if (rs != null)
					rs.close();
				rs = null;
			} catch (Exception e) {
			}
			try {
				if (ps != null)
					ps.close();
				ps = null;
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
				conn = null;
			} catch (Exception e) {
				
			}
		}	
		return vc;
	}
}
