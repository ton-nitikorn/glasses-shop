package com.glassshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.glassshop.dao.CustomerDao;
import com.glassshop.model.Customer;
import com.glassshop.model.CustomerSight;

public class CustomerDaoImpl extends JdbcDaoSupport implements CustomerDao {

	static Logger log = Logger.getLogger(CustomerDaoImpl.class.getName());

	@Override
	public Customer selectById(String id) {
		String sql = "SELECT CUST_ID, CUST_NO, CUST_RECORD_DATE, CUST_NEWS, CUST_NAME, CUST_SURNAME, CUST_PLACE, CUST_ADDRESS, CUST_SOI, CUST_ROAD "
				+ ", CUST_DISTRICT, CUST_CITY, CUST_PROVINCE, CUST_ZIPCODE, CUST_TEL, CUST_MOBILE, CUST_BIRTHDAY, CUST_TARGET "
				+ " FROM CUSTOMER_TB WHERE CUST_ID = ?";
		log.info(sql);
		Customer customer = (Customer) getJdbcTemplate().queryForObject(sql,
				new Object[] { id }, new BeanPropertyRowMapper(Customer.class));
		return customer;
	}

	@Override
	public List<Customer> selectAll() {
		String sql = "SELECT CUST_ID, CUST_NO, CUST_RECORD_DATE, CUST_NEWS, CUST_NAME, CUST_SURNAME, CUST_PLACE, CUST_ADDRESS, CUST_SOI, CUST_ROAD "
				+ ", CUST_DISTRICT, CUST_CITY, CUST_PROVINCE, CUST_ZIPCODE, CUST_TEL, CUST_MOBILE, CUST_BIRTHDAY, CUST_TARGET FROM CUSTOMER_TB "
				+ " ORDER BY CUST_ID ";
		
		log.info(sql);
		List<Customer> customer = getJdbcTemplate().query(sql,
				new BeanPropertyRowMapper(Customer.class));
		return customer;
	}

	@Override
	public List<Customer> selectPrintReportAll() {
		String sql = "SELECT CUST_ID, CUST_NO, CUST_RECORD_DATE, CUST_NEWS, CUST_TITLE, CUST_NAME, CUST_SURNAME, CUST_PLACE, CUST_ADDRESS, CUST_SOI, CUST_ROAD "
			+ ", CUST_DISTRICT, CUST_CITY, CUST_PROVINCE, CUST_ZIPCODE, CUST_TEL, CUST_MOBILE, CUST_BIRTHDAY, CUST_TARGET FROM CUSTOMER_TB "
			+ " WHERE CUST_NAME <> '' ORDER BY CUST_NAME ";
	
	log.info(sql);
	List<Customer> customer = getJdbcTemplate().query(sql,
			new BeanPropertyRowMapper(Customer.class));
	return customer;
	}
	
	@Override
	public int insert(Customer customer) {
		String sql = "INSERT INTO CUSTOMER_TB (CUST_NO, CUST_RECORD_DATE, CUST_NEWS, CUST_NAME, CUST_SURNAME, CUST_PLACE, CUST_ADDRESS, CUST_SOI, CUST_ROAD "
				+ ", CUST_DISTRICT, CUST_CITY, CUST_PROVINCE, CUST_ZIPCODE, CUST_TEL, CUST_MOBILE, CUST_BIRTHDAY, CUST_TARGET) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		log.info(sql);

		int result = getJdbcTemplate().update(
				sql,
				new Object[] { customer.getCustNo(),
						customer.getCustRecordDate(), customer.getCustNews(),
						customer.getCustName(), customer.getCustSurname(),
						customer.getCustPlace(), customer.getCustAddress(),
						customer.getCustSoi(), customer.getCustRoad(),
						customer.getCustDistrict(), customer.getCustCity(),
						customer.getCustProvince(), customer.getCustZipcode(),
						customer.getCustTel(), customer.getCustMobile(),
						customer.getCustBirthday(), customer.getCustTarget() });
		return result;
	}
	@Override
	public int insertSight(CustomerSight customerSight) {
		String sql = "Insert into CUSTOMER_SIGHT_TB (CUST_ID,SIGHT_DATE,SIGHT_APPOINT,SIGHT_R_SPH,SIGHT_R_CYL,SIGHT_R_AXIS,SIGHT_R_ADD, " +
				" SIGHT_R_VA,SIGHT_R_PRISM,SIGHT_R_BASE,SIGHT_L_SPH,SIGHT_L_CYL,SIGHT_L_AXIS,SIGHT_L_ADD,SIGHT_L_VA,SIGHT_L_PRISM,SIGHT_L_BASE," +
				"SIGHT_PD,SIGHT_SEGSH,LENS_SINGLE,LENS_BIFOCAL,LENS_PROGRESS,LENS_LENS_TYPE,LENS_COLOR,LENS_FRAME,LENS_NOTE,LENS_TYPE,SIGHT_FREQUENCY)" +
				" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		log.info(sql);

		int result = getJdbcTemplate().update(
				sql,
				new Object[] { customerSight.getCustId(),
						customerSight.getSightDate(), customerSight.getSightAppoint(),
						customerSight.getSightRSph(), customerSight.getSightRCyl(),
						customerSight.getSightRAxis(), customerSight.getSightRAdd(),
						customerSight.getSightRVa(), customerSight.getSightRPrism(),
						customerSight.getSightRBase(), customerSight.getSightLSph(),
						customerSight.getSightLCyl(), customerSight.getSightLAxis(),
						customerSight.getSightLAdd(), customerSight.getSightLVa(),
						customerSight.getSightLPrism(), customerSight.getSightLBase(),
						customerSight.getSightPd(),customerSight.getSightSegsh(),
						customerSight.getLensSingle(),customerSight.getLensBifocal(),
						customerSight.getLensProgress(),customerSight.getLensLensType(),
						customerSight.getLensColor(),customerSight.getLensFrame(),
						customerSight.getLensNote(),customerSight.getLensType(),
						customerSight.getSightFrequency()});
		return result;
	}
	@Override
	public int update(Customer customer) {

		String sql = "UPDATE CUSTOMER_TB SET CUST_NO = ?, CUST_RECORD_DATE = ?, CUST_NEWS = ?, CUST_NAME = ?, CUST_SURNAME = ?, "
				+ "CUST_PLACE = ?, CUST_ADDRESS = ?, CUST_SOI = ?, CUST_ROAD = ? , CUST_DISTRICT = ?, CUST_CITY = ?, "
				+ "CUST_PROVINCE = ?, CUST_ZIPCODE = ?, CUST_TEL = ?, CUST_MOBILE = ?, CUST_BIRTHDAY = ?, CUST_TARGET = ? "
				+ " WHERE CUST_ID = ?";
		log.info(sql);
		int result = getJdbcTemplate().update(
				sql,
				new Object[] { customer.getCustNo(),
						customer.getCustRecordDate(), customer.getCustNews(),
						customer.getCustName(), customer.getCustSurname(),
						customer.getCustPlace(), customer.getCustAddress(),
						customer.getCustSoi(), customer.getCustRoad(),
						customer.getCustDistrict(), customer.getCustCity(),
						customer.getCustProvince(), customer.getCustZipcode(),
						customer.getCustTel(), customer.getCustMobile(),
						customer.getCustBirthday(), customer.getCustTarget() });
		return result;
	}

