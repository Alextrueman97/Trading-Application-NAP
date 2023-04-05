package com.napgroup.services;

import java.util.List;

import com.napgroup.models.Company;

public interface CompanyService {
	
	public Company findCompanyById(int companyId);
	public Company addCompany(Company company);
	public List<Company> findAllCompanies();
}
