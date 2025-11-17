package org.example.stockservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.stockservice.dto.StockMarketRequest;
import org.example.stockservice.dto.StockMarketResponse;
import org.example.stockservice.service.StockMarketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockMarketController {
    
    private final StockMarketService stockMarketService;
    
    @PostMapping
    public ResponseEntity<StockMarketResponse> addStockMarket(@Valid @RequestBody StockMarketRequest request) {
        StockMarketResponse response = stockMarketService.addStockMarket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockMarket(@PathVariable Long id) {
        stockMarketService.deleteStockMarket(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/update-price/{companyId}")
    public ResponseEntity<Void> updateCompanyStockPrice(@PathVariable Long companyId) {
        stockMarketService.updateCompanyStockPrice(companyId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<List<StockMarketResponse>> getAllStockMarkets() {
        List<StockMarketResponse> stocks = stockMarketService.getAllStockMarkets();
        return ResponseEntity.ok(stocks);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<StockMarketResponse> getStockMarketById(@PathVariable Long id) {
        StockMarketResponse response = stockMarketService.getStockMarketById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<StockMarketResponse>> getStockMarketsByCompanyId(@PathVariable Long companyId) {
        List<StockMarketResponse> stocks = stockMarketService.getStockMarketsByCompanyId(companyId);
        return ResponseEntity.ok(stocks);
    }
}
