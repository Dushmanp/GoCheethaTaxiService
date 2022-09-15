package com.icbtassignment.gct.entities;

import javax.validation.constraints.NotBlank;

public class VehicleCategory {

	private String vehicle_category_Id;
	
	@NotBlank(message = "is required")
	private String vehicle_type_Name;
	
	@NotBlank(message = "is required")
	private String service_Charge;
	
	@NotBlank(message = "is required")
	private String charge_per_Km;
	
	
	public VehicleCategory() {
		
	}


	public VehicleCategory(String vehicle_category_Id) {
		super();
		this.vehicle_category_Id = vehicle_category_Id;
	}


	public VehicleCategory(String vehicle_category_Id, String vehicle_type_Name, String service_Charge,
			String charge_per_Km) {
		super();
		this.vehicle_category_Id = vehicle_category_Id;
		this.vehicle_type_Name = vehicle_type_Name;
		this.service_Charge = service_Charge;
		this.charge_per_Km = charge_per_Km;
	}


	public String getVehicle_category_Id() {
		return vehicle_category_Id;
	}


	public void setVehicle_category_Id(String vehicle_category_Id) {
		this.vehicle_category_Id = vehicle_category_Id;
	}


	public String getVehicle_type_Name() {
		return vehicle_type_Name;
	}


	public void setVehicle_type_Name(String vehicle_type_Name) {
		this.vehicle_type_Name = vehicle_type_Name;
	}


	public String getService_Charge() {
		return service_Charge;
	}


	public void setService_Charge(String service_Charge) {
		this.service_Charge = service_Charge;
	}


	public String getCharge_per_Km() {
		return charge_per_Km;
	}


	public void setCharge_per_Km(String charge_per_Km) {
		this.charge_per_Km = charge_per_Km;
	}


	@Override
	public String toString() {
		return "VehicleCategory [vehicle_category_Id=" + vehicle_category_Id + ", vehicle_type_Name="
				+ vehicle_type_Name + ", service_Charge=" + service_Charge + ", charge_per_Km=" + charge_per_Km + "]";
	}
	
	

}
