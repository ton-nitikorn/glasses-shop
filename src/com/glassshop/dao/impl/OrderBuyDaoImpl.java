package com.glassshop.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.glassshop.dao.OrderBuyDao;
import com.glassshop.model.OrderGoods;
import com.glassshop.model.OrderbuyModel;
import com.glassshop.report.model.OrderReportModel;

public class OrderBuyDaoImpl extends JdbcDaoSupport implements OrderBuyDao {

	static Logger log = Logger.getLogger(CustomerDaoImpl.class.getName());
	
	@Override
	public OrderbuyModel selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderbuyModel> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(OrderbuyModel order, Vector vc) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		double orderId = 0;
		double goodsId = 0;
		double stockId = 0;
		boolean isSuccess = false;
		try {
			
			conn = getConnection();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer();
			StringBuffer ordersql = new StringBuffer();
			StringBuffer gsql = new StringBuffer();
			StringBuffer goodssql = new StringBuffer();
			StringBuffer ssql = new StringBuffer();
			StringBuffer stocksql = new StringBuffer();
			
			sql.append("SELECT MAX(ORDER_ID)+1 AS ORDERID FROM ORDER_TB");
			ps = conn.prepareStatement(sql.toString());
			
			rs = ps.executeQuery();
			while (rs.next()) {
				orderId = rs.getDouble("ORDERID");
			}
			
			rs.close();
			
			
			ordersql.append("INSERT INTO ORDER_TB (ORDER_ID, ORDER_NO, VENDOR_ID, SALESMAN_ID, ORDER_DATE, ORDER_NOTE) VALUES (?, ?, ?, ?, ?, ?)");
			
			log.info("ordersql >> " + ordersql.toString());
			
			ps = conn.prepareStatement(ordersql.toString());
			int Index = 1;
			ps.setDouble(Index++, orderId);
			ps.setString(Index++, order.getOrderNo());
			ps.setString(Index++, order.getVendorId());
			ps.setString(Index++, order.getSalesmanId());
			ps.setString(Index++, order.getOrderDate());
			ps.setString(Index++, order.getOrderNote());
			
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				isSuccess = true;
			}else{
				isSuccess = false;
			}
			
