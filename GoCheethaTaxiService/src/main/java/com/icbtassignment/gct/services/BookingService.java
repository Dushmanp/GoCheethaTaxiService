package com.icbtassignment.gct.services;

import java.util.List;

import javax.sql.DataSource;

import com.icbtassignment.gct.dao.BookingDao;
import com.icbtassignment.gct.dao.BranchDao;
import com.icbtassignment.gct.entities.Booking;


public class BookingService {
	
	public static List<Booking> getBooking(DataSource dataSource,String bookingStatus,String driverId){
			
			return BookingDao.getCheckBooking(dataSource,bookingStatus,driverId);
	}
	
	public static List<Booking> getBooking(DataSource dataSource,String bookingStatus){
		
		return BookingDao.getCheckBooking(dataSource,bookingStatus);
	}
	
	public static List<Booking> getAllBooking(DataSource dataSource,String bookingStatus,String branch,String bookingDate){
	
		return BookingDao.getAllBooking(dataSource,bookingStatus,branch,bookingDate);
	}
	
	public static Double getAllBookingTotal(DataSource dataSource,String bookingStatus,String branch,String bookingDate){
		return BookingDao.getAllBookingTotal(dataSource,bookingStatus,branch,bookingDate);
	}
	
	public static Double getBookingTotal(DataSource dataSource,String bookingStatus){
		return BookingDao.getBookingTotal(dataSource,bookingStatus);
	}
	
	
	public static List<Booking> getDriverBooking(DataSource dataSource,String bookingStatus,String driverId,String bookingDate){
		return BookingDao.getDriverBooking(dataSource,bookingStatus,driverId,bookingDate);
	}
	
	public static Double getDriverBookingTotal(DataSource dataSource,String bookingStatus,String driverId,String bookingDate){
		return BookingDao.getDriverBookingTotal(dataSource,bookingStatus,driverId,bookingDate);
	}
	
	public static Double getDriverTotal(DataSource dataSource,String bookingStatus,String driverId){
		return BookingDao.getDriverTotal(dataSource,bookingStatus,driverId);
	}
	
	
	public static List<Booking> getPendingBooking(DataSource dataSource,String bookingStatus,String vehiclecategoryId,String CityId){
		return BookingDao.getPendingBooking(dataSource,bookingStatus, vehiclecategoryId,CityId);
	}
	
	
	
	public static List<Booking> getOnGoingBooking(DataSource dataSource,String driverId){
		
		return BookingDao.getOnGoingBooking(dataSource,driverId);
	}
	
	public static Booking bookingList(DataSource dataSource, String bookingId) {
	
		return BookingDao.bookingList(dataSource,bookingId);
	}
	
	public static boolean checkPendingBooking(DataSource dataSource, String driverId) {
		
		int checkPending ;
	
		checkPending =	BookingDao.checkPendingBooking(dataSource,driverId);
		
		if(checkPending == 0) {
			
			return false ;
		}
		else {
			return true;
		}
	}
	
	public static void updatePendingBooking(DataSource dataSource, String driver_Id,String booking_Status,String booking_Id){
		
		BookingDao.updatePendingBooking(dataSource,driver_Id,booking_Status,booking_Id);
}

	public static void updatePickupTimeBooking(DataSource dataSource, String pickup, String booking_Status,String booking_Id){
			
			BookingDao.updatePickupTimeBooking(dataSource,pickup,booking_Status,booking_Id);
	}
	
	public static void updateDropTimeBooking(DataSource dataSource, String dropTime, String booking_Status,String booking_Id){
		
		BookingDao.updateDropTimeBooking(dataSource,dropTime,booking_Status,booking_Id);
	}

	public static void updateCompleteBookin(DataSource dataSource, String booking_Status,String booking_Id){
		
		BookingDao.updateCompleteBooking(dataSource,booking_Status,booking_Id);
}
	public static void addPayment(DataSource dataSource, String payment_Id,String booking_Id,Double charges_Calculated,Double km_Covered){
			
			BookingDao.addPayment(dataSource,payment_Id,booking_Id,charges_Calculated,km_Covered);
	}

	
}
