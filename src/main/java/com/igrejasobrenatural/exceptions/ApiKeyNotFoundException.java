package com.igrejasobrenatural.exceptions;

public class ApiKeyNotFoundException extends RuntimeException{
    public ApiKeyNotFoundException(){
        super("Missing Header: X-api-key");
    }
}
