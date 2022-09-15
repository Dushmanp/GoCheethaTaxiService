<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Booking Completed Details - GoCheetha Taxi</title>
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
			<jsp:param name="bookingconfirmed" value="active"/>
		</jsp:include>
		<div class="page-wrapper">
					
		<jsp:include page="Shared/searchbar.jsp"></jsp:include>

		<div class="page-content">
	
	        <div class="d-flex justify-content-between align-items-center flex-wrap grid-margin">
		          <div>
		            <h4 class="mb-3 mb-md-0">Booking Completed</h4>
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
							<div class="p-1 pl-0">
			                        <div class="input-group date datepicker dashboard-date mr-2 mb-2 mb-md-0 d-md-none d-xl-flex" id="dashboardStartDate">
			                            <span class="input-group-addon bg-transparent text-primary">Date</span>
			                            <input type="text" id="txtStartDate" class="form-control">
			                        </div>
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
                                               <th>Booking Date</th>
                                               <th>Pickup</th>
                                               <th>Dropped</th>
                                               <th>Payment</th>
                                              
											
										</tr>
									</thead>
									<tbody id="tblBody">
										<c:forEach items="${booking_lists}" var="booking_lists">
	
                                              <tr id="row_${booking_lists.booking_Id}">
                                                  <td>${booking_lists.booking_Id}</td>
                                                  <td>${booking_lists.customer_Name} (Mobile No. ${booking_lists.phone_No})</td>
                                                   <td>${booking_lists.booking_Date}</td>
                                                  <td>${booking_lists.source} at ${booking_lists.pickup_Time}</td>
                                                  <td>${booking_lists.destinationation} at ${booking_lists.drop_Time} </td>
                                                  <td>RS.${booking_lists.charges_Calculated} for ${booking_lists.km_Covered} KM </td>
												
											</tr>
										</c:forEach>
									</tbody>
									
								</table>
							</div>
		                
		                </div>
						  <div class="container-fluid mt-4 w-100">
	                    <div class="row">
	                        <div class="col-md-6 ml-auto">
	                            <div class="table-responsive">
	                                <table class="table">
	                                    <tbody>
											
	                                        <tr class="bg-light">
	                                            <td class="text-bold-800">Total Sales : </td>
	                                            <td class="text-bold-800 text-right text-danger">${Total}</td>
	                                        </tr>
										 
	                                    </tbody>
	                                </table>
	                            </div>
	                        </div>
	                    </div>
	                </div>
							
		            </div>
		        </div>
		    </div>
		</div>

       
		
		 
		</div>

			<jsp:include page="Shared/footer.html"></jsp:include>
		
		</div>
	</div>
 
	<script>
	 var params = new window.URLSearchParams(window.location.search);
		
	 if ($('#dashboardStartDate').length) {
         var date = new Date();
         var today = new Date(date.getFullYear(), date.getMonth(),date.getDate());
         $('#dashboardStartDate').datepicker({
             format: "yyyy-mm-dd",
             todayHighlight: true,
             autoclose: true
         });
         var StartD = params.get('bookingDate');
         if (StartD == null || StartD == '') {
             $('#dashboardStartDate').datepicker('setDate', today);
         }
         else {
             $('#dashboardStartDate').datepicker('setDate', StartD);
         }

     }
	
	  
	
		
	  $("#dashboardStartDate").on("change", function CalDate() {
		  Search();
      });
	  
	  function Search() {

          
          var StartDate = $("#txtStartDate").val();
          var url = 'BookingControllerServlet?command=SHOW-Driver&bookingDate='+StartDate;
    

          window.location.href = url 
          event.preventDefault();
          
	  }
	  
	</script>
</body>
</html>
 