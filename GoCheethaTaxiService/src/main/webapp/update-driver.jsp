<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Update Driver - GoCheetha Taxi</title>
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
			<jsp:param name="driver" value="active"/>
		</jsp:include>
		<div class="page-wrapper">
					
		<jsp:include page="Shared/searchbar.jsp"></jsp:include>

		<div class="page-content">
	
	        <div class="d-flex justify-content-between align-items-center flex-wrap grid-margin">
		          <div>
		            <h4 class="mb-3 mb-md-0">Update Driver</h4>
		          </div>
            </div>
            
            
    <div class="row">
        <div class="col-md-6 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
					  <form action="DriverControllerServlet" method="post">
		                   <div class="alert alert-danger col-md-12 ${exceptionerrorshow}" id="divShowError" role="alert">
								<span id="showerrormsg"class="text-danger">${exceptionerror}</span>
							</div>
		
	                 		 <div class="form-group row d-none">
                                            <input type="hidden" name="command" value="UPDATEDATA">
                                        </div>

                                        <div class="form-group row d-none">
                                            <label class="col-4 col-form-label" for="txtdriver_Id">Driver ID</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" name="driver_Id" id="txtdriver_Id" value="${driver.driver_Id }">

                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="col-4 col-form-label" for="txtdriver_Name">Driver Name</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" name="driver_Name" id="txtdriver_Name" value="${driver.driver_Name }" placeholder="Enter Driver Name">

                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="col-4 col-form-label" for="txtdriver_Name">Branch Name</label>
                                            <div class="col-8">
                                                <select name="cmbbranchId" id="cmbbranchId" class="form-control">

                                                    <c:forEach items="${branchDrp}" var="branch">
                                                        <option value="${branch.branch_Id}">${branch.branch_Name} - ${branch.city_Name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group row d-none">
                                            <label class="col-4 col-form-label" for="txtbranch_Id">Branch</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" name="branch_Id" id="txtbranch_Id" value="${driver.branch_Id }" placeholder="Enter Branch">

                                            </div>
                                        </div>

                                        <div class="form-group row ">
                                            <label class="col-4 col-form-label" for="cmbvehiclecategoryId">Vehicle Type</label>
                                            <div class="col-8">
                                                <select name="cmbvehiclecategoryId" id="cmbvehiclecategoryId" class="form-control">

                                                    <c:forEach items="${catergoryDrp}" var="category">
                                                        <option value="${category.vehicle_category_Id}">${category.vehicle_type_Name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group row d-none">
                                            <label class="col-4 col-form-label" for="txtvehicle_category_Id">Vehicle Type</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" name="vehicle_category_Id" id="txtvehicle_category_Id" value="${driver.vehicle_category_Id }" placeholder="Enter Vehicle Category">

                                            </div>
                                        </div>


                                        <div class="form-group row">
                                            <label class="col-4 col-form-label" for="txtphone_No">TelePhone No.</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" name="phone_No" id="txtphone_No" value="${driver.phone_No }" placeholder="Enter TelePhone No.">

                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="col-4 col-form-label" for="txtaddress">Address</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" name="address" id="txtaddress" value="${driver.address }" placeholder="Enter Address">

                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="col-4 col-form-label" for="txtlicence_No">Licence No.</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" name="licence_No" id="txtlicence_No" value="${driver.licence_No }" placeholder="Enter Licence No.">

                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="col-4 col-form-label" for="txtpassword">Driver Password</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" name="password" id="txtpassword" value="${driver.password }" placeholder="Enter Driver Password">

                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="col-4 col-form-label" for="txtvehicle_No">Vehicle No.</label>
                                            <div class="col-8">
                                                <input type="text" class="form-control" name="vehicle_No" id="txtvehicle_No" value="${driver.vehicle_No }" placeholder="Enter Vehicle No.">

                                            </div>
                                        </div>

	                         <div class="form-group row ">
	                             <code>${error }</code>
	                         </div>
	                    
	
	                    <div class="form-group row">
	                        <div class="col-4">
	                        </div>
	                        <div class="col-8">
	                            <button type="submit" class="btn btn-primary mr-2">Update</button>
	                            &nbsp;
	                            <a href="DriverControllerServlet">Cancel</a>
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
            get_txtBranchId();
            get_txtCategoryId();
            show_Error();
        });

        function show_Error() {
            var showerror = $("#showerrormsg").text();
            if (showerror == "") {
                $("#divShowError").hide();
            }

        }

        $("#cmbbranchId").change(function (event) {
            set_txtBranchId();
        });


        function set_txtBranchId() {
            var BranchId = $("#cmbbranchId").val();
            $("#txtbranch_Id").val(BranchId);

        }

        function get_txtBranchId() {
            var BranchId = $("#txtbranch_Id").val();
            $('select[name="cmbbranchId"]').first().val(BranchId);


        }




        $("#cmbvehiclecategoryId").change(function (event) {
            set_txtCategoryId();
        });


        function set_txtCategoryId() {
            var CategoryId = $("#cmbvehiclecategoryId").val();
            $("#txtvehicle_category_Id").val(CategoryId);

        }

        function get_txtCategoryId() {
            var CategoryId = $("#txtvehicle_category_Id").val();
            $('select[name="cmbvehiclecategoryId"]').first().val(CategoryId);


        }


    </script>
</body>
</html>
 
