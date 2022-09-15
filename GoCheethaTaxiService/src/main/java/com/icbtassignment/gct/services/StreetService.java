package com.icbtassignment.gct.services;

import java.util.List;

import javax.sql.DataSource;


import com.icbtassignment.gct.dao.StreetDao;
import com.icbtassignment.gct.entities.Street;



public class StreetService {
	
	
	public static List<Street> getStreet(DataSource dataSource,String city_Id){
			
			return StreetDao.getStreet(dataSource,city_Id);
	}
	
	
	public static Street get(DataSource dataSource, String city_Id) {
		
		return StreetDao.get(dataSource,city_Id);
	}
	
	public static boolean checkCityName(DataSource dataSource, String street_Name,String street_Id) {
		int checkstreet ;
		if(street_Id == null || street_Id.isEmpty() ||street_Id.trim().isEmpty())
		{
			checkstreet =	StreetDao.checkStreetName(dataSource,street_Name);
		}else {
			checkstreet =	StreetDao.checkStreetUpdateName(dataSource,street_Name,street_Id);
		}
		
		if(checkstreet == 0) {
			
			return true ;
		}
		else {
			return false;
		}
		
	}
	
	public static void addStreet(DataSource dataSource, Street street) {
		StreetDao.addStreet(dataSource,street);
		
	}
	
	public static void updateStreet(DataSource dataSource, Street street) {
		StreetDao.updateStreet(dataSource,street);
		
	}

	public static void deleteStreet(DataSource dataSource, String city_Id) {
		StreetDao.deleteStreet(dataSource,city_Id);
		
	}

	

}
