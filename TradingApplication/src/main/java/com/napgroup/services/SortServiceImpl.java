package com.napgroup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.OrderType;
import com.napgroup.models.Region;
import com.napgroup.models.SaleType;
import com.napgroup.models.Sort;

public class SortServiceImpl implements SortService {

	private Sort sort;
	@Autowired
	private AskOrdersService askOrdersService;
	
	public SortServiceImpl(Sort sort) {
		this.sort = sort;
	}
	
	@Override
	public Optional<OrderTableSuper> findAsk(OrderTableSuper bid, Region region) {
		OrderTableSuper ask = null;
		List<OrderTableSuper>  orderBook = sort.getOrderBooks().get(region).getOrders();
		
		if(bid.getOrderType() == OrderType.MARKET) {
			for(OrderTableSuper other: orderBook) {
				if(other.getSaleType() == SaleType.ASK) {
					if(bid.getSalePrice() == other.getSalePrice()) {
						ask = other;
						break;
					}
				}
			}
		} else if(bid.getOrderType() == OrderType.LIMIT) {
			for(OrderTableSuper other: orderBook) {
				if(other.getSaleType() == SaleType.ASK) {
					if(bid.getSalePrice() <= other.getSalePrice()) {
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
		if(ask.getOrderType() == OrderType.MARKET) {
			for(OrderTableSuper other: orderBook) {
				if(other.getSaleType() == SaleType.BID) {
					if(ask.getSalePrice() == other.getSalePrice()) {
						bid = other;
						break;
					}
				}
			}
		} else if(ask.getOrderType() == OrderType.LIMIT) {
			for(OrderTableSuper other: orderBook) {
				if(other.getSaleType() == SaleType.BID) {
					if(ask.getSalePrice() >= other.getSalePrice()) {
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
			// transfer stocks between users
		} else if(ask.getStockAmount() < bid.getStockAmount()) {
			// set bid to partially filled
			// set ask to filled
			// transfer stocks between users
		} else if(ask.getStockAmount() > bid.getStockAmount()) {
			// set ask to partially filled
			// set bid to filled
			// transfer stocks between users
		}
		return 0;
	}

}
