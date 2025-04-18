
package com.api.bazar.entity.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDto {
    
    private Long idProduct;
    @Size(min = 2 , max = 20, message = "The name must have 2 min characters and 20 max characters")
    @NotBlank(message="Insert the name of the product")
    private String name;
    @Size(min = 2, max = 20, message = "The brand must have 2 min characters and 20 max characters")
    @NotBlank(message = "Inser the name of the brand")
    private String brand;
    @NotNull @Min(0)
    private BigDecimal cost;
    @NotNull @Min(0)
    private Long stock;
    private Integer quantity;

    public ProductDto() {
    }

    public ProductDto(Long idProduct, String name, String brand,Long stock, BigDecimal cost, Integer quantity) {
        this.idProduct = idProduct;
        this.name = name;
        this.brand = brand;
        this.stock = stock;
        this.cost = cost;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductDto{" + "idProduct=" + idProduct + ", name=" + name + ", brand=" + brand + ", cost=" + cost + ", stock=" + stock + ", quantity=" + quantity + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idProduct);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.brand);
        hash = 97 * hash + Objects.hashCode(this.cost);
        hash = 97 * hash + Objects.hashCode(this.stock);
        hash = 97 * hash + Objects.hashCode(this.quantity);
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
        final ProductDto other = (ProductDto) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.brand, other.brand)) {
            return false;
        }
        if (!Objects.equals(this.idProduct, other.idProduct)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        if (!Objects.equals(this.stock, other.stock)) {
            return false;
        }
        return Objects.equals(this.quantity, other.quantity);
    }

    
    
    
    
    
    
    
}
