package com.icbtassignment.gct.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.persistence.Convert;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.icbtassignment.gct.entities.Booking;
import com.icbtassignment.gct.entities.Branch;
import com.icbtassignment.gct.entities.City;
import com.icbtassignment.gct.entities.Street;
import com.icbtassignment.gct.entities.VehicleCategory;
import com.icbtassignment.gct.services.BookingService;

import com.icbtassignment.gct.services.BranchService;
import com.icbtassignment.gct.services.CityServices;
import com.icbtassignment.gct.services.CommonServices;
import com.icbtassignment.gct.services.StreetService;
import com.icbtassignment.gct.services.VehicleCategoryService;
import com.icbtassignment.gct.utils.EntityValidator;
import com.icbtassignment.gct.utils.MailSender;

@WebServlet("/BookingControllerServlet")
public class BookingControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name ="jdbc/gocheetha_application")
	private DataSource dataSource;
	
  
    public BookingControllerServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command =null;
		command = request.getParameter("command");

		if(command.equals("SHOW-PENDING") )
		{
			pendingList(request,response);
		}
		if(command.equals("UPDATE-PENDING") )
		{
			updateConfirmData(request,response);
		}
		
		if(command.equals("UPDATE-REJECT") )
		{
			updateRejectData(request,response);
		}
		
		if(command.equals("SHOW-ALL") )
		{
			ShowMainList(request,response);
		}
		
		if(command.equals("SHOW-Driver") )
		{
			ShowBookingDriverList(request,response);
		}
		if(command.equals("SHOW-BOOKING") )
		{
			ShowBookingList(request,response);
		}
		
		if(command.equals("SHOW-BOOKING-BRANCH") )
		{
			ShowBookingBranchList(request,response);
		}
		
		if(command.equals("SHOW-OnGoing") )
		{
			ShowOnGoingList(request, response);
		}
			
			
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command =null;
		command = request.getParameter("command");
		
		if(command.equals("UPDATE-PICKUP") )
		{
			updatePickupData(request,response);
		}
		
		if(command.equals("UPDATE-DROPPED") )
		{
			updateDropData(request,response);
		}
		if(command.equals("UPDATE-COMPLETED") )
		{
			updateCompletedData(request,response);
		}
		
		
		
	}
	

	private void ShowBookingList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String bookingStatus = "Completed";
	
			List<Branch> branchDrp =  BranchService.getBranch(dataSource,"");
			request.setAttribute("branchDrp", branchDrp);
			
				List<Booking> booking =  BookingService.getBooking(dataSource,bookingStatus);
				Double Total =  BookingService.getBookingTotal(dataSource,bookingStatus);
				
				request.setAttribute("booking_lists", booking);
				request.setAttribute("Total", Total);
				request.setAttribute("exceptionerrorshow", "d-none");
				request.getRequestDispatcher("/booking-list.jsp").forward(request, response);
			
			
			
			
			
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/booking-list.jsp").forward(request, response);
		}
		
	}
	
	
	private void ShowBookingBranchList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String bookingStatus = "Completed";
			String branchId =  request.getParameter("branch_Id"); 
			
			String bookingDate = request.getParameter("bookingDate");
			List<Branch> branchDrp =  BranchService.getBranch(dataSource,"");
			request.setAttribute("branchDrp", branchDrp);
			List<Booking> booking =  BookingService.getAllBooking(dataSource,bookingStatus,branchId,bookingDate);
			Double Total =  BookingService.getAllBookingTotal(dataSource,bookingStatus,branchId,bookingDate);
			request.setAttribute("booking_lists", booking);
			request.setAttribute("Total", Total);
			request.setAttribute("exceptionerrorshow", "d-none");
			request.getRequestDispatcher("/booking-list.jsp").forward(request, response);
	
		
			
			
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/booking-list.jsp").forward(request, response);
		}
		
	}
	
	
	private void ShowBookingDriverList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String bookingStatus = "Completed";
			String driverId=(String)session.getAttribute("Driver_Id");
			String bookingDate = request.getParameter("bookingDate");
			List<Branch> branchDrp =  BranchService.getBranch(dataSource,"");
			request.setAttribute("branchDrp", branchDrp);
			List<Booking> booking =  BookingService.getDriverBooking(dataSource,bookingStatus,driverId,bookingDate);
			Double Total =  BookingService.getDriverBookingTotal(dataSource,bookingStatus,driverId,bookingDate);
			request.setAttribute("booking_lists", booking);
			request.setAttribute("Total", Total);
			request.setAttribute("exceptionerrorshow", "d-none");
			request.getRequestDispatcher("/booking-confirmed.jsp").forward(request, response);
	
		
			
			
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/booking-confirmed.jsp").forward(request, response);
		}
		
	}
	
	
	
	
	private void ShowMainList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String bookingStatus = "Completed";
			String driverId=(String)session.getAttribute("Driver_Id");
			
			List<Booking> booking =  BookingService.getBooking(dataSource,bookingStatus,driverId);
			Double Total =  BookingService.getDriverTotal(dataSource,bookingStatus,driverId);
			request.setAttribute("booking_lists", booking);
			request.setAttribute("Total", Total);
			request.setAttribute("exceptionerrorshow", "d-none");
			request.getRequestDispatcher("/booking-confirmed.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/booking-confirmed.jsp").forward(request, response);
		}
		
	}

	private void pendingList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session=request.getSession();  
			String driverId=(String)session.getAttribute("Driver_Id");
			String bookingStatus = "Pending";
			String vehiclecategoryId = (String)session.getAttribute("vehicle_category_Id");
			String CityId = (String)session.getAttribute("CityId");

			List<Booking> booking =  BookingService.getPendingBooking(dataSource,bookingStatus,vehiclecategoryId,CityId);
			request.setAttribute("booking_lists", booking);
			request.setAttribute("exceptionerrorshow", "d-none");
			request.getRequestDispatcher("/booking-pending-list.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/booking-pending-list.jsp").forward(request, response);
		}
		
	}
	

	private void ShowOnGoingList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String driverId=(String)session.getAttribute("Driver_Id"); 
			String driver_Id = driverId;
		
			List<Booking> booking =  BookingService.getOnGoingBooking(dataSource,driverId);
			request.setAttribute("booking_lists", booking);
			request.setAttribute("exceptionerrorshow", "d-none");
			request.getRequestDispatcher("/driver-dashboard.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/driver-dashboard.jsp").forward(request, response);
		}
		
	}

	private void updateConfirmData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();  
		String driverId=(String)session.getAttribute("Driver_Id"); 
		String driver_Id = driverId;
		String booking_Status = "Confirmed";
		String booking_Id = request.getParameter("booking_Id");
		
		
		try {
		
			if(!BookingService.checkPendingBooking(dataSource, driver_Id)) 
			{
				BookingService.updatePendingBooking(dataSource, driver_Id,booking_Status,booking_Id);
				Booking booking =  BookingService.bookingList(dataSource,booking_Id);
				MailSender.SendMail("Booking confirmed - GoCheetha Taxi",  booking);
				response.sendRedirect("BookingControllerServlet?command=SHOW-OnGoing");
			}
			else {
				request.setAttribute("exceptionerror", "Please Complete the On Going Booking");
				request.setAttribute("exceptionerrorshow", "");
				request.getRequestDispatcher("/booking-pending-list.jsp").forward(request, response);
			}
//			
		
		} catch (Exception e) {
		
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/booking-pending-list.jsp").forward(request, response);
		}
		
	}

	private void updatePickupData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			HttpSession session=request.getSession();  
			String driverId=(String)session.getAttribute("Driver_Id"); 
			String pickUpTime =  LocalTime.now().toString();
			String booking_Status = "PickedUp";
			String booking_Id = request.getParameter("booking_Id");
			
			
			try {
			
				
					BookingService.updatePickupTimeBooking(dataSource, pickUpTime,booking_Status,booking_Id);
					Booking booking =  BookingService.bookingList(dataSource,booking_Id);
					MailSender.SendMail("Driver as Picked up - GoCheetha Taxi", booking);
					response.sendRedirect("BookingControllerServlet?command=SHOW-OnGoing");
				
			
			} catch (Exception e) {
			
				request.setAttribute("exceptionerror", e.toString());
				request.setAttribute("exceptionerrorshow", "");
				request.getRequestDispatcher("/driver-dashboard.jsp").forward(request, response);
			}
			
		}
	
	private void updateDropData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();  
		String driverId=(String)session.getAttribute("Driver_Id"); 
		String dropByTime =  LocalTime.now().toString();
		String booking_Status = "Dropped";
		String booking_Id = request.getParameter("booking_Id");
		
		
		try {
		
			
				BookingService.updateDropTimeBooking(dataSource, dropByTime,booking_Status,booking_Id);
				Booking booking =  BookingService.bookingList(dataSource,booking_Id);
				MailSender.SendMail("Driver as Picked up - GoCheetha Taxi", booking);
				response.sendRedirect("BookingControllerServlet?command=SHOW-OnGoing");
			
		
		} catch (Exception e) {
		
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/driver-dashboard.jsp").forward(request, response);
		}
		
	}
	
	private void updateCompletedData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String tableName ="payment";
			String booking_Status = "Completed";
			String booking_Id = request.getParameter("booking_Id");
			Double charges_Calculated  =  Double.parseDouble(request.getParameter("charges_Calculated"));
			Double km_Covered  =  Double.parseDouble(request.getParameter("km_Covered"));
			
			try {
				String payment_Id = CommonServices.getNumberFormat(dataSource,tableName);
				BookingService.updateCompleteBookin(dataSource, booking_Status, booking_Id);
				BookingService.addPayment(dataSource, payment_Id, booking_Id, charges_Calculated, km_Covered);
				Booking booking =  BookingService.bookingList(dataSource,booking_Id);
				MailSender.SendMail("Driver as Completed the Booking - GoCheetha Taxi", booking);
				response.sendRedirect("BookingControllerServlet?command=SHOW-ALL");
			} catch (Exception e) {
				
				request.setAttribute("exceptionerror", e.toString());
				request.setAttribute("exceptionerrorshow", "");
				request.getRequestDispatcher("/driver-dashboard.jsp").forward(request, response);
			}
			
			
		}
	
	private void updateRejectData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		HttpSession session=request.getSession();  
		String driverId=(String)session.getAttribute("Driver_Id"); 
		String driver_Id = driverId;
		String booking_Status = "Rejected";
		String booking_Id = request.getParameter("booking_Id");
		
			try {
			
				
					BookingService.updatePendingBooking(dataSource, driver_Id,booking_Status,booking_Id);
					Booking booking =  BookingService.bookingList(dataSource,booking_Id);
					MailSender.SendMail("Booking Rejected - GoCheetha Taxi",  booking);
					pendingList(request,response);
				
				
			
			} catch (Exception e) {
			
				request.setAttribute("exceptionerror", e.toString());
				request.setAttribute("exceptionerrorshow", "");
				request.getRequestDispatcher("/booking-pending-list.jsp").forward(request, response);
			}
			
		}

	
	
	
}
