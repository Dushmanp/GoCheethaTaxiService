<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dashboard - GoCheetha Taxi</title>
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
			<jsp:param name="index-driver" value="active"/>
		</jsp:include>
		<div class="page-wrapper">
					
		<jsp:include page="Shared/searchbar.jsp"></jsp:include>

		<div class="page-content">
	
	        <div class="d-flex justify-content-between align-items-center flex-wrap grid-margin">
		          <div>
		            <h4 class="mb-3 mb-md-0">Dashboard </h4>
		          </div>
            </div>
            <div class="alert alert-danger ${exceptionerrorshow}" id="ShowAlert" role="alert">
				<span class="text-danger" id="txtError">${exceptionerror}</span>
			</div>
             
            <c:forEach items="${booking_lists}" var="booking_lists">
            
    <div class="row">
		<div class="col-md-12">
            <div class="card">
                <div class="card-body">
                    <h4 class="text-center mb-1 mt-1">On Going Booking</h4>
                    <p class="text-muted text-center mb-1 pb-1">GoCheetha Taxi Service.</p>

                    
                    
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-6 stretch-card grid-margin grid-margin-sm-0">
                                <div class="card">
                                    <div class="card-body">
                                       
                                        <div class="d-flex align-items-center mb-2">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check icon-md text-primary mr-2"><polyline points="20 6 9 17 4 12"></polyline></svg>
                                            <p>Customer Name : ${booking_lists.customer_Name}</p>
                                        </div>
                                        <div class="d-flex align-items-center mb-2">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check icon-md text-primary mr-2"><polyline points="20 6 9 17 4 12"></polyline></svg>
                                            <p>Customer Mobile : ${booking_lists.phone_No}</p>
                                        </div>
                                        <div class="d-flex align-items-center mb-2">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check icon-md text-primary mr-2"><polyline points="20 6 9 17 4 12"></polyline></svg>
                                            <p>Booking Date : ${booking_lists.booking_Date}</p>
                                        </div>
                                        <div class="d-flex align-items-center mb-2">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check icon-md text-primary mr-2"><polyline points="20 6 9 17 4 12"></polyline></svg>
                                            <p>PickUP Location :  ${booking_lists.source}</p>
                                        </div>
                                        <div class="d-flex align-items-center mb-2">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check icon-md text-primary mr-2"><polyline points="20 6 9 17 4 12"></polyline></svg>
                                            <p>Drop By Location :  ${booking_lists.destinationation}</p>
                                        </div>
                                        <div class="d-flex align-items-center mb-2">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check icon-md text-primary mr-2"><polyline points="20 6 9 17 4 12"></polyline></svg>
                                            <p>Status :  ${booking_lists.booking_Status}</p>
                                        </div>
                                        
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6 stretch-card grid-margin grid-margin-sm-0">
                                <div class="card">
                                    <div class="card-body">
                                 

                                        <div class="d-flex align-items-center mb-2">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check icon-md text-danger mr-2"><polyline points="20 6 9 17 4 12"></polyline></svg>
                                            <p>PickUp Time :  ${booking_lists.pickup_Time}</p>
                                        </div>
                                        <div class="d-flex align-items-center mb-2">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check icon-md text-danger mr-2"><polyline points="20 6 9 17 4 12"></polyline></svg>
                                            <p>Drop By Time :  ${booking_lists.drop_Time}</p>
                                        </div>
                                        <div class="d-flex align-items-center mb-2">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check icon-md text-danger mr-2"><polyline points="20 6 9 17 4 12"></polyline></svg>
                                            <p>Vehicle Type :  ${booking_lists.vehicle_type_Name}</p>
                                        </div>
                                        <div class="d-flex align-items-center mb-2">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check icon-md text-danger mr-2"><polyline points="20 6 9 17 4 12"></polyline></svg>
                                            <p>Charge Per KM :  ${booking_lists.charge_per_Km}</p>
                                        </div>
                                        <div class="d-flex align-items-center mb-2">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check icon-md text-danger mr-2"><polyline points="20 6 9 17 4 12"></polyline></svg>
                                            <p>Service Charge :  ${booking_lists.service_Charge}</p>
                                        </div>
	                                        
                                       
                                    </div>
                                </div>
                            </div>
                           

                        </div>
                    </div>
                     <div class="row">
                        <div class="col-sm-6 grid-margin grid-margin-sm-0">
                            <div id="BtnConfirmPickedUp" >
                            	 <button onclick="ConfirmBtnPickedUp('${booking_lists.booking_Id}')" class="btn btn-outline-warning  d-block mx-auto mt-4">Picked Up</button>
		                  
                            </div >
                            <div id="BtnConfirmDroppedBy">
                            	<button onclick="ConfirmBtnDroppedBy('${booking_lists.booking_Id}')"  class="btn btn-outline-info  d-block mx-auto mt-4">Dropped</button>
		                    
                            </div>
                            <div id="BtnConfirmCompleted">
                            	<button onclick="ConfirmBtnCompleted('${booking_lists.booking_Id}','${booking_lists.charge_per_Km}','${booking_lists.service_Charge}')"  class="btn btn-outline-success  d-block mx-auto mt-4">Completed</button>
   				 	
                            </div>
                             
                            </div>
                            <div class="col-sm-6  grid-margin grid-margin-sm-0">
	                            <div id="BtnReject">
	                            	<button class="btn btn-outline-danger   d-block mx-auto mt-4"  onclick="ConfirmBtnReject('${booking_lists.booking_Id}')">Reject</button>
	                            </div>
                             	
                             </div>
                           

                        </div>
                  
                     	
                   		<input type="hidden" id="txtStatus" value="${booking_lists.booking_Status}">

                    </div>
                
            </div>
		</div>
	</div>
            
	</c:forEach>
              
	  <div class="modal fade" id="ConfirmPickedUp" role="dialog" aria-hidden="true">
			    <div class="modal-dialog modal-dialog-centered">
			        <div class="modal-content">
			            <div class="modal-header">
			                <h4 class="modal-title" id="bottomModalLabel">Confirm Picked Up</h4>
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            </div>
			            <div class="modal-body">
			                <p>Are you sure you want to Confirm  Picked UP?</p>
							<form id="frmPickUp"  action="BookingControllerServlet" method="POST">
			    				<input type="hidden" name="booking_Id" id="txtPickUPID">
			    				<input type="hidden" name="command" value="UPDATE-PICKUP" id="txtPickUPID">
			                </form>
			            </div>
			            <div class="modal-footer">
			
			                <button type="button" class="btn btn-primary"  id="bDelete" onclick="ConfirmPickUpRecord()">Confirm PickUp</button>
			                <button type="button" class="btn btn-light" data-dismiss="modal">Cancel</button>
			            </div>
			        </div><!-- /.modal-content -->
			    </div><!-- /.modal-dialog -->
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
		
		
   	
   	  <div class="modal fade" id="ConfirmDropBy" role="dialog" aria-hidden="true">
			    <div class="modal-dialog modal-dialog-centered">
			        <div class="modal-content">
			            <div class="modal-header">
			                <h4 class="modal-title" id="bottomModalLabel">Confirm Drop By</h4>
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			            </div>
			            <div class="modal-body">
			                <p>Are you sure you want to Confirm  Drop By?</p>
							<form id="frmDropBy" action="BookingControllerServlet" method="POST">
			    				<input type="hidden" name="booking_Id" id="txtConfirmDropByID">
			    				<input type="hidden" name="command" value="UPDATE-DROPPED" >
			                </form>
			            </div>
			            <div class="modal-footer">
			
			                <button type="button" class="btn btn-primary"  id="bDelete" onclick="ConfirmDroppedByRecord()">Confirm Drop By</button>
			                <button type="button" class="btn btn-light" data-dismiss="modal">Cancel</button>
			            </div>
			        </div><!-- /.modal-content -->
			    </div><!-- /.modal-dialog -->
			</div>
		
		 
	 <div class="modal fade" id="ConfirmCompleted" role="dialog" aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title" id="bottomModalLabel">Confirm Completed</h4>
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	            </div>
	            <div class="modal-body">
	                <p>Are you sure you want to Confirm Completed?</p>
					<form id="frmCompleted" action="BookingControllerServlet" method=post>
						<input type="hidden" name="booking_Id" id="txtConfirmCompleted">
	    				<input type="hidden" name="command" value="UPDATE-COMPLETED" id="txtPickUPID">
	    					
	    				
			          <div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label class="control-label">KM Per Charge:</label>
								<input type="text" class="form-control" name="charge_per_Km" id="txtchargeperKm"  readonly="readonly">
							</div>
						</div><!-- Col -->
						<div class="col-sm-6">
							<div class="form-group">
								<label class="control-label">Service Charges:</label>
								<input type="text" class="form-control" name="service_Charge" id="txtserviceCharge" readonly="readonly">
							</div>
						</div><!-- Col -->
					</div>
          			
          			 <div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label class="control-label">Total KM Covered:</label>
								<input type="text" class="form-control" name="km_Covered" id="kmCovered"  value="0" onchange="AddData(this)" onKeyUp="AddData(this)" min="0"required>
							</div>
						</div><!-- Col -->
						<div class="col-sm-6">
							<div class="form-group">
								<label class="control-label">Total Charges:</label>
								<input type="text" class="form-control" name="charges_Calculated" id="txtchargesCalculated" value="0"  readonly="readonly">
							</div>
						</div><!-- Col -->
					</div>
			          
	                </form>
	            </div>
	            <div class="modal-footer">
	
	                <button type="button" class="btn btn-primary"  id="bDelete" onclick="ConfirmCompletedRecord()">Confirm Completed</button>
	                <button type="button" class="btn btn-light" data-dismiss="modal">Cancel</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	</div>
		
   	</div>
   	

		<jsp:include page="Shared/footer.html"></jsp:include>
	
	</div>
