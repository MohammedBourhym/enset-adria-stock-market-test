package org.example.companyservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequest {
    
    @NotBlank(message = "Company name is required")
    private String name;
    
    @NotNull(message = "IPO date is required")
    private LocalDate ipoDate;
    
    @NotNull(message = "Current stock price is required")
    @Positive(message = "Stock price must be positive")
    private Double currentStockPrice;
    
    @NotBlank(message = "Domain is required")
    private String domain;
}
