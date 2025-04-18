
package com.api.bazar.repository;

import com.api.bazar.entity.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale,Long>{
    
}
