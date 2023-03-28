package com.napgroup.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks" )
public class Stocks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stock_id")
	private int stockId;
	@JoinColumn(name = "account_id")
	private Company companyId;
	@Column(name = "stock_price")
	private double stockPrice;
	@Enumerated(EnumType.STRING)
	private Region region;
	@Column(name = "stock_amount")
	private int stockAmount;
	
	public Stocks() {
		super();
	}

	public Stocks(double stockPrice, Region region, int stockAmount) {
		super();
		this.stockPrice = stockPrice;
		this.region = region;
		this.stockAmount = stockAmount;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public Company getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Company companyId) {
		this.companyId = companyId;
	}

	public double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
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

	@Override
	public String toString() {
		return "Stocks [stockId=" + stockId + ", companyId=" + companyId + ", stockPrice=" + stockPrice + ", region="
				+ region + ", stockAmount=" + stockAmount + "]";
	}
	
	

	
}
