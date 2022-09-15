package com.icbtassignment.gct.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.icbtassignment.gct.entities.Booking;

import com.icbtassignment.gct.utils.CustomException;

public class BookingDao {
	
	
	public static List<Booking> getCheckBooking(DataSource dataSource,String bookingStatus,String driverId){
		 
		List<Booking> bookings = new ArrayList<Booking>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT  booking.booking_Id,booking.driver_Id,booking.customer_Id,booking.vehicle_category_Id, \r\n"
					+ "source_Location ,destinationation_Location,pickup_Time,drop_Time,booking_Status,booking_Date,\r\n"
					+ "(select street_Name from street where street_Id = source_Location) as source,\r\n"
					+ "(select street_Name from street where street_Id = destinationation_Location) as destinationation,\r\n"
					+ "\r\n"
					+ "city.city_Name,\r\n"
					+ "driver.driver_Name,driver.vehicle_No,driver.phone_No as driver_phone_No,\r\n"
					+ "vehicle_category.vehicle_type_Name,\r\n"
					+ "customer.customer_Name,customer.phone_No,customer.email,vehicle_category.service_Charge,vehicle_category.charge_per_Km,"
					+ "payment.charges_Calculated, payment.km_Covered \r\n"
					+ "FROM booking \r\n"
					+ "INNER JOIN city on city.city_Id = booking.city_Id \r\n"
					+ "inner JOIN customer on booking.customer_Id=customer.customer_Id\r\n"
					+ "inner JOIN vehicle_category on booking.vehicle_category_Id=vehicle_category.vehicle_category_Id "
					+ "left JOIN payment on booking.booking_Id = payment.booking_Id \r\n"
					+ "left JOIN driver on booking.driver_Id=driver.driver_Id where booking_Status=? and booking.driver_Id =?"
					+ "ORDER BY booking_Date DESC";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bookingStatus);
			
			stmt.setString(2, driverId);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String booking_Id = rset.getString(1);
				String driver_Id = rset.getString(2);
				String customer_Id = rset.getString(3);
				String vehicle_category_Id = rset.getString(4);
				String city_Id = "";
				String source_Location = rset.getString(5);
				String destinationation_Location = rset.getString(6);
				String pickup_Time = rset.getString(7);
				String drop_Time = rset.getString(8);
				String booking_Status = rset.getString(9);
			
				java.sql.Date booking_Date = rset.getDate(10);
				
				
			
				String source = rset.getString(11);
				String destinationation = rset.getString(12);
				String city_Name = rset.getString(13);
				String driver_Name = rset.getString(14);
				String vehicle_No = rset.getString(15);
				String driver_phone_No = rset.getString(16);
				String vehicle_type_Name = rset.getString(17);
				String customer_Name = rset.getString(18);
				String phone_No = rset.getString(19);
				String email = rset.getString(20);
				Double service_Charge = rset.getDouble(21);
				Double charge_per_Km = rset.getDouble(22);
				Double charges_Calculated = rset.getDouble(23);
				Double km_Covered = rset.getDouble(24);
				
				
				
