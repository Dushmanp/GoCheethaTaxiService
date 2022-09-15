package com.icbtassignment.gct.entities;

import javax.validation.constraints.NotBlank;

public class Branch {
	
	private String branch_Id;
	@NotBlank(message = "is required")
	private String branch_Name;
	@NotBlank(message = "is required")
	private String city_Id;

	private String city_Name;
	
	public Branch() {
		
	}
	
	public Branch(String branch_Id) {
		super();
		this.branch_Id = branch_Id;
	}

	public Branch(String branch_Id, String branch_Name, String city_Id) {
		super();
		this.branch_Id = branch_Id;
		this.branch_Name = branch_Name;
		this.city_Id = city_Id;
	}

	public Branch(String branch_Id, String branch_Name, String city_Id, String city_Name) {
		super();
		this.branch_Id = branch_Id;
		this.branch_Name = branch_Name;
		this.city_Id = city_Id;
		this.city_Name = city_Name;
	}

	public String getBranch_Id() {
		return branch_Id;
	}

	public void setBranch_Id(String branch_Id) {
		this.branch_Id = branch_Id;
	}

	public String getBranch_Name() {
		return branch_Name;
	}

	public void setBranch_Name(String branch_Name) {
		this.branch_Name = branch_Name;
	}

	public String getCity_Id() {
		return city_Id;
	}

	public void setCity_Id(String city_Id) {
		this.city_Id = city_Id;
	}

	public String getCity_Name() {
		return city_Name;
	}

	public void setCity_Name(String city_Name) {
		this.city_Name = city_Name;
	}

	@Override
	public String toString() {
		return "branch [branch_Id=" + branch_Id + ", branch_Name=" + branch_Name + ", city_Id=" + city_Id
				+ ", city_Name=" + city_Name + "]";
	}
	
	
}
