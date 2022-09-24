package com.example.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidEntityAttributeException extends RuntimeException{

    public InvalidEntityAttributeException(String msg){
        super(msg);
    }
}
