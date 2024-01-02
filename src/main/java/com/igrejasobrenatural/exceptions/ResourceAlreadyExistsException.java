package com.igrejasobrenatural.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(Class classe) {
        super(classe.getSimpleName()+" already exists");
    }
}
