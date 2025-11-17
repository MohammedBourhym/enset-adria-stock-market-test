package org.example.companyservice.service;

import org.example.companyservice.dto.CompanyRequest;
import org.example.companyservice.dto.CompanyResponse;
import org.example.companyservice.dto.PriceUpdateRequest;
import org.example.companyservice.enums.Domain;

import java.util.List;

public interface CompanyService {
    
    CompanyResponse createCompany(CompanyRequest request);
    
    void deleteCompany(Long id);
    
    CompanyResponse updateStockPrice(Long id, PriceUpdateRequest request);
    
    List<CompanyResponse> getAllCompanies();
    
    CompanyResponse getCompanyById(Long id);
    
    List<CompanyResponse> getCompaniesByDomain(Domain domain);
}
