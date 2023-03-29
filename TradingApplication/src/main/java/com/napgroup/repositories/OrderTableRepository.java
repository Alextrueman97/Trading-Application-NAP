package com.napgroup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTable;

public interface OrderTableRepository extends JpaRepository<OrderTable, Integer>{

	//@Query(value = "SELECT * FROM order_table ot WHERE ot.account_id = :accountId", nativeQuery = true)
	//public List<OrderTable> findAllOrdersByUserId(int accountId);
	
	@Query(value = "SELECT * FROM order_table ot WHERE ot.seller_id = :accountId", nativeQuery = true)
	public List<OrderTable> findAskOrdersById(int accountId);
	//seller
	
	@Query(value = "SELECT * FROM order_table ot WHERE ot.buyer_id = :accountId", nativeQuery = true)
	public List<OrderTable> findBidOrdersById(int accountId);
	//buyer
	
	
	@Query(value = "SELECT * FROM order_table ot WHERE ot.company_id = :companyId AND ot.region = :region AND (ot.order_status = 'PARTIAL' OR ot.order_status = 'PENDING')", nativeQuery = true)
	public List<OrderTable> findAllUnfilledOrdersByCompanyAndRegion(int companyId, String region);
	
	@Query(value = "SELECT * FROM order_table ot WHERE ot.account_id = :accountId AND ot.order_status = 'COMPLETE'", nativeQuery = true)
	public List<OrderTable> findAllFilledOrdersByUserId(int accountId);
	
	@Query(value = "UPDATE order_table ot SET ot.order_status = :orderStatus WHERE ot.order_id = :orderId", nativeQuery = true)
	public OrderTable updateOrderStatusById(int orderId, OrderStatus orderStatus);
	
	@Query(value = "UPDATE order_table ot SET stock_amount = :stockAmount WHERE ot.order_id = :orderId", nativeQuery = true)
	public OrderTable updateStockAmountById(int orderId, int stockAmount);
	
	@Query(value = "UPDATE order_table ot SET stock_price = :stockPrice WHERE ot.order_id = :orderId", nativeQuery = true)
	public OrderTable updateStockPriceById(int orderId, double stockPrice);
	
}
