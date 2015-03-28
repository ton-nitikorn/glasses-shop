package com.glassshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.glassshop.dao.UserDao;
import com.glassshop.model.Goods;
import com.glassshop.model.UserModel;
import com.glassshop.model.Vendor;

public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	static Logger log = Logger.getLogger(GoodsDaoImpl.class.getName());
	
	@Override
	public boolean selectPassword(String password) {
		boolean isPassword= false;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			
			conn = getConnection();
			String sql = "SELECT PASSWORD_NAME FROM PASSWORD_TB WHERE PASSWORD_NAME = ?";
			log.info(sql);
			ps = conn.prepareStatement(sql.toString());
			
			ps.setString(1,password);
			
			rs = ps.executeQuery();
			
			if (rs.next()){
				isPassword = true;
			}
			log.info("password = " + isPassword);
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
			return isPassword;
	}

	@Override
	public int changePassword(String password) {
			String sql = "UPDATE PASSWORD_TB SET PASSWORD_NAME = ? WHERE PASSWORD_ID = 1 ";
			log.info(sql);
			int result = getJdbcTemplate().update(sql, 
					new Object[] { 
					password
					});
			return result;
		}

	@Override
	public UserModel selectById(String id) {
		String sql = "SELECT * FROM PASSWORD_TB";
		log.info(sql);
		UserModel user = (UserModel)getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper(UserModel.class));
		return user;
	}
	

}
