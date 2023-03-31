package com.napgroup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mysql.cj.protocol.Resultset;
import com.napgroup.models.Company;
import com.napgroup.models.OrderBook;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.OrderType;
import com.napgroup.models.Region;
import com.napgroup.models.SaleType;
import com.napgroup.models.Sort;
import com.napgroup.models.Stocks;
import com.napgroup.models.UserAccount;
import com.napgroup.repositories.AskOrdersRepository;
import com.napgroup.repositories.BidOrdersRepository;
import com.napgroup.services.AskOrdersServiceImpl;
import com.napgroup.services.BidOrdersServiceImpl;
import com.napgroup.services.SortServiceImpl;

@ExtendWith(MockitoExtension.class)
class SortServiceTest {
	
	
	@Mock
	private AskOrdersRepository askOrdersRepository;
	@Mock
	private BidOrdersRepository bidOrdersRepository;
	@InjectMocks
	private AskOrdersServiceImpl askOrdersService;
	@InjectMocks
	private BidOrdersServiceImpl bidOrderService;
	@InjectMocks
	private SortServiceImpl sortServiceImpl;

	
	private List<OrderTableSuper> orders;
	private OrderBook orderBook;
	private Map<Region, OrderBook> orderBooks;
	private UserAccount u1, u2;
	private Stocks s;
	
	@BeforeEach
	void setUp() {
		
		u1 = new UserAccount();
		u2 = new UserAccount();
		s = new Stocks();
		
	}
	
	@Test
	void testFindAskEmptyBid() {
		
		orders = new LinkedList<OrderTableSuper>();
		orderBook = new OrderBook(orders);
		orderBooks = new HashMap<Region, OrderBook>();
		Company company = new Company();
		orderBooks.put(Region.LSE, orderBook);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl = new SortServiceImpl(sort);
		OrderTableSuper bid = new OrderTableSuper();
		Optional<OrderTableSuper> ask = sortServiceImpl.findAsk(bid, Region.LSE);
		assertTrue(ask.isEmpty());
	
	}
	
	@Test
	void testFindAskNoAskAvailable() {
		
		orders = List.of(new OrderTableSuper(1, u1, s, 100.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.ASK, LocalDateTime.now()));
		orderBook = new OrderBook(orders);
		orderBooks = new HashMap<Region, OrderBook>();
		Company company = new Company();
		orderBooks.put(Region.LSE, orderBook);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl = new SortServiceImpl(sort);
		OrderTableSuper bid = new OrderTableSuper(2, u2, s, 200.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now());
		Optional<OrderTableSuper> ask = sortServiceImpl.findAsk(bid, Region.LSE);
		assertTrue(ask.isEmpty());
		
	}
	
	@Test
	void testFindAskOneAskAvailable() {
		
		orders = List.of(new OrderTableSuper(1, u1, s, 100.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.ASK, LocalDateTime.now()));
		orderBook = new OrderBook(orders);
		orderBooks = new HashMap<Region, OrderBook>();
		Company company = new Company();
		orderBooks.put(Region.LSE, orderBook);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl = new SortServiceImpl(sort);
		OrderTableSuper bid = new OrderTableSuper(2, u2, s, 100.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now());
		Optional<OrderTableSuper> ask = sortServiceImpl.findAsk(bid, Region.LSE);
		assertEquals(ask.get().getOrderId(), 1);
		
	}
	
	@Test
	void testFindAskOneAskAvailableLimitOrder() {
		
		orders = List.of(new OrderTableSuper(1, u1, s, 49.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.ASK, LocalDateTime.now()));
		orderBook = new OrderBook(orders);
		orderBooks = new HashMap<Region, OrderBook>();
		Company company = new Company();
		orderBooks.put(Region.LSE, orderBook);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl = new SortServiceImpl(sort);
		OrderTableSuper bid = new OrderTableSuper(2, u2, s, 50.0, 10, OrderStatus.PENDING, OrderType.LIMIT, SaleType.BID, LocalDateTime.now());
		Optional<OrderTableSuper> ask = sortServiceImpl.findAsk(bid, Region.LSE);
		assertEquals(ask.get().getOrderId(), 1);
		
	}
	
