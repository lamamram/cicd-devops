package com.example.bank.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.bank.entities.Account;
import com.example.bank.repositories.AccountRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.given;
import java.util.stream.Stream;
// utilisation de Mockito avec Spring Boot
@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
  private Account account;
  Float amount;
  
  // simulation d'un simulacre de dépôt (mêmes attributs, mêmes méthodes)
  // MAIS pas de data !!!
  @Mock
  private AccountRepository accountRepository;
  
  // injections du dépôt fake dans le service
  @InjectMocks
  private AccountServiceImpl accountServiceImpl;
  
  // FIXTURE statique: ressource nécessaire au test
  @BeforeEach
  public void generate_account(){
    // ARRANGE : GIVEN : contexte
    account = new Account("machin", 500.f, -100.f);
    // description du comportement attendu du dépôt FAKE vs dépôt vrai
    // given(accountRepository.findByTitle(account.getTitle())).willReturn(account);
    amount = 100.f;
  }

  // jeu de données
  private static Stream<Arguments> generator(){
    Arguments arg1 = Arguments.of(new Account("machin", 500.f, -100.f), 100.f);
    Arguments arg2 = Arguments.of(new Account("trucmuche", 50.f, -50.f), 120.f);
    return Stream.of(arg1, arg2);
  }
  
  //@ParameterizedTest
  //@MethodSource("generator")
  // void testWithdrawal(Account account, Float amount) {
  //   Float initBalance = account.getBalance();
  //   try {
  //     accountServiceImpl.withdrawal(account, amount);
  //     assertThat(account.getBalance()).isEqualTo(initBalance - amount);
  //   }
  //   catch (IllegalArgumentException e) {
  //     assertThat(e.getMessage()).contains("bucks");
  //   }
  // }

  // chemin depuis src/test/resources
  // normalement ce chemin est auto. dans le classpath
  // sinon pom.xml -> build -> resources 
  @ParameterizedTest
  @CsvFileSource(resources = "/fixtures/withdrawal.csv", numLinesToSkip = 1)
  void testWithdrawal(String title, Float balance, Float overdraft, Float amount) {
    Account account = new Account(title, balance, overdraft);
    Float initBalance = account.getBalance();
    try {
      accountServiceImpl.withdrawal(account, amount);
      assertThat(account.getBalance()).isEqualTo(initBalance - amount);
    }
    catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).contains("bucks");
    }
  }
}
