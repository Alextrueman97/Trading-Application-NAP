package com.napgroup.models;

import java.time.LocalDateTime;

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
	@Column(name = "stock_amount")
	private int stockAmount;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	@Enumerated(EnumType.STRING)
	private OrderType orderType;
	@Enumerated(EnumType.STRING)
	private SaleType saleType;
	@Column(name = "sale_date")
	private LocalDateTime saleDate;
	@JoinColumn(name = "seller_id", referencedColumnName = "account_id")
	private int sellerId;
	@JoinColumn(name = "buyer_id", referencedColumnName = "account_id")
	private int buyerId;
	
	public OrderTable() {}
	
	public OrderTable(int orderId, int stockId, double salePrice, int stockAmount, OrderStatus orderStatus, OrderType orderType,
			SaleType saleType, LocalDateTime saleDate, int sellerId, int buyerId) {
		super();
		this.orderId = orderId;
		this.stockId = stockId;
		this.salePrice = salePrice;
		this.stockAmount = stockAmount;
		this.orderStatus = orderStatus;
		this.orderType = orderType;
		this.saleType = saleType;
		this.saleDate = saleDate;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
	}
	//constructor without orderId
	public OrderTable(int stockId, double salePrice, int stockAmount, OrderStatus orderStatus, OrderType orderType,
			SaleType saleType, LocalDateTime saleDate, int sellerId, int buyerId) {
		super();
		this.stockId = stockId;
		this.salePrice = salePrice;
		this.stockAmount = stockAmount;
		this.orderStatus = orderStatus;
		this.orderType = orderType;
		this.saleType = saleType;
		this.saleDate = saleDate;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
	}
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public SaleType getSaleType() {
		return saleType;
	}

	public void setSaleType(SaleType saleType) {
		this.saleType = saleType;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public int getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(int stockAmount) {
		this.stockAmount = stockAmount;
	}

	public LocalDateTime getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDateTime saleDate) {
		this.saleDate = saleDate;
	}
	
	
}
