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
import com.icbtassignment.gct.entities.City;

import com.icbtassignment.gct.services.BranchService;
import com.icbtassignment.gct.services.CityServices;
import com.icbtassignment.gct.services.CommonServices;

import com.icbtassignment.gct.utils.EntityValidator;


@WebServlet("/BranchControllerServlet")
public class BranchControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name ="jdbc/gocheetha_application")
    private DataSource dataSource;
	
    public BranchControllerServlet() {
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

			List<Branch> branch =  BranchService.getBranch(dataSource,"");
			List<City> cityDrp =  CityServices.getCity(dataSource);
			request.setAttribute("branch_lists", branch);
			request.setAttribute("cityDrp", cityDrp);
			request.setAttribute("exceptionerrorshow", "d-none");
			request.getRequestDispatcher("/branch-list.jsp").forward(request, response);
			
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/branch-list.jsp").forward(request, response);
		}
		
		
		
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<City> cityDrp =  CityServices.getCity(dataSource);
			request.setAttribute("cityDrp", cityDrp);
			
			request.getRequestDispatcher("/add-branch.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/add-branch.jsp").forward(request, response);
		}
			
	}
	
	protected void addData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
		try {
			String branch_Name = request.getParameter("branch_Name");
			if(BranchService.checkBranchName(dataSource, branch_Name, null)) 
			{
				String tableName ="branch";
				String branch_Id = CommonServices.getNumberFormat(dataSource,tableName);
				
				String city_Id = request.getParameter("city_Id");
				Branch branch = new Branch(branch_Id,branch_Name,city_Id);
				
				EntityValidator<Branch> validator = new EntityValidator<Branch>();
				String errors = validator.validate(branch);
				request.setAttribute("exceptionerrorshow", "d-none");
				if(!errors.isEmpty()) {
					request.setAttribute("branch", branch);
					request.setAttribute("error", errors);
					add(request, response);
				}else {
					BranchService.addBranch(dataSource, branch);
					 CommonServices.setNumberFormat(dataSource, tableName);
					 response.sendRedirect("BranchControllerServlet?command=SHOW-LIST");
				}
			}
			else {
				request.setAttribute("exceptionerror", "Branch Name already exists");
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
			String branch_Id= request.getParameter("id");	
			Branch branch = BranchService.get(dataSource,branch_Id);
			
			List<City> cityDrp =  CityServices.getCity(dataSource);
			request.setAttribute("cityDrp", cityDrp);
			
			request.setAttribute("branch", branch);
			
			request.getRequestDispatcher("/update-branch.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exceptionerror", e.toString());
			request.setAttribute("exceptionerrorshow", "");
			request.getRequestDispatcher("/update-branch.jsp").forward(request, response);
		}
		
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String branch_Id = request.getParameter("branch_Id");
			String branch_Name = request.getParameter("branch_Name");
			String city_Id = request.getParameter("city_Id");
			
			Branch branch = new Branch(branch_Id,branch_Name,city_Id);
			try {
				if(BranchService.checkBranchName(dataSource, branch_Name, branch_Id)) 
				{
					EntityValidator<Branch> validator = new EntityValidator<Branch>();
					String errors = validator.validate(branch);
					request.setAttribute("exceptionerrorshow", "d-none");
					if(!errors.isEmpty()) {
						request.setAttribute("branch", branch);
						request.setAttribute("error", errors);
						edit(request, response);
					}else {
						BranchService.updateBranch(dataSource, branch);
						response.sendRedirect("BranchControllerServlet?command=SHOW-LIST");
					}
				}
				else {
					request.setAttribute("branch", branch);
					request.setAttribute("exceptionerror", "Branch Name already exists");
					request.setAttribute("exceptionerrorshow", "");
					edit(request, response);
				}
					
			} catch (Exception e) {
				request.setAttribute("branch", branch);
				request.setAttribute("exceptionerror", e.toString());
				request.setAttribute("exceptionerrorshow", "");

				edit(request, response);
			}
			
		}
	
	protected void deleteList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String branch_Id= request.getParameter("id");	
		BranchService.deleteBranch(dataSource,branch_Id);
		mainList(request,response);
	}
	

	
}
