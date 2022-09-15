package com.icbtassignment.gct.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;

import com.icbtassignment.gct.dao.CommonDao;
import com.icbtassignment.gct.entities.NumberFomart;

public class CommanServicesTest {
	
	@Resource(name ="jdbc/gocheetha_application")
    private static DataSource dataSource;
	@Test
	public static void getNumberFormatTest() {
		String actual = "CT00025";
		NumberFomart numberFormat = CommonDao.getNumberFormat(dataSource,"city");
		int numberPart = numberFormat.getNumberPart();
		String prefix = numberFormat.getPrefix();
		String expected = prefix + (String.format("%05d" , ++numberPart));
		assertEquals(expected, actual);
		
	}
}
