package org.example.bakery.Exception;

public class NotEnoughStockException extends RuntimeException{
    public NotEnoughStockException(String message){
        super(message);
    }
}
