package com.icbtassignment.gct.controllers;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.icbtassignment.gct.entities.City;
import com.icbtassignment.gct.entities.Employee;
import com.icbtassignment.gct.services.CityServices;
import com.icbtassignment.gct.services.CommonServices;
import com.icbtassignment.gct.services.EmployeeService;
import com.icbtassignment.gct.utils.EntityValidator;


@WebServlet("/EmployeeControllerServlet")
public class EmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name ="jdbc/gocheetha_application")
	private DataSource dataSource;
	
    public EmployeeControllerServlet() {
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
		
		
		
	}
	
	protected void mainList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String employeeID=  (String)session.getAttribute("employee_Id");
		if(employeeID.equals("0000")) {
			try {
				List<Employee> employee =  EmployeeService.getEmployee(dataSource);
				request.setAttribute("employee_lists", employee);
				request.setAttribute("exceptionerrorshow", "d-none");
				request.getRequestDispatcher("/employee-list.jsp").forward(request, response);
			}catch (Exception e) {
				request.setAttribute("exceptionerror", e.toString());
				request.setAttribute("exceptionerrorshow", "");
				request.getRequestDispatcher("/employee-list.jsp").forward(request, response);
			}
			
		}
		else {
			request.setAttribute("exceptionerror", "You dont have access");
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/employee-list.jsp").forward(request, response);
		}
			
		}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String employeeID=  (String)session.getAttribute("employee_Id");
		
		if(employeeID.equals("0000")) {
			request.getRequestDispatcher("/add-employee.jsp").forward(request, response);
		}
		else {
			request.setAttribute("exceptionerror", "You dont have access");
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/employee-list.jsp").forward(request, response);
		}
	}
	
	protected void addData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
				String tableName ="employee";
				
				String userName = request.getParameter("userName");
				try {
					if(EmployeeService.checkUsername(dataSource,userName,null)) {
						
						
							String employee_Id = CommonServices.getNumberFormat(dataSource,tableName);
							String password = request.getParameter("password");
							String email = request.getParameter("email");
							String display_Name = request.getParameter("display_Name");
							String userType = request.getParameter("userType");
							Employee employee = new Employee(employee_Id,userName,password,email,display_Name,userType);
							
							EntityValidator<Employee> validator = new EntityValidator<Employee>();
							String errors = validator.validate(employee);
							
							if(!errors.isEmpty()) {
								request.setAttribute("employee", employee);
								request.setAttribute("error", errors);
								request.getRequestDispatcher("/add-employee.jsp").forward(request, response);
							}else {
								EmployeeService.addEmployee(dataSource, employee);
								 CommonServices.setNumberFormat(dataSource, tableName);
								 response.sendRedirect("EmployeeControllerServlet?command=SHOW-LIST");
							}
						
					}
					else {
						request.setAttribute("exceptionerror","Username already exists");
						request.setAttribute("exceptionerrorshow", "");
					
						add(request, response);
					}
				} catch (Exception e) {
					
					request.setAttribute("exceptionerror", e.toString());
					request.setAttribute("exceptionerrorshow", "");
					add(request, response);
			}
			
		
		}


	
	
	
	protected void deleteList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String employee_Id= request.getParameter("id");	
			EmployeeService.deleteEmployee(dataSource,employee_Id);
			mainList(request,response);
		}
		
	
}
