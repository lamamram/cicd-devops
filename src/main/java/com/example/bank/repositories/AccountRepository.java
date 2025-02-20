package com.example.bank.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.bank.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
  Account findByTitle(String title);
}