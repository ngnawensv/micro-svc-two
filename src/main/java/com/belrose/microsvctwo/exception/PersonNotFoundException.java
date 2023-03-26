package com.belrose.microsvctwo.exception;

public class PersonNotFoundException extends RuntimeException{

  public PersonNotFoundException() {
    super();
  }

  public PersonNotFoundException(String message) {
    super(message);
  }
}
