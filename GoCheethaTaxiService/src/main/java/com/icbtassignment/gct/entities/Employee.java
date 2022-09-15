package com.icbtassignment.gct.entities;

import javax.validation.constraints.NotBlank;

public class Employee {
	private String employee_Id;
	@NotBlank(message = "is required")
	private String username;
	@NotBlank(message = "is required")
	private String Password;
	@NotBlank(message = "is required")
	private String email;
	@NotBlank(message = "is required")
	private String display_Name;
	
	@NotBlank(message = "is required")
	private String userType;
	
    public Employee() {
		
	}

	public Employee(String employee_Id) {
		super();
		this.employee_Id = employee_Id;
	}

	public Employee(String employee_Id, String username, String password, String email, String display_Name,String userType) {
		super();
		this.employee_Id = employee_Id;
		this.username = username;
		Password = password;
		this.email = email;
		this.display_Name = display_Name;
		this.userType = userType;
	}

	public String getEmployee_Id() {
		return employee_Id;
	}

	public void setEmployee_Id(String employee_Id) {
		this.employee_Id = employee_Id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisplay_Name() {
		return display_Name;
	}

	public void setDisplay_Name(String display_Name) {
		this.display_Name = display_Name;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
