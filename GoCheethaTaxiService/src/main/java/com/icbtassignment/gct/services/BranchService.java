package com.icbtassignment.gct.services;

import java.util.List;

import javax.sql.DataSource;

import com.icbtassignment.gct.dao.BranchDao;
import com.icbtassignment.gct.entities.Branch;



public class BranchService {
	
	public static List<Branch> getBranch(DataSource dataSource,String city_Id){
		
		return BranchDao.getBranch(dataSource,city_Id);
	}


	public static Branch get(DataSource dataSource, String branchId) {
		
		return BranchDao.get(dataSource,branchId);
	}
	
	public static boolean checkBranchName(DataSource dataSource, String branch_Name,String branch_Id) {
		int checkbranch ;
		if(branch_Id == null || branch_Id.isEmpty() ||branch_Id.trim().isEmpty())
		{
			checkbranch =	BranchDao.checkBranchName(dataSource,branch_Name);
		}else {
			checkbranch =	BranchDao.checkBranchUpdateName(dataSource,branch_Name,branch_Id);
		}
		
		if(checkbranch == 0) {
			
			return true ;
		}
		else {
			return false;
		}
		
	}
	
	public static void addBranch(DataSource dataSource, Branch branch) {
		BranchDao.addBranch(dataSource,branch);
		
	}
	
	public static void updateBranch(DataSource dataSource, Branch branch) {
		BranchDao.updateBranch(dataSource,branch);
		
	}
	
	public static void deleteBranch(DataSource dataSource, String city_Id) {
		BranchDao.deleteBranch(dataSource,city_Id);
		
	}
	
	
	
}
