package com.icbtassignment.gct.utils;

import com.icbtassignment.gct.factorydesignpattern.dao.AdministratorLoginDao;
import com.icbtassignment.gct.factorydesignpattern.dao.DriverLoginDao;
import com.icbtassignment.gct.factorydesignpattern.dao.LoginDao;

public class LoginManager {
	
	public static LoginDao factory(String type){
        if(type.equals("Driver")){
            
            return new DriverLoginDao();
            
        }else if(type.equals("Administration")){
            
            return new AdministratorLoginDao();
            
        }else{
            /**
             * It would be more appropriate to throw a custom exception here
             */
            throw new RuntimeException("No login type found");
        }
    }

}
