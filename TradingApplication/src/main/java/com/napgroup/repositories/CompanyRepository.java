package com.napgroup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.napgroup.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
