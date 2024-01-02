package com.igrejasobrenatural.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Class classe) {
        super(classe.getSimpleName()+" not found");
    }
}
