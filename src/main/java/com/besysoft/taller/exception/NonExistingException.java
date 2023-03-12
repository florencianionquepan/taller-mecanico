package com.besysoft.taller.exception;

public class NonExistingException extends RuntimeException{

    public NonExistingException(String message) {
        super(message);
    }

    public NonExistingException(String message, Throwable cause) {
        super(message, cause);
    }
}
