package com.icbtassignment.gct.entities;

import javax.validation.constraints.NotBlank;

public class Street {
	
	private String street_Id;
	
	@NotBlank(message = "is required")
	private String street_Name;
	
	@NotBlank(message = "is required")
	private String city_Id;
	
	private String city_Name;
	
	public Street() {
		
	}

	public Street(String street_Id) {
		super();
		this.street_Id = street_Id;
	}

	public Street(String street_Id, String street_Name,String city_Id) {
		super();
		this.street_Id = street_Id;
		this.street_Name = street_Name;
		this.city_Id = city_Id;
	}

	public Street(String street_Id, String street_Name,String city_Id, String city_Name) {
		super();
		this.street_Id = street_Id;
		this.street_Name = street_Name;
		this.city_Id = city_Id;
		this.city_Name = city_Name;
	}

	public String getStreet_Id() {
		return street_Id;
	}

	public void setStreet_Id(String street_Id) {
		this.street_Id = street_Id;
	}

	public String getStreet_Name() {
		return street_Name;
	}

	public void setStreet_Name(String street_Name) {
		this.street_Name = street_Name;
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
		return "Street [street_Id=" + street_Id + ", street_Name=" + street_Name + ", city_Id=" + city_Id
				+ ", city_Name=" + city_Name + "]";
	}
	
	
	
	
	
	

}
