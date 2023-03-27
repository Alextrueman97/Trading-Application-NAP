package com.napgroup.services;

import java.util.Optional;

public class SortServiceImpl implements SortService {

	private Sort sort;
	
	@Override
	public Optional<Order> findAsk(Order bid) {
		Order ask = null;
		Region region = bid.getRegion();
		List<OrderBook> orderBook = sort.getOrderBooks().get(region);
		for(Order other: orderBook) {
			if(other.getSaleType() == SaleType.ASK) {
				if(bid.getSalePrice() <= other.getSalePrice()) {
					ask = other;
					break;
				}
			}
		}
		return Optional.ofNullable(ask);
	}

	@Override
	public Optional<Order> findBid(Order ask) {
		Order bid = null;
		Region region = ask.getRegion();
		List<OrderBook> orderBook = sort.getOrderBooks().get(region);
		for(Order other: orderBook) {
			if(other.getSaleType() == SaleType.BID) {
				if(ask.getSalePrice() <= other.getSalePrice()) {
					bid = other;
					break;
				}
			}
		}
		return Optional.ofNullable(bid);
	}

	@Override
	public int executeTrade(Order ask, Order bid) {
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
	}

}
