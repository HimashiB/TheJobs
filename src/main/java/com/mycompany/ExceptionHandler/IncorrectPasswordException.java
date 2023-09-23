package com.mycompany.ExceptionHandler;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException() {
        super("Invalid Password Please Try Again!");
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }

    public IncorrectPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
