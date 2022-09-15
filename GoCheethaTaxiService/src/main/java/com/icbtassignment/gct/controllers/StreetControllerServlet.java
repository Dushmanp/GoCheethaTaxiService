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

import com.icbtassignment.gct.entities.City;
import com.icbtassignment.gct.entities.Street;
import com.icbtassignment.gct.services.CityServices;
import com.icbtassignment.gct.services.CommonServices;
import com.icbtassignment.gct.services.StreetService;
import com.icbtassignment.gct.utils.EntityValidator;

@WebServlet("/StreetControllerServlet")
public class StreetControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name ="jdbc/gocheetha_application")
    private DataSource dataSource;
    
    public StreetControllerServlet() {
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
			List<Street> street =  StreetService.getStreet(dataSource,"");
			List<City> cityDrp =  CityServices.getCity(dataSource);
			request.setAttribute("street_lists", street);
			request.setAttribute("cityDrp", cityDrp);
			request.setAttribute("exceptionerrorshow", "d-none");
			request.getRequestDispatcher("/street-list.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/street-list.jsp").forward(request, response);
		}
		
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<City> cityDrp =  CityServices.getCity(dataSource);
			request.setAttribute("cityDrp", cityDrp);
		
			request.getRequestDispatcher("/add-street.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/add-street.jsp").forward(request, response);
		}
		
	}
	
	
	protected void addData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			String street_Name = request.getParameter("street_Name");
			if(StreetService.checkCityName(dataSource, street_Name, null)) 
			{
				String tableName ="street";
				String street_Id = CommonServices.getNumberFormat(dataSource,tableName);
				
				String city_Id = request.getParameter("city_Id");
				Street street = new Street(street_Id,street_Name,city_Id);
				
				EntityValidator<Street> validator = new EntityValidator<Street>();
				String errors = validator.validate(street);
				request.setAttribute("exceptionerrorshow", "d-none");
				if(!errors.isEmpty()) {
					request.setAttribute("street", street);
					request.setAttribute("error", errors);
					add(request, response);
				}else {
					StreetService.addStreet(dataSource, street);
					 CommonServices.setNumberFormat(dataSource, tableName);
					 response.sendRedirect("StreetControllerServlet?command=SHOW-LIST");
				}
			}
			else {
				request.setAttribute("exceptionerror", "Street Name already exists");
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
		String street_Id= request.getParameter("id");	
		Street street = StreetService.get(dataSource,street_Id);
		
		List<City> cityDrp =  CityServices.getCity(dataSource);
		request.setAttribute("cityDrp", cityDrp);
		
		request.setAttribute("street", street);
		request.getRequestDispatcher("/update-street.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/update-street.jsp").forward(request, response);
		}
		
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String street_Id = request.getParameter("street_Id");
		String street_Name = request.getParameter("street_Name");
		String city_Id = request.getParameter("city_Id");
		
		Street street = new Street(street_Id,street_Name,city_Id);
		try {
			if(StreetService.checkCityName(dataSource, street_Name, street_Id)) 
			{
				EntityValidator<Street> validator = new EntityValidator<Street>();
				String errors = validator.validate(street);
				request.setAttribute("exceptionerrorshow", "d-none");
				if(!errors.isEmpty()) {
					request.setAttribute("street", street);
					request.setAttribute("error", errors);
					edit(request, response);
				}else {
					StreetService.updateStreet(dataSource, street);
					response.sendRedirect("StreetControllerServlet?command=SHOW-LIST");
				}
			}
			else {
				request.setAttribute("street", street);
				request.setAttribute("exceptionerror", "Street Name already exists");
				request.setAttribute("exceptionerrorshow", "");
				
				edit(request, response);
			}
				
		} catch (Exception e) {
			request.setAttribute("street", street);
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			edit(request, response);
		}
		
	}
	
	protected void deleteList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String street_Id= request.getParameter("id");	
		StreetService.deleteStreet(dataSource,street_Id);
		mainList(request,response);
	}

	

	
}
