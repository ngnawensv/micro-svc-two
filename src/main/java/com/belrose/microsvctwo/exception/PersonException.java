package com.belrose.microsvctwo.exception;

public class PersonException extends RuntimeException{
    public PersonException(String message) {
        super(message);
    }

    public PersonException(Throwable throwable) {
        super(throwable);
    }
    public PersonException(String message,Throwable throwable) {
        super(message,throwable);
    }
}
