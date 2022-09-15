package com.icbtassignment.gct.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.icbtassignment.gct.entities.Employee;
import com.icbtassignment.gct.utils.CustomException;

public class EmployeeDao {
	
	public static List<Employee> getEmployee(DataSource dataSource){
		 
		List<Employee> employee = new ArrayList<Employee>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT employee_Id,username,password,email,display_Name,userType FROM employee where employee_Id !='0000';";
			stmt = con.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				String employee_Id = rset.getString("employee_Id");
				String username = rset.getString("username");
				String password = rset.getString("password");
				String email = rset.getString("email");
				String display_Name = rset.getString("display_Name");
				String userType = rset.getString("userType");
				
				Employee emp = new Employee(employee_Id,username,password,email,display_Name,userType);
				employee.add(emp);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return employee;
	}
	
	public static Employee get(DataSource dataSource, String employeeId){
		 
		Employee employee =null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT employee_Id,username,password,email,display_Name,userType FROM employee where employee_Id=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, employeeId);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String employee_Id = rset.getString("employee_Id");
				String username = rset.getString("username");
				String password = rset.getString("password");
				String email = rset.getString("email");
				String display_Name = rset.getString("display_Name");
				String userType = rset.getString("userType");
				
				employee = new Employee(employee_Id,username,password,email,display_Name,userType);
				
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return employee;
	}
	
	public static Employee getList(DataSource dataSource, String user_name){
		 
		Employee employee =null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT employee_Id,username,password,userType,email,display_Name FROM employee where username=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, user_name);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String employee_Id = rset.getString("employee_Id");
				String username = rset.getString("username");
				String password = rset.getString("password");
				String email = rset.getString("email");
				String display_Name = rset.getString("display_Name");
				String userType = rset.getString(4);
				
				employee = new Employee(employee_Id,username,password,email,display_Name,userType);
				
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return employee;
	}
	
	
	public static int checkUsername(DataSource dataSource, String username) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		int returnVal = 0;
		
		try {
			con = dataSource.getConnection();
			sql= "select * FROM employee where username = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
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
	
	public static int checkUpdateUsername(DataSource dataSource, String username,String employeeId) {
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		int returnVal = 0;
		
		try {
			con = dataSource.getConnection();
			sql= "select * FROM employee where username = ? AND employee_Id <>?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, employeeId);
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
	

	public static void addEmployee(DataSource dataSource, Employee employee) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "INSERT INTO employee(employee_Id,username,password,email,display_Name,userType) VALUES (?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, employee.getEmployee_Id());
			pstmt.setString(2, employee.getUsername());
			pstmt.setString(3, employee.getPassword());
			pstmt.setString(4, employee.getEmail()); 
			pstmt.setString(5, employee.getDisplay_Name());
			pstmt.setString(6, employee.getUserType());
			pstmt.execute();
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
				close(con,pstmt,null);
		}
	}
	
	
	public static void updateEmployee(DataSource dataSource, Employee employee) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "UPDATE employee SET username=?,password=?,email=?,display_Name=? WHERE employee_Id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, employee.getUsername());
			pstmt.setString(2, employee.getPassword());
			pstmt.setString(3, employee.getEmail());
			pstmt.setString(4, employee.getDisplay_Name());
			pstmt.setString(5, employee.getEmployee_Id());
			
			pstmt.execute();
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
				close(con,pstmt,null);
		}
	}
	

	public static void deleteEmployee(DataSource dataSource, String employeeId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			con = dataSource.getConnection();
			 sql = "DELETE FROM employee WHERE employee_Id=?";
			 pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, employeeId);
		
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
