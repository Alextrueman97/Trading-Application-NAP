package com.napgroup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.napgroup.models.BidOrders;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.OrderType;
import com.napgroup.models.Region;
import com.napgroup.models.SaleType;
import com.napgroup.models.Stocks;
import com.napgroup.repositories.BidOrdersRepository;
import com.napgroup.services.BidOrdersServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BidOrderServiceTest {

	@Mock
	private BidOrdersRepository bidOrdersRepository;
	@InjectMocks 
	BidOrdersServiceImpl bidOrdersServiceImpl;
	
	@Test
	void testFindUserBidOrdersByAccountId() {
		
		//mocking data
		List<OrderTableSuper> mockBidOrders = new ArrayList<>();
		mockBidOrders.add(new BidOrders());
		mockBidOrders.add(new BidOrders());
		when(bidOrdersRepository.findUserBidOrdersByAccountId(1)).thenReturn(mockBidOrders);
		
		//calling service method
		List<OrderTableSuper> result = bidOrdersServiceImpl.findUserOrdersByAccountId(1);
		
		//verifying result
		assertEquals(mockBidOrders.size(), result.size());
	}
	
	@Test
	void testFindIncompleteOrdersByCompanyAndRegion() {
		
		List<OrderTableSuper> mockBidOrders = new ArrayList<>();
		mockBidOrders.add(new BidOrders());
		when(bidOrdersRepository.findIncompleteOrdersByCompanyAndRegion(1, Region.LSE)).thenReturn(mockBidOrders);
		
		List<OrderTableSuper> result = bidOrdersServiceImpl.findIncompleteOrdersByCompanyAndRegion(1, Region.LSE);
		
		assertEquals(mockBidOrders.size(), result.size());
	}
	
	@Test
	void testFindCompleteOrdersByAccountId() {
		
		List<OrderTableSuper> mockBidOrders = new ArrayList<>();
		mockBidOrders.add(new BidOrders());
		when(bidOrdersRepository.findCompleteOrdersByAccountId(1)).thenReturn(mockBidOrders);
		
		List<OrderTableSuper> result = bidOrdersServiceImpl.findCompleteOrdersByAccountId(1);
		
		assertEquals(mockBidOrders.size(), result.size());
	}
	
	@Test
	void testAddBidOrder() {
		Stocks stock = new Stocks();
		stock.setStockId(1);
		BidOrders bidOrder = new BidOrders(stock, 10.0, 100, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now());
		
		when(bidOrdersRepository.save(any(BidOrders.class))).thenReturn(bidOrder);
		
		OrderTableSuper savedBidOrder = bidOrdersServiceImpl.addOrder(bidOrder);
		
		assertEquals(bidOrder, savedBidOrder);
	}
	
	
	@Test 
	void testUpdateOrderStatus() {
		BidOrders bidOrder = new BidOrders(1, new Stocks(), 10, 100, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now());
		
		when(bidOrdersRepository.save(bidOrder)).thenReturn(new BidOrders(1, new Stocks(), 10, 100, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now()));
		
		OrderTableSuper savedOrder = bidOrdersServiceImpl.addOrder(bidOrder);
		
		BidOrders updatedOrder = new BidOrders(savedOrder.getOrderId(), savedOrder.getStockId(), savedOrder.getSalePrice(), savedOrder.getStockAmount(), OrderStatus.COMPLETE, savedOrder.getOrderType(), savedOrder.getSaleType(), savedOrder.getSaleDate());
		
		when(bidOrdersRepository.updateOrderStatus(savedOrder.getOrderId(),OrderStatus.COMPLETE)).thenReturn(updatedOrder);
		
		OrderTableSuper result = bidOrdersServiceImpl.updateOrderStatus(savedOrder.getOrderId(), OrderStatus.COMPLETE);
		
		assertEquals(updatedOrder, result);
	}

	@Test 
	void testUpdateStockAmount() {
		BidOrders bidOrder = new BidOrders(1, new Stocks(), 10, 100, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now());
		
		when(bidOrdersRepository.save(bidOrder)).thenReturn(new BidOrders(1, new Stocks(), 10, 100, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now()));
		
		OrderTableSuper savedOrder = bidOrdersServiceImpl.addOrder(bidOrder);
		
		BidOrders updateStock = new BidOrders(savedOrder.getOrderId(), savedOrder.getStockId(), savedOrder.getSalePrice(), 500, savedOrder.getOrderStatus(), savedOrder.getOrderType(), savedOrder.getSaleType(), savedOrder.getSaleDate());
		
		when(bidOrdersRepository.updateStockAmount(savedOrder.getOrderId(), 500)).thenReturn(updateStock);
		
		OrderTableSuper result = bidOrdersServiceImpl.updateStockAmount(savedOrder.getOrderId(), 500);
		
		assertEquals(updateStock, result);
	}
	
	@Test 
	void testUpdateStockPrie() {						//price is 10
		BidOrders bidOrder = new BidOrders(1, new Stocks(), 10, 100, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now());
		
		when(bidOrdersRepository.save(bidOrder)).thenReturn(new BidOrders(1, new Stocks(), 10, 100, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now()));
		
		OrderTableSuper savedOrder = bidOrdersServiceImpl.addOrder(bidOrder);
		
		BidOrders updateStockPrice = new BidOrders(savedOrder.getOrderId(), savedOrder.getStockId(), 300, savedOrder.getStockAmount(), savedOrder.getOrderStatus(), savedOrder.getOrderType(), savedOrder.getSaleType(), savedOrder.getSaleDate());
		
		when(bidOrdersRepository.updateStockPrice(savedOrder.getOrderId(), 300)).thenReturn(updateStockPrice);
		
		OrderTableSuper result = bidOrdersServiceImpl.updateStockPrice(savedOrder.getOrderId(), 300);
		
		assertEquals(updateStockPrice, result);
	}
	
	@Test
	void testFindTotalStocksByAccountIdAndCompanyAndRegion() {
		//mocking 
		int accountId = 1;
		int companyId = 1;
		Region region = Region.LSE;
		List<OrderTableSuper> bids = new ArrayList<>();
		OrderTableSuper bid1 = new OrderTableSuper();
		bid1.setStockAmount(50);
		bids.add(bid1);
		OrderTableSuper bid2 = new OrderTableSuper();
		bid2.setStockAmount(50);
		bids.add(bid2);
		when(bidOrdersRepository.findCompleteOrdersByAccountIdAndCompanyAndRegion(accountId, companyId, region)).thenReturn(bids);
		
		int result = bidOrdersServiceImpl.findTotalStocksByAccountIdAndCompanyAndRegion(accountId, companyId, region);
		
		assertEquals(100, result);
	}
}
