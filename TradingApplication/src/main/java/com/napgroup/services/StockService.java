package com.napgroup.services;



import java.util.List;

import org.springframework.data.repository.query.Param;

import com.napgroup.models.Company;
import com.napgroup.models.Region;
import com.napgroup.models.Stocks;
public interface StockService {
	
	public Stocks findStockById(int stockId);
	public Stocks addStock(Stocks stock);
	public int updateStockAmountById(int stockId, int stockAmount);
	public List<Object[]> findAllStocksWithCompanyInfo();
	public Stocks findStockByCompanyAndRegion(Company company, Region region); 

}
