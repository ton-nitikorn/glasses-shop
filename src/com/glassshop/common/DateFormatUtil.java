package com.glassshop.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
	
	public static String getFormatDate(String strDate, String format){
		String output = strDate;
		
		DateFormat df = new SimpleDateFormat(format); 
		DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy"); 
	    Date startDate;
	    try {
	        startDate = df.parse(strDate);
	        output = df2.format(startDate);
	    } catch (ParseException e) {
//	        e.printStackTrace();
	    }
		
		return output;
	}
	
	public static String getFormatDate(String strDate){
		String output = strDate;
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy"); 
	    Date startDate;
	    try {
	        startDate = df.parse(strDate);
	        output = df2.format(startDate);
	    } catch (ParseException e) {
//	        e.printStackTrace();
	    }
		
		return output;
	}
	
	public static void main(String[] a){
		String str = DateFormatUtil.getFormatDate("2009-06-09 00:00:00");
		System.out.println(str);
	}

}
