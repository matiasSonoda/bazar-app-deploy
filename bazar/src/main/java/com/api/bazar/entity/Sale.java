package com.api.bazar.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="sale")
@Getter @Setter
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSale;

    private LocalDateTime dateSale;
    
    private BigDecimal total = BigDecimal.ZERO;
    
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSale> products = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name="id_customer", nullable=false) 
    private Customer customer;

    public Sale() {
    }

    public Sale(LocalDateTime dateSale, BigDecimal total,  Customer customer) {
        this.dateSale = dateSale;
        this.total = total;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Sale{" + "idSale=" + idSale + ", dateSale=" + dateSale + ", total=" + total + ", ListProducts=" + products + ", customer=" + customer + '}';
    }
    
    
}
