
package com.api.bazar.exception;


public class EmptyStockException extends RuntimeException{
    public EmptyStockException(String message){
        super(message);
    }
    
}