	@Override
	public int delete(String id) {
		String sql = "DELETE FROM CUSTOMER_TB WHERE CUST_ID = ?";
		log.info(sql);
		int result = getJdbcTemplate().update(sql, new Object[] { id });
		return result;
	}

	@Override
	public List<Customer> selectReport(String fromDt, String toDt) {
		
		String sql = "SELECT CUST_NO, CUST_RECORD_DATE, CUST_NEWS, CUST_NAME, CUST_SURNAME, CUST_PLACE, CUST_ADDRESS, CUST_SOI, CUST_ROAD "
				+ ", CUST_DISTRICT, CUST_CITY, CUST_PROVINCE, CUST_ZIPCODE, CUST_TEL, CUST_MOBILE, CUST_BIRTHDAY, CUST_TARGET "
				+ ", LENS_TYPE FROM CUSTOMER_TB TB INNER JOIN CUSTOMER_SIGHT_TB CT ON TB.CUST_ID = CT.CUST_ID "
				+ " WHERE (FORMAT(SIGHT_DATE,'YYYY/MM/DD') between ? and ?) AND (FORMAT(SIGHT_APPOINT,'YYYY/MM/DD') between ? and ?) ORDER BY TB.CUST_ID  ";
		log.info(sql);
		List<Customer> customer = getJdbcTemplate().query(sql,
				new Object[] { fromDt, toDt, fromDt, toDt },
				new BeanPropertyRowMapper(Customer.class));
		return customer;
	}

	public List<Customer> selectFillter(String custNo, String custName,
			String fromDt, String toDt) {

		// StringBuffer sql = new StringBuffer();
		// month(sight_date) = 1
		StringBuffer sql = new StringBuffer(
				"SELECT CUST_ID, CUST_NO, CUST_RECORD_DATE, CUST_NEWS, CUST_NAME, CUST_SURNAME, CUST_PLACE, CUST_ADDRESS, CUST_SOI, CUST_ROAD "
						+ ", CUST_DISTRICT, CUST_CITY, CUST_PROVINCE, CUST_ZIPCODE, CUST_TEL, CUST_MOBILE, CUST_BIRTHDAY, CUST_TARGET FROM CUSTOMER_TB "
						+ " WHERE CUST_ID <= 20 ");

		List<Customer> customer = null;

		if ((fromDt != null && !fromDt.equals(""))
				&& (toDt != null && !toDt.equals(""))
				&& (custNo != null && !custNo.equals(""))
				&& (custName != null && !custName.equals(""))) {
			sql.append(" AND format(CUST_RECORD_DATE,'YYYY/MM/DD') between ? and ? AND CUST_NO LIKE ? AND CUST_NAME LIKE ?");
			customer = getJdbcTemplate().query(
					sql.toString(),
					new Object[] { fromDt, toDt, "%" + custNo + "%",
							"%" + custName + "%" },
					new BeanPropertyRowMapper(Customer.class));

		} else if ((fromDt != null && !fromDt.equals(""))
				&& (toDt != null && !toDt.equals("")) && custNo != null
				&& !custNo.equals("")) {
			sql.append(" AND format(CUST_RECORD_DATE,'YYYY/MM/DD') between ? and ? AND CUST_NO LIKE ?");
			customer = getJdbcTemplate().query(sql.toString(),
					new Object[] { fromDt, toDt, "%" + custNo + "%" },
					new BeanPropertyRowMapper(Customer.class));

		} else if ((fromDt != null && !fromDt.equals(""))
				&& (toDt != null && !toDt.equals("")) && custName != null
				&& !custName.equals("")) {
			sql.append(" AND format(CUST_RECORD_DATE,'YYYY/MM/DD') between ? and ? AND CUST_NAME LIKE ?");
			customer = getJdbcTemplate().query(sql.toString(),
					new Object[] { fromDt, toDt, "%" + custName + "%" },
					new BeanPropertyRowMapper(Customer.class));

		} else if ((fromDt != null && !fromDt.equals(""))
				&& (toDt != null && !toDt.equals(""))) {
			sql.append(" AND format(CUST_RECORD_DATE,'YYYY/MM/DD') between ? and ? ");
			customer = getJdbcTemplate().query(sql.toString(),
					new Object[] { fromDt, toDt },
					new BeanPropertyRowMapper(Customer.class));

		}
		log.info(sql.toString());

		return customer;
	}

