package com.icbtassignment.gct.services;

import java.util.List;

import javax.sql.DataSource;


import com.icbtassignment.gct.dao.VehicleCategoryDao;

import com.icbtassignment.gct.entities.VehicleCategory;


public class VehicleCategoryService {
	
	public static List<VehicleCategory> getCategory(DataSource dataSource){
		
		return VehicleCategoryDao.getCategory(dataSource);
	}
	
	public static VehicleCategory get(DataSource dataSource, String vehicle_category_Id) {
		
		return VehicleCategoryDao.get(dataSource,vehicle_category_Id);
	}

	public static boolean checkVehicleCatName(DataSource dataSource, String vehicle_type_Name,String vehicle_category_Id) {
		int checkVehicleCat ;
		if(vehicle_category_Id == null || vehicle_category_Id.isEmpty() ||vehicle_category_Id.trim().isEmpty())
		{
			
			checkVehicleCat =	VehicleCategoryDao.checkVehicleCatName(dataSource,vehicle_type_Name);
		}else {
			checkVehicleCat =	VehicleCategoryDao.checkVehicleCatUpdateName(dataSource,vehicle_type_Name,vehicle_category_Id);
		}
		
		if(checkVehicleCat == 0) {
			
			return true ;
		}
		else {
			return false;
		}
		
	}

	public static void addVehicleCat(DataSource dataSource, VehicleCategory vehiclecat) {
		VehicleCategoryDao.addVehicleCat(dataSource,vehiclecat);
		
	}

	public static void updateVehicleCat(DataSource dataSource, VehicleCategory vehiclecat) {
		
		VehicleCategoryDao.updateVehicleCat(dataSource,vehiclecat);
		
		
		
	}
	
	public static void deleteVehicleCat(DataSource dataSource, String vehicle_category_Id) {
		VehicleCategoryDao.deleteVehicleCat(dataSource,vehicle_category_Id);
		
	}
}
