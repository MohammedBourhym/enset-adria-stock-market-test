package org.example.stockservice.service;

import org.example.stockservice.dto.StockMarketRequest;
import org.example.stockservice.dto.StockMarketResponse;

import java.util.List;

public interface StockMarketService {
    
    StockMarketResponse addStockMarket(StockMarketRequest request);
    
    void deleteStockMarket(Long id);
    
    void updateCompanyStockPrice(Long companyId);
    
    List<StockMarketResponse> getAllStockMarkets();
    
    StockMarketResponse getStockMarketById(Long id);
    
    List<StockMarketResponse> getStockMarketsByCompanyId(Long companyId);
}
