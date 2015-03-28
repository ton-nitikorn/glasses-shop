package com.glassshop.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.glassshop.dao.CustomerSightDao;
import com.glassshop.model.CustomerSight;
import com.glassshop.model.Vendor;

public class CustomerSightDaoImpl extends JdbcDaoSupport implements
		CustomerSightDao {
	static Logger log = Logger.getLogger(CustomerSightDaoImpl.class.getName());
	@Override
	public CustomerSight selectById(String id) {
		String sql = "SELECT * FROM VENDOR_TB WHERE VENDOR_ID = ?";
		log.info(sql);
		CustomerSight cs = (CustomerSight)getJdbcTemplate().queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper(CustomerSight.class));
		return cs;
	}
	@Override
	public List<CustomerSight> selectByIdAll(String id) {
		String sql = "SELECT SIGHT_ID, CUST_ID,SIGHT_DATE,SIGHT_APPOINT,SIGHT_R_SPH,SIGHT_R_CYL,SIGHT_R_AXIS,SIGHT_R_ADD, "
					+" SIGHT_R_VA,SIGHT_R_PRISM,SIGHT_R_BASE,SIGHT_L_SPH,SIGHT_L_CYL,SIGHT_L_AXIS,SIGHT_L_ADD,SIGHT_L_VA,SIGHT_L_PRISM,SIGHT_L_BASE, "
					+"SIGHT_PD,SIGHT_SEGSH,LENS_SINGLE,LENS_BIFOCAL,LENS_PROGRESS,LENS_LENS_TYPE,LENS_COLOR,LENS_FRAME,LENS_NOTE,LENS_TYPE,SIGHT_FREQUENCY FROM CUSTOMER_SIGHT_TB "
					+ " WHERE CUST_ID = ? ORDER BY SIGHT_ID DESC";	
		log.info(sql);
		List<CustomerSight> cs = getJdbcTemplate().query(sql,new Object[] { Double.valueOf(id) },new BeanPropertyRowMapper(CustomerSight.class));
		return cs;
	}
	@Override
	public List<CustomerSight> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(CustomerSight custSight) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(CustomerSight custSight) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int updateAppointmentDate(String custId, String appointDate) {
		String sql = "UPDATE CUSTOMER_SIGHT_TB SET SIGHT_APPOINT = ? WHERE CUST_ID = ?";
		log.info(sql);
		int result = getJdbcTemplate().update(
				sql,
				new Object[] {appointDate, custId });
		
		return result;
	}

}
