package com.example.bank;

public class AccountNotFoundException extends RuntimeException {

  public AccountNotFoundException(String message, Throwable cause) {
      super(message, cause);
  }
  // ...
}
