
package com.api.bazar.controller;

import com.api.bazar.entity.Sale;
import com.api.bazar.entity.dto.SaleDto;
import com.api.bazar.entity.dto.CustomerDto;
import com.api.bazar.entity.dto.HighestSaleDetailsDto;
import com.api.bazar.entity.dto.ProductDto;
import com.api.bazar.service.SaleService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sale")
public class SaleController {
    
    @Autowired
    private SaleService saleService;
    
    @PostMapping
    public ResponseEntity<SaleDto> saveSale(@RequestBody @Valid SaleDto saleDto){
        Sale sale = saleService.saveSale(saleDto);  
        CustomerDto customerDto = new CustomerDto();
        customerDto.setDni(sale.getCustomer().getDni());
        customerDto.setIdCustomer(sale.getCustomer().getIdCustomer());
        customerDto.setName(sale.getCustomer().getName());
        customerDto.setLastName(sale.getCustomer().getLastName());
        
        SaleDto response = new SaleDto();
        response.setCustomer(customerDto);
        response.setIdSale(sale.getIdSale());
        response.setDateSale(sale.getDateSale());
        response.setTotal(sale.getTotal());
        
        sale.getProducts().stream().forEach((product)->{
            ProductDto productDto = new ProductDto();
            productDto.setBrand(product.getProduct().getBrand());
            productDto.setCost(product.getProduct().getCost());
            productDto.setIdProduct(product.getProduct().getIdProduct());
            productDto.setName(product.getProduct().getName());
            productDto.setQuantity(product.getQuantity());        
            
            response.getProducts().add(productDto);
        });
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
  
    }
    
    @GetMapping
    public ResponseEntity<List<SaleDto>> getAllSales(){
        List<SaleDto> response = saleService.getAllSales();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SaleDto> getSale(@PathVariable Long id) {
        SaleDto response = saleService.getSale(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("/product/{id}")
    public ResponseEntity<List<ProductDto>> getProductsSale(@PathVariable Long id){
        List<ProductDto> response = saleService.getProductsSale(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("/date/{date}")
    public ResponseEntity<SaleDto> getTotalSales(@PathVariable("date") LocalDate date){
        
        SaleDto response = saleService.getTotalSales(date);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("/highestSale")
    public ResponseEntity<HighestSaleDetailsDto> getHighestSaleDetails(){
        HighestSaleDetailsDto response = saleService.getHighestSaleDetails();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id){
        saleService.deleteSale(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SaleDto> updateSale(@PathVariable Long id, @Valid @RequestBody SaleDto saleDto){
        Sale sale = saleService.updateSale(id,saleDto);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setDni(sale.getCustomer().getDni());
        customerDto.setIdCustomer(sale.getCustomer().getIdCustomer());
        customerDto.setName(sale.getCustomer().getName());
        customerDto.setLastName(sale.getCustomer().getLastName());
        
        SaleDto response = new SaleDto();
        response.setCustomer(customerDto);
        response.setIdSale(sale.getIdSale());
        response.setDateSale(sale.getDateSale());
        response.setTotal(sale.getTotal());
        
        sale.getProducts().stream().forEach((product)->{
            ProductDto productDto = new ProductDto();
            productDto.setBrand(product.getProduct().getBrand());
            productDto.setCost(product.getProduct().getCost());
            productDto.setIdProduct(product.getProduct().getIdProduct());
            productDto.setName(product.getProduct().getName());
            productDto.setQuantity(product.getQuantity());        
            
            response.getProducts().add(productDto);
        });
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
