package com.icbtassignment.gct.services;

import javax.sql.DataSource;


import com.icbtassignment.gct.dao.CommonDao;
import com.icbtassignment.gct.entities.NumberFomart;


public class CommonServices {
	
	
	public static String getNumberFormat(DataSource dataSource, String tableName) {
			
		NumberFomart numberFormat = CommonDao.getNumberFormat(dataSource,tableName);
		int numberPart = numberFormat.getNumberPart();
		String prefix = numberFormat.getPrefix();
		String RetVal = prefix + (String.format("%05d" , ++numberPart));
			 
		
		return RetVal;
	}

	
	public static void setNumberFormat(DataSource dataSource, String tableName ) {
		
		NumberFomart numberFormat = CommonDao.getNumberFormat(dataSource,tableName);
		int numberPart = numberFormat.getNumberPart();
		
		CommonDao.setNumberFormat(dataSource,tableName,++numberPart );
		
	}
	
	
		

}
