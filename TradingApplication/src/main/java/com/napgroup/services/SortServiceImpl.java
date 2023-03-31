package com.napgroup.services;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.OrderTableSuperComparator;
import com.napgroup.models.OrderType;
import com.napgroup.models.Region;
import com.napgroup.models.SaleType;
import com.napgroup.models.Sort;

@Service
public class SortServiceImpl implements SortService {

	private Sort sort;
	@Autowired
	private AskOrdersService askOrdersService;
	@Autowired
	private BidOrderService bidOrdersService;
	@Autowired
	private OrderTableSuperService orderTableSuperService;
	
	public SortServiceImpl(Sort sort) {
		this.sort = sort;
	}
	
	@Override
	public Optional<OrderTableSuper> findAsk(OrderTableSuper bid, Region region) {
		OrderTableSuper ask = null;
		List<OrderTableSuper>  orderBook = sort.getOrderBooks().get(region).getOrders();
		LinkedList<OrderTableSuper> sorted = new LinkedList<>(orderBook);
		Collections.sort(sorted, new OrderTableSuperComparator());
		if(bid.getOrderType() == OrderType.MARKET) {
			for(OrderTableSuper other: sorted) {
				if(other.getSaleType() == SaleType.ASK) {
					if(bid.getSalePrice() == other.getSalePrice()) {
						ask = other;
						break;
					}
				}
			}
		} else if(bid.getOrderType() == OrderType.LIMIT) {
			for(OrderTableSuper other: sorted) {
				if(other.getSaleType() == SaleType.ASK) {
					if(other.getSalePrice() <= bid.getSalePrice()) {
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
					if(ask.getSalePrice() == other.getSalePrice()) {
						bid = other;
						break;
					}
				}
			}
		} else if(ask.getOrderType() == OrderType.LIMIT) {
			for(OrderTableSuper other: sorted) {
				if(other.getSaleType() == SaleType.BID) {
					if(other.getSalePrice() >= ask.getSalePrice()) {
						bid = other;
						break;
					}
				}
			}
		}
		return Optional.ofNullable(bid);
	}

	@Override
	public int executeTrade(OrderTableSuper ask, OrderTableSuper bid) {
		if(ask.getStockAmount() == bid.getStockAmount() ) {
			// set orders to filled
			askOrdersService.updateOrderStatus(ask.getOrderId(), OrderStatus.COMPLETE);
			bidOrdersService.updateOrderStatus(bid.getOrderId(), OrderStatus.COMPLETE);
			// transfer stocks between users
			return 2;
		} else if(ask.getStockAmount() < bid.getStockAmount()) {
			// set bid to partially filled
			bidOrdersService.updateStockAmount(bid.getOrderId(), ask.getStockAmount());
			// set ask to filled
			askOrdersService.updateOrderStatus(ask.getOrderId(), OrderStatus.COMPLETE);
			// transfer stocks between users
			OrderTableSuper newBid = new OrderTableSuper(bid.getStockId(), bid.getSalePrice(), bid.getStockAmount() - ask.getStockAmount(), OrderStatus.PARTIAL, bid.getOrderType(), bid.getSaleType(), bid.getSaleDate());
			bidOrdersService.addOrder(newBid);
			return 3;
		} else if(ask.getStockAmount() > bid.getStockAmount()) {
			// set ask to partially filled
			askOrdersService.updateStockAmount(ask.getOrderId(), bid.getStockAmount());
			// set bid to filled
			bidOrdersService.updateOrderStatus(bid.getOrderId(), OrderStatus.COMPLETE);
			// transfer stocks between users
			OrderTableSuper newAsk = new OrderTableSuper(ask.getStockId(), ask.getSalePrice(), ask.getStockAmount() - bid.getStockAmount(), OrderStatus.PARTIAL, ask.getOrderType(), ask.getSaleType(), ask.getSaleDate());
			askOrdersService.addOrder(newAsk);
			return 3;
		}
		return 0;
	}

}