	@Test
	void testFindAskMultipleAsksButOnlyOneAskMatches() {
		

		
		orders = List.of(
				new OrderTableSuper(1, u1, s, 600.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.ASK, LocalDateTime.now()),
				new OrderTableSuper(2, u2, s, 100.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.ASK, LocalDateTime.now())
				);
		orderBook = new OrderBook(orders);
		orderBooks = new HashMap<Region, OrderBook>();
		Company company = new Company();
		orderBooks.put(Region.LSE, orderBook);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl = new SortServiceImpl(sort);
		OrderTableSuper bid = new OrderTableSuper(3, new UserAccount(), s, 100.0, 5, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now());
		Optional<OrderTableSuper> ask = sortServiceImpl.findAsk(bid, Region.LSE);
		assertEquals(ask.get().getOrderId(), 2);
		
	}
	
	@Test
	void testFindAskMultipleAsksMultipleMatches() {
		

		orders = List.of(
				new OrderTableSuper(1, u1, s, 600.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.ASK, LocalDateTime.now()),
				new OrderTableSuper(2, u2, s, 100.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.ASK, LocalDateTime.now())
				);
		orderBook = new OrderBook(orders);
		orderBooks = new HashMap<Region, OrderBook>();
		Company company = new Company();
		orderBooks.put(Region.LSE, orderBook);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl = new SortServiceImpl(sort);
		OrderTableSuper bid = new OrderTableSuper(3, new UserAccount(), s, 100.0, 5, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now());
		Optional<OrderTableSuper> ask = sortServiceImpl.findAsk(bid, Region.LSE);
		assertEquals(ask.get().getOrderId(), 2);
		
	}
	
	@Test
	void testFindBidOneBidAvailable() {
		
		orders = List.of(new OrderTableSuper(1, u1, s, 100.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now()));
		orderBook = new OrderBook(orders);
		orderBooks = new HashMap<Region, OrderBook>();
		Company company = new Company();
		orderBooks.put(Region.LSE, orderBook);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl = new SortServiceImpl(sort);
		OrderTableSuper ask = new OrderTableSuper(2, u2, s, 100.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.ASK, LocalDateTime.now());
		Optional<OrderTableSuper> bid = sortServiceImpl.findBid(ask, Region.LSE);
		assertEquals(bid.get().getOrderId(), 1);
		
	}
	
	@Test
	void testFindBidOneBidAvailableLimitOrder() {
		
		orders = List.of(new OrderTableSuper(1, u1, s, 220.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now()));
		orderBook = new OrderBook(orders);
		orderBooks = new HashMap<Region, OrderBook>();
		Company company = new Company();
		orderBooks.put(Region.LSE, orderBook);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl = new SortServiceImpl(sort);
		OrderTableSuper ask = new OrderTableSuper(2, u2, s, 200.0, 10, OrderStatus.PENDING, OrderType.LIMIT, SaleType.ASK, LocalDateTime.now());
		Optional<OrderTableSuper> bid = sortServiceImpl.findBid(ask, Region.LSE);
		assertEquals(bid.get().getOrderId(), 1);
		
	}
	
	@Test
	void testExecuteTradeExactMatch() {
		
		OrderTableSuper bid = new OrderTableSuper(1, u1, s, 200.0, 10, OrderStatus.PENDING, OrderType.MARKET, SaleType.BID, LocalDateTime.now());
		OrderTableSuper ask = new OrderTableSuper(2, u2, s, 200.0, 10, OrderStatus.PENDING, OrderType.LIMIT, SaleType.ASK, LocalDateTime.now());
		OrderTableSuper askCompleted = new OrderTableSuper(2, u2, s, 200.0, 10, OrderStatus.COMPLETE, OrderType.LIMIT, SaleType.ASK, LocalDateTime.now());
		when(askOrdersService.updateOrderStatus(ask.getOrderId(), OrderStatus.COMPLETE)).thenReturn(askCompleted);
		when(sortServiceImpl.executeTrade(ask, bid)).thenReturn(2);
		int result = sortServiceImpl.executeTrade(ask, bid);
		assertEquals(result, 2);
		
	}
	
}
