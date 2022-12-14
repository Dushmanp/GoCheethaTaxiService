<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Driver Details - GoCheetha Taxi</title>
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
		            <h4 class="mb-3 mb-md-0">Driver Details</h4>
		          </div>
            </div>
            
            
			<div class="row">
			    <div class="col-md-12 grid-margin stretch-card">
			        <div class="card">
			            <div class="card-body p-2 p-md-3">
			
			                <div class="text-center float-md-right">
			                    <a href="DriverControllerServlet?command=SHOW-ADDDATA" class="btn btn-outline-primary btn-icon-text">
			                        <i class="mdi mdi-plus-circle-outline"></i>
			                        Add Driver
			                    </a>
			                </div>
			
			                <div class="row justify-content-md-start justify-content-center">
			
			                    <div class="p-1 pl-0">
			                        <input type="search" class="form-control form-control" id="txtKeyW" autofocus="autofocus" onkeyup="SearchStart()" placeholder="Search Here">
			                    </div>
			
			                </div>
							<div class="alert alert-danger ${exceptionerrorshow}" role="alert">
								<span class="text-danger">${exceptionerror}</span>
							</div>
			              
			                <div id="divRecords" class="m-0 p-0 mt-2">
								<div class="table-responsive">
									<table class="table table-centered table-nowrap table-striped">
										<thead>
											<tr>
													<th class="d-none">Driver ID</th>
                                                    <th class="d-none">Branch ID</th>
                                                    <th class="d-none">Type ID</th>
                                                     <th>Driver Name</th>
                                                    <th>Branch</th>
                                                    <th>Vehicle Type</th>
                                                   

                                                    <th>Mobile Num</th>
                                                    <th>Address</th>
                                                    <th>Licence</th>
                                                
                                                    <th>Vehicle Num </th>
													<th style="width:70px;">Action</th>
											</tr>
										</thead>
										<tbody id="tblBody">
											<c:forEach items="${driver_lists}" var="driver_list">

                                                    <tr id="row_${driver_list.driver_Id}">
                                                       	  <td class="d-none">${driver_list.driver_Id}</td>
	                                                      <td class="d-none">${driver_list.branch_Id}</td>
	                                                      <td class="d-none">${driver_list.vehicle_category_Id}</td>
	                                                      <td>${driver_list.driver_Name}</td>
	                                                      <td>${driver_list.branch_Name} </td>
	                                                      <td>${driver_list.vehicle_type_Name}</td>
	                                                      
	
	                                                      <td>${driver_list.phone_No}</td>
	                                                      <td>${driver_list.address}</td>
	
	
	                                                      <td>${driver_list.licence_No}</td>
	                                                
	                                                      <td>${driver_list.vehicle_No}</td>
															<td>
											                    <a href="DriverControllerServlet?command=SHOW-UPDATE&id=${driver_list.driver_Id}" class="table-icon text-primary"><i class="mdi mdi-square-edit-outline"></i></a>
											                    <a href="javascript:void(0);" onclick="ConfirmDelete('${driver_list.driver_Id}')" class="table-icon text-danger"> <i class="mdi mdi-delete"></i></a>
											                </td>
												</tr>
											</c:forEach>
										</tbody>
										
									</table>
								</div>
			                
			                </div>
			
			            </div>
			        </div>
			    </div>
			</div>

	        <div class="modal fade" id="ConfirmDelete" role="dialog" aria-hidden="true">
			    <div class="modal-dialog modal-dialog-centered">
			        <div class="modal-content">
			            <div class="modal-header">
			                <h4 class="modal-title" id="bottomModalLabel">Confirm Delete</h4>
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">??</button>
			            </div>
			            <div class="modal-body">
			                <p>Are you sure you want to delete this record?</p>
							
			    			<input type="hidden" id="txtID">
			                
			            </div>
			            <div class="modal-footer">
			
			                <button type="button" class="btn btn-primary"  id="bDelete" onclick="DeleteRecord()">Confirm</button>
			                <button type="button" class="btn btn-light" data-dismiss="modal">Cancel</button>
			            </div>
			        </div><!-- /.modal-content -->
			    </div><!-- /.modal-dialog -->
			</div>
		</div>
			
			<jsp:include page="Shared/footer.html"></jsp:include>
		
		</div>
	</div>
	
	
	
 	<script title="ask">
 	
 	 $(document).ready(function () {
			
 		 $("#ConfirmDelete").modal('hide');
     });
 	
	 	
	 	
	 	var ConfirmDelete = function (ID) {
	 		$("#txtID").val(ID);
	        $("#ConfirmDelete").modal('show');
	    }
	 	
	 	
	 	var DeleteRecord = function (){
	 		 	var id = $("#txtID").val();
	           	var command = "DELETE-LIST";

	            var url = 'DriverControllerServlet';

	            var ParamPart = "&";
	            ParamPart = ParamPart + ((command != "") ? '&command=' + command : '');
	            ParamPart = ParamPart + ((id != "") ? '&id=' + id : '');
	         

	            ParamPart = ParamPart.replace("&&", "");

	            window.location.href = url + '?' + ParamPart;
	            event.preventDefault();
	 	}
	 	
	    function SearchStart() {
	        // Declare variables
	        var input, filter, table, tr, td, i, txtValue;
	        input = document.getElementById("txtKeyW");
	        filter = input.value.toUpperCase();
	        table = document.getElementById("tblBody");
	        tr = table.getElementsByTagName("tr");
	
	        for (i = 0; i < tr.length; i++) {
	            td = tr[i].getElementsByTagName("td")[0];
	            td1 = tr[i].getElementsByTagName("td")[1];
	
	            if (td) {
	                txtValue = td.textContent || td.innerText;
	                txtValue = txtValue + ' ' + td1.textContent || td1.innerText;
	                if (txtValue.toUpperCase().indexOf(filter) > -1) {
	                    tr[i].style.display = "";
	                } else {
	                    tr[i].style.display = "none";
	                }
	            }
	        }
	    }

      
    </script>
	
	

</body>
</html>
 