	public Vector selectFillterCustomer(String custNo, String custName, String custSurname, String custPlace
			, String custAddress, String custSoi, String custRoad, String custDistric, String custCity
			, String custProvince, String custZipCode, String custTel, String custMobile, String custTarget
			, String custNews, String lensType, String monthBirthday,String searchType, String fromDt, String toDt) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Vector vc = new Vector();
		try {
			
			conn = getConnection();
			StringBuffer sql = new StringBuffer(
					"SELECT DISTINCT TB.CUST_ID, CUST_NO, CUST_RECORD_DATE, CUST_NEWS, CUST_TITLE, CUST_NAME, CUST_SURNAME, CUST_PLACE, CUST_ADDRESS, CUST_SOI, CUST_ROAD "
							+ ", CUST_DISTRICT, CUST_CITY, CUST_PROVINCE, CUST_ZIPCODE, CUST_TEL, CUST_MOBILE, CUST_BIRTHDAY, CUST_TARGET "
							+ " FROM CUSTOMER_TB TB LEFT JOIN CUSTOMER_SIGHT_TB CT ON TB.CUST_ID = CT.CUST_ID ");
			
			if ((custNo != null && !custNo.equals("")) || (custName != null && !custName.equals("")) || (custSurname != null && !custSurname.equals(""))
					|| (custPlace != null && !custPlace.equals(""))|| (custAddress != null && !custAddress.equals(""))|| (custSoi != null && !custSoi.equals(""))
					|| (custRoad != null && !custRoad.equals(""))|| (custDistric != null && !custDistric.equals(""))|| (custCity != null && !custCity.equals(""))
					|| (custProvince != null && !custProvince.equals(""))|| (custZipCode != null && !custZipCode.equals(""))|| (custTel != null && !custTel.equals(""))
					|| (custMobile != null && !custMobile.equals(""))|| (custTarget != null && !custTarget.equals(""))|| (custNews != null && !custNews.equals(""))
					|| (lensType != null && !lensType.equals(""))|| (monthBirthday != null && !monthBirthday.equals(""))
					|| (searchType != null && !searchType.equals("")) || (fromDt != null && !fromDt.equals(""))|| (toDt != null && !toDt.equals(""))){
				
				sql.append(" WHERE ");
				
			}
			if (custNo != null && !custNo.equals("")) {
				sql.append(" CUST_NO LIKE ? AND ");
			}
			if (custName != null && !custName.equals("")) {
				sql.append(" CUST_NAME LIKE ? AND ");
			}
			if (custSurname != null && !custSurname.equals("")) {
				sql.append(" CUST_SURNAME LIKE ? AND ");
			}
			if (custPlace != null && !custPlace.equals("")) {
				sql.append(" CUST_PLACE LIKE ? AND ");
			}
			if (custAddress != null && !custAddress.equals("")) {
				sql.append(" CUST_ADDRESS LIKE ? AND ");
			}
			if (custSoi != null && !custSoi.equals("")) {
				sql.append(" CUST_SOI LIKE ? AND ");
			}
			if (custRoad != null && !custRoad.equals("")) {
				sql.append(" CUST_ROAD LIKE ? AND ");
			}
			if (custDistric != null && !custDistric.equals("")) {
				sql.append(" CUST_DISTRICT LIKE ? AND ");
			}
			if (custCity != null && !custCity.equals("")) {
				sql.append(" CUST_CITY LIKE ? AND ");
			}
			if (custProvince != null && !custProvince.equals("")) {
				sql.append(" CUST_PROVINCE LIKE ? AND ");
			}
			if (custZipCode != null && !custZipCode.equals("")) {
				sql.append(" CUST_ZIPCODE LIKE ? AND ");
			}
			if (custTel != null && !custTel.equals("")) {
				sql.append(" CUST_TEL LIKE ? AND ");
			}
			if (custMobile != null && !custMobile.equals("")) {
				sql.append(" CUST_MOBILE LIKE ? AND ");
			}
			if (custTarget != null && !custTarget.equals("")) {
				sql.append(" CUST_TARGET LIKE ? AND ");
			}
			if (custNews != null && !custNews.equals("")) {
				sql.append(" CUST_NEWS = ? AND ");
			}
			if (lensType != null && !lensType.equals("")) {
				sql.append(" LENS_TYPE LIKE ? AND ");
			}
			if ((monthBirthday != null && !monthBirthday.equals("")) && (!monthBirthday.equals("0"))){
				sql.append(" MONTH(CUST_BIRTHDAY) = ? AND ");
			}
			if (searchType != null && !searchType.equals("")) {
				if(searchType.equalsIgnoreCase("SIGHT_DATE")){
					if ((fromDt != null && !fromDt.equals("")) && (toDt != null && !toDt.equals(""))){
						sql.append(" FORMAT(SIGHT_DATE,'YYYY/MM/DD') between ? and ? ");
					}
				}else if(searchType.equalsIgnoreCase("SIGHT_APPOINT")){
					if ((fromDt != null && !fromDt.equals("")) && (toDt != null && !toDt.equals(""))){
						sql.append(" FORMAT(SIGHT_APPOINT,'YYYY/MM/DD') between ? and ? ");
					}
				}else{
					sql.append(" (FORMAT(SIGHT_DATE,'YYYY/MM/DD') between ? and ?) AND (FORMAT(SIGHT_APPOINT,'YYYY/MM/DD') between ? and ?) ");
				}
			}
			StringBuffer dsql = removeWasteSQL(sql);
			dsql.append(" ORDER BY TB.CUST_ID ");
			
			log.info("selectFillterCustomer " + dsql.toString());
			ps = conn.prepareStatement(dsql.toString());
			int index = 0;
			if (custNo != null && !custNo.equals("")) {
				ps.setString(++index, "%" + custNo.toUpperCase() + "%");
			}
			if (custName != null && !custName.equals("")) {
				ps.setString(++index, "%" + custName + "%");
			}
			if (custSurname != null && !custSurname.equals("")) {
				ps.setString(++index, "%" + custSurname + "%");
			}
			if (custPlace != null && !custPlace.equals("")) {
				ps.setString(++index, "%" + custPlace + "%");
			}
			if (custAddress != null && !custAddress.equals("")) {
				ps.setString(++index, "%" + custAddress + "%");
			}
			if (custSoi != null && !custSoi.equals("")) {
				ps.setString(++index, "%" + custSoi + "%");
			}
			if (custRoad != null && !custRoad.equals("")) {
				ps.setString(++index, "%" + custRoad + "%");
			}
			if (custDistric != null && !custDistric.equals("")) {
				ps.setString(++index, "%" + custDistric + "%");
			}
			if (custCity != null && !custCity.equals("")) {
				ps.setString(++index, "%" + custCity + "%");
			}
			if (custProvince != null && !custProvince.equals("")) {
				ps.setString(++index, "%" + custProvince + "%");
			}
			if (custZipCode != null && !custZipCode.equals("")) {
				ps.setString(++index, "%" + custZipCode + "%");
			}
			if (custTel != null && !custTel.equals("")) {
				ps.setString(++index, "%" + custTel + "%");
			}
			if (custMobile != null && !custMobile.equals("")) {
				ps.setString(++index, "%" + custMobile + "%");
			}
			if (custTarget != null && !custTarget.equals("")) {
				ps.setString(++index, "%" + custTarget + "%");
			}
			if (custNews != null && !custNews.equals("")) {
				ps.setString(++index, custNews );
			}
			if (lensType != null && !lensType.equals("")) {
				ps.setString(++index, "%" + lensType + "%");
			}
			if ((monthBirthday != null && !monthBirthday.equals("")) && (!monthBirthday.equals("0"))){
				ps.setString(++index, monthBirthday);
			}
			if (searchType != null && !searchType.equals("")) {
				if(!searchType.equalsIgnoreCase("0")){
					if (fromDt != null && !fromDt.equals("")){
						ps.setString(++index, fromDt);
					}
					if (toDt != null && !toDt.equals("")) {
						ps.setString(++index, toDt);
					}
				}else {
					if (fromDt != null && !fromDt.equals("")){
						ps.setString(++index, fromDt);
					}
					if (toDt != null && !toDt.equals("")) {
						ps.setString(++index, toDt);
					}
					if (fromDt != null && !fromDt.equals("")){
						ps.setString(++index, fromDt);
					}
					if (toDt != null && !toDt.equals("")) {
						ps.setString(++index, toDt);
					}
				}
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Customer cm = new Customer();
				cm.setCustId(rs.getString("CUST_ID"));
				cm.setCustNo(rs.getString("CUST_NO"));
				cm.setCustRecordDate(rs.getString("CUST_RECORD_DATE"));
				cm.setCustTitle(rs.getString("CUST_TITLE"));
				cm.setCustName(rs.getString("CUST_NAME"));
				cm.setCustSurname(rs.getString("CUST_SURNAME"));
				cm.setCustPlace(rs.getString("CUST_PLACE"));
				cm.setCustAddress(rs.getString("CUST_ADDRESS"));
				cm.setCustSoi(rs.getString("CUST_SOI"));
				cm.setCustRoad(rs.getString("CUST_ROAD"));
				cm.setCustDistrict(rs.getString("CUST_DISTRICT"));
				cm.setCustCity(rs.getString("CUST_CITY"));
				cm.setCustProvince(rs.getString("CUST_PROVINCE"));
				cm.setCustZipcode(rs.getString("CUST_ZIPCODE"));
				cm.setCustTel(rs.getString("CUST_TEL"));
				cm.setCustMobile(rs.getString("CUST_MOBILE"));
				cm.setCustBirthday(rs.getString("CUST_BIRTHDAY"));
				cm.setCustTarget(rs.getString("CUST_TARGET"));
				cm.setCustNews(rs.getString("CUST_NEWS"));
				
				vc.add(cm);
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
	public String selectCustNo() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String custNo = "";
		try {
			
			conn = getConnection();
			String sql = "select max(cust_no) as cu_no from customer_tb";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				custNo = rs.getString("cu_no");
				logger.debug(" cus no " + custNo);
				custNo = "C" + (Integer.parseInt(custNo.substring(1,custNo.length())) + 1);
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
		return custNo;
	}
	@Override
	public boolean insertCustomerAndSight(Customer customer,
			CustomerSight custSight) {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	boolean isSuccess = false;
	double cust_id = 0;
	double sight_id = 0;
	try{
		conn = getConnection();
		StringBuffer sql = new StringBuffer();
		StringBuffer csql = new StringBuffer();
		StringBuffer msql = new StringBuffer();
		StringBuffer ssql = new StringBuffer();
		
		conn.setAutoCommit(false);
		
		sql.append("select max(cust_id)+1 as cid from customer_tb");
		
		ps = conn.prepareStatement(sql.toString());
		rs = ps.executeQuery();
		while (rs.next()) {
			cust_id =rs.getDouble("cid");
		}
		rs.close();
		
		
		csql.append("INSERT INTO CUSTOMER_TB (CUST_ID, CUST_NO, CUST_RECORD_DATE, CUST_NEWS, CUST_TITLE, CUST_NAME, CUST_SURNAME, CUST_PLACE, CUST_ADDRESS, ");
		csql.append("CUST_SOI, CUST_ROAD, CUST_DISTRICT, CUST_CITY, CUST_PROVINCE, CUST_ZIPCODE, CUST_TEL, CUST_MOBILE, CUST_BIRTHDAY, CUST_TARGET)");
		csql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		ps = conn.prepareStatement(csql.toString());
		int Index = 1;
		ps.setDouble(Index++, cust_id);
		ps.setString(Index++, customer.getCustNo());
		ps.setString(Index++, customer.getCustRecordDate());
		ps.setString(Index++, customer.getCustNews());
		ps.setString(Index++, customer.getCustTitle());
		ps.setString(Index++, customer.getCustName());
		ps.setString(Index++, customer.getCustSurname());
		ps.setString(Index++, customer.getCustPlace());
		ps.setString(Index++, customer.getCustAddress());
		ps.setString(Index++, customer.getCustSoi());
		ps.setString(Index++, customer.getCustRoad());
		ps.setString(Index++, customer.getCustDistrict());
		ps.setString(Index++, customer.getCustCity());
		ps.setString(Index++, customer.getCustProvince());
		ps.setString(Index++, customer.getCustZipcode());
		ps.setString(Index++, customer.getCustTel());
		ps.setString(Index++, customer.getCustMobile());
		ps.setString(Index++, customer.getCustBirthday());
		ps.setString(Index++, customer.getCustTarget());
		
		int rsInt = ps.executeUpdate();
		if (rsInt > 0) {
			isSuccess = true;
		}else{
			isSuccess = false;
		}
		
		msql.append("select max(sight_id)+1 as sid from CUSTOMER_SIGHT_TB");
		
		ps = conn.prepareStatement(msql.toString());
		rs = ps.executeQuery();
		while (rs.next()) {
			sight_id =rs.getDouble("sid");
		}
		rs.close();
		
		
			
		ssql.append("Insert into CUSTOMER_SIGHT_TB (SIGHT_ID, CUST_ID,SIGHT_DATE,SIGHT_APPOINT,SIGHT_R_SPH,SIGHT_R_CYL,SIGHT_R_AXIS,SIGHT_R_ADD, ");
		ssql.append(" SIGHT_R_VA,SIGHT_R_PRISM,SIGHT_R_BASE,SIGHT_L_SPH,SIGHT_L_CYL,SIGHT_L_AXIS,SIGHT_L_ADD,SIGHT_L_VA,SIGHT_L_PRISM,SIGHT_L_BASE, ");
		ssql.append("SIGHT_PD,SIGHT_SEGSH,LENS_SINGLE,LENS_BIFOCAL,LENS_PROGRESS,LENS_LENS_TYPE,LENS_COLOR,LENS_FRAME,LENS_NOTE,LENS_TYPE,SIGHT_FREQUENCY)");
		ssql.append(" values (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		ps = conn.prepareStatement(ssql.toString());
		int sightIndex = 1;
		
		log.info("Dao sight date  " + custSight.getSightDate());
		log.info("Dao appoint date  " + custSight.getSightAppoint());
		
		ps.setDouble(sightIndex++, sight_id);
		ps.setDouble(sightIndex++, cust_id);
		ps.setString(sightIndex++, custSight.getSightDate());
		ps.setString(sightIndex++, custSight.getSightAppoint());
		ps.setString(sightIndex++, custSight.getSightRSph());
		ps.setString(sightIndex++, custSight.getSightRCyl());
		ps.setString(sightIndex++, custSight.getSightRAxis());
		ps.setString(sightIndex++, custSight.getSightRAdd());
		ps.setString(sightIndex++, custSight.getSightRVa());
		ps.setString(sightIndex++, custSight.getSightRPrism());
		ps.setString(sightIndex++, custSight.getSightRBase());
		ps.setString(sightIndex++, custSight.getSightLSph());
		ps.setString(sightIndex++, custSight.getSightLCyl());
		ps.setString(sightIndex++, custSight.getSightLAxis());
		ps.setString(sightIndex++, custSight.getSightLAdd());
		ps.setString(sightIndex++, custSight.getSightLVa());
		ps.setString(sightIndex++, custSight.getSightLPrism());
		ps.setString(sightIndex++, custSight.getSightLBase());
		ps.setString(sightIndex++, custSight.getSightPd());
		ps.setString(sightIndex++, custSight.getSightSegsh());
		ps.setString(sightIndex++, custSight.getLensSingle());
		ps.setString(sightIndex++, custSight.getLensBifocal());
		ps.setString(sightIndex++, custSight.getLensProgress());
		ps.setString(sightIndex++, custSight.getLensLensType());
		ps.setString(sightIndex++, custSight.getLensColor());
		ps.setString(sightIndex++, custSight.getLensFrame());
		ps.setString(sightIndex++, custSight.getLensNote());
		ps.setString(sightIndex++, custSight.getLensType());
		ps.setString(sightIndex++, custSight.getSightFrequency());
		int rsInt1 = ps.executeUpdate();
		if (rsInt1 > 0) {
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
	public boolean updateCustomerAndSight(Customer customer, CustomerSight custSight) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean isSuccess = false;
		double cust_id = 0;
		double sight_id = 0;
		try{
			conn = getConnection();
			StringBuffer sql = new StringBuffer();
			StringBuffer dsql = new StringBuffer();
			StringBuffer csql = new StringBuffer();
			StringBuffer msql = new StringBuffer();
			StringBuffer ssql = new StringBuffer();
			
			conn.setAutoCommit(false);
			
			csql.append("UPDATE CUSTOMER_TB SET CUST_RECORD_DATE = ?, CUST_NEWS = ?, CUST_TITLE = ?, CUST_NAME = ?, CUST_SURNAME = ?, " );
			csql.append("CUST_PLACE = ?, CUST_ADDRESS = ?, CUST_SOI = ?, CUST_ROAD = ? , CUST_DISTRICT = ?, CUST_CITY = ?, " );
			csql.append("CUST_PROVINCE = ?, CUST_ZIPCODE = ?, CUST_TEL = ?, CUST_MOBILE = ?, CUST_BIRTHDAY = ?, CUST_TARGET = ? " );
			csql.append("WHERE CUST_ID = ? ");
			
			ps = conn.prepareStatement(csql.toString());
			int Index = 1;
			
			ps.setString(Index++, customer.getCustRecordDate());
			ps.setString(Index++, customer.getCustNews());
			ps.setString(Index++, customer.getCustTitle());
			ps.setString(Index++, customer.getCustName());
			ps.setString(Index++, customer.getCustSurname());
			ps.setString(Index++, customer.getCustPlace());
			ps.setString(Index++, customer.getCustAddress());
			ps.setString(Index++, customer.getCustSoi());
			ps.setString(Index++, customer.getCustRoad());
			ps.setString(Index++, customer.getCustDistrict());
			ps.setString(Index++, customer.getCustCity());
			ps.setString(Index++, customer.getCustProvince());
			ps.setString(Index++, customer.getCustZipcode());
			ps.setString(Index++, customer.getCustTel());
			ps.setString(Index++, customer.getCustMobile());
			ps.setString(Index++, customer.getCustBirthday());
			ps.setString(Index++, customer.getCustTarget());
			ps.setDouble(Index++, Double.parseDouble(customer.getCustId()));
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				isSuccess = true;
			}else{
				isSuccess = false;
			}
			
			
			double oldSightId = 0;
			double countSightId=0;
			sql.append("SELECT MAX(SIGHT_ID) AS SSID, COUNT(SIGHT_ID) AS CSID FROM CUSTOMER_SIGHT_TB WHERE CUST_ID = ? ");
			ps = conn.prepareStatement(sql.toString());
			
			ps.setDouble(1, Double.parseDouble(customer.getCustId()));
			rs = ps.executeQuery();
			while (rs.next()) {
				oldSightId = rs.getDouble("SSID");
				countSightId = rs.getDouble("CSID");
			}
			rs.close();
			
			int rsInt1 = 0;
			if(countSightId == 3){
				dsql.append("DELETE FROM CUSTOMER_SIGHT_TB WHERE SIGHT_ID = ? ");
				ps = conn.prepareStatement(dsql.toString());
				ps.setDouble(1, oldSightId);
				rsInt1 = ps.executeUpdate();
				if (rsInt1 > 0) {
					isSuccess = true;
				}else{
					isSuccess = false;
				}
			}
			
			
			msql.append("select max(sight_id)+1 as sid from CUSTOMER_SIGHT_TB");
			
			ps = conn.prepareStatement(msql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				sight_id =rs.getDouble("sid");
			}
			rs.close();
			
			
				
			ssql.append("Insert into CUSTOMER_SIGHT_TB (SIGHT_ID, CUST_ID,SIGHT_DATE,SIGHT_APPOINT,SIGHT_R_SPH,SIGHT_R_CYL,SIGHT_R_AXIS,SIGHT_R_ADD, ");
			ssql.append(" SIGHT_R_VA,SIGHT_R_PRISM,SIGHT_R_BASE,SIGHT_L_SPH,SIGHT_L_CYL,SIGHT_L_AXIS,SIGHT_L_ADD,SIGHT_L_VA,SIGHT_L_PRISM,SIGHT_L_BASE, ");
			ssql.append("SIGHT_PD,SIGHT_SEGSH,LENS_SINGLE,LENS_BIFOCAL,LENS_PROGRESS,LENS_LENS_TYPE,LENS_COLOR,LENS_FRAME,LENS_NOTE,LENS_TYPE,SIGHT_FREQUENCY)");
			ssql.append(" values (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps = conn.prepareStatement(ssql.toString());
			int sightIndex = 1;
				
			ps.setDouble(sightIndex++, sight_id);
			ps.setDouble(sightIndex++, Double.parseDouble(customer.getCustId()));
			ps.setString(sightIndex++, custSight.getSightDate());
			ps.setString(sightIndex++, custSight.getSightAppoint());
			ps.setString(sightIndex++, custSight.getSightRSph());
			ps.setString(sightIndex++, custSight.getSightRCyl());
			ps.setString(sightIndex++, custSight.getSightRAxis());
			ps.setString(sightIndex++, custSight.getSightRAdd());
			ps.setString(sightIndex++, custSight.getSightRVa());
			ps.setString(sightIndex++, custSight.getSightRPrism());
			ps.setString(sightIndex++, custSight.getSightRBase());
			ps.setString(sightIndex++, custSight.getSightLSph());
			ps.setString(sightIndex++, custSight.getSightLCyl());
			ps.setString(sightIndex++, custSight.getSightLAxis());
			ps.setString(sightIndex++, custSight.getSightLAdd());
			ps.setString(sightIndex++, custSight.getSightLVa());
			ps.setString(sightIndex++, custSight.getSightLPrism());
			ps.setString(sightIndex++, custSight.getSightLBase());
			ps.setString(sightIndex++, custSight.getSightPd());
			ps.setString(sightIndex++, custSight.getSightSegsh());
			ps.setString(sightIndex++, custSight.getLensSingle());
			ps.setString(sightIndex++, custSight.getLensBifocal());
			ps.setString(sightIndex++, custSight.getLensProgress());
			ps.setString(sightIndex++, custSight.getLensLensType());
			ps.setString(sightIndex++, custSight.getLensColor());
			ps.setString(sightIndex++, custSight.getLensFrame());
			ps.setString(sightIndex++, custSight.getLensNote());
			ps.setString(sightIndex++, custSight.getLensType());
			ps.setString(sightIndex++, custSight.getSightFrequency());
			
			rsInt1 = ps.executeUpdate();
			if (rsInt1 > 0) {
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
	public boolean deleteSight(String custId) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean isSuccess = false;
		double cust_id = 0;
		double sight_id = 0;
		try{
			conn = getConnection();
			StringBuffer csql = new StringBuffer();
			StringBuffer dsql = new StringBuffer();
			
			conn.setAutoCommit(false);
			
//			csql.append("UPDATE CUSTOMER_TB SET CUST_RECORD_DATE = ?, CUST_NEWS = ?, CUST_TITLE = ?, CUST_NAME = ?, CUST_SURNAME = ?, " );
//			csql.append("CUST_PLACE = ?, CUST_ADDRESS = ?, CUST_SOI = ?, CUST_ROAD = ? , CUST_DISTRICT = ?, CUST_CITY = ?, " );
//			csql.append("CUST_PROVINCE = ?, CUST_ZIPCODE = ?, CUST_TEL = ?, CUST_MOBILE = ?, CUST_BIRTHDAY = ?, CUST_TARGET = ? " );
//			csql.append("WHERE CUST_ID = ? ");
//			
//			ps = conn.prepareStatement(csql.toString());
//			int Index = 1;
//			
//			ps.setString(Index++, null);
//			ps.setString(Index++, "");
//			ps.setString(Index++, "");
//			ps.setString(Index++, "");
//			ps.setString(Index++, "");
//			ps.setString(Index++, "");
//			ps.setString(Index++, "");
//			ps.setString(Index++, "");
//			ps.setString(Index++, "");
//			ps.setString(Index++, "");
//			ps.setString(Index++, "");
//			ps.setString(Index++, "");
//			ps.setString(Index++, "");
//			ps.setString(Index++, "");
//			ps.setString(Index++, "");
//			ps.setString(Index++, null);
//			ps.setString(Index++, "");
//			ps.setDouble(Index++, Double.parseDouble(custId));
//			
//			int rsInt = ps.executeUpdate();
//			if (rsInt > 0) {
//				isSuccess = true;
//			}else{
//				isSuccess = false;
//			}
//			
			
			
			int rsInt1 = 0;
				dsql.append("DELETE FROM CUSTOMER_SIGHT_TB WHERE CUST_ID = ? ");
				ps = conn.prepareStatement(dsql.toString());
				ps.setDouble(1, Double.parseDouble(custId));
				rsInt1 = ps.executeUpdate();
				if (rsInt1 > 0) {
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
	public boolean updateCustomer(Customer customer) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean isSuccess = false;
		try{
			conn = getConnection();
			StringBuffer csql = new StringBuffer();
			
			conn.setAutoCommit(false);
			
			csql.append("UPDATE CUSTOMER_TB SET CUST_RECORD_DATE = ?, CUST_NEWS = ?, CUST_TITLE = ?, CUST_NAME = ?, CUST_SURNAME = ?, " );
			csql.append("CUST_PLACE = ?, CUST_ADDRESS = ?, CUST_SOI = ?, CUST_ROAD = ? , CUST_DISTRICT = ?, CUST_CITY = ?, " );
			csql.append("CUST_PROVINCE = ?, CUST_ZIPCODE = ?, CUST_TEL = ?, CUST_MOBILE = ?, CUST_BIRTHDAY = ?, CUST_TARGET = ? " );
			csql.append("WHERE CUST_ID = ? ");
			
			ps = conn.prepareStatement(csql.toString());
			int Index = 1;
			
			ps.setString(Index++, customer.getCustRecordDate());
			ps.setString(Index++, customer.getCustNews());
			ps.setString(Index++, customer.getCustTitle());
			ps.setString(Index++, customer.getCustName());
			ps.setString(Index++, customer.getCustSurname());
			ps.setString(Index++, customer.getCustPlace());
			ps.setString(Index++, customer.getCustAddress());
			ps.setString(Index++, customer.getCustSoi());
			ps.setString(Index++, customer.getCustRoad());
			ps.setString(Index++, customer.getCustDistrict());
			ps.setString(Index++, customer.getCustCity());
			ps.setString(Index++, customer.getCustProvince());
			ps.setString(Index++, customer.getCustZipcode());
			ps.setString(Index++, customer.getCustTel());
			ps.setString(Index++, customer.getCustMobile());
			ps.setString(Index++, customer.getCustBirthday());
			ps.setString(Index++, customer.getCustTarget());
			ps.setDouble(Index++, Double.parseDouble(customer.getCustId()));
			
			int rsInt = ps.executeUpdate();
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
	public boolean updateSight(Customer customer, CustomerSight custSight) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean isSuccess = false;
		double cust_id = 0;
		double sight_id = 0;
		try{
			conn = getConnection();
			StringBuffer sql = new StringBuffer();
			StringBuffer dsql = new StringBuffer();
			StringBuffer msql = new StringBuffer();
			StringBuffer ssql = new StringBuffer();
			
			conn.setAutoCommit(false);
			
			double oldSightId = 0;
			double countSightId=0;
			sql.append("SELECT MIN(SIGHT_ID) AS SSID, COUNT(SIGHT_ID) AS CSID FROM CUSTOMER_SIGHT_TB WHERE CUST_ID = ? ");
			ps = conn.prepareStatement(sql.toString());
			
			ps.setDouble(1, Double.parseDouble(customer.getCustId()));
			rs = ps.executeQuery();
			while (rs.next()) {
				oldSightId = rs.getDouble("SSID");
				countSightId = rs.getDouble("CSID");
			}
			rs.close();
			
			int rsInt1 = 0;
			if(countSightId == 3){
				dsql.append("DELETE FROM CUSTOMER_SIGHT_TB WHERE SIGHT_ID = ? ");
				ps = conn.prepareStatement(dsql.toString());
				ps.setDouble(1, oldSightId);
				rsInt1 = ps.executeUpdate();
				if (rsInt1 > 0) {
					isSuccess = true;
				}else{
					isSuccess = false;
				}
			}
			
			
			msql.append("select max(sight_id)+1 as sid from CUSTOMER_SIGHT_TB");
			
			ps = conn.prepareStatement(msql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				sight_id =rs.getDouble("sid");
			}
			rs.close();
			
			
				
			ssql.append("Insert into CUSTOMER_SIGHT_TB (SIGHT_ID, CUST_ID,SIGHT_DATE,SIGHT_APPOINT,SIGHT_R_SPH,SIGHT_R_CYL,SIGHT_R_AXIS,SIGHT_R_ADD, ");
			ssql.append(" SIGHT_R_VA,SIGHT_R_PRISM,SIGHT_R_BASE,SIGHT_L_SPH,SIGHT_L_CYL,SIGHT_L_AXIS,SIGHT_L_ADD,SIGHT_L_VA,SIGHT_L_PRISM,SIGHT_L_BASE, ");
			ssql.append("SIGHT_PD,SIGHT_SEGSH,LENS_SINGLE,LENS_BIFOCAL,LENS_PROGRESS,LENS_LENS_TYPE,LENS_COLOR,LENS_FRAME,LENS_NOTE,LENS_TYPE,SIGHT_FREQUENCY)");
			ssql.append(" values (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps = conn.prepareStatement(ssql.toString());
			int sightIndex = 1;
				
			ps.setDouble(sightIndex++, sight_id);
			ps.setDouble(sightIndex++, Double.parseDouble(customer.getCustId()));
			ps.setString(sightIndex++, custSight.getSightDate());
			ps.setString(sightIndex++, custSight.getSightAppoint());
			ps.setString(sightIndex++, custSight.getSightRSph());
			ps.setString(sightIndex++, custSight.getSightRCyl());
			ps.setString(sightIndex++, custSight.getSightRAxis());
			ps.setString(sightIndex++, custSight.getSightRAdd());
			ps.setString(sightIndex++, custSight.getSightRVa());
			ps.setString(sightIndex++, custSight.getSightRPrism());
			ps.setString(sightIndex++, custSight.getSightRBase());
			ps.setString(sightIndex++, custSight.getSightLSph());
			ps.setString(sightIndex++, custSight.getSightLCyl());
			ps.setString(sightIndex++, custSight.getSightLAxis());
			ps.setString(sightIndex++, custSight.getSightLAdd());
			ps.setString(sightIndex++, custSight.getSightLVa());
			ps.setString(sightIndex++, custSight.getSightLPrism());
			ps.setString(sightIndex++, custSight.getSightLBase());
			ps.setString(sightIndex++, custSight.getSightPd());
			ps.setString(sightIndex++, custSight.getSightSegsh());
			ps.setString(sightIndex++, custSight.getLensSingle());
			ps.setString(sightIndex++, custSight.getLensBifocal());
			ps.setString(sightIndex++, custSight.getLensProgress());
			ps.setString(sightIndex++, custSight.getLensLensType());
			ps.setString(sightIndex++, custSight.getLensColor());
			ps.setString(sightIndex++, custSight.getLensFrame());
			ps.setString(sightIndex++, custSight.getLensNote());
			ps.setString(sightIndex++, custSight.getLensType());
			ps.setString(sightIndex++, custSight.getSightFrequency());
			
			rsInt1 = ps.executeUpdate();
			if (rsInt1 > 0) {
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
	public Vector selectFillterCustomerReport(String custNo, String custName,
			String custSurname, String custPlace, String custAddress,
			String custSoi, String custRoad, String custDistric,
			String custCity, String custProvince, String custZipCode,
			String custTel, String custMobile, String custTarget,
			String custNews, String lensType, String monthBirthday,
			String searchType, String fromDt, String toDt) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Vector vc = new Vector();
		try {
			
			conn = getConnection();
			StringBuffer sql = new StringBuffer(
					"SELECT DISTINCT TB.CUST_ID, CUST_NO, CUST_RECORD_DATE, CUST_NEWS, CUST_TITLE, CUST_NAME, CUST_SURNAME, CUST_PLACE, CUST_ADDRESS, CUST_SOI, CUST_ROAD "
							+ ", CUST_DISTRICT, CUST_CITY, CUST_PROVINCE, CUST_ZIPCODE, CUST_TEL, CUST_MOBILE, CUST_BIRTHDAY, CUST_TARGET "
							+ ", (SELECT TOP 1 LENS_TYPE FROM CUSTOMER_SIGHT_TB WHERE CUSTOMER_SIGHT_TB.CUST_ID = CT.CUST_ID ORDER BY SIGHT_ID DESC) AS LT "
							+ " FROM CUSTOMER_TB TB LEFT JOIN CUSTOMER_SIGHT_TB CT ON TB.CUST_ID = CT.CUST_ID ");
			
			if ((custNo != null && !custNo.equals("")) || (custName != null && !custName.equals("")) || (custSurname != null && !custSurname.equals(""))
					|| (custPlace != null && !custPlace.equals(""))|| (custAddress != null && !custAddress.equals(""))|| (custSoi != null && !custSoi.equals(""))
					|| (custRoad != null && !custRoad.equals(""))|| (custDistric != null && !custDistric.equals(""))|| (custCity != null && !custCity.equals(""))
					|| (custProvince != null && !custProvince.equals(""))|| (custZipCode != null && !custZipCode.equals(""))|| (custTel != null && !custTel.equals(""))
					|| (custMobile != null && !custMobile.equals(""))|| (custTarget != null && !custTarget.equals(""))|| (custNews != null && !custNews.equals(""))
					|| (lensType != null && !lensType.equals(""))|| (monthBirthday != null && !monthBirthday.equals(""))
					|| (searchType != null && !searchType.equals("")) || (fromDt != null && !fromDt.equals(""))|| (toDt != null && !toDt.equals(""))){
				
				sql.append(" WHERE ");
				
			}
			if (custNo != null && !custNo.equals("")) {
				sql.append(" CUST_NO LIKE ? AND ");
			}
			if (custName != null && !custName.equals("")) {
				sql.append(" CUST_NAME LIKE ? AND ");
			}
			if (custSurname != null && !custSurname.equals("")) {
				sql.append(" CUST_SURNAME LIKE ? AND ");
			}
			if (custPlace != null && !custPlace.equals("")) {
				sql.append(" CUST_PLACE LIKE ? AND ");
			}
			if (custAddress != null && !custAddress.equals("")) {
				sql.append(" CUST_ADDRESS LIKE ? AND ");
			}
			if (custSoi != null && !custSoi.equals("")) {
				sql.append(" CUST_SOI LIKE ? AND ");
			}
			if (custRoad != null && !custRoad.equals("")) {
				sql.append(" CUST_ROAD LIKE ? AND ");
			}
			if (custDistric != null && !custDistric.equals("")) {
				sql.append(" CUST_DISTRICT LIKE ? AND ");
			}
			if (custCity != null && !custCity.equals("")) {
				sql.append(" CUST_CITY LIKE ? AND ");
			}
			if (custProvince != null && !custProvince.equals("")) {
				sql.append(" CUST_PROVINCE LIKE ? AND ");
			}
			if (custZipCode != null && !custZipCode.equals("")) {
				sql.append(" CUST_ZIPCODE LIKE ? AND ");
			}
			if (custTel != null && !custTel.equals("")) {
				sql.append(" CUST_TEL LIKE ? AND ");
			}
			if (custMobile != null && !custMobile.equals("")) {
				sql.append(" CUST_MOBILE LIKE ? AND ");
			}
			if (custTarget != null && !custTarget.equals("")) {
				sql.append(" CUST_TARGET LIKE ? AND ");
			}
			if (custNews != null && !custNews.equals("")) {
				sql.append(" CUST_NEWS = ? AND ");
			}
			if (lensType != null && !lensType.equals("")) {
				sql.append(" LENS_TYPE LIKE ? AND ");
			}
			if ((monthBirthday != null && !monthBirthday.equals("")) && (!monthBirthday.equals("0"))){
				sql.append(" MONTH(CUST_BIRTHDAY) = ? AND ");
			}
			if (searchType != null && !searchType.equals("")) {
				if(searchType.equalsIgnoreCase("SIGHT_DATE")){
					if ((fromDt != null && !fromDt.equals("")) && (toDt != null && !toDt.equals(""))){
						sql.append(" FORMAT(SIGHT_DATE,'YYYY/MM/DD') between ? and ? ");
					}
				}else if(searchType.equalsIgnoreCase("SIGHT_APPOINT")){
					if ((fromDt != null && !fromDt.equals("")) && (toDt != null && !toDt.equals(""))){
						sql.append(" FORMAT(SIGHT_APPOINT,'YYYY/MM/DD') between ? and ? ");
					}
				}else{
					sql.append(" (FORMAT(SIGHT_DATE,'YYYY/MM/DD') between ? and ?) AND (FORMAT(SIGHT_APPOINT,'YYYY/MM/DD') between ? and ?) ");
				}
			}
			StringBuffer dsql = removeWasteSQL(sql);
			dsql.append(" ORDER BY TB.CUST_ID ");
			
			log.info("selectFillterCustomer " + dsql.toString());
			ps = conn.prepareStatement(dsql.toString());
			int index = 0;
			if (custNo != null && !custNo.equals("")) {
				ps.setString(++index, "%" + custNo.toUpperCase() + "%");
			}
			if (custName != null && !custName.equals("")) {
				ps.setString(++index, "%" + custName + "%");
			}
			if (custSurname != null && !custSurname.equals("")) {
				ps.setString(++index, "%" + custSurname + "%");
			}
			if (custPlace != null && !custPlace.equals("")) {
				ps.setString(++index, "%" + custPlace + "%");
			}
			if (custAddress != null && !custAddress.equals("")) {
				ps.setString(++index, "%" + custAddress + "%");
			}
			if (custSoi != null && !custSoi.equals("")) {
				ps.setString(++index, "%" + custSoi + "%");
			}
			if (custRoad != null && !custRoad.equals("")) {
				ps.setString(++index, "%" + custRoad + "%");
			}
			if (custDistric != null && !custDistric.equals("")) {
				ps.setString(++index, "%" + custDistric + "%");
			}
			if (custCity != null && !custCity.equals("")) {
				ps.setString(++index, "%" + custCity + "%");
			}
			if (custProvince != null && !custProvince.equals("")) {
				ps.setString(++index, "%" + custProvince + "%");
			}
			if (custZipCode != null && !custZipCode.equals("")) {
				ps.setString(++index, "%" + custZipCode + "%");
			}
			if (custTel != null && !custTel.equals("")) {
				ps.setString(++index, "%" + custTel + "%");
			}
			if (custMobile != null && !custMobile.equals("")) {
				ps.setString(++index, "%" + custMobile + "%");
			}
			if (custTarget != null && !custTarget.equals("")) {
				ps.setString(++index, "%" + custTarget + "%");
			}
			if (custNews != null && !custNews.equals("")) {
				ps.setString(++index, custNews );
			}
			if (lensType != null && !lensType.equals("")) {
				ps.setString(++index, "%" + lensType + "%");
			}
			if ((monthBirthday != null && !monthBirthday.equals("")) && (!monthBirthday.equals("0"))){
				ps.setString(++index, monthBirthday);
			}
			if (searchType != null && !searchType.equals("")) {
				if(!searchType.equalsIgnoreCase("0")){
					if (fromDt != null && !fromDt.equals("")){
						ps.setString(++index, fromDt);
					}
					if (toDt != null && !toDt.equals("")) {
						ps.setString(++index, toDt);
					}
				}else {
					if (fromDt != null && !fromDt.equals("")){
						ps.setString(++index, fromDt);
					}
					if (toDt != null && !toDt.equals("")) {
						ps.setString(++index, toDt);
					}
					if (fromDt != null && !fromDt.equals("")){
						ps.setString(++index, fromDt);
					}
					if (toDt != null && !toDt.equals("")) {
						ps.setString(++index, toDt);
					}
				}
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Customer cm = new Customer();
				cm.setCustId(rs.getString("CUST_ID"));
				cm.setCustNo(rs.getString("CUST_NO"));
				cm.setCustRecordDate(rs.getString("CUST_RECORD_DATE"));
				cm.setCustTitle(rs.getString("CUST_TITLE"));
				cm.setCustName(rs.getString("CUST_NAME"));
				cm.setCustSurname(rs.getString("CUST_SURNAME"));
				cm.setCustPlace(rs.getString("CUST_PLACE"));
				cm.setCustAddress(rs.getString("CUST_ADDRESS"));
				cm.setCustSoi(rs.getString("CUST_SOI"));
				cm.setCustRoad(rs.getString("CUST_ROAD"));
				cm.setCustDistrict(rs.getString("CUST_DISTRICT"));
				cm.setCustCity(rs.getString("CUST_CITY"));
				cm.setCustProvince(rs.getString("CUST_PROVINCE"));
				cm.setCustZipcode(rs.getString("CUST_ZIPCODE"));
				cm.setCustTel(rs.getString("CUST_TEL"));
				cm.setCustMobile(rs.getString("CUST_MOBILE"));
				cm.setCustBirthday(rs.getString("CUST_BIRTHDAY"));
				cm.setCustTarget(rs.getString("CUST_TARGET"));
				cm.setLensType(rs.getString("LT"));
				cm.setCustNews(rs.getString("CUST_NEWS"));
				
				vc.add(cm);
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
	public boolean deleteCustomer(String custId) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean isSuccess = false;
		double cust_id = 0;
		double sight_id = 0;
		try{
			conn = getConnection();
			StringBuffer csql = new StringBuffer();
			StringBuffer dsql = new StringBuffer();
			
			conn.setAutoCommit(false);
			
			csql.append("UPDATE CUSTOMER_TB SET CUST_RECORD_DATE = ?, CUST_NEWS = ?, CUST_TITLE = ?, CUST_NAME = ?, CUST_SURNAME = ?, " );
			csql.append("CUST_PLACE = ?, CUST_ADDRESS = ?, CUST_SOI = ?, CUST_ROAD = ? , CUST_DISTRICT = ?, CUST_CITY = ?, " );
			csql.append("CUST_PROVINCE = ?, CUST_ZIPCODE = ?, CUST_TEL = ?, CUST_MOBILE = ?, CUST_BIRTHDAY = ?, CUST_TARGET = ? " );
			csql.append("WHERE CUST_ID = ? ");
			
			ps = conn.prepareStatement(csql.toString());
			int Index = 1;
			
			ps.setString(Index++, null);
			ps.setString(Index++, "");
			ps.setString(Index++, "");
			ps.setString(Index++, "");
			ps.setString(Index++, "");
			ps.setString(Index++, "");
			ps.setString(Index++, "");
			ps.setString(Index++, "");
			ps.setString(Index++, "");
			ps.setString(Index++, "");
			ps.setString(Index++, "");
			ps.setString(Index++, "");
			ps.setString(Index++, "");
			ps.setString(Index++, "");
			ps.setString(Index++, "");
			ps.setString(Index++, null);
			ps.setString(Index++, "");
			ps.setDouble(Index++, Double.parseDouble(custId));
			
			int rsInt = ps.executeUpdate();
			if (rsInt > 0) {
				isSuccess = true;
			}else{
				isSuccess = false;
			}
			
			
			
			int rsInt1 = 0;
				dsql.append("DELETE FROM CUSTOMER_SIGHT_TB WHERE CUST_ID = ? ");
				ps = conn.prepareStatement(dsql.toString());
				ps.setDouble(1, Double.parseDouble(custId));
				rsInt1 = ps.executeUpdate();
				if (rsInt1 > 0) {
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
	
}
