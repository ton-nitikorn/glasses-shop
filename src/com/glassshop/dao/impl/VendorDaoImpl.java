package com.glassshop.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.glassshop.dao.VendorDao;
import com.glassshop.model.Vendor;

public class VendorDaoImpl extends JdbcDaoSupport implements VendorDao{
	
	static Logger log = Logger.getLogger(VendorDaoImpl.class.getName());

	public Vendor selectById(String id) {
		String sql = "SELECT * FROM VENDOR_TB WHERE VENDOR_ID = ?";
		log.info(sql);
		Vendor vendor = (Vendor)getJdbcTemplate().queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper(Vendor.class));
		return vendor;
	}

	public List<Vendor> selectAll() {
		String sql = "SELECT * FROM VENDOR_TB";	
		log.info(sql);
		List<Vendor> vendors = getJdbcTemplate().query(sql,new BeanPropertyRowMapper(Vendor.class));
		return vendors;
	}

	public int insert(Vendor vendor) {
		String sql = "INSERT INTO VENDOR_TB (VENDOR_NAME, VENDOR_TEL) VALUES (?, ?)";
		log.info(sql);
		int result = getJdbcTemplate().update(sql, 
				new Object[] { 
					vendor.getVendorName(),
					vendor.getVendorTel() 
				});
		return result;
	}

	public int update(Vendor vendor) {
		String sql = "UPDATE VENDOR_TB SET VENDOR_NAME = ? , VENDOR_TEL = ? WHERE VENDOR_ID = ?";
		log.info(sql);
		int result = getJdbcTemplate().update(sql, 
				new Object[] { 
					vendor.getVendorName(),
					vendor.getVendorTel(),
					vendor.getVendorId()
				});
		return result;
	}

	public int delete(String id) {
		String sql = "DELETE FROM VENDOR_TB WHERE VENDOR_ID = ?";
		log.info(sql);
		int result = getJdbcTemplate().update(sql, new Object[] {id});
		return result;
	}

	public List<Vendor> selectFillter(String vendorName, String vendorTel) {
	log.info("vendorName "+vendorName + ":"+vendorTel);
	StringBuffer sql = new StringBuffer("SELECT * FROM VENDOR_TB ");
	List<Vendor> vendors = null;
	
	if((vendorName != null && !vendorName.equals("")) && (vendorTel != null && !vendorTel.equals(""))){
		sql.append(" WHERE VENDOR_NAME LIKE ? AND VENDOR_TEL LIKE ?");
		vendors = getJdbcTemplate().query(sql.toString(), new Object[] { "%"+vendorName+"%", "%"+ vendorTel+"%"} ,new BeanPropertyRowMapper(Vendor.class));
	
	}else if(vendorName != null && !vendorName.equals("")){
		sql.append(" WHERE VENDOR_NAME LIKE ?");
		vendors = getJdbcTemplate().query(sql.toString(), new Object[] { "%"+vendorName+"%"} ,new BeanPropertyRowMapper(Vendor.class));
		
	}else if(vendorTel != null && !vendorTel.equals("")){
		sql.append(" WHERE VENDOR_TEL LIKE ?");
		vendors = getJdbcTemplate().query(sql.toString(), new Object[] { "%"+vendorTel+"%"} ,new BeanPropertyRowMapper(Vendor.class));
	
	}
	
	log.info(sql.toString());

	return vendors;
}

	@Override
	public List<Vendor> selectNameAll() {
		String sql = "SELECT * FROM VENDOR_TB ORDER BY VENDOR_NAME";	
		log.info(sql);
		List<Vendor> vendors = getJdbcTemplate().query(sql,new BeanPropertyRowMapper(Vendor.class));
		return vendors;
	}
}
