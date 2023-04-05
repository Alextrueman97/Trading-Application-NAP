package com.napgroup.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks" )
public class Stocks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stockId;
	@ManyToOne
	private Company companyId;
	@Column(name = "stock_price")
	private double stockPrice;
	@Enumerated(EnumType.STRING)
	private Region region;
	@Column(name = "stock_amount")
	private int stockAmount;
	@OneToMany(mappedBy = "stockId")
	private List<AskOrders>askOrderTable;
	@OneToMany(mappedBy = "stockId")
	private List<BidOrders>bidOrderTable;
	
	public Stocks() {
		super();
	}
	
	public Stocks(int stockId, Company companyId, double stockPrice, Region region, int stockAmount, List<AskOrders> askOrderTable, List<BidOrders> bidOrderTable) {
		super();
		this.stockId = stockId;

		this.companyId = companyId;
		this.stockPrice = stockPrice;
		this.region = region;
		this.stockAmount = stockAmount;
		this.askOrderTable = askOrderTable;
		this.bidOrderTable = bidOrderTable;
	}
	
	public Stocks(int stockId, Company companyId, double stockPrice, Region region, int stockAmount) {
		super();
		this.stockId = stockId;

		this.companyId = companyId;
		this.stockPrice = stockPrice;
		this.region = region;
		this.stockAmount = stockAmount;
	}
	
	public Stocks(Company companyId, double stockPrice, Region region, int stockAmount, List<AskOrders> askOrderTable, List<BidOrders> bidOrderTable) {
		super();
		this.companyId = companyId;
		this.stockPrice = stockPrice;
		this.region = region;
		this.stockAmount = stockAmount;
		this.askOrderTable = askOrderTable;
		this.bidOrderTable = bidOrderTable;
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
	

	public List<AskOrders> getAskOrderTable() {
		return askOrderTable;
	}

	public void setAskOrderTable(List<AskOrders> askOrderTable) {
		this.askOrderTable = askOrderTable;
	}

	public List<BidOrders> getBidOrderTable() {
		return bidOrderTable;
	}

	public void setBidOrderTable(List<BidOrders> bidOrderTable) {
		this.bidOrderTable = bidOrderTable;
	}

	@Override
	public String toString() {
		return "Stocks [stockId=" + stockId + ", companyId=" + companyId + ", stockPrice=" + stockPrice + ", region="
				+ region + ", stockAmount=" + stockAmount + ", askOrderTable=" + askOrderTable.size() + ", bidOrderTable="
				+ bidOrderTable.size() + "]";
	}

	
	
	

	

	
}
