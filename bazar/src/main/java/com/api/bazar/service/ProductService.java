
package com.api.bazar.service;

import com.api.bazar.entity.Product;
import com.api.bazar.entity.dto.ProductDto;
import com.api.bazar.exception.ProductNotFoundException;
import com.api.bazar.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    public ProductDto createProduct(ProductDto request){
        Product product = new Product();
        if(request.getIdProduct() !=  null){
            product.setIdProduct(request.getIdProduct());
        }
        product.setBrand(request.getBrand());
        product.setName(request.getName());
        product.setCost(request.getCost());
        product.setStock(request.getStock());
        Product aux = productRepository.save(product);
        ProductDto response = new ProductDto();
        response.setIdProduct(aux.getIdProduct());
        response.setName(aux.getName());
        response.setBrand(aux.getBrand());
        response.setCost(aux.getCost());
        response.setStock(aux.getStock());
        return response;
    }
    
    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> response = products.stream().map((product)->{
            ProductDto aux = new ProductDto();
            aux.setBrand(product.getBrand());
            aux.setIdProduct(product.getIdProduct());
            aux.setName(product.getName());
            aux.setCost(product.getCost());
            aux.setStock(product.getStock());
            return aux;
        
        }).collect(Collectors.toList());
        return response;
    }
    
    public ProductDto getProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException());
        ProductDto response = new ProductDto();
        response.setIdProduct(product.getIdProduct());
        response.setName(product.getName());
        response.setBrand(product.getBrand());
        response.setCost(product.getCost());
        response.setStock(product.getStock());
        return response;
    }
    
    public List<ProductDto> getLowStock(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> response = products.stream()
                                        .filter(predicate -> predicate.getStock() <= 5)
                                        .map(action -> {
                                                              ProductDto dto = new ProductDto();
                                                              dto.setIdProduct(action.getIdProduct());
                                                              dto.setName(action.getName());
                                                              dto.setBrand(action.getBrand());
                                                              dto.setCost(action.getCost());
                                                              dto.setStock(action.getStock());
                                                              return dto;})
                                        .collect(Collectors.toList());
        return response;
    }
    
    public void deleteProduct(Long id){
        if (!productRepository.existsById(id)){
           throw new ProductNotFoundException("The product you are trying to remove was not found: " + id);
        }
        productRepository.deleteById(id);
    }
    
    public ProductDto updateProduct(Long id, ProductDto request){
        if(request.getIdProduct() == null || !id.equals(request.getIdProduct())){
            throw new IllegalArgumentException("Invalid credentials");
        }
        if(!productRepository.existsById(request.getIdProduct())){
             throw new ProductNotFoundException("The product you are trying to remove was not found" + request.getIdProduct());
        }
        return createProduct(request);   
    }
    
}
