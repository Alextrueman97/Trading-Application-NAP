package com.napgroup.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "company")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "company_id")
	private int companyId;
	@Column(name = "company_symbol", columnDefinition = "VARCHAR(4)")
	private String companySymbol;
	@Column(name = "company_name", columnDefinition = "VARCHAR(30)")
	private String companyName;

	public Company() {
		super();
	}

	public Company(String companySymbol, String companyName) {
		super();
		this.companySymbol = companySymbol;
		this.companyName = companyName;
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

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companySymbol=" + companySymbol + ", companyName=" + companyName
				+ "]";
	}
	
	
}
