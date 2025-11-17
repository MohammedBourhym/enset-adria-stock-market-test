package org.example.companyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    
    private Long id;
    private String name;
    private LocalDate ipoDate;
    private Double currentStockPrice;
    private String domain;
}
