package com.example.bank.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.bank.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
  List<Account> findByTitle(String title);
}