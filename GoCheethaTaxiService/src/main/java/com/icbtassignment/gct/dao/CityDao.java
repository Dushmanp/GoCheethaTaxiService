package com.icbtassignment.gct.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.icbtassignment.gct.entities.City;
import com.icbtassignment.gct.utils.CustomException;

public class CityDao {

	public static List<City> getCity(DataSource dataSource){
		 
		List<City> cities = new ArrayList<City>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from city";
			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				String city_Id = rset.getString("city_Id");
				String city_Name = rset.getString("city_Name");
				City city = new City(city_Id,city_Name);
				cities.add(city);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return cities;
 }

	
	public static City get(DataSource dataSource, String cityId) {
		City cities = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from city where city_Id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cityId);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String city_Id = rset.getString("city_Id");
				String city_Name = rset.getString("city_Name");
				cities = new City(city_Id,city_Name);
				
				
			}	
			
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return cities;
	}
	
	public static int checkCityName(DataSource dataSource, String cityName) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		int returnVal = 0;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from city where city_Name = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cityName);
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
	
	public static int checkCityUpdateName(DataSource dataSource, String cityName, String cityId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		int returnVal = 0;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from city where city_Name=? AND city_Id <>?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cityName);
			stmt.setString(2, cityId);
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

	

	public static void addCity(DataSource dataSource, City city) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "insert into city (city_Id,city_Name) values(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, city.getCity_Id());
			pstmt.setString(2, city.getCity_Name());
			
			pstmt.execute();
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
				close(con,pstmt,null);
		}
		
	}

	
	public static void updateCity(DataSource dataSource, City city) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			con = dataSource.getConnection();
			 sql = "UPDATE city SET city_Name=? WHERE city_Id=?";
			 pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, city.getCity_Name());
			pstmt.setString(2, city.getCity_Id());
		
			pstmt.execute();
			
		} catch (SQLException e) {
			throw new CustomException(e.getMessage());
		}finally {
			close(con, pstmt, null);
		}
		
	}
	
	public static void deleteCity(DataSource dataSource, String City_Id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			con = dataSource.getConnection();
			 sql = "DELETE FROM city  WHERE city_Id=?";
			 pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, City_Id);
		
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

