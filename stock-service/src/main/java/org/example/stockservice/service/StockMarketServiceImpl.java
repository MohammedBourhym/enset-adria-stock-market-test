package org.example.stockservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.stockservice.client.CompanyServiceClient;
import org.example.stockservice.dto.StockMarketRequest;
import org.example.stockservice.dto.StockMarketResponse;
import org.example.stockservice.entity.StockMarket;
import org.example.stockservice.repository.StockMarketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StockMarketServiceImpl implements StockMarketService {
    
    private final StockMarketRepository stockMarketRepository;
    private final CompanyServiceClient companyServiceClient;
    
    @Override
    public StockMarketResponse addStockMarket(StockMarketRequest request) {
        StockMarket stockMarket = new StockMarket();
        stockMarket.setDate(request.getDate());
        stockMarket.setOpenValue(request.getOpenValue());
        stockMarket.setHighValue(request.getHighValue());
        stockMarket.setLowValue(request.getLowValue());
        stockMarket.setCloseValue(request.getCloseValue());
        stockMarket.setVolume(request.getVolume());
        stockMarket.setCompanyId(request.getCompanyId());
        
        StockMarket savedStockMarket = stockMarketRepository.save(stockMarket);
        
        // Update company stock price automatically
        updateCompanyStockPrice(request.getCompanyId());
        
        return mapToResponse(savedStockMarket);
    }
    
    @Override
    public void deleteStockMarket(Long id) {
        if (!stockMarketRepository.existsById(id)) {
            throw new RuntimeException("Stock market not found with id: " + id);
        }
        stockMarketRepository.deleteById(id);
    }
    
    @Override
    public void updateCompanyStockPrice(Long companyId) {
        try {
            StockMarket latestStock = stockMarketRepository.findLatestByCompanyId(companyId)
                    .orElseThrow(() -> new RuntimeException("No stock data found for company: " + companyId));
            
            Map<String, Double> priceUpdate = new HashMap<>();
            priceUpdate.put("currentStockPrice", latestStock.getCloseValue());
            
            companyServiceClient.updateStockPrice(companyId, priceUpdate);
            log.info("Updated stock price for company {} to {}", companyId, latestStock.getCloseValue());
        } catch (Exception e) {
            log.error("Failed to update company stock price: {}", e.getMessage());
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<StockMarketResponse> getAllStockMarkets() {
        return stockMarketRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public StockMarketResponse getStockMarketById(Long id) {
        StockMarket stockMarket = stockMarketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock market not found with id: " + id));
        return mapToResponse(stockMarket);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<StockMarketResponse> getStockMarketsByCompanyId(Long companyId) {
        return stockMarketRepository.findByCompanyId(companyId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    private StockMarketResponse mapToResponse(StockMarket stockMarket) {
        StockMarketResponse response = new StockMarketResponse();
        response.setId(stockMarket.getId());
        response.setDate(stockMarket.getDate());
        response.setOpenValue(stockMarket.getOpenValue());
        response.setHighValue(stockMarket.getHighValue());
        response.setLowValue(stockMarket.getLowValue());
        response.setCloseValue(stockMarket.getCloseValue());
        response.setVolume(stockMarket.getVolume());
        response.setCompanyId(stockMarket.getCompanyId());
        return response;
    }
}
