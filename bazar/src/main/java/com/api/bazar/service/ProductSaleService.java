
package com.api.bazar.service;

import com.api.bazar.entity.ProductSale;
import com.api.bazar.repository.ProductSaleRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


public class ProductSaleService {
    
    @Autowired
    private ProductSaleRepository productSaleRepository;
    
    
    public ProductSale saveProductSale(ProductSale productSale){
        return productSaleRepository.save(productSale);
    }
    
    
}
