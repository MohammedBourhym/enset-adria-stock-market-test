package org.example.stockservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMarket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Date is required")
    private LocalDate date;
    
    @NotNull(message = "Open value is required")
    @Positive(message = "Open value must be positive")
    private Double openValue;
    
    @NotNull(message = "High value is required")
    @Positive(message = "High value must be positive")
    private Double highValue;
    
    @NotNull(message = "Low value is required")
    @Positive(message = "Low value must be positive")
    private Double lowValue;
    
    @NotNull(message = "Close value is required")
    @Positive(message = "Close value must be positive")
    private Double closeValue;
    
    @NotNull(message = "Volume is required")
    @Positive(message = "Volume must be positive")
    private Long volume;
    
    @NotNull(message = "Company ID is required")
    private Long companyId;
}
