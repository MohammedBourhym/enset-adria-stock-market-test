package org.example.companyservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.companyservice.dto.CompanyRequest;
import org.example.companyservice.dto.CompanyResponse;
import org.example.companyservice.dto.PriceUpdateRequest;
import org.example.companyservice.enums.Domain;
import org.example.companyservice.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {
    
    private final CompanyService companyService;
    
    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@Valid @RequestBody CompanyRequest request) {
        CompanyResponse response = companyService.createCompany(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/price")
    public ResponseEntity<CompanyResponse> updateStockPrice(
            @PathVariable Long id, 
            @Valid @RequestBody PriceUpdateRequest request) {
        CompanyResponse response = companyService.updateStockPrice(id, request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        List<CompanyResponse> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) {
        CompanyResponse response = companyService.getCompanyById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/domain/{domain}")
    public ResponseEntity<List<CompanyResponse>> getCompaniesByDomain(@PathVariable Domain domain) {
        List<CompanyResponse> companies = companyService.getCompaniesByDomain(domain);
        return ResponseEntity.ok(companies);
    }
}
