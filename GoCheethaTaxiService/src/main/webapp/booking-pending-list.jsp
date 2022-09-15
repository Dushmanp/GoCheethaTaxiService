<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Booking Pending - GoCheetha Taxi</title>
	<jsp:include page="Shared/header.html"></jsp:include>
</head>
<body class="sidebar-dark">

	 <%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    if(session.getAttribute("Driver_Id") == null  ){
    response.sendRedirect("login.jsp");

    }
    %>
	<div class="main-wrapper">

		<jsp:include page="Shared/sidebar-driver.jsp">
			<jsp:param name="pending" value="inactive"/>
		</jsp:include>
		<div class="page-wrapper">
					
		<jsp:include page="Shared/searchbar.jsp"></jsp:include>

		<div class="page-content">
	
	        <div class="d-flex justify-content-between align-items-center flex-wrap grid-margin">
		          <div>
		            <h4 class="mb-3 mb-md-0">Booking Pending</h4>
		          </div>
            </div>
            
            
		<div class="row">
		    <div class="col-md-12 grid-margin stretch-card">
		        <div class="card">
		            <div class="card-body p-2 p-md-3">
		
		               
		
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
											<th>Booking No.</th>
                                               <th>Customer</th>
                                               <th>Pickup Location</th>
                                               <th>Dropping Location</th>
                                               <th>Booking Date</th>
											<th style="width:70px;">Action</th>
										</tr>
									</thead>
									<tbody id="tblBody">
										<c:forEach items="${booking_lists}" var="booking_lists">
	
                                              <tr id="row_${booking_lists.booking_Id}">
                                                   <td>${booking_lists.booking_Id}</td>
                                                   <td>${booking_lists.customer_Name}  ${booking_lists.phone_No}</td>
                                                  <td>${booking_lists.source}</td>
                                                  <td>${booking_lists.destinationation}</td>
                                                  <td>${booking_lists.booking_Date}</td>
												<td>
								                    <a href="javascript:void(0);" onclick="ConfirmBtnBooking('${booking_lists.booking_Id}')" data-toggle="tooltip" title="" class="table-icon text-primary"><i class="mdi mdi-square-edit-outline"></i>Accept</a>
								                   	<a href="javascript:void(0);" onclick="ConfirmBtnReject('${booking_lists.booking_Id}')" class="table-icon text-danger"> <i class="mdi mdi-delete"></i>Reject</a>
								               
								                  
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

         <div class="modal fade" id="ConfirmReject" role="dialog" aria-hidden="true">
			    <div class="modal-dialog modal-dialog-centered">
			        <div class="modal-content">
			            <div class="modal-header">
			                <h4 class="modal-title" id="bottomModalLabel">Confirm Reject</h4>
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            </div>
			            <div class="modal-body">
			                <p>Are you sure you want to Reject  this Booking of Customer?</p>
							
			    			<input type="hidden" id="txtID">
			                
			            </div>
			            <div class="modal-footer">
			
			                <button type="button" class="btn btn-primary"  id="bDelete" onclick="RejectRecord()">Reject</button>
			                <button type="button" class="btn btn-light" data-dismiss="modal">Cancel</button>
			            </div>
			        </div><!-- /.modal-content -->
			    </div><!-- /.modal-dialog -->
			</div>
		
		
		
		  <div class="modal fade" id="ConfirmBooking" role="dialog" aria-hidden="true">
			    <div class="modal-dialog modal-dialog-centered">
			        <div class="modal-content">
			            <div class="modal-header">
			                <h4 class="modal-title" id="bottomModalLabel">Confirm Booking</h4>
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            </div>
			            <div class="modal-body">
			                <p>Are you sure you want to Confirm  this Booking of Customer?</p>
							
			    			<input type="hidden" id="txtBID">
			                
			            </div>
			            <div class="modal-footer">
			
			                <button type="button" class="btn btn-primary"  id="bDelete" onclick="ConfirmRecord()">Confirm</button>
			                <button type="button" class="btn btn-light" data-dismiss="modal">Cancel</button>
			            </div>
			        </div><!-- /.modal-content -->
			    </div><!-- /.modal-dialog -->
			</div>
		
		</div>

			<jsp:include page="Shared/footer.html"></jsp:include>
		
		</div>
	</div>
 	<script title="Task">

 $(document).ready(function () {
	
	 $("#ConfirmReject").modal('hide');
	 $("#ConfirmBooking").modal('hide');
   });

	
	
	var ConfirmBtnReject = function (ID) {
		$("#txtID").val(ID);
       $("#ConfirmReject").modal('show');
   }
	
	var ConfirmBtnBooking = function (ID) {
		$("#txtBID").val(ID);
       $("#ConfirmBooking").modal('show');
   }
	
	
	var RejectRecord = function (){
		 	var booking_Id = $("#txtID").val();
          	var command = "UPDATE-REJECT";

           var url = 'BookingControllerServlet';

           var ParamPart = "&";
           ParamPart = ParamPart + ((command != "") ? '&command=' + command : '');
           ParamPart = ParamPart + ((booking_Id != "") ? '&booking_Id=' + booking_Id : '');
        

           ParamPart = ParamPart.replace("&&", "");

           window.location.href = url + '?' + ParamPart;
           event.preventDefault();
	}
	
	var ConfirmRecord = function (){
	 	var booking_Id = $("#txtBID").val();
      	var command = "UPDATE-PENDING";

       var url = 'BookingControllerServlet';

       var ParamPart = "&";
       ParamPart = ParamPart + ((command != "") ? '&command=' + command : '');
       ParamPart = ParamPart + ((booking_Id != "") ? '&booking_Id=' + booking_Id : '');
    

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
 