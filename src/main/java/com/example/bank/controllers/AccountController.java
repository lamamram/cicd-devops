package com.example.bank.controllers;

import java.util.List;
import javax.security.auth.login.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.bank.entities.Account;
import com.example.bank.repositories.AccountRepository;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
  @Autowired
  private AccountRepository accountRepository;

  @GetMapping
  public Iterable<Account> findAll() {
    return accountRepository.findAll();
  }

  @GetMapping("/{id}")
  public Account findOne(@PathVariable Long id) throws AccountNotFoundException {
    return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
  }

  @GetMapping("/title/{title}")
  public Account findByTitle(@PathVariable String title) {
    return accountRepository.findByTitle(title);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Account create(@RequestBody Account account) {
    return accountRepository.save(account);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) throws AccountNotFoundException {
    accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
    accountRepository.deleteById(id);
  }

  @PutMapping("/{id}")
  public Account updateaccount(@RequestBody Account account, @PathVariable Long id) throws AccountNotFoundException {
    accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
    return accountRepository.save(account);
  }

}
