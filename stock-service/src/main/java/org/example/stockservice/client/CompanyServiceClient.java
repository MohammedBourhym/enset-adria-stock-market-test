package org.example.stockservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "COMPANY-SERVICE")
public interface CompanyServiceClient {
    
    @PatchMapping("/api/companies/{id}/price")
    void updateStockPrice(@PathVariable Long id, @RequestBody Map<String, Double> priceUpdate);
}
