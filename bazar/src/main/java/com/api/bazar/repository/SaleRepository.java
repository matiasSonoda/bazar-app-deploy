package com.api.bazar.repository;

import com.api.bazar.entity.Sale;
import com.api.bazar.entity.dto.SaleDto;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long>{

    public Sale save(SaleDto saleDto);
    public List<Sale> findByDateSaleBetween(LocalDateTime start, LocalDateTime end);
    
}
