package com.napgroup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.napgroup.models.BidOrders;

public interface BidOrdersRepository extends JpaRepository<BidOrders, Integer> {
	

}
