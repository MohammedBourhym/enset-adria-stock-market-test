package org.example.companyservice.service;

import lombok.RequiredArgsConstructor;
import org.example.companyservice.dto.CompanyRequest;
import org.example.companyservice.dto.CompanyResponse;
import org.example.companyservice.dto.PriceUpdateRequest;
import org.example.companyservice.entity.Company;
import org.example.companyservice.enums.Domain;
import org.example.companyservice.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {
    
    private final CompanyRepository companyRepository;
    
    @Override
    public CompanyResponse createCompany(CompanyRequest request) {
        Company company = new Company();
        company.setName(request.getName());
        company.setIpoDate(request.getIpoDate());
        company.setCurrentStockPrice(request.getCurrentStockPrice());
        company.setDomain(request.getDomain());
        
        Company savedCompany = companyRepository.save(company);
        return mapToResponse(savedCompany);
    }
    
    @Override
    public void deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Company not found with id: " + id);
        }
        companyRepository.deleteById(id);
    }
    
    @Override
    public CompanyResponse updateStockPrice(Long id, PriceUpdateRequest request) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
        
        company.setCurrentStockPrice(request.getCurrentStockPrice());
        Company updatedCompany = companyRepository.save(company);
        return mapToResponse(updatedCompany);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public CompanyResponse getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
        return mapToResponse(company);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponse> getCompaniesByDomain(Domain domain) {
        return companyRepository.findByDomain(domain).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    private CompanyResponse mapToResponse(Company company) {
        CompanyResponse response = new CompanyResponse();
        response.setId(company.getId());
        response.setName(company.getName());
        response.setIpoDate(company.getIpoDate());
        response.setCurrentStockPrice(company.getCurrentStockPrice());
        response.setDomain(company.getDomain());
        return response;
    }
}
