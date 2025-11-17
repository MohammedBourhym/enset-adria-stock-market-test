package org.example.stockservice.repository;

import org.example.stockservice.entity.StockMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockMarketRepository extends JpaRepository<StockMarket, Long> {
    
    List<StockMarket> findByCompanyId(Long companyId);
    
    @Query("SELECT s FROM StockMarket s WHERE s.companyId = :companyId ORDER BY s.date DESC LIMIT 1")
    Optional<StockMarket> findLatestByCompanyId(Long companyId);
}
