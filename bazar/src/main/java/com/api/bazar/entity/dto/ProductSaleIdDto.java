package com.api.bazar.entity.dto;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
class ProductSaleIdDto {
    private Long productId;
    private Long saleId;

    public ProductSaleIdDto() {
    }

    @Override
    public String toString() {
        return "ProductSaleIdDto{" + "productId=" + productId + ", saleId=" + saleId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.productId);
        hash = 89 * hash + Objects.hashCode(this.saleId);
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
        final ProductSaleIdDto other = (ProductSaleIdDto) obj;
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        return Objects.equals(this.saleId, other.saleId);
    }
    
    
    
    
}
