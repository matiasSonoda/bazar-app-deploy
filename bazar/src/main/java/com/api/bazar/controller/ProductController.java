
package com.api.bazar.controller;

import com.api.bazar.entity.Product;
import com.api.bazar.entity.dto.ProductDto;
import com.api.bazar.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto product){
        ProductDto response = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> response = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){
        ProductDto response = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
    
    @GetMapping("/low_stock")
    public ResponseEntity<List<ProductDto>> getLowStock(){
        List<ProductDto> products = productService.getLowStock();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
       productService.deleteProduct(id);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id ,@RequestBody @Valid ProductDto request){
        ProductDto response =productService.updateProduct(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
