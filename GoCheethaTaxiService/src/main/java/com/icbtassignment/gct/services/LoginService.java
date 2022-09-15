package com.icbtassignment.gct.services;
import javax.sql.DataSource;

import com.icbtassignment.gct.factorydesignpattern.dao.AdministratorLoginDao;
import com.icbtassignment.gct.factorydesignpattern.dao.DriverLoginDao;
import com.icbtassignment.gct.factorydesignpattern.dao.LoginDao;
import com.icbtassignment.gct.utils.LoginManager;


public class LoginService {

	public  static boolean checkAuthentication(DataSource datasource,String type, String Username,String password) {
		int checkCredinatial ;
		LoginDao login;
		
		 if(type.equals("Driver")){
	            
				login = LoginManager.factory(type);
				checkCredinatial = login.verify(datasource,Username, password);
				if(checkCredinatial == 1) {
					
					return true ;
				}
				else {
					return false;
				}
	            
	        }else if(type.equals("Administration")){
	            
	        	login = LoginManager.factory(type);
	        	checkCredinatial = login.verify(datasource,Username, password);
	        	if(checkCredinatial == 1) {
	    			
	    			return true ;
	    		}
	    		else {
	    			return false;
	    		}
	            
	        }
	        else {
	        	 throw new RuntimeException("No login type found");
	        }
		
       
	}
}
