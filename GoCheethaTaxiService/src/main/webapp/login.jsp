<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login - GoCheetha Taxi</title>

   <jsp:include page="Shared/header.html"></jsp:include>
    
</head>
<body class="sidebar-dark">
    <div class="main-wrapper">
        <div class="page-wrapper full-page" style=" background-color: #eef4ff;">
            <div class="page-content d-flex align-items-center justify-content-center">

                <div class="row w-100 mx-0 auth-page" >
                    <div class="col-md-8  col-xl-6 mx-auto">
                          <div class="alert  text-center col-md-12 ${exceptionerrorshow}" id="divShowError" role="alert">
								<span id="showerrormsg" class="text-danger">${exceptionerror}</span>
								<input type="hidden" id="showerrormsg" value="${exceptionerror}" >
							</div>
                            <div class="row grid-margin">
                                <div class="col-md-6 pr-md-0">
                                    <div class="auth-form-wrapper auth-left-wrapper px-4 py-5" style=" background-color: #0c1427;border-radius : 0.25rem;" >
                                        <a href="#" class="noble-ui-logo d-block noble-ui-logo logo-light mb-2">Administrator Login<span> </span></a>
                                      <form action="LoginControllerServlet" method="post">
                                          
                                           <div class="form-group d-none">
                                            	<input type="hidden" class="form-control" name="type" value="Administration" />
                                       	   </div>
                                              <div class="form-group">
                                              	Username
							                        <input type="text" class="form-control" name="username" placeholder="Your Username" value="" />
							                    </div>
							                    <div class="form-group">
							                    Password
							                        <input type="password" class="form-control" name="password" placeholder="Your Password *" value="" />
							                    </div>
							                                            
							                <div class="mt-3">
                                                <input type="submit" value="Login" class="btn btn-primary mr-2 mb-2 mb-md-0 text-white" />
                                               
                                            </div>

											
										</form>
                                           
                                           
                                    </div>
                                </div>
                                <div class="col-md-6 pl-md-0">
                                    <div class="auth-form-wrapper px-4 py-5" style=" background-color: #fff;border-radius : 0.25rem;" >
                                     
                                        <a href="#" class="noble-ui-logo d-block  mb-2">Driver Login<span> </span></a>
                                           
                                             <form action="LoginControllerServlet" method="post">
                                               
                                                <div class="form-group d-none">
                                                <input type="hidden" class="form-control" name="type" value="Driver" />
                                             	</div>
                                                <div class="form-group">
                                                    Licence No
                                                    <input type="text" class="form-control" name="username" placeholder="Enter Licence No." value="" />
                                                </div>
                                               
                                               
                                                 <div class="form-group">
                                                    Password
                                               		<input type="password" class="form-control" name="password" placeholder="Enter Password *" value="" />
                                                  
                                                </div>
                                                <div class="mt-3">
                                                    <input type="submit" value="Login" class="btn btn-primary mr-2 mb-2 mb-md-0 text-white" />
                                                    
                                                </div>

                                                
                                          </form>
                                        
                                    </div>
                                </div>



                            </div>
                       

                    

                    </div>
                </div>

                
            </div>
        </div>
    </div>

   <script type="text/javascript">
 
	
	 

    </script>

</body>

</html>


