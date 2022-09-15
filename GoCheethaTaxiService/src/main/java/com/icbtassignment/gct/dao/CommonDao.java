package com.icbtassignment.gct.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.icbtassignment.gct.entities.NumberFomart;

public class CommonDao {
	
	
	public static NumberFomart getNumberFormat(DataSource dataSource, String tableName) {
		NumberFomart numberFormat = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rset  = null;
		String sql = null;
		
		try {
			con = dataSource.getConnection();
			sql= "select * from numberformat where TableName = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tableName);
			rset= stmt.executeQuery();
			
			while(rset.next()) {
				String tableNames = rset.getString("tableName");
				String prefix = rset.getString("prefix");
				int numberPart = rset.getInt("numberPart");
				numberFormat = new NumberFomart(tableNames,prefix,numberPart);
				
			}	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			close(con,stmt,null);
		}
		
		return numberFormat;
	}
	
	public static int setNumberFormat(DataSource dataSource, String tableName, int NumberPart ) {
			
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			con = dataSource.getConnection();
			 sql = "UPDATE numberformat SET numberPart=? WHERE tableName=?";
			 pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, NumberPart);
			pstmt.setString(2, tableName);
		
			pstmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(con, pstmt, null);
		}
		
		return NumberPart	;
	}
		
	
	private static void close(Connection con,Statement stmt,ResultSet rset) {
		try {
			if(rset != null)
			{
				rset.close();
			}
			if(stmt != null)
			{
				stmt.close();
			}
			
			if(con != null)
			{
				con.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