</div>
 	 <script type="text/javascript">
 	$(document).ready(function () {
 		
 		$('#BtnConfirmPickedUp').hide();
		$('#BtnConfirmDroppedBy').hide();
		
		$('#BtnConfirmCompleted').hide();
		$('#BtnReject').hide();
 		CheckStatus();
 		 $("#ConfirmPickedUp").modal('hide');
 		 $("#ConfirmDropBy").modal('hide');
 		 $("#ConfirmReject").modal('hide');
		 $("#ConfirmCompleted").modal('hide');
 	   });
	//Picked Up
	var ConfirmBtnPickedUp = function (ID) {
		$("#txtPickUPID").val(ID);
       $("#ConfirmPickedUp").modal('show');
   }
	
	var ConfirmBtnReject = function (ID) {
		$("#txtID").val(ID);
       $("#ConfirmReject").modal('show');
   }
	
	var ConfirmPickUpRecord = function () {
		$("#frmPickUp").submit();
		
	}
	
	//Dropped By
	var ConfirmBtnDroppedBy = function (ID) {
		$("#txtConfirmDropByID").val(ID);
       $("#ConfirmDropBy").modal('show');
   }
	
	var ConfirmDroppedByRecord = function () {
		$("#frmDropBy").submit();
		
	}
	
	//Completed
	var ConfirmBtnCompleted = function (ID,charge_per_Km,service_Charge) {
		$("#txtConfirmCompleted").val(ID);
		$("#txtchargeperKm").val(charge_per_Km);
		$("#txtserviceCharge").val(service_Charge);
       $("#ConfirmCompleted").modal('show');
   }
	
	var ConfirmCompletedRecord = function () {
		var CheckCharges= $("#txtchargesCalculated").val
		if( CheckCharges == 0 || CheckCharges== null)
		{
			$('#ShowAlert').show();
			$('#txtError').html('Please Enter Valid KM');
		}
		else{
			$("#frmCompleted").submit();
		}
		
	}
	
	//Reject
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
	
	var CheckStatus = function () {
		var BookingStatus =$("#txtStatus").val();
		
		if(BookingStatus=="Confirmed")
		{
			$('#BtnConfirmPickedUp').show();
			$('#BtnReject').show();
		}
		
		if(BookingStatus=="PickedUp")
		{
			$('#BtnConfirmDroppedBy').show();
		}
		
		if(BookingStatus=="Dropped")
		{
			$('#BtnConfirmCompleted').show();
		}
		
	}
	
	function AddData(e) {
        var kmCovered = $(e).val();
        var chargeperKm = $("#txtchargeperKm").val();
        var txtserviceCharge = $('#txtserviceCharge').val();
        var  TotalAmount = ((parseFloat(kmCovered) * parseFloat(chargeperKm)) + parseFloat(txtserviceCharge));
        
        $("#txtchargesCalculated").val(TotalAmount);
        
    }
	
	
		
	

    </script>

</body>
</html>
 
