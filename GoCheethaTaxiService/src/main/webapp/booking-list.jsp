<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Total Sales - GoCheetha Taxi</title>
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
			<jsp:param name="booking" value="active"/>
		</jsp:include>
		<div class="page-wrapper">
					
		<jsp:include page="Shared/searchbar.jsp"></jsp:include>

		<div class="page-content">
	
	        <div class="d-flex justify-content-between align-items-center flex-wrap grid-margin">
	          <div>
	            <h4 class="mb-3 mb-md-0">Total Sales</h4>
	          </div>
           
           	 <div class="d-flex align-items-center flex-wrap text-nowrap">

		        <div class="input-groupdate datepicker dashboard-date  mr-2 mb-2 mb-md-0 d-md-none d-xl-flex" >
		            <input type="search" class="form-control form-control" id="txtKeyW" autofocus="autofocus" onkeyup="SearchStart()" placeholder="Search Here">
		        </div>
				 <div class="input-group date datepicker dashboard-date mr-2 mb-2 mb-md-0 d-md-none d-xl-flex" id="dashboardStartDate">
	                 <span class="input-group-addon bg-transparent text-primary">Date</span>
	                 <input type="text" id="txtStartDate" class="form-control">
	             </div>
	             
              	 <div class="input-groupdate datepicker dashboard-date  mr-2 mb-2 mb-md-0 d-md-none d-xl-flex" >
	                  <select name="branch_Id" id="cmbBranch" name="branch_Id" class="form-control">
					  	   <option value="">Select Branch</option>
	                       <c:forEach items="${branchDrp}" var="branchDrp">
	                           <option value="${branchDrp.branch_Id}">${branchDrp.city_Name} - ${branchDrp.branch_Name}</option>
	                       </c:forEach>
                       </select>
	             </div>
	
		        <a onclick="printDiv('printableArea')" class="btn btn-outline-danger float-right  mr-2 mb-2 mb-md-0"><i data-feather="printer" class="mr-2 icon-md"></i>Print</a>
	
	    	</div>
           
           
            </div>
            
            
			<div class="row ">
			    <div class="col-md-12">
			        <div class="card" id="printableArea">
			            <div class="card-body">
			
			                <div class="container-fluid d-flex justify-content-between">
			                    <div class="col-lg-6 pl-0">
			                        <a href="#" class="noble-ui-logo d-block mt-1">Booking Total Summary</a>
			
			
			                    </div>
								
			                
			
			                </div>
			                
			                <div id="divLoad" class="container-fluid mt-3 d-flex justify-content-center w-100 mb-0 mt-3">
			
			
			                    <div class="table-responsive w-100" id="divRecords">
			                    	<table class="table table-centered table-nowrap table-striped">
									<thead>
										<tr>
											   <th>Booking No.</th>
                                               <th>Customer</th>
                                               <th>Booking Date</th>
                                               <th>City</th>
                                               <th>Driver Details</th>
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
                                                  <td>${booking_lists.city_Name}</td>
                                                  <td>${booking_lists.driver_Name} - ${booking_lists.vehicle_No} (${booking_lists.vehicle_type_Name})</td>
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
	
	 <script title="PrintData">

        function printDiv(divName) {
            var printContents = document.getElementById(divName).innerHTML;
            var originalContents = document.body.innerHTML;

            document.body.innerHTML = printContents;

            window.print();

            document.body.innerHTML = originalContents;
        }
    </script>
	
	 <script title="main">
	 
	 var params = new window.URLSearchParams(window.location.search);
	 
	 $(document).ready(function () {
		 
		 var branch_Id = params.get('branch_Id');
         if (branch_Id != null || branch_Id != '') {
        	 $('select[name="branch_Id"]').first().val(branch_Id);
        	
         }
		 
	 });
	 
	  $("#cmbBranch").change(function (event) {
		  Search();
      });
	  
	
	  
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

          var branch_Id = $("#cmbBranch").val();
          var StartDate = $("#txtStartDate").val();
          var url = 'BookingControllerServlet?command=SHOW-BOOKING-BRANCH&branch_Id='+branch_Id+'&bookingDate='+StartDate;
    

          window.location.href = url 
          event.preventDefault();
          
	  }
	 	
	 </script>

</body>
</html>
 