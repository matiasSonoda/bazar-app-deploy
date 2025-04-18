
package com.api.bazar.entity.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductSaleDto {
    
    private ProductSaleIdDto idProductSale;

    private ProductDto product;
    
    private SaleDto sale;
    @NotNull @Min(0)
    private Integer quantity;

    public ProductSaleDto() {
    }

    
    @Override
    public String toString() {
        return "ProductSaleDto{" + "idProductSale=" + idProductSale + ", product=" + product + ", sale=" + sale + ", quantity=" + quantity + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.idProductSale);
        hash = 71 * hash + Objects.hashCode(this.product);
        hash = 71 * hash + Objects.hashCode(this.sale);
        hash = 71 * hash + Objects.hashCode(this.quantity);
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
        final ProductSaleDto other = (ProductSaleDto) obj;
        if (!Objects.equals(this.idProductSale, other.idProductSale)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.sale, other.sale)) {
            return false;
        }
        return Objects.equals(this.quantity, other.quantity);
    }

    
    
}
