package com.icbtassignment.gct.entities;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

public class Booking {
	
	private String booking_Id ;
	
	private String driver_Id;
	
	private String customer_Id;
	
	@NotBlank(message = "is required")
	private String source_Location;
	
	@NotBlank(message = "is required")
	private String destinationation_Location;
	
	private String pickup_Time;
	private String drop_Time;
	
	@NotBlank(message = "is required")
	private String booking_Status;
	
	@NotBlank(message = "is required")
	private String vehicle_category_Id;
	
	@NotBlank(message = "is required")
	private Date  booking_Date;
	
	@NotBlank(message = "is required")
	private String city_Id;
	

	private String source;
	private String destinationation;
	private String city_Name;
	private String driver_Name;
	private String vehicle_No;
	private String driver_phone_No;
	private String vehicle_type_Name;
	private String customer_Name;
	private String phone_No;
	private String email;
	
	private Double service_Charge;
	
	private Double charge_per_Km;
	
	private Double charges_Calculated;
	
	private Double km_Covered;
	
	public Booking(String booking_Id, String driver_Id, String customer_Id,String source_Location,
			String destinationation_Location, String pickup_Time, String drop_Time,String booking_Status,
			String vehicle_category_Id,Date booking_Date, String city_Id,
			String source, String destinationation, String city_Name, String driver_Name, String vehicle_No,
			String driver_phone_No, String vehicle_type_Name, String customer_Name, String phone_No, String email,Double service_Charge,
			Double charge_per_Km,Double charges_Calculated, Double km_Covered) {
	super();
	this.booking_Id = booking_Id;
	this.driver_Id = driver_Id;
	this.customer_Id = customer_Id;
	this.source_Location = source_Location;
	this.destinationation_Location = destinationation_Location;
	this.pickup_Time = pickup_Time;
	this.drop_Time = drop_Time;
	this.booking_Status = booking_Status;
	this.vehicle_category_Id = vehicle_category_Id;
	this.booking_Date = booking_Date;
	this.city_Id = city_Id;
	this.source = source;
	this.destinationation = destinationation;
	this.city_Name = city_Name;
	this.driver_Name = driver_Name;
	this.vehicle_No = vehicle_No;
	this.driver_phone_No = driver_phone_No;
	this.vehicle_type_Name = vehicle_type_Name;
	this.customer_Name = customer_Name;
	this.phone_No = phone_No;
	this.email = email;
	this.service_Charge = service_Charge;
	this.charge_per_Km = charge_per_Km;
	this.charges_Calculated = charges_Calculated;
	this.km_Covered = km_Covered;
}
	
	public Booking(String booking_Id, String driver_Id, String customer_Id,String source_Location,
				String destinationation_Location, String pickup_Time, String drop_Time,String booking_Status,
				String vehicle_category_Id,Date booking_Date, String city_Id,
				String source, String destinationation, String city_Name, String driver_Name, String vehicle_No,
				String driver_phone_No, String vehicle_type_Name, String customer_Name, String phone_No, String email,Double service_Charge,
				Double charge_per_Km) {
		super();
		this.booking_Id = booking_Id;
		this.driver_Id = driver_Id;
		this.customer_Id = customer_Id;
		this.source_Location = source_Location;
		this.destinationation_Location = destinationation_Location;
		this.pickup_Time = pickup_Time;
		this.drop_Time = drop_Time;
		this.booking_Status = booking_Status;
		this.vehicle_category_Id = vehicle_category_Id;
		this.booking_Date = booking_Date;
		this.city_Id = city_Id;
		this.source = source;
		this.destinationation = destinationation;
		this.city_Name = city_Name;
		this.driver_Name = driver_Name;
		this.vehicle_No = vehicle_No;
		this.driver_phone_No = driver_phone_No;
		this.vehicle_type_Name = vehicle_type_Name;
		this.customer_Name = customer_Name;
		this.phone_No = phone_No;
		this.email = email;
		this.service_Charge = service_Charge;
		this.charge_per_Km = charge_per_Km;
	}

	public  Booking() {
		
	}

	public Booking(String booking_Id, String customer_Id, String source_Location, String destinationation_Location,
			String booking_Status, String vehicle_category_Id) {
		super();
		this.booking_Id = booking_Id;
		this.customer_Id = customer_Id;
		this.source_Location = source_Location;
		this.destinationation_Location = destinationation_Location;
		this.booking_Status = booking_Status;
		this.vehicle_category_Id = vehicle_category_Id;
	}

