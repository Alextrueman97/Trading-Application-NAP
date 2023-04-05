package com.napgroup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.napgroup.models.Stocks;
import com.napgroup.repositories.StocksRepository;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	private StocksRepository stocksRepository;

	@Override
	public int updateStockAmountById(int stockId, int stockAmount) {
		return stocksRepository.updateStockAmountById(stockId, stockAmount);
	}

	@Override
	public Stocks addStock(Stocks stock) {
		return stocksRepository.save(stock);
	}

	@Override
	public Stocks findStockById(int stockId) {
		return stocksRepository.findById(stockId).get();
	}
	
	@Override
	public List<Object[]> findAllStocksWithCompanyInfo(){
		return stocksRepository.findAllStocksWithCompanyInfo();
	}
	

}
