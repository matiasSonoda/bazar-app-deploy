
package com.api.bazar.entity.dto;

import com.api.bazar.entity.Customer;
import com.api.bazar.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SaleDto {
    
    private Long idSale;
    @NotNull @PastOrPresent
    private LocalDateTime dateSale = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    @NotNull
    private BigDecimal total = BigDecimal.ZERO;
    
    private Integer totalSales = 0;
    
    private List<ProductDto> products = new ArrayList<>();
    
    private CustomerDto customer;
    
    public SaleDto() {
    }

    public SaleDto(Long idSale, LocalDateTime dateSale, BigDecimal total, CustomerDto customer) {
        this.idSale = idSale;
        this.dateSale = dateSale;
        this.total = total;
        this.customer = customer;
    }

   

    @Override
    public String toString() {
        return "SaleDto{" + "idSale=" + idSale + ", dateSale=" + dateSale + ", total=" + total + ", listProducts=" + products + ", customer=" + customer + '}';
    }   

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.idSale);
        hash = 71 * hash + Objects.hashCode(this.dateSale);
        hash = 71 * hash + Objects.hashCode(this.total);
        hash = 71 * hash + Objects.hashCode(this.products);
        hash = 71 * hash + Objects.hashCode(this.customer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SaleDto other = (SaleDto) obj;
        if (!Objects.equals(this.idSale, other.idSale)) {
            return false;
        }
        if (!Objects.equals(this.dateSale, other.dateSale)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        if (!Objects.equals(this.products, other.products)) {
            return false;
        }
        return Objects.equals(this.customer, other.customer);
    }

    
    
}
