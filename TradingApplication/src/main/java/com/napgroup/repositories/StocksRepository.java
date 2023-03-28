package com.napgroup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.napgroup.models.Stocks;

public interface StocksRepository extends JpaRepository<Stocks, Integer> {

}
