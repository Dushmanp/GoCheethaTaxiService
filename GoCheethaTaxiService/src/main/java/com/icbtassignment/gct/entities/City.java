package com.icbtassignment.gct.entities;

import javax.validation.constraints.NotBlank;

public class City {
	
	
	private String city_Id;
	
	@NotBlank(message = "is required")
	private String city_Name;
		
	public City() {
			
		}

	public City(String city_Id) {
		this.city_Id = city_Id;
	}
	public City(String city_Id, String city_Name) {
		super();
		this.city_Id = city_Id;
		this.city_Name = city_Name;
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
		return "City [city_Id=" + city_Id + ", city_name=" + city_Name + "]";
	}
	
	
	
}