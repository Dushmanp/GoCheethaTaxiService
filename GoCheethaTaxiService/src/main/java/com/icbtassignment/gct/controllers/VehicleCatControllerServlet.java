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


import com.icbtassignment.gct.entities.VehicleCategory;
import com.icbtassignment.gct.services.CommonServices;
import com.icbtassignment.gct.services.VehicleCategoryService;
import com.icbtassignment.gct.utils.EntityValidator;





@WebServlet("/VehicleCatControllerServlet")
public class VehicleCatControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name ="jdbc/gocheetha_application")
    private DataSource dataSource;
	
   
   
    public VehicleCatControllerServlet() {
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
		
		else if(command.equals("SHOW-UPDATE") )
		{
			edit(request,response);
		}
		else if(command.equals("SHOW-ADDDATA") )
		{
			add(request,response);
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
			List<VehicleCategory> vehicleCategory =  VehicleCategoryService.getCategory(dataSource);
			request.setAttribute("Category_lists", vehicleCategory);
			request.setAttribute("exceptionerrorshow", "d-none");
			request.getRequestDispatcher("/vehiclecategory-list.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/vehiclecategory-list.jsp").forward(request, response);
		}
		
		
	}

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/add-vehiclecategory.jsp").forward(request, response);
		
	}
	
	protected void addData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tableName ="vehicle_category";
		
		String vehicle_type_Name = request.getParameter("vehicle_type_Name");
		try {
			if(VehicleCategoryService.checkVehicleCatName(dataSource,vehicle_type_Name,null)) {
				
				
					String vehicle_category_Id = CommonServices.getNumberFormat(dataSource,tableName);
					String service_Charge =request.getParameter("service_Charge")  ;
					String charge_per_Km =request.getParameter("charge_per_Km") ;
					VehicleCategory vehiclecat = new VehicleCategory(vehicle_category_Id,vehicle_type_Name,service_Charge,charge_per_Km);
					
					EntityValidator<VehicleCategory> validator = new EntityValidator<VehicleCategory>();
					String errors = validator.validate(vehiclecat);
					
					if(!errors.isEmpty()) {
						request.setAttribute("vehiclecat", vehiclecat);
						request.setAttribute("error", errors);
						request.getRequestDispatcher("/add-vehiclecategory.jsp").forward(request, response);
					}else {
						VehicleCategoryService.addVehicleCat(dataSource, vehiclecat);
						 CommonServices.setNumberFormat(dataSource, tableName);
						 response.sendRedirect("VehicleCatControllerServlet?command=SHOW-LIST");
					}
				
			}
			else {
				request.setAttribute("exceptionerror","Category Name already exists");
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
		String vehicle_category_Id= request.getParameter("id");	
		VehicleCategory vc = VehicleCategoryService.get(dataSource,vehicle_category_Id);
		
		request.setAttribute("vehiclecat", vc);
		request.getRequestDispatcher("/update-vehiclecategory.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/update-vehiclecategory.jsp").forward(request, response);
		}
		
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String vehicle_category_Id = request.getParameter("vehicle_category_Id");
		String vehicle_type_Name = request.getParameter("vehicle_type_Name");
		String service_Charge =request.getParameter("service_Charge")  ;
		String charge_per_Km =request.getParameter("charge_per_Km") ;
		VehicleCategory vehiclecat = new VehicleCategory(vehicle_category_Id,vehicle_type_Name,service_Charge,charge_per_Km);
		
		
		try {
			if(VehicleCategoryService.checkVehicleCatName(dataSource,vehicle_type_Name,vehicle_category_Id)) {
				EntityValidator<VehicleCategory> validator = new EntityValidator<VehicleCategory>();
				String errors = validator.validate(vehiclecat);
				request.setAttribute("exceptionerrorshow", "d-none");
				if(!errors.isEmpty()) {
					request.setAttribute("vehiclecat", vehiclecat);
					request.setAttribute("error", errors);
					request.getRequestDispatcher("/update-vehiclecategory.jsp").forward(request, response);
				}else {
					VehicleCategoryService.updateVehicleCat(dataSource, vehiclecat);
					response.sendRedirect("VehicleCatControllerServlet?command=SHOW-LIST");
				}
			}else {
				
				request.setAttribute("exceptionerror","Category Name already exists" );
				request.setAttribute("exceptionerrorshow", "");
				request.setAttribute("vehiclecat", vehiclecat);
				edit(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("vehiclecat", vehiclecat);
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			edit(request, response);
		}
			
	}
	
	protected void deleteList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vehicle_category_Id= request.getParameter("id");	
		VehicleCategoryService.deleteVehicleCat(dataSource,vehicle_category_Id);
		mainList(request,response);
	}
}
