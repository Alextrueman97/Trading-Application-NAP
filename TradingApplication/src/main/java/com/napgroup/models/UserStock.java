package com.napgroup.models;

public class UserStock {

	private Company company;
	private Region region;
	private int stockAmount;
	
	public UserStock(Company company, Region region, int stockAmount) {
		super();
		this.company = company;
		this.region = region;
		this.stockAmount = stockAmount;
	}
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public int getStockAmount() {
		return stockAmount;
	}
	public void setStockAmount(int stockAmount) {
		this.stockAmount = stockAmount;
	}
	
}
