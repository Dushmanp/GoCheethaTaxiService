package com.icbtassignment.gct.controllers;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.icbtassignment.gct.entities.Branch;

import com.icbtassignment.gct.entities.Driver;
import com.icbtassignment.gct.entities.VehicleCategory;
import com.icbtassignment.gct.services.BranchService;
import com.icbtassignment.gct.services.CityServices;
import com.icbtassignment.gct.services.CommonServices;
import com.icbtassignment.gct.services.DriverService;
import com.icbtassignment.gct.services.VehicleCategoryService;
import com.icbtassignment.gct.utils.EntityValidator;



@WebServlet("/DriverControllerServlet")
public class DriverControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name ="jdbc/gocheetha_application")
    private DataSource dataSource; 
    
    public DriverControllerServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command =null;
		command = request.getParameter("command");
		
		if(command == null )
		{
			mainList(request,response);
		}
		else if(command.equals("SHOW-LIST") )
		{
			mainList(request,response);
		}
		
		else if(command.equals("SHOW-ADDDATA") )
		{
			add(request,response);
		}
		else if(command.equals("SHOW-UPDATE") )
		{
			edit(request,response);
		}
		else
		{
			deleteList(request,response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command =request.getParameter("command");
		if(command.equals("ADDDATA") )
		{
			addData(request,response);
		}
		
		if(command.equals("UPDATEDATA") )
		{
			update(request,response);
		}
	
	}
	
	protected void mainList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			List<Driver> driver =  DriverService.getDriver(dataSource,"");
			//List<City> cityDrp =  CityServices.getCity(dataSource);
			request.setAttribute("driver_lists", driver);
			//request.setAttribute("cityDrp", cityDrp);
			request.setAttribute("exceptionerrorshow", "d-none");
			request.getRequestDispatcher("/driver-list.jsp").forward(request, response);
			
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/driver-list.jsp").forward(request, response);
		}
		
		
		
	}

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Branch> branchDrp =  BranchService.getBranch(dataSource,"");
			request.setAttribute("branchDrp", branchDrp);
			
			List<VehicleCategory> catergoryDrp =  VehicleCategoryService.getCategory(dataSource);
			request.setAttribute("catergoryDrp", catergoryDrp);
			
			request.getRequestDispatcher("/add-Driver.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/add-Driver.jsp").forward(request, response);
		}
			
	}
	
	protected void addData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
				
			
				String tableName ="driver";
				String driver_Id  = CommonServices.getNumberFormat(dataSource,tableName);
				String branch_Id = request.getParameter("branch_Id");
				String vehicle_category_Id = request.getParameter("vehicle_category_Id");
				String driver_Name = request.getParameter("driver_Name");
				String phone_No = request.getParameter("phone_No");
				String address = request.getParameter("address");
				String licence_No = request.getParameter("licence_No");
				String password = request.getParameter("password");
				String vehicle_No = request.getParameter("vehicle_No");
				if(DriverService.checkLicenceNo(dataSource,licence_No,null)) {
					
					
				Driver driver = new Driver(driver_Id,branch_Id,vehicle_category_Id,driver_Name,phone_No,address,licence_No,password,vehicle_No);
				
				EntityValidator<Driver> validator = new EntityValidator<Driver>();
				String errors = validator.validate(driver);
				request.setAttribute("exceptionerrorshow", "d-none");
				if(!errors.isEmpty()) {
					request.setAttribute("driver", driver);
					request.setAttribute("error", errors);
					add(request, response);
				}else {
					 DriverService.addDriver(dataSource, driver);
					 CommonServices.setNumberFormat(dataSource, tableName);
					 response.sendRedirect("DriverControllerServlet?command=SHOW-LIST");
				}
				
				}else {
					request.setAttribute("exceptionerror","Licence no already exists");
					request.setAttribute("exceptionerrorshow", "");
				
					add(request, response);
				}
			
			
		} catch (Exception e) {
			
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			add(request, response);
		}
		
			
	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String driver_Id= request.getParameter("id");	
			Driver driver = DriverService.get(dataSource,driver_Id);
			
			List<Branch> branchDrp =  BranchService.getBranch(dataSource,"");
			request.setAttribute("branchDrp", branchDrp);
			
			List<VehicleCategory> catergoryDrp =  VehicleCategoryService.getCategory(dataSource);
			request.setAttribute("catergoryDrp", catergoryDrp);
			
			
			request.setAttribute("driver", driver);
			
			request.getRequestDispatcher("/update-driver.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/update-driver.jsp").forward(request, response);
		}
		
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String driver_Id = request.getParameter("driver_Id");
			String branch_Id = request.getParameter("branch_Id");
			String vehicle_category_Id = request.getParameter("vehicle_category_Id");
			String driver_Name = request.getParameter("driver_Name");
			String phone_No = request.getParameter("phone_No");
			String address = request.getParameter("address");
			String licence_No = request.getParameter("licence_No");
			String password = request.getParameter("password");
			String vehicle_No = request.getParameter("vehicle_No");
				
				
			Driver driver = new Driver(driver_Id,branch_Id,vehicle_category_Id,driver_Name,phone_No,address,licence_No,password,vehicle_No);
			
			try {
				if(DriverService.checkLicenceNo(dataSource,licence_No,driver_Id)) {
						
						EntityValidator<Driver> validator = new EntityValidator<Driver>();
						String errors = validator.validate(driver);
						request.setAttribute("exceptionerrorshow", "d-none");
						if(!errors.isEmpty()) {
							request.setAttribute("driver", driver);
							request.setAttribute("error", errors);
							edit(request, response);
						}else {
							DriverService.updateDriver(dataSource, driver);
							response.sendRedirect("DriverControllerServlet?command=SHOW-LIST");
						}
				}else {
					request.setAttribute("exceptionerror","Licence no already exists");
					request.setAttribute("exceptionerrorshow", "");
				
					edit(request, response);
				}
					
			} catch (Exception e) {
				request.setAttribute("driver", driver);
				request.setAttribute("exceptionerror", e.toString());
				request.setAttribute("exceptionerrorshow", "");

				edit(request, response);
			}
			
		}
	
	protected void deleteList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String driver_Id= request.getParameter("id");	
		DriverService.deleteDriver(dataSource,driver_Id);
		mainList(request,response);
	}
	
	
}
