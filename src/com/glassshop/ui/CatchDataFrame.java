package com.glassshop.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

import com.glassshop.dao.GoodsBrandDao;
import com.glassshop.dao.GoodsDao;
import com.glassshop.dao.GoodsTypeDao;
import com.glassshop.dao.SalesManDao;
import com.glassshop.dao.VendorDao;
import com.glassshop.model.CatchDataModel;
import com.glassshop.model.Goods;
import com.glassshop.model.GoodsBrand;
import com.glassshop.model.GoodsType;
import com.glassshop.model.SalesMan;
import com.glassshop.model.Vendor;

public class CatchDataFrame extends JFrame{
	
	public JComboBox VendorCombobox(){
		JComboBox cbo = new JComboBox();
		VendorDao vendorDao = (VendorDao) GlassShop.CONTEXT.getBean("vendorDAO");
		List<Vendor> listVendor = vendorDao.selectNameAll();
		CatchDataModel cm = new CatchDataModel(" ", "");
		cbo.addItem(cm);
		for(int i=0; i<listVendor.size(); i++){
			Vendor vendor = listVendor.get(i);
			cm = new CatchDataModel(vendor.getVendorName(), vendor.getVendorId());
			cbo.addItem(cm);
		}
		
		return cbo;
	}
	public JComboBox GoodsTypeCombobox(){
		JComboBox cbo = new JComboBox();
		GoodsDao goodsDao = (GoodsDao)GlassShop.CONTEXT.getBean("goodsDAO");
		List<Goods> listGoods = goodsDao.selectGoodNameAll();
		CatchDataModel cm = new CatchDataModel(" ", "");
		cbo.addItem(cm);
		for(int i=0; i<listGoods.size(); i++){
			Goods goods = listGoods.get(i);
			cm = new CatchDataModel(goods.getGoodsName(), goods.getGoodsId());
			cbo.addItem(cm);
		}
		return cbo;
	}
	
	public JComboBox SalesNameCombobox(){
		JComboBox cbo = new JComboBox();
		SalesManDao salesMan = (SalesManDao) GlassShop.CONTEXT.getBean("salesManDAO");
		List<SalesMan> listSale = salesMan.selectSalesNameAll();
		CatchDataModel cm = new CatchDataModel(" ", "");
		cbo.addItem(cm);
		for(int i=0; i<listSale.size(); i++){
			SalesMan sm = listSale.get(i);
			cm = new CatchDataModel(sm.getSalesmanName(), sm.getSalesmanId());
			cbo.addItem(cm);
		}
		
		return cbo;
	}
	public void SalesNameByVendorCombobox(String id, JComboBox cbo){
//		JComboBox cbo = new JComboBox();
		SalesManDao salesMan = (SalesManDao) GlassShop.CONTEXT.getBean("salesManDAO");
		Vector listSale = salesMan.selectSalesNameByVendor(id);
		CatchDataModel cm = new CatchDataModel(" ", "");
		cbo.addItem(cm);
		for(int i=0; i<listSale.size(); i++){
			SalesMan sm = (SalesMan)listSale.get(i);
			cm = new CatchDataModel(sm.getSalesmanName(), sm.getSalesmanId());
			System.out.println(sm.getSalesmanName() + "-" + sm.getSalesmanId());
			cbo.addItem(cm);
		}
		
//		return cbo;
	}
	public JComboBox GoodTypeNameCombobox(){
		JComboBox cbo = new JComboBox();
		GoodsTypeDao goodType = (GoodsTypeDao) GlassShop.CONTEXT.getBean("goodsTypeDAO");
		List<GoodsType> listGoods = goodType.selectComboAll();
		CatchDataModel cm = new CatchDataModel(" ", "");
		cbo.addItem(cm);
		for(int i=0; i<listGoods.size(); i++){
			GoodsType gt = listGoods.get(i);
			cm = new CatchDataModel(gt.getGoodstypeName(), gt.getGoodstypeId());
			cbo.addItem(cm);
		}
		
		return cbo;
	}
	public void GoodTypeNameByGoodsCombobox(String id, JComboBox cbo){
		GoodsTypeDao goodType = (GoodsTypeDao) GlassShop.CONTEXT.getBean("goodsTypeDAO");
		Vector listGoods = goodType.selectComboByGood(id);
		CatchDataModel cm = new CatchDataModel(" ", "");
		cbo.addItem(cm);
		for(int i=0; i<listGoods.size(); i++){
			GoodsType gt = (GoodsType)listGoods.get(i);
			cm = new CatchDataModel(gt.getGoodstypeName(), gt.getGoodstypeId());
			System.out.println(gt.getGoodstypeName() + "-" + gt.getGoodstypeId());
			cbo.addItem(cm);
		}
		
	}
	
