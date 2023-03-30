package com.napgroup.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "company")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private int companyId;
	@Column(name = "company_symbol", columnDefinition = "VARCHAR(4)")
	private String companySymbol;
	@Column(name = "company_name", columnDefinition = "VARCHAR(30)")
	private String companyName;
	@OneToMany(mappedBy = "companyId")
	private List<Stocks> stocks;
	
	public Company() {
		super();
	}
	
	public Company(int companyId, String companySymbol, String companyName, List<Stocks> stocks) {
		super();
		this.companyId = companyId;
		this.companySymbol = companySymbol;
		this.companyName = companyName;
		this.stocks = stocks;
	}
	
	public Company(String companySymbol, String companyName, List<Stocks> stocks) {
		super();
		this.companySymbol = companySymbol;
		this.companyName = companyName;
		this.stocks = stocks;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanySymbol() {
		return companySymbol;
	}

	public void setCompanySymbol(String companySymbol) {
		this.companySymbol = companySymbol;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public List<Stocks> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stocks> stocks) {
		this.stocks = stocks;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companySymbol=" + companySymbol + ", companyName=" + companyName
				+ ", stocks=" + stocks.size() + "]";
	}

	
	
	
}
