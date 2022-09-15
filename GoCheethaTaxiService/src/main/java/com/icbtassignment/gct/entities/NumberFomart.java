package com.icbtassignment.gct.entities;

public class NumberFomart {
	
	private String tableName;
	private String prefix;
	private int numberPart;
	
	public NumberFomart() {
		
	}	
	public NumberFomart(String tableName, String prefix, int numberPart) {
		super();
		this.tableName = tableName;
		this.prefix = prefix;
		this.numberPart = numberPart;
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getNumberPart() {
		return numberPart;
	}

	public void setNumberPart(int numberPart) {
		this.numberPart = numberPart;
	}

	
	@Override
	public String toString() {
		return "NumberFomart [tableName=" + tableName + ", prefix=" + prefix + ", numberPart=" + numberPart + "]";
	}

}
