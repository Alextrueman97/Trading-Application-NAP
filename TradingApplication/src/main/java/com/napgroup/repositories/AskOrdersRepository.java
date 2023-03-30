package com.napgroup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.napgroup.models.AskOrders;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.Region;

import jakarta.transaction.Transactional;

@Repository
public interface AskOrdersRepository extends JpaRepository<AskOrders, Integer> {

	@Query(value = "select new AskOrders(a.orderId, a.accountId, a.stockId, a.salePrice, a.stockAmount, a.orderStatus, a.orderType, a.saleType, a.saleDate) from AskOrders a where a.accountId.accountId = :accountId")
	public List<AskOrders> findUserAskOrdersByAccountId(@Param("accountId") int accountId);
	
	@Query(value = "select new AskOrders(a.orderId, a.accountId, a.stockId, a.salePrice, a.stockAmount, a.orderStatus, a.orderType, a.saleType, a.saleDate) from AskOrders a where a.stockId.companyId.companyId = :companyId and a.stockId.region = :region and (a.orderStatus = 'PENDING' or a.orderStatus = 'PARTIAL')")
	public List<AskOrders> findIncompleteOrdersByCompanyAndRegion(@Param("companyId") int companyId, @Param("region") Region region);
	
	@Query(value = "select new AskOrders(a.orderId, a.accountId, a.stockId, a.salePrice, a.stockAmount, a.orderStatus, a.orderType, a.saleType, a.saleDate) from AskOrders a where a.accountId.accountId = :accountId and a.orderStatus = 'COMPLETE'")
	public List<AskOrders> findCompleteOrdersByAccountId(@Param("accountId") int accountId);

	@Query(value = "update AskOrders a set a.orderStatus = :orderStatus where a.orderId = :orderId")
	@Transactional
	@Modifying
	public AskOrders updateOrderStatus(@Param("orderId") int orderId, @Param("orderStatus") OrderStatus orderStatus);

	@Query(value = "update AskOrders a set a.stockAmount = :stockAmount where a.orderId = :orderId")
	@Transactional
	@Modifying
	public AskOrders updateStockAmount(@Param("orderId") int orderId, @Param("stockAmount") int stockAmount);
	
	@Query(value = "update AskOrders a set a.salePrice = :salePrice where a.orderId = :orderId")
	@Transactional
	@Modifying
	public AskOrders updateStockPrice(@Param("orderId") int orderId, @Param("salePrice") double salePrice);

}