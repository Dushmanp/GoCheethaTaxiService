package com.icbtassignment.gct.services;

import java.util.List;

import javax.sql.DataSource;

import com.icbtassignment.gct.dao.CityDao;
import com.icbtassignment.gct.entities.City;

public class CityServices {
	
	public static List<City> getCity(DataSource dataSource){
		
		return CityDao.getCity(dataSource);
	}
	
	public static City get(DataSource dataSource, String city_Id) {
		
		return CityDao.get(dataSource,city_Id);
	}

	public static boolean checkCityName(DataSource dataSource, String city_Name,String city_Id) {
		int checkCity ;
		if(city_Id == null || city_Id.isEmpty() ||city_Id.trim().isEmpty())
		{
			
			checkCity =	CityDao.checkCityName(dataSource,city_Name);
		}else {
			checkCity =	CityDao.checkCityUpdateName(dataSource,city_Name,city_Id);
		}
		
		if(checkCity == 0) {
			
			return true ;
		}
		else {
			return false;
		}
		
	}

	public static void addCity(DataSource dataSource, City city) {
		CityDao.addCity(dataSource,city);
		
	}

	public static void updateCity(DataSource dataSource, City city) {
		
			CityDao.updateCity(dataSource,city);
		
		
		
	}
	
	public static void deleteCity(DataSource dataSource, String city_Id) {
		 CityDao.deleteCity(dataSource,city_Id);
		
	}

}
