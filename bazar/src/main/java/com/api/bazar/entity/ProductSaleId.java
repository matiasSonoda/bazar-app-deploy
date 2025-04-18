
package com.api.bazar.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class ProductSaleId implements Serializable{
    private Long productId;
    private Long saleId;

    public ProductSaleId() {
    }

    
    public ProductSaleId(Long productId, Long saleId) {
        this.productId = productId;
        this.saleId = saleId;
    }

    @Override
    public String toString() {
        return "ProductSaleId{" + "productId=" + productId + ", saleId=" + saleId + '}';
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.productId);
        hash = 47 * hash + Objects.hashCode(this.saleId);
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
        final ProductSaleId other = (ProductSaleId) obj;
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        return Objects.equals(this.saleId, other.saleId);
    }

    
    
}
