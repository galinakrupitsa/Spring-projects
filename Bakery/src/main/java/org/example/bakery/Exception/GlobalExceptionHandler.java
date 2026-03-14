package org.example.bakery.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ItemNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public String handleItemNotFound(ItemNotFoundException ex){
            return ex.getMessage();
        }

        @ExceptionHandler(NotEnoughStockException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public String handleStock(NotEnoughStockException ex){
            return ex.getMessage();
        }
        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public String handleDateError(MethodArgumentTypeMismatchException ex){
            return "Invalid date format. Use yyyy-MM-dd";
        }
    }

