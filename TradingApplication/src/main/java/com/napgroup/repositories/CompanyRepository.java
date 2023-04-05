package com.napgroup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.napgroup.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	@Query("select new Company(c.companyId, c.companyName, c.companySymbol) from Company c where companyName = :companyName")
	public Company findCompanyByCompanyName(@Param("companyName") String companyName);

}
