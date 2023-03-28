package com.napgroup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTable;

public interface OrderTableRepository extends JpaRepository<OrderTable, Integer>{

	@Query(value = "SELECT * FROM order_table ot WHERE ot.account_id = :accountId", nativeQuery = true)
	public List<OrderTable> findAllOrdersByUserId(int accountId);
	
	@Query(value = "SELECT * FROM order_table ot WHERE ot.company_id = :companyId AND ot.region = :region AND (ot.order_status = 'PARTIAL' OR ot.order_status = 'PENDING')", nativeQuery = true)
	public List<OrderTable> findAllUnfilledOrdersByCompanyAndRegion(int companyId, String region);
	
	@Query(value = "SELECT * FROM order_table ot WHERE ot.account_id = :accountId AND ot.order_status = 'COMPLETE'", nativeQuery = true)
	public List<OrderTable> findAllFilledOrdersByUserId(int accountId);
	
	@Query()
	public OrderTable addOrder(OrderTable order);
	
	@Query()
	public OrderTable updateOrderStatusById(int orderId, OrderStatus orderStatus);
	
	@Query()
	public OrderTable updateStockAmountById(int orderId, OrderStatus orderStatus);
	
	@Query()
	public OrderTable updateStockPriceById(int orderId, OrderStatus orderStatus);
	
}