	public Booking(String booking_Id, String driver_Id, String customer_Id, String source_Location,
			String destinationation_Location, String pickup_Time, String drop_Time, String booking_Status,
			String vehicle_category_Id,Date booking_Date, String city_Id) {
		super();
		this.booking_Id = booking_Id;
		this.driver_Id = driver_Id;
		this.customer_Id = customer_Id;
		this.source_Location = source_Location;
		this.destinationation_Location = destinationation_Location;
		this.pickup_Time = pickup_Time;
		this.drop_Time = drop_Time;
		this.booking_Status = booking_Status;
		this.vehicle_category_Id = vehicle_category_Id;
		this.booking_Date = booking_Date;
		this.city_Id =city_Id;
	}

	public Booking(String booking_Id, String driver_Id, String customer_Id, String source_Location,
			String destinationation_Location, String pickup_Time, String drop_Time, String booking_Status,
			String vehicle_category_Id) {
		super();
		this.booking_Id = booking_Id;
		this.driver_Id = driver_Id;
		this.customer_Id = customer_Id;
		this.source_Location = source_Location;
		this.destinationation_Location = destinationation_Location;
		this.pickup_Time = pickup_Time;
		this.drop_Time = drop_Time;
		this.booking_Status = booking_Status;
		this.vehicle_category_Id = vehicle_category_Id;
	
	}

	public String getCity_Id() {
		return city_Id;
	}

	public void setCity_Id(String city_Id) {
		this.city_Id = city_Id;
	}

	public Date getBooking_Date() {
		return booking_Date;
	}

	public void setBooking_Date(Date booking_Date) {
		this.booking_Date = booking_Date;
	}

	public String getBooking_Id() {
		return booking_Id;
	}

	public void setBooking_Id(String booking_Id) {
		this.booking_Id = booking_Id;
	}

	public String getDriver_Id() {
		return driver_Id;
	}

	public void setDriver_Id(String driver_Id) {
		this.driver_Id = driver_Id;
	}

	public String getCustomer_Id() {
		return customer_Id;
	}

	public void setCustomer_Id(String customer_Id) {
		this.customer_Id = customer_Id;
	}

	public String getSource_Location() {
		return source_Location;
	}

	public void setSource_Location(String source_Location) {
		this.source_Location = source_Location;
	}

	public String getDestinationation_Location() {
		return destinationation_Location;
	}

	public void setDestinationation_Location(String destinationation_Location) {
		this.destinationation_Location = destinationation_Location;
	}

	public String getPickup_Time() {
		return pickup_Time;
	}

	public void setPickup_Time(String pickup_Time) {
		this.pickup_Time = pickup_Time;
	}

	public String getDrop_Time() {
		return drop_Time;
	}

	public void setDrop_Time(String drop_Time) {
		this.drop_Time = drop_Time;
	}

	public String getBooking_Status() {
		return booking_Status;
	}

	public void setBooking_Status(String booking_Status) {
		this.booking_Status = booking_Status;
	}

	public String getVehicle_category_Id() {
		return vehicle_category_Id;
	}

	public void setVehicle_category_Id(String vehicle_category_Id) {
		this.vehicle_category_Id = vehicle_category_Id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestinationation() {
		return destinationation;
	}

	public void setDestinationation(String destinationation) {
		this.destinationation = destinationation;
	}

	public String getCity_Name() {
		return city_Name;
	}

	public void setCity_Name(String city_Name) {
		this.city_Name = city_Name;
	}

	public String getDriver_Name() {
		return driver_Name;
	}

	public void setDriver_Name(String driver_Name) {
		this.driver_Name = driver_Name;
	}

	public String getVehicle_No() {
		return vehicle_No;
	}

	public void setVehicle_No(String vehicle_No) {
		this.vehicle_No = vehicle_No;
	}

	public String getDriver_phone_No() {
		return driver_phone_No;
	}

	public void setDriver_phone_No(String driver_phone_No) {
		this.driver_phone_No = driver_phone_No;
	}

	public String getVehicle_type_Name() {
		return vehicle_type_Name;
	}

	public void setVehicle_type_Name(String vehicle_type_Name) {
		this.vehicle_type_Name = vehicle_type_Name;
	}

	public String getCustomer_Name() {
		return customer_Name;
	}

	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}

	public String getPhone_No() {
		return phone_No;
	}

	public void setPhone_No(String phone_No) {
		this.phone_No = phone_No;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Double getService_Charge() {
		return service_Charge;
	}

	public void setService_Charge(Double service_Charge) {
		this.service_Charge = service_Charge;
	}

	public Double getCharge_per_Km() {
		return charge_per_Km;
	}

	public void setCharge_per_Km(Double charge_per_Km) {
		this.charge_per_Km = charge_per_Km;
	}

	public Double getCharges_Calculated() {
		return charges_Calculated;
	}

	public void setCharges_Calculated(Double charges_Calculated) {
		this.charges_Calculated = charges_Calculated;
	}

	public Double getKm_Covered() {
		return km_Covered;
	}

	public void setKm_Covered(Double km_Covered) {
		this.km_Covered = km_Covered;
	}
	
	
	

}
