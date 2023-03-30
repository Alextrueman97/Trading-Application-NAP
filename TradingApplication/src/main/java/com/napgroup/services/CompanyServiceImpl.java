package com.napgroup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.napgroup.models.Company;
import com.napgroup.repositories.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public Company addCompany(Company company) {
		return companyRepository.save(company);
	}

}
