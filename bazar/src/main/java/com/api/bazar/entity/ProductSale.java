package com.api.bazar.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name="product_sale")
@Getter @Setter
public class ProductSale {
    
    @EmbeddedId
    private ProductSaleId idProductSale;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name="product_id")
    private Product product;
    
    @ManyToOne
    @MapsId("saleId")
    @JoinColumn(name="sale_id")
    private Sale sale;
    
    private Integer quantity;

    public ProductSale() {
    }

    public ProductSale(ProductSaleId idProductSale, Product product, Sale sale, Integer quantity) {
        this.idProductSale = idProductSale;
        this.product = product;
        this.sale = sale;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductSale{" + "idProductSale=" + idProductSale + ", product=" + product + ", sale=" + sale + ", quantity=" + quantity + '}';
    }
    
    

    
}
