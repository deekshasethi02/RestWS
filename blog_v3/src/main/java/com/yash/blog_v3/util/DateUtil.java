package com.yash.blog_v3.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public String convertDateToString(SimpleDateFormat formatter, Date date) {
		return formatter.format(date);
		
	}

	public String convertDateStringToString(String dateFromDB) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		String stringDate = null;
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateFromDB);
			
			stringDate = convertDateToString(formatter,date);
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return stringDate;
	}
	
		public String convertStringToDbDateString(String date) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stringDate = null;
		try {
			//Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateFromDB);
			Date dateOb = new SimpleDateFormat("dd MMM yyyy").parse(date);
			
			stringDate = convertDateToString(formatter,dateOb);
			System.out.println(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return stringDate;
	}
}
