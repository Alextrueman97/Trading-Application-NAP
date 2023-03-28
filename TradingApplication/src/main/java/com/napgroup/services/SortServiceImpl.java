package com.napgroup.services;

import java.util.Optional;

import com.napgroup.models.OrderBook;
import com.napgroup.models.OrderTable;
import com.napgroup.models.SaleType;
import com.napgroup.models.Sort;

public class SortServiceImpl implements SortService {

	private Sort sort;
	
	@Override
	public Optional<OrderTable> findAsk(OrderTable bid) {
		OrderTable ask = null;
		Region region = bid.getRegion();
		List<OrderBook> orderBook = sort.getOrderBooks().get(region);
		for(OrderTable other: orderBook) {
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
	public Optional<OrderTable> findBid(OrderTable ask) {
		OrderTable bid = null;
		Region region = ask.getRegion();
		List<OrderBook> orderBook = sort.getOrderBooks().get(region);
		for(OrderTable other: orderBook) {
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
	public int executeTrade(OrderTable ask, OrderTable bid) {
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
