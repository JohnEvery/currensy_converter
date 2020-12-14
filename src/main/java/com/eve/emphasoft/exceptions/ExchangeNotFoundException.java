package com.eve.emphasoft.exceptions;

public class ExchangeNotFoundException extends RuntimeException{
    public ExchangeNotFoundException(String message) {
        super(message);
    }
}
