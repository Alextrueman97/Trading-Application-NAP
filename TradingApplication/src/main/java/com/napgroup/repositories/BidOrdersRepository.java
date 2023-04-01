package com.napgroup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.napgroup.models.BidOrders;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.Region;

import jakarta.transaction.Transactional;

@Repository
public interface BidOrdersRepository extends JpaRepository<BidOrders, Integer> {
	
	@Query(value = "select new BidOrders(b.orderId, b.accountId, b.stockId, b.salePrice, b.stockAmount, b.orderStatus, b.orderType, b.saleType, b.saleDate) from BidOrders b where b.accountId.accountId = :accountId")
	public List<OrderTableSuper> findUserBidOrdersByAccountId(@Param("accountId") int accountId);
	
	@Query(value = "select new BidOrders(b.orderId, b.accountId, b.stockId, b.salePrice, b.stockAmount, b.orderStatus, b.orderType, b.saleType, b.saleDate) from BidOrders b where b.stockId.companyId.companyId = :companyId and b.stockId.region = :region and (b.orderStatus = 'PENDING' or b.orderStatus = 'PARTIAL')")
	public List<OrderTableSuper> findIncompleteOrdersByCompanyAndRegion(@Param("companyId") int companyId, @Param("region") Region region);
	
	@Query(value = "select new BidOrders(b.orderId, b.accountId, b.stockId, b.salePrice, b.stockAmount, b.orderStatus, b.orderType, b.saleType, b.saleDate) from BidOrders b where b.accountId.accountId = :accountId and b.orderStatus = 'COMPLETE'")
	public List<OrderTableSuper> findCompleteOrdersByAccountId(@Param("accountId") int accountId);
	
	@Query(value = "select new BidOrders(b.orderId, b.accountId, b.stockId, b.salePrice, b.stockAmount, b.orderStatus, b.orderType, b.saleType, b.saleDate) from BidOrders b where b.accountId.accountId = :accountId and b.orderStatus = 'COMPLETE' and b.stockId.companyId.companyId = :companyId and b.stockId.region = :region")
	public List<OrderTableSuper> findCompleteOrdersByAccountIdAndCompanyAndRegion(@Param("accountId") int accountId, @Param("companyId") int companyId, @Param("region") Region region);
	
	@Query(value = "update BidOrders b set b.orderStatus = :orderStatus where b.orderId = :orderId")
	@Transactional
	@Modifying(clearAutomatically = true)
	public int updateOrderStatus(@Param("orderId") int orderId, @Param("orderStatus") OrderStatus orderStatus);

	@Query(value = "update BidOrders b set b.stockAmount = :stockAmount where b.orderId = :orderId")
	@Transactional
	@Modifying(clearAutomatically = true)
	public int updateStockAmount(@Param("orderId") int orderId, @Param("stockAmount") int stockAmount);
	
	@Query(value = "update BidOrders b set b.salePrice = :salePrice where b.orderId = :orderId")
	@Transactional
	@Modifying(clearAutomatically = true)
	public int updateStockPrice(@Param("orderId") int orderId, @Param("salePrice") double salePrice);
	
}
