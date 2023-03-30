package com.napgroup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.napgroup.models.Company;
import com.napgroup.models.OrderBook;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTable;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.OrderType;
import com.napgroup.models.Region;
import com.napgroup.models.SaleType;
import com.napgroup.models.Sort;
import com.napgroup.models.Stocks;
import com.napgroup.services.SortServiceImpl;

@ExtendWith(MockitoExtension.class)
class SortServiceTest {
	
	private SortServiceImpl sortServiceImpl;
	private List<OrderTableSuper> orders;
	private OrderBook orderBook;
	private Map<Region, OrderBook> orderBooks;
	
	@BeforeEach
	void setUp() {}
	
	@Test
	void testFindAskEmptyBid() {
		
		orders = new LinkedList<OrderTableSuper>();
		orderBook = new OrderBook(orders);
		orderBooks = new HashMap<Region, OrderBook>();
		Company company = new Company("APP", "Apple", new Stocks());
		orderBooks.put(Region.LSE, orderBook);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl = new SortServiceImpl(sort);
		OrderTableSuper bid = new OrderTableSuper();
		Optional<OrderTableSuper> ask = sortServiceImpl.findAsk(bid, Region.LSE);
		assertTrue(ask.isEmpty());
	
	}
	
	@Test
	void testFindAskNoAskAvailable() {
		
		orders = List.of(new OrderTable(1, 1, 100.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.ASK, LocalDateTime.now(), 1, 2));
		orderBook = new OrderBook(orders);
		orderBooks = new HashMap<Region, OrderBook>();
		Company company = new Company("APP", "Apple");
		orderBooks.put(Region.LSE, orderBook);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl = new SortServiceImpl(sort);
		OrderTable bid = new OrderTable(2, 1, 200, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now(), 1, 2);
		Optional<OrderTable> ask = sortServiceImpl.findAsk(bid, Region.LSE);
		assertTrue(ask.isEmpty());
		
	}
	
	@Test
	void testFindAskOneAskAvailable() {
		
		orders = List.of(new OrderTable(1, 1, 100.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.ASK, LocalDateTime.now(), 1, 2));
		orderBook = new OrderBook(orders);
		orderBooks = new HashMap<Region, OrderBook>();
		Company company = new Company("APP", "Apple");
		orderBooks.put(Region.LSE, orderBook);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl = new SortServiceImpl(sort);
		OrderTable bid = new OrderTable(2, 1, 100, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now(), 1, 2);
		Optional<OrderTable> ask = sortServiceImpl.findAsk(bid, Region.LSE);
		assertEquals(ask.get().getOrderId(), 1);
		
	}
	
	@Test
	void testFindAskOneAskAvailableLimitOrder() {
		
		orders = List.of(new OrderTable(1, 1, 100.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.ASK, LocalDateTime.now(), 1, 2));
		orderBook = new OrderBook(orders);
		orderBooks = new HashMap<Region, OrderBook>();
		Company company = new Company("APP", "Apple");
		orderBooks.put(Region.LSE, orderBook);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl = new SortServiceImpl(sort);
		OrderTable bid = new OrderTable(2, 1, 50, 10, OrderStatus.PENDING, OrderType.LIMIT, SaleType.BID, LocalDateTime.now(), 1, 2);
		Optional<OrderTable> ask = sortServiceImpl.findAsk(bid, Region.LSE);
		assertEquals(ask.get().getOrderId(), 1);
		
	}
	
	@Test
	void testFindAskMultipleAsksButOnlyOneAskMatches() {
		
		orders = List.of(
				new OrderTable(1, 1, 600.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.ASK, LocalDateTime.now(), 1, 2),
				new OrderTable(2, 1, 100.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.ASK, LocalDateTime.now(), 1, 2)
				);
		orderBook = new OrderBook(orders);
		orderBooks = new HashMap<Region, OrderBook>();
		Company company = new Company("APP", "Apple");
		orderBooks.put(Region.LSE, orderBook);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl = new SortServiceImpl(sort);
		OrderTable bid = new OrderTable(2, 1, 100.0, 5, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now(), 1, 2);
		Optional<OrderTable> ask = sortServiceImpl.findAsk(bid, Region.LSE);
		assertEquals(ask.get().getOrderId(), 2);
		
	}
	
	@Test
	void testFindBidOneBidAvailable() {
		
		orders = List.of(new OrderTable(1, 1, 100.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now(), 1, 2));
		orderBook = new OrderBook(orders);
		orderBooks = new HashMap<Region, OrderBook>();
		Company company = new Company("APP", "Apple");
		orderBooks.put(Region.LSE, orderBook);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl = new SortServiceImpl(sort);
		OrderTable ask = new OrderTable(2, 1, 100.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.ASK, LocalDateTime.now(), 1, 2);
		Optional<OrderTable> bid = sortServiceImpl.findBid(ask, Region.LSE);
		assertEquals(bid.get().getOrderId(), 1);
		
	}
	
	@Test
	void testFindBidOneBidAvailableLimitOrder() {
		
		orders = List.of(new OrderTable(1, 1, 220.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now(), 1, 2));
		orderBook = new OrderBook(orders);
		orderBooks = new HashMap<Region, OrderBook>();
		Company company = new Company("APP", "Apple");
		orderBooks.put(Region.LSE, orderBook);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl = new SortServiceImpl(sort);
		OrderTable ask = new OrderTable(2, 1, 200.0, 10, OrderStatus.PENDING, OrderType.LIMIT, SaleType.ASK, LocalDateTime.now(), 1, 2);
		Optional<OrderTable> bid = sortServiceImpl.findBid(ask, Region.LSE);
		assertEquals(bid.get().getOrderId(), 1);
		
	}
	
}
