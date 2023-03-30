package com.napgroup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.napgroup.models.Stocks;

import jakarta.transaction.Transactional;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Integer> {

	@Query(value = "update Stocks s set s.stockAmount = :stockAmount where s.stockId = :stockId")
	@Transactional
	@Modifying
	public int updateStockAmountById(@Param("stockId") int stockId, @Param("stockAmount") int stockAmount);
	
}
