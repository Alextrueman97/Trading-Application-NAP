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

import com.napgroup.models.AskOrders;
import com.napgroup.models.BidOrders;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderType;
import com.napgroup.models.Region;
import com.napgroup.models.SaleType;
import com.napgroup.models.Stocks;
import com.napgroup.repositories.AskOrdersRepository;
import com.napgroup.services.AskOrdersServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AskOrderServiceTest {
	
	@Mock
	private AskOrdersRepository askOrdersRepository;
	@InjectMocks
	AskOrdersServiceImpl askOrdersServiceImpl;
	
	@Test
	void testFindUserBidOrdersByAccountId() {
		
		//mocking data
		List<AskOrders> mockAskOrders = new ArrayList<>();
		mockAskOrders.add(new AskOrders());
		mockAskOrders.add(new AskOrders());
		when(askOrdersRepository.findUserAskOrdersByAccountId(1)).thenReturn(mockAskOrders);
		
		//calling service method
		List<AskOrders> result = askOrdersServiceImpl.findUserAskOrdersByAccountId(1);
		
		//verifying result
		assertEquals(mockAskOrders.size(), result.size());
	}
	
	@Test
	void testFindIncompleteOrdersByCompanyAndRegion() {
		
		List<AskOrders> mockAskOrders = new ArrayList<>();
		mockAskOrders.add(new AskOrders());
		when(askOrdersRepository.findIncompleteOrdersByCompanyAndRegion(1, Region.LSE)).thenReturn(mockAskOrders);
		
		List<AskOrders> result = askOrdersServiceImpl.findIncompleteOrdersByCompanyAndRegion(1, Region.LSE);
		
		assertEquals(mockAskOrders.size(), result.size());
	}
	
	@Test
	void testFindCompleteOrdersByAccountId() {
		
		List<AskOrders> mockAskOrders = new ArrayList<>();
		mockAskOrders.add(new AskOrders());
		when(askOrdersRepository.findCompleteOrdersByAccountId(1)).thenReturn(mockAskOrders);
		
		List<AskOrders> result = askOrdersServiceImpl.findCompleteOrdersByAccountId(1);
		
		assertEquals(mockAskOrders.size(), result.size());
	}
	
	@Test
	void testAddBidOrder() {
		Stocks stock = new Stocks();
		stock.setStockId(1);
		AskOrders askOrder = new AskOrders(stock, 10.0, 100, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now());
		
		when(askOrdersRepository.save(any(AskOrders.class))).thenReturn(askOrder);
		
		AskOrders savedAskOrder = askOrdersServiceImpl.addAskOrder(askOrder);
		
		assertEquals(askOrder, savedAskOrder);
	}
	
	
	@Test 
	void testUpdateOrderStatus() {
		AskOrders askOrder = new AskOrders(1, new Stocks(), 10, 100, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now());
		
		when(askOrdersRepository.save(askOrder)).thenReturn(new AskOrders(1, new Stocks(), 10, 100, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now()));
		
		AskOrders savedOrder = askOrdersServiceImpl.addAskOrder(askOrder);
		
		AskOrders updatedOrder = new AskOrders(savedOrder.getOrderId(), savedOrder.getStockId(), savedOrder.getSalePrice(), savedOrder.getStockAmount(), OrderStatus.COMPLETE, savedOrder.getOrderType(), savedOrder.getSaleType(), savedOrder.getSaleDate());
		
		when(askOrdersRepository.updateOrderStatus(savedOrder.getOrderId(),OrderStatus.COMPLETE)).thenReturn(updatedOrder);
		
		AskOrders result = askOrdersServiceImpl.updateOrderStatus(savedOrder.getOrderId(), OrderStatus.COMPLETE);
		
		assertEquals(updatedOrder, result);
	}

	@Test 
	void testUpdateStockAmount() {
		AskOrders askOrder = new AskOrders(1, new Stocks(), 10, 100, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now());
		
		when(askOrdersRepository.save(askOrder)).thenReturn(new AskOrders(1, new Stocks(), 10, 100, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now()));
		
		AskOrders savedOrder = askOrdersServiceImpl.addAskOrder(askOrder);
		
		AskOrders updateStock = new AskOrders(savedOrder.getOrderId(), savedOrder.getStockId(), savedOrder.getSalePrice(), 500, savedOrder.getOrderStatus(), savedOrder.getOrderType(), savedOrder.getSaleType(), savedOrder.getSaleDate());
		
		when(askOrdersRepository.updateStockAmount(savedOrder.getOrderId(), 500)).thenReturn(updateStock);
		
		AskOrders result = askOrdersServiceImpl.updateStockAmount(savedOrder.getOrderId(), 500);
		
		assertEquals(updateStock, result);
	}
	
	@Test 
	void testUpdateStockPrie() {						//price is 10
		AskOrders askOrder = new AskOrders(1, new Stocks(), 10, 100, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now());
		
		when(askOrdersRepository.save(askOrder)).thenReturn(new AskOrders(1, new Stocks(), 10, 100, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now()));
		
		AskOrders savedOrder = askOrdersServiceImpl.addAskOrder(askOrder);
		
		AskOrders updateStockPrice = new AskOrders(savedOrder.getOrderId(), savedOrder.getStockId(), 300, savedOrder.getStockAmount(), savedOrder.getOrderStatus(), savedOrder.getOrderType(), savedOrder.getSaleType(), savedOrder.getSaleDate());
		
		when(askOrdersRepository.updateStockPrice(savedOrder.getOrderId(), 300)).thenReturn(updateStockPrice);
		
		AskOrders result = askOrdersServiceImpl.updateStockPrice(savedOrder.getOrderId(), 300);
		
		assertEquals(updateStockPrice, result);
	}
}
