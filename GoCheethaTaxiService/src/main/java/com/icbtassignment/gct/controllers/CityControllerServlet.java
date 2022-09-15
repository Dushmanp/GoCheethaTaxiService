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
import com.icbtassignment.gct.services.CityServices;
import com.icbtassignment.gct.services.CommonServices;
import com.icbtassignment.gct.utils.EntityValidator;

@WebServlet("/CityControllerServlet")
public class CityControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name ="jdbc/gocheetha_application")
    private DataSource dataSource;
	
   
    public CityControllerServlet() {
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
			List<City> city =  CityServices.getCity(dataSource);
			request.setAttribute("city_lists", city);
			request.setAttribute("exceptionerrorshow", "d-none");
			request.getRequestDispatcher("/city-list.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/city-list.jsp").forward(request, response);
		}
		
	
		
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/add-city.jsp").forward(request, response);
		
	}
	
	protected void addData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String tableName ="city";
			
			String city_Name = request.getParameter("city_Name");
			try {
				if(CityServices.checkCityName(dataSource,city_Name,null)) {
					
					
						String city_Id = CommonServices.getNumberFormat(dataSource,tableName);
						City city = new City(city_Id,city_Name);
						
						EntityValidator<City> validator = new EntityValidator<City>();
						String errors = validator.validate(city);
						
						if(!errors.isEmpty()) {
							request.setAttribute("city", city);
							request.setAttribute("error", errors);
							request.getRequestDispatcher("/add-city.jsp").forward(request, response);
						}else {
							CityServices.addCity(dataSource, city);
							 CommonServices.setNumberFormat(dataSource, tableName);
							 response.sendRedirect("CityControllerServlet?command=SHOW-LIST");
						}
					
				}
				else {
					request.setAttribute("exceptionerror","City Name already exists");
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
		String city_Id= request.getParameter("id");	
		City city = CityServices.get(dataSource,city_Id);
		
		request.setAttribute("city", city);
		request.getRequestDispatcher("/update-city.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/update-city.jsp").forward(request, response);
		}
		
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String city_Id = request.getParameter("city_Id");
		String city_Name = request.getParameter("city_Name");
		
		City city = new City(city_Id,city_Name);
		
		try {
			if(CityServices.checkCityName(dataSource,city_Name,city_Id)) {
				EntityValidator<City> validator = new EntityValidator<City>();
				String errors = validator.validate(city);
				request.setAttribute("exceptionerrorshow", "d-none");
				if(!errors.isEmpty()) {
					request.setAttribute("city", city);
					request.setAttribute("error", errors);
					request.getRequestDispatcher("/update-city.jsp").forward(request, response);
				}else {
					CityServices.updateCity(dataSource, city);
					response.sendRedirect("CityControllerServlet?command=SHOW-LIST");
				}
			}else {
				
				request.setAttribute("exceptionerror","City Name already exists" );
				request.setAttribute("exceptionerrorshow", "");
				request.setAttribute("city", city);
				edit(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("city", city);
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			edit(request, response);
		}
			
	}
	
	protected void deleteList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String city_Id= request.getParameter("id");	
		CityServices.deleteCity(dataSource,city_Id);
		mainList(request,response);
	}

	
}
