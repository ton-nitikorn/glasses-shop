package com.glassshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.glassshop.dao.GoodsTypeDao;
import com.glassshop.model.Goods;
import com.glassshop.model.GoodsType;

public class GoodsTypeDaoImpl extends JdbcDaoSupport implements GoodsTypeDao {
	static Logger log = Logger.getLogger(GoodsTypeDaoImpl.class.getName());
	@Override
	public GoodsType selectById(String id) {
		String sql = "SELECT GOODSTYPE_ID, GOODS_ID, GOODSTYPE_NAME FROM GOODSTYPE_TB WHERE GOODSTYPE_ID = ?";
		log.info(sql);
		GoodsType goodsType = (GoodsType)getJdbcTemplate().queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper(GoodsType.class));
		return goodsType;
	}

	@Override
	public List<GoodsType> selectAll() {
		String sql = "SELECT GOODSTYPE_ID, GT.GOODS_ID, GOODS_NAME, GOODSTYPE_NAME FROM GOODSTYPE_TB GT LEFT JOIN GOODS_TB G ON G.GOODS_ID = GT.GOODS_ID ";
		log.info(sql);
		List<GoodsType> goodsType = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(GoodsType.class));
		return goodsType;
	}

	@Override
	public int insert(GoodsType goodsType) {
		String sql = "INSERT INTO GOODSTYPE_TB (GOODS_ID, GOODSTYPE_NAME) VALUES (?, ?)";
		log.info(sql);
		int result = getJdbcTemplate().update(sql, 
				new Object[] { 
				goodsType.getGoodsId(),
				goodsType.getGoodstypeName()
				});
		return result;
	}

	@Override
	public int update(GoodsType goodsType) {
		String sql = "UPDATE GOODSTYPE_TB SET GOODS_ID = ?, GOODSTYPE_NAME = ?  WHERE GOODSTYPE_ID = ?";
		log.info(sql);
		int result = getJdbcTemplate().update(sql, 
				new Object[] { 
				goodsType.getGoodsId(),
				goodsType.getGoodstypeName(),
				goodsType.getGoodstypeId()
				});
		return result;
	}

	@Override
	public int delete(String id) {
		String sql = "DELETE FROM GOODSTYPE_TB WHERE GOODSTYPE_ID = ?";
		log.info(sql);
		int result = getJdbcTemplate().update(sql, new Object[] {id});
		return result;
	}

	@Override
	public List<GoodsType> selectComboAll() {
		String sql = "SELECT GOODSTYPE_ID, GOODSTYPE_NAME FROM GOODSTYPE_TB ORDER BY GOODSTYPE_NAME";
		log.info(sql);
		List<GoodsType> goodsType = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(GoodsType.class));
		return goodsType;
	}

	@Override
	public Vector selectComboByGood(String id) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Vector vc = new Vector();
		try {
			
			conn = getConnection();
			StringBuffer sql = new StringBuffer();
		
			sql.append("SELECT GOODSTYPE_ID, GOODSTYPE_NAME FROM GOODSTYPE_TB WHERE GOODS_ID = ? ORDER BY GOODSTYPE_NAME ")	;
			log.info(sql.toString());
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1,id); 
					
			rs = ps.executeQuery();
			while (rs.next()) {
				GoodsType good = new GoodsType();
				good.setGoodstypeId(rs.getString("GOODSTYPE_ID"));
				good.setGoodstypeName(rs.getString("GOODSTYPE_NAME"));
				vc.add(good);
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
