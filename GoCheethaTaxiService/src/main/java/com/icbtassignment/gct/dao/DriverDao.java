package com.icbtassignment.gct.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import com.icbtassignment.gct.entities.Driver;
import com.icbtassignment.gct.utils.CustomException;

public class DriverDao {

	public static List<Driver> getDriver(DataSource dataSource, String driverId){
		 
		List<Driver> drivers = new ArrayList<Driver>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT driver_Id,driver.vehicle_category_Id,driver.branch_Id,vehicle_category.vehicle_type_Name,branch.branch_Name,driver_Name,phone_No, address,licence_No,password,vehicle_No FROM driver \r\n"
					+ "inner join vehicle_category on driver.vehicle_category_Id =vehicle_category.vehicle_category_Id\r\n"
					+ "inner join branch on driver.branch_Id =branch.branch_Id;";
			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				String driver_Id = rset.getString("driver_Id");
				String branch_Id = rset.getString("branch_Id");
				String vehicle_category_Id = rset.getString("vehicle_category_Id");
				String branch_Name = rset.getString("branch_Name");
				String vehicle_type_Name = rset.getString("vehicle_type_Name");
				String driver_Name = rset.getString("driver_Name");
				
				String phone_No = rset.getString("phone_No");
				String address = rset.getString("address");
				String licence_No = rset.getString("licence_No");
				String password = rset.getString("password");
				
				String vehicle_No = rset.getString("vehicle_No");
				
				Driver driver = new Driver(driver_Id,branch_Id,vehicle_category_Id,driver_Name,phone_No,address,licence_No,password,vehicle_No,vehicle_type_Name, branch_Name);
				drivers.add(driver);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return drivers;
	}
	
	public static Driver get(DataSource dataSource, String driverId){
		 
		Driver drivers =null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT * from driver where driver_Id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, driverId);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String driver_Id = rset.getString("driver_Id");
				String branch_Id = rset.getString("branch_Id");
				String vehicle_category_Id = rset.getString("vehicle_category_Id");
				
				String driver_Name = rset.getString("driver_Name");
				
				String phone_No = rset.getString("phone_No");
				String address = rset.getString("address");
				String licence_No = rset.getString("licence_No");
				String password = rset.getString("password");
				
				String vehicle_No = rset.getString("vehicle_No");
				
				drivers = new Driver(driver_Id,branch_Id,vehicle_category_Id,driver_Name,phone_No,address,licence_No,password,vehicle_No);
				
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return drivers;
	}
	
	public static Driver getList(DataSource dataSource, String licenceNo){
		 
		Driver drivers =null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT * from driver where licence_No=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, licenceNo);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String driver_Id = rset.getString("driver_Id");
				String branch_Id = rset.getString("branch_Id");
				String vehicle_category_Id = rset.getString("vehicle_category_Id");
				
				String driver_Name = rset.getString("driver_Name");
				
				String phone_No = rset.getString("phone_No");
				String address = rset.getString("address");
				String licence_No = rset.getString("licence_No");
				String password = rset.getString("password");
				
				String vehicle_No = rset.getString("vehicle_No");
				
				drivers = new Driver(driver_Id,branch_Id,vehicle_category_Id,driver_Name,phone_No,address,licence_No,password,vehicle_No);
				
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return drivers;
	}
	
	public static int checkLicenceNo(DataSource dataSource, String licenceNo) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		int returnVal = 0;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from driver where licence_No = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, licenceNo);
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
	
	public static int checkLicenceNo(DataSource dataSource, String licenceNo, String driver_Id) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		int returnVal = 0;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from driver where licence_No=? AND driver_Id <>?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, licenceNo);
			stmt.setString(2, driver_Id);
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


	
	public static int checkvehicleNo(DataSource dataSource, String vehicleNo) {
			
			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rset  = null;
			String sql = null;
			int returnVal = 0;
			
			try {
				con = dataSource.getConnection();
				sql= "select * from driver where vehicle_No = ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, vehicleNo);
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
	
	public static int checkPhoneNo(DataSource dataSource, String phoneNo) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		int returnVal = 0;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from driver where phone_No = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, phoneNo);
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
	
	
	public static void addDriver(DataSource dataSource, Driver driver) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "INSERT INTO driver(driver_Id,branch_Id,vehicle_category_Id,driver_Name,phone_No,address,licence_No,password,vehicle_No) VALUES (?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, driver.getDriver_Id());
			pstmt.setString(2, driver.getBranch_Id());
			pstmt.setString(3, driver.getvehicle_category_Id());
			pstmt.setString(4, driver.getDriver_Name());
			pstmt.setString(5, driver.getPhone_No());
			pstmt.setString(6, driver.getAddress());
			pstmt.setString(7, driver.getLicence_No());
			pstmt.setString(8, driver.getPassword());
			pstmt.setString(9, driver.getVehicle_No());
			pstmt.execute();
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
				close(con,pstmt,null);
		}
	}
	
	
	public static void updateDriver(DataSource dataSource, Driver driver) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "UPDATE driver SET branch_Id=?,vehicle_category_Id=?,driver_Name=?,phone_No=?,address=?,licence_No=?,password=?,vehicle_No=? WHERE driver_Id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, driver.getBranch_Id());
			pstmt.setString(2, driver.getvehicle_category_Id());
			pstmt.setString(3, driver.getDriver_Name());
			pstmt.setString(4, driver.getPhone_No());
			pstmt.setString(5, driver.getAddress());
			pstmt.setString(6, driver.getLicence_No());
			pstmt.setString(7, driver.getPassword());
			pstmt.setString(8, driver.getVehicle_No());
			pstmt.setString(9, driver.getDriver_Id());
			pstmt.execute();
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
				close(con,pstmt,null);
		}
	}
	


	public static void deleteDriver(DataSource dataSource, String driverId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			con = dataSource.getConnection();
			 sql = "DELETE FROM driver WHERE driver_Id=?";
			 pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, driverId);
		
			pstmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			throw new CustomException(e.getMessage());
		}
		
	}

	

}
