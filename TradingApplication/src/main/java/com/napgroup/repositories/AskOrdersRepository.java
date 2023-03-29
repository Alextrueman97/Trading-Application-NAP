package com.napgroup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.napgroup.models.AskOrders;

@Repository
public interface AskOrdersRepository extends JpaRepository<AskOrders, Integer> {

	@Query(value = "select new AskOrders(a.orderId, a.accountId, a.stockId, a.salePrice, a.stockAmount, a.orderStatus, a.orderType, a.saleType, a.saleDate) from AskOrders a where a.accountId.accountId = :accountId")
	public List<AskOrders> findUserAskOrders(int accountId);
}
