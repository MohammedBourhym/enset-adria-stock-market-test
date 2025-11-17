package org.example.companyservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.companyservice.enums.Domain;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Company name is required")
    private String name;
    
    @NotNull(message = "IPO date is required")
    private LocalDate ipoDate;
    
    @NotNull(message = "Current stock price is required")
    @Positive(message = "Stock price must be positive")
    private Double currentStockPrice;
    
    @NotNull(message = "Domain is required")
    @Enumerated(EnumType.STRING)
    private Domain domain;
}
