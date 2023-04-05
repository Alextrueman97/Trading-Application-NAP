package com.napgroup.services;

import java.util.List;

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

	@Override
	public Company findCompanyById(int companyId) {
		return companyRepository.findById(companyId).get();
	}

	@Override
	public List<Company> findAllCompanies() {
		return companyRepository.findAll();
	}
	
	@Override
	public Company findCompanyByCompanyName(String companyName) {
		return companyRepository.findCompanyByCompanyName(companyName);
	}
	

}
