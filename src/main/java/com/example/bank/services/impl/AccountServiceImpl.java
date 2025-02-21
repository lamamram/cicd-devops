package com.example.bank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bank.entities.Account;
import com.example.bank.repositories.AccountRepository;
import com.example.bank.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
  
  @Autowired
  private AccountRepository accountRepository;

  

  @Override
  public Account getAccountByTitle(String title) {
    return accountRepository.findByTitle(title);
  }


  @Override
  public Account withdrawal(Account account, Float amount) throws IllegalArgumentException {
    Float newBalance = (Float)account.getBalance() - (Float)amount;
    if (newBalance < account.getOverdraft()){
      throw new IllegalArgumentException("not enough bucks !!");
    }
    account.setBalance(newBalance);
    return accountRepository.save(account);
  }
  
}
