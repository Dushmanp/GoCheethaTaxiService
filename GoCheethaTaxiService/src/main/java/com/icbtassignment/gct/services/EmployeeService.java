package com.icbtassignment.gct.services;

import java.util.List;

import javax.sql.DataSource;

import com.icbtassignment.gct.dao.EmployeeDao;
import com.icbtassignment.gct.entities.Employee;

public class EmployeeService {
	
	public static List<Employee> getEmployee(DataSource dataSource){
		return EmployeeDao.getEmployee(dataSource);
	}
	
	public static Employee get(DataSource dataSource, String employeeId){
		return EmployeeDao.get(dataSource,employeeId);
	}
	
	public static boolean checkUsername(DataSource dataSource,String username,String employeeId) {
		int checkUsername ;
		if(employeeId == null || employeeId.isEmpty() ||employeeId.trim().isEmpty())
		{
			
			checkUsername =	EmployeeDao.checkUsername(dataSource,username);
		}else {
			checkUsername =	EmployeeDao.checkUpdateUsername(dataSource,username,employeeId);
		}
		
		if(checkUsername == 0) {
			
			return true ;
		}
		else {
			return false;
		}
		
	}
	
	public static Employee getList(DataSource dataSource, String user_name) {
		return EmployeeDao.getList(dataSource,user_name);
	}
	
	public static void addEmployee(DataSource dataSource, Employee employee) {
		EmployeeDao.addEmployee(dataSource, employee);
	}
	
	public static void updateEmployee(DataSource dataSource, Employee employee) {
		EmployeeDao.updateEmployee(dataSource, employee);
	}

	public static void deleteEmployee(DataSource dataSource, String employeeId) {
		EmployeeDao.deleteEmployee(dataSource, employeeId);
	}
	

}
