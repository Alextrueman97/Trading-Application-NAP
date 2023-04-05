package com.napgroup.services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.napgroup.models.Company;
import com.napgroup.models.OrderBook;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.OrderTableSuperComparator;
import com.napgroup.models.OrderType;
import com.napgroup.models.Region;
import com.napgroup.models.SaleType;
import com.napgroup.models.Sort;

@Service
public class SortServiceImpl implements SortService {

	@Autowired
	private AskOrdersService askOrdersService;
	@Autowired
	private BidOrderService bidOrdersService;
	@Autowired
	@Qualifier("orderTableSuperServiceImpl")
	private OrderTableSuperService orderTableSuperService;
	private Sort sort;
	
	public SortServiceImpl() {}


	public void setSort(Sort sort) {}
	
	
	
	public void updateSort() {
		Map<Region, OrderBook> orderBooks = sort.getOrderBooks();
		Company company = sort.getCompany();
		OrderBook lse = new OrderBook(orderTableSuperService.findIncompleteOrdersByCompanyAndRegion(company.getCompanyId(), Region.LSE));
		OrderBook sse = new OrderBook(orderTableSuperService.findIncompleteOrdersByCompanyAndRegion(company.getCompanyId(), Region.SSE));
		OrderBook nyse = new OrderBook(orderTableSuperService.findIncompleteOrdersByCompanyAndRegion(company.getCompanyId(), Region.NYSE));
		orderBooks.put(Region.LSE, lse);
		orderBooks.put(Region.SSE, sse);
		orderBooks.put(Region.NYSE, nyse);
		sort.setOrderBooks(orderBooks);
	}
	
	@Override
	public Optional<OrderTableSuper> findAsk(OrderTableSuper bid, Region region) {
		
		OrderTableSuper ask = null;
		List<OrderTableSuper>  orderBook = sort.getOrderBooks().get(region).getOrders();
		System.err.println(orderBook.size());
		LinkedList<OrderTableSuper> sorted = new LinkedList<>(orderBook);
		
		if(bid.getOrderType() == OrderType.MARKET) {
			for(OrderTableSuper other: sorted) {
				if(other.getSaleType() == SaleType.ASK) {
					if((other.getOrderType() == OrderType.MARKET &&  bid.getSalePrice() == other.getSalePrice()) ||
							(other.getOrderType() == OrderType.LIMIT &&  bid.getSalePrice() >= other.getSalePrice())) {
								ask = other;
								break;
					}
				}
					
			}
				
		} else if (bid.getOrderType() == OrderType.LIMIT) {
			for(OrderTableSuper other: sorted) { 
				if(other.getSaleType() == SaleType.ASK) {
					if((other.getOrderType() == OrderType.MARKET &&  bid.getSalePrice() >= other.getSalePrice()) ||
							(other.getOrderType() == OrderType.LIMIT &&  bid.getSalePrice() >= other.getSalePrice())) {
								ask = other;
								break;
					}
				}
				
			}
		}
		return Optional.ofNullable(ask);
	}
	
	@Override
	public Optional<OrderTableSuper> findBid(OrderTableSuper ask, Region region) {
		OrderTableSuper bid = null;
		List<OrderTableSuper>  orderBook = sort.getOrderBooks().get(region).getOrders();
		LinkedList<OrderTableSuper> sorted = new LinkedList<>(orderBook);
		Collections.sort(sorted, new OrderTableSuperComparator());
		if(ask.getOrderType() == OrderType.MARKET) {
			for(OrderTableSuper other: sorted) {
				if(other.getSaleType() == SaleType.BID) { 
					if((other.getOrderType() == OrderType.MARKET &&  ask.getSalePrice() == other.getSalePrice()) ||
							(other.getOrderType() == OrderType.LIMIT &&  ask.getSalePrice() <= other.getSalePrice())) {
								bid = other;
								break;
					}
					
				}
			}
		} else if (ask.getOrderType() == OrderType.LIMIT) {
			for(OrderTableSuper other: sorted) {
				if(other.getSaleType() == SaleType.BID) { 
					if((other.getOrderType() == OrderType.MARKET &&  ask.getSalePrice() <= other.getSalePrice()) ||
							(other.getOrderType() == OrderType.LIMIT &&  ask.getSalePrice() <= other.getSalePrice())) {
								bid = other;
								break;
					}
					
				}
			}	
		}
		return Optional.ofNullable(bid);
	}
	
	@Override
	public Optional<OrderTableSuper> findMatch(OrderTableSuper order, Region region) {
		return (order.getSaleType() == SaleType.BID) ? findAsk(order, region) : findBid(order, region);
	}
	
