<!-- partial:partials/_sidebar.html -->
		<nav class="sidebar">
	      <div class="sidebar-header">
	        <a href="#" class="sidebar-brand">
	          GO<span>Cheetha</span>
	        </a>
	        <div class="sidebar-toggler not-active">
	          <span></span>
	          <span></span>
	          <span></span>
	        </div>
	      </div>
	      <div class="sidebar-body">
	        <ul class="nav">
	          <li class="nav-item nav-category">Main</li>
	          <li class="nav-item  ${param.index-driver}">
	            <a href="BookingControllerServlet?command=SHOW-OnGoing" class="nav-link">
	              <i class="link-icon" data-feather="box"></i>
	              <span class="link-title">Home</span>
	            </a>
	          </li>
	          <li class="nav-item nav-category">Booking</li>
	          <li class="nav-item  ${param.pending}">
	            <a href="BookingControllerServlet?command=SHOW-PENDING" class="nav-link">
	              <i class="link-icon" data-feather="message-square"></i>
	              <span class="link-title">Booking Pending</span>
	            </a>
	          </li>
	          <li class="nav-item  ${param.bookingconfirmed}">
	            <a href="BookingControllerServlet?command=SHOW-ALL" class="nav-link">
	              <i class="link-icon" data-feather="calendar"></i>
	              <span class="link-title">Booking Details</span>
	            </a>
	          </li>

	          
	         
	         </ul>
	      </div>
	    </nav>
	    
	    
	    
	    <nav class="settings-sidebar">
	      <div class="sidebar-body">
	        <a href="#" class="settings-sidebar-toggler">
	          <i data-feather="settings"></i>
	        </a>
	        <h6 class="text-muted">Sidebar:</h6>
	        <div class="form-group">
	          <div class="form-check form-check-inline">
	            <label class="form-check-label">
	              <input type="radio" class="form-check-input" name="sidebarThemeSettings" id="sidebarLight" value="sidebar-light" checked>
	              Light
	            </label>
	          </div>
	          <div class="form-check form-check-inline">
	            <label class="form-check-label">
	              <input type="radio" class="form-check-input" name="sidebarThemeSettings" id="sidebarDark" value="sidebar-dark">
	              Dark
	            </label>
	          </div>
	        </div>
	        </div>
	    </nav>
			<!-- partial -->
		
