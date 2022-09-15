<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Add Vehicle Category - GoCheetha Taxi</title>
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
			<jsp:param name="vehiclecategory" value="active"/>
		</jsp:include>
		<div class="page-wrapper">
					
		<jsp:include page="Shared/searchbar.jsp"></jsp:include>

		<div class="page-content">
	
	        <div class="d-flex justify-content-between align-items-center flex-wrap grid-margin">
		          <div>
		            <h4 class="mb-3 mb-md-0">Add Vehicle Category</h4>
		          </div>
            </div>
            
            
    <div class="row">
        <div class="col-md-6 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
					  <form action="VehicleCatControllerServlet" method="post">
		                   <div class="alert alert-danger col-md-12 ${exceptionerrorshow}" id="divShowError" role="alert">
								<span id="showerrormsg"class="text-danger">${exceptionerror}</span>
							</div>
		
	                 		<div class="form-group row d-none">
	                             <input type="hidden" name="command" value="ADDDATA">
	                         </div>
							
							<div class="form-group row d-none">
                                   <label class="col-4 col-form-label" for="txtcity_Id">Vehicle Category ID</label>
                                   <div class="col-8">
                                       <input type="text" class="form-control" name="vehicle_category_Id" id="txtvehicle_category_Id" value="${vehiclecat.vehicle_category_Id }">

                                 </div>
                             </div>

                             <div class="form-group row">
                                 <label class="col-4 col-form-label" for="txtvehicle_type_Name">Vehicle Category Type</label>
                                 <div class="col-8">
                                     <input type="text" class="form-control" name="vehicle_type_Name" id="txtvehicle_type_Name" value="${vehiclecat.vehicle_type_Name }" placeholder="Enter Vehicle Type">

                                 </div>
                             </div>
                             <div class="form-group row">
                                 <label class="col-4 col-form-label" for="txtservice_Charge">Service Charge</label>
                                 <div class="col-8">
                                     <input type="number" class="form-control" name="service_Charge" id="txtservice_Charge" value="${vehiclecat.service_Charge }" placeholder="Enter Service Charge">

                                 </div>
                             </div>
                             <div class="form-group row">
                                 <label class="col-4 col-form-label" for="txtcharge_per_Km">Charge per Km</label>
                                 <div class="col-8">
                                     <input type="number" class="form-control" name="charge_per_Km" id="txtcharge_per_Km" value="${vehiclecat.charge_per_Km }" placeholder="Enter Charge per KM">

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
	                            <a href="VehicleCatControllerServlet">Cancel</a>
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
 

 
 
 
 