	@Override
	public Optional<OrderTableSuper> executeBidTrade(OrderTableSuper bid, OrderTableSuper ask) {
		
		int compareStock = (bid.getStockAmount() == ask.getStockAmount() ? (0) 
				: (bid.getStockAmount() < ask.getStockAmount() ? -1 : 1));
		
		double setPrice = bid.getSalePrice();
		if (bid.getOrderType() == OrderType.LIMIT && ask.getOrderType() == OrderType.MARKET) {
			setPrice = ask.getSalePrice();
		}
		
		int bidId = bid.getOrderId();
		int askId = ask.getOrderId();
		OrderTableSuper partialOrder = null;
		if(compareStock == 0) {
			bidOrdersService.updateOrderStatus(bidId, OrderStatus.COMPLETE);
			askOrdersService.updateOrderStatus(askId, OrderStatus.COMPLETE);
			bidOrdersService.updateStockPrice(bidId, setPrice);
			askOrdersService.updateStockPrice(askId, setPrice);
		} else if (compareStock == -1) {
			bidOrdersService.updateOrderStatus(bidId, OrderStatus.COMPLETE);
			askOrdersService.updateOrderStatus(askId, OrderStatus.PARTIAL);
			askOrdersService.updateStockAmount(askId, ask.getStockAmount() - bid.getStockAmount());
			partialOrder = askOrdersService.findOrderById(askId);
			bidOrdersService.updateStockPrice(bidId, setPrice);
			OrderTableSuper newAsk = new OrderTableSuper(ask.getAccountId(), ask.getStockId(), setPrice, bid.getStockAmount(), OrderStatus.COMPLETE, ask.getOrderType(), ask.getSaleType(), LocalDateTime.now());
			askOrdersService.addOrder(newAsk);
		} else if (compareStock == 1) {
			bidOrdersService.updateOrderStatus(bidId, OrderStatus.PARTIAL);
			bidOrdersService.updateStockAmount(bidId, bid.getStockAmount() - ask.getStockAmount());
			partialOrder = bidOrdersService.findOrderById(bidId);
			askOrdersService.updateOrderStatus(askId, OrderStatus.COMPLETE);
			askOrdersService.updateStockPrice(askId, setPrice);
			OrderTableSuper newBid = new OrderTableSuper(bid.getAccountId(), bid.getStockId(), setPrice, ask.getStockAmount(), OrderStatus.COMPLETE, bid.getOrderType(), bid.getSaleType(), LocalDateTime.now());
			askOrdersService.addOrder(newBid);
		}
		
		return Optional.ofNullable(partialOrder);
		
	}
	
	@Override
	public Optional<OrderTableSuper> executeAskTrade(OrderTableSuper ask, OrderTableSuper bid) {
		
		int compareStock = (ask.getStockAmount() == bid.getStockAmount() ? (0) 
				: (ask.getStockAmount() < bid.getStockAmount() ? -1 : 1));
		
		double setPrice = ask.getSalePrice();
		if (ask.getOrderType() == OrderType.LIMIT && bid.getOrderType() == OrderType.MARKET) {
			setPrice = bid.getSalePrice();
		}
		
		int askId = ask.getOrderId();
		int bidId = bid.getOrderId();
		System.err.println("ask: " + askId + " : " + ask);
		System.err.println("bid: " + bidId + " : " + bid);
		OrderTableSuper partialOrder = null;
		if(compareStock == 0) {
			askOrdersService.updateOrderStatus(askId, OrderStatus.COMPLETE);
			bidOrdersService.updateOrderStatus(bidId, OrderStatus.COMPLETE);
			askOrdersService.updateStockPrice(askId, setPrice);
			bidOrdersService.updateStockPrice(bidId, setPrice);
		} else if (compareStock == -1) {
			askOrdersService.updateOrderStatus(askId, OrderStatus.COMPLETE);
			bidOrdersService.updateOrderStatus(bidId, OrderStatus.PARTIAL);
			bidOrdersService.updateStockAmount(bidId, bid.getStockAmount() - ask.getStockAmount());
			partialOrder = bidOrdersService.findOrderById(bidId);
			askOrdersService.updateStockPrice(askId, setPrice);
			OrderTableSuper newBid = new OrderTableSuper(bid.getAccountId(), bid.getStockId(), setPrice, ask.getStockAmount(), OrderStatus.COMPLETE, bid.getOrderType(), bid.getSaleType(), LocalDateTime.now());
			askOrdersService.addOrder(newBid);
		} else if (compareStock == 1) {
			askOrdersService.updateOrderStatus(askId, OrderStatus.PARTIAL);
			askOrdersService.updateStockAmount(askId, ask.getStockAmount() - bid.getStockAmount());
			partialOrder = askOrdersService.findOrderById(askId);
			System.err.println("partial : " + partialOrder);
			bidOrdersService.updateOrderStatus(bidId, OrderStatus.COMPLETE);
			bidOrdersService.updateStockPrice(bidId, setPrice);
			OrderTableSuper newAsk = new OrderTableSuper(ask.getAccountId(), ask.getStockId(), setPrice, bid.getStockAmount(), OrderStatus.COMPLETE, ask.getOrderType(), ask.getSaleType(), LocalDateTime.now());
			askOrdersService.addOrder(newAsk);
		}
		
		return Optional.ofNullable(partialOrder);
			
	}
	
	@Override
	public Optional<OrderTableSuper> executeTrade(OrderTableSuper orderOne, OrderTableSuper orderTwo) {
		return (orderOne.getSaleType() == SaleType.BID) ? executeBidTrade(orderOne, orderTwo) : executeAskTrade(orderOne, orderTwo);
	}
	
	
	@Override
	public void executeMatchAndTrade(OrderTableSuper order, Region region) {
		
		boolean isMatch = true;
		OrderTableSuper currentOrder = null;
		if (order.getSaleType() == SaleType.ASK) {
			currentOrder = askOrdersService.addOrder(order);
		} else {
			currentOrder = bidOrdersService.addOrder(order);
		}
		while((currentOrder.getOrderStatus() != OrderStatus.COMPLETE) && isMatch) {
			Optional<OrderTableSuper> match = findMatch(currentOrder, region);
			if(match.isPresent()) {
				System.err.println("match : " + match.get());
				Optional<OrderTableSuper> nextOrder = executeTrade(currentOrder, match.get());
				if(nextOrder.isPresent()) {
					System.err.println("next : " + nextOrder.get());
					currentOrder = nextOrder.get();
				} else {
					isMatch = false;
				}
			} else {
				isMatch = false;
			}
			updateSort();
		}
	}
}
