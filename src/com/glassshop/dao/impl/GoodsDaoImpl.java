package com.glassshop.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.glassshop.dao.GoodsDao;
import com.glassshop.model.Goods;
import com.glassshop.model.GoodsBrand;
import com.glassshop.model.Vendor;

public class GoodsDaoImpl extends JdbcDaoSupport implements GoodsDao {
	static Logger log = Logger.getLogger(GoodsDaoImpl.class.getName());
	@Override
	public Goods selectById(String id) {
		String sql = "SELECT GOODS_ID, GOODS_NAME FROM GOODS_TB WHERE GOODS_ID = ?";
		log.info(sql);
		Goods goods = (Goods)getJdbcTemplate().queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper(Goods.class));
		return goods;
	}

	@Override
	public List<Goods> selectAll() {
		String sql = "SELECT GOODS_ID, GOODS_NAME FROM GOODS_TB ";
		log.info(sql);
		List<Goods> goods = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(Goods.class));
		return goods;
	}

	@Override
	public int insert(Goods goods) {
		String sql = "INSERT INTO GOODS_TB (GOODS_NAME) VALUES (?)";
		log.info(sql);
		int result = getJdbcTemplate().update(sql, 
				new Object[] { 
					goods.getGoodsName()
				});
		return result;
	}

	@Override
	public int update(Goods goods) {
		String sql = "UPDATE GOODS_TB SET GOODS_NAME = ?  WHERE GOODS_ID = ?";
		log.info(sql);
		int result = getJdbcTemplate().update(sql, 
				new Object[] { 
					goods.getGoodsName(),
					goods.getGoodsId()
				});
		return result;
	}

	@Override
	public int delete(String id) {
		String sql = "DELETE FROM GOODS_TB WHERE GOODS_ID = ?";
		log.info(sql);
		int result = getJdbcTemplate().update(sql, new Object[] {id});
		return result;
	}

	@Override
	public List<Goods> selectGoodNameAll() {
		String sql = "SELECT GOODS_ID, GOODS_NAME FROM GOODS_TB ORDER BY GOODS_NAME ";
		log.info(sql);
		List<Goods> goods = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(Goods.class));
		return goods;
	}

}
