package com.igrejasobrenatural.exceptions;

public class RateLimitException extends RuntimeException{
    public RateLimitException(){
        super("You have exhausted your API Request Quota");
    }
}
