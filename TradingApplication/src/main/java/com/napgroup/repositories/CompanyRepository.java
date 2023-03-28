package com.napgroup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.napgroup.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
