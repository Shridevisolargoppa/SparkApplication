package com.aliens.CustomerManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerException extends Exception {

    public CustomerException(String messege)
    {
        super(messege);
    }
}