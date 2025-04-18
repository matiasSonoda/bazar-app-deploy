
package com.api.bazar.utils;

import com.api.bazar.entity.Product;
import com.api.bazar.entity.ProductSale;
import com.api.bazar.entity.ProductSaleId;
import com.api.bazar.entity.Sale;
import com.api.bazar.entity.dto.ProductDto;
import com.api.bazar.entity.dto.SaleDto;
import com.api.bazar.exception.EmptyStockException;
import com.api.bazar.exception.ProductNotFoundException;
import com.api.bazar.repository.ProductRepository;
import com.api.bazar.repository.ProductSaleRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleUtils {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductSaleRepository productSaleRepository;
    
    public List<Product> addProducts(SaleDto saleDto, Sale sale){
        List<Product> products = saleDto.getProducts().stream().map(e ->{
            Product prod = productRepository.findById(e.getIdProduct())
                    .orElseThrow(() -> new ProductNotFoundException("producto no encontrado" + e.getIdProduct()));
                    //Actualizo el stock en la bdd restandolo por cada producto que tenga la lista
                    if (saleDto.getIdSale() != null){
                        sale.getProducts().stream().filter(predicate -> predicate.getProduct().getIdProduct().equals(prod.getIdProduct()))
                                .findFirst().ifPresent(action -> prod.setStock(prod.getStock() + action.getQuantity()));
                    }
                    prod.setStock(prod.getStock() - e.getQuantity());
                    if (prod.getStock() < 0){
                        throw new EmptyStockException("No hay suficiente stock para vender el producto: " + prod.getIdProduct());
                    }
                    return prod;})
                    .collect(Collectors.toList());
        return productRepository.saveAll(products);
    }
    
    public BigDecimal totalPrice(List<Product> products, SaleDto saleDto){
        return products.stream()
                    .map(product -> {
                        ProductDto prodDto = saleDto.getProducts().stream()
                                .filter(dto -> dto.getIdProduct().equals(product.getIdProduct()))
                                .findFirst()
                                .orElseThrow(()-> new ProductNotFoundException("No se encontro el producto para cacular el valor total: " + product.getIdProduct()));

                    return product.getCost().multiply(new BigDecimal(prodDto.getQuantity()));})
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public void buildProductSale(List<Product> products, List<ProductSale> listProdSale, Sale actualSale, SaleDto saleDto){
        products.stream().forEach((prod) -> {ProductSale prodSale = new ProductSale();
                                             ProductSaleId productSaleId = new ProductSaleId();
                                             productSaleId.setProductId(prod.getIdProduct());
                                             productSaleId.setSaleId(actualSale.getIdSale());
                                             prodSale.setIdProductSale(productSaleId);
                                             prodSale.setProduct(prod);
                                             prodSale.setSale(actualSale);
                                             saleDto.getProducts().stream().filter(dto -> dto.getIdProduct().equals(prod.getIdProduct()))
                                                    .findFirst()
                                                    .ifPresent(dto -> {
                                                        if (dto.getQuantity() == null || dto.getQuantity() < 0){
                                                            throw new IllegalArgumentException("Valores invalidos para la cantidad del producto: " + dto.getIdProduct());
                                                        }
                                                    prodSale.setQuantity(dto.getQuantity());}
                                            );
                                            listProdSale.add(prodSale);
                                            });
        productSaleRepository.saveAll(listProdSale);
        if ( saleDto.getIdSale() != null){
            actualSale.getProducts().clear();
            listProdSale.forEach(actualSale.getProducts()::add);
        }
        else{
            listProdSale.forEach(actualSale.getProducts()::add);
        }
        
    }
}
