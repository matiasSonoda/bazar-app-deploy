
package com.api.bazar.entity.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HighestSaleDetailsDto {
    
    private Long idSale;
    private BigDecimal total = BigDecimal.ZERO;
    private int productsQuantity = Integer.valueOf(0);
    private String name;
    private String lastName;
    
    public HighestSaleDetailsDto(){
        
    }
    
}