			if (vc != null){
				if (vc.size()>0){
					
					double ordergoodsId = 0;
					
					gsql.append("SELECT MAX(ORDERGOODS_ID)+1 AS GOODSID FROM ORDERGOODS_TB");
					ps = conn.prepareStatement(gsql.toString());
					
					rs = ps.executeQuery();
					while (rs.next()) {
						goodsId = rs.getDouble("GOODSID");
					}
					
					rs.close();
					ordergoodsId = goodsId;
					goodssql.append("INSERT INTO ORDERGOODS_TB (ORDERGOODS_ID, ORDER_ID, GOODS_ID, GOODSTYPE_ID, GOODSBRAND_ID, GOODSSUB, ORDERGOODS_COST, ORDERGOODS_COST_LABEL, ORDERGOODS_QUANTITY, ORDERGOODS_COST_SUM)");
					goodssql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
					
					log.info("goodssql >> " + goodssql.toString());
					
					ps = conn.prepareStatement(goodssql.toString());
					
					for(int i = 0; i<vc.size();i++){
						OrderGoods goods = (OrderGoods)vc.get(i);
						int goodsIndex = 1;
						
						ps.setDouble(goodsIndex++, ordergoodsId);
						ps.setDouble(goodsIndex++, orderId);
						ps.setString(goodsIndex++, goods.getGoodsId());
						ps.setString(goodsIndex++, goods.getGoodstypeId());
						ps.setString(goodsIndex++, goods.getGoodsbrandId());
						ps.setString(goodsIndex++, goods.getGoodssub());
						ps.setString(goodsIndex++, goods.getOrdergoodsCost());
						ps.setString(goodsIndex++, goods.getOrdergoodsCostLabel());
						ps.setString(goodsIndex++, goods.getOrdergoodsQuantity());
						ps.setString(goodsIndex++, goods.getOrdergoodsCostSum());
						ordergoodsId = ordergoodsId + 1;
						
						rsInt = ps.executeUpdate();
						if (rsInt > 0) {
							isSuccess = true;
						}else{
							isSuccess = false;
						}
					}
					
					ordergoodsId = goodsId;
					
					stocksql.append("INSERT INTO STOCK_TB (ID, ORDERGOODS_ID, ORDERGOODS_QUANTITY, SELL_QUANTITY, STOCK_QUANTITY) ");
					stocksql.append(" VALUES (?, ?, ?, 0, ?) ");
					
					log.info("Stock Sql " + stocksql.toString());
					
					ps = conn.prepareStatement(stocksql.toString());
//					
					for(int j = 0; j<vc.size();j++){
						OrderGoods goods = (OrderGoods)vc.get(j);
						int IndexStock = 1;
						
						ps.setDouble(IndexStock++, ordergoodsId);
						ps.setDouble(IndexStock++, ordergoodsId);
						ps.setString(IndexStock++, goods.getOrdergoodsQuantity());
						ps.setString(IndexStock++, goods.getOrdergoodsQuantity());
						ordergoodsId = ordergoodsId + 1;
						rsInt = ps.executeUpdate();
						if (rsInt > 0) {
							isSuccess = true;
						}else{
							isSuccess = false;
						}
					}
				}
				
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
	public boolean update(OrderbuyModel order, Vector vc) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean isSuccess = false;
		String orderId="";
		try {
			
			conn = getConnection();
			conn.setAutoCommit(false);
			StringBuffer ordersql = new StringBuffer();
			StringBuffer dGoodsql = new StringBuffer();
			StringBuffer dtsql = new StringBuffer();
			StringBuffer stsql = new StringBuffer();
			StringBuffer gsql = new StringBuffer();
			StringBuffer goodssql = new StringBuffer();
			StringBuffer ssql = new StringBuffer();
			StringBuffer stocksql = new StringBuffer();
			
			ordersql.append("UPDATE ORDER_TB SET VENDOR_ID = ?, SALESMAN_ID = ?, ORDER_DATE = ?, ORDER_NOTE = ?");
			ordersql.append(" WHERE ORDER_ID = ? ");
					
			log.info("ordersql >> " + ordersql.toString());
			
			ps = conn.prepareStatement(ordersql.toString());
			int Index = 1;
			ps.setString(Index++, order.getVendorId());
			ps.setString(Index++, order.getSalesmanId());
			ps.setString(Index++, order.getOrderDate());
			ps.setString(Index++, order.getOrderNote());
			ps.setString(Index++, order.getOrderId());
			orderId = order.getOrderId();
			log.info("orderid = " + orderId);
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				isSuccess = true;
			}else{
				isSuccess = false;
			}
			
			if (vc != null){
				double goodsId = 0;
				if (vc.size()>0){
					
					double ordergoodsId = 0;
					dGoodsql.append("DELETE FROM ORDERGOODS_TB WHERE ORDER_ID = ? ");
					ps = conn.prepareStatement(dGoodsql.toString());
					ps.setString(1, orderId);
					rsInt = ps.executeUpdate();
					if (rsInt > 0) {
						isSuccess = true;
					}else{
						isSuccess = false;
					}
					
					stsql.append("SELECT ORDERGOODS_ID FROM ORDERGOODS_TB WHERE ORDER_ID = ? ");
					ps = conn.prepareStatement(stsql.toString());
					ps.setString(1, orderId);
					rs = ps.executeQuery();
					
					dtsql.append("DELETE  FROM STOCK_TB WHERE ORDERGOODS_ID = ? ");
					ps = conn.prepareStatement(stsql.toString());
					while (rs.next()) {
						ps.setString(1, rs.getString("ORDERGOODS_ID"));
						rsInt = ps.executeUpdate();
						if (rsInt > 0) {
							isSuccess = true;
						}else{
							isSuccess = false;
						}
					}
					
					gsql.append("SELECT MAX(ORDERGOODS_ID)+1 AS GOODSID FROM ORDERGOODS_TB");
					ps = conn.prepareStatement(gsql.toString());
					
					rs = ps.executeQuery();
					while (rs.next()) {
						goodsId = rs.getDouble("GOODSID");
					}
					
					rs.close();
					ordergoodsId = goodsId;
					goodssql.append("INSERT INTO ORDERGOODS_TB (ORDERGOODS_ID, ORDER_ID, GOODS_ID, GOODSTYPE_ID, GOODSBRAND_ID, GOODSSUB, ORDERGOODS_COST, ORDERGOODS_COST_LABEL, ORDERGOODS_QUANTITY, ORDERGOODS_COST_SUM)");
					goodssql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
					
					log.info("goodssql >> " + goodssql.toString());
					
					ps = conn.prepareStatement(goodssql.toString());
//					Double dbOrderId = new Double(orderId);
//					double dbOrderId = Double.parseDouble(orderId);
					log.info("order goods = " + orderId);
					for(int i = 0; i<vc.size();i++){
						OrderGoods goods = (OrderGoods)vc.get(i);
						int goodsIndex = 1;
						
						ps.setDouble(goodsIndex++, ordergoodsId);
						ps.setDouble(goodsIndex++, Double.parseDouble(orderId));
						ps.setString(goodsIndex++, goods.getGoodsId());
						ps.setString(goodsIndex++, goods.getGoodstypeId());
						ps.setString(goodsIndex++, goods.getGoodsbrandId());
						ps.setString(goodsIndex++, goods.getGoodssub());
						ps.setString(goodsIndex++, goods.getOrdergoodsCost());
						ps.setString(goodsIndex++, goods.getOrdergoodsCostLabel());
						ps.setString(goodsIndex++, goods.getOrdergoodsQuantity());
						ps.setString(goodsIndex++, goods.getOrdergoodsCostSum());
						ordergoodsId = ordergoodsId + 1;
						
						rsInt = ps.executeUpdate();
						if (rsInt > 0) {
							isSuccess = true;
						}else{
							isSuccess = false;
						}
					}
					
					ordergoodsId = goodsId;
					
					stocksql.append("INSERT INTO STOCK_TB (ID, ORDERGOODS_ID, ORDERGOODS_QUANTITY, SELL_QUANTITY, STOCK_QUANTITY) ");
					stocksql.append(" VALUES (?, ?, ?, 0, ?) ");
					
					log.info("Stock Sql " + stocksql.toString());
					
					ps = conn.prepareStatement(stocksql.toString());
//					
					for(int j = 0; j<vc.size();j++){
						OrderGoods goods = (OrderGoods)vc.get(j);
						int IndexStock = 1;
						
						ps.setDouble(IndexStock++, ordergoodsId);
						ps.setDouble(IndexStock++, ordergoodsId);
						ps.setString(IndexStock++, goods.getOrdergoodsQuantity());
						ps.setString(IndexStock++, goods.getOrdergoodsQuantity());
						ordergoodsId = ordergoodsId + 1;
						rsInt = ps.executeUpdate();
						if (rsInt > 0) {
							isSuccess = true;
						}else{
							isSuccess = false;
						}
					}
				}
				
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
	public boolean delete(String id) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean isSuccess = false;
		boolean isTrue = false;
		try {
			
			conn = getConnection();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer();
			StringBuffer ordersql = new StringBuffer();
			
			sql.append("SELECT * FROM SELL_TB WHERE ORDERGOODS_ID = ? ");
			ps = conn.prepareStatement(sql.toString());
			
			ps.setDouble(1, Double.parseDouble(id));
			
			rs = ps.executeQuery();
			while (rs.next()) {
				isTrue = true;
			}
			
			rs.close();
			
			if (!isTrue){
				ordersql.append("DELETE FROM ORDERGOODS_TB WHERE ORDERGOODS_ID = ? ");
				
				log.info("ordersql >> " + ordersql.toString() + " id = " + id);
				
				ps = conn.prepareStatement(ordersql.toString());
				
				ps.setDouble(1, Double.parseDouble(id));
				
				int rsInt = ps.executeUpdate();
				if (rsInt > 0) {
					isSuccess = true;
				}else{
					isSuccess = false;
				}
				
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
	public Vector selectFillterOrder(String orderNo, String fromDt, String toDt, String vendorId, String SalesManId
			, String goodsId, String goodsTypeId, String goodsBrandId, String goodsSub) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Vector vc = new Vector();
		try {
			
			conn = getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select C.ORDER_ID, C.ORDER_NO,C.ORDER_DATE, ");
			sql.append("(SELECT SALESMAN_NAME FROM SALESMAN_TB WHERE SALESMAN_TB.SALESMAN_ID = C.SALESMAN_ID) AS SNAME, ");
			sql.append("(SELECT VENDOR_NAME FROM VENDOR_TB WHERE VENDOR_TB.VENDOR_ID = C.VENDOR_ID) AS VNAME , ");
			sql.append("(SELECT GOODS_NAME FROM GOODS_TB WHERE GOODS_TB.GOODS_ID = A.GOODS_ID) AS GNAME, ");
			sql.append("(SELECT GOODSTYPE_NAME FROM GOODSTYPE_TB WHERE GOODSTYPE_TB.GOODSTYPE_ID = A.GOODSTYPE_ID) AS GTNAME, ");
			sql.append("(SELECT GOODSBRAND_NAME FROM GOODSBRAND_TB WHERE GOODSBRAND_TB.GOODSBRAND_ID = A.GOODSBRAND_ID) AS GBNAME, ");
			sql.append("A.GOODSSUB, A.ORDERGOODS_COST,	A.ORDERGOODS_COST_LABEL, A.ORDERGOODS_QUANTITY, A.ORDERGOODS_COST_SUM, C.ORDER_NOTE ");
			sql.append("from ordergoods_tb a, stock_tb b, ORDER_TB C where a.ORDERGOODS_ID = b.ORDERGOODS_ID AND C.ORDER_ID = A.ORDER_ID ");
			
			if((orderNo!=null && orderNo.length()>0)){
				sql.append(" AND C.ORDER_NO LIKE ? ");
			}
			if ((fromDt != null && !fromDt.equals("")) && (toDt != null && !toDt.equals(""))){
				sql.append(" AND FORMAT(C.ORDER_DATE,'YYYY/MM/DD') between ? and ? ");
			}
			if((vendorId!=null && vendorId.length()>0)){
				sql.append(" AND C.VENDOR_ID = ? ");
			}
			if((SalesManId!=null && SalesManId.length()>0)){
				sql.append(" AND C.SALESMAN_ID = ? ");
			}
			if((goodsId!=null && goodsId.length()>0)){
				sql.append(" AND A.GOODS_ID = ? ");
			}
			if((goodsTypeId!=null && goodsTypeId.length()>0)){
				sql.append(" AND A.GOODSTYPE_ID = ? ");
			}
			if((goodsBrandId!=null && goodsBrandId.length()>0)){
				sql.append(" AND A.GOODSBRAND_ID = ? ");
			}
			if((goodsSub!=null && goodsSub.length()>0)){
				sql.append(" AND A.GOODSSUB LIKE ? ");
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
			if((SalesManId!=null && SalesManId.length()>0)){
				ps.setString(++index, SalesManId);
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
				OrderbuyModel orderModel = new OrderbuyModel();
				orderModel.setOrderId(rs.getString("ORDER_ID"));
				orderModel.setOrderNo(rs.getString("ORDER_NO"));
				orderModel.setOrderDate(rs.getString("ORDER_DATE"));
				orderModel.setVendorName(rs.getString("VNAME"));
				orderModel.setSalesmanName(rs.getString("SNAME"));
				orderModel.setGoodsName(rs.getString("GNAME"));
				orderModel.setGoodsType(rs.getString("GTNAME"));
				orderModel.setGoodsBrand(rs.getString("GBNAME"));
				orderModel.setGoodsSub(rs.getString("GOODSSUB"));
				orderModel.setGoodsCost(rs.getString("ORDERGOODS_COST"));
				orderModel.setGoodsCostLabel(rs.getString("ORDERGOODS_COST_LABEL"));
				orderModel.setOrderQuantity(rs.getString("ORDERGOODS_QUANTITY"));
				orderModel.setGoodsCostSum(rs.getString("ORDERGOODS_COST_SUM"));
				orderModel.setOrderNote(rs.getString("ORDER_NOTE"));
				
				
				
				
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
	public Vector selectByOrderId(String orderId) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Vector vc = new Vector();
		try {
			
			conn = getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ORDERGOODS_ID, ORDER_ID,GOODS_ID, (SELECT GOODS_NAME FROM GOODS_TB WHERE GOODS_TB.GOODS_ID = A.GOODS_ID) AS GNAME, ");
			sql.append("GOODSTYPE_ID, (SELECT GOODSTYPE_NAME FROM GOODSTYPE_TB WHERE GOODSTYPE_TB.GOODSTYPE_ID = A.GOODSTYPE_ID) AS GTNAME, ");
			sql.append("GOODSBRAND_ID, (SELECT GOODSBRAND_NAME FROM GOODSBRAND_TB WHERE GOODSBRAND_TB.GOODSBRAND_ID = A.GOODSBRAND_ID) AS GBNAME, ");
			sql.append("GOODSSUB, ORDERGOODS_COST, ORDERGOODS_COST_LABEL, ORDERGOODS_QUANTITY,ORDERGOODS_COST_SUM ");
			sql.append(" FROM ORDERGOODS_TB A ");
			
			if((orderId!=null && orderId.length()>0)){
				sql.append(" WHERE order_id = ? ");
			}
			
			StringBuffer dsql = removeWasteSQL(sql);
			log.info("selectByOrderId " + dsql.toString());
			ps = conn.prepareStatement(dsql.toString());
			int index = 0;
			if((orderId!=null && orderId.length()>0)){
				ps.setString(++index,orderId);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				OrderGoods order = new OrderGoods();
				order.setOrdergoodsId(rs.getString("ORDERGOODS_ID"));
				order.setOrderId(rs.getString("ORDER_ID"));
				order.setGoodsId(rs.getString("GOODS_ID"));
				order.setGoodsName(rs.getString("GNAME"));
				order.setGoodstypeId(rs.getString("GOODSTYPE_ID"));
				order.setGoodsTypeName(rs.getString("GTNAME"));
				order.setGoodsbrandId(rs.getString("GOODSBRAND_ID"));
				order.setGoodsBrand(rs.getString("GBNAME"));
				order.setGoodssub(rs.getString("GOODSSUB"));
				order.setOrdergoodsCost(rs.getString("ORDERGOODS_COST"));
				order.setOrdergoodsCostLabel(rs.getString("ORDERGOODS_COST_LABEL"));
				order.setOrdergoodsQuantity(rs.getString("ORDERGOODS_QUANTITY"));
				order.setOrdergoodsCostSum(rs.getString("ORDERGOODS_COST_SUM"));
								
				vc.add(order);
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

	@Override
	public Vector selectOrderReport(String orderNo, String fromDt, String toDt, String vendorId, String SalesManId
			, String goodsId, String goodsTypeId, String goodsBrandId, String goodsSub) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Vector vc = new Vector();
		try {
			
			conn = getConnection();
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT A.ORDER_ID, A.ORDER_NO, A.ORDER_DATE, (SELECT SALESMAN_NAME FROM SALESMAN_TB WHERE SALESMAN_TB.SALESMAN_ID = A.SALESMAN_ID) AS SNAME, ");
			sql.append("(SELECT VENDOR_NAME FROM VENDOR_TB WHERE VENDOR_TB.VENDOR_ID = A.VENDOR_ID) AS VNAME, ");
			sql.append(" SUM(B.ORDERGOODS_QUANTITY) AS QUANTITY, SUM(B.ORDERGOODS_COST_SUM) AS COSTSUM ");
			sql.append(" FROM ORDER_TB A, ORDERGOODS_TB B WHERE A.ORDER_ID = B.ORDER_ID ");
			
			
			if((orderNo!=null && orderNo.length()>0)){
				sql.append(" AND A.ORDER_NO LIKE ? ");
			}
			if ((fromDt != null && !fromDt.equals("")) && (toDt != null && !toDt.equals(""))){
				sql.append(" AND FORMAT(A.ORDER_DATE,'YYYY/MM/DD') between ? and ? ");
			}
			if((vendorId!=null && vendorId.length()>0)){
				sql.append(" AND A.VENDOR_ID = ? ");
			}
			if((SalesManId!=null && SalesManId.length()>0)){
				sql.append(" AND A.SALESMAN_ID = ? ");
			}
			if((goodsId!=null && goodsId.length()>0)){
				sql.append(" AND B.GOODS_ID = ? ");
			}
			if((goodsTypeId!=null && goodsTypeId.length()>0)){
				sql.append(" AND B.GOODSTYPE_ID = ? ");
			}
			if((goodsBrandId!=null && goodsBrandId.length()>0)){
				sql.append(" AND B.GOODSBRAND_ID = ? ");
			}
			if((goodsSub!=null && goodsSub.length()>0)){
				sql.append(" AND B.GOODSSUB LIKE ? ");
			}
			sql.append(" GROUP BY A.ORDER_ID,B.ORDER_ID, A.ORDER_NO, A.ORDER_DATE, A.SALESMAN_ID, A.VENDOR_ID ORDER BY A.ORDER_ID DESC");
			
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
			if((SalesManId!=null && SalesManId.length()>0)){
				ps.setString(++index, SalesManId);
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
				OrderbuyModel orderModel = new OrderbuyModel();
				orderModel.setOrderId(rs.getString("ORDER_ID"));
				orderModel.setOrderNo(rs.getString("ORDER_NO"));
				orderModel.setOrderDate(rs.getString("ORDER_DATE"));
				orderModel.setVendorName(rs.getString("VNAME"));
				orderModel.setSalesmanName(rs.getString("SNAME"));
				orderModel.setOrderQuantity(rs.getString("QUANTITY"));
				orderModel.setGoodsCostSum(rs.getString("COSTSUM"));
				
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
	
	@Override
	public Vector selectOrderReport2(String orderNo, String fromDt, String toDt, String vendorId, String SalesManId
			, String goodsId, String goodsTypeId, String goodsBrandId, String goodsSub) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Vector vc = new Vector();
		try {
			
			conn = getConnection();
			StringBuffer sql = new StringBuffer();
			 
			sql.append("SELECT ");
			sql.append("(SELECT GOODS_NAME FROM GOODS_TB WHERE GOODS_ID=OG.GOODS_ID) AS GNAME, ");
			sql.append("(SELECT GOODSTYPE_NAME FROM GOODSTYPE_TB WHERE GOODSTYPE_ID = OG.GOODSTYPE_ID) AS GTYPE, ");
			sql.append("(SELECT GOODSBRAND_NAME FROM GOODSBRAND_TB WHERE GOODSBRAND_ID = OG.GOODSBRAND_ID) AS GBRAND, ");
			sql.append("OG.GOODSSUB, ");
			sql.append("OG.ORDERGOODS_COST, ");
			sql.append("OG.ORDERGOODS_COST_LABEL, ");
			sql.append("OG.ORDERGOODS_QUANTITY, ");
			sql.append("OG.ORDERGOODS_COST_SUM,");
			sql.append("(SELECT VENDOR_NAME FROM VENDOR_TB WHERE VENDOR_ID = O.VENDOR_ID) AS VNAME, ");
			sql.append("(SELECT SALESMAN_NAME FROM SALESMAN_TB WHERE SALESMAN_ID = O.SALESMAN_ID) AS SNAME, ");
			sql.append("O.ORDER_NO, ");
			sql.append("O.ORDER_DATE, ");
			sql.append("O.ORDER_NOTE, ");
			sql.append("O.ORDER_QUANTITY, ");
			sql.append("O.ORDER_COST ");
			sql.append("FROM ORDERGOODS_TB OG, ORDER_TB O ");
			sql.append("WHERE OG.ORDER_ID=O.ORDER_ID ");
			
			if((orderNo!=null && orderNo.length()>0)){
				sql.append(" AND O.ORDER_NO LIKE ? ");
			}
			if ((fromDt != null && !fromDt.equals("")) && (toDt != null && !toDt.equals(""))){
				sql.append(" AND FORMAT(O.ORDER_DATE,'YYYY/MM/DD') between ? and ? ");
			}
			if((vendorId!=null && vendorId.length()>0)){
				sql.append(" AND O.VENDOR_ID = ? ");
			}
			if((SalesManId!=null && SalesManId.length()>0)){
				sql.append(" AND O.SALESMAN_ID = ? ");
			}
			if((goodsId!=null && goodsId.length()>0)){
				sql.append(" AND OG.GOODS_ID = ? ");
			}
			if((goodsTypeId!=null && goodsTypeId.length()>0)){
				sql.append(" AND OG.GOODSTYPE_ID = ? ");
			}
			if((goodsBrandId!=null && goodsBrandId.length()>0)){
				sql.append(" AND OG.GOODSBRAND_ID = ? ");
			}
			if((goodsSub!=null && goodsSub.length()>0)){
				sql.append(" AND OG.GOODSSUB LIKE ? ");
			}
			sql.append(" ORDER BY O.ORDER_NO ");
			
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
			if((SalesManId!=null && SalesManId.length()>0)){
				ps.setString(++index, SalesManId);
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
				
				OrderReportModel orderModel = new OrderReportModel();
				orderModel.setOrderNo(rs.getString("ORDER_NO"));
				orderModel.setGoodsName(rs.getString("GNAME"));
				orderModel.setGoodsType(rs.getString("GTYPE"));
				orderModel.setGoodsBrand(rs.getString("GBRAND"));
				orderModel.setGoodsSub(rs.getString("GOODSSUB"));
				orderModel.setGoodsCost(Double.parseDouble(rs.getString("ORDERGOODS_COST")));
				orderModel.setGoodsCostLabel(Double.parseDouble(rs.getString("ORDERGOODS_COST_LABEL")));
				orderModel.setGoodsQuantity(Integer.parseInt(rs.getString("ORDERGOODS_QUANTITY")));
				orderModel.setGoodsCostSum(Double.parseDouble(rs.getString("ORDERGOODS_COST_SUM")));
				orderModel.setVendorName(rs.getString("VNAME"));
				orderModel.setSalemanName(rs.getString("SNAME"));
				orderModel.setOrderDate(rs.getString("ORDER_DATE"));
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

	@Override
	public Vector selectRemainReport(String orderNo, String fromDt,
			String toDt, String vendorId,String goodsId,
			String goodsTypeId, String goodsBrandId, String goodsSub) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Vector vc = new Vector();
		try {
			
			conn = getConnection();
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT A.ORDER_ID, A.ORDER_DATE, A.ORDER_NO, ");
			sql.append("(SELECT VENDOR_NAME FROM VENDOR_TB WHERE VENDOR_TB.VENDOR_ID = A.VENDOR_ID) AS VNAME, ");
			sql.append("(SELECT GOODS_NAME FROM GOODS_TB WHERE GOODS_TB.GOODS_ID = B.GOODS_ID) AS GNAME, ");
			sql.append("(SELECT GOODSTYPE_NAME FROM GOODSTYPE_TB WHERE GOODSTYPE_TB.GOODSTYPE_ID = B.GOODSTYPE_ID) AS GTNAME, ");
			sql.append("(SELECT GOODSBRAND_NAME FROM GOODSBRAND_TB WHERE GOODSBRAND_TB.GOODSBRAND_ID = B.GOODSBRAND_ID) AS GBNAME, ");
			sql.append(" B.GOODSSUB, B.ORDERGOODS_QUANTITY, C.SELL_QUANTITY, C.STOCK_QUANTITY FROM ORDER_TB A, ORDERGOODS_TB B, STOCK_TB C ");
			sql.append(" WHERE A.ORDER_ID = B.ORDER_ID AND B.ORDERGOODS_ID = C.ORDERGOODS_ID AND C.STOCK_QUANTITY > 0  ");
			
			
			if((orderNo!=null && orderNo.length()>0)){
				sql.append(" AND A.ORDER_NO LIKE ? ");
			}
			if ((fromDt != null && !fromDt.equals("")) && (toDt != null && !toDt.equals(""))){
				sql.append(" AND FORMAT(A.ORDER_DATE,'YYYY/MM/DD') between ? and ? ");
			}
			if((vendorId!=null && vendorId.length()>0)){
				sql.append(" AND A.VENDOR_ID = ? ");
			}
			if((goodsId!=null && goodsId.length()>0)){
				sql.append(" AND B.GOODS_ID = ? ");
			}
			if((goodsTypeId!=null && goodsTypeId.length()>0)){
				sql.append(" AND B.GOODSTYPE_ID = ? ");
			}
			if((goodsBrandId!=null && goodsBrandId.length()>0)){
				sql.append(" AND B.GOODSBRAND_ID = ? ");
			}
			if((goodsSub!=null && goodsSub.length()>0)){
				sql.append(" AND B.GOODSSUB LIKE ? ");
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
				OrderbuyModel orderModel = new OrderbuyModel();
				orderModel.setOrderId(rs.getString("ORDER_ID"));
				orderModel.setOrderNo(rs.getString("ORDER_NO"));
				orderModel.setOrderDate(rs.getString("ORDER_DATE"));
				orderModel.setVendorName(rs.getString("VNAME"));
				orderModel.setGoodsName(rs.getString("GNAME"));
				orderModel.setGoodsType(rs.getString("GTNAME"));
				orderModel.setGoodsBrand(rs.getString("GBNAME"));
				orderModel.setGoodsSub(rs.getString("GOODSSUB"));
				orderModel.setOrderQuantity(rs.getString("ORDERGOODS_QUANTITY"));
				orderModel.setSellQuantity(rs.getString("SELL_QUANTITY"));
				orderModel.setStockQuantity(rs.getString("STOCK_QUANTITY"));
				
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

	@Override
	public Vector selectRemainReport2(String orderNo, String fromDt,
			String toDt, String vendorId, String goodsId, String goodsTypeId,
			String goodsBrandId, String goodsSub) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Vector vc = new Vector();
		try {
			
			conn = getConnection();
			StringBuffer sql = new StringBuffer();
			 
			sql.append("SELECT ");
			sql.append("(SELECT GOODS_NAME FROM GOODS_TB WHERE GOODS_ID=OG.GOODS_ID) AS GNAME, ");
			sql.append("(SELECT GOODSTYPE_NAME FROM GOODSTYPE_TB WHERE GOODSTYPE_ID = OG.GOODSTYPE_ID) AS GTYPE, ");
			sql.append("(SELECT GOODSBRAND_NAME FROM GOODSBRAND_TB WHERE GOODSBRAND_ID = OG.GOODSBRAND_ID) AS GBRAND, ");
			sql.append("OG.GOODSSUB, ");
			sql.append("OG.ORDERGOODS_COST, ");
			sql.append("OG.ORDERGOODS_COST_LABEL, ");
			sql.append("OG.ORDERGOODS_QUANTITY, ");
			sql.append("OG.ORDERGOODS_COST_SUM,");
			sql.append("(SELECT VENDOR_NAME FROM VENDOR_TB WHERE VENDOR_ID = O.VENDOR_ID) AS VNAME, ");
			sql.append("(SELECT SALESMAN_NAME FROM SALESMAN_TB WHERE SALESMAN_ID = O.SALESMAN_ID) AS SNAME, ");
			sql.append("O.ORDER_NO, ");
			sql.append("O.ORDER_DATE, ");
			sql.append("O.ORDER_NOTE, ");
			sql.append("O.ORDER_QUANTITY, ");
			sql.append("O.ORDER_COST, ");
			sql.append("ST.ORDERGOODS_QUANTITY, ST.SELL_QUANTITY, ST.STOCK_QUANTITY ");
			sql.append("FROM ORDERGOODS_TB OG, ORDER_TB O , STOCK_TB ST ");
			sql.append("WHERE OG.ORDER_ID=O.ORDER_ID  AND OG.ORDERGOODS_ID = ST.ORDERGOODS_ID AND ST.STOCK_QUANTITY > 0  ");
			
			if((orderNo!=null && orderNo.length()>0)){
				sql.append(" AND O.ORDER_NO LIKE ? ");
			}
			if ((fromDt != null && !fromDt.equals("")) && (toDt != null && !toDt.equals(""))){
				sql.append(" AND FORMAT(O.ORDER_DATE,'YYYY/MM/DD') between ? and ? ");
			}
			if((vendorId!=null && vendorId.length()>0)){
				sql.append(" AND O.VENDOR_ID = ? ");
			}
			if((goodsId!=null && goodsId.length()>0)){
				sql.append(" AND OG.GOODS_ID = ? ");
			}
			if((goodsTypeId!=null && goodsTypeId.length()>0)){
				sql.append(" AND OG.GOODSTYPE_ID = ? ");
			}
			if((goodsBrandId!=null && goodsBrandId.length()>0)){
				sql.append(" AND OG.GOODSBRAND_ID = ? ");
			}
			if((goodsSub!=null && goodsSub.length()>0)){
				sql.append(" AND OG.GOODSSUB LIKE ? ");
			}
			sql.append(" ORDER BY O.ORDER_NO ");
			
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
				
				OrderReportModel orderModel = new OrderReportModel();
				orderModel.setOrderNo(rs.getString("ORDER_NO"));
				orderModel.setGoodsName(rs.getString("GNAME"));
				orderModel.setGoodsType(rs.getString("GTYPE"));
				orderModel.setGoodsBrand(rs.getString("GBRAND"));
				orderModel.setGoodsSub(rs.getString("GOODSSUB"));
				orderModel.setGoodsCost(Double.parseDouble(rs.getString("ORDERGOODS_COST")));
				orderModel.setGoodsCostLabel(Double.parseDouble(rs.getString("ORDERGOODS_COST_LABEL")));
				orderModel.setGoodsQuantity(Integer.parseInt(rs.getString("ORDERGOODS_QUANTITY")));
				orderModel.setGoodsCostSum(Double.parseDouble(rs.getString("ORDERGOODS_COST_SUM")));
				orderModel.setVendorName(rs.getString("VNAME"));
				orderModel.setSalemanName(rs.getString("SNAME"));
				orderModel.setOrderDate(rs.getString("ORDER_DATE"));
				orderModel.setFromDate(fromDt);
				orderModel.setToDate(toDt);
				orderModel.setStockGoodsQty(orderModel.getGoodsQuantity());
				orderModel.setStockSellQty(Integer.parseInt(rs.getString("SELL_QUANTITY")));
				orderModel.setStockQty(Integer.parseInt(rs.getString("STOCK_QUANTITY")));
				
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
