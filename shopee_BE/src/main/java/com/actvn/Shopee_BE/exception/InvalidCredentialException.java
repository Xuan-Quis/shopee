package com.actvn.Shopee_BE.exception;



public class InvalidCredentialException extends RuntimeException{
    private String message;
    public InvalidCredentialException(String message) {
        super(message);
    }
}
