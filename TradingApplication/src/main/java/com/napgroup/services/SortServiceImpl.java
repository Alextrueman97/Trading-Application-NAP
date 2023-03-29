package com.napgroup.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.napgroup.models.OrderBook;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTable;
import com.napgroup.models.OrderType;
import com.napgroup.models.Region;
import com.napgroup.models.SaleType;
import com.napgroup.models.Sort;

public class SortServiceImpl implements SortService {

	private Sort sort;
	
	public SortServiceImpl(Sort sort) {
		this.sort = sort;
	}
	
	@Override
	public Optional<OrderTable> findAsk(OrderTable bid, Region region) {
		OrderTable ask = null;
		List<OrderTable>  orderBook = sort.getOrderBooks().get(region).getOrders();
		
		if(bid.getOrderType() == OrderType.MARKET) {
			for(OrderTable other: orderBook) {
				if(other.getSaleType() == SaleType.ASK) {
					if(bid.getSalePrice() == other.getSalePrice()) {
						ask = other;
						break;
					}
				}
			}
		} else if(bid.getOrderType() == OrderType.LIMIT) {
			for(OrderTable other: orderBook) {
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
	public Optional<OrderTable> findBid(OrderTable ask, Region region) {
		OrderTable bid = null;
		List<OrderTable>  orderBook = sort.getOrderBooks().get(region).getOrders();
		if(ask.getOrderType() == OrderType.MARKET) {
			for(OrderTable other: orderBook) {
				if(other.getSaleType() == SaleType.BID) {
					if(ask.getSalePrice() == other.getSalePrice()) {
						bid = other;
						break;
					}
				}
			}
		} else if(ask.getOrderType() == OrderType.LIMIT) {
			for(OrderTable other: orderBook) {
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
		return 0;
	}

}
