<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Add Employee - GoCheetha Taxi</title>
	<jsp:include page="Shared/header.html"></jsp:include>
</head>
<body class="sidebar-dark">

	<%
	   response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
		if(session.getAttribute("employee_Id") == null  ){
			response.sendRedirect("login.jsp");
			
		}
	%>
	<div class="main-wrapper">

		<jsp:include page="Shared/sidebar.jsp">
			<jsp:param name="employee" value="active"/>
		</jsp:include>
		<div class="page-wrapper">
					
		<jsp:include page="Shared/searchbar.jsp"></jsp:include>

		<div class="page-content">
	
	        <div class="d-flex justify-content-between align-items-center flex-wrap grid-margin">
		          <div>
		            <h4 class="mb-3 mb-md-0">Add Employee</h4>
		          </div>
            </div>
            
            
    <div class="row">
        <div class="col-md-6 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
					 <form action="EmployeeControllerServlet" method="post">
	                   <div class="alert alert-danger col-md-12 ${exceptionerrorshow}" id="divShowError" role="alert">
							<span id="showerrormsg"class="text-danger">${exceptionerror}</span>
						</div>
	
	                 	<div class="form-group row d-none">
	                       <input type="hidden" name="command" value="ADDDATA">
	                    </div>
	
                        <div class="form-group row d-none">
                            <label class="col-4 col-form-label" for="txtemployee_Id">Employee ID</label>
                            <div class="col-8">
                                <input type="text" class="form-control" name="employee_Id" id="txtemployee_Id" value="${employee.employee_Id }">

                            </div>
                        </div>
	
	                    <div class="form-group row">
                             <label class="col-4 col-form-label" for="txtuserName">UserName</label>
                             <div class="col-8">
                                 <input type="text" class="form-control" name="userName" id="txtuserName" value="${employee.userName }" placeholder="Enter Username">

                             </div>
                         </div>
                         
                          <div class="form-group row">
                             <label class="col-4 col-form-label" for="txtpassword">password</label>
                             <div class="col-8">
                                 <input type="password" class="form-control" name="password" id="txtpassword" value="${employee.password }" placeholder="Enter Password">

                             </div>
                         </div>
                         
                          <div class="form-group row">
                             <label class="col-4 col-form-label" for="txtemail">email</label>
                             <div class="col-8">
                                 <input type="email" class="form-control" name="email" id="txtemail" value="${employee.email }" placeholder="Enter Email">

                             </div>
                         </div>
                         
                          <div class="form-group row">
                             <label class="col-4 col-form-label" for="txtdisplay_Name">Display Name</label>
                             <div class="col-8">
                                 <input type="text" class="form-control" name="display_Name" id="txtdisplay_Name" value="${employee.display_Name }" placeholder="Enter Display Name">

                             </div>
                         </div>
                         
                           <div class="form-group row ">
	                             <label class="col-4 col-form-label" for="txtuserType">User Type</label>
	                             <div class="col-8">
	                                 <select name="userType" class="form-control">
	
	                                 
	                                         <option value="Administrator">Administrator</option>
	                                    	 <option value="Employee">Employee</option>
	                                 </select>
	                             </div>
	                         </div>
	                       
                         
                         <div class="form-group row ">
                             <code>${error }</code>
                         </div>
	                    
	
	                    <div class="form-group row">
	                        <div class="col-4">
	                        </div>
	                        <div class="col-8">
	                            <button type="submit" class="btn btn-primary mr-2">Add</button>
	                            &nbsp;
	                            <a href="CityControllerServlet">Cancel</a>
	                        </div>
	                    </div>
 					</form>

                </div>
            </div>
        </div>
    </div>
            
			
		</div>

			<jsp:include page="Shared/footer.html"></jsp:include>
		
		</div>
	</div>
 	 <script type="text/javascript">

	$(document).ready(function () {

		show_Error();
    });

	function show_Error() {
		var showerror = $("#showerrormsg").text();
		if(showerror ==""){
			$("#divShowError").hide();
		}


	}


    </script>

</body>
</html>
 
