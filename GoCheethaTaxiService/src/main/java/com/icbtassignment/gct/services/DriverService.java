package com.icbtassignment.gct.services;

import java.util.List;

import javax.sql.DataSource;

import com.icbtassignment.gct.dao.CityDao;
import com.icbtassignment.gct.dao.DriverDao;
import com.icbtassignment.gct.entities.Driver;


public class DriverService {
	
	public static List<Driver> getDriver(DataSource dataSource,String driver_Id){
		
		return DriverDao.getDriver(dataSource,driver_Id);
	}

	public static Driver get(DataSource dataSource, String driverId) {
		
		return DriverDao.get(dataSource,driverId);
	}
	
	public static Driver getList(DataSource dataSource, String licenceNo) {
		return DriverDao.getList(dataSource,licenceNo);
		
	}
	
	public static boolean checkLicenceNo(DataSource dataSource, String LicenceNo,String driver_Id) {
		int checkDriver ;
		if(driver_Id == null || driver_Id.isEmpty() ||driver_Id.trim().isEmpty())
		{
			
			checkDriver =	DriverDao.checkLicenceNo(dataSource,LicenceNo);
		}else {
			checkDriver =	DriverDao.checkLicenceNo(dataSource,LicenceNo,driver_Id);
		}
		
		if(checkDriver == 0) {
			
			return true ;
		}
		else {
			return false;
		}
		
	}

	
	public static void addDriver(DataSource dataSource, Driver driver) {
		DriverDao.addDriver(dataSource,driver);
		
	}
	
	public static void updateDriver(DataSource dataSource, Driver driver) {
		DriverDao.updateDriver(dataSource,driver);
		
	}
	
	public static void deleteDriver(DataSource dataSource, String driver_Id) {
		DriverDao.deleteDriver(dataSource,driver_Id);
		
	}

}