	public JComboBox GoodBrandNameCombobox(){
		JComboBox cbo = new JComboBox();
		GoodsBrandDao goodBrand = (GoodsBrandDao) GlassShop.CONTEXT.getBean("goodsBrandDAO");
		List<GoodsBrand> listGoods = goodBrand.selectBrandNameAll();
		CatchDataModel cm = new CatchDataModel(" ", "");
		cbo.addItem(cm);
		for(int i=0; i<listGoods.size(); i++){
			GoodsBrand gt = listGoods.get(i);
			cm = new CatchDataModel(gt.getGoodsbrandName(), gt.getGoodsbrandId());
			cbo.addItem(cm);
		}
		
		return cbo;
	}
	
	public JComboBox SearchTypeCombobox(){
		JComboBox cbo = new JComboBox();
		
		CatchDataModel cm = new CatchDataModel("", "0");
		cbo.addItem(cm);
		CatchDataModel cm1 = new CatchDataModel("วันที่ทำล่าสุด", "SIGHT_DATE");
		cbo.addItem(cm1);
		CatchDataModel cm2 = new CatchDataModel("วันที่นัด", "SIGHT_APPOINT");
		cbo.addItem(cm2);
			
		return cbo;
	}
	public JComboBox MonthsCombobox(){
		JComboBox cbo = new JComboBox();
		
		CatchDataModel cm = new CatchDataModel("", "0");
		cbo.addItem(cm);
		CatchDataModel cm1 = new CatchDataModel("มกราคม", "1");
		cbo.addItem(cm1);
		CatchDataModel cm2 = new CatchDataModel("กุมภาพันธ์", "2");
		cbo.addItem(cm2);
		CatchDataModel cm3 = new CatchDataModel("มีนาคม", "3");
		cbo.addItem(cm3);
		CatchDataModel cm4 = new CatchDataModel("เมษายน", "4");
		cbo.addItem(cm4);
		CatchDataModel cm5 = new CatchDataModel("พฤษภาคม", "5");
		cbo.addItem(cm5);
		CatchDataModel cm6 = new CatchDataModel("มิถุนายน", "6");
		cbo.addItem(cm6);
		CatchDataModel cm7 = new CatchDataModel("กรกฎาคม", "7");
		cbo.addItem(cm7);
		CatchDataModel cm8 = new CatchDataModel("สิงหาคม", "8");
		cbo.addItem(cm8);
		CatchDataModel cm9 = new CatchDataModel("กันยายน", "9");
		cbo.addItem(cm9);
		CatchDataModel cm10 = new CatchDataModel("ตุลาคม", "10");
		cbo.addItem(cm10);
		CatchDataModel cm11 = new CatchDataModel("พฤศจิกายน", "11");
		cbo.addItem(cm11);
		CatchDataModel cm12 = new CatchDataModel("ธันวาคม", "12");
		cbo.addItem(cm12);
			
		return cbo;
	}
	public static String PickerDat2String(JDatePickerImpl datePicker){
		String dt="";
		String rangeYearsStartFrom = "";
		String rangeDaysStartFrom = String.valueOf(datePicker.getModel().getDay());
        String rangeMonthsStartFrom = String.valueOf(1 + datePicker.getModel().getMonth());
        if (datePicker.getModel().getYear() < 2500){
        	rangeYearsStartFrom =  String.valueOf(datePicker.getModel().getYear()+543);  
        }else{
        	rangeYearsStartFrom =  String.valueOf(datePicker.getModel().getYear());  
        }
        rangeYearsStartFrom =  String.valueOf(datePicker.getModel().getYear());  
                   
        if (rangeDaysStartFrom.length()<=1)
        	rangeDaysStartFrom = "0" + rangeDaysStartFrom;
        if (rangeMonthsStartFrom.length()<=1)
        	rangeMonthsStartFrom = "0" + rangeMonthsStartFrom;
        
        return dt = rangeYearsStartFrom + "/" + rangeMonthsStartFrom + "/"  + rangeDaysStartFrom;
	}
	public static int PickerDay2Int(JDatePickerImpl datePicker){
		int rangeDaysStartFrom = datePicker.getModel().getDay();
        
        return rangeDaysStartFrom;
	}
	public static int PickerMonth2Int(JDatePickerImpl datePicker){
		int rangeMonthsStartFrom = 1 + datePicker.getModel().getMonth();
        
        return rangeMonthsStartFrom;
	}
	public static int PickerYear2Int(JDatePickerImpl datePicker){
		int rangeYearsStartFrom = 0;
        if (datePicker.getModel().getYear() < 2500){
        	rangeYearsStartFrom = datePicker.getModel().getYear() + 543;     
        }else{
        	rangeYearsStartFrom = datePicker.getModel().getYear();
        }
		        
        return rangeYearsStartFrom;
	}
	