				Booking booking = new Booking(booking_Id,driver_Id,customer_Id,source_Location,
						 destinationation_Location,pickup_Time,drop_Time,booking_Status,
						 vehicle_category_Id,booking_Date,city_Id,
						 source,destinationation,city_Name,driver_Name,vehicle_No,
						 driver_phone_No,vehicle_type_Name,customer_Name,phone_No,email,service_Charge,charge_per_Km,
						 charges_Calculated,km_Covered);
				bookings.add(booking);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return bookings;
}
	
	public static List<Booking> getCheckBooking(DataSource dataSource,String bookingStatus){
		 
		List<Booking> bookings = new ArrayList<Booking>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT  booking.booking_Id,booking.driver_Id,booking.customer_Id,booking.vehicle_category_Id, \r\n"
					+ "source_Location ,destinationation_Location,pickup_Time,drop_Time,booking_Status,booking_Date,\r\n"
					+ "(select street_Name from street where street_Id = source_Location) as source,\r\n"
					+ "(select street_Name from street where street_Id = destinationation_Location) as destinationation,\r\n"
					+ "\r\n"
					+ "city.city_Name,\r\n"
					+ "driver.driver_Name,driver.vehicle_No,driver.phone_No as driver_phone_No,\r\n"
					+ "vehicle_category.vehicle_type_Name,\r\n"
					+ "customer.customer_Name,customer.phone_No,customer.email,vehicle_category.service_Charge,vehicle_category.charge_per_Km,"
					+ "payment.charges_Calculated, payment.km_Covered \r\n"
					+ "FROM booking \r\n"
					+ "INNER JOIN city on city.city_Id = booking.city_Id \r\n"
					+ "inner JOIN customer on booking.customer_Id=customer.customer_Id\r\n"
					+ "inner JOIN vehicle_category on booking.vehicle_category_Id=vehicle_category.vehicle_category_Id "
					+ "inner JOIN payment on payment.booking_Id = booking.booking_Id  \r\n"
					+ "left JOIN driver on booking.driver_Id=driver.driver_Id where booking_Status=?"
					+ "ORDER BY booking_Date DESC";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bookingStatus);
		
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String booking_Id = rset.getString(1);
				String driver_Id = rset.getString(2);
				String customer_Id = rset.getString(3);
				String vehicle_category_Id = rset.getString(4);
				String city_Id = "";
				String source_Location = rset.getString(5);
				String destinationation_Location = rset.getString(6);
				String pickup_Time = rset.getString(7);
				String drop_Time = rset.getString(8);
				String booking_Status = rset.getString(9);
			
				java.sql.Date booking_Date = rset.getDate(10);
				
				
			
				String source = rset.getString(11);
				String destinationation = rset.getString(12);
				String city_Name = rset.getString(13);
				String driver_Name = rset.getString(14);
				String vehicle_No = rset.getString(15);
				String driver_phone_No = rset.getString(16);
				String vehicle_type_Name = rset.getString(17);
				String customer_Name = rset.getString(18);
				String phone_No = rset.getString(19);
				String email = rset.getString(20);
				Double service_Charge = rset.getDouble(21);
				Double charge_per_Km = rset.getDouble(22);
				Double charges_Calculated = rset.getDouble(23);
				Double km_Covered = rset.getDouble(24);
				
				
				
				Booking booking = new Booking(booking_Id,driver_Id,customer_Id,source_Location,
						 destinationation_Location,pickup_Time,drop_Time,booking_Status,
						 vehicle_category_Id,booking_Date,city_Id,
						 source,destinationation,city_Name,driver_Name,vehicle_No,
						 driver_phone_No,vehicle_type_Name,customer_Name,phone_No,email,service_Charge,charge_per_Km,
						 charges_Calculated,km_Covered);
				bookings.add(booking);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return bookings;
}
	
	public static List<Booking> getAllBooking(DataSource dataSource,String bookingStatus,String branch,String bookingDate){
		 
		List<Booking> bookings = new ArrayList<Booking>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT  booking.booking_Id,booking.driver_Id,booking.customer_Id,booking.vehicle_category_Id, \r\n"
					+ "source_Location ,destinationation_Location,pickup_Time,drop_Time,booking_Status,booking_Date,\r\n"
					+ "(select street_Name from street where street_Id = source_Location) as source,\r\n"
					+ "(select street_Name from street where street_Id = destinationation_Location) as destinationation,\r\n"
					+ "\r\n"
					+ "city.city_Name,\r\n"
					+ "driver.driver_Name,driver.vehicle_No,driver.phone_No as driver_phone_No,\r\n"
					+ "vehicle_category.vehicle_type_Name,\r\n"
					+ "customer.customer_Name,customer.phone_No,customer.email,vehicle_category.service_Charge,vehicle_category.charge_per_Km,"
					+ "payment.charges_Calculated, payment.km_Covered \r\n"
					+ "FROM booking \r\n"
					+ "INNER JOIN city on city.city_Id = booking.city_Id \r\n"
					+ "inner JOIN customer on booking.customer_Id=customer.customer_Id\r\n"
					+ "inner JOIN vehicle_category on booking.vehicle_category_Id=vehicle_category.vehicle_category_Id "
					+ "inner JOIN payment on payment.booking_Id = booking.booking_Id  \r\n"
					+ "left JOIN driver on booking.driver_Id=driver.driver_Id where booking_Status=? and driver.branch_Id =? and booking_Date=?"
					+ "ORDER BY booking_Date DESC";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bookingStatus);
			stmt.setString(2, branch);
			stmt.setString(3, bookingDate);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String booking_Id = rset.getString(1);
				String driver_Id = rset.getString(2);
				String customer_Id = rset.getString(3);
				String vehicle_category_Id = rset.getString(4);
				String city_Id = "";
				String source_Location = rset.getString(5);
				String destinationation_Location = rset.getString(6);
				String pickup_Time = rset.getString(7);
				String drop_Time = rset.getString(8);
				String booking_Status = rset.getString(9);
			
				java.sql.Date booking_Date = rset.getDate(10);
				
				
			
				String source = rset.getString(11);
				String destinationation = rset.getString(12);
				String city_Name = rset.getString(13);
				String driver_Name = rset.getString(14);
				String vehicle_No = rset.getString(15);
				String driver_phone_No = rset.getString(16);
				String vehicle_type_Name = rset.getString(17);
				String customer_Name = rset.getString(18);
				String phone_No = rset.getString(19);
				String email = rset.getString(20);
				Double service_Charge = rset.getDouble(21);
				Double charge_per_Km = rset.getDouble(22);
				Double charges_Calculated = rset.getDouble(23);
				Double km_Covered = rset.getDouble(24);
				
				
				
				Booking booking = new Booking(booking_Id,driver_Id,customer_Id,source_Location,
						 destinationation_Location,pickup_Time,drop_Time,booking_Status,
						 vehicle_category_Id,booking_Date,city_Id,
						 source,destinationation,city_Name,driver_Name,vehicle_No,
						 driver_phone_No,vehicle_type_Name,customer_Name,phone_No,email,service_Charge,charge_per_Km,
						 charges_Calculated,km_Covered);
				bookings.add(booking);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return bookings;
}

	public static Double getAllBookingTotal(DataSource dataSource,String bookingStatus,String branch,String bookingDate){
		 
		
		Double Total=0.0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT  "
					+"sum(payment.charges_Calculated) \r\n"
					+ "FROM booking \r\n"
					+ "INNER JOIN city on city.city_Id = booking.city_Id \r\n"
					+ "inner JOIN customer on booking.customer_Id=customer.customer_Id\r\n"
					+ "inner JOIN vehicle_category on booking.vehicle_category_Id=vehicle_category.vehicle_category_Id "
					+ "inner JOIN payment on payment.booking_Id = booking.booking_Id  \r\n"
					+ "left JOIN driver on booking.driver_Id=driver.driver_Id where booking_Status=? and driver.branch_Id =? and booking_Date=?";
				
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bookingStatus);
			stmt.setString(2, branch);
			stmt.setString(3, bookingDate);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				Total = rset.getDouble(1);
				
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return Total;
}
	
	public static List<Booking> getDriverBooking(DataSource dataSource,String bookingStatus,String driverId,String bookingDate){
		 
		List<Booking> bookings = new ArrayList<Booking>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT  booking.booking_Id,booking.driver_Id,booking.customer_Id,booking.vehicle_category_Id, \r\n"
					+ "source_Location ,destinationation_Location,pickup_Time,drop_Time,booking_Status,booking_Date,\r\n"
					+ "(select street_Name from street where street_Id = source_Location) as source,\r\n"
					+ "(select street_Name from street where street_Id = destinationation_Location) as destinationation,\r\n"
					+ "\r\n"
					+ "city.city_Name,\r\n"
					+ "driver.driver_Name,driver.vehicle_No,driver.phone_No as driver_phone_No,\r\n"
					+ "vehicle_category.vehicle_type_Name,\r\n"
					+ "customer.customer_Name,customer.phone_No,customer.email,vehicle_category.service_Charge,vehicle_category.charge_per_Km,"
					+ "payment.charges_Calculated, payment.km_Covered \r\n"
					+ "FROM booking \r\n"
					+ "INNER JOIN city on city.city_Id = booking.city_Id \r\n"
					+ "inner JOIN customer on booking.customer_Id=customer.customer_Id\r\n"
					+ "inner JOIN vehicle_category on booking.vehicle_category_Id=vehicle_category.vehicle_category_Id "
					+ "inner JOIN payment on payment.booking_Id = booking.booking_Id  \r\n"
					+ "left JOIN driver on booking.driver_Id=driver.driver_Id where booking_Status=? and booking.driver_Id =? and booking_Date=?"
					+ "ORDER BY booking_Date DESC";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bookingStatus);
			stmt.setString(2, driverId);
			stmt.setString(3, bookingDate);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String booking_Id = rset.getString(1);
				String driver_Id = rset.getString(2);
				String customer_Id = rset.getString(3);
				String vehicle_category_Id = rset.getString(4);
				String city_Id = "";
				String source_Location = rset.getString(5);
				String destinationation_Location = rset.getString(6);
				String pickup_Time = rset.getString(7);
				String drop_Time = rset.getString(8);
				String booking_Status = rset.getString(9);
			
				java.sql.Date booking_Date = rset.getDate(10);
				
				
			
				String source = rset.getString(11);
				String destinationation = rset.getString(12);
				String city_Name = rset.getString(13);
				String driver_Name = rset.getString(14);
				String vehicle_No = rset.getString(15);
				String driver_phone_No = rset.getString(16);
				String vehicle_type_Name = rset.getString(17);
				String customer_Name = rset.getString(18);
				String phone_No = rset.getString(19);
				String email = rset.getString(20);
				Double service_Charge = rset.getDouble(21);
				Double charge_per_Km = rset.getDouble(22);
				Double charges_Calculated = rset.getDouble(23);
				Double km_Covered = rset.getDouble(24);
				
				
				
				Booking booking = new Booking(booking_Id,driver_Id,customer_Id,source_Location,
						 destinationation_Location,pickup_Time,drop_Time,booking_Status,
						 vehicle_category_Id,booking_Date,city_Id,
						 source,destinationation,city_Name,driver_Name,vehicle_No,
						 driver_phone_No,vehicle_type_Name,customer_Name,phone_No,email,service_Charge,charge_per_Km,
						 charges_Calculated,km_Covered);
				bookings.add(booking);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return bookings;
}

	public static Double getDriverBookingTotal(DataSource dataSource,String bookingStatus,String driverId,String bookingDate){
		 
		
		Double Total=0.0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT  "
					+"sum(payment.charges_Calculated) \r\n"
					+ "FROM booking \r\n"
					+ "INNER JOIN city on city.city_Id = booking.city_Id \r\n"
					+ "inner JOIN customer on booking.customer_Id=customer.customer_Id\r\n"
					+ "inner JOIN vehicle_category on booking.vehicle_category_Id=vehicle_category.vehicle_category_Id "
					+ "inner JOIN payment on payment.booking_Id = booking.booking_Id  \r\n"
					+ "left JOIN driver on booking.driver_Id=driver.driver_Id where booking_Status=? and booking.driver_Id =? and booking_Date=?";
				
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bookingStatus);
			stmt.setString(2, driverId);
			stmt.setString(3, bookingDate);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				Total = rset.getDouble(1);
				
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return Total;
}
	
	public static Double getDriverTotal(DataSource dataSource,String bookingStatus,String driverId){
		 
		
		Double Total=0.0;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT  "
					+"sum(payment.charges_Calculated) \r\n"
					+ "FROM booking \r\n"
					+ "INNER JOIN city on city.city_Id = booking.city_Id \r\n"
					+ "inner JOIN customer on booking.customer_Id=customer.customer_Id\r\n"
					+ "inner JOIN vehicle_category on booking.vehicle_category_Id=vehicle_category.vehicle_category_Id "
					+ "inner JOIN payment on payment.booking_Id = booking.booking_Id  \r\n"
					+ "left JOIN driver on booking.driver_Id=driver.driver_Id where booking_Status=? and booking.driver_Id =?";
				
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bookingStatus);
			stmt.setString(2, driverId);
			
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				Total = rset.getDouble(1);
				
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return Total;
}
	
	
	public static Double getBookingTotal(DataSource dataSource,String bookingStatus){
			 
			
			Double Total=0.0;
			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rset  = null;
			String sql = null;
			
			try {
				con = dataSource.getConnection();
				sql= "SELECT  "
						+"sum(payment.charges_Calculated) \r\n"
						+ "FROM booking \r\n"
						+ "INNER JOIN city on city.city_Id = booking.city_Id \r\n"
						+ "inner JOIN customer on booking.customer_Id=customer.customer_Id\r\n"
						+ "inner JOIN vehicle_category on booking.vehicle_category_Id=vehicle_category.vehicle_category_Id "
						+ "inner JOIN payment on payment.booking_Id = booking.booking_Id  \r\n"
						+ "left JOIN driver on booking.driver_Id=driver.driver_Id where booking_Status=? ";
					
				stmt = con.prepareStatement(sql);
				stmt.setString(1, bookingStatus);
				
				rset= stmt.executeQuery();
				
				while(rset.next()) {
					Total = rset.getDouble(1);
					
					
				}	
				
			} catch (Exception e) {
				throw new CustomException(e.getMessage());
			}finally {
				
				close(con,stmt,null);
			}
			
			return Total;
	}

	
	
	public static Booking bookingList(DataSource dataSource,String bookingId){
		 
		Booking bookings = null;
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT  booking_Id,booking.driver_Id,booking.customer_Id,booking.vehicle_category_Id, \r\n"
					+ "source_Location ,destinationation_Location,pickup_Time,drop_Time,booking_Status,booking_Date,\r\n"
					+ "(select street_Name from street where street_Id = source_Location) as source,\r\n"
					+ "(select street_Name from street where street_Id = destinationation_Location) as destinationation,\r\n"
					+ "\r\n"
					+ "city.city_Name,\r\n"
					+ "driver.driver_Name,driver.vehicle_No,driver.phone_No as driver_phone_No,\r\n"
					+ "vehicle_category.vehicle_type_Name,\r\n"
					+ "customer.customer_Name,customer.phone_No,customer.email,vehicle_category.service_Charge,vehicle_category.charge_per_Km \r\n"
					+ "FROM booking \r\n"
					+ "INNER JOIN city on city.city_Id = booking.city_Id \r\n"
					+ "inner JOIN customer on booking.customer_Id=customer.customer_Id\r\n"
					+ "inner JOIN vehicle_category on booking.vehicle_category_Id=vehicle_category.vehicle_category_Id \r\n"
					+ "left JOIN driver on booking.driver_Id=driver.driver_Id where booking_Id=? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bookingId);
			
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String booking_Id = rset.getString(1);
				String driver_Id = rset.getString(2);
				String customer_Id = rset.getString(3);
				String vehicle_category_Id = rset.getString(4);
				String city_Id = "";
				String source_Location = rset.getString(5);
				String destinationation_Location = rset.getString(6);
				String pickup_Time = rset.getString(7);
				String drop_Time = rset.getString(8);
				String booking_Status = rset.getString(9);
			
				java.sql.Date booking_Date = rset.getDate(10);
				
				
			
				String source = rset.getString(11);
				String destinationation = rset.getString(12);
				String city_Name = rset.getString(13);
				String driver_Name = rset.getString(14);
				String vehicle_No = rset.getString(15);
				String driver_phone_No = rset.getString(16);
				String vehicle_type_Name = rset.getString(17);
				String customer_Name = rset.getString(18);
				String phone_No = rset.getString(19);
				String email = rset.getString(20);
				Double service_Charge = rset.getDouble(21);
				Double charge_per_Km = rset.getDouble(22);
				
				
				
				 bookings = new Booking(booking_Id,driver_Id,customer_Id,source_Location,
						 destinationation_Location,pickup_Time,drop_Time,booking_Status,
						 vehicle_category_Id,booking_Date,city_Id,
						 source,destinationation,city_Name,driver_Name,vehicle_No,
						 driver_phone_No,vehicle_type_Name,customer_Name,phone_No,email,service_Charge,charge_per_Km
						);
				
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return bookings;
}
	
	public static List<Booking> getOnGoingBooking(DataSource dataSource,String driverId){
		 
		List<Booking> bookings = new ArrayList<Booking>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT  booking_Id,booking.driver_Id,booking.customer_Id,booking.vehicle_category_Id, \r\n"
					+ "source_Location ,destinationation_Location,pickup_Time,drop_Time,booking_Status,booking_Date,\r\n"
					+ "(select street_Name from street where street_Id = source_Location) as source,\r\n"
					+ "(select street_Name from street where street_Id = destinationation_Location) as destinationation,\r\n"
					+ "\r\n"
					+ "city.city_Name,\r\n"
					+ "driver.driver_Name,driver.vehicle_No,driver.phone_No as driver_phone_No,\r\n"
					+ "vehicle_category.vehicle_type_Name,\r\n"
					+ "customer.customer_Name,customer.phone_No,customer.email,vehicle_category.service_Charge,vehicle_category.charge_per_Km \r\n"
					+ "FROM booking \r\n"
					+ "INNER JOIN city on city.city_Id = booking.city_Id \r\n"
					+ "inner JOIN customer on booking.customer_Id=customer.customer_Id\r\n"
					+ "inner JOIN vehicle_category on booking.vehicle_category_Id=vehicle_category.vehicle_category_Id \r\n"
					+ "left JOIN driver on booking.driver_Id=driver.driver_Id where booking_Status !='Pending' and booking_Status !='Rejected' and booking_Status !='Completed' and booking.driver_Id =?";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, driverId);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String booking_Id = rset.getString(1);
				String driver_Id = rset.getString(2);
				String customer_Id = rset.getString(3);
				String vehicle_category_Id = rset.getString(4);
				String city_Id = "";
				String source_Location = rset.getString(5);
				String destinationation_Location = rset.getString(6);
				String pickup_Time = rset.getString(7);
				String drop_Time = rset.getString(8);
				String booking_Status = rset.getString(9);
			
				java.sql.Date booking_Date = rset.getDate(10);
				
				
			
				String source = rset.getString(11);
				String destinationation = rset.getString(12);
				String city_Name = rset.getString(13);
				String driver_Name = rset.getString(14);
				String vehicle_No = rset.getString(15);
				String driver_phone_No = rset.getString(16);
				String vehicle_type_Name = rset.getString(17);
				String customer_Name = rset.getString(18);
				String phone_No = rset.getString(19);
				String email = rset.getString(20);
				Double service_Charge = rset.getDouble(21);
				Double charge_per_Km = rset.getDouble(22);
				
				
				Booking booking = new Booking(booking_Id,driver_Id,customer_Id,source_Location,
						 destinationation_Location,pickup_Time,drop_Time,booking_Status,
						 vehicle_category_Id,booking_Date,city_Id,
						 source,destinationation,city_Name,driver_Name,vehicle_No,
						 driver_phone_No,vehicle_type_Name,customer_Name,phone_No,email,service_Charge,charge_per_Km
						);
				bookings.add(booking);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return bookings;
}

	public static List<Booking> getPendingBooking(DataSource dataSource,String bookingStatus,String vehiclecategoryId, String cityId){
		 
		List<Booking> bookings = new ArrayList<Booking>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "SELECT  booking.booking_Id,booking.driver_Id,booking.customer_Id,booking.vehicle_category_Id, \r\n"
					+ "source_Location ,destinationation_Location,pickup_Time,drop_Time,booking_Status,booking_Date,\r\n"
					+ "(select street_Name from street where street_Id = source_Location) as source,\r\n"
					+ "(select street_Name from street where street_Id = destinationation_Location) as destinationation,\r\n"
					+ "\r\n"
					+ "city.city_Name,\r\n"
					+ "driver.driver_Name,driver.vehicle_No,driver.phone_No as driver_phone_No,\r\n"
					+ "vehicle_category.vehicle_type_Name,\r\n"
					+ "customer.customer_Name,customer.phone_No,customer.email,vehicle_category.service_Charge,vehicle_category.charge_per_Km,"
					+ "payment.charges_Calculated, payment.km_Covered,booking.city_Id \r\n"
					+ "FROM booking \r\n"
					+ "INNER JOIN city on city.city_Id = booking.city_Id \r\n"
					+ "inner JOIN customer on booking.customer_Id=customer.customer_Id\r\n"
					+ "inner JOIN vehicle_category on booking.vehicle_category_Id=vehicle_category.vehicle_category_Id "
					+ "left JOIN payment on booking.booking_Id = payment.booking_Id  \r\n"
					+ "left JOIN driver on booking.driver_Id=driver.driver_Id where booking_Status=? and vehicle_category.vehicle_category_Id =? and booking.city_Id=? "
					+ "ORDER BY booking_Date   and booking.booking_Id  asc";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bookingStatus);
			stmt.setString(2, vehiclecategoryId);
			stmt.setString(3, cityId);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String booking_Id = rset.getString(1);
				String driver_Id = rset.getString(2);
				String customer_Id = rset.getString(3);
				String vehicle_category_Id = rset.getString(4);
				
				String source_Location = rset.getString(5);
				String destinationation_Location = rset.getString(6);
				String pickup_Time = rset.getString(7);
				String drop_Time = rset.getString(8);
				String booking_Status = rset.getString(9);
			
				java.sql.Date booking_Date = rset.getDate(10);
				
				
			
				String source = rset.getString(11);
				String destinationation = rset.getString(12);
				String city_Name = rset.getString(13);
				String driver_Name = rset.getString(14);
				String vehicle_No = rset.getString(15);
				String driver_phone_No = rset.getString(16);
				String vehicle_type_Name = rset.getString(17);
				String customer_Name = rset.getString(18);
				String phone_No = rset.getString(19);
				String email = rset.getString(20);
				Double service_Charge = rset.getDouble(21);
				Double charge_per_Km = rset.getDouble(22);
				Double charges_Calculated = rset.getDouble(23);
				Double km_Covered = rset.getDouble(24);
				String city_Id = rset.getString(25);
				
				
				Booking booking = new Booking(booking_Id,driver_Id,customer_Id,source_Location,
						 destinationation_Location,pickup_Time,drop_Time,booking_Status,
						 vehicle_category_Id,booking_Date,city_Id,
						 source,destinationation,city_Name,driver_Name,vehicle_No,
						 driver_phone_No,vehicle_type_Name,customer_Name,phone_No,email,service_Charge,charge_per_Km,
						 charges_Calculated,km_Covered);
				bookings.add(booking);
				
			}	
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
			close(con,stmt,null);
		}
		
		return bookings;
}
	
	public static int checkPendingBooking(DataSource dataSource, String driverId) {
			
			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rset  = null;
			String sql = null;
			int returnVal = 0;
			
			try {
				con = dataSource.getConnection();
				sql= "select * from Booking where  driver_Id=? and booking_Status ='Confirmed' or booking_Status ='PickedUp' or booking_Status ='Dropped' ";
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, driverId);
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
	
	public static void updatePendingBooking(DataSource dataSource, String driver_Id, String booking_Status,String booking_Id) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				
				con = dataSource.getConnection();
				sql= "UPDATE booking Set driver_Id =?,booking_Status=? where booking_Id=?";
						
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, driver_Id);
				pstmt.setString(2, booking_Status);
				pstmt.setString(3, booking_Id);
				
				pstmt.execute();
				
			} catch (Exception e) {
				throw new CustomException(e.getMessage());
			}finally {
				
					close(con,pstmt,null);
			}
			
		}

	public static void updatePickupTimeBooking(DataSource dataSource, String pickup, String booking_Status,String booking_Id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			
			con = dataSource.getConnection();
			sql= "UPDATE booking Set pickup_Time =?,booking_Status=? where booking_Id=?";
					
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pickup);
			pstmt.setString(2, booking_Status);
			pstmt.setString(3, booking_Id);
			
			pstmt.execute();
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
				close(con,pstmt,null);
		}
		
	}

	public static void updateDropTimeBooking(DataSource dataSource, String dropTime, String booking_Status,String booking_Id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			
			con = dataSource.getConnection();
			sql= "UPDATE booking Set drop_Time =?,booking_Status=? where booking_Id=?";
					
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dropTime);
			pstmt.setString(2, booking_Status);
			pstmt.setString(3, booking_Id);
			
			pstmt.execute();
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
				close(con,pstmt,null);
		}
		
	}
	
	public static void updateCompleteBooking(DataSource dataSource, String booking_Status,String booking_Id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			
			con = dataSource.getConnection();
			sql= "UPDATE booking Set booking_Status=? where booking_Id=?";
					
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, booking_Status);
			pstmt.setString(2, booking_Id);
			
			pstmt.execute();
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
				close(con,pstmt,null);
		}
		
	}

	public static void addPayment(DataSource dataSource,String payment_Id,String booking_Id,Double charges_Calculated,Double km_Covered) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			
			con = dataSource.getConnection();
			sql= "INSERT INTO payment (payment_Id, booking_Id,charges_Calculated,km_Covered) VALUES (?,?,?,?)";
					
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, payment_Id);
			pstmt.setString(2, booking_Id);
			pstmt.setDouble(3, charges_Calculated);
			pstmt.setDouble(4, km_Covered);
			pstmt.execute();
			
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}finally {
			
				close(con,pstmt,null);
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
			throw new CustomException(e.getMessage());
		}
		
	}


	
}
