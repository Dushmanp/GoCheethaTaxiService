package com.icbtassignment.gct.entities;

import javax.validation.constraints.NotBlank;

public class Driver {
	private String driver_Id;
	
	@NotBlank(message = "is required")
	private String branch_Id;
	
	@NotBlank(message = "is required")
	private String vehicle_category_Id;
	
	@NotBlank(message = "is required")
	private String driver_Name;
	
	@NotBlank(message = "is required")
	private String phone_No;
	
	@NotBlank(message = "is required")
	private String address;
	
	@NotBlank(message = "is required")
	private String licence_No;
	
	@NotBlank(message = "is required")
	private String password;
	
	@NotBlank(message = "is required")
	private String vehicle_No;
	
	private String vehicle_type_Name;
	
	private String branch_Name;
	
	public Driver() {
		
	}

	public Driver(String driver_Id) {
		super();
		this.driver_Id = driver_Id;
	}

	public Driver(String driver_Id, String branch_Id, String vehicle_category_Id, String driver_Name, String phone_No,
			String address, String licence_No, String password, String vehicle_No) {
		super();
		this.driver_Id = driver_Id;
		this.branch_Id = branch_Id;
		this.vehicle_category_Id = vehicle_category_Id;
		this.driver_Name = driver_Name;
		this.phone_No = phone_No;
		this.address = address;
		this.licence_No = licence_No;
		this.password = password;
		this.vehicle_No = vehicle_No;
	}
	
	
	

	public Driver(String driver_Id, String branch_Id,
			String vehicle_category_Id, String driver_Name, String phone_No, String address,String licence_No,String password, String vehicle_No,
			String vehicle_type_Name, String branch_Name) {
		super();
		this.driver_Id = driver_Id;
		this.branch_Id = branch_Id;
		this.vehicle_category_Id = vehicle_category_Id;
		this.driver_Name = driver_Name;
		this.phone_No = phone_No;
		this.address = address;
		this.licence_No = licence_No;
		this.password = password;
		this.vehicle_No = vehicle_No;
		this.vehicle_type_Name = vehicle_type_Name;
		this.branch_Name = branch_Name;
	}

	public String getDriver_Id() {
		return driver_Id;
	}

	public void setDriver_ID(String driver_ID) {
		this.driver_Id = driver_ID;
	}

	public String getBranch_Id() {
		return branch_Id;
	}

	public void setBranch_ID(String branch_Id) {
		this.branch_Id = branch_Id;
	}

	public String getvehicle_category_Id() {
		return vehicle_category_Id;
	}

	public void setVehicle_Category_Id(String vehicle_category_Id) {
		this.vehicle_category_Id = vehicle_category_Id;
	}

	public String getDriver_Name() {
		return driver_Name;
	}

	public void setDriver_Name(String driver_Name) {
		this.driver_Name = driver_Name;
	}

	public String getPhone_No() {
		return phone_No;
	}

	public void setPhone_No(String phone_No) {
		this.phone_No = phone_No;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLicence_No() {
		return licence_No;
	}

	public void setLicence_No(String licence_No) {
		this.licence_No = licence_No;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVehicle_No() {
		return vehicle_No;
	}

	public void setVehicle_No(String vehicle_No) {
		this.vehicle_No = vehicle_No;
	}
	
	

	public String getVehicle_type_Name() {
		return vehicle_type_Name;
	}

	public void setVehicle_type_Name(String vehicle_type_Name) {
		this.vehicle_type_Name = vehicle_type_Name;
	}

	public String getBranch_Name() {
		return branch_Name;
	}

	public void setBranch_Name(String branch_Name) {
		this.branch_Name = branch_Name;
	}

	@Override
	public String toString() {
		return "Driver [driver_Id=" + driver_Id + ", branch_Id=" + branch_Id + ", vehicle_category_Id="
				+ vehicle_category_Id + ", driver_Name=" + driver_Name + ", phone_No=" + phone_No + ", address="
				+ address + ", licence_No=" + licence_No + ", password=" + password + ", vehicle_No=" + vehicle_No
				+ "]";
	}
	
	
}
