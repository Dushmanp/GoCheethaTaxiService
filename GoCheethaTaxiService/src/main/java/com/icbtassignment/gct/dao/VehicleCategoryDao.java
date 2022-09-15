package com.icbtassignment.gct.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import com.icbtassignment.gct.entities.VehicleCategory;
import com.icbtassignment.gct.utils.CustomException;

public class VehicleCategoryDao {

	public static List<VehicleCategory> getCategory(DataSource dataSource){
		 
		List<VehicleCategory> cities = new ArrayList<VehicleCategory>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from vehicle_category";
			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				String vehicle_category_Id = rset.getString("vehicle_category_Id");
				String vehicle_type_Name = rset.getString("vehicle_type_Name");
				String service_Charge = rset.getString("service_Charge");
				String charge_per_Km = rset.getString("charge_per_Km");
				VehicleCategory city = new VehicleCategory(vehicle_category_Id,vehicle_type_Name,service_Charge,charge_per_Km);
				cities.add(city);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return cities;
	}
	
	
	public static VehicleCategory get(DataSource dataSource,String vehiclecategoryId){
		 
		VehicleCategory vehiclecategories = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from vehicle_category where vehicle_category_Id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, vehiclecategoryId);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String vehicle_category_Id = rset.getString("vehicle_category_Id");
				String vehicle_type_Name = rset.getString("vehicle_type_Name");
				String service_Charge = rset.getString("service_Charge");
				String charge_per_Km = rset.getString("charge_per_Km");
				vehiclecategories = new VehicleCategory(vehicle_category_Id,vehicle_type_Name,service_Charge,charge_per_Km);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return vehiclecategories;
	}
	
	public static int checkVehicleCatName(DataSource dataSource,String vehicletypeName){
		 
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		int returnVal = 0;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from vehicle_category where vehicle_type_Name=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, vehicletypeName);
			rset= stmt.executeQuery();
			
			if(rset.next()) {
				returnVal = 1;
				
			}	
			else {
				returnVal = 0;
			}
			
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return returnVal;
	}

	public static int checkVehicleCatUpdateName(DataSource dataSource,String vehicletypeName,String vehiclecategoryId){
		 
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		int returnVal = 0;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from vehicle_category where vehicle_type_Name=? and vehicle_category_Id<>?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, vehicletypeName);
			stmt.setString(2, vehiclecategoryId);
			rset= stmt.executeQuery();
			
			if(rset.next()) {
				returnVal = 1;
				
			}	
			else {
				returnVal = 0;
			}
			
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return returnVal;
	}
	
	
	public static void addVehicleCat(DataSource dataSource, VehicleCategory vc) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "insert into vehicle_category (vehicle_category_Id,vehicle_type_Name,service_Charge,charge_per_Km) values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vc.getVehicle_category_Id());
			pstmt.setString(2, vc.getVehicle_type_Name());
			pstmt.setString(3, vc.getService_Charge());
			pstmt.setString(4, vc.getCharge_per_Km());
			pstmt.execute();
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
				close(con,pstmt,null);
		}
		
	}
	
	public static void updateVehicleCat(DataSource dataSource, VehicleCategory vc) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "UPDATE vehicle_category SET vehicle_type_Name=?,service_Charge=?,charge_per_Km=? WHERE vehicle_category_Id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vc.getVehicle_type_Name());
			pstmt.setString(2, vc.getService_Charge());
			pstmt.setString(3, vc.getCharge_per_Km());
			pstmt.setString(4, vc.getVehicle_category_Id());
			pstmt.execute();
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
				close(con,pstmt,null);
		}
		
	}
	
	public static void deleteVehicleCat(DataSource dataSource, String vehiclecategoryId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			con = dataSource.getConnection();
			 sql = "DELETE FROM vehicle_category  WHERE vehicle_category_Id=?";
			 pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, vehiclecategoryId);
		
			pstmt.execute();
			
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}finally {
			close(con, pstmt, null);
		}
		
	}
	
	private static void close(Connection con,Statement stmt,ResultSet rset) {
		try {
			if(rset != null)
			{
				rset.close();
			}
			if(stmt != null)
			{
				stmt.close();
			}
			
			if(con != null)
			{
				con.close();
			}
			
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}
		
	}


	
}
