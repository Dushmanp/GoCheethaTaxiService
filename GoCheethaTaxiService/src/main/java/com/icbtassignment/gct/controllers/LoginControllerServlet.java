package com.icbtassignment.gct.controllers;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.icbtassignment.gct.entities.Branch;
import com.icbtassignment.gct.entities.City;
import com.icbtassignment.gct.entities.Driver;
import com.icbtassignment.gct.entities.Employee;
import com.icbtassignment.gct.services.BranchService;
import com.icbtassignment.gct.services.CityServices;
import com.icbtassignment.gct.services.DriverService;
import com.icbtassignment.gct.services.EmployeeService;
import com.icbtassignment.gct.services.LoginService;



@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name ="jdbc/gocheetha_application")
    private DataSource dataSource;
    
    public LoginControllerServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("login.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LoginAuthentication(request, response);
	}


	private void LoginAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String user_Name = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		HttpSession session = request.getSession();
		
		try {
		
		if(LoginService.checkAuthentication(dataSource,type,user_Name,password)) {
			
			if(type.equals("Driver")) {
				
				Driver driver = DriverService.getList(dataSource, user_Name);
				Branch branch = BranchService.get(dataSource, driver.getBranch_Id());
				session.setAttribute("Driver_Id", driver.getDriver_Id());
				session.setAttribute("Driver_Name", driver.getDriver_Name());
				session.setAttribute("UserDisplayName", driver.getDriver_Name());
				session.setAttribute("UserContact", driver.getLicence_No());
				session.setAttribute("Licence_No", driver.getLicence_No());
				session.setAttribute("vehicle_category_Id", driver.getvehicle_category_Id());
				session.setAttribute("CityId", branch.getCity_Id());
				response.sendRedirect("BookingControllerServlet?command=SHOW-OnGoing");
				
			}
			if(type.equals("Administration"))
			{
				Employee employee = EmployeeService.getList(dataSource, user_Name);
				
				session.setAttribute("employee_Id", employee.getEmployee_Id());
				session.setAttribute("UserDisplayName", employee.getDisplay_Name());
				session.setAttribute("UserContact", employee.getEmail());
				session.setAttribute("UserType", employee.getUserType());
				response.sendRedirect("index.jsp");
			}			
			
		}
		else {
			request.setAttribute("exceptionerror","User Credinatial incorrect");
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	} catch (Exception e) {
		
		request.setAttribute("exceptionerror", e.toString());
		request.setAttribute("exceptionerrorshow", "");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		
	}
		
	}

}