	public static int StringDay2Int(String dt){
		if (dt.length()>0){
			String strDt = dt.substring(0,2);
			int rangeDaysStartFrom = Integer.parseInt(strDt);
	        
	        return rangeDaysStartFrom;
		}else{
			return 0;
		}
	}
	public static int StringMonth2Int(String dt){
		if (dt.length()>0){
			String strDt = dt.substring(3,5);
			int rangeMonthsStartFrom = Integer.parseInt(strDt);
        
			return rangeMonthsStartFrom;
		}else{
			return 0;
		}
	}
	public static int StringYear2Int(String dt){
		if (dt.length()>0){
			String strDt = dt.substring(6,10);
			int tempYear = Integer.parseInt(strDt);
			int rangeYearsStartFrom = 0 ;
			
			if (tempYear < 2500){
				rangeYearsStartFrom = tempYear + 543;       
			}else{
				rangeYearsStartFrom = tempYear;
			}
	        return rangeYearsStartFrom;
		}else{
			return 0;
		}
	}
	public static String ConvertSQLDate2String(String dt){
		String strDD = dt.substring(0,2);
		String strMM = dt.substring(3,5);
		String strYYYY = dt.substring(6,10);
		int tempYear = Integer.parseInt(strYYYY);
		String rangeYearsStartFrom = "" ;
		
		if ((tempYear < 2500) || (tempYear > 9000)){
			rangeYearsStartFrom = String.valueOf(tempYear + 543);       
		}else{
			rangeYearsStartFrom = String.valueOf(tempYear);
		}
        return strDD + "/" + strMM + "/" + rangeYearsStartFrom;
	}
	public static String CurrentDate(){
		java.sql.Timestamp curDate = new java.sql.Timestamp(new java.util.Date().getTime());
		
		SimpleDateFormat outFmt = new SimpleDateFormat("dd/mm/yyyy");
		
		String dateOut = outFmt.format(curDate);
				
		return dateOut;
	}
	public static String SQLDate2String(String dates){
		String returnValue = "";
		Date date;
		try {
			SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd"); 
			date = dt.parse(dates);
			
			SimpleDateFormat dt1 = new SimpleDateFormat("dd/mm/yyyy");
			returnValue = (String) dt1.format(date);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return ConvertSQLDate2String(returnValue);
//		System.out.println(dt1.format(date));
	}
	
	public static JComboBox selectValues(JComboBox cbo, String val){
		for (int i=0; i<cbo.getItemCount();i++){
			if(((CatchDataModel) cbo.getItemAt(i)).name.equals(val)){
				cbo.setSelectedIndex(i);
			}
		}
		return cbo;
	}
}
