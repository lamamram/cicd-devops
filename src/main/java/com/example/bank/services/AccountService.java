package com.example.bank.services;

import com.example.bank.entities.Account;

public interface AccountService {
  Account getAccountByTitle(String title);
  void withdrawal(Account account, Float amount) throws Exception;
}
