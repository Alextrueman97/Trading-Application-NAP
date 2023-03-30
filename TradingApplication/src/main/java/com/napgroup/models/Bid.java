package com.napgroup.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="bid")
public class Bid extends NewOrder {

	public Bid() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bid(int orderId, Stocks stockId, double salePrice, int stockAmount, OrderStatus orderStatus,
			OrderType orderType, SaleType saleType, LocalDateTime saleDate) {
		super(orderId, stockId, salePrice, stockAmount, orderStatus, orderType, saleType, saleDate);
		// TODO Auto-generated constructor stub
	}

	public Bid(Stocks stockId, double salePrice, int stockAmount, OrderStatus orderStatus, OrderType orderType,
			SaleType saleType, LocalDateTime saleDate) {
		super(stockId, salePrice, stockAmount, orderStatus, orderType, saleType, saleDate);
		// TODO Auto-generated constructor stub
	}
	

}
