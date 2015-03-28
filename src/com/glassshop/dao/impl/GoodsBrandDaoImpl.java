package com.glassshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.glassshop.dao.GoodsBrandDao;
import com.glassshop.model.GoodsBrand;
import com.glassshop.model.SalesMan;

public class GoodsBrandDaoImpl extends JdbcDaoSupport implements GoodsBrandDao {

	static Logger log = Logger.getLogger(GoodsBrandDaoImpl.class.getName());
	@Override
	public GoodsBrand selectById(String id) {
		String sql = "SELECT GOODSBRAND_ID, GOODSBRAND_NAME FROM GOODSBRAND_TB WHERE GOODSBRAND_ID = ?";
		log.info(sql);
		GoodsBrand goodsBrand = (GoodsBrand)getJdbcTemplate().queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper(GoodsBrand.class));
		return goodsBrand;
	}

	@Override
	public List<GoodsBrand> selectAll() {
		String sql = "SELECT GOODSBRAND_ID, GOODSBRAND_NAME FROM GOODSBRAND_TB ";
		log.info(sql);
		List<GoodsBrand> goodsBrand = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(GoodsBrand.class));
		return goodsBrand;
	}

	@Override
	public int insert(GoodsBrand goodsBrand) {
		String sql = "INSERT INTO GOODSBRAND_TB (GOODSBRAND_NAME) VALUES (?)";
		log.info(sql);
		int result = getJdbcTemplate().update(sql, 
				new Object[] { 
				goodsBrand.getGoodsbrandName()
				});
		return result;
	}

	@Override
	public int update(GoodsBrand goodsBrand) {
		String sql = "UPDATE GOODSBRAND_TB SET GOODSBRAND_NAME = ?  WHERE GOODSBRAND_ID = ?";
		log.info(sql);
		int result = getJdbcTemplate().update(sql, 
				new Object[] { 
				goodsBrand.getGoodsbrandName(),
				goodsBrand.getGoodsbrandId()
				});
		return result;
	}

	@Override
	public int delete(String id) {
		String sql = "DELETE FROM GOODSBRAND_TB WHERE GOODSBRAND_ID = ?";
		log.info(sql);
		int result = getJdbcTemplate().update(sql, new Object[] {id});
		return result;
	}

	@Override
	public List<GoodsBrand> selectBrandNameAll() {
		String sql = "SELECT GOODSBRAND_ID, GOODSBRAND_NAME FROM GOODSBRAND_TB ORDER BY GOODSBRAND_NAME";
		log.info(sql);
		List<GoodsBrand> goodsBrand = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(GoodsBrand.class));
		return goodsBrand;
	}

	

}
