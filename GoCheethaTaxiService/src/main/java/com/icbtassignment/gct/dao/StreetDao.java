package com.icbtassignment.gct.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import com.icbtassignment.gct.entities.Street;
import com.icbtassignment.gct.utils.CustomException;


public class StreetDao {

	
	public static List<Street> getStreet(DataSource dataSource, String cityId){
		 
		List<Street> streets = new ArrayList<Street>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT street_Id,street_Name,street.city_Id,city.city_Name FROM street"
					+ " INNER JOIN city on street.city_Id =city.city_Id";
			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				String street_Id = rset.getString("street_Id");
				String street_Name = rset.getString("street_Name");
				String city_Id = rset.getString("city_Id");
				String city_Name = rset.getString("city_Name");
				Street street = new Street(street_Id,street_Name,city_Id,city_Name);
				streets.add(street);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return streets;
	}

	public static Street get(DataSource dataSource, String streetId) {
		Street street = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from street where street_Id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, streetId);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String street_Id = rset.getString("street_Id");
				String street_Name = rset.getString("street_Name");
				String city_Id = rset.getString("city_Id");
				street = new Street(street_Id,street_Name,city_Id);
				
				
			}	
			
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return street;
	}
	
	
	public static int checkStreetName(DataSource dataSource, String streetName) {
			
			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rset  = null;
			String sql = null;
			int returnVal = 0;
			
			try {
				con = dataSource.getConnection();
				sql= "select * from street where street_Name = ?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, streetName);
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
	
	public static int checkStreetUpdateName(DataSource dataSource, String streetName, String streetId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		int returnVal = 0;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from street where street_Name=? AND street_Id <>?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, streetName);
			stmt.setString(2, streetId);
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

	

	public static void addStreet(DataSource dataSource, Street street) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "insert into street (street_Id,street_Name,city_Id) values(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, street.getStreet_Id());
			pstmt.setString(2, street.getStreet_Name());
			pstmt.setString(3, street.getCity_Id());
			pstmt.execute();
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
				close(con,pstmt,null);
		}
		
	}
	
	public static void updateStreet(DataSource dataSource, Street street) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			con = dataSource.getConnection();
			sql = "UPDATE street SET street_Name=?,city_ID=? WHERE street_Id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, street.getStreet_Name());
			pstmt.setString(2, street.getCity_Id());
			pstmt.setString(3, street.getStreet_Id());
			pstmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CustomException(e.getMessage());
		}finally {
			close(con, pstmt, null);
		}
		
	}
	
	public static void deleteStreet(DataSource dataSource, String street_Id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			con = dataSource.getConnection();
			 sql = "DELETE FROM street WHERE street_Id=?";
			 pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, street_Id);
		
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
