package com.icbtassignment.gct.factorydesignpattern.dao;

import javax.sql.DataSource;

public interface LoginDao {
	
	public int verify(DataSource dataSource,String UserName , String password);
}
