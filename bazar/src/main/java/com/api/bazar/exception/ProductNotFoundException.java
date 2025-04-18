
package com.api.bazar.exception;


public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(){
        super("Product not found");
    }
    public ProductNotFoundException(String message){
        super(message);
    }
    
}
