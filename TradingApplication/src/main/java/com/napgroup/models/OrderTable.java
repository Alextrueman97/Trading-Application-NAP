package com.napgroup.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_table")
public class OrderTable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name  = "oder_id")
	private int orderId;
	@JoinColumn(name = "stock_id")
	private int stockId;
	@Column(name = "sale_price")
	private double salePrice;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	@Enumerated(EnumType.STRING)
	private OrderType orderType;
	@Enumerated(EnumType.STRING)
	private SaleType saleType;
	@JoinColumn(name = "seller_id", referencedColumnName = "account_id")
	private int sellerId;
	@JoinColumn(name = "buyer_id", referencedColumnName = "account_id")
	private int buyerId;
	
}
