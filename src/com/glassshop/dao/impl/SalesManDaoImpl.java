package com.glassshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.glassshop.dao.SalesManDao;
import com.glassshop.model.SalesMan;

public class SalesManDaoImpl extends JdbcDaoSupport implements SalesManDao {

	static Logger log = Logger.getLogger(SalesManDaoImpl.class.getName());

	@Override
	public SalesMan selectById(String id) {
		String sql = "SELECT SALESMAN_ID, V.VENDOR_ID, V.VENDOR_NAME, V.VENDOR_TEL, SALESMAN_NAME, SALESMAN_TEL "
				+ "FROM SALESMAN_TB S INNER JOIN VENDOR_TB V ON S.VENDOR_ID = V.VENDOR_ID  "
				+ "WHERE SALESMAN_ID = ? ";
		log.info(sql);
		SalesMan salesMan = (SalesMan) getJdbcTemplate().queryForObject(sql,
				new Object[] { id }, new BeanPropertyRowMapper(SalesMan.class));
		return salesMan;
	}

	@Override
	public List<SalesMan> selectAll() {
		String sql = "SELECT SALESMAN_ID, V.VENDOR_ID, V.VENDOR_NAME, V.VENDOR_TEL, SALESMAN_NAME, SALESMAN_TEL FROM SALESMAN_TB S INNER JOIN VENDOR_TB V ON S.VENDOR_ID = V.VENDOR_ID ORDER BY SALESMAN_ID ";
		log.info(sql);
		List<SalesMan> salesMan = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(SalesMan.class));
		return salesMan;
	}

	@Override
	public int insert(SalesMan salesMan) {
		String sql = "INSERT INTO SALESMAN_TB (VENDOR_ID, SALESMAN_NAME, SALESMAN_TEL) VALUES (?, ?, ?)";
		log.info(sql);
		int result = getJdbcTemplate()
				.update(sql,
						new Object[] { salesMan.getVendorId(),
								salesMan.getSalesmanName(),
								salesMan.getSalesmanTel() });
		return result;
	}

	@Override
	public int update(SalesMan salesMan) {
		String sql = "UPDATE SALESMAN_TB SET VENDOR_ID = ?, SALESMAN_NAME = ?, SALESMAN_TEL = ? WHERE SALESMAN_ID = ?";
		log.info(sql);
		int result = getJdbcTemplate()
				.update(sql,
						new Object[] { salesMan.getVendorId(),
								salesMan.getSalesmanName(),
								salesMan.getSalesmanTel(),
								salesMan.getSalesmanId()});
		return result;
	}

	@Override
	public int delete(String id) {
		String sql = "DELETE FROM SALESMAN_TB WHERE SALESMAN_ID = ?";
		log.info(sql);
		int result = getJdbcTemplate().update(sql, new Object[] {id});
		return result;
	}
	public Vector selectFillter(String vendorId, String saleName, String saleTel) {

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Vector vc = new Vector();
		try {
			
			conn = getConnection();
			StringBuffer sql = new StringBuffer("SELECT SALESMAN_ID, V.VENDOR_ID, V.VENDOR_NAME, V.VENDOR_TEL, SALESMAN_NAME, SALESMAN_TEL FROM SALESMAN_TB S INNER JOIN VENDOR_TB V ON S.VENDOR_ID = V.VENDOR_ID ");
		
			if ((null!=vendorId && vendorId.length()>0) || (null!=saleName && saleName.length()>0) || (null!=saleTel && saleTel.length()>0)){
				sql.append(" WHERE ");
			}
			
			if (vendorId.length()>0){
				sql.append(" V.VENDOR_ID LIKE ? AND ");
			}
			if (saleName.length()>0){
				sql.append(" SALESMAN_NAME LIKE ? AND ");
			}
			if (saleTel.length()>0){
				sql.append(" SALESMAN_TEL LIKE ? AND ");
			}
			
			StringBuffer dsql = removeWasteSQL(sql);
			dsql.append(" ORDER BY SALESMAN_ID ");
			
			log.info("selectFillter " + dsql.toString());
			
			ps = conn.prepareStatement(dsql.toString());
			int index = 0;
			if (vendorId.length()>0){
				ps.setString(++index, "%" + vendorId.toUpperCase() + "%");
			}
			if (saleName.length()>0){
				ps.setString(++index, "%" + saleName.toUpperCase() + "%");
			}
			if (saleTel.length()>0){
				ps.setString(++index, "%" + saleTel.toUpperCase() + "%");
			}
			
			rs = ps.executeQuery();
			while (rs.next()) {
				SalesMan sm = new SalesMan();
				sm.setSalesmanId(rs.getString("SALESMAN_ID"));
				sm.setSalesmanName(rs.getString("SALESMAN_NAME"));
				sm.setSalesmanTel(rs.getString("SALESMAN_TEL"));
				sm.setVendorId(rs.getString("VENDOR_ID"));
				sm.setVendorName(rs.getString("VENDOR_NAME"));
				sm.setVendorTel(rs.getString("VENDOR_TEL"));
				vc.add(sm);
			}
			
		log.info(sql.toString());

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
	public List<SalesMan> selectSalesNameAll() {
		String sql = "SELECT SALESMAN_ID, SALESMAN_NAME, SALESMAN_TEL FROM SALESMAN_TB ORDER BY SALESMAN_NAME ";
		log.info(sql);
		List<SalesMan> salesMan = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(SalesMan.class));
		return salesMan;
	}

	@Override
	public Vector selectSalesNameByVendor(String id) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Vector vc = new Vector();
		try {
			
			conn = getConnection();
			StringBuffer sql = new StringBuffer();
		
			sql.append("SELECT SALESMAN_ID, SALESMAN_NAME, SALESMAN_TEL FROM SALESMAN_TB WHERE VENDOR_ID = ? ORDER BY SALESMAN_NAME ")	;
			log.info(sql.toString());
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1,id); 
					
			rs = ps.executeQuery();
			while (rs.next()) {
				SalesMan sm = new SalesMan();
				sm.setSalesmanId(rs.getString("SALESMAN_ID"));
				sm.setSalesmanName(rs.getString("SALESMAN_NAME"));
				sm.setSalesmanTel(rs.getString("SALESMAN_TEL"));
				vc.add(sm);
